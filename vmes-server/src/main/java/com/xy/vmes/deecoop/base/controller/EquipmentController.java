package com.xy.vmes.deecoop.base.controller;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.base.service.EquipmentService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Equipment;
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

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
* 说明：操作日志Controller
* @author 刘威 自动生成
* @date 2018-09-20
*/
@RestController
@Slf4j
public class EquipmentController {

    private Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private FileService fileService;
    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @GetMapping("/base/equipment/selectById/{id}")
    public ResultModel selectById(@PathVariable("id") String id)  throws Exception {

        logger.info("################equipment/selectById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        Equipment equipment = equipmentService.selectById(id);
        model.putResult(equipment);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/selectById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/save")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel save()  throws Exception {

        logger.info("################equipment/save 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();

        Equipment equipment = (Equipment)HttpUtils.pageData2Entity(pd, new Equipment());
        equipment.setId(Conv.createUuid());
        equipmentService.save(equipment);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/save 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/update")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel update()  throws Exception {

        logger.info("################equipment/update 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Equipment equipment = (Equipment)HttpUtils.pageData2Entity(pd, new Equipment());
        equipmentService.update(equipment);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/update 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @GetMapping("/base/equipment/deleteById/{id}")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteById(@PathVariable("id") String id)  throws Exception {

        logger.info("################equipment/deleteById 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        equipmentService.deleteById(id);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/deleteById 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/deleteByIds")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel deleteByIds()  throws Exception {

        logger.info("################equipment/deleteByIds 执行开始 ################# ");
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
            equipmentService.deleteByIds(id_arry);
        }
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/deleteByIds 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/dataListPage")
    public ResultModel dataListPage()  throws Exception {

        logger.info("################equipment/dataListPage 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        List<Equipment> equipmentList = equipmentService.dataListPage(pd,pg);
        Map result = new HashMap();
        result.put("varList",equipmentList);
        result.put("pageData", pg);
        model.putResult(result);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/dataListPage 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
    * @author 刘威 自动创建，禁止修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/dataList")
    public ResultModel dataList()  throws Exception {

        logger.info("################equipment/dataList 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        List<Equipment> equipmentList = equipmentService.dataList(pd);
        model.putResult(equipmentList);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/dataList 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }



    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/listPageEquipments")
    public ResultModel listPageEquipments()  throws Exception {

        logger.info("################equipment/listPageEquipments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = equipmentService.listPageEquipments(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/listPageEquipments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 部门设备树
     *
     * @author 陈刚
     * @date 2019-07-24
     */
    @PostMapping("/base/equipment/treeDepartmentEquipment")
    public ResultModel treeDepartmentEquipment() throws Exception {
        logger.info("################/base/equipment/treeDepartmentEquipment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = equipmentService.treeDepartmentEquipment(pd);
        Long endTime = System.currentTimeMillis();
        logger.info("################/base/equipment/treeDepartmentEquipment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /**
    * Excel导出
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/exportExcelEquipments")
    public void exportExcelEquipments() throws Exception {
        logger.info("################equipment/exportExcelEquipments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        Pagination pg = HttpUtils.parsePagination(pd);
        equipmentService.exportExcelEquipments(pd,pg);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/exportExcelEquipments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
    }

    /**
    * Excel导入
    *
    * @author 刘威 自动创建，可以修改
    * @date 2018-09-20
    */
    @PostMapping("/base/equipment/importExcelEquipments")
    public ResultModel importExcelEquipments(@RequestParam(value="excelFile") MultipartFile file) throws Exception  {
        logger.info("################equipment/importExcelEquipments 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        //HttpServletRequest Request = HttpUtils.currentRequest();

        if (file == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请上传Excel文件！");
            return model;
        }

        // 验证文件是否合法
        // 获取上传的文件名(文件名.后缀)
        String fileName = file.getOriginalFilename();
        if (fileName == null
                || !(fileName.matches("^.+\\.(?i)(xlsx)$")
                || fileName.matches("^.+\\.(?i)(xls)$"))
                ) {
            String failMesg = "不是excel格式文件,请重新选择！";
            model.putCode(Integer.valueOf(1));
            model.putMsg(failMesg);
            return model;
        }

        // 判断文件的类型，是2003还是2007
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }

        List<List<String>> dataLst = ExcelUtil.readExcel(file.getInputStream(), isExcel2003);
        List<LinkedHashMap<String, String>> dataMapLst = ExcelUtil.reflectMapList(dataLst);

        HttpServletRequest httpRequest = HttpUtils.currentRequest();
        String companyId = (String)httpRequest.getParameter("companyId");
        String userId = (String)httpRequest.getParameter("userId");

        if (dataMapLst == null || dataMapLst.size() == 1) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("导入文件数据为空，请至少填写一行导入数据！");
            return model;
        }
        //去掉列表名称行
        dataMapLst.remove(0);

        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        String msgStr = this.checkColumnImportExcel(dataMapLst,
                companyId,
                userId,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(this.exportExcelError(msgStr).toString());
            return model;
        }
        //2. Excel导入字段-名称唯一性判断-在Excel文件中
        //3. Excel导入字段-名称唯一性判断-在业务表中判断
        //4. Excel数据添加到货品表
        this.addImportExcelByList(dataMapLst);

        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/importExcelEquipments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/equipment/addEquipment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel addEquipment()  throws Exception {

        logger.info("################equipment/addEquipment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pd = HttpUtils.parsePageData();
        ResultModel model = new ResultModel();

        Equipment equipment = (Equipment)HttpUtils.pageData2Entity(pd, new Equipment());
        equipment.setId(Conv.createUuid());

        //获取二维码(设备主键id)
        String url = fileService.createQRCode("equipment", equipment.getId());
        equipment.setQrcode(url);

        if(StringUtils.isEmpty(pd.getString("currentCompanyId"))){
            model.putCode(Integer.valueOf(1));
            model.putMsg("当前用户企业id为空！");
            return model;
        }
        equipment.setCompanyId(pd.getString("currentCompanyId"));
        String code = coderuleService.createCoder(pd.getString("currentCompanyId"),"vmes_equipment","E");
        equipment.setCode(code);
        equipmentService.save(equipment);

        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/addEquipment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * @author 刘威 自动创建，禁止修改
     * @date 2018-09-20
     */
    @PostMapping("/base/equipment/updateEquipment")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel updateEquipment()  throws Exception {

        logger.info("################equipment/updateEquipment 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pd = HttpUtils.parsePageData();
        Equipment equipment = (Equipment)HttpUtils.pageData2Entity(pd, new Equipment());
        equipmentService.update(equipment);
        Long endTime = System.currentTimeMillis();
        logger.info("################equipment/updateEquipment 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

//    public ResultModel deleteEquipments()  throws Exception {
//        logger.info("################equipment/deleteEquipments 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        HttpServletResponse response  = HttpUtils.currentResponse();
//        ResultModel model = new ResultModel();
//        PageData pd = HttpUtils.parsePageData();
//        String ids = pd.getString("ids");
//        if(StringUtils.isEmpty(ids)){
//            model.putCode("1");
//            model.putMsg("未勾选删除记录，请重新选择！");
//            return model;
//        }
//        String id_str = StringUtil.stringTrimSpace(ids);
//        String[] id_arry = id_str.split(",");
//        if(id_arry.length>0){
//            equipmentService.deleteByIds(id_arry);
//        }
//        Long endTime = System.currentTimeMillis();
//        logger.info("################equipment/deleteEquipments 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }


    private StringBuffer exportExcelError(String msgStr) {
        StringBuffer msgBuf = new StringBuffer();
        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
        msgBuf.append(msgStr.trim());
        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

        return msgBuf;
    }



    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                         String companyId,
                                         String userId,
                                         Integer index,
                                         Integer maxShowRow) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int maxRow = 0;
        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //获取当前企业下全部部门 deptKeyNameMap<部门id, 部门名称> deptNameKeyMap<部门名称, 部门id>
        departmentService.implementDeptMapByCompanyId(companyId);
        Map<String, String> deptNameKeyMap = departmentService.getDeptNameKeyMap();

        //获取全部 设备类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("equipmentType"), companyId);
        Map<String, String> typeNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //name 设备名称
            String name = mapObject.get("name");
            if (name == null || name.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "设备名称");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //spec 规格型号
            String spec = mapObject.get("spec");
            if (spec == null || spec.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "规格型号");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //typeName 设备类型
            String typeName = mapObject.get("typeName");
            if (typeName == null || typeName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "设备类型");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (typeName != null && typeName.trim().length() > 0) {
                if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "设备类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) != null) {
                    //type 设备类型id
                    mapObject.put("type", typeNameKeyMap.get(typeName));
                }
            }

            //deptName 所属部门
            String deptName = mapObject.get("deptName");
            if (deptName == null || deptName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "所属部门");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (deptName != null && deptName.trim().length() > 0) {
                if (deptNameKeyMap != null && deptNameKeyMap.size() > 0 && deptNameKeyMap.get(deptName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "所属部门",
                            deptName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (deptNameKeyMap != null && deptNameKeyMap.size() > 0 && deptNameKeyMap.get(deptName) != null) {
                    //deptId 所属部门id
                    mapObject.put("deptId", deptNameKeyMap.get(deptName));
                }
            }

            //buyDate 采购日期
            String buyDate = mapObject.get("buyDate");
            if (buyDate != null && buyDate.trim().length() > 0) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat.DEFAULT_DATE_FORMAT);
                    dateFormat.parse(buyDate);
                    //buyDate 采购日期
                    mapObject.put("buyDate", buyDate);
                } catch (ParseException e) {
                    //String msg_column_date_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的日期格式(yyyy-MM-dd)！"
                    String str_error = MessageFormat.format(msg_column_date_error,
                            (i + index_int),
                            "采购日期",
                            buyDate);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {
                        return strBuf.toString();
                    }
                }
            }

            //supplier 厂商名称
            //remark 备注
        }

        return strBuf.toString();
    }


    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
        if (objectList == null || objectList.size() == 0) {return;}

        for (int i = 0; i < objectList.size(); i++) {
            Equipment equipment = new Equipment();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            String userId = mapObject.get("userId");
            equipment.setCuser(userId);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            equipment.setCompanyId(companyId);

            //name 设备名称
            String name = mapObject.get("name");
            equipment.setName(name);

            //spec 规格型号
            String spec = mapObject.get("spec");
            equipment.setSpec(spec);

            //typeName 设备类型 type 设备类型id
            String type = mapObject.get("type");
            equipment.setType(type);

            //deptName 所属部门 deptId 所属部门id
            String deptId = mapObject.get("deptId");
            equipment.setDeptId(deptId);

            //buyDate 采购日期
            String buyDate = mapObject.get("buyDate");
            if (buyDate != null && buyDate.trim().length() > 0) {
                Date date = DateFormat.dateString2Date(buyDate, DateFormat.DEFAULT_DATE_FORMAT);
                if (date != null) {
                    equipment.setBuyDate(date);
                }
            }

            //supplier 厂商名称
            String supplier = mapObject.get("supplier");
            if (supplier != null && supplier.trim().length() > 0) {
                equipment.setSupplier(supplier);
            }

            //remark 备注
            String remark = mapObject.get("remark");
            if (remark != null && remark.trim().length() > 0) {
                equipment.setRemark(remark);
            }

            try {
                //获取设备编码
                String code = coderuleService.createCoder(companyId, "vmes_equipment", "E");
                equipment.setCode(code);

                //生成设备二维码
                equipment.setId(Conv.createUuid());
                String qrcode = fileService.createQRCode("equipment", equipment.getId());
                if (qrcode != null && qrcode.trim().length() > 0) {
                    equipment.setQrcode(qrcode);
                }

                equipmentService.save(equipment);

                //System.out.println("第" + (i+1) + "行：添加成功！");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}



