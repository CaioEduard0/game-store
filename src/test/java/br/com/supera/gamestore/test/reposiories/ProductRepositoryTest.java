package br.com.supera.gamestore.test.reposiories;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.repositories.ProductRepository;
import br.com.supera.gamestore.test.utils.ProductCreator;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {
	
	@Autowired
	private ProductRepository productRepository;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(new File("src/main/resources/products.yaml"));
		Yaml yaml = new Yaml(new Constructor(Product[].class));
		Product[] products = yaml.load(inputStream);
		
		for (Product product : products) {
			productRepository.save(product);			
		}
	}
	
	@Test
	void findById_ReturnsProduct_WhenSuccessful() {
		Optional<Product> product = productRepository.findById(1L);
		
		assertThat(product).isNotEmpty().isNotNull();
		assertThat(product.get()).isInstanceOf(Product.class);
	}
	
	@Test
	void findAll_ReturnsListOfProducts_WhenSuccessful() {
		List<Product> products = productRepository.findAll();
		
		assertThat(products).isNotEmpty().isNotNull();
	}
	
	@Test
	void save_SaveAndReturnsProduct_WhenSuccessful() {
		Product product = productRepository.save(ProductCreator.createProduct());
		
		assertThat(product).isNotNull().isInstanceOf(Product.class);
	}
}
