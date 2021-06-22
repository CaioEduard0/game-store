package br.com.supera.gamestore.test.services;

import static br.com.supera.gamestore.test.utils.UserCreator.createUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.exceptions.RepeatedEmailException;
import br.com.supera.gamestore.repositories.UserRepository;
import br.com.supera.gamestore.services.UserService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepositoryMock;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoderMock;
	
	@BeforeEach
	void setUp() {
		
		when(userRepositoryMock.findByEmail(anyString())).thenReturn(Optional.of(createUser()));
		
		when(userRepositoryMock.save(any(User.class))).thenReturn(createUser());
		
		when(bCryptPasswordEncoderMock.encode(anyString())).thenReturn(createUser().getPassword());
	}
	
	@Test
	void findUserByEmail_ReturnsUser_WhenSuccessful() {
		User userTest = userService.findUserByEmail("jose@gmail.com");
		
		assertThat(userTest).isNotNull();
		assertThat(userTest.getEmail()).isEqualTo(createUser().getEmail());
	}
	
	@Test
	void insertUser_SavesUser_WhenSuccessful() {
		when(userRepositoryMock.findByEmail(anyString())).thenReturn(Optional.empty());

		assertThatCode(() -> userService.insertUser(createUser())).doesNotThrowAnyException();
	}
	
	@Test
	void insertUser_ThrowsRepeatedEmailException_WhenEmailIsAlreadyRegistered() {		
		assertThatExceptionOfType(RepeatedEmailException.class).isThrownBy(() -> userService.insertUser(createUser()));
	}
}
