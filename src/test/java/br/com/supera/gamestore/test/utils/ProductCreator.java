package br.com.supera.gamestore.test.utils;

import java.math.BigDecimal;

import br.com.supera.gamestore.entities.Product;

public class ProductCreator {
	
	public static Product createProduct() {
		Product product = new Product();
		product.setName("Donkey Kong 3");
		product.setPrice(new BigDecimal(100));
		product.setScore((short) 500);
		product.setImage("donkeykong3image");
		return product;
	}
}
