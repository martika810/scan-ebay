package com.mkingstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EbayScannerImpl implements EbayScanner {

	@Override
	public List<String> findCategories() {
		Document doc;
		List<String> categoryLink = new ArrayList<>();
		try {
			doc = Jsoup.connect("https://www.ebay.co.uk/sch/allcategories/all-categories").get();
			Elements categories = doc.select("#mainContent div .right-section .categories-with-links");
			for (Element category : categories) {
				String href = category.attr("href");
				if (href.contains("www.ebay.co.uk/b/")) {
					categoryLink.add(href);
				}
			}
			System.out.println("categories: " + categories.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return categoryLink;
		}
		return categoryLink;

	}

	@Override
	public List<Item> findItems(String urlCategory) {
		System.out.println("New Category");
		List<Item> items = new ArrayList<>();
		Document doc;
		Elements itemsHtmlRaw = null;
		for (int pageIndex = 1; pageIndex < 4; pageIndex++) {
			try {
				doc = Jsoup.connect(urlCategory + Pagination.pageSuffix(pageIndex)).get();
				itemsHtmlRaw = doc.select("#mainContent ul .s-item__info");

				for (Element item : itemsHtmlRaw) {
					try {
						String itemLink = item.child(0).attr("href");
						Elements details = null;
						if (item.children().size() > 2) {
							details = item.child(2).children();
						} else {
							details = item.child(1).children();
						}
						String detailText = "";
						for (Element detail : details) {
							detailText += (SpecialCharacters.SPLIT_DETAILS + detail.text());
						}
						if (EbayFilter.isGoodProduct(detailText)) {
							items.add(new Item(itemLink, detailText));
							System.out.println("");
							System.out.println(itemLink);
							System.out.println(detailText);
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("End category: " + urlCategory);
		return items;
	}
}