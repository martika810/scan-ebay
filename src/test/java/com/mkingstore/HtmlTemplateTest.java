package com.mkingstore;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class HtmlTemplateTest {

	@Test
	public void getTemplate() throws IOException {
		String htmlTemplate = HtmlTemplate.getSkeleton();
		assertTrue(htmlTemplate != null);
	}

}
