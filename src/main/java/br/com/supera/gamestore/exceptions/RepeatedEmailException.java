package br.com.supera.gamestore.exceptions;

public class RepeatedEmailException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RepeatedEmailException(String email) {
		super("The e-mail " + email + " is already registered!");
	}
}
