package com.mkingstore;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Ini");
		EbayScanner scanner = new EbayScannerImpl();
		List<String> urlCategories = scanner.findCategories();
		for (String categoryUrl : urlCategories) {
			scanner.findItems(categoryUrl);
		}

		System.out.println("End");
	}
}
