package com.yvan.template;

import javax.servlet.http.HttpServletRequest;

import com.lowagie.text.Document;

public interface PdfWordAjaxTemplate {

	public void execute(HttpServletRequest request, Document document) throws Exception;

}
