package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.BomTree;
import com.xy.vmes.deecoop.base.service.BomTreeService;
import com.yvan.*;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang.StringUtils;

import java.util.*;



/**
* 说明：操作日志Controller
* @author 刘威 自动生成
* @date 2018-10-09
*/
@RestController
@Slf4j
public class BomTreeController {

    private Logger logger = LoggerFactory.getLogger(BomTreeController.class);

    @Autowired
    private BomTreeService bomTreeService;


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @GetMapping("/base/bomTree/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################bomTree/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        BomTree bomTree = bomTreeService.selectById(id);
        model.putResult(bomTree);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################bomTree/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        BomTree bomTree = (BomTree)HttpUtils.pageData2Entity(pd, new BomTree());
        bomTreeService.save(bomTree);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################bomTree/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        BomTree bomTree = (BomTree)HttpUtils.pageData2Entity(pd, new BomTree());
        bomTreeService.update(bomTree);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @GetMapping("/base/bomTree/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################bomTree/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        bomTreeService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################bomTree/deleteByIds 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = new ResultModel();
        String ids = pd.getString("ids");
        if(StringUtils.isEmpty(ids)){
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }
        String id_str = StringUtil.stringTrimSpace(ids);
        String[] id_arry = id_str.split(",");
        if(id_arry.length>0){
            bomTreeService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/deleteByIds 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################bomTree/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<BomTree> bomTreeList = bomTreeService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",bomTreeList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################bomTree/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<BomTree> bomTreeList = bomTreeService.dataList(pd);
        model.putResult(bomTreeList);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * @author 刘威 自动创建，可以修改
     * @date 2018-10-09
     */
    @PostMapping("/base/bomTree/listProdLackNum")
    public ResultModel listProdLackNum()  throws Exception {

        logger.info("################/base/bomTree/listProdLackNum 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = bomTreeService.listProdLackNum(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/bomTree/listProdLackNum 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，可以修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/listPageBomTrees")
    public ResultModel listPageBomTrees()  throws Exception {

        logger.info("################bomTree/listPageBomTrees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = bomTreeService.listPageBomTrees(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/listPageBomTrees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/exportExcelBomTrees")
    public void exportExcelBomTrees() throws Exception {
        logger.info("################bomTree/exportExcelBomTrees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        bomTreeService.exportExcelBomTrees(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/exportExcelBomTrees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2018-10-09
    */
    @PostMapping("/base/bomTree/importExcelBomTrees")
    public ResultModel importExcelBomTrees(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################bomTree/importExcelBomTrees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = bomTreeService.importExcelBomTrees(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/importExcelBomTrees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威
     * @date 2018-07-31
     */
    @PostMapping("/base/bomTree/getBomTree")
    public ResultModel getBomTree()  throws Exception {
        logger.info("################/bomTree/getBomTree 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = bomTreeService.getBomTree(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/bomTree/getBomTree 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威
     * @date 2018-07-31
     */
//    @PostMapping("/base/bomTree/getBomTreeProduct")
    @GetMapping("/test/getBomTreeProduct")
    public ResultModel getBomTreeProduct()  throws Exception {
        logger.info("################/base/bomTree/getBomTreeProduct 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
//        pd.getString("productId"),pd.getString("expectCount")
        ResultModel model = bomTreeService.getBomTreeProduct(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/bomTree/getBomTreeProduct 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bomTree/addBomTree")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addBomTree()  throws Exception {

        logger.info("################bomTree/addBomTree 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = bomTreeService.addBomTree(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/addBomTree 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bomTree/addBomTrees")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addBomTrees()  throws Exception {

        logger.info("################bomTree/addBomTrees 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = bomTreeService.addBomTrees(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/addBomTrees 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bomTree/updateBomTree")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateBomTree()  throws Exception {

        logger.info("################bomTree/updateBomTree 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = bomTreeService.updateBomTree(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/updateBomTree 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }





    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bomTree/deleteBomTree")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteBomTree()  throws Exception {
        logger.info("################bomTree/deleteBomTree 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = bomTreeService.deleteBomTree(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bomTree/deleteBomTree 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



