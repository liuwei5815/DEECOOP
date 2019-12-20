package com.xy.vmes.demo.controller;

import com.xy.vmes.deecoop.system.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by luoyifan on 2018/7/14.
 */
@RestController
@Slf4j
public class RootController {

    private Long a;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/mobile/ok")
    public String ok() {
        return "OK";
    }

//    @GetMapping("/department/{id}")
//    public Object department(@PathVariable("id") Long id) {
//        return departmentService.selectById(id);
//    }

//    @GetMapping("/departments")
//    public Object department() {
//        Pagination pagination = HttpUtils.parsePagination();
//        return departmentService.selectAll(pagination);
//    }
//
//
//    @GetMapping("/selectDepartmentList")
//    public Object selectDepartmentList() {
//        Pagination pagination = HttpUtils.parsePagination();
//        return departmentService.selectDepartmentList(pagination);
//    }
//
//    @GetMapping("/findById")
//    public Object findById() {
//        PageData pd = HttpUtils.parsePageData();
//        return departmentService.findById(pd);
//    }

}
