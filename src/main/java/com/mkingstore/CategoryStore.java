package com.mkingstore;

import java.util.List;

import com.mkingstore.domain.Category;

public interface CategoryStore {

	public void add(Category category);
	public Category read(String name);
	public List<Category> readAll();

}
