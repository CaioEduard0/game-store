package br.com.supera.gamestore.utils;

import java.util.Comparator;

import br.com.supera.gamestore.entities.Product;

public class CompareByScore implements Comparator<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		if (p1.getScore() < p2.getScore()) {
			return 1;
		} else if (p1.getScore() > p2.getScore()) {
			return -1;
		}
		return 0;
	}
}
