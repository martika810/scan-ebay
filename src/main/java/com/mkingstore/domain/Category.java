package com.mkingstore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
	private final String name;
	private final int numberSellableItems;
	private final float marketShare;
	private final List<Item> products;

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public int getNumberSellableItems() {
		return numberSellableItems;
	}

	@JsonProperty
	public float getMarketShare() {
		return marketShare;
	}

	@JsonIgnore
	public List<Item> getProducts() {
		return products;
	}

	public static class Builder {
		private String name;
		private int numberSellableItems;
		private float marketShare;
		private List<Item> products;

		public Builder(String name) {
			this.name = name;
		}
		public Builder withNumberSellable(int numberSellableItems) {
			this.numberSellableItems = numberSellableItems;
			return this;
		}

		public Builder withMarketShare(float marketShare) {
			this.marketShare = marketShare;
			return this;
		}

		public Builder withProducts(List<Item> products) {
			this.products = products;
			return this;
		}

		public Category build() {
			return new Category(this);
		}

	}

	public Category(Builder builder) {
		this.name = builder.name;
		this.numberSellableItems = builder.numberSellableItems;
		this.marketShare = builder.marketShare;
		this.products = builder.products;
	}

}
