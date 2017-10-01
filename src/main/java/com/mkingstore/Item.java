package com.mkingstore;

public final class Item {
	private final String name;
	private final String link;
	private final String price;
	private final String postage;
	private final String soldItems;
	private final String image;

	public Item(Builder build) {
		super();
		this.name = build.name;
		this.link = build.link;
		this.price = build.price;
		this.postage = build.postage;
		this.soldItems = build.soldItems;
		this.image = build.image;
	}

	public static class Builder {
		private String name;
		private String link;
		private String price;
		private String postage;
		private String soldItems;
		private String image;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withLink(String link) {
			this.link = link;
			return this;
		}
		public Builder withPrice(String price) {
			this.price = price;
			return this;
		}
		public Builder withPostage(String postage) {
			this.postage = postage;
			return this;
		}

		public Builder withImage(String image) {
			this.image = image;
			return this;
		}
		public Builder withSoldItems(String soldItems) {
			this.soldItems = soldItems;
			return this;
		}

		public Item build() {
			return new Item(this);
		}
	}

	public String getName() {
		return name;
	}
	public String getLink() {
		return link;
	}
	public String getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}

	public String getPostage() {
		return postage;
	}

	public String getSoldItems() {
		return soldItems;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", \r\n details=" + getDetails() + ", \r\n image=" + image + "]";
	}

	public String getDetails() {
		return price + " // " + postage + " // " + soldItems;
	}

	public String toHtml() {
		String htmlTemplate = HtmlTemplate.item();
		htmlTemplate = htmlTemplate.replaceAll("--item_url--", link);
		htmlTemplate = htmlTemplate.replaceAll("--item_name--", name);
		htmlTemplate = htmlTemplate.replaceAll("--item_image--", image);
		htmlTemplate = htmlTemplate.replaceAll("--item_details--", getDetails());
		return htmlTemplate;
	}

}
