package com.mkingstore.scanners;

import java.util.List;

import org.jsoup.select.Elements;

import com.mkingstore.domain.Item;

public interface ProductScanner {

	public List<Item> extractProductInfo(Elements productInHtml);
	public float calculateMarketShare(List<Item> products);
}
