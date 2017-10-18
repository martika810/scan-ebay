package com.mkingstore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.OS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mkingstore.domain.Category;
import com.mkingstore.domain.Item;
import com.mkingstore.scanners.ProductScannerImpl;

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Macintosh;

public class EbayScannerImpl implements EbayScanner {

	@Override
	public List<String> findCategories() {
		Document doc;
		List<String> categoryLink = new ArrayList<>();
		try {
			doc = Jsoup.connect("https://www.ebay.co.uk/sch/allcategories/all-categories")
					.timeout(30000)
					.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:49.0) Gecko/20100101 Firefox/49.0")
					.ignoreHttpErrors(true).followRedirects(true).ignoreContentType(true).get();
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
		String categoryName = EbayFilter.extractCategoryName(urlCategory);
		System.out.println("New Category : " + categoryName);
		List<Item> items = new ArrayList<>();
		Document doc;
		Elements itemsHtmlRaw = null;
		for (int pageIndex = 1; pageIndex < 4; pageIndex++) {
			try {
				Thread.sleep(300);
				doc = Jsoup.connect(urlCategory + Pagination.pageSuffix(pageIndex)).get();
				itemsHtmlRaw = doc.select("#mainContent ul .s-item__wrapper");

				List<Item> itemsInPage = new ProductScannerImpl().extractProductInfo(itemsHtmlRaw);
				items.addAll(itemsInPage);

			} catch (InterruptedException |IOException e) {
				e.printStackTrace();
			}

		}

		if (items.size() > 0) {
			float marketShare = new ProductScannerImpl().calculateMarketShare(items);
			Category newCategory = new Category.Builder(categoryName).withNumberSellable(items.size()).withMarketShare(marketShare)
					.withProducts(items).build();
			CategoryStoreImpl.getInstance().add(newCategory);
		}
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// if (items.size() > 0) {
		// new IOUtilImpl().saveHTML(categoryName, items);
		// }
		//
		// }
		// }).run();

		System.out.println("End category ");
		return items;
	}
}
