package com.mkingstore.webapp;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkingstore.CategoryStoreImpl;
import com.mkingstore.domain.Category;

@RestController
public class EbayRestController {

	@RequestMapping("/categories")
	public ResponseEntity<List<Category>> readCategories() {

		List<Category> categories = CategoryStoreImpl.getInstance().readAll();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}

	@RequestMapping("/products/{categoryName}")
	public ResponseEntity<String> readProduct(@PathVariable String categoryName) {
		return ResponseEntity.status(HttpStatus.OK).body("products");
	}
}
