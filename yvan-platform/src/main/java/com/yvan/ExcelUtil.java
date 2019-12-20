package com.yvan;

import java.io.*;
import java.text.MessageFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yvan.common.util.Common;
import com.yvan.platform.RestException;
import com.yvan.template.ExcelAjaxTemplate;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil{
	public static Logger logger = Logger.getLogger(ExcelUtil.class.getName());
	public final static String SHEET_NAME = "sheet1";

	//Bom货品Excel数据导出
	public static void excelExportBomByDataList(HttpServletResponse response,
	                                            String fileName,
	                                            Map<String, Object> rootMap,
	                                            List<LinkedHashMap<String, String>> dataMapList) throws Exception {
		ServletOutputStream outputStream = null;

		try {
			response.reset();
			response.setHeader("Content-Type","application/octet-stream" );
			response.setHeader("Connection", "close");
			response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls" );

			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "*");
			response.addHeader("Access-Control-Max-Age", "100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Allow-Credentials", "false");
//			response.setContentType("octets/stream;charset=utf-8");
//			response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			outputStream = response.getOutputStream();

			//1. 生成Excel文件对象<HSSFWorkbook>: 业务查询数据结构体-Excel对象
			HSSFWorkbook hssfWorkbook = ExcelUtil.loadExcelBomByDataList(rootMap, dataMapList);
			hssfWorkbook.write(outputStream);
		} catch (Exception e) {

		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	//Bom货品Excel数据导出
	public static HSSFWorkbook loadExcelBomByDataList(Map<String, Object> rootMap, List<LinkedHashMap<String, String>> dataMapList) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(SHEET_NAME);

		//1. 设置货品名称行
		//productCode 货品编码
		String productCode = (String)rootMap.get("productCode");
		//productName 货品名称
		String productName = (String)rootMap.get("productName");

		String titleTemp = "{0}/{1} 货品明细表";
		String titleStr = MessageFormat.format(titleTemp, productCode, productName);

		//设置第一行样式-单元格(垂直,水平)居中：
		HSSFCellStyle firstRowStyle = hssfWorkbook.createCellStyle();
		firstRowStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
		firstRowStyle.setAlignment(HorizontalAlignment.CENTER);//水平

		HSSFRow firstRow = sheet.createRow(0);
		firstRow.setHeight((short) 500);
		//创建Excel单元格
		HSSFCell firstRowCell = firstRow.createCell(0);
		firstRowCell.setCellValue(titleStr);
		firstRowCell.setCellStyle(firstRowStyle);
		HSSFCellStyle twoStyle = getTwoStyle(hssfWorkbook);
		HSSFCellStyle oneStyle = getOneStyle(hssfWorkbook);
		//设置列表样式(第一行,第二行)
		CellStyle titleStyle0 = getTitleStyle(hssfWorkbook);
		HSSFCell cell = null;
		if (dataMapList == null || dataMapList.size() == 0) {return hssfWorkbook;}
		for (int i = 0; i < dataMapList.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);

			//设置Excel行高度
			row.setHeight((short) 350);
			if (i == 0) {
				row.setZeroHeight(true);
			} else if (i == 1) {
				row.setHeight((short) 450);
			}

			//获取Excel导入列数据
			LinkedHashMap columnMap = dataMapList.get(i);

			int indexMap = 0;
			for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
				String columnCode = iterator.next().toString().trim();
				String columnValue = columnMap.get(columnCode).toString();

				//栏位编码_hide: 该列为隐藏列
				if (columnCode.indexOf("_hide") != -1) {
					sheet.setColumnHidden(indexMap, true);
				}

				//创建Excel单元格
				cell = row.createCell(indexMap);
				//数据行
				cell.setCellValue(columnValue);
				//(第一行,第二行)
				if (i == 0 || i == 1) {
					RichTextString text = new HSSFRichTextString(columnValue);
					cell.setCellValue(text);
					cell.setCellStyle(titleStyle0);
					sheet.setColumnWidth(indexMap, 5000);
				} else if (i > 1 && i % 2 == 0) {
					//数据行样式
					cell.setCellStyle(twoStyle);
				} else if (i > 1 && i % 2 != 0) {
					cell.setCellStyle(oneStyle);
				}

				indexMap = indexMap + 1;
			}
		}

		//合并单元格
		//Excel 第一行:第一列 到 第八列 合并单元格
		//第一行:从0开始 第一列:从0开始
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 7);
		sheet.addMergedRegion(cra);

		return hssfWorkbook;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void excelExportByDataList(HttpServletResponse response,
	                                         String fileName,
	                                         List<LinkedHashMap<String, String>> dataMapList) throws Exception {
		ServletOutputStream outputStream = null;

		try {
			response.reset();
			response.setHeader("Content-Type","application/octet-stream" );
			response.setHeader("Connection", "close");
			response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls" );

			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "*");
			response.addHeader("Access-Control-Max-Age", "100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Allow-Credentials", "false");
//			response.setContentType("octets/stream;charset=utf-8");
//			response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			outputStream = response.getOutputStream();

			//1. 生成Excel文件对象<HSSFWorkbook>: 业务查询数据结构体-Excel对象
			HSSFWorkbook hssfWorkbook = loadExcelByDataList(dataMapList);
			hssfWorkbook.write(outputStream);
		} catch (Exception e) {

		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	/**
	 * 业务查询数据结构体-生成Excel对象
	 * 1. 第一行: 栏位编码Map<栏位编码, 栏位编码>
	 * 2. 第二行: 栏位名称Map<栏位编码, 栏位名称>
	 * 3. 第三行: 栏位值Map<栏位编码, 栏位值>
	 *
	 * @param dataMapList  业务查询数据结构体
	 * @return
	 */
	public static HSSFWorkbook loadExcelByDataList(List<LinkedHashMap<String, String>> dataMapList) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(SHEET_NAME);
		if (dataMapList == null || dataMapList.size() == 0) {return hssfWorkbook;}

		//设置列表样式(第一行,第二行)
		CellStyle titleStyle0 = getTitleStyle(hssfWorkbook);
		HSSFCell cell = null;
		HSSFCellStyle twoStyle = getTwoStyle(hssfWorkbook);
		HSSFCellStyle oneStyle = getOneStyle(hssfWorkbook);
		for (int i = 0; i < dataMapList.size(); i++) {
			HSSFRow row = sheet.createRow(i);

			//设置Excel行高度
			row.setHeight((short) 350);
			if (i == 0) {
				row.setZeroHeight(true);
			} else if (i == 1) {
				row.setHeight((short) 450);
			}

			//获取Excel导入列数据
			LinkedHashMap columnMap = dataMapList.get(i);

			int indexMap = 0;
			for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
				String columnCode = iterator.next().toString().trim();
				String columnValue = columnMap.get(columnCode).toString();

				//栏位编码_hide: 该列为隐藏列
				if (columnCode.indexOf("_hide") != -1) {
					sheet.setColumnHidden(indexMap, true);
				}

				//创建Excel单元格
				cell = row.createCell(indexMap);
				//数据行
				cell.setCellValue(columnValue);
				//(第一行,第二行)
				if (i == 0 || i == 1) {
					RichTextString text = new HSSFRichTextString(columnValue);
					cell.setCellValue(text);
					cell.setCellStyle(titleStyle0);
					sheet.setColumnWidth(indexMap, 5000);
				} else if (i > 1 && i % 2 == 0) {
					//数据行样式
					cell.setCellStyle(twoStyle);
				} else if (i > 1 && i % 2 != 0) {
					cell.setCellStyle(oneStyle);
				}

				indexMap = indexMap + 1;
			}
		}

		return hssfWorkbook;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//第一列(id)列隐藏
	public static void excelExportHideFirstByDataList(HttpServletResponse response,
	                                         String fileName,
	                                         List<LinkedHashMap<String, String>> dataMapList) throws Exception {
		ServletOutputStream outputStream = null;

		try {
			response.reset();
			response.setHeader("Content-Type","application/octet-stream" );
			response.setHeader("Connection", "close");
			response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xls" );

			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods", "*");
			response.addHeader("Access-Control-Max-Age", "100");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Allow-Credentials", "false");
//			response.setContentType("octets/stream;charset=utf-8");
//			response.addHeader("Content-disposition", "attachment;filename=" + fileName + ".xls");
			outputStream = response.getOutputStream();

			//1. 生成Excel文件对象<HSSFWorkbook>: 业务查询数据结构体-Excel对象
			HSSFWorkbook hssfWorkbook = loadExcelHideFirstByDataList(dataMapList);
			hssfWorkbook.write(outputStream);
		} catch (Exception e) {

		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
	}

	//第一列(id)列隐藏
	public static HSSFWorkbook loadExcelHideFirstByDataList(List<LinkedHashMap<String, String>> dataMapList) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet(SHEET_NAME);
		if (dataMapList == null || dataMapList.size() == 0) {return hssfWorkbook;}

		//设置列表样式(第一行,第二行)
		CellStyle titleStyle0 = getTitleStyle(hssfWorkbook);
		HSSFCell cell = null;
		HSSFCellStyle twoStyle = getTwoStyle(hssfWorkbook);
		HSSFCellStyle oneStyle = getOneStyle(hssfWorkbook);
		for (int i = 0; i < dataMapList.size(); i++) {
			HSSFRow row = sheet.createRow(i);

			//设置Excel行高度
			row.setHeight((short) 350);
			if (i == 0) {
				row.setZeroHeight(true);
			} else if (i == 1) {
				row.setHeight((short) 450);
			}

			//获取Excel导入列数据
			LinkedHashMap columnMap = dataMapList.get(i);

			int indexMap = 0;
			for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
				String columnCode = iterator.next().toString().trim();
				String columnValue = columnMap.get(columnCode).toString();

				//栏位编码_hide: 该列为隐藏列
				if (columnCode.indexOf("_hide") != -1) {
					sheet.setColumnHidden(indexMap, true);
				} else if ("id".equals(columnValue)) {
					sheet.setColumnHidden(indexMap, true);
				}

				//创建Excel单元格
				cell = row.createCell(indexMap);
				//数据行
				cell.setCellValue(columnValue);
				//(第一行,第二行)
				if (i == 0 || i == 1) {
					RichTextString text = new HSSFRichTextString(columnValue);
					cell.setCellValue(text);
					cell.setCellStyle(titleStyle0);
					sheet.setColumnWidth(indexMap, 5000);
				} else if (i > 1 && i % 2 == 0) {
					//数据行样式
					cell.setCellStyle(twoStyle);
				} else if (i > 1 && i % 2 != 0) {
					cell.setCellStyle(oneStyle);
				}

				indexMap = indexMap + 1;
			}
		}

		return hssfWorkbook;
	}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static List<List<String>> readExcel(InputStream inputStream, boolean isExcel2003) throws Exception {
		List<List<String>> dataLst = new ArrayList<List<String>>();
		if (inputStream == null) {return dataLst;}

		try {
			Workbook wb = null;
			if (isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
			} else {
				wb = new XSSFWorkbook(inputStream);
			}
			if (wb == null) {return dataLst;}

			// 得到第一个shell
			Sheet sheet = wb.getSheetAt(0);

			// 得到Excel的行数
			int totalRows = 0;
			totalRows = sheet.getPhysicalNumberOfRows();
			if (totalRows == 0) {return dataLst;}

			// 得到Excel的列数
			int totalCells = 0;
			if (sheet.getRow(0) != null) {
				totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}

			// 循环Excel的行
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			for (int r = 0; r < totalRows; r++) {
				Row row = sheet.getRow(r);
				if (row == null || isBlankRow(row)) {continue;}
				List<String> rowLst = new ArrayList<String>();

				// 循环Excel的列
				for (int c = 0; c < totalCells; c++) {
					Cell cell = row.getCell(c);

					// 获取合并单元格的数量
					int sheetMergeCount = sheet.getNumMergedRegions();
					for (int d = 0; d < sheetMergeCount; d++) {
						CellRangeAddress ca = sheet.getMergedRegion(d);
						//如果是合并单元格则把合并单元格打散，每个单元格重新赋值
						if (r >= ca.getFirstRow() && r <= ca.getLastRow()) {
							if (c >= ca.getFirstColumn() && c <= ca.getLastColumn()) {
								Row fRow = sheet.getRow(ca.getFirstRow());
								Cell fCell = fRow.getCell(ca.getFirstColumn());
								cell = fCell;
							}
						}
					}

					String cellValue = "";
					CellValue cv = evaluator.evaluate(cell);
					if (cell != null && cv != null) {
						//判断数据的类型
						switch (cv.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC: // 数字
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									// 如果是Date类型则，取得该Cell的Date值
									Date date = cell.getDateCellValue();
									// 把Date转换成本地格式的字符串(yyyy-MM-dd HH:mm:ss)
									cellValue = DateUtils.toDateTimeStr(date);
								} else if (cell.getCellStyle().getDataFormat() == 58) {
									// 处理自定义日期格式：(yyyy-MM-dd)(通过判断单元格的格式id解决，id的值是58)
									double value = cell.getNumericCellValue();
									Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
									cellValue = DateUtils.toDateStr(date);
								} else {
									// 取得当前Cell的数值
									String val = String.valueOf(cv.getNumberValue());
									if (val.indexOf('.') > -1) {
										cellValue = String.valueOf(new Double(val)).trim();
									} else {
										Integer num = new Integer((int)cv.getNumberValue());
										cellValue = String.valueOf(num);
									}
								}
								break;
							case HSSFCell.CELL_TYPE_STRING: // 字符串
								cellValue = cv.getStringValue();
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
								cellValue = cv.getBooleanValue() + "";
								break;
							case HSSFCell.CELL_TYPE_FORMULA: // 公式
								cellValue = cv.toString();
								break;

							case HSSFCell.CELL_TYPE_BLANK: // 空值
								cellValue = "";
								break;
							case HSSFCell.CELL_TYPE_ERROR: // 故障
								cellValue = "非法字符";
								break;

							default:
								cellValue = "未知类型";
								break;
						}
					}
					rowLst.add(cellValue);
				}

				dataLst.add(rowLst);
			}
		} catch (Exception e) {
			throw new RestException("", e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return dataLst;
	}

	public static List<LinkedHashMap<String, String>> reflectMapList(List<List<String>> list) {
		List<LinkedHashMap<String, String>> mapList = new ArrayList<LinkedHashMap<String, String>>();
		if (list == null || list.size() == 0) {return mapList;}

		for (int i = 1; i < list.size(); i++) {
			List<String> cellList = list.get(i);
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

			for (int j = 0; j < list.get(0).size(); j++) {
				map.put(list.get(0).get(j), cellList.get(j));
			}
			mapList.add(map);
		}

		return mapList;
	}

	/**
	 * @param fi //文件流
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
	public static List<Object> readExcel(InputStream fi, int startrow, int startcol, int sheetnum) {
		List<Object> varList = new ArrayList<Object>();

		return varList;
	}

	/**
	 * @param target //文件
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
//	public static List<Object> readExcel(File target, int startrow, int startcol, int sheetnum) {
//		List<Object> varList = new ArrayList<Object>();
//
//		try {
//			FileInputStream fi = new FileInputStream(target);
//			varList = readExcel(fi,startrow,startcol,sheetnum);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		return varList;
//	}

	
	
	/**
	 * @param filepath //文件路径
	 * @param filename //文件名
	 * @param startrow //开始行号
	 * @param startcol //开始列号
	 * @param sheetnum //sheet
	 * @return list
	 */
//	public static List<Object> readExcel(String filepath, String filename, int startrow, int startcol, int sheetnum) {
//		List<Object> varList = new ArrayList<Object>();
//		File target = new File(filepath, filename);
//		varList = readExcel(target,startrow,startcol,sheetnum);
//		return varList;
//	}

	
	
	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
//	public static byte[] toByteArray(String filePath) throws IOException {
//		File f = new File(filePath);
//		if (!f.exists()) {
//			throw new FileNotFoundException(filePath);
//		}
//		FileChannel channel = null;
//		FileInputStream fs = null;
//		try {
//			fs = new FileInputStream(f);
//			channel = fs.getChannel();
//			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
//			while ((channel.read(byteBuffer)) > 0) {
//				// do nothing
//				// System.out.println("reading");
//			}
//			return byteBuffer.array();
//		} catch (IOException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				channel.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			try {
//				fs.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	/**
	 * @param response 
	 * @param 		//文件完整路径(包括文件名和扩展名)
	 * @param 		//下载后看到的文件名
	 * @return  文件名
	 */
//	public static void excelDownload(final HttpServletResponse response, String filePath, String fileName) throws Exception{
//
//		byte[] data = toByteArray(filePath);
//	    fileName = URLEncoder.encode(fileName, "UTF-8");
//	    response.reset();
//	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//	    response.addHeader("Content-Length", "" + data.length);
//	    response.setContentType("application/octet-stream;charset=UTF-8");
//	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//	    outputStream.write(data);
//	    outputStream.flush();
//	    outputStream.close();
//	    response.flushBuffer();
//
//	}
	    
	    
//	public static void buildExcelDocument( HttpServletRequest request,HttpServletResponse response,ExcelAjaxTemplate callBack) throws Exception {
//    	ServletOutputStream out = response.getOutputStream();
//    	try{
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			response.setContentType("application/octet-stream");
//			response.setHeader("Content-Disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".xls");
//			response.setContentType("text/html;charset=ISO-8859-1");
//
//			callBack.execute(request,workbook);
//
//			workbook.write(out);
//	    } catch (Exception e) {
//        	e.printStackTrace();
//			StringWriter sw = new StringWriter();
//			e.printStackTrace(new PrintWriter(sw));
//			logger.error(sw.toString());
//        }finally {
//        	out.flush();
//			out.close();
//        }
//
//	}


	public static void buildDefaultExcelDocument( HttpServletRequest request,HttpServletResponse response,ExcelAjaxTemplate callBack) throws Exception {

	}
	    
	    
	    
	    
	    
	    
	    
	    
//	public static HSSFCell getCell(HSSFSheet sheet, int row, int col)
//   {
//     HSSFRow sheetRow = sheet.getRow(row);
//     if (sheetRow == null) {
//       sheetRow = sheet.createRow(row);
//     }
//     HSSFCell cell = sheetRow.getCell(col);
//     if (cell == null) {
//       cell = sheetRow.createCell(col);
//     }
//     return cell;
//   }
  

//	public static void setText(HSSFCell cell, String text)
//   {
//     cell.setCellType(1);
//     cell.setCellValue(text);
//   }

	///////////////////////////////////////////////////////////////////////////////////////////////////
	private static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
		// 产生Excel表头
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setBorderBottom(BorderStyle.DOUBLE); // 设置边框样式

		titleStyle.setBorderLeft(BorderStyle.MEDIUM); // 左边框
		titleStyle.setBorderRight(BorderStyle.MEDIUM); // 右边框
		titleStyle.setBorderTop(BorderStyle.MEDIUM); // 上边框
		titleStyle.setBorderBottom(BorderStyle.MEDIUM); // 下边框

		titleStyle.setBorderTop(BorderStyle.DOUBLE); // 顶边框
		titleStyle.setAlignment(HorizontalAlignment.CENTER);

		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充图案
		titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index); // 填充的背景颜色

		return titleStyle;
	}

	private static HSSFCellStyle getOneStyle(HSSFWorkbook workbook) {
		// 产生Excel表头
		// 产生Excel表头
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft(BorderStyle.THIN); // 左边框
		style.setBorderRight(BorderStyle.THIN); // 右边框
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		return style;
	}

	private static HSSFCellStyle getTwoStyle(HSSFWorkbook workbook) {
		// 产生Excel表头
		// 产生Excel表头
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);

		style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 填充图案
		style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index); // 填充的背景颜色
		return style;
	}

	/**
	 * 判断当前行是否是空行
	 * true : 当前行是空行(当前行全部单元格是空值)
	 * false：当前行非空行(当前行单元格中一个或多个为非空)
	 * @param row
	 * @return
	 */
	private static boolean isBlankRow(Row row) {
		if (row == null) {return true;}
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			String value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						value = cell.getStringCellValue();
						break;
					case Cell.CELL_TYPE_NUMERIC:
						value = String.valueOf((int)cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						value = String.valueOf(cell.getCellFormula());
						break;
					default:
						break;
				}

				if (value.trim().length() > 0) {
					result = false;
					break;
				}
			}
		}

		return result;
	}





































	/**
	 * 根据栏位编码(业务表字段','分隔的字符串)-修改业务模块列表属性(隐藏显示属性)
	 * 应用场景: 某些业务List列表显示或Excel导入导出列表与业务默认列表不一致
	 *
	 * 1. 根据栏位编码-重新组装Titl列表显示隐藏属性-显示字段在前::隐藏字段在后
	 * 2. 栏位编码(业务表字段','分隔的字符串)不为空-按照栏位编码指定的显示顺序显示或Excel导出
	 * 3. 栏位编码为空-按照业务默认显示顺序显示或Excel导出
	 *
	 * @param fieldCode   导出Excel栏位编码
	 * @param columnMap  业务模块列表Map<栏位编码, 栏位名称>
	 * @return
	 */
	public static LinkedHashMap modifyColumnMap(String fieldCode, LinkedHashMap columnMap) {
		if (columnMap == null) {columnMap = new LinkedHashMap();}

		//1. 重新生成TitlMap-显示字段-隐藏字段
		//LinkedHashMap columnMap = columnList.get(0);
		Map<String, LinkedHashMap> showHideMap = findShowhideColumnMap(fieldCode, columnMap);

		//2. 重新拼装业务Title列表-显示字段在前-隐藏字段在后
		return createColumnMap(showHideMap.get("hideColumnMap"), showHideMap.get("showColumnMap"));
	}


	/**
	 * 查询结果集List<Map>-按照栏位列表Map的显示顺序-重构结果集List<Map>
	 *
	 * @param columnMap  栏位列表Map<栏位编码, 栏位名称>
	 * @param dataList   业务查询结果集Map<栏位编码, Object>
	 * @return
	 */
	public static List<LinkedHashMap<String, String>> modifyDataList(LinkedHashMap columnMap, List<Map> dataList) {
		List<LinkedHashMap<String, String>> dataMapList = new ArrayList<LinkedHashMap<String, String>>();
		if(dataList == null) {return dataMapList;}

		//1. 获取(第一行:栏位编码 第二行: 栏位名称)
		LinkedHashMap<String, String> columnCodeMap = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> columnNameMap = new LinkedHashMap<String, String>();
		for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
			String columnMapKey = iterator.next().toString().trim();
			//获取栏位名称
			String columnName = columnMap.get(columnMapKey).toString();
			columnCodeMap.put(columnMapKey, columnMapKey);
			columnNameMap.put(columnMapKey, columnName);
		}
		dataMapList.add(columnCodeMap);
		dataMapList.add(columnNameMap);

		//获取栏位值
		for (Map dataMap : dataList) {
			LinkedHashMap<String, String> columnValueMap = new LinkedHashMap<String, String>();
			for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
				String columnMapKey = iterator.next().toString().trim();
				//获取栏位编码
				String columnCode = columnMapKey;
				String strTemp = columnMapKey;
				if (strTemp.indexOf("_hide") != -1) {
					columnCode = strTemp.replace("_hide", "");
				}

				String dataValue = "";
				Object object = dataMap.get(columnCode);
				//Integer
				//Date
				//BigDecimal
				if (object != null) {dataValue = object.toString();}
				columnValueMap.put(columnMapKey, dataValue);
			}
			dataMapList.add(columnValueMap);
		}

		return dataMapList;
	}







	///////////////////////////////////////////////////
	private static LinkedHashMap createColumnMap(LinkedHashMap hideColumnMap, LinkedHashMap showColumnMap) {
		LinkedHashMap newColumnMap = new LinkedHashMap();

		if (showColumnMap != null) {
			for (Iterator iterator = showColumnMap.keySet().iterator(); iterator.hasNext();) {
				String mapKey = iterator.next().toString().trim();
				String mapValue = showColumnMap.get(mapKey).toString();
				newColumnMap.put(mapKey, mapValue);
			}
		}

		if (hideColumnMap != null) {
			for (Iterator iterator = hideColumnMap.keySet().iterator(); iterator.hasNext();) {
				String mapKey = iterator.next().toString().trim();
				String mapValue = hideColumnMap.get(mapKey).toString();
				newColumnMap.put(mapKey, mapValue);
			}
		}

		return newColumnMap;
	}


	private static Map<String, LinkedHashMap> findShowhideColumnMap(LinkedHashMap columnMap) {
		Map<String, LinkedHashMap> showHideMap = new HashMap<String, LinkedHashMap>();
		if (columnMap == null || columnMap.size() == 0) {return showHideMap;}

		LinkedHashMap hideColumnMap = new LinkedHashMap();
		LinkedHashMap showColumnMap = new LinkedHashMap();
		for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
			String mapKey = iterator.next().toString().trim();
			String mapValue = columnMap.get(mapKey).toString();

			//"_hide"(默认字段为隐藏栏位)-修改为显示
			if (mapKey.indexOf("_hide") != -1) {
				hideColumnMap.put(mapKey, mapValue);
			} else if (mapKey.indexOf("_hide") == -1) {
				showColumnMap.put(mapKey, mapValue);
			}
		}

		showHideMap.put("showColumnMap", showColumnMap);
		showHideMap.put("hideColumnMap", hideColumnMap);
		return showHideMap;
	}

	private static Map<String, LinkedHashMap> findShowhideColumnMap(String fieldCode, LinkedHashMap columnMap) {
		Map<String, LinkedHashMap> showHideMap = new HashMap<String, LinkedHashMap>();
		if (columnMap == null || columnMap.size() == 0) {return showHideMap;}

		if (fieldCode == null || fieldCode.trim().length() == 0) {
			return findShowhideColumnMap(columnMap);
		}

		//2.遍历栏位Map<栏位编码, 栏位名称>-重新定义业务列表(隐藏,显示)属性
		String[] fieldArry = fieldCode.split(",");
		LinkedHashMap hideColumnMap = new LinkedHashMap();
		LinkedHashMap showColumnMap = new LinkedHashMap();
		for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
			String mapKey = iterator.next().toString().trim();
			String mapValue = columnMap.get(mapKey).toString();

			boolean isExist = false;
			for (int i = 0; i < fieldArry.length; i++) {
				String field = fieldArry[i].trim();
				if (mapKey.indexOf(field) != -1) {
					isExist = true;
					break;
				}
			}

			//"_hide"(默认字段为隐藏栏位)-修改为显示
			if (isExist) {
				if (mapKey.indexOf("_hide") != -1) {
					String newKey = mapKey.replace("_hide", "");
					showColumnMap.put(newKey, mapValue);
				} else {
					showColumnMap.put(mapKey, mapValue);
				}
			} else if (!isExist) {
				if (mapKey.indexOf("_hide") == -1) {
					hideColumnMap.put(mapKey + "_hide", mapValue);
				} else {
					hideColumnMap.put(mapKey, mapValue);
				}
			}
		}

		showHideMap.put("showColumnMap", showColumnMap);
		showHideMap.put("hideColumnMap", hideColumnMap);
		return showHideMap;
	}
	    
	    
	    

	    
	    
}
