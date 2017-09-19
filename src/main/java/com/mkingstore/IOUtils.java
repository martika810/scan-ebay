package com.mkingstore;

import java.util.List;

public interface IOUtils {

	public void save(String fileName, List<Item> items);

	public void saveHTML(String fileName, List<Item> items);
}
