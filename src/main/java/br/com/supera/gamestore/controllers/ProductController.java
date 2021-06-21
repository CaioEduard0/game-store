package br.com.supera.gamestore.controllers;

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
	
	@GetMapping("/products")
	public String productsPage(Model model) {		
		model.addAttribute("products", productService.findAllProducts());
		return "products";
	}
	
	@GetMapping("/products/order-by-price")
	public String productsOrderedByPrice(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products);
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/products/order-by-score")
	public String productsOrderedByScore(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products, new CompareByScore());
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/products/order-by-name")
	public String productsOrderedByName(Model model) {
		List<Product> products = productService.findAllProducts();
		Collections.sort(products, new CompareByName());
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/admin/products/register-product")
	public String registerProductPage() {
		return "register-product";
	}
	
	@PostMapping("/admin/products/register-product")
	public String registerProduct(@Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return "register-product";
		}
		return "redirect:/products";
	}	
}
