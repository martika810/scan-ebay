package com.mkingstore;

public class FindItemTask implements Runnable {

	private final String categoryUrl;

	public FindItemTask(String categoryUrl) {
		this.categoryUrl = categoryUrl;
	}

	@Override
	public void run() {
		new EbayScannerImpl().findItems(categoryUrl);

	}

}
