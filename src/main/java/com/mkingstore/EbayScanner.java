package com.mkingstore;

import java.util.List;

public interface EbayScanner {

	List<String> findCategories();
	List<Item> findItems(String urlCategory);

}
