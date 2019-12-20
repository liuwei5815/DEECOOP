package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.deecoop.base.service.CustomerService;
import com.xy.vmes.deecoop.base.service.EquipmentService;
import com.xy.vmes.deecoop.base.service.ProductService;
import com.xy.vmes.deecoop.base.service.ProductUnitService;
import com.xy.vmes.deecoop.system.service.DictionaryDeleteCheckService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.xy.vmes.deecoop.system.service.EmployeeService;
import com.xy.vmes.entity.*;
import com.yvan.PageData;
import com.yvan.common.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class DictionaryDeleteCheckServiceImp implements DictionaryDeleteCheckService {
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductUnitService productUnitService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private EquipmentService equipmentService;

    /**
     * 删除字典时验证是否允许删除
     * 根据(字典id)查询相关业务表
     *
     * @param id 字典表id
     *
     * @return
     */
    public String checkDeleteDictionary(String id, String companyId) throws Exception {
        String msgStr = new String();
        if (id == null || id.trim().length() == 0) {return msgStr;}

        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        if (dictionary == null) {return msgStr;}

        if (Common.DICTIONARY_MAP.get("productUnit").equals(dictionary.getPid())) {
            //字典表id:0ae6e79890db490585e13f34bf00ea4b 计量单位  Common.DICTIONARY_MAP.productUnit

            PageData findMap = new PageData();
            findMap.put("unit", id);

            String sqlStr = new String();
            if (companyId != null && companyId.trim().length() > 0) {
                String sqlStrTemp = "select id from vmes_product where company_id=''{0}''";
                sqlStr = MessageFormat.format(sqlStrTemp, companyId);
            }
            findMap.put("productQueryStr", sqlStr);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            List<ProductUnit> objectList = productUnitService.findProductUnitList(findMap);

            if (objectList != null && objectList.size() > 0) {
                String msgTemp = "该字典名称({0})在系统中正在使用";
                msgStr = MessageFormat.format(msgTemp, dictionary.getName());
                return msgStr;
            }

        } else if (Common.DICTIONARY_MAP.get("supplierType").equals(dictionary.getPid())
            || Common.DICTIONARY_MAP.get("customerType").equals(dictionary.getPid())
        ) {
            //字典表id:d28640b12a454246b172c49a604a89f5(字典id)  供应商类型 Common.DICTIONARY_MAP.supplierType:
            //字典表id:a50dcf66b14a440282eed9e26c1d9482(字典id)  客户类型  Common.DICTIONARY_MAP.customerType:

            PageData findMap = new PageData();
            findMap.put("type", id);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<Customer> objectList = customerService.findCustomerList(findMap);

            if (objectList != null && objectList.size() > 0) {
                String msgTemp = "该字典名称({0})在系统中正在使用";
                msgStr = MessageFormat.format(msgTemp, dictionary.getName());
                return msgStr;
            }

        } else if (Common.DICTIONARY_MAP.get("productType").equals(dictionary.getPid())) {
            //字典表id:a39ac4c1e02e45788eb03a52a5e9a972(字典id)  货品类型 Common.DICTIONARY_MAP.productType:

            PageData findMap = new PageData();
            findMap.put("type", id);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<Product> objectList = productService.findProductList(findMap);

            if (objectList != null && objectList.size() > 0) {
                String msgTemp = "该字典名称({0})在系统中正在使用";
                msgStr = MessageFormat.format(msgTemp, dictionary.getName());
                return msgStr;
            }

        } else if (Common.DICTIONARY_MAP.get("political").equals(dictionary.getPid())) {
            //字典表id:cecdb7fdd450c8a21c7c97d406aa4 政治面貌 Common.DICTIONARY_MAP.political:

            PageData findMap = new PageData();
            findMap.put("political", id);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<Employee> objectList = employeeService.findEmployeeList(findMap);

            if (objectList != null && objectList.size() > 0) {
                String msgTemp = "该字典名称({0})在系统中正在使用";
                msgStr = MessageFormat.format(msgTemp, dictionary.getName());
                return msgStr;
            }

        } else if (Common.DICTIONARY_MAP.get("equipmentType").equals(dictionary.getPid())) {
            //字典表id:7d24edc83dcf4619b618bf0b0eba2851 设备类型 Common.DICTIONARY_MAP.equipmentType:

            PageData findMap = new PageData();
            findMap.put("type", id);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<Equipment> objectList = equipmentService.findEquipmentList(findMap);

            if (objectList != null && objectList.size() > 0) {
                String msgTemp = "该字典名称({0})在系统中正在使用";
                msgStr = MessageFormat.format(msgTemp, dictionary.getName());
                return msgStr;
            }

        }

        return msgStr;
    }
}
