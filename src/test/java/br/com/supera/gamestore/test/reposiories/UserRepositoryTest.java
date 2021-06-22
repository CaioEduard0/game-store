package br.com.supera.gamestore.test.reposiories;

import static br.com.supera.gamestore.test.utils.UserCreator.createUser;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.repositories.UserRepository;

@ActiveProfiles("test")
@DataJpaTest
class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	private User user;
	
	@BeforeEach
	void setUp() {
		this.user = userRepository.save(createUser());
	}
	
	@Test
	void findByEmail_ReturnsUser_WhenSuccessful() {
		Optional<User> userTest = userRepository.findByEmail(user.getEmail());
		
		assertThat(userTest).isNotEmpty().isNotNull();
		assertThat(userTest.get()).isEqualTo(user);
	}
	
	@Test
	void save_SaveAndReturnsUser_WhenSuccessful() {
		User userSaved = userRepository.save(user);
		
		assertThat(userSaved).isNotNull();
		assertThat(userSaved.getId()).isNotNull();
		assertThat(userSaved.getName()).isEqualTo(user.getName());
	}
}
