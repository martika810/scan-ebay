package com.mkingstore;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class EbayReadAllCategoriesTask implements Runnable {

	@Override
	public void run() {
		System.out.println("Ini");

		List<String> urlCategories = new EbayScannerImpl().findCategories();
		CompletableFuture<?> promises[] = new CompletableFuture[urlCategories.size()];

		for (int i = 0; i < urlCategories.size(); i++) {

			CompletableFuture<Void> promise = CompletableFuture.runAsync(new FindItemTask(urlCategories.get(i)));
			promises[i] = promise;

		}

		CompletableFuture.allOf(promises).join();
		System.out.println("End");

	}

}
