package com.yvan.template;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public interface ExcelAjaxTemplate {

	public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception;

}
