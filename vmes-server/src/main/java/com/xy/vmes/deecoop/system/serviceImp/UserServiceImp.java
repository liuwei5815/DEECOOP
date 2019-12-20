package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.UserMapper;
import com.xy.vmes.deecoop.system.service.*;
import com.xy.vmes.entity.*;
import com.yvan.*;
import com.yvan.common.util.Common;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

/**
 * 说明：vmes_user:系统用户表 实现类
 * 创建人：刘威 自动创建
 * 创建时间：2018-07-26
 */
@Service
@Transactional(readOnly = false)
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserEmployeeService userEmployeeService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserDefinedMenuService userDefinedMenuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private UserExcelService userExcelService;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public void save(User user) throws Exception{
        user.setId(Conv.createUuid());
        user.setCdate(new Date());
        user.setUdate(new Date());
        userMapper.insert(user);
    }


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public void update(User user) throws Exception{
        user.setUdate(new Date());
        userMapper.updateById(user);
//        userMapper.updateAllColumnById(user);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public void updateAll(User user) throws Exception{
        user.setUdate(new Date());
//        userMapper.updateById(user);
        userMapper.updateAllColumnById(user);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    //@Cacheable(cacheNames = "user", key = "''+#id")
    public User selectById(String id) throws Exception{
        return userMapper.selectById(id);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public void deleteById(String id) throws Exception{
        userMapper.deleteById(id);
    }


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public List<User> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return userMapper.dataListPage(pd,pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public List<User> dataList(PageData pd) throws Exception{
        return userMapper.dataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return userMapper.findColumnList();
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return userMapper.findDataList(pd);
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/




    /**
     * 创建人：刘威
     * 创建时间：2018-07-23
     */
//    @Override
    public void deleteByIds(String[] ids) throws Exception{
        userMapper.deleteByIds(ids);
    }

//    /**
//     * 生成用户编码
//     *
//     * 创建人：陈刚
//     * 创建时间：2018-07-26
//     *
//     * @param companyID  公司ID-组织架构ID
//     * @return
//     */
//    @Override
//    public String createCoder(String companyID) {
//        //(企业编号+前缀字符+日期字符+流水号)-(company+prefix+date+code)
//        //(企业编号+无需+无需+流水号)-W000142
//        CoderuleEntity object = new CoderuleEntity();
//        //tableName 业务名称(表名)
//        object.setTableName("vmes_user");
//        //companyID 公司ID
//        object.setCompanyID(companyID);
//        //length 指定位数(6)
//        object.setLength(Integer.valueOf(6));
//        //firstName 第一个编码名称
//        object.setFirstName("company");
//
//        //separator 分隔符
//        //object.setSeparator("-");
//        //filling 填充字符(0)
//        object.setFilling(Common.CODE_RULE_DEFAULT_FILLING);
//
//        //isNeedCompany 是否需要企业编号
//        object.setIsNeedCompany(Boolean.TRUE);
//
//        return coderuleService.findCoderule(object);
//    }



    /**
     * 判断用户手机号是否存在
     *
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    @Override
    public boolean isExistMobile(PageData pd) throws Exception{
        User user = this.selectById(pd.getString("id"));
        if(user!=null){
            if("2fb9bbee46ca4ce1913f3a673a7dd68f".equals(user.getUserType())||"6839818aecfc41be8f367e62502dfde4".equals(user.getUserType())){
                return false;
            }else{
                List<User> userList = userMapper.isExistMobile(pd);
                if(userList!=null&&userList.size()>0){
                    return true;
                }
            }
        }else {
            List<User> userList = userMapper.isExistMobile(pd);
            if(userList!=null&&userList.size()>0){
                return true;
            }
        }
        return false;
    }


    /**
     * 批量修改用户信息为禁用状态
     *
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
//    @Override
//    public void updateToDisableByIds(String[] ids) throws Exception{
//        userMapper.updateToDisableByIds(ids);
//    }

//    /**
//     * 创建人：刘威
//     * 创建时间：2018-08-03
//     */
//    @Override
//    public void  updateToDisableByEmployIds(String[] ids) throws Exception{
//        userMapper.updateToDisableByEmployIds(ids);
//    }

//    /**
//     * 创建人：刘威
//     * 创建时间：2018-07-26
//     */
//    @Override
//    public List<LinkedHashMap> getColumnList() throws Exception{
//        return userMapper.getColumnList();
//    }

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return userMapper.getDataList(pd);
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception{
        List<Map> mapList = new ArrayList<Map>();
        if (pd == null) {return mapList;}

        if (pg == null) {
            return userMapper.getDataListPage(pd);
        } else if (pg != null) {
            return userMapper.getDataListPage(pd,pg);
        }

        return mapList;
    }


    /**
     * 创建人：刘威
     * 创建时间：2018-08-03
     */
    @Override
    public ResultModel createUserAndRole(PageData pd, Employee employee ) throws Exception {
        ResultModel model = new ResultModel();
        String roleId = pd.getString("roleId");
        if(!StringUtils.isEmpty(roleId)){
            User user = new User();
            if(isExistMobile(pd)){
//                throw  new RestException("10","用户中该手机号已存在，请修改手机号！");
                model.putCode("10");
                model.putMsg("用户中该手机号已存在，请修改手机号！");
                return model;
            }
            String code = coderuleService.createCoder(employee.getCompanyId(),"vmes_user");
            if(StringUtils.isEmpty(code)){
//                throw  new RestException("11","编码规则创建异常，请重新操作！");
                model.putCode("11");
                model.putMsg("编码规则创建异常，请重新操作！");
                return model;
            }
            model = checkUserNum(employee.getCompanyId());
            if(((Integer)model.get("code")).intValue()!=0){
                return model;
            }

            user.setUserCode(code);
            user.setUserName(employee.getName());
            user.setCompanyId(employee.getCompanyId());
            user.setDeptId(pd.getString("deptId"));
            user.setEmail(employee.getEmail());
            user.setEmployId(employee.getId());
            user.setMobile(employee.getMobile());
            //用户类型(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
            user.setUserType(Common.DICTIONARY_MAP.get("userType_employee"));
            user.setCuser(pd.getString("cuser"));
            user.setUuser(pd.getString("uuser"));
            //使用手机号后六位进行加密作为默认密码
            String mobile = employee.getMobile();
            String password = mobile.substring(mobile.length()-6,mobile.length());
            user.setPassword(MD5Utils.MD5(password));
            save(user);

            //修改员工表用户ID
            employee.setUserId(user.getId());
            //是否开通用户 0:不开通 1:开通 is null 不开通
            employee.setIsOpenUser("1");
            employeeService.update(employee);

            //新增用户角色信息
            UserRole userRole = new UserRole();
            userRole.setRoleId(pd.getString("roleId"));
            userRole.setUserId(user.getId());
            userRole.setCuser(pd.getString("cuser"));
            userRole.setUuser(pd.getString("uuser"));
            userRoleService.save(userRole);
        }
        return model;
    }

    public User findUser(PageData object) {
        if (object == null) {return null;}

        List<User> objectList = this.findUserList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    public User findUserById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findUser(findMap);
    }

    public Boolean isExistUserByUserCode(String id, String userCode) {
        if (userCode == null || userCode.trim().length() == 0) {return Boolean.FALSE;}

        PageData findMap = new PageData();
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
        }
        findMap.put("userCode", userCode);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("isSelfExist", "true");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<User> userList = this.findUserList(findMap);
        if (userList != null && userList.size() > 0) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public boolean isExistByMobile(String id, String mobile) {
        if (mobile == null || mobile.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
        }
        findMap.put("mobile", mobile);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("isSelfExist", "true");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<User> userList = this.findUserList(findMap);
        if (userList != null && userList.size() > 0) {
            return true;
        }

        return false;
    }

    public List<User> findUserList(PageData object) {
        if (object == null) {return null;}

        List<User> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    /**
     * 获取企业管理员
     *
     * @param companyID  企业id
     * @return
     */
    public User findCompanyAdmin(String companyID) {
        if (companyID == null || companyID.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("companyId", companyID);
        //数据字典  userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户
        findMap.put("userType", Common.DICTIONARY_MAP.get("userType_company"));
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findUser(findMap);
    }

    /**
     * 批量修改(企业管理员)为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     */
    public void updateDisableByCompanyIds(String[] companyIds) {
        userMapper.updateDisableByCompanyIds(companyIds);
    }

    /**
     * 获取企业当前用户数及用户总数
     * 创建人：刘威
     * @param pd
     * @return
     */
    public ResultModel selectCountUserNum(PageData pd) throws Exception{
        ResultModel model = new ResultModel();
        String deptId = pd.getString("deptId");
        if(StringUtils.isEmpty(deptId)){
            model.putCode(1);
            model.putMsg("部门ID不能为空！");
            return model;
        }
        List<Map>  countUserNum =  checkCompanyUserNum(deptId,null);
        if(countUserNum!=null&&countUserNum.size()>0){
            model.putResult(countUserNum.get(0));
        }else{
            model.putCode(2);
            model.putMsg("公司当前用户数查询异常！");
            return model;
        }

        return  model;
    }

    public List<Map> checkCompanyUserNum(String deptId,String queryStr)  throws Exception {
        PageData pd = new PageData();
        pd.put("deptId",deptId);
        pd.putQueryStr(queryStr);
        List<Map>  countUserNum =  userMapper.selectCountUserNum(pd);
        return  countUserNum;
    }



    @Override
    public ResultModel addUser(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        User user = (User) HttpUtils.pageData2Entity(pd, new User());

        //A. 手机号验证
        String mobile = user.getMobile();
        if(mobile == null || mobile.trim().length() == 0){
            model.putCode(1);
            model.putMsg("该用户手机号不能为空！");
            return model;
        }
        if (mobile.trim().length() != 11) {
            model.putCode(1);
            model.putMsg("手机号长度错误！");
            return model;
        }
        if(isExistMobile(pd)){
            model.putCode(1);
            model.putMsg("该用户手机号已存在！");
            return model;
        }
        user.setMobile(mobile.trim());

        //B. 通过手机号-设置用户默认密码
        //如果用户设置了密码那就用设置的密码加密，如果没有设置密码，那么就用手机号后六位进行加密作为默认密码
        if(StringUtils.isEmpty(user.getPassword())){
            if(mobile!=null&&mobile.trim().length()>6){
                mobile = mobile.trim();
                String password = mobile.substring(mobile.length()-6,mobile.length());
                user.setPassword(MD5Utils.MD5(password));
            }else{
                model.putCode(2);
                model.putMsg("输入手机号长度错误！");
                return model;
            }
        }else{
            user.setPassword(MD5Utils.MD5(user.getPassword()));
        }

        //C. 部门
        //Service (deptId) 该参数名称已经使用 -- 更改为(userDeptId)
        String deptId = pd.getString("userDeptId");
        if(StringUtils.isEmpty(deptId)){
            model.putCode(3);
            model.putMsg("所属部门不能为空！");
            return model;
        }
        Department department = departmentService.selectById(deptId);
        String companyId = department.getId1();
        if(!StringUtils.isEmpty(companyId)){
            user.setCompanyId(companyId);
        }else{
            companyId = department.getId0();
            user.setCompanyId(companyId);
        }
        user.setDeptId(deptId);

        //设置用户编码
        String userCode = user.getUserCode();
        if(userCode == null || userCode.trim().length() == 0){
            String code = coderuleService.createCoder(companyId,"vmes_user");
            if(StringUtils.isEmpty(code)){
                model.putCode(4);
                model.putMsg("编码规则创建异常，请重新操作！");
                return model;
            }
            user.setUserCode(code);
        } else if (userCode != null && userCode.trim().length() > 0) {
            //验证账号系统中是否唯一
            if (this.isExistUserByUserCode(null, userCode.trim())) {
                model.putCode(1);
                model.putMsg("账号(" +userCode.trim()+ ")在系统中已经存在，账号运用于系统登录必须唯一，请重新设置账号！");
                return model;
            }
        }


        model = checkUserNum(companyId);

        if(((Integer)model.get("code")).intValue()!=0){
            return model;
        }

//        List<Map> countUserNum = checkCompanyUserNum(deptId,null);
//        if(countUserNum!=null&&countUserNum.size()>0){
//            Map userNumMap = countUserNum.get(0);
//            if(userNumMap.get("isFull")!=null){
//                String isFull = userNumMap.get("isFull").toString();
//                if(!StringUtils.isEmpty(isFull)&&"1".equals(isFull)){
//                    model.putCode(7);
//                    model.putMsg("当前公司用户数已满员，请联系平台相关人员购买用户数！");
//                    return model;
//                }
//            }
//        }else{
//            model.putCode(8);
//            model.putMsg("公司当前用户数查询异常！");
//            return model;
//        }

        String employeeId = pd.getString("employeeId");
        if (employeeId != null && employeeId.trim().length() > 0) {
            user.setEmployId(employeeId);
        }
        this.save(user);

        String roleId = pd.getString("roleId");
        if (roleId != null && roleId.trim().length() > 0) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            userRole.setCuser(pd.getString("cuser"));
            userRole.setUuser(pd.getString("uuser"));
            userRoleService.save(userRole);
        }

        if (employeeId != null && employeeId.trim().length() > 0) {
            Employee employee = employeeService.findEmployeeById(employeeId);
            //mobile:手机号码
            employee.setMobile(user.getMobile());
            //email:邮箱地址
            employee.setEmail(user.getEmail());
            //user_name:姓名->name:员工姓名
            employee.setName(user.getUserName());
            employee.setUserId(user.getId());
            //是否开通用户(0:不开通 1:开通 is null 不开通)
            employee.setIsOpenUser("1");
            employeeService.update(employee);
        }

        return model;
    }

    private ResultModel checkUserNum(String companyId) {
        ResultModel model = new ResultModel();
        //D. 验证企业用户数
        //获取当前企业最大用户数
        String msg_company_error_1 = "企业编码:{0} 企业名称:{1} 没有设定系统用户数，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
        Department company = departmentService.findDepartmentById(companyId);
        if (company.getCompanyUserCount() == null) {
            String msgStr = MessageFormat.format(msg_company_error_1,
                    company.getCode(),
                    company.getName());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }
        Integer maxUserCount = company.getCompanyUserCount();

        //当前企业有效用户数
        Integer userCount = this.findUserCountByCompanyId(companyId);
        String msg_company_error_2 = "企业编码:{0} 企业名称:{1} 最大系统用户数:{2} 有效用户数:{3} 当前公司用户数已满员，请联系平台相关人员购买用户数！" + Common.SYS_ENDLINE_DEFAULT;
        if ((userCount.intValue() + 1) > maxUserCount.intValue()) {
            String msgStr = MessageFormat.format(msg_company_error_2,
                    company.getCode(),
                    company.getName(),
                    maxUserCount.toString(),
                    userCount.toString());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }
        return  model;
    }

    @Override
    public ResultModel updateUser(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        User user = this.findUserById(pd.getString("id"));

        //A. 手机号验证
        String mobile = pd.getString("mobile");
        String email = pd.getString("email");
        String userName = pd.getString("userName");
        String deptId = pd.getString("deptId");
        if(mobile == null || mobile.trim().length() == 0){
            model.putCode(1);
            model.putMsg("该用户手机号不能为空！");
            return model;
        }
        if (mobile.trim().length() != 11) {
            model.putCode(1);
            model.putMsg("手机号长度错误！");
            return model;
        }
        if(isExistMobile(pd)){
            model.putCode(1);
            model.putMsg("该用户手机号已存在！");
            return model;
        }
        user.setMobile(mobile.trim());
        user.setDeptId(deptId);

//        //B. 通过手机号-设置用户默认密码
//        //如果用户设置了密码那就用设置的密码加密，如果没有设置密码，那么就用手机号后六位进行加密作为默认密码
//        if(StringUtils.isEmpty(pd.getString("password"))){
//            if(mobile!=null&&mobile.trim().length()>6){
//                mobile = mobile.trim();
//                String password = mobile.substring(mobile.length()-6,mobile.length());
//                user.setPassword(MD5Utils.MD5(password));
//            }else{
//                model.putCode(2);
//                model.putMsg("输入手机号长度错误！");
//                return model;
//            }
//        }else{
//            user.setPassword(MD5Utils.MD5(pd.getString("password")));
//        }

        //删除用户角色信息
        userRoleService.deleteUserRoleByUserId(user.getId());
        //新增用户角色信息
        if(!StringUtils.isEmpty(pd.getString("roleId"))){
            UserRole userRole = new UserRole();
            userRole.setRoleId(pd.getString("roleId"));
            userRole.setUserId(user.getId());
            userRole.setCuser(pd.getString("cuser"));
            userRole.setUuser(pd.getString("uuser"));
            userRoleService.save(userRole);
        }
        //如果是企业管理员角色变更，则需修改企业管理员下面角色对应的菜单权限
        //判断是否为企业管理员角色：如果当前用户类型是2fb9bbee46ca4ce1913f3a673a7dd68f，就判断为企业管理员角色
        if("2fb9bbee46ca4ce1913f3a673a7dd68f".equals(user.getUserType())){
            PageData parm = new PageData();
            pd.put("roleId",pd.getString("roleId"));
            pd.put("companyId",user.getCompanyId());
            roleMenuService.deleteMenuFromParentRole(parm);
        }

        //原绑定员工
        String old_employeeId = user.getEmployId();
        if (old_employeeId != null && old_employeeId.trim().length() > 0) {
            Employee employee = employeeService.findEmployeeById(old_employeeId);
            if (employee != null) {
                employee.setUserId(null);
                //是否开通用户(0:不开通 1:开通 is null 不开通)
                employee.setIsOpenUser("0");
                employeeService.updateAll(employee);
            }
        }

        //新绑定员工
        String employeeId = pd.getString("employeeId");
        String employeeDeptId = new String();
        if (employeeId != null && employeeId.trim().length() > 0) {
            Employee employee = employeeService.findEmployeeById(employeeId);
            if (employee != null) {
                employee.setUserId(user.getId());
                //是否开通用户(0:不开通 1:开通 is null 不开通)
                employee.setIsOpenUser("1");
                employee.setMobile(mobile);
                employee.setEmail(email);
//                employee.setName(userName);
                employeeService.update(employee);

                //获取新绑定员工的(部门id) 根据(employeeId员工id) 查询
                List<Map> employeeMapList = null;
                try {
                    PageData findMap = new PageData();
                    findMap.put("employeeId", employeeId);
                    employeeMapList = employeeService.getDataListPage(findMap);

                    if (employeeMapList != null && employeeMapList.size() > 0) {
                        Map<String, Object> employeeMap = employeeMapList.get(0);
                        if (employeeMap != null && employeeMap.get("deptId") != null) {
                            employeeDeptId = (String)employeeMap.get("deptId");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            user.setEmployId(employeeId);
            //mobile:手机号码
            user.setMobile(mobile);
            //email:邮箱地址
            user.setEmail(email);
            //user_name:姓名->name:员工姓名
            user.setUserName(userName);
            //设定用户(所属部门id) <-- 新绑定员工(所属部门id)
            if (employeeDeptId != null && employeeDeptId.trim().length() > 0) {
                user.setDeptId(employeeDeptId);
            }
        }

        user.setRemark("");
        String remark = pd.getString("remark");
        if (remark != null && remark.trim().length() > 0) {
            user.setRemark(remark);
        }

        this.update(user);

        return model;
    }

    public ResultModel updateUserByPassword(PageData pd) throws Exception {
        ResultModel model = new ResultModel();

        String userID = pd.getString("id");
        if (userID == null || userID.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("用户id为空或空字符串！");
            return model;
        }

        //新密码 firstPassword
        String firstPassword = pd.getString("firstPassword");
        if (firstPassword == null || firstPassword.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("新密码为空或空字符串！");
            return model;
        }

        //确认密码 confirmPassword
        String confirmPassword = pd.getString("confirmPassword");
        if (confirmPassword == null || firstPassword.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("确认密码为空或空字符串！");
            return model;
        }

        if (!firstPassword.equals(confirmPassword)) {
            model.putCode(1);
            model.putMsg("新密码与确认密码不一致，请重新输入！");
            return model;
        }

        //2. 修改用户新密码
        User userEdit = new User();
        userEdit.setId(userID);
        userEdit.setPassword(MD5Utils.MD5(firstPassword));
        this.update(userEdit);

        return model;
    }

    @Override
    public ResultModel updatePasswords(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String userIds = pd.getString("ids");
        String[] ids = userIds.split(",");

        if(ids!=null&&ids.length>0){
            for(int i=0;i<ids.length;i++){
                String id =  ids[i];
                User user = this.selectById(id);
                if(StringUtils.isEmpty(user.getMobile())){
                    user.setPassword(MD5Utils.MD5("123"));
                }else{
                    String mobile = user.getMobile();
                    if(mobile!=null&&mobile.trim().length()>6){
                        mobile = mobile.trim();
                        String password = mobile.substring(mobile.length()-6,mobile.length());
                        user.setPassword(MD5Utils.MD5(password));
                    }else{
                        model.putCode(1);
                        model.putMsg("用户"+user.getUserName()+"的手机号长度错误！");
                        return model;
                    }

                }
                this.update(user);
            }
        }


        return model;
    }



    @Override
    public ResultModel updateEmployeeUserUnbind(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String userId = pd.getString("id");
        String employeeId = pd.getString("employeeId");

        if(!StringUtils.isEmpty(userId)){
            User user = this.selectById(userId);
            user.setEmployId(null);
            this.updateAll(user);
        }

        if(!StringUtils.isEmpty(employeeId)){
            Employee employee = employeeService.selectById(employeeId);
            employee.setUserId(null);
            //是否开通用户(0:不开通 1:开通 is null 不开通)
            employee.setIsOpenUser("0");
            employeeService.updateAll(employee);
        }
        return model;
    }

    @Override
    public ResultModel updateEmployeeUserBind(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String userId = pd.getString("id");
        if (userId == null || userId.trim().length() == 0) {
            model.putCode(1);
            model.putMsg("用户id为空或空字符串！");
            return model;
        }

        User user = this.selectById(userId);

        //原绑定员工
        String old_employeeId = user.getEmployId();
        if (old_employeeId != null && old_employeeId.trim().length() > 0) {
            Employee employee = employeeService.findEmployeeById(old_employeeId);
            if (employee != null) {
                employee.setUserId(null);
                //是否开通用户(0:不开通 1:开通 is null 不开通)
                employee.setIsOpenUser("0");
                employeeService.updateAll(employee);
            }
        }

        //新绑定员工
        String employeeId = pd.getString("employeeId");
        if (employeeId != null && employeeId.trim().length() > 0) {
            Employee employee = employeeService.findEmployeeById(employeeId);
            if (employee != null) {
                employee.setUserId(user.getId());
                //是否开通用户(0:不开通 1:开通 is null 不开通)
                employee.setIsOpenUser("1");
                employeeService.update(employee);
            }

            user.setEmployId(employeeId);
            //mobile:手机号码
            user.setMobile(employee.getMobile());
            //email:邮箱地址
            user.setEmail(employee.getEmail());
            //user_name:姓名->name:员工姓名
            user.setUserName(employee.getName());

            //获取员工主岗部门id
            PageData findMap = new PageData();
            findMap.put("employeeId", employeeId);
            //是否兼岗(1:兼岗0:主岗)
            findMap.put("isplurality", "0");
            List<Map> varList = employeeService.getDataListPage(findMap, null);
            if (varList != null && varList.size() > 0) {
                Map<String, Object> mapObject = varList.get(0);
                if (mapObject != null && mapObject.get("deptId") != null) {
                    String deptId = (String)mapObject.get("deptId");
                    user.setDeptId(deptId);
                }
            }
        }

        this.update(user);

        return model;
    }

    @Override
    public ResultModel deleteUsers(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String userIds = pd.getString("ids");
        String[] ids = userIds.split(",");

        for (int i = 0; i < ids.length; i++) {
            String userId = ids[i];
            //1. 删除(vmes_user_role)用户角色表
            userRoleService.deleteUserRoleByUserId(userId);
            //2. 删除(vmes_user_defined_menu)用户主页表
            userDefinedMenuService.deleteUserDefinedMenuByUserId(userId);

            //3. 修改员工表(vmes_employee)-字段user_id 设置为 Null
            //用户id查询(vmes_user)
            Map<String, Object> mapObject = userEmployeeService.findViewUserEmployByUserId(userId);
            if (mapObject != null) {
                Employee employee = userEmployeeService.mapObject2Employee(mapObject, null);
                employee.setUserId(null);
                //是否开通用户(0:不开通 1:开通 is null 不开通)
                employee.setIsOpenUser("0");
                employeeService.updateAll(employee);
            }
        }

        //3. 删除(vmes_user)用户表
        this.deleteByIds(ids);

        return model;
    }

    @Override
    public ResultModel listPageUsers(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Pagination pg = HttpUtils.parsePagination(pd);

        List<Column> columnList = columnService.findColumnList("user");
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

        //默认isdisable:=1
        pd.put("isdisable", Common.SYS_DEFAULT_ISDISABLE_1);
        String isdisableByQuery = pd.getString("isdisableByQuery");
        if (isdisableByQuery != null && isdisableByQuery.trim().length() > 0) {
            pd.put("isdisable", isdisableByQuery);
        }

        //是否需要分页 true:需要分页 false:不需要分页
        Map result = new HashMap();
        String isNeedPage = pd.getString("isNeedPage");
        if ("false".equals(isNeedPage)) {
            pg = null;
        } else {
            result.put("pageData", pg);
        }

        List<Map> varList = this.getDataListPage(pd, pg);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);

        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);
        model.putResult(result);
        return model;
    }

    @Override
    public void exportExcelUsers(PageData pd,Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("user");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

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
        HttpServletResponse response  = HttpUtils.currentResponse();


        //查询数据-Excel文件导出
        //String fileName = "Excel数据字典数据导出";
        String fileName = "ExcelUser";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    public Integer findUserCountByCompanyId(String companyId) {
        if (companyId == null || companyId.trim().length() == 0) {return Integer.valueOf(0);}

        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");

        List<Map> mapList = userMapper.findUserCountByCompanyId(findMap);
        if (mapList != null && mapList.size() > 0 && mapList.get(0).get("userCount") != null) {
            Long userCount = (Long)mapList.get(0).get("userCount");
            return Integer.valueOf(userCount.intValue());
        }

        return Integer.valueOf(0);
    }

    public ResultModel importExcelUser(MultipartFile file) throws Exception {
        ResultModel model = new ResultModel();

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

        //获取当前企业最大用户数
        String msg_company_error_1 = "企业编码:{0} 企业名称:{1} 没有设定系统用户数，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
        Department company = departmentService.findDepartmentById(companyId);
        if (company.getCompanyUserCount() == null) {
            String msgStr = MessageFormat.format(msg_company_error_1,
                    company.getCode(),
                    company.getName());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }
        Integer maxUserCount = company.getCompanyUserCount();
        Integer userCount = this.findUserCountByCompanyId(companyId);
        Integer excelUserCount =  Integer.valueOf(dataMapLst.size());

        String msg_company_error_2 = "企业编码:{0} 企业名称:{1} 最大系统用户数:{2} 有效用户数:{3} 当前导入用户数:{4} Excel导入后用户数已经超过系统用户数！" + Common.SYS_ENDLINE_DEFAULT;
        if (maxUserCount.intValue() < (userCount.intValue() + excelUserCount.intValue())) {
            String msgStr = MessageFormat.format(msg_company_error_2,
                    company.getCode(),
                    company.getName(),
                    maxUserCount.toString(),
                    userCount.toString(),
                    excelUserCount.toString());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //1. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        String msgStr = userExcelService.checkColumnImportExcel(dataMapLst,
                companyId,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(this.exportExcelError(msgStr).toString());
            return model;
        }

        //2. Excel导入字段唯一性判断-在Excel文件中
        msgStr = userExcelService.checkExistImportExcelBySelf(dataMapLst,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(this.exportExcelError(msgStr).toString());
            return model;
        }

        //3. Excel导入字段唯一性判断-在业务表中判断
        msgStr = userExcelService.checkExistImportExcelByDatabase(dataMapLst,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(this.exportExcelError(msgStr).toString());
            return model;
        }

        //4.Excel数据添加到用户表
        userExcelService.addImportExcelByList(dataMapLst, userId);

        return model;
    }

    private StringBuffer exportExcelError(String msgStr) {
        StringBuffer msgBuf = new StringBuffer();
        msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
        msgBuf.append(msgStr.trim());
        msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

        return msgBuf;
    }

}



