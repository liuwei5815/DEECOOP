package com.xy.vmes.deecoop.fileIO.controller;

import com.xy.vmes.deecoop.fileIO.service.AttachmentService;
import com.xy.vmes.entity.Attachment;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
* 说明：vmes_attachment:附件管理Controller
* @author 刘威 自动生成
* @date 2019-05-13
*/
@RestController
@Slf4j
public class AttachmentController {

    private Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private AttachmentService attachmentService;

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-05-13
    */
    @GetMapping("/fileIO/attachment/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/fileIO/attachment/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Attachment attachment = attachmentService.selectById(id);
        model.putResult(attachment);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-05-13
    */
    @PostMapping("/fileIO/attachment/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/fileIO/attachment/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Attachment attachment = (Attachment)HttpUtils.pageData2Entity(pd, new Attachment());
        attachmentService.save(attachment);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-05-13
    */
    @PostMapping("/fileIO/attachment/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/fileIO/attachment/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Attachment attachment = (Attachment)HttpUtils.pageData2Entity(pd, new Attachment());
        attachmentService.update(attachment);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2019-05-13
    */
    @GetMapping("/fileIO/attachment/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/fileIO/attachment/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        attachmentService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * @author 刘威
     * @date 2018-08-02
     */
    @PostMapping("/fileIO/attachment/uploadAttachment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel uploadAttachment(@RequestParam("fileName") MultipartFile file)  throws Exception {
        logger.info("################/fileIO/attachment/uploadAttachment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        HttpServletRequest httpRequest = HttpUtils.currentRequest();
        String fileDir = httpRequest.getParameter("fileDir");
        String businessId = httpRequest.getParameter("businessId");
        String companyId = httpRequest.getParameter("companyId");
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileDir)){
            model.putCode(1);
            model.putMsg("请输入文件上传目录！");
            return model;
        }
        String fileUrl = attachmentService.uploadFile(fileDir, file);

        System.out.println("fileUrl:" + fileUrl);

        Attachment attachment = new Attachment();
        attachment.setBusinessId(businessId);
        attachment.setCompanyId(companyId);
        attachment.setName(fileName);
        attachment.setUrl(fileUrl);
        attachmentService.save(attachment);

        model.put("fileUrl",fileUrl);
        model.putMsg("文件上传成功！");

        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/uploadAttachment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2019-05-13
     */
    @PostMapping("/fileIO/attachment/deleteAttachment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteAttachment()  throws Exception {

        logger.info("################/fileIO/attachment/deleteAttachment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Attachment attachment = (Attachment)HttpUtils.pageData2Entity(pd, new Attachment());
        int isDelete = attachmentService.deleteFile(attachment.getUrl());
        if(isDelete==1){
            attachmentService.deleteById(attachment.getId());
        }else {
            model.putCode(1);
            model.putMsg("文件删除失败！");
            return model;
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/deleteAttachment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }




    /**
    * @author 刘威 自动创建，可以修改
    * @date 2019-05-13
    */
    @PostMapping("/fileIO/attachment/listPageAttachments")
    public ResultModel listPageAttachments()  throws Exception {
        logger.info("################/fileIO/attachment/listPageAttachments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = attachmentService.listPageAttachments(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/listPageAttachments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2019-05-13
    */
    @PostMapping("/fileIO/attachment/exportExcelAttachments")
    public void exportExcelAttachments() throws Exception {
        logger.info("################/fileIO/attachment/exportExcelAttachments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        attachmentService.exportExcelAttachments(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/exportExcelAttachments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2019-05-13
    */
    @PostMapping("/fileIO/attachment/importExcelAttachments")
    public ResultModel importExcelAttachments(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################/fileIO/attachment/importExcelAttachments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = attachmentService.importExcelAttachments(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################/fileIO/attachment/importExcelAttachments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



