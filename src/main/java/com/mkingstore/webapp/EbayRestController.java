package com.mkingstore.webapp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mkingstore.CategoryStoreImpl;
import com.mkingstore.domain.Category;
import com.mkingstore.domain.Item;

@Controller
public class EbayRestController {

	@RequestMapping("/categories")
	public ResponseEntity<List<Category>> readCategories() {

		List<Category> categories = CategoryStoreImpl.getInstance().readAll();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}

	@RequestMapping("/products/{categoryName}")
	public ResponseEntity<List<Item>> readProduct(@PathVariable String categoryName) {

		List<Item> products = CategoryStoreImpl.getInstance().readProducts(categoryName);
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
}
