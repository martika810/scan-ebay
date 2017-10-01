package com.mkingstore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlTemplate {

	public static String getSkeleton() throws IOException {
		return new String(Files.readAllBytes(Paths.get("./template.html")));
	}

	public static String item() {

		try {
			return new String(Files.readAllBytes(Paths.get("./template_item.html")));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
}
