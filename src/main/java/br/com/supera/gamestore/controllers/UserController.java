package br.com.supera.gamestore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.exceptions.RepeatedEmailException;
import br.com.supera.gamestore.services.UserService;

@Controller
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/sign-up")
	public String signUpPage() {
		return "sign-up";
	}
	
	@PostMapping("/sign-up")
	public String createAccount(@Valid User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<String> messages = new ArrayList<>();
			
			bindingResult.getFieldErrors().forEach((error) -> {
				int birthDateError = error.getField().compareTo("birthDate");
				int numberError = error.getField().compareTo("number");
				if (birthDateError == 0) {
					messages.add("The birth date cannot be empty!");
					return;
				} 			
				if (numberError == 0) {
					messages.add("The number cannot be empty, and must be between 1 and 10000!");
					return;
				}
				messages.add(error.getDefaultMessage());
			});
			model.addAttribute("errors", messages);
			return "sign-up";
		}		
		try {
			userService.insertUser(user);
			return "redirect:/login";
		} catch (RepeatedEmailException error) {
			model.addAttribute("exception", error.getLocalizedMessage());
			return "sign-up";
		}
	}
}
