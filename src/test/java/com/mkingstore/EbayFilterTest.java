package com.mkingstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EbayFilterTest {

	@Test
	public void isGood() {
		assertTrue(EbayFilter.isGoodProduct("£19.99//Free postage//233 sold"));
		assertTrue(EbayFilter.isGoodProduct("£19.99 to £21.99//Free postage//233 sold"));
	}

	@Test
	public void isNotGood() {
		assertFalse(EbayFilter.isGoodProduct("£2.99//Free postage//233 sold"));
		assertFalse(EbayFilter.isGoodProduct("£2.99//Free postage//233 watching"));
		assertFalse(EbayFilter.isGoodProduct("£2.99//233 sold"));
		assertFalse(EbayFilter.isGoodProduct(""));
	}

	@Test
	public void extractSoldItems_valid() {
		assertEquals(233, EbayFilter.extractNumberSold("//£2.99//eBay Premium Service//Free postage//233 sold"));
	}

	@Test
	public void extractCategoryName() {
		assertEquals("Private-UK-Ireland-Property",
				EbayFilter.extractCategoryName("https://www.ebay.co.uk/b/Private-UK-Ireland-Property/93847/bn_2317917"));
	}
}
