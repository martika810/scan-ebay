package com.mkingstore;

public class ExtractItemInfo {

	private final float price;
	private final int sold;

	public ExtractItemInfo(float price, int sold) {
		super();
		this.price = price;
		this.sold = sold;
	}

	public float getPrice() {
		return price;
	}

	public int getSold() {
		return sold;
	}

}
