package com.mkingstore;

public final class Item {
	private final String name;
	private final String details;
	private final String image;

	public Item(String name, String details, String image) {
		super();
		this.name = name;
		this.details = details;
		this.image = image;
	}

	public String getName() {
		return name;
	}
	public String getDetails() {
		return details;
	}
	public String getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", \r\n details=" + details + ", \r\n image=" + image + "]";
	}

	public String toHtml() {
		String htmlTemplate = HtmlTemplate.item();
		htmlTemplate = htmlTemplate.replaceAll("--item_url--", name);
		htmlTemplate = htmlTemplate.replaceAll("--item_image--", image);
		htmlTemplate = htmlTemplate.replaceAll("--item_details--", details);
		return htmlTemplate;
	}

}
