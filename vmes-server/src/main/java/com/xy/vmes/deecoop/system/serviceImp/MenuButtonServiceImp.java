package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.MenuButtonMapper;
import com.xy.vmes.entity.*;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.MenuButtonService;
import com.xy.vmes.deecoop.system.service.MenuService;
import com.xy.vmes.deecoop.system.service.RoleButtonService;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_menu_button:菜单按钮表 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-08-03
*/
@Service
@Transactional(readOnly = false)
public class MenuButtonServiceImp implements MenuButtonService {


    @Autowired
    private MenuButtonMapper menuButtonMapper;
    @Autowired
    private RoleButtonService roleButtonService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private MenuService menuService;
    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public void save(MenuButton menuButton) throws Exception{
        menuButton.setId(Conv.createUuid());
        menuButton.setCdate(new Date());
        menuButton.setUdate(new Date());
        menuButtonMapper.insert(menuButton);
    }


    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-03
     */
    @Override
    public void update(MenuButton menuButton) throws Exception{
        menuButton.setUdate(new Date());
        menuButtonMapper.updateById(menuButton);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-08-03
     */
    @Override
    public void updateAll(MenuButton menuButton) throws Exception{
        menuButton.setUdate(new Date());
        menuButtonMapper.updateAllColumnById(menuButton);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    //@Cacheable(cacheNames = "menuButton", key = "''+#id")
    public MenuButton selectById(String id) throws Exception{
        return menuButtonMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public void deleteById(String id) throws Exception{
        menuButtonMapper.deleteById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        menuButtonMapper.deleteByIds(ids);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<MenuButton> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return menuButtonMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<MenuButton> dataList(PageData pd) throws Exception{
        return menuButtonMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return menuButtonMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return menuButtonMapper.findDataList(pd);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        menuButtonMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<MenuButton> selectByColumnMap(Map columnMap) throws Exception{
    List<MenuButton> menuButtonList =  menuButtonMapper.selectByMap(columnMap);
        return menuButtonList;
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return menuButtonMapper.getColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return menuButtonMapper.getDataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return menuButtonMapper.getDataListPage(pd, pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-08-03
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        menuButtonMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public List<MenuButton> findMenuButtonList(PageData object) {
        List<MenuButton> objectList = new ArrayList<MenuButton>();
        if (object == null) {return objectList;}

        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<MenuButton> findMenuButtonListByIds(String ids) {
        List<MenuButton> objectList = new ArrayList<MenuButton>();
        if (ids == null || ids.trim().length() == 0) {return objectList;}

        ids = StringUtil.stringTrimSpace(ids);
        ids = "'" + ids.replace(",", "','") + "'";
        String queryStr = "id in (" + ids + ") ";

        PageData findMap = new PageData();
        findMap.put("queryStr", queryStr);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findMenuButtonList(findMap);
    }
    /**
     * 根据ID-查询菜单按钮表(vmes_menu_button)
     * @param id
     * @return
     */
    public MenuButton findMenuButtonById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<MenuButton> objectList = this.findMenuButtonList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }

    /**
     * 遍历List<MenuButton>-获取(修改禁用属性主键ID,物理删除主键ID)
     * 启用状态改为(禁用)-禁用执行物理删除
     * Map<String, String>
     *     "updateDisableIds"
     *     "deleteIds"
     *
     * @param objectList
     * @return
     */
    public Map<String, String> checkDeleteMenuButtonByList(List<MenuButton> objectList) {
        Map<String, String> mapObject = new HashMap<String, String>();
        if (objectList == null || objectList.size() == 0) {return mapObject;}

        StringBuffer updateIds = new StringBuffer();
        StringBuffer deleteIds = new StringBuffer();
        for (MenuButton object : objectList) {
            String id = object.getId();
            //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
            if ("1".equals(object.getIsdisable())) {
                updateIds.append(id);
                updateIds.append(",");
            } else {
                deleteIds.append(id);
                deleteIds.append(",");
            }
        }

        String strTemp_1 = updateIds.toString();
        //去掉最后一个','
        if (strTemp_1.lastIndexOf(",") != -1) {
            strTemp_1 = strTemp_1.substring(0, strTemp_1.lastIndexOf(","));
        }
        mapObject.put("updateDisableIds", strTemp_1);

        String strTemp_2 = deleteIds.toString();
        //去掉最后一个','
        if (strTemp_2.lastIndexOf(",") != -1) {
            strTemp_2 = strTemp_2.substring(0, strTemp_2.lastIndexOf(","));
        }
        mapObject.put("deleteIds", strTemp_2);

        return mapObject;
    }

    /**
     * 根据菜单ID-删除菜单按钮
     * 创建人：陈刚
     * 创建时间：2018-08-01
     */
    public void deleteMenuButtonByMenuId(String menuId) throws Exception {
        menuButtonMapper.deleteMenuButtonByMenuId(menuId);
    }

    /**
     * 修改禁用属性(isdisable)
     * 根据菜单ID-修改菜单按钮
     * 创建人：陈刚
     * 创建时间：2018-08-01
     */
    public void updateDisableByMenuId(String menuId) {
        menuButtonMapper.updateDisableByMenuId(menuId);
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-03
     * @param object
     * @return
     */
    public String checkColumnByAdd(MenuButton object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "按钮名称");
            msgBuf.append(str_isnull);
        }
        if (object.getNameEn() == null || object.getNameEn().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "按钮英文名称");

            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }
    /**
     * 创建人：陈刚
     * 创建时间：2018-08-03
     * @param object
     * @return
     */
    public String checkColumnByEdit(MenuButton object) {
        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        if (object.getId() == null || object.getId().trim().length() == 0) {
            msgBuf.append("id为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "按钮名称");
            msgBuf.append(str_isnull);
        }
        if (object.getNameEn() == null || object.getNameEn().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "按钮英文名称");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    /**
     * 菜单ID-按钮名称是否相同
     *
     * @param menuId  (不可为空)
     * @param id      (允许为空)-(添加时is null, 修改时 is not null)
     * @param name    (不可为空)
     * @return
     *     true : 菜单名称存在名称相同
     *     false: 菜单名称不存在名称相同(默认值)
     */
    public boolean isExistByName(String menuId, String id, String name) {
        if (menuId == null || menuId.trim().length() == 0) {return false;}
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("menuId", menuId);
        findMap.put("name", name);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<MenuButton> objectList = this.findMenuButtonList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    /**
     * 菜单ID-按钮属性值是否相同
     *
     * @param menuId  (不可为空)
     * @param id      (允许为空)-(添加时is null, 修改时 is not null)
     * @param nameEn  (不可为空)-按钮英文名称
     * @return
     *     true : 菜单名称存在名称相同
     *     false: 菜单名称不存在名称相同(默认值)
     */
    public boolean isExistByNameEn(String menuId, String id, String nameEn) {
        if (menuId == null || menuId.trim().length() == 0) {return false;}
        if (nameEn == null || nameEn.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("menuId", menuId);
        findMap.put("nameEn", nameEn);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<MenuButton> objectList = this.findMenuButtonList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    public Integer findMaxSerialNumber(String menuId) {
        if (menuId == null || menuId.trim().length() == 0) {return Integer.valueOf(0);}

        PageData findMap = new PageData();
        findMap.put("menuId", menuId);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<MenuButton> objectList = null;
        try {
            objectList = this.findMenuButtonList(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (objectList != null && objectList.size() > 0) {
            return Integer.valueOf(objectList.size());
        }

        return Integer.valueOf(0);
    }

    public MenuButton object2objectDB(MenuButton object, MenuButton objectDB) {
        if (objectDB == null) {objectDB = new MenuButton();}
        if (object == null) {return objectDB;}

        objectDB.setName(object.getName());
        objectDB.setNameEn(object.getNameEn());
        //serialNumber按钮顺序
        if (object.getSerialNumber() != null) {
            objectDB.setSerialNumber(object.getSerialNumber());
        }

        return objectDB;
    }

    /**
     * 获取按钮ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-09-14
     *
     * @param objectList
     * @return
     */
    public String findButtonIdsByMenuButtonList(List<MenuButton> objectList) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        StringBuffer strBuf = new StringBuffer();
        for (MenuButton object : objectList) {
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

    @Override
    public ResultModel addMeunButton(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        MenuButton buttonObj = (MenuButton) HttpUtils.pageData2Entity(pageData, new MenuButton());
        if (buttonObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 按钮对象MenuButton 异常！");
            return model;
        }

        String msgStr = this.checkColumnByAdd(buttonObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }
//
        //menuId 获取菜单对象<Menu>
        Menu menuObj = menuService.findMenuById(buttonObj.getMenuId());
        if (menuObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(菜单ID:"+ buttonObj.getMenuId() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (菜单)按钮名称-按钮属性值-不可重复
        StringBuffer msgBuf = new StringBuffer();

        if (this.isExistByNameEn(buttonObj.getMenuId(), null, buttonObj.getNameEn())) {
            String msgTemp = "菜单名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "按钮英文名: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    menuObj.getName(),
                    buttonObj.getCode());
            msgBuf.append(msgExist);
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //设置按钮默认顺序
        if (buttonObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumber(buttonObj.getMenuId());
            buttonObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }

        //3. 添加(菜单)按钮
        this.save(buttonObj);
        return model;
    }

    @Override
    public ResultModel updateMeunButton(PageData pageData) throws Exception {


        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        MenuButton buttonObj = (MenuButton)HttpUtils.pageData2Entity(pageData, new MenuButton());
        if (buttonObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 按钮对象MenuButton 异常！");
            return model;
        }

        String msgStr = this.checkColumnByEdit(buttonObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //menuId 获取菜单对象<Menu>
        Menu menuObj = menuService.findMenuById(buttonObj.getMenuId());
        if (menuObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(菜单ID:"+ buttonObj.getMenuId() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (菜单)按钮名称-按钮属性值-不可重复
        StringBuffer msgBuf = new StringBuffer();

        if (this.isExistByNameEn(buttonObj.getMenuId(), buttonObj.getId(), buttonObj.getNameEn())) {
            String msgTemp = "菜单名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "按钮英文名: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String msgExist = MessageFormat.format(msgTemp,
                    menuObj.getName(),
                    buttonObj.getCode());
            msgBuf.append(msgExist);
        }
        if (msgBuf.toString().trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        MenuButton objectDB = this.findMenuButtonById(buttonObj.getId());
        objectDB = this.object2objectDB(buttonObj, objectDB);

        //设置按钮默认顺序
        if (buttonObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumber(buttonObj.getMenuId());
            objectDB.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }

        //修改(菜单)按钮
        this.update(objectDB);
        return model;
    }

    @Override
    public ResultModel updateDisableMeunButton(PageData pageData) throws Exception {
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

        //2. 修改按钮(禁用)状态
        MenuButton objectDB = this.findMenuButtonById(id);
        objectDB.setIsdisable(isdisable);
        objectDB.setUdate(new Date());
        this.update(objectDB);
        return model;
    }


    @Override
    public ResultModel deleteMeunButtons(PageData pageData) throws Exception {
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

        String id_str = StringUtil.stringTrimSpace(ids);
        String[] id_arry = id_str.split(",");

        for (int i = 0; i < id_arry.length; i++) {
            String menuButtonId = id_arry[i];
            //1. 删除(vmes_role_button)菜单按钮表
            roleButtonService.deleteRoleButtonByButtonId(menuButtonId);
        }
        //2. 删除(vmes_menu_button)菜单按钮表
        this.deleteByIds(id_arry);
        return model;
    }

    @Override
    public ResultModel listPageMenuButtons(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map<String, Object> mapObj = new HashMap<String, Object>();

        List<Column> columnList = columnService.findColumnList("button");
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
        mapObj.put("titles", YvanUtil.toJson(titlesList));

        //2. 分页查询数据List

        //设置查询排序
        pd.put("orderStr", "cdate asc");
        String orderStr = pd.getString("orderStr");
        if (orderStr != null && orderStr.trim().length() > 0) {
            pd.put("orderStr", orderStr);
        }
        pg.setSize(100);
        List<Map<String, String>> varMapList = new ArrayList<Map<String, String>>();
        List<Map> varList = this.getDataListPage(pd, pg);
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
    public void exportExcelMenuButtons(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        String ids = pd.getString("ids");
        String queryColumn = pd.getString("queryColumn");
        List<Column> columnList = columnService.findColumnList("button");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //3. 根据查询条件获取业务数据List
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        if (queryColumn != null && queryColumn.trim().length() > 0) {
            queryStr = queryStr + queryColumn;
        }

        pd.put("queryStr", queryStr);
        //分页参数默认设置100000
        pg.setSize(100000);
        List<Map> dataList = this.getDataListPage(pd,pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response  = HttpUtils.currentResponse();


        //查询数据-Excel文件导出
        //String fileName = "Excel数据字典数据导出";
        String fileName = "ExcelMenuButton";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel initMenuButtons(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String menuId = pageData.getString("menuId");
        if (menuId == null || menuId.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("菜单ID为空或空字符串！");
            return model;
        }

        List<MenuButtonEntity> buttonList = new ArrayList<MenuButtonEntity>();
        String roleIds = pageData.getString("roleId");
        if (roleIds != null && roleIds.trim().length() > 0) {
            //1. 根据角色id查询(vmes_role_button)-角色按钮表
            PageData findMap = new PageData();
            roleIds = StringUtil.stringTrimSpace(roleIds);
            roleIds = "'" + roleIds.replace(",", "','") + "'";
            findMap.put("queryStr", "role_id in (" + roleIds + ")");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<RoleButton> roleButtonList = new ArrayList<RoleButton>();
            roleButtonList = roleButtonService.findRoleButtonList(findMap);
            if (roleButtonList == null || roleButtonList.size() == 0) {
                model.put("buttonList", buttonList);
                return model;
            }

            //2. 遍历List<RoleButton>-获取按钮id字符串
            String buttonIds = "";
            if (roleButtonList.size() > 0) {
                buttonIds = roleButtonService.findButtonIdsByRoleButtonList(roleButtonList);
            }
            if (buttonIds.trim().length() == 0) {
                model.put("buttonList", buttonList);
                return model;
            }

            //3. (菜单id, 按钮id)-查询(vmes_menu_button)-菜单按钮表
            findMap = new PageData();
            findMap.put("menuId", menuId);
            //是否禁用(0:已禁用 1:启用)
            findMap.put("isdisable", "1");
            if (buttonIds.trim().length() > 0) {
                buttonIds = StringUtil.stringTrimSpace(buttonIds);
                buttonIds = "'" + buttonIds.replace(",", "','") + "'";
                findMap.put("queryStr", "id in (" + buttonIds + ")");
            }
            findMap.put("mapSize", Integer.valueOf(findMap.size()));
            List<MenuButton> menuButtonList = this.findMenuButtonList(findMap);

            //4. 遍历List<MenuButton> 得到按钮List<MenuButtonEntity>
            if (menuButtonList != null && menuButtonList.size() > 0) {
                for (MenuButton menuButton : menuButtonList) {
                    MenuButtonEntity buttonEntity = roleButtonService.menuButton2ButtonsEntity(menuButton, null);
                    buttonList.add(buttonEntity);
                }
            }
        }

        model.put("buttonList", buttonList);
        return model;
    }



}



