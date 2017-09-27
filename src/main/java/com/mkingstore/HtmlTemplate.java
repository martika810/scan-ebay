package com.mkingstore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlTemplate {

	public static String getSkeleton() throws IOException {
		return new String(Files.readAllBytes(Paths.get("./template.html")));
	}

	public static String item() {
		return "<br/>" + "<a href=--item_url--><img src=\"--item_image--\" /></a> <br/>" + "<p>--item_details--</p>" + "<br/>";
	}
}
