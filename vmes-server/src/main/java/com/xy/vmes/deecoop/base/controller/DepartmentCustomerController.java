package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.base.service.DepartmentCustomerService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明：部门客户供应商
 * @author 陈刚 自动生成
 * @date 2018-10-24
 */
@RestController
@Slf4j
public class DepartmentCustomerController {
    private Logger logger = LoggerFactory.getLogger(DepartmentCustomerController.class);

    @Autowired
    private DepartmentCustomerService departmentCustomerService;
    @Autowired
    private ColumnService columnService;

    /**
     * @author 陈刚 自动创建，可以修改
     * @date 2018-09-18
     */
    @PostMapping("/base/departmentCustomer/listPageDepartmentCustomer")
    public ResultModel listPageDepartmentCustomer()  throws Exception {
        logger.info("################base/departmentCustomer/listPageDepartmentCustomer 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = departmentCustomerService.listPageDepartmentCustomer(pd, pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################base/departmentCustomer/listPageDepartmentCustomer 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }
}
