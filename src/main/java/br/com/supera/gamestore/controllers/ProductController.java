package br.com.supera.gamestore.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.services.ProductService;
import br.com.supera.gamestore.utils.CompareByName;
import br.com.supera.gamestore.utils.CompareByScore;

@Controller
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/")
	public String productsPage(Model model) {		
		model.addAttribute("products", productService.findAllProducts());
		return "index";
	}
	
	@GetMapping("products/order-by-price")
	public String productsOrderedByPricePage(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products);
		model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("/products/order-by-score")
	public String productsOrderedByScorePage(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products, new CompareByScore());
		model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("/products/order-by-name")
	public String productsOrderedByNamePage(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products, new CompareByName());
		model.addAttribute("products", products);
		return "index";
	}
	
	@GetMapping("/admin/products/register-product")
	public String registerProductPage() {
		return "register-product";
	}
	
	@PostMapping("/admin/products/register-product")
	public String registerProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<String> messages = new ArrayList<>();
			
			bindingResult.getFieldErrors().forEach((error) -> {
				int scoreError = error.getField().compareTo("score");
				if (scoreError == 0) {
					messages.add("The score cannot be empty, and must be between 1 and 1000!");
					return;
				}
				messages.add(error.getDefaultMessage());
			});
			model.addAttribute("errors", messages);
			return "register-product";
		}
		productService.insertProduct(product);
		return "redirect:/";
	}
}
