package br.com.supera.gamestore.test.services;

import static br.com.supera.gamestore.test.utils.ProductCreator.createProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.supera.gamestore.entities.Product;
import br.com.supera.gamestore.exceptions.EmptyCartException;
import br.com.supera.gamestore.repositories.ProductRepository;
import br.com.supera.gamestore.services.ProductService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;
	
	@Mock
	private ProductRepository productRepositoryMock;
	
	@BeforeEach
	void setUp() throws FileNotFoundException {
		when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.of(createProduct()));
		
		when(productRepositoryMock.findAll()).thenReturn(List.of(createProduct()));
		
		when(productRepositoryMock.save(any(Product.class))).thenReturn(createProduct());
	}
	
	@Test
	void findProductById_ReturnsProduct_WhenSuccessful() {
		Product product = productService.findProductById(1L);
		
		assertThat(product).isNotNull().isInstanceOf(Product.class);
	}
	
	@Test
	void findProductById_ThrowsEmptyCartException_WhenDoNotHaveProductsInCart() {
		when(productRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
		
		assertThatExceptionOfType(EmptyCartException.class).isThrownBy(() -> productService.findProductById(1L));
	}
	
	@Test
	void findAllProducts_ReturnsListOfProducts_WhenSuccessful() {
		List<Product> products = productService.findAllProducts();
		
		assertThat(products).isNotEmpty().isNotNull().hasSize(1);
		assertThat(products.get(0)).isInstanceOf(Product.class);
	}
	
	@Test
	void insertProduct_SavesProduct_WhenSuccessful() {
		
		assertThatCode(() -> productService.insertProduct(createProduct())).doesNotThrowAnyException();
	}
}
