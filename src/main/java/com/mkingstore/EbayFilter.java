package com.mkingstore;

public class EbayFilter {

	public static boolean isGoodProduct(final String productDetails) {
		if (productDetails == null || productDetails.isEmpty())
			return false;
		boolean isSellableAndFreePostage = productDetails.contains("sold") && productDetails.contains("Free postage");
		if (!isSellableAndFreePostage)
			return false;

		float price = extractPrice(productDetails);
		boolean isGoodPrice = Float.valueOf(10.0f) < price && Float.valueOf(40.0f) > price;

		int soldItems = extractNumberSold(productDetails);
		boolean enoughSellMarket = 100 < soldItems;
		return isSellableAndFreePostage && isGoodPrice && enoughSellMarket;
	}
	public static Float extractPrice(final String productDetails) {

		try {
			String[] splitDetails = productDetails.split(SpecialCharacters.SPLIT_DETAILS);
			String priceWithCurrency = splitDetails[1];
			String price = priceWithCurrency.split(" ")[0].replace("£", "");
			return Float.valueOf(price);
		} catch (java.lang.NumberFormatException ex) {
			return 0.00f;
		}

	}

	public static int extractNumberSold(final String productDetails) {
		String[] firstSplit = productDetails.split("sold")[0].split(SpecialCharacters.SPLIT_DETAILS);
		String soldItemsInText = firstSplit[firstSplit.length - 1].trim().replace(",", "");
		return Integer.parseInt(soldItemsInText);

	}

}
