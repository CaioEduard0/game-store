package br.com.supera.gamestore.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.exceptions.EmptyCartException;
import br.com.supera.gamestore.repositories.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product findProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new EmptyCartException());
	}
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public void insertProduct(Product product) {
		productRepository.save(product);
	}	
}
