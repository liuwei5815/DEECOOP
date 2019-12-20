package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.CompanyMapper;
import com.xy.vmes.entity.Column;
import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.User;
import com.xy.vmes.entity.UserRole;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

@Service
@Transactional(readOnly = false)
public class CompanyServiceImp implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserDefinedMenuService userDefinedMenuService;

    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private FileService fileService;

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-27
     * @param object
     * @return
     */
    public String checkColumnByAdd(Department object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        //code:企业编码
        if (object.getCode() == null || object.getCode().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业编码");
            msgBuf.append(str_isnull);
        }
        //name 企业名称
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业名称");
            msgBuf.append(str_isnull);
        }
        //companyType企业性质(字典)
        if (object.getCompanyType() == null || object.getCompanyType().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业性质");
            msgBuf.append(str_isnull);
        }
        //companyValidityDate有效期
        if (object.getCompanyValidityDate() == null) {
            String str_isnull = MessageFormat.format(column_isnull, "有效期");
            msgBuf.append(str_isnull);
        }
        //companyUserCount企业用户数
        if (object.getCompanyUserCount() == null) {
            String str_isnull = MessageFormat.format(column_isnull, "企业用户数");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-27
     * @param object
     * @return
     */
    public String checkColumnByEdit(Department object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        if (object.getId() == null || object.getId().trim().length() == 0) {
            msgBuf.append("id为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        //code:企业编码
        if (object.getCode() == null || object.getCode().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业编码");
            msgBuf.append(str_isnull);
        }
        //name 企业名称
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业名称");
            msgBuf.append(str_isnull);
        }
        //companyType企业性质(字典)
        if (object.getCompanyType() == null || object.getCompanyType().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "企业性质");
            msgBuf.append(str_isnull);
        }
//        //companyValidityDate有效期
//        if (object.getCompanyValidityDate() == null) {
//            String str_isnull = MessageFormat.format(column_isnull, "有效期");
//            msgBuf.append(str_isnull);
//        }
        //companyUserCount企业用户数
        if (object.getCompanyUserCount() == null) {
            String str_isnull = MessageFormat.format(column_isnull, "企业用户数");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    /**
     * 企业名称同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param name  (不可为空)
     * @return
     *     true : 组织名称存在名称相同
     *     false: 组织名称不存在名称相同(默认值)
     */
    public boolean isExistByName(String pid, String id, String name) {
        if (pid == null || pid.trim().length() == 0) {return false;}
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        //organizeType 组织类型(1:公司 2:部门)
        findMap.put("organizeType", "1");
        findMap.put("name", name);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = departmentService.findDepartmentList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    /**
     * 企业编码同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param code  (不可为空)
     * @return
     *     true : 组织名称存在名称相同
     *     false: 组织名称不存在名称相同(默认值)
     */
    public boolean isExistByCode(String pid, String id, String code) {
        if (pid == null || pid.trim().length() == 0) {return false;}
        if (code == null || code.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        //organizeType 组织类型(1:公司 2:部门)
        findMap.put("organizeType", "1");
        findMap.put("code", code);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = departmentService.findDepartmentList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    public Department object2objectDB(Department object, Department objectDB) {
        if (objectDB == null) {objectDB = new Department();}
        if (object == null) {return objectDB;}

        //code:企业编码
        objectDB.setCode(object.getCode());
        //name 企业名称
        objectDB.setName(object.getName());
        //companyType企业性质(字典)
        objectDB.setCompanyType(object.getCompanyType());
        //companyValidityDate有效期
        objectDB.setCompanyValidityDate(object.getCompanyValidityDate());
        //companyUserCount企业用户数
        objectDB.setCompanyUserCount(object.getCompanyUserCount());
        //area地区
        objectDB.setArea(object.getArea());

        //isdisable是否禁用(1:已禁用 0:启用)
        if (object.getIsdisable() != null && object.getIsdisable().trim().length() > 0) {
            objectDB.setIsdisable(object.getIsdisable().trim());
        }
        //companyShortname 企业简称
        if (object.getCompanyShortname() != null && object.getCompanyShortname().trim().length() > 0) {
            objectDB.setCompanyShortname(object.getCompanyShortname());
        }
        //serialNumber显示顺序
        if (object.getSerialNumber() != null) {
            objectDB.setSerialNumber(object.getSerialNumber());
        }

        return objectDB;
    }

//    public Integer findMaxSerialNumber(String pid) {
//        if (pid == null || pid.trim().length() == 0) {return Integer.valueOf(0);}
//
//        PageData findMap = new PageData();
//        findMap.put("pid", pid);
//        findMap.put("mapSize", Integer.valueOf(findMap.size()));
//
//        List<Department> objectList = null;
//        try {
//            objectList = departmentService.findDepartmentList(findMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (objectList != null && objectList.size() > 0) {
//            return Integer.valueOf(objectList.size());
//        }
//
//        return Integer.valueOf(0);
//    }

    /**
     * check企业ID是否允许删除
     * 当前企业ID(子节点)-是否使用
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param ids
     * @return
     */
    public String checkDeleteCompanyByIds(String ids) {
        if (ids == null || ids.trim().length() == 0) {return new String();}
        String[] id_arry = ids.split(",");

        String msgTemp = "第 {0} 行: 存在子企业或子部门不可删除！" + Common.SYS_ENDLINE_DEFAULT;
        StringBuffer msgBuf = new StringBuffer();
        for (int i = 0; i < id_arry.length; i++) {
            String id = id_arry[i];

            //当前企业ID(子节点)-是否使用
            List<Department> deptList = departmentService.findDepartmentListByPid(id);
            if (deptList != null && deptList.size() > 0) {
                String msg_Str = MessageFormat.format(msgTemp, (i+1));
                msgBuf.append(msg_Str);
            }
        }

        return  msgBuf.toString();
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-16
     */
    public List<LinkedHashMap> getColumnList() throws Exception {
        return companyMapper.getColumnList();
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-16
     */
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return companyMapper.getDataListPage(pd, pg);
    }

    /**
     * 根据企业ID-判断当前企业有效期是否超时
     *
     * @param companyID  企业ID
     * @return
     *   true : 已超时
     *   false: 未超时
     */
    public String checkCompanyValidityDate(String companyID) {
        StringBuffer msgStr = new StringBuffer();
        if (companyID == null || companyID.trim().length() == 0) {
            msgStr.append("企业id为空或空字符串");
            return msgStr.toString();
        }

        PageData findMap = new PageData();
        findMap.put("id", companyID);
        //组织类型(1:公司 2:部门)
        findMap.put("organizeType", "1");
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        Department company = departmentService.findDepartment(findMap);
        if (company == null) {
            msgStr.append("您所在的企业系统中不存在或已禁用，请与管理员联系！");
            msgStr.append(Common.SYS_ENDLINE_DEFAULT);
            msgStr.append("企业id:" + companyID);
            msgStr.append(Common.SYS_ENDLINE_DEFAULT);
            return msgStr.toString();
        }

        //companyValidityDate 当前企业.有效期 与系统时间比较 (yyyy-mm-dd)
        if (company.getCompanyValidityDate() == null) {
            return msgStr.toString();
        }
        //当前企业有效期(yyyy-mm-dd)
        String validityDateStr = DateUtils.toDateStr(company.getCompanyValidityDate());


        //系统当前时间
        String sysDateStr = DateUtils.toDateStr(new Date());
        Date sysDate = DateUtils.fromDate(sysDateStr);

        //系统当前时间 > 企业有效期
        if (sysDate.getTime() > company.getCompanyValidityDate().getTime()) {
            msgStr.append("企业有效期: " + validityDateStr);
            msgStr.append("您所在的企业(有效期)已经到期，请与管理员联系！");
            return msgStr.toString();
        }

        return msgStr.toString();
    }

    @Override
    public void exportExcelCompanys(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("company");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

        pd.put("layer", "1");
        String ids = pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);


        pg.setSize(100000);
        List<Map> dataList = this.getDataListPage(pd, pg);
        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response  = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        //String fileName = "Excel数据字典数据导出";
        String fileName = "ExcelCompany";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);

    }

    @Override
    public ResultModel addCompanyAdmin(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String roleId = (String)pageData.get("roleId");
        if (roleId == null || roleId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请选择一个角色套餐！");
            return model;
        }
        Department companyObj = (Department)HttpUtils.pageData2Entity(pageData, new Department());
        if (companyObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 组织对象Department 异常！");
            return model;
        }

        String msgStr = this.checkColumnByAdd(companyObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Department>-组织架构的根节点(pid:root)-企业挂在此节点上
        Department rootObj = departmentService.findDepartmentByRoot();
        if (rootObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("根节点(pid:root)系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (企业名称-企业编码)在同一层不可重复
        StringBuffer msgBuf = new StringBuffer();
        if (this.isExistByName(rootObj.getId(), null, companyObj.getName())) {
            String msgTemp = "根名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "企业名称: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    rootObj.getName(),
                    companyObj.getName());
            msgBuf.append(msgExist);
        }
        if (this.isExistByCode(rootObj.getId(), null, companyObj.getCode())) {
            String msgTemp = "根名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "企业编码: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    rootObj.getName(),
                    companyObj.getCode());
            msgBuf.append(msgExist);
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //3. 创建(企业-企业管理员)信息
        String id = Conv.createUuid();
        companyObj.setId(id);
        //organizeType组织类型(1:公司 2:部门)
        companyObj.setOrganizeType("1");
        companyObj.setCuser(pageData.getString("cuser"));

        companyObj = departmentService.id2DepartmentByLayer(id,
                Integer.valueOf(rootObj.getLayer().intValue() + 1),
                companyObj);
        companyObj = departmentService.paterObject2ObjectDB(rootObj, companyObj);

//        //获取(长名称,长编码)- 通过'-'连接的字符串
//        Map<String, String> longNameCodeMpa = departmentService.findLongNameCodeByPater(rootObj);
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongName") != null
//                && longNameCodeMpa.get("LongName").trim().length() > 0
//                ) {
//            companyObj.setLongName(longNameCodeMpa.get("LongName").trim() + "-" + companyObj.getName());
//        }
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongCode") != null
//                && longNameCodeMpa.get("LongCode").trim().length() > 0
//                ) {
//            companyObj.setLongCode(longNameCodeMpa.get("LongCode").trim() + "-" + companyObj.getCode());
//        }
        //该企业-在组织表级别
        //companyObj.setLayer(Integer.valueOf(rootObj.getLayer().intValue() + 1));
        //companyObj.setPid(rootObj.getId());

        //排列顺序serialNumber
        if (companyObj.getSerialNumber() == null) {
            Integer maxCount = departmentService.findMaxSerialNumber(rootObj.getId());
            companyObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }

        departmentService.save(companyObj);

        //创建备件仓库:当创建企业时默认创建备件仓库
        Map<String, Object> valueMap = new HashMap<String, Object>();
        String warehouse_id =Conv.createUuid();
        valueMap.put("id", warehouse_id);
        ////实体库 warehouseEntity 2d75e49bcb9911e884ad00163e105f05
        valueMap.put("pid", Common.DICTIONARY_MAP.get("warehouseEntity"));
        valueMap.put("company_id", companyObj.getId());
        valueMap.put("entity_type", "2c81c25fbe9a45519e2df5bccaed240d");
        valueMap.put("layer", Integer.valueOf(2));

        valueMap.put("qrcode", "");
        //获取仓库编码
        String code = coderuleService.createCoder(companyObj.getId(), "vmes_warehouse","WE");
        valueMap.put("code", code);
        valueMap.put("warehouse_id", warehouse_id);
        valueMap.put("name", "备件库");
        valueMap.put("path_id", "df930aaecb7111e884ad00163e105f05-2d75e49bcb9911e884ad00163e105f05-" + warehouse_id);

        valueMap.put("path_name", "备件库");
        valueMap.put("cuser", companyObj.getCuser());
        valueMap.put("cdate", new Date());

        //创建备件仓库:创建企业时默认创建备件仓库
        try {
            //生成货位二维码
            String qrcode = fileService.createQRCode("warehouseBase", warehouse_id);
            if (qrcode != null && qrcode.trim().length() > 0) {
                valueMap.put("qrcode", qrcode);
            }
            companyMapper.insertWarehouseBySpare(valueMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //4. 创建(企业管理员)账户

        String userName = (String)pageData.get("userName");
        String mobile = (String)pageData.get("mobile");
        String email = (String)pageData.get("email");

        User user = new User();
        user.setCompanyId(companyObj.getId());
        user.setDeptId(companyObj.getId());
//        String userCode = coderuleService.createCoder(companyObj.getId(), "vmes_user");
        String userCode = companyObj.getCode().toLowerCase()+"admin";
        user.setUserCode(userCode);
        user.setUserName(StringUtils.isEmpty(userName)?userCode:userName);
        user.setMobile(mobile);
        user.setEmail(email);

        if(mobile!=null&&mobile.trim().length()>6){
            mobile = mobile.trim();
            String password = mobile.substring(mobile.length()-6,mobile.length());
            user.setPassword(MD5Utils.MD5(password));
        }else{
            user.setPassword(MD5Utils.MD5(Common.DEFAULT_PASSWORD));
        }


        //用户类型(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        user.setUserType(Common.DICTIONARY_MAP.get("userType_company"));
        userService.save(user);

        //5. 创建(用户角色)
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(roleId);
        userRoleService.save(userRole);
        return model;
    }

    @Override
    public ResultModel updateCompany(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String roleId = (String)pageData.get("roleId");
        if (roleId == null || roleId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("请选择一个角色套餐！");
            return model;
        }

        Department companyObj = (Department)HttpUtils.pageData2Entity(pageData, new Department());
        if (companyObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 组织对象Department 异常！");
            return model;
        }

        String msgStr = this.checkColumnByEdit(companyObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Department>-组织架构的根节点(pid:root)-企业挂在此节点上
        Department rootObj = departmentService.findDepartmentByRoot();
        if (rootObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("根节点(pid:root)系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (企业名称-企业编码)在同一层不可重复
        StringBuffer msgBuf = new StringBuffer();
        if (this.isExistByName(rootObj.getId(), companyObj.getId(), companyObj.getName())) {
            String msgTemp = "根名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "企业名称: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    rootObj.getName(),
                    companyObj.getName());
            msgBuf.append(msgExist);
        }
        if (this.isExistByCode(rootObj.getId(), companyObj.getId(), companyObj.getCode())) {
            String msgTemp = "根名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "企业编码: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    rootObj.getName(),
                    companyObj.getCode());
            msgBuf.append(msgExist);
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }
//
        //3. 修改企业信息
        Department companyDB = departmentService.findDepartmentById(companyObj.getId());
        companyDB = this.object2objectDB(companyObj, companyDB);

//        //获取(长名称,长编码)- 通过'-'连接的字符串
//        Map<String, String> longNameCodeMpa = departmentService.findLongNameCodeByPater(rootObj);
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongName") != null
//                && longNameCodeMpa.get("LongName").trim().length() > 0
//                ) {
//            companyDB.setLongName(longNameCodeMpa.get("LongName").trim() + "-" + companyDB.getName());
//        }
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongCode") != null
//                && longNameCodeMpa.get("LongCode").trim().length() > 0
//                ) {
//            companyDB.setLongCode(longNameCodeMpa.get("LongCode").trim() + "-" + companyDB.getCode());
//        }
        departmentService.update(companyDB);

        //获取当前企业管理员

        User user = userService.findCompanyAdmin(companyDB.getId());
        if (user != null) {
            String userName = (String)pageData.get("userName");
            String mobile = (String)pageData.get("mobile");
            String email = (String)pageData.get("email");
            //4. 修改用户表
            user.setUserName(userName);
            user.setMobile(mobile);
            user.setEmail(email);
            userService.update(user);

            //5. 修改用户角色表
            PageData findMap = new PageData();
            findMap.put("userId", user.getId());
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            UserRole userRole = userRoleService.findUserRole(findMap);

            if (userRole != null) {
                userRole.setRoleId(roleId);
                userRoleService.update(userRole);
            } else {
                UserRole addUserRole = new UserRole();
                addUserRole.setUserId(user.getId());
                addUserRole.setRoleId(roleId);
                userRoleService.save(addUserRole);
            }
        }
        return model;
    }

    public ResultModel updateCompanyByCompanyUser(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        String id = (String)pageData.get("id");
        if (id == null || id.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        String name = (String)pageData.get("name");
        if (name == null || name.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业名称为空或空字符串！");
            return model;
        }


        Department companyEdit = new Department();
        companyEdit.setId(id);
        companyEdit.setName(name);

        String companyShortname = (String)pageData.get("companyShortname");
        if (companyShortname != null && companyShortname.trim().length() > 0) {
            companyEdit.setCompanyShortname(companyShortname.trim());
        }

        departmentService.update(companyEdit);

        return model;
    }

    @Override
    public ResultModel deleteCompanyAdmins(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！/n");
            return model;
        }

        String companyIds = (String)pageData.get("companyIds");
        if (companyIds == null || companyIds.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！/n");
            return model;
        }

        String companyId_str = StringUtil.stringTrimSpace(companyIds);
        String[] companyId_arry = companyId_str.split(",");

        //当前企业节点下是否含有子节点
        String msgStr = this.checkDeleteCompanyByIds(companyId_str);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        for (int j = 0; j < companyId_arry.length; j++) {
            String companyId = companyId_arry[j];

            //(部门id,user_type)查询(vmes_user)用户表
            PageData findMap = new PageData();
            findMap.put("companyId", companyId);
            findMap.put("deptId", companyId);
            findMap.put("userType", Common.DICTIONARY_MAP.get("userType_company"));
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            User user = userService.findUser(findMap);

            String userId = "";
            if (user != null && user.getId() != null && user.getId().trim().length() > 0) {
                userId = user.getId().trim();

                //2. 删除(vmes_user)用户表
                userService.deleteById(userId);
                //3. 删除(vmes_user_role)用户角色表
                userRoleService.deleteUserRoleByUserId(userId);
                //4. 删除(vmes_user_defined_menu)用户主页表
                userDefinedMenuService.deleteUserDefinedMenuByUserId(userId);
            }
        }

        //删除企业
        departmentService.deleteByIds(companyId_arry);
        return model;
    }

    public ResultModel findListCompany(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        List<Column> columnList = columnService.findColumnList("setCompany");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pageData.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }

        Map<String, Object> titleMap = ColumnUtil.findTitleMapByColumnList(columnList);
        List<Map> varList = companyMapper.findListCompany(pageData);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);

        Map result = new HashMap();
        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);

        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel listPageCompanyAdmins(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("company");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();
        if(columnList!=null&&columnList.size()>0){
            for (Column column : columnList) {
                if(column!=null){
                    if("0".equals(column.getIshide())){
                        titlesHideList.add(column.getTitleKey());
                    }
                    LinkedHashMap titlesLinkedMap = new LinkedHashMap();
                    titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
                    varModelMap.put(column.getTitleKey(),"");
                    titlesList.add(titlesLinkedMap);
                }
            }
        }

        Map result = new HashMap();
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);

        pd.put("layer", "1");

        pd.put("orderStr", "a.cdate desc");
        String orderStr = pd.getString("orderStr");
        if (orderStr != null && orderStr.trim().length() > 0) {
            pd.put("orderStr", orderStr);
        }

        Pagination pg = HttpUtils.parsePagination(pd);
        List<Map> varMapList = new ArrayList();
        List<Map> varList = this.getDataListPage(pd, pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);

        model.putResult(result);
        return model;
    }
}
