package com.mkingstore;

public final class Item {
	private final String name;
	private final String details;

	public Item(String name, String details) {
		super();
		this.name = name;
		this.details = details;
	}

	public String getName() {
		return name;
	}
	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", \r\n details=" + details + "]";
	}

	public String toHtml() {
		String htmlTemplate = HtmlTemplate.item();
		htmlTemplate = htmlTemplate.replaceAll("--item_url--", name);
		htmlTemplate = htmlTemplate.replaceAll("--item_details--", details);
		return htmlTemplate;
	}

}
