package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.ProductUnitPriceService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
* 说明：vmes_product_unit_price:货品价格Controller
* @author 陈刚 自动生成
* @date 2018-12-04
*/
@RestController
@Slf4j
public class ProductUnitPriceController {
    private Logger logger = LoggerFactory.getLogger(ProductUnitPriceController.class);

    @Autowired
    private ProductUnitPriceService productUnitPriceService;

    @Autowired
    private ColumnService columnService;

    /**
    * @author 陈刚 自动创建，可以修改
    * @date 2018-12-04
    */
    @PostMapping("/base/productUnitPrice/listPageProductUnitPrices")
    public ResultModel listPageProductUnitPrices()  throws Exception {
        logger.info("################productUnitPrice/listPageProductUnitPrices 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = productUnitPriceService.listPageProductUnitPrices(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################productUnitPrice/listPageProductUnitPrices 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

}



