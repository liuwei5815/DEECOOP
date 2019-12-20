package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.MessageService;
import com.xy.vmes.entity.Message;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
* 说明：vmes_message:系统公告Controller
* @author 陈刚 自动生成
* @date 2019-04-18
*/
@RestController
@Slf4j
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2019-04-18
    */
    @GetMapping("/system/message/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/system/message/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Message message = messageService.selectById(id);
        model.putResult(message);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2019-04-18
    */
    @PostMapping("/system/message/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################/system/message/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Message message = (Message)HttpUtils.pageData2Entity(pd, new Message());
        messageService.save(message);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2019-04-18
    */
    @PostMapping("/system/message/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################/system/message/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Message message = (Message)HttpUtils.pageData2Entity(pd, new Message());
        messageService.update(message);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 陈刚 自动创建，禁止修改
    * @date 2019-04-18
    */
    @GetMapping("/system/message/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################/system/message/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        messageService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2019-04-18
    */
    @PostMapping("/system/message/listPageMessages")
    public ResultModel listPageMessages() throws Exception {
        logger.info("################/system/message/listPageMessages 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = messageService.listPageMessages(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/listPageMessages 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/message/addMessage")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addMessage() throws Exception {
        logger.info("################/system/message/addMessage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = messageService.addMessage(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/addMessage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/message/updateMessage")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateMessage() throws Exception {
        logger.info("################/system/message/updateMessage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = messageService.updateMessage(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/updateMessage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/message/updateIsShowMessage")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateIsShowMessage() throws Exception {
        logger.info("################/system/message/updateIsShowMessage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = messageService.updateIsShowMessage(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/updateIsShowMessage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    @PostMapping("/system/message/deleteMessage")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteMessage() throws Exception {
        logger.info("################/system/message/deleteMessage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = messageService.deleteMessage(pd);

        Long endTime = System.currentTimeMillis();
        logger.info("################/system/message/deleteMessage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


}



