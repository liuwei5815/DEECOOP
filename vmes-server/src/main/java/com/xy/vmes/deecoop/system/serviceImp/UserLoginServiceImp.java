package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.Employee;
import com.xy.vmes.entity.Role;
import com.xy.vmes.entity.User;
import com.xy.vmes.exception.ApplicationException;
import com.yvan.*;
import com.yvan.cache.RedisClient;
import com.yvan.common.util.JsonUtil;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;

@Service
public class UserLoginServiceImp implements UserLoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserEmployeeService userEmployService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    RedisClient redisClient;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    RoleMenuService roleMenuService;

    public Map<String, Object> findRedisMap(String jsonString) {
        Map<String, Object> mapObj = new HashMap<String, Object>();
        if (jsonString == null || jsonString.trim().length() == 0) {
            return mapObj;
        }

        //JsonString 转换Map<String, String> 对象
        Map mapObject = JsonUtil.jsonString2Map(jsonString);
        for (Iterator iterator = mapObject.keySet().iterator(); iterator.hasNext(); ) {
            String mapKey = (String) iterator.next();
            String mapValue = Conv.NS(mapObject.get(mapKey));
            if (mapValue == null || mapValue.trim().length() == 0) {
                continue;
            }

            try {
                if ("sessionID".equals(mapKey)) {
                    mapObj.put(mapKey, mapValue);
                } else if ("user".equals(mapKey)) {
                    User user = (User) JsonUtil.jsonString2Object(mapValue, User.class);
                    mapObj.put(mapKey, user);
                } else if ("employ".equals(mapKey)) {
                    Employee employ = (Employee) JsonUtil.jsonString2Object(mapValue, Employee.class);
                    mapObj.put(mapKey, employ);
                } else if ("dept".equals(mapKey)) {
                    Department dept = (Department) JsonUtil.jsonString2Object(mapValue, Department.class);
                    mapObj.put(mapKey, dept);
                } else if ("userRole".equals(mapKey)) {

                } else if ("userMenu".equals(mapKey)) {

                } else if ("userButton".equals(mapKey)) {

                }
            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }

        }


        return mapObj;
    }

    @Override
    public ResultModel loginIn(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //非空判断
        StringBuffer msgBuf = new StringBuffer();

        //登录类型(loginType):(app,web)
        String loginType = new String();

        if (pageData == null || pageData.size() == 0) {
            msgBuf.append("参数错误：用户登录参数(pageData)为空！");
        } else {
            if (pageData.get("userCode") == null || pageData.get("userCode").toString().trim().length() == 0 ) {
                msgBuf.append("参数错误：账号输入为空或空字符串，账号为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
            if (pageData.get("userPassword") == null || pageData.get("userPassword").toString().trim().length() == 0 ) {
                msgBuf.append("参数错误：密码输入为空或空字符串，密码为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
            if (pageData.get("loginType") == null || pageData.get("loginType").toString().trim().length() == 0 ) {
                msgBuf.append("参数错误：登录类型(loginType)为空或空字符串，密码为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            } else if ("app,web".indexOf(pageData.get("loginType").toString().trim()) == -1) {
                msgBuf.append("参数错误：登录类型(loginType)必须为(app,web)！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            } else {
                loginType = pageData.get("loginType").toString().trim();
            }

            if ("web".equals(loginType) && (pageData.get("securityCode") == null || pageData.get("securityCode").toString().trim().length() == 0)) {
                msgBuf.append("参数错误：验证码入为空或空字符串，验证码为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
            if ("web".equals(loginType) && (pageData.get("securityCodeKey") == null || pageData.get("securityCodeKey").toString().trim().length() == 0)) {
                msgBuf.append("参数错误：验证码(Redis缓存Key)为空或空字符串！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
        }

        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //验证码-是否过期
        if ("web".equals(loginType)) {
            String securityCode = pageData.get("securityCode").toString().trim();
            String old_securityCode = redisClient.get(pageData.get("securityCodeKey").toString().trim());
            if (!securityCode.equalsIgnoreCase(old_securityCode)) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("验证码输入错误或已经过期，请重新输入验证码！");
                return model;
            }
        }

        //1. (用户账号, 密码MD5)-
        String userCode = pageData.get("userCode").toString().trim();
        String userPassword = pageData.get("userPassword").toString().trim();
        userPassword = MD5Utils.MD5(userPassword);
        String queryStr = " (a.user_code = ''{0}'' or a.mobile = ''{0}'') and a.password = ''{1}'' ";
        queryStr = MessageFormat.format(queryStr,
                userCode,
                userPassword);

        PageData findMap = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        findMap.put("userIsdisable", "1");
        findMap.put("queryStr", queryStr);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Map<String, Object>> objectList = userEmployService.findViewUserEmployList(findMap);
        if (objectList == null || objectList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("当前(用户,密码)输入错误，请重新输入！");
            return model;
        }
        Map<String, Object> userEmployMap = objectList.get(0);
        String userID = userEmployMap.get("userID").toString().toLowerCase();
        String userType = userEmployMap.get("userType").toString();

        String companyID = "";
        if (userEmployMap.get("userCompanyID") != null) {
            companyID = (String)userEmployMap.get("userCompanyID");
        }

        //2. 非超级管理员(账号)-比较当前登录企业账号-是否超过(有效期)
        //(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType)) {
            String checkValidityDate = companyService.checkCompanyValidityDate(companyID);
            if (checkValidityDate != null && checkValidityDate.trim().length() > 0) {
                model.putCode(Integer.valueOf(1));
                model.putMsg(checkValidityDate);
                return model;
            }
        }

        //(用户账号, 密码MD5)-系统中存在--RedisKey: uuid_系统用户ID_deecoop
        //3. 生成新的Redis会话(生成新的uuid)
        String new_uuid = Conv.createUuid();
        String redis_uuid = "";
        try{
            redis_uuid = RedisUtils.findRedisUuidByUserID(redisClient, userID, loginType);
            if (redis_uuid != null && redis_uuid.trim().length() > 0) {redis_uuid = redis_uuid.toLowerCase();}
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        //生成新的uuid-与Redis缓存中的Key比较
        //生成新的uuid-与Redis缓存中的历史(uuid)-不同(在其他终端登录)
        //清空历史Redis缓存Key(系统用户ID)字符串匹配
        if (redis_uuid != null && redis_uuid.trim().length() > 0
                && !new_uuid.trim().equals(redis_uuid.trim())) {
            RedisUtils.removeByUserID(redisClient, userID, loginType);
        }


        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, String> RedisMap = new HashMap<String, String>();

        //user:用户信息()
        //User user = new User();
        User user = userEmployService.mapObject2User(userEmployMap, null);
        RedisMap.put("user", YvanUtil.toJson(user));
        dataMap.put("userId", user.getId());
        dataMap.put("userCode", user.getUserCode());
        dataMap.put("companyId", user.getCompanyId());
        dataMap.put("userType", user.getUserType());
        dataMap.put("userName", user.getUserName());
        Department company = departmentService.selectById(user.getCompanyId());
        if(company!=null&&(!StringUtils.isEmpty(company.getCompanyShortname()))){
            dataMap.put("companyShortname", company.getCompanyShortname());
        }else {
            dataMap.put("companyShortname", "智造云管家");
        }



        //employ:员工信息()
        Employee employ = new Employee();
        employ = userEmployService.mapObject2Employee(userEmployMap, employ);
        RedisMap.put("employ", YvanUtil.toJson(employ));

        //deptId部门id-postId岗位ID
        Department dept = departmentService.selectById(user.getDeptId());

        dataMap.put("deptId", dept.getId());
        dataMap.put("deptName", dept.getName());
        dataMap.put("layer", dept.getLayer());
        dataMap.put("postId", "");
//
        //userRole用户角色(角色ID','分隔的字符串)
        String roleIds = userRoleService.findRoleIdsByByUserID(user.getId());
        dataMap.put("roleIds", roleIds);
        RedisMap.put("userRole", roleIds);

        //userMenu菜单权限()
        //当前登录用户id, 当前用户角色id
        try{
            menuService.checkMeunByUserRole(user.getId(), roleIds);
        } catch (ApplicationException appExc) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(appExc.getMessage());
            return model;
        }

        //登录类型(loginType):(app,web)
        //appMenu移动端菜单权限
        if (loginType != null && "app".equals(loginType.trim())) {
            Role role = roleService.findRoleById(roleIds);

            roleIds = StringUtil.stringTrimSpace(roleIds);
            roleIds = "'" + roleIds.replace(",", "','") + "'";

            List<Map<String, Object>> menuMapList = menuService.listMenuKeyByApp(roleIds, Common.SYS_MENU_MAP.get("app"));
            if (menuMapList == null || menuMapList.size() == 0) {
                String msgTemp_2 = "用户姓名({0}) 用户账号({1}) 绑定的角色名称({2}) 该角色没有绑定APP菜单，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
                String msgStr = MessageFormat.format(msgTemp_2,
                        user.getUserName(),
                        user.getUserCode(),
                        role.getName());
                model.putCode(Integer.valueOf(1));
                model.putMsg(msgStr);
                return model;
            }

            List<Map<String, String>> roleMenuList = new ArrayList<Map<String, String>>();
            if (menuMapList != null && menuMapList.size() > 0) {
                for (Map<String, Object> mapObject : menuMapList) {
                    Map<String, String> menuMap = new HashMap<String, String>();

                    String name = new String();
                    if (mapObject.get("name") != null && mapObject.get("name").toString().trim().length() > 0) {
                        name = mapObject.get("name").toString().trim();
                    }
                    menuMap.put("name", name);

                    String url = new String();
                    if (mapObject.get("url") != null && mapObject.get("url").toString().trim().length() > 0) {
                        url = mapObject.get("url").toString().trim();
                    }
                    menuMap.put("url", url);

                    roleMenuList.add(menuMap);
                }
            }

            String appMenuJson = new String();
            if (roleMenuList.size() > 0) {
                appMenuJson = YvanUtil.toJson(roleMenuList);
            }
            model.put("appMenuJson", appMenuJson);
        }

        //userButton按钮权限()

        //缓存业务数据
        //(手机端)Redis缓存Key:   (uuid:用户ID:企业ID:部门ID:部门层级:deecoop:userLoginMap:app)
        //(web端)Redis缓存Key:   (uuid:用户ID:企业ID:部门ID:部门层级:deecoop:userLoginMap:web)
        String Redis_userLogin_Key = new_uuid + ":" +
                userID + ":" +
                companyID + ":" +
                dept.getId() + ":" +
                dept.getLayer() + ":" +
                "deecoop" + ":" +
                Common.REDIS_USERLOGINMAP + ":" +
                loginType;
        //redisClient.set(Redis_userLogin_Key, YvanUtil.toJson(RedisMap));
        //30 * 60 * 1000 (半小时)
        redisClient.setWithExpireTime(Redis_userLogin_Key, YvanUtil.toJson(RedisMap), Common.REDIS_SESSIONID_LONG);

        model.putCode(Integer.valueOf(0));
        model.putResult(YvanUtil.toJson(dataMap));
        model.set("sessionID", Redis_userLogin_Key);

        return model;
    }

    @Override
    public ResultModel createSecurityCode(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String SecurityCode = this.drawImg(new ByteArrayOutputStream());

        //Redis-验证码-缓存1分钟(60 * 1000)
        String RedisCodeKey = Conv.createUuid() + ":" + Common.REDIS_SECURITY_CODE;
        redisClient.setWithExpireTime(RedisCodeKey, SecurityCode, Common.REDIS_SECURITYCODE_LONG);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("securityCode", SecurityCode);
        dataMap.put("securityCodeKey", RedisCodeKey);
        model.putResult(dataMap);
        return model;
    }

    private String drawImg(ByteArrayOutputStream output){
        String code = "";
        for(int i=0; i<4; i++){
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int)x, (int)baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return code;
    }

    private char randomChar(){
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }

    @Override
    public ResultModel changePassWord(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String userID = pageData.getString("userID");
        String passwordOld = pageData.getString("passwordOld");
        String passwordNew = pageData.getString("passwordNew");

        //非空判断
        StringBuffer msgBuf = new StringBuffer();
        if (userID == null || userID.trim().length() == 0 ) {
            msgBuf.append("参数错误：系统用户ID为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (passwordOld == null || passwordOld.trim().length() == 0 ) {
            msgBuf.append("参数错误：原密码输入为空或空字符串，原密码为必填项不可为空！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (passwordNew == null || passwordNew.trim().length() == 0 ) {
            msgBuf.append("参数错误：新密码输入为空或空字符串，新密码为必填项不可为空！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //1. 查询(用户id,原密码)-系统中是否存在
        PageData findMap = new PageData();
        findMap.put("userID", userID);
        findMap.put("userPassword", MD5Utils.MD5(passwordOld));
        //isdisable:是否禁用(0:已禁用 1:启用)
        findMap.put("userIsdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Map<String, Object>> objectList = userEmployService.findViewUserEmployList(findMap);
        if (objectList == null || objectList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("原密码输入错误，请核对后再次输入！");
            return model;
        }

        //2. 修改用户新密码
        Map<String, Object> userEmployMap = objectList.get(0);
        User userDB = userEmployService.mapObject2User(userEmployMap, null);
        userDB.setPassword(MD5Utils.MD5(passwordNew));
        userService.update(userDB);

        model.putMsg("密码修改成功！");

        return model;
    }

    @Override
    public ResultModel findPassWord(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String type = (String)pageData.get("type");
        String mobile = (String)pageData.get("mobile");
        String email = (String)pageData.get("email");
        String userCode = (String)pageData.get("userCode");

        if (type == null || type.trim().length() == 0) {
            //throw new RestException("", "参数错误：密码找回方式为空或空字符串，密码找回方式为必填项不可为空！");
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：密码找回方式为空或空字符串！");
            return model;
        }
        //type (mobile, email)取值范围
        StringBuffer msgBuf = new StringBuffer();
        if ("mobile".equals(type.toLowerCase())) {
            if (mobile == null || mobile.trim().length() == 0) {
                msgBuf.append("参数错误：手机号为空或空字符串，手机号为必填项不可为空！");
            }
        } else if ("email".equals(type.toLowerCase())) {
            if (userCode == null || userCode.trim().length() == 0) {
                msgBuf.append("参数错误：账号手机为空或空字符串，账号手机为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
            if (email == null || email.trim().length() == 0) {
                msgBuf.append("参数错误：邮箱地址为空或空字符串，邮箱地址为必填项不可为空！");
                msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            }
        }
        if (msgBuf.toString().trim().length() > 0) {
            //throw new RestException("", msgBuf.toString());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //获取查询条件
        msgBuf = new StringBuffer();
        PageData findMap = new PageData();
        //isdisable:是否禁用(1:已禁用 0:启用)
        findMap.put("userIsdisable", "0");
        if ("mobile".equals(type.toLowerCase())) {
            findMap.put("userMobile", mobile);
            msgBuf.append("手机号:" + mobile);
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        } else if ("email".equals(type.toLowerCase())) {
            String queryStr = " (a.user_code = ''{0}'' or a.mobile = ''{0}'') ";
            queryStr = MessageFormat.format(queryStr, userCode);
            findMap.put("queryStr", queryStr);
            findMap.put("userEmail", email);

            msgBuf.append("账号手机:" + userCode);
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
            msgBuf.append("邮箱地址:" + email);
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Map<String, Object>> objectList = userEmployService.findViewUserEmployList(findMap);
        if (objectList == null || objectList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString() + "系统中不存在，请与管理员联系！");
            return model;
        }
        Map<String, Object> userEmployMap = objectList.get(0);
        User userDB = userEmployService.mapObject2User(userEmployMap, new User());

        //获取默认新密码:
        String default_password = MD5Utils.MD5(Common.DEFAULT_PASSWORD);
        userDB.setPassword(default_password);

        if ("mobile".equals(type.toLowerCase())) {
            //TODO 重置密码(新密码)-手机短信(mobile)

        } else if ("email".equals(type.toLowerCase())) {
            //TODO 重置密码(新密码)-邮件发给(email)
        }

        userService.update(userDB);

        model.putCode(Integer.valueOf(0));
        model.putMsg("密码重置成功！");
        return model;
    }

    @Override
    public ResultModel loginOut(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String sessionID = (String)pageData.get("sessionID");
        if (sessionID == null || sessionID.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("sessionID为空或空字符串！");
            return model;
        }

        String[] str_arry = sessionID.split(":");
        String uuid = str_arry[0];
        RedisUtils.removeByUuid(redisClient, uuid);
        return model;
    }


}
