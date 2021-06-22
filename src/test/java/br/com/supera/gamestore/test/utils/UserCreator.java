package br.com.supera.gamestore.test.utils;

import br.com.supera.gamestore.entities.User;
import br.com.supera.gamestore.enums.Gender;

public class UserCreator {
	
	public static User createUser() {
		User user = new User();
		user.setName("José da Silva");
		user.setEmail("jose@gmail.com");
		user.setPassword("jose123");
		user.setAuthorities("ROLE_USER");
		user.setCpf("169.116.380-54");
		user.setPhone("99998888");
		user.setBirthDate("2000-10-10");
		user.setGender(Gender.MALE);
		user.setStreet("Rua 10");
		user.setNumber(100);
		user.setDistrict("Bairro 10");
		user.setCity("Jataí");
		user.setState("Goiás");
		user.setZipCode("77778888");
		user.setCountry("Brasil");
		return user;
	}
}
