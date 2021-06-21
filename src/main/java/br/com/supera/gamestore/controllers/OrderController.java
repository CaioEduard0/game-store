package br.com.supera.gamestore.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.supera.gamestore.entities.Order;
import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.services.OrderService;
import br.com.supera.gamestore.services.ProductService;
import br.com.supera.gamestore.services.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private final OrderService orderService;
	private final ProductService productService;
	private final UserService userService;
	
	public OrderController(OrderService orderService, ProductService productService, UserService userService) {
		this.orderService = orderService;
		this.productService = productService;
		this.userService = userService;
	}
	
	@PostMapping("/new-order")
	public String orderPage(Long[] ids, Model model) {
		List<Product> products = new ArrayList<>();
		for (Long id: ids) {
			products.add(productService.findProductById(id));			
		}
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal total = new BigDecimal(0);
		BigDecimal shipping = new BigDecimal(0);
		
		for (Product product : products) {
			subTotal = subTotal.add(product.getPrice());
			shipping = shipping.add(new BigDecimal(10));
		}
		if (subTotal.compareTo(new BigDecimal(250)) == 1 || subTotal.compareTo(new BigDecimal(250)) == 0) {
			total = total.add(subTotal);
			shipping = new BigDecimal(0);
		} else {
			total = total.add(subTotal).add(shipping);
		}
		model.addAttribute("subTotal", subTotal);
		model.addAttribute("shipping", shipping);
		model.addAttribute("total", total);
		model.addAttribute("products", products);
		return "order";
	}
	
	@GetMapping("/new-order")
	public String order() {
		return "order";
	}
	
	@PostMapping("/register-order")
	public String registerOrder(@AuthenticationPrincipal UserDetails userDetails, Order order) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		orderService.insertOrder(order, user);
		return "redirect:/products";
	}
}
