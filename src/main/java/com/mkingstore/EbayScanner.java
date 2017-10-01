package com.mkingstore;

import java.util.List;

import com.mkingstore.domain.Item;

public interface EbayScanner {

	List<String> findCategories();
	List<Item> findItems(String urlCategory);

}
