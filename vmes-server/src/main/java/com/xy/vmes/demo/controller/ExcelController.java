package com.xy.vmes.demo.controller;

import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.template.ExcelAjaxTemplate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by 46368 on 2018/7/16.
 */
@RestController
public class ExcelController {

    private Logger logger = LoggerFactory.getLogger(ExcelController.class);



    @GetMapping("/excel/importDefault")
    public void importExcel(@RequestParam(value="excel",required=false) MultipartFile file
    ) throws Exception{


        if (null != file && !file.isEmpty()) {
            InputStream fi = file.getInputStream();
            List<Map> listPd = (List)ExcelUtil.readExcel(fi, 2, 0, 0);                 //执行上传
        }
    }


    /*****
     *
     * Excel 默认导出模板         只需要传入两个参数即可：titles,varList
     * */
    @GetMapping("/excel/exportDefault")
    public void buildDefaultExcelDocument(){
        HttpServletResponse response  = HttpUtils.currentResponse();
        HttpServletRequest request  = HttpUtils.currentRequest();
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","*");
        response.addHeader("Access-Control-Max-Age","100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials","false");



        try {


            ExcelUtil.buildDefaultExcelDocument( request, response,new ExcelAjaxTemplate() {



                @Override
                public void execute(HttpServletRequest request, HSSFWorkbook workbook) throws Exception {


                    LinkedHashMap titles = new LinkedHashMap();
                    titles.put("b", "用户名");
                    titles.put("a", "编号");
                    titles.put("c", "姓名");
                    titles.put("d", "手机号");
                    titles.put("e", "身份证号");
                    titles.put("f", "等级");
                    titles.put("h", "邮箱");
                    titles.put("i", "最近登录");
                    titles.put("j", "到期时间");
                    titles.put("k", "上次登录IP");
                    request.setAttribute("titles", titles);

                    List<Map> varList = new ArrayList<Map>();
                    for(int j=0;j<10;j++){
                        Map vpd = new HashMap();
                        vpd.put("b", "USERNAME");		//1
                        vpd.put("a", "NUMBER");		//2
                        vpd.put("c", "NAME");			//3
                        vpd.put("d", "PHONE");		//4
                        vpd.put("e", "SFID");			//5
                        vpd.put("f", "ROLE_NAME");	//6
                        vpd.put("h", "EMAIL");		//7
                        vpd.put("i", "LAST_LOGIN");	//8
                        vpd.put("j", "END_TIME");		//9
                        vpd.put("k","IP");			//10
                        varList.add(vpd);
                    }
                    request.setAttribute("varList", varList);


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
