package br.com.supera.gamestore.utils;

import java.util.Comparator;

import br.com.supera.gamestore.entities.Product;

public class CompareByName implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		return p1.getName().compareTo(p2.getName());
	}
}
