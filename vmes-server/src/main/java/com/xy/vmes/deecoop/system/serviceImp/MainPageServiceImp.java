package com.xy.vmes.deecoop.system.serviceImp;


import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.entity.*;
import com.yvan.HttpUtils;
import com.yvan.MD5Utils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 说明：vmes_menu_button:菜单按钮表 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-08-03
*/
@Service
@Transactional(readOnly = false)
public class MainPageServiceImp implements MainPageService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDefinedMenuService userDefinedMenuService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private RoleMenuService roleMenuService;
//
    @Override
    public ResultModel changePassWord(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //修改用户信息
        User user = (User) HttpUtils.pageData2Entity(pd, new User());
        user.setPassword(MD5Utils.MD5(user.getPassword()));
        userService.update(user);
        return model;
    }

    @Override
    public ResultModel changePageStyle(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //修改用户信息
        User user = (User)HttpUtils.pageData2Entity(pd, new User());
        userService.update(user);
        return model;
    }

    @Override
    public ResultModel saveUserDefinedMenu(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        ArrayList userDefinedMenusList = (ArrayList)pd.get("userDefinedMenus");
        if(userDefinedMenusList!=null&&userDefinedMenusList.size()>0){
            for(int i=0;i<userDefinedMenusList.size();i++){
                Map userDefinedMenusMap = (Map) userDefinedMenusList.get(i);
                if(userDefinedMenusMap!=null){
                    UserDefinedMenu userDefinedMenu = new UserDefinedMenu();

                    userDefinedMenu.setMenuId(userDefinedMenusMap.get("id").toString());
                    userDefinedMenu.setSerialNumber(Integer.parseInt(userDefinedMenusMap.get("serialNumber").toString()));
                    userDefinedMenu.setUserId(userDefinedMenusMap.get("userId").toString());

                    if(i==0){
                        Map columnMap = new HashMap();
                        columnMap.put("user_id",userDefinedMenu.getUserId());
                        userDefinedMenuService.deleteByColumnMap(columnMap);
                    }

                    userDefinedMenuService.save(userDefinedMenu);
                }

            }
        }
        return model;
    }

    @Override
    public ResultModel listUserDefinedMenu(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("mainPage");
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
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);


        List<Map> varMapList = new ArrayList();
        List<Map> varList = userDefinedMenuService.getDataList(pd);
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

        model.putResult(result);

        return model;
    }

    @Override
    public ResultModel listRoleMeunAll(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String userType = (String)pageData.get("userType");

        //角色Id字符串-->转换成 sql查询字符串
        String roleIds = (String)pageData.get("roleIds");
        String queryIds = "";
        if (roleIds != null && roleIds.trim().length() > 0) {
            roleIds = StringUtil.stringTrimSpace(roleIds);
            roleIds = "'" + roleIds.replace(",", "','") + "'";
            queryIds = "b.role_id in (" + roleIds + ")";
        }

        PageData findMap = new PageData();
        //超级管理员:  全部菜单表数据
        //非超级管理员 当前登录用户角色已经绑定的菜单
        //userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        if (userType != null
                && !Common.DICTIONARY_MAP.get("userType_admin").equals(userType)
                && queryIds.trim().length() > 0
                ) {
            findMap.put("queryStr", queryIds);
        }
        findMap.put("menuIsdisable", "1");
        findMap.put("rootStr", "pid = 'root'");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Map<String, Object>> mapList = roleMenuService.findRoleMenuMapList(findMap);

        List<TreeEntity> entityList = roleMenuService.roleMenuList2TreeList(mapList, null);
        List<TreeEntity> treeList = TreeUtil.listSwitchTree(null, entityList);
        List<TreeEntity> nodeList = TreeUtil.findNodeListByTreeList("leaf", treeList, null);

        List<MenuEntity> menuLiset = roleMenuService.treeList2MenuEntityList(nodeList, null);
        roleMenuService.orderAcsByLayer(menuLiset);
        model.putResult(menuLiset);
        return model;
    }
}



