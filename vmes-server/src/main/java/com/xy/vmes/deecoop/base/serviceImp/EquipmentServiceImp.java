package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.base.dao.EquipmentMapper;
import com.xy.vmes.deecoop.base.service.EquipmentService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.entity.*;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：操作日志 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-09-20
*/
@Service
@Transactional(readOnly = false)
public class EquipmentServiceImp implements EquipmentService {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private ColumnService columnService;
    @Autowired
    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void save(Equipment equipment) throws Exception{
        equipment.setCdate(new Date());
        equipment.setUdate(new Date());
        equipmentMapper.insert(equipment);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void update(Equipment equipment) throws Exception{
        equipment.setUdate(new Date());
        equipmentMapper.updateById(equipment);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void updateAll(Equipment equipment) throws Exception{
        equipment.setUdate(new Date());
        equipmentMapper.updateAllColumnById(equipment);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    //@Cacheable(cacheNames = "equipment", key = "''+#id")
    public Equipment selectById(String id) throws Exception{
        return equipmentMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteById(String id) throws Exception{
        equipmentMapper.deleteById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        equipmentMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Equipment> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return equipmentMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Equipment> dataList(PageData pd) throws Exception{
        return equipmentMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return equipmentMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return equipmentMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        equipmentMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Equipment> selectByColumnMap(Map columnMap) throws Exception{
    List<Equipment> equipmentList =  equipmentMapper.selectByMap(columnMap);
        return equipmentList;
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return equipmentMapper.getDataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return equipmentMapper.getDataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-09-20
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        equipmentMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public Equipment findEquipment(PageData object) {
        if (object == null) {return null;}

        List<Equipment> objectList = this.findEquipmentList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }
    public Equipment findEquipmentById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findEquipment(findMap);
    }
    public List<Equipment> findEquipmentList(PageData object) {
        if (object == null) {return null;}

        List<Equipment> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<Map> getDataListPage(PageData pd) throws Exception {
        return equipmentMapper.getDataListPage(pd);
    }

    @Override
    public ResultModel listPageEquipments(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();

        List<Column> columnList = columnService.findColumnList("Equipment");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }
        Map<String, Object> titleMap = ColumnUtil.findTitleMapByColumnList(columnList);

        List<Map> varList = this.getDataListPage(pd,pg);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);

        Map result = new HashMap();
        result.put("hideTitles", titleMap.get("hideTitles"));
        result.put("titles", titleMap.get("titles"));
        result.put("varList",varMapList);
        result.put("pageData", pg);

        model.putResult(result);
        return model;
    }

    //获取部门表和设备表的树形结构
    @Override
    public ResultModel treeDepartmentEquipment(PageData pd) throws Exception {
        ResultModel model = new ResultModel();

        PageData findMap = new PageData();
        findMap.put("deptDisable", pd.getString("deptDisable"));
        findMap.put("eqptDisable", pd.getString("eqptDisable"));

        String deptId = pd.getString("currentCompanyId");
        if (deptId != null && deptId.trim().length() > 0) {
            String queryIdStr = departmentService.findDeptidById(deptId, null, null);
            findMap.put("deptQuery", queryIdStr);

            String queryIdStr_1 = departmentService.findDeptidById(deptId, null, "b.");
            findMap.put("eqptQuery", queryIdStr_1);
        }

        //1. 查询(部门+岗位)表
        List<Map<String, Object>> deptPostList = equipmentMapper.listDepartmentEquipment(findMap);
        List<TreeEntity> treeList = this.deptEquipmentList2TreeList(deptPostList, null);

        //2. 获得部门岗位树形结构
        TreeEntity treeObj = TreeUtil.switchTree(deptId, treeList);
        String treeJsonStr = YvanUtil.toJson(treeObj);
        System.out.println("treeJsonStr: " + treeJsonStr);

        //3. 树形结构返回前端
        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);

        return model;
    }

    @Override
    public void exportExcelEquipments(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("Equipment");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List
        String ids = (String)pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "equipment.id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);

        pg.setSize(100000);
        List<Map> dataList = this.getDataListPage(pd, pg);
        if (dataList != null && dataList.size() > 0) {
            for (Map<String, Object> mapObject : dataList) {

                //是否启用(0:已禁用 1:启用)
                String isdisable = (String)mapObject.get("isdisable");
                String isdisableName = "否";
                if ("1".equals(isdisable)) {
                    isdisableName = "是";
                }
                mapObject.put("isdisable", isdisableName);
            }
        }

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelEquipment";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

//    @Override
//    public ResultModel importExcelEquipments(MultipartFile file) throws Exception {
//        ResultModel model = new ResultModel();
//        //HttpServletRequest Request = HttpUtils.currentRequest();
//
//        if (file == null) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("请上传Excel文件！");
//            return model;
//        }
//
//        // 验证文件是否合法
//        // 获取上传的文件名(文件名.后缀)
//        String fileName = file.getOriginalFilename();
//        if (fileName == null
//                || !(fileName.matches("^.+\\.(?i)(xlsx)$")
//                || fileName.matches("^.+\\.(?i)(xls)$"))
//                ) {
//            String failMesg = "不是excel格式文件,请重新选择！";
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(failMesg);
//            return model;
//        }
//
//        // 判断文件的类型，是2003还是2007
//        boolean isExcel2003 = true;
//        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
//            isExcel2003 = false;
//        }
//
//        List<List<String>> dataLst = ExcelUtil.readExcel(file.getInputStream(), isExcel2003);
//        List<LinkedHashMap<String, String>> dataMapLst = ExcelUtil.reflectMapList(dataLst);
//
//        HttpServletRequest httpRequest = HttpUtils.currentRequest();
//        String companyId = (String)httpRequest.getParameter("companyId");
//        String userId = (String)httpRequest.getParameter("userId");
//
//        if (dataMapLst == null || dataMapLst.size() == 1) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("导入文件数据为空，请至少填写一行导入数据！");
//            return model;
//        }
//        //去掉列表名称行
//        dataMapLst.remove(0);
//
//        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
//        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
//        String msgStr = equipmentExcelService.checkColumnImportExcel(dataMapLst,
//                companyId,
//                userId,
//                Integer.valueOf(3),
//                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
//        if (msgStr != null && msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(this.exportExcelError(msgStr).toString());
//            return model;
//        }
//        //2. Excel导入字段-名称唯一性判断-在Excel文件中
//        //3. Excel导入字段-名称唯一性判断-在业务表中判断
//        //4. Excel数据添加到货品表
//        equipmentExcelService.addImportExcelByList(dataMapLst);
//
//        return model;
//    }

//    @Override
//    public ResultModel addEquipment(PageData pd) throws Exception {
//        ResultModel model = new ResultModel();
//
//        Equipment equipment = (Equipment)HttpUtils.pageData2Entity(pd, new Equipment());
//        equipment.setId(Conv.createUuid());
//
//        //获取二维码(设备主键id)
//        String url = fileService.createQRCode("equipment", equipment.getId());
//        equipment.setQrcode(url);
//
//        if(StringUtils.isEmpty(pd.getString("currentCompanyId"))){
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("当前用户企业id为空！");
//            return model;
//        }
//        equipment.setCompanyId(pd.getString("currentCompanyId"));
//        String code = coderuleService.createCoder(pd.getString("currentCompanyId"),"vmes_equipment","E");
//        equipment.setCode(code);
//        this.save(equipment);
//
//        return model;
//    }

//    private StringBuffer exportExcelError(String msgStr) {
//        StringBuffer msgBuf = new StringBuffer();
//        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
//        msgBuf.append(msgStr.trim());
//        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);
//
//        return msgBuf;
//    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<TreeEntity> deptEquipmentList2TreeList(List<Map<String, Object>> mapList, List<TreeEntity> treeList) {
        if (treeList == null) {treeList = new ArrayList<TreeEntity>();}
        if (mapList == null || mapList.size() == 0) {return treeList;}

        //遍历mapList-生成treeList
        for (Map<String, Object> mapObj : mapList) {
            DeptEquipmentEntity deptEqpt = (DeptEquipmentEntity)HttpUtils.pageData2Entity(mapObj, new DeptEquipmentEntity());
            TreeEntity tree = this.deptEquipment2Tree(deptEqpt, null);
            treeList.add(tree);
        }
        return treeList;
    }

    private TreeEntity deptEquipment2Tree(DeptEquipmentEntity deptEqpt, TreeEntity tree) {
        if (tree == null) {tree = new TreeEntity();}
        if (deptEqpt == null) {return tree;}

        //id;
        tree.setId(deptEqpt.getId());
        //pid;
        tree.setPid(deptEqpt.getPid());
        //isdisable
        tree.setIsdisable(deptEqpt.getIsdisable());
        //name;
        tree.setName(deptEqpt.getName());
        //deptName;
        tree.setDeptName(deptEqpt.getDeptName());
        //eqptName;
        tree.setEqptName(deptEqpt.getEqptName());

        //layer;
        tree.setLayer(deptEqpt.getLayer());
        //serialNumber;
        tree.setSerialNumber(deptEqpt.getSerialNumber());
        //"dept" 部门 "eqpt" 设备
        tree.setType(deptEqpt.getType());

        return tree;
    }
}



