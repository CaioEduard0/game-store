package br.com.supera.gamestore.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.repositories.ProductRepository;

@Configuration
public class SavingProducts implements CommandLineRunner {
	
	private final ProductRepository productRepository;

	public SavingProducts(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		InputStream inputStream = new FileInputStream(new File("src/main/resources/products.yaml"));
		Yaml yaml = new Yaml(new Constructor(Product[].class));
		Product[] products = yaml.load(inputStream);
		
		for (Product product : products) {
			productRepository.save(product);			
		}
	}
}
