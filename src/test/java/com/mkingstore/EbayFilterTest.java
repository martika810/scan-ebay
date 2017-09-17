package com.mkingstore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EbayFilterTest {

	@Test
	public void isGood() {
		assertTrue(EbayFilter.isGoodProduct("//£20.99//eBay Premium Service//Free postage//233 sold"));
		assertTrue(EbayFilter.isGoodProduct("//£20.99 to £21.99//eBay Premium Service//Free postage//233 sold"));
	}

	@Test
	public void isNotGood() {
		assertFalse(EbayFilter.isGoodProduct("//£2.99//eBay Premium Service//Free postage//233 sold"));
		assertFalse(EbayFilter.isGoodProduct("//£2.99//eBay Premium Service//Free postage//233 watching"));
		assertFalse(EbayFilter.isGoodProduct("//£2.99//eBay Premium Service//233 sold"));
		assertFalse(EbayFilter.isGoodProduct(""));
	}

	@Test
	public void extractSoldItems_valid() {
		assertEquals(233, EbayFilter.extractNumberSold("//£2.99//eBay Premium Service//Free postage//233 sold"));
	}

}
