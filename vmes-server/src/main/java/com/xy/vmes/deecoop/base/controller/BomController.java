package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Bom;
import com.xy.vmes.deecoop.base.service.BomTreeService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.BomService;
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
* @date 2018-09-29
*/
@RestController
@Slf4j
public class BomController {

    private Logger logger = LoggerFactory.getLogger(BomController.class);

    @Autowired
    private BomService bomService;

    @Autowired
    private BomTreeService bomTreeService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private CoderuleService coderuleService;


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @GetMapping("/base/bom/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################bom/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Bom bom = bomService.selectById(id);
        model.putResult(bom);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################bom/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Bom bom = (Bom)HttpUtils.pageData2Entity(pd, new Bom());
        bomService.save(bom);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################bom/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Bom bom = (Bom)HttpUtils.pageData2Entity(pd, new Bom());
        bomService.update(bom);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @GetMapping("/base/bom/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################bom/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        bomService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################bom/deleteByIds 执行开始 ################# ");
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
            bomService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/deleteByIds 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################bom/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Bom> bomList = bomService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",bomList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################bom/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Bom> bomList = bomService.dataList(pd);
        model.putResult(bomList);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/listPageBoms")
    public ResultModel listPageBoms()  throws Exception {

        logger.info("################bom/listPageBoms 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = bomService.listPageBoms(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/listPageBoms 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/exportExcelBoms")
    public void exportExcelBoms() throws Exception {
        logger.info("################bom/exportExcelBoms 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        bomService.exportExcelBoms(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/exportExcelBoms 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-29
    */
    @PostMapping("/base/bom/importExcelBoms")
    public ResultModel importExcelBoms(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################bom/importExcelBoms 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = bomService.importExcelBoms(file);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/importExcelBoms 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bom/addBom")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addBom()  throws Exception {

        logger.info("################bom/addBom 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model =bomService.addBom(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/addBom 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bom/updateBom")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateBom()  throws Exception {

        logger.info("################bom/updateBom 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model =bomService.updateBom(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/updateBom 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bom/updateIsDefaultBom")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateIsDefaultBom()  throws Exception {

        logger.info("################bom/updateIsDefaultBom 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model =bomService.updateIsDefaultBom(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/updateIsDefaultBom 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/bom/deleteBoms")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteBoms()  throws Exception {
        logger.info("################bom/deleteBoms 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model =bomService.deleteBoms(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################bom/deleteBoms 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


}



