package com.xy.vmes.deecoop.system.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.PostService;
import com.xy.vmes.entity.*;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * 说明：vmes_post:岗位管理Controller
 * @author 刘威 自动生成
 * @date 2018-08-01
 */
@RestController
@Slf4j
public class PostController {

    private Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @GetMapping("/system/post/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################post/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Post post = postService.selectById(id);
        model.putResult(post);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @PostMapping("/system/post/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################post/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Post post = (Post)HttpUtils.pageData2Entity(pd, new Post());
        postService.save(post);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @PostMapping("/system/post/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################post/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Post post = (Post)HttpUtils.pageData2Entity(pd, new Post());
        postService.update(post);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @GetMapping("/system/post/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################post/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        postService.deleteById(id);
//        String code = postService.createCoder(id);
//        model.putCode(code);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @PostMapping("/system/post/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################post/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Post> postList = postService.dataListPage(pd,pg);
        model.putResult(postList);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-08-01
     */
    @PostMapping("/system/post/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################post/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Post> postList = postService.dataList(pd);
        model.putResult(postList);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }




    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/


    /**
     * @author 刘威
     * @date 2018-08-01
     */
    @PostMapping("/system/post/addPost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addPost()  throws Exception {

        logger.info("################post/addPost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = postService.addPost(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/addPost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威
     * @date 2018-08-01
     */
    @PostMapping("/system/post/updatePost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updatePost()  throws Exception {
        logger.info("################post/updatePost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = postService.updatePost(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/updatePost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改岗位(禁用)状态
     * @author 陈刚
     * @date 2018-07-27
     */
    @PostMapping("/system/post/updateDisablePost")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateDisablePost() throws Exception {
        logger.info("################post/updateDisablePost 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = postService.updateDisablePost(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/updateDisablePost 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威
     * @date 2018-08-01
     */
    @PostMapping("/system/post/deletePosts")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deletePosts()  throws Exception {
        logger.info("################post/deletePosts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = postService.deletePosts(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/deletePosts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
     * @author 刘威
     * @date 2018-08-01
     */
    @PostMapping("/system/post/listPagePosts")
    public ResultModel listPagePosts()  throws Exception {
        logger.info("################post/listPagePosts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = postService.listPagePosts(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/listPagePosts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * Excel导出功能：
     * 1. 勾选指定行导出-(','逗号分隔的id字符串)
     * 2. 按查询条件导出(默认查询方式)
     * 参数说明:
     *   ids          : 业务id字符串-(','分隔的字符串)
     *   queryColumn  : 查询字段(sql where 子句)
     *   showFieldcode: 导出Excel字段Code-显示顺序按照字符串排列顺序-(','分隔的字符串)

     * 注意: 参数(ids,queryColumn)这两个参数是互斥的，(有且有一个参数不为空)
     *
     * @throws Exception
     */
    @PostMapping("/system/post/exportExcelPosts")
    public void exportExcelPosts() throws Exception {
        logger.info("################post/exportExcelPosts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        postService.exportExcelPosts(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################post/exportExcelPosts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");

    }

    /**
     * 部门岗位树
     *
     * @author 陈刚
     * @date 2018-08-28
     */
    @PostMapping("/system/post/treeDeptPosts")
    public ResultModel treeDeptPosts()  throws Exception {
        logger.info("################/post/treeDeptPosts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = postService.treeDeptPosts(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/post/treeDeptPosts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 部门岗位树
     *
     * @author 陈刚
     * @date 2018-08-28
     */
    @PostMapping("/system/post/treeDeptPostsNotMainPost")
    public ResultModel treeDeptPostsNotMainPost() throws Exception {
        logger.info("################/post/treeDeptPosts 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = postService.treeDeptPostsNotMainPost(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/post/treeDeptPosts 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



