package com.mkingstore.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.mkingstore.domain.Item;

public class IOUtilImpl implements IOUtils {

	@Override
	public void save(String fileName, List<Item> items) {
		File file = new File("./results/" + fileName + ".txt");
		String fullString = "";
		for (Item item : items) {
			fullString += item.toString() + "\r\n ";
		}
		try {
			FileUtils.writeStringToFile(file, fullString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void saveHTML(String fileName, List<Item> items) {
		File file = new File("./results/" + items.size() + "_" + fileName + ".html");
		String fullString = "";
		for (Item item : items) {
			fullString += item.toHtml();
		}
		try {
			String fullHTML = HtmlTemplate.getSkeleton();
			fullHTML = fullHTML.replaceAll("--CATEGORY_NAME--", fileName);
			fullHTML = fullHTML.replaceAll("--BODY--", fullString);

			FileUtils.writeStringToFile(file, fullHTML);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
