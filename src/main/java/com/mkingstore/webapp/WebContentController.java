package com.mkingstore.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebContentController {

	@RequestMapping("/home")
	public String page() {
		return "homepage";

	}

	@RequestMapping("/find_products")
	public String taskcontainer() {
		return "products";
	}
	//
	// @RequestMapping("/task_add_new")
	// public String taskAddContainer() {
	// return "html/task_add_new";
	// }

}