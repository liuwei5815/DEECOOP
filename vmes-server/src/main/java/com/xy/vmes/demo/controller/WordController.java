package com.xy.vmes.demo.controller;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.yvan.WordUtil;
import com.yvan.platform.ResponseCode;
import com.yvan.platform.RestException;
import com.yvan.template.PdfWordAjaxTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by 46368 on 2018/7/16.
 */
@RestController
public class WordController {

    private Logger logger = LoggerFactory.getLogger(WordController.class);

    @GetMapping("/word/wordDefault")
    public void exportPdfWord(HttpServletRequest request, HttpServletResponse response) throws Exception{

        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");


        if(StringUtils.isEmpty(request.getParameter("type"))){
            throw new RestException(ResponseCode.ILLEGAL_PARAM, "缺少type参数");
        }

        try {

            WordUtil.createRtfContext(request, response, new PdfWordAjaxTemplate() {

                @Override
                public void execute(HttpServletRequest request,Document document) throws Exception {
//					String cusName = request.getParameter("cusName");

                    Font FontChinese = WordUtil.font;//加入document：


                    /** 创建文档主体内容 */
                    document.add(new Paragraph("First page of the document."));// Paragraph添加文本
                    document.add(new Paragraph("我们是害虫", FontChinese));
                    /** 创建章节对象 */
                    Paragraph title1 = new Paragraph("第一章", FontChinese);
                    Chapter chapter1 = new Chapter(title1, 1);
                    chapter1.setNumberDepth(0);
                    /** 创建章节中的小节 */
                    Paragraph title11 = new Paragraph("表格的添加", FontChinese);
                    Section section1 = chapter1.addSection(title11);
                    /** 创建段落并添加到小节中 */
                    Paragraph someSectionText = new Paragraph("下面展示的为3 X 2 表格.",
                            FontChinese);
                    section1.add(someSectionText);
                    /** 创建表格对象（包含行列矩阵的表格） */
                    Table t = new Table(3, 2);// 2行3列
                    t.setBorderColor(new Color(220, 255, 100));
                    t.setPadding(5);
                    t.setSpacing(5);
                    t.setBorderWidth(1);
                    Cell c1 = new Cell(new Paragraph("第一格", FontChinese));
                    t.addCell(c1);
                    c1 = new Cell("Header2");
                    t.addCell(c1);
                    c1 = new Cell("Header3");
                    t.addCell(c1);
                    // 第二行开始不需要new Cell()
                    t.addCell("1.1");
                    t.addCell("1.2");
                    t.addCell("1.3");
                    section1.add(t);
                    /** 创建章节中的小节 */
                    Paragraph title13 = new Paragraph("列表的添加", FontChinese);
                    Section section3 = chapter1.addSection(title13);
                    /** 创建段落并添加到小节中 */
                    Paragraph someSectionText3 = new Paragraph("下面展示的为列表.", FontChinese);
                    section3.add(someSectionText3);
                    /** 创建列表并添加到pdf文档中 */
                    List l = new List(true, true, 10);// 第一个参数为true，则创建一个要自行编号的列表，
                    // 如果为false则不进行自行编号
                    l.add(new ListItem("First item of list"));
                    l.add(new ListItem("第二个列表", FontChinese));
                    section3.add(l);
                    document.add(chapter1);
                    /** 创建章节对象 */
                    Paragraph title2 = new Paragraph("第二章", FontChinese);
                    Chapter chapter2 = new Chapter(title2, 1);
                    chapter2.setNumberDepth(0);
                    /** 创建章节中的小节 */
                    Paragraph title12 = new Paragraph("png图片添加", FontChinese);
                    Section section2 = chapter2.addSection(title12);
                    /** 添加图片 */
                    section2.add(new Paragraph("图片添加: 饼图", FontChinese));
                    Image png = Image.getInstance("D:/pie.png");//图片的地址
                    section2.add(png);
                    document.add(chapter2);

                }

            });



        }catch(Exception e) {
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.error(sw.toString());

        }

    }


}
