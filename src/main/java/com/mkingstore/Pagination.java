package com.mkingstore;

public class Pagination {

	public static String pageSuffix(int page) {
		if (page == 1) {
			return "";
		}
		return "?_pgn=" + page;
	}

}
