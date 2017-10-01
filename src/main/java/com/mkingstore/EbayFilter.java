package com.mkingstore;

public class EbayFilter {

	public static boolean isGoodProduct(final String productDetails) {
		if (productDetails == null || productDetails.isEmpty())
			return false;
		boolean isSellableAndFreePostage = productDetails.contains("sold") && productDetails.contains("Free postage");
		if (!isSellableAndFreePostage)
			return false;

		float price = extractPrice(productDetails);
		boolean isGoodPrice = Float.valueOf(9.99f) < price && Float.valueOf(20.0f) > price;

		int soldItems = extractNumberSold(productDetails);
		boolean enoughSellMarket = 70 < soldItems;
		return isSellableAndFreePostage && isGoodPrice && enoughSellMarket;
	}

	public static boolean isGoodProduct(final Item itemDetails) {
		if (itemDetails == null)
			return false;
		boolean isSellableAndFreePostage = !"".equals(itemDetails.getSoldItems()) && "Free postage".equals(itemDetails.getPostage());
		if (!isSellableAndFreePostage)
			return false;

		float price = extractPrice(itemDetails.getPrice());
		boolean isGoodPrice = Float.valueOf(9.99f) < price && Float.valueOf(51.0f) > price;

		int soldItems = extractNumberSold(itemDetails.getSoldItems());
		boolean enoughSellMarket = 70 < soldItems;
		return isSellableAndFreePostage && isGoodPrice && enoughSellMarket;
	}
	public static Float extractPrice(final String productDetails) {

		try {
			String[] splitDetails = productDetails.split(SpecialCharacters.SPLIT_DETAILS);
			String priceWithCurrency = splitDetails[0];
			String price = priceWithCurrency.split(" ")[0].replace("£", "");
			return Float.valueOf(price);
		} catch (java.lang.NumberFormatException ex) {
			return 0.00f;
		}

	}

	public static int extractNumberSold(final String soldDetails) {
		if (soldDetails == null || soldDetails.isEmpty() || !soldDetails.contains("sold"))
			return 0;
		String[] firstSplit = soldDetails.split("sold")[0].split(SpecialCharacters.SPLIT_DETAILS);
		String soldItemsInText = firstSplit[firstSplit.length - 1].trim().replace(",", "");
		return Integer.parseInt(soldItemsInText);

	}

	public static String extractCategoryName(final String urlCategory) {
		String categoryName = urlCategory.split("/")[4];
		return categoryName;
	}
}
