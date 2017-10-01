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

	public static Item extractItemInfo(final Element itemInRawHtml) {
		String image = itemInRawHtml.select("img.s-item__image-img").attr("data-src");
		if ("".equals(image)) {
			image = itemInRawHtml.select("img.s-item__image-img").attr("src");
		}
		String name = itemInRawHtml.select("h3.s-item__title").text();
		String link = itemInRawHtml.select("a.s-item__link").attr("href");
		String price = itemInRawHtml.select("span.s-item__price").text();
		String freeShipping = (itemInRawHtml.select("span.s-item__shipping") != null) ? itemInRawHtml.select("span.s-item__shipping").text() : "";
		String soldItems = (itemInRawHtml.select("span.s-item__hotness").select(".NEGATIVE") != null) ? itemInRawHtml.select("span.s-item__hotness")
				.select(".NEGATIVE").text() : "";

		return new Item.Builder().withName(name).withLink(link).withImage(image).withPostage(freeShipping).withPrice(price).withSoldItems(soldItems)
				.build();

	}

	@Override
	public List<Item> findItems(String urlCategory) {
		String categoryName = EbayFilter.extractCategoryName(urlCategory);
		System.out.println("New Category : " + categoryName);
		List<Item> items = new ArrayList<>();
		Document doc;
		Elements itemsHtmlRaw = null;
		for (int pageIndex = 1; pageIndex < 4; pageIndex++) {
			try {
				doc = Jsoup.connect(urlCategory + Pagination.pageSuffix(pageIndex)).get();
				itemsHtmlRaw = doc.select("#mainContent ul .s-item__wrapper");

				for (Element item : itemsHtmlRaw) {
					try {
						Item extractedItem = extractItemInfo(item);

						if (EbayFilter.isGoodProduct(extractedItem)) {
							items.add(extractedItem);
							System.out.println("");
							System.out.println(extractedItem.getName());
							System.out.println(extractedItem.getDetails());
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				if (items.size() > 0) {
					new IOUtilImpl().saveHTML(categoryName, items);
				}

			}
		}).run();
		System.out.println("End category ");
		return items;
	}
}
