package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.system.dao.RoleMapper;
import com.xy.vmes.entity.*;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import static com.yvan.common.util.Common.DICTIONARY_MAP;

/**
* 说明：vmes_role:角色 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-07-30
*/
@Service
@Transactional(readOnly = false)
public class RoleServiceImp implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleButtonService roleButtonService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuTreeService menuTreeService;
    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void save(Role role) throws Exception{
        role.setId(Conv.createUuid());
        role.setCdate(new Date());
        roleMapper.insert(role);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void update(Role role) throws Exception{
        role.setUdate(new Date());
        roleMapper.updateById(role);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    @Override
    public void updateAll(Role role) throws Exception{
        role.setUdate(new Date());
        roleMapper.updateAllColumnById(role);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    //@Cacheable(cacheNames = "role", key = "''+#id")
    public Role selectById(String id) throws Exception{
        return roleMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void deleteById(String id) throws Exception{
        roleMapper.deleteById(id);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<Role> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return roleMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<Role> dataList(PageData pd) throws Exception{
        return roleMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return roleMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return roleMapper.findDataList(pd);
    }

    public void deleteByIds(String[] ids) throws Exception {
        roleMapper.deleteByIds(ids);
    }
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-30
     */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception {
        return roleMapper.getColumnList();
    }
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-30
     */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return roleMapper.getDataListPage(pd, pg);
    }

    /**
     * 批量修改角色信息为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-07-30
     */
    public void updateDisableByIds(String[] ids) throws Exception {
        roleMapper.updateDisableByIds(ids);
    }

    public List<Role> findRoleList(PageData object) {
        List<Role> objectList = new ArrayList<Role>();
        if (object == null) {return objectList;}

        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public Role findRoleById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Role> objectList = this.findRoleList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }

    /**
     * check角色ID是否允许删除
     * 当前角色ID(用户角色,角色菜单,角色按钮)-是否使用
     *
     * 创建人：陈刚
     * 创建时间：2018-07-30
     * @param roleIds
     * @return
     */
    public String checkDeleteRoleByRoleIds(String roleIds) {
        if (roleIds == null || roleIds.trim().length() == 0) {return new String();}

        String msgTemp = "第 {0} 行: 角色在({1})中使用不可禁用！" + Common.SYS_ENDLINE_DEFAULT;
        StringBuffer msgBuf = new StringBuffer();

        String[] roleid_arry = roleIds.split(",");
        PageData findMap = new PageData();
        for (int i = 0; i < roleid_arry.length; i++) {
            String roleID = roleid_arry[i];

            findMap.put("isdisable", "1");
            findMap.put("roleId", roleID);
            findMap.put("mapSize", Integer.valueOf(findMap.size()));

            //1. 当前角色ID(用户角色)
            List<UserRole> list_1 = userRoleService.findUserRoleList(findMap);
            if (list_1 != null && list_1.size() > 0) {
                String msg_1 = MessageFormat.format(msgTemp, (i+1), "用户角色");
                msgBuf.append(msg_1);
            }

            //2. 当前角色ID(角色菜单)
            List<RoleMenu> list_2 = roleMenuService.findRoleMenuList(findMap);
            if (list_2 != null && list_2.size() > 0) {
                String msg_2 = MessageFormat.format(msgTemp, (i+1), "角色菜单");
                msgBuf.append(msg_2);
            }

            //3. 当前角色ID(角色按钮)
            List<RoleButton> list_3 = roleButtonService.findRoleButtonList(findMap);
            if (list_3 != null && list_3.size() > 0) {
                String msg_3 = MessageFormat.format(msgTemp, (i+1), "角色按钮");
                msgBuf.append(msg_3);
            }
        }

        return  msgBuf.toString();
    }

    /**
     * 获取角色ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-08-01
     *
     * @param objectList
     * @return
     */
    public String findRoleIdsByRoleList(List<Role> objectList) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        StringBuffer strBuf = new StringBuffer();
        for (Role object : objectList) {
            if (object.getId() != null && object.getId().trim().length() > 0)  {
                strBuf.append(object.getId().trim());
                strBuf.append(",");
            }
        }

        String strTemp = strBuf.toString();
        if (strTemp.trim().length() > 0 && strTemp.lastIndexOf(",") != -1) {
            strTemp = strTemp.substring(0, strTemp.lastIndexOf(","));
            return strTemp;
        }

        return strBuf.toString();
    }

    /**
     * 角色名称是否相同
     *
     * @param companyId  (不可为空)
     * @param id         (允许为空)-(添加时is null, 修改时 is not null)
     * @param name       (不可为空)
     * @return
     *     true : 角色名称相同
     *     false: 角色名称不相同(默认值)
     */
    public boolean isExistByName(String companyId, String id, String name) {
        if (companyId == null || companyId.trim().length() == 0) {return false;}
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("currentCompanyId", companyId);
        findMap.put("name", name);
        if (id != null && id.trim().length() > 0) {
            findMap.put("isSelfExist", "true");
            findMap.put("id", id);
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Role> objectList = this.findRoleList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    @Override
    public ResultModel listPageRoles(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        //1. 查询遍历List列表
        List<Column> columnList = columnService.findColumnList("role");
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
        Map<String, Object> mapObj = new HashMap<String, Object>();
        mapObj.put("hideTitles", titlesHideList);
        mapObj.put("titles", YvanUtil.toJson(titlesList));

        //设置查询排序
        pd.put("cuser", null);
        pd.put("orderStr", "a.company_id asc, a.cdate desc");
        String orderStr = pd.getString("orderStr");
        if (orderStr != null && orderStr.trim().length() > 0) {
            pd.put("orderStr", orderStr);
        }

        List<Map> varList = this.getDataListPage(pd, pg);
        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
        if(varList != null && varList.size() > 0) {
            for (Map<String, Object> map : varList) {
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(), map.get(entry.getKey()) != null ? map.get(entry.getKey()).toString() : "");
                }
                varMapList.add(varMap);
            }
        }
        mapObj.put("varList", YvanUtil.toJson(varMapList));
        mapObj.put("pageData", YvanUtil.toJson(pg));
        model.putResult(mapObj);
        return model;
    }

    @Override
    public void exportExcelRoles(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("role");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List
        //1. 获取Excel导出数据查询条件

        pd.put("cuser", null);
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
        String fileName = "ExcelRole";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel addRole(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：参数(pageData)为空！");
            return model;
        }

        String name = (String)pageData.get("name");
        if (name == null || name.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(角色名称)输入为空或空字符串，(角色名称)是必填字段不可为空！");
            return model;
        }

        String userId = (String)pageData.get("userId");
        String companyId = "";
        if (pageData.get("companyId") != null) {
            companyId = (String)pageData.get("companyId");
        }

        //角色名称是否相同
        if (this.isExistByName(companyId, null, name)) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色名称：" + name + " 在系统中已经存在！");
            return model;
        }

        //获取角色编码
        String code = coderuleService.createCoder(companyId, "vmes_role", "R");
        if (code == null || code.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("生成角色编码失败，请与管理员联系！");
            return model;
        }

        //3. 添加角色
        Role role = new Role();
        if (companyId != null && companyId.trim().length() > 0) {
            role.setCompanyId(companyId.trim());
        }
        role.setCuser(userId);
        role.setCode(code);
        role.setName(name);
        role.setRemark(pageData.getString("remark"));
        this.save(role);

        //4. 添加角色菜单
        RoleMenu roleMenu_1 = new RoleMenu();
        roleMenu_1.setRoleId(role.getId());
        roleMenu_1.setMenuId(Common.SYS_MENU_MAP.get("root"));
        roleMenu_1.setCuser(userId);
        roleMenuService.save(roleMenu_1);

        RoleMenu roleMenu_2 = new RoleMenu();
        roleMenu_2.setRoleId(role.getId());
        roleMenu_2.setMenuId(Common.SYS_MENU_MAP.get("home"));
        roleMenu_2.setCuser(userId);
        roleMenuService.save(roleMenu_2);

        return model;
    }

    @Override
    public ResultModel updateRole(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String id = (String)pageData.get("id");
        String name = (String)pageData.get("name");
        String companyId = (String)pageData.get("companyId");

        String msgStr = new String();
        if (id == null || id.trim().length() == 0) {
            msgStr = msgStr + "id为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (name == null || name.trim().length() == 0) {
            msgStr = msgStr + "(角色名称)输入为空或空字符串，(角色名称)是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //角色名称是否相同
        if (this.isExistByName(companyId, id, name)) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色名称：" + name + " 在系统中已经存在！");
            return model;
        }

        //3. 修改角色
        Role objectDB = this.findRoleById(id);
        objectDB.setName(name);
        objectDB.setRemark(pageData.getString("remark"));
        this.update(objectDB);
        return model;
    }

    @Override
    public ResultModel updateDisableRole(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String id = (String)pageData.get("id");
        String isdisable = (String)pageData.get("isdisable");

        String msgStr = new String();
        if (id == null || id.trim().length() == 0) {
            msgStr = msgStr + "id为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (isdisable == null || isdisable.trim().length() == 0) {
            msgStr = msgStr + "isdisable为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //修改角色(禁用)状态
        Role objectDB = (Role)HttpUtils.pageData2Entity(pageData, new Role());
        this.update(objectDB);

        return model;
    }

    @Override
    public ResultModel deleteRoles(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String ids = (String)pageData.get("ids");
        if (ids == null || ids.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：请至少选择一行数据！");
            return model;
        }

        ids = StringUtil.stringTrimSpace(ids);
        String[] id_arry = ids.split(",");

        //验证删除角色是否关联有用户
        StringBuffer msgBuf = new StringBuffer();
        String msgTemp = "第 {0} 行: 角色名称({1}) 关联有用户，禁止删除！" + Common.SYS_ENDLINE_DEFAULT;
        for (int i = 0; i < id_arry.length; i++) {
            String roleId = id_arry[i];
            Role role = this.findRoleById(roleId);

            PageData findMap = new PageData();
            findMap.put("roleId", roleId);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            findMap.put("mapSize", findMap.size());

            List<UserRole> userRoleList = userRoleService.findUserRoleList(findMap);

            if (userRoleList != null && userRoleList.size() > 0) {
                String msgStr = MessageFormat.format(msgTemp,
                        (i+1),
                        role.getName());
                msgBuf.append(msgStr);
            }
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        for (int i = 0; i < id_arry.length; i++) {
            String roleID = id_arry[i];
            try {
                //1. 当前角色ID-禁用(用户角色)
                userRoleService.deleteUserRoleByRoleId(roleID);
                //2. 当前角色ID-禁用(角色菜单)
                roleMenuService.deleteRoleMenuByRoleId(roleID);
                //3. 当前角色ID-禁用(角色按钮)
                roleButtonService.deleteRoleButtonByRoleId(roleID);

            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }
        }

        this.deleteByIds(ids.split(","));

        return model;
    }

    @Override
    public ResultModel saveRoleUsers(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        //1. 非空判断
        String roleId = (String)pageData.get("roleId");
        if (roleId == null || roleId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("roleId为空或空字符串！");
            return model;
        }
        String userIds = (String)pageData.get("userIds");

        //2. 删除角色用户(当前角色)
        userRoleService.deleteUserRoleByRoleId(roleId);

        //3. 添加角色用户(当前角色)
        userRoleService.addUserRoleByUserIds(roleId, userIds, (String)pageData.get("cuser"));

        //4. 如果是企业管理员角色变更，则需修改企业管理员下面角色对应的菜单权限
        Role role = this.selectById(roleId);
        //判断是否为企业管理员角色：如果当前角色的公司ID是b6ff76cb95f711e884ad00163e105f05，就判断为企业管理员角色
        if("b6ff76cb95f711e884ad00163e105f05".equals(role.getCompanyId())){
            PageData pd = new PageData();
            pd.put("roleId",roleId);
            roleMenuService.deleteMenuFromParentRole(pd);
        }

        return model;
    }

    @Override
    public ResultModel saveRoleMeuns(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String msgStr = new String();
        String roleID = (String)pageData.get("roleID");
        if (roleID == null || roleID.trim().length() == 0) {
            msgStr = msgStr + "roleID为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        String meunIds = (String)pageData.get("meunIds");
        if (meunIds == null || meunIds.trim().length() == 0) {
            msgStr = msgStr + "meunIds为空或空字符串！" + Common.SYS_ENDLINE_DEFAULT;
        }
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

//        //2.当前角色ID(用户角色,角色菜单,角色按钮)-是否使用
//        msgStr = roleService.checkDeleteRoleByRoleIds(roleID);
//        if (msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(msgStr);
//            return model;
//        }

        //3. 删除角色菜单(当前角色)
        roleMenuService.deleteRoleMenuByRoleId(roleID);

        //4. 添加角色菜单(当前角色)
        roleMenuService.addRoleMenuByMeunIds(roleID, meunIds);

        //5. 如果是企业管理员角色变更，则需修改企业管理员下面角色对应的菜单权限
        Role role = this.selectById(roleID);
        //判断是否为企业管理员角色：如果当前角色的公司ID是b6ff76cb95f711e884ad00163e105f05，就判断为企业管理员角色
        if("b6ff76cb95f711e884ad00163e105f05".equals(role.getCompanyId())){
            PageData pd = new PageData();
            pd.put("roleId",roleID);
            roleMenuService.deleteMenuFromParentRole(pd);
        }
        return model;
    }

    @Override
    public ResultModel treeRoleMeunsAll(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //角色List页面-勾选的角色id
        String roleIds = pageData.getString("roleIds");
        if (roleIds == null || roleIds.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色ID为空或空字符串！");
            return model;
        }

        roleIds = StringUtil.stringTrimSpace(roleIds);
        roleIds = "'" + roleIds.replace(",", "','") + "'";
        String queryRoleIds = "b.role_id in (" +  roleIds + ")";

        PageData findMap = new PageData();
        findMap.put("queryRoleIds", queryRoleIds);

        //B. 获取用户角色相关菜单树-查询条件-
        //获取当前登录用户-用户类型
        String userType = pageData.getString("userType");
        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType)) {
            //获取当前登录用户-用户角色id
            String userRoleId = pageData.getString("userRoleId");
            if (userRoleId != null && userRoleId.trim().length() > 0) {
                String menuIds = roleMenuService.findMenuidByRoleIds(userRoleId);
                menuIds = StringUtil.stringTrimSpace(menuIds);
                menuIds = "'" + menuIds.replace(",", "','") + "'";

                String queryStr = "a.id in (" +  menuIds + ")";
                findMap.put("queryStr", queryStr);
            }
            findMap.put("rootStr", "a.pid in ('root')");
        }

        List<Map<String, Object>> roleMenuMapList = roleMenuService.listMenuMapByRole(findMap);
        List<TreeEntity> treeList = roleMenuService.roleMenuList2TreeList(roleMenuMapList, null);
        List<TreeEntity> menuTreeList = TreeUtil.listSwitchTree(null, treeList);
        String treeJsonStr = YvanUtil.toJson(menuTreeList);
//        System.out.println("treeJson: " + treeJsonStr);
        model.putResult(treeJsonStr);
        return model;
    }

    @Override
    public ResultModel treeRoleMeunsSelected(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String roleIds = (String)pageData.get("roleIds");
        if (roleIds == null || roleIds.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色ID为空或空字符串！");
            return model;
        }

        roleIds = StringUtil.stringTrimSpace(roleIds);
        roleIds = "'" + roleIds.replace(",", "','") + "'";
        String queryRoleIds = "b.role_id in (" +  roleIds + ")";

        PageData findMap = new PageData();
        findMap.put("queryStr", queryRoleIds);
        findMap.put("menuIsdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        //1. 根据角色ID-获取当前角色ID绑定的菜单
        List<Map<String, Object>> mapList = roleMenuService.findRoleMenuMapList(findMap);
        List<Menu> menuList = roleMenuService.mapList2MenuList(mapList, new ArrayList<Menu>());
        Integer maxLayer = menuService.findMaxLayerByMenuList(menuList);

        //3. 生成菜单树
        menuTreeService.initialization();
        menuTreeService.findMenuTreeByList(menuList, maxLayer);
        List<TreeEntity> treeList = menuTreeService.creatMenuTree(maxLayer, null, null);

        String treeJsonStr = YvanUtil.toJson(treeList);
//        System.out.println("treeJsonStr: " + treeJsonStr);
        model.putResult(treeJsonStr);
        return model;
    }

    @Override
    public ResultModel listRoleMeunsButtonsAll(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String roleIds = (String)pageData.get("roleIds");
        if (roleIds == null || roleIds.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色ID为空或空字符串！");
            return model;
        }

        String menuId = (String)pageData.get("menuId");
        if (menuId == null || menuId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("菜单ID为空或空字符串！");
            return model;
        }

        roleIds = StringUtil.stringTrimSpace(roleIds);
        roleIds = "'" + roleIds.replace(",", "','") + "'";
        String queryRoleIds = "b.role_id in (" +  roleIds + ")";

        PageData findMap = new PageData();
        findMap.put("queryRoleIds", queryRoleIds);
        findMap.put("menuId", menuId);

        List<Map<String, Object>> mapList = roleButtonService.listMenuButtonMapByRole(findMap);
        List<MenuButtonEntity> entityList = roleButtonService.roleButtonList2ButtonList(mapList, null);

        String treeJsonStr = YvanUtil.toJson(entityList);
//        System.out.println("treeJsonStr: " + treeJsonStr);

        model.putResult(treeJsonStr);
        return model;
    }

    @Override
    public ResultModel saveRoleMeunsButtons(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        String roleID = (String)pageData.get("roleID");
        if (roleID == null || roleID.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色id为空或空字符串！");
            return model;
        }

        String menuId = (String)pageData.get("menuId");
        if (menuId == null || menuId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("菜单id为空或空字符串！");
            return model;
        }

        //获取(菜单id,角色id)所有按钮id字符串-查询(vmes_role_button,vmes_menu_button)-菜单按钮表
        PageData findMap = new PageData();
        findMap.put("menuId", menuId);
        findMap.put("roleId", roleID);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<RoleButton> roleButtonList = roleButtonService.findRoleButtonList(findMap);
        String roleButtonIds = roleButtonService.findButtonIdsByRoleButtonList(roleButtonList);

        //3. 删除角色按钮(当前角色)
        if (roleButtonIds != null && roleButtonIds.trim().length() > 0) {
            findMap = new PageData();
            findMap.put("roleId", roleID);

            roleButtonIds = StringUtil.stringTrimSpace(roleButtonIds);
            String[] idArry = roleButtonIds.split(",");
            for (String buttonId : idArry) {
                try {
                    roleButtonService.deleteRoleButtonByButtonId(buttonId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //4. 添加角色按钮(当前角色)
        String buttonIds = (String)pageData.get("buttonIds");
        if (buttonIds != null && buttonIds.trim().length() > 0) {
            roleButtonService.addRoleButtonByMeunIds(roleID, buttonIds);
        }

        return model;
    }

    @Override
    public ResultModel listAllUsersByDeptId(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        Map<String, Object> mapObj = new HashMap<String, Object>();

        //1. 查询遍历List列表
        List<Column> columnList = columnService.findColumnList("userRole");
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
        mapObj.put("hideTitles", titlesHideList);
        mapObj.put("titles", titlesList);

        //2. 查询数据List
        PageData findMap = new PageData();

        //Service (deptId)参数已经使用 -- 更改为(userDeptId)
        String deptId = (String)pageData.get("userDeptId");
        if (deptId != null && deptId.trim().length() > 0) {
            String queryIdStr = departmentService.findDeptidById(deptId, null, "dept.");
            findMap.put("queryStr", queryIdStr);
        }

        String roleId = (String)pageData.get("roleId");
        if (roleId != null && roleId.trim().length() > 0) {
            String userIds = userRoleService.findUserIdsByByRoleID(roleId);
            if (userIds != null && userIds.trim().length() > 0) {
                userIds = StringUtil.stringTrimSpace(userIds);
                userIds = "'" + userIds.replace(",", "','") + "'";
                findMap.put("queryUserIdStr", "user.id not in (" + userIds + ")");
            }
        }

        findMap.put("userIsdisable", "1");
        //查询用户未绑定角色
        //findMap.put("queryIsBindRole", "userRole.role_id is null");

        //普通用户-外部用户
        //String queryUserType = "user_type in ('69726efa45044117ac94a33ab2938ce4','028fb82cfbe341b1954834edfa2fc18d')";
        //findMap.put("queryUserType", queryUserType);

        //获取当前登录用户id
        String userID = (String)pageData.get("cuser");
        User user = userService.findUserById(userID);
        if (user != null && DICTIONARY_MAP.get("userType_admin").equals(user.getUserType())) {
            //超级管理员 --> userType_admin:6839818aecfc41be8f367e62502dfde4
            //查询(用户类型:=企业管理员 )
            findMap.put("queryUserType", "user_type in ('2fb9bbee46ca4ce1913f3a673a7dd68f')");
        } else if (user != null && DICTIONARY_MAP.get("userType_company").equals(user.getUserType())) {
            //企业管理员 --> userType_company:2fb9bbee46ca4ce1913f3a673a7dd68f
            //查询(用户类型:= 普通用户-外部用户)
            findMap.put("queryUserType", "user_type in ('69726efa45044117ac94a33ab2938ce4','028fb82cfbe341b1954834edfa2fc18d')");
        }

        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
        List<Map> varList = userRoleService.listUserByRole(findMap);
        if(varList != null && varList.size() > 0) {
            for (Map<String, Object> map : varList) {
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(), map.get(entry.getKey()) != null ? map.get(entry.getKey()).toString() : "");
                }
                varMapList.add(varMap);
            }
        }
        mapObj.put("varList", varMapList);

        model.putResult(mapObj);
        return model;
    }

    @Override
    public ResultModel listUsersByRole(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        Map<String, Object> mapObj = new HashMap<String, Object>();

        //1. 查询遍历List列表
        List<Column> columnList = columnService.findColumnList("userRole");
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
        mapObj.put("hideTitles", titlesHideList);
        mapObj.put("titles", titlesList);

        //角色id-已经绑定的用户ID
        PageData findMap = new PageData();
        String roleId = (String)pageData.get("roleId");
        findMap.put("roleId", roleId);

        //获取当前登录用户id
        String userID = (String)pageData.get("cuser");
        User user = userService.findUserById(userID);
        if (user != null && DICTIONARY_MAP.get("userType_admin").equals(user.getUserType())) {
            //超级管理员 --> userType_admin:6839818aecfc41be8f367e62502dfde4
            //查询(用户类型:=企业管理员 )
            findMap.put("queryUserType", "user_type in ('2fb9bbee46ca4ce1913f3a673a7dd68f')");
        } else if (user != null && DICTIONARY_MAP.get("userType_company").equals(user.getUserType())) {
            //企业管理员 --> userType_company:2fb9bbee46ca4ce1913f3a673a7dd68f
            //查询(用户类型:= 普通用户-外部用户)
            findMap.put("queryUserType", "user_type in ('69726efa45044117ac94a33ab2938ce4','028fb82cfbe341b1954834edfa2fc18d')");
        }

        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
        List<Map> varList = userRoleService.listUserByRole(findMap);
        if(varList != null && varList.size() > 0) {
            for (Map<String, Object> map : varList) {
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(), map.get(entry.getKey()) != null ? map.get(entry.getKey()).toString() : "");
                }
                varMapList.add(varMap);
            }
        }
        mapObj.put("varList", varMapList);

        model.putResult(mapObj);
        return model;
    }

    @Override
    public ResultModel findListUserByRole(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        List<Map> mapList = userRoleService.listUserByRole(pageData);

        List<Map<String, String>> userMapList = new ArrayList<Map<String, String>>();
        if (mapList != null && mapList.size() > 0) {
            for (Map<String, Object> mapObj : mapList) {
                String id = (String)mapObj.get("id");
                String userName = (String)mapObj.get("userName");
                if (id != null && id.trim().length() > 0 && userName != null && userName.trim().length() > 0) {
                    Map<String, String> userMap = new HashMap<String, String>();
                    userMap.put("id", id);
                    userMap.put("userName", userName);
                    userMap.put("label",userName);
                    userMap.put("value",id);
                    userMapList.add(userMap);
                }
            }
        }

        Map result = new HashMap();
        result.put("options", userMapList);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel saveRoleMeunsData(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        RoleMenu roleMenu = (RoleMenu)HttpUtils.pageData2Entity(pageData, new RoleMenu());
        roleMenuService.update(roleMenu);
        return model;
    }

    @Override
    public ResultModel getRoleMeunsDataType(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        pageData.put("isQueryAll","true");
        List<RoleMenu> roleMenuList = roleMenuService.findRoleMenuList(pageData);
        if(roleMenuList!=null&&roleMenuList.size()>0){
            RoleMenu roleMenu = roleMenuList.get(0);
            Map result = new HashMap();
            result.put("roleMenu", roleMenu);
            model.putResult(result);
        }
        return model;
    }

    @Override
    public ResultModel listUserByRole(PageData pd) throws Exception {
        Pagination pg = HttpUtils.parsePagination(pd);
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("userRole");
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
        List<Map>  varList = userRoleService.listUserByRole(pd);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);

        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);
        result.put("pageData", pg);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel getRoles(PageData pd) throws Exception {

        ResultModel model = new ResultModel();
        List<Map> roleList = this.findDataList(pd);

        List<Map> options = new ArrayList<Map>();
        if(roleList!=null&&roleList.size()>0){
            for(int i=0;i<roleList.size();i++){
                Map role = roleList.get(i);
                if(role!=null){
                    Map option = new HashMap();
                    option.put("label",role.get("name"));
                    option.put("value",role.get("id"));
                    option.put("remark",role.get("remark"));
                    options.add(option);
                }

            }
        }
        Map result = new HashMap();
        result.put("options", options);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel addRoleByName(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String userType = pageData.getString("userType");
        //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型-超级管理员(userType_admin)
        if (Common.DICTIONARY_MAP.get("userType_admin").equals(userType)) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("超级管理员不可操作！");
            return model;
        }

        String roleName = pageData.getString("roleName");
        if (roleName == null || roleName.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("角色名称为空或空字符串，角色名称不可为空！");
            return model;
        }

        String companyId = pageData.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("企业id为空或空字符串！");
            return model;
        }

        //1. (企业id,角色名称) 查询(vmes_role)
        PageData findMap = new PageData();
        findMap.put("currentCompanyId", companyId);
        findMap.put("name", roleName);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Role> roleList = this.findRoleList(findMap);

        //Map<"roleId", String>
        //   <"roleName", String>
        //   <"type", old new>

        Map<String, String> roleMap = new HashMap<String, String>();
        if (roleList == null || roleList.size() == 0) {
            //获取角色编码
            String code = coderuleService.createCoder(companyId, "vmes_role", "R");

            //创建角色
            Role role = new Role();
            role.setCuser(pageData.getString("cuser"));
            role.setCompanyId(companyId);
            role.setName(roleName);
            role.setCode(code);
            this.save(role);

            roleMap.put("roleId", role.getId());
            roleMap.put("roleName", role.getName());
            roleMap.put("type", "new");
        } else if (roleList != null && roleList.size() > 0) {
            Role role = roleList.get(0);
            roleMap.put("roleId", role.getId());
            roleMap.put("roleName", role.getName());
            roleMap.put("type", "old");
        }

        model.putResult(roleMap);
        return model;
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    private Map<String, String> keyNameMap;
    private Map<String, String> nameKeyMap;

    public Map<String, String> getKeyNameMap() {
        return keyNameMap;
    }
    public Map<String, String> getNameKeyMap() {
        return nameKeyMap;
    }

    public void createBusinessMap() {
        this.keyNameMap = new HashMap<String, String>();
        this.nameKeyMap = new HashMap<String, String>();
    }
    public void implementBusinessMapByCompanyId(String companyId) {
        this.createBusinessMap();

        PageData findMap = new PageData();
        if (companyId != null && companyId.trim().length() > 0) {
            findMap.put("currentCompanyId", companyId.trim());
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Role> objectList = this.findRoleList(findMap);
        if (objectList == null || objectList.size() == 0) {return;}

        for (Role object : objectList) {
            String mapKey = object.getId();
            String mapName = object.getName();
            if (mapName != null && mapName.trim().length() > 0) {
                this.keyNameMap.put(mapKey, mapName);
                this.nameKeyMap.put(mapName, mapKey);
            }
        }
    }


}



