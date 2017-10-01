package com.mkingstore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mkingstore.domain.Category;

public class CategoryStoreTest {

	@Test
	public void addCategory() {
		Category category1 = new Category.Builder("cat1").withMarketShare(100f).withNumberSellable(1).build();
		CategoryStoreImpl.getInstance().add(category1);
		assertEquals(1, CategoryStoreImpl.getInstance().readAll().size());

		Category category2 = new Category.Builder("cat2").withMarketShare(100f).withNumberSellable(1).build();
		CategoryStoreImpl.getInstance().add(category2);
		assertEquals(2, CategoryStoreImpl.getInstance().readAll().size());

	}

}
