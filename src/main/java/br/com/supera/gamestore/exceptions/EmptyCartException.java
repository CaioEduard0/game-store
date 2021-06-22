package br.com.supera.gamestore.exceptions;

public class EmptyCartException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmptyCartException() {
		super("You cannot make a order without select at least one product!");
	}
}
