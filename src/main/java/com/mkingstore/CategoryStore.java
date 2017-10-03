package com.mkingstore;

import java.util.List;

import com.mkingstore.domain.Category;
import com.mkingstore.domain.Item;

public interface CategoryStore {

	public void add(Category category);
	public Category read(String name);
	public List<Category> readAll();
	public List<Item> readProducts(String categoryName);

}
