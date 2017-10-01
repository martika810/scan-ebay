package com.mkingstore.scanners;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mkingstore.EbayFilter;
import com.mkingstore.domain.Item;

public class ProductScannerImpl implements ProductScanner {

	@Override
	public List<Item> extractProductInfo(Elements productInHtml) {
		List<Item> items = new ArrayList<>();
		for (Element item : productInHtml) {
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
		return items;
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

}
