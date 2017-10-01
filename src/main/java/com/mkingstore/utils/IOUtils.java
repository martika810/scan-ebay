package com.mkingstore.utils;

import java.util.List;

import com.mkingstore.domain.Item;

public interface IOUtils {

	public void save(String fileName, List<Item> items);

	public void saveHTML(String fileName, List<Item> items);
}
