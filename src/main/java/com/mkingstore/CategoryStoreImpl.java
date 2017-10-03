package com.mkingstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkingstore.domain.Category;
import com.mkingstore.domain.Item;

public class CategoryStoreImpl implements CategoryStore {

	private Map<String, Category> categoryMap;
	private List<Category> sortedCategories;

	private static CategoryStoreImpl INSTANCE;

	private CategoryStoreImpl() {
		this.categoryMap = new HashMap<>();
		this.sortedCategories = new ArrayList<>();

	}

	private Comparator<Category> categoryComparator = new Comparator<Category>() {

		@Override
		public int compare(Category o1, Category o2) {
			// TODO Auto-generated method stub
			return o2.getNumberSellableItems() - o1.getNumberSellableItems();
		}

	};
	public static synchronized CategoryStore getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CategoryStoreImpl();

		}
		return INSTANCE;

	}

	@Override
	public void add(Category category) {
		categoryMap.put(category.getName(), category);
		Category categoryWithoutProducts = new Category.Builder(category.getName()).withNumberSellable(category.getNumberSellableItems())
				.withMarketShare(category.getMarketShare()).build();
		sortedCategories.add(categoryWithoutProducts);
		Collections.sort(sortedCategories, categoryComparator);
	}

	@Override
	public Category read(String name) {

		return categoryMap.get(name);
	}

	@Override
	public List<Category> readAll() {
		return sortedCategories;
	}

	@Override
	public List<Item> readProducts(String categoryName) {
		return categoryMap.get(categoryName).getProducts();

	}

}
