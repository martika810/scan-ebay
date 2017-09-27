package com.mkingstore;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class EbayScannerTest {

	@Test
	public void extractItemInfo() throws IOException {
		Document doc = Jsoup.connect("https://www.ebay.co.uk/b/Travel-Backpacks-Rucksacks/16081/bn_9536129?rt=nc&_dmd=1").get();
		Elements itemsInRawHtml = doc.select("#mainContent ul .s-item__wrapper");
		Item item = EbayScannerImpl.extractItemInfo(itemsInRawHtml.get(4));
		assertTrue(!item.getDetails().isEmpty());
		assertTrue(!item.getImage().isEmpty());
		assertTrue(!item.getName().isEmpty());
	}

}
