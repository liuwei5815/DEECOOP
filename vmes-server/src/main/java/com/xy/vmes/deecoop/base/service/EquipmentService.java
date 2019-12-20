package com.xy.vmes.deecoop.base.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Equipment;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：操作日志 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-09-20
*/
public interface EquipmentService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void save(Equipment equipment) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void update(Equipment equipment) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void updateAll(Equipment equipment) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    Equipment selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Equipment> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Equipment> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Equipment> selectByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
//    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    void updateToDisableByIds(String[] ids)throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    Equipment findEquipment(PageData object);
    Equipment findEquipmentById(String id);
    List<Equipment> findEquipmentList(PageData object);


    List<Map> getDataListPage(PageData pd) throws Exception;

    ResultModel listPageEquipments(PageData pd, Pagination pg) throws Exception;

    //获取部门表和设备表的树形结构
    ResultModel treeDepartmentEquipment(PageData pd) throws Exception;

    void exportExcelEquipments(PageData pd, Pagination pg) throws Exception;

//    ResultModel importExcelEquipments(MultipartFile file) throws Exception;

//    ResultModel addEquipment(PageData pd) throws Exception;
}



