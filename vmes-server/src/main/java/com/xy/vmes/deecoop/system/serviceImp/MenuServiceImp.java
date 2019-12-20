package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.system.service.*;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.MenuMapper;
import com.xy.vmes.entity.*;
import com.xy.vmes.exception.ApplicationException;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

/**
* 说明：vmes_menu:系统功能菜单 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-07-31
*/
@Service
@Transactional(readOnly = false)
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    UserDefinedMenuService userDefinedMenuService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuButtonService menuButtonService;

    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void save(Menu menu) throws Exception{
        menu.setCdate(new Date());
        menuMapper.insert(menu);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void update(Menu menu) throws Exception{
        menu.setUdate(new Date());
        menuMapper.updateById(menu);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public void updateAll(Menu menu) throws Exception{
        menu.setUdate(new Date());
        menuMapper.updateAllColumnById(menu);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    //@Cacheable(cacheNames = "menu", key = "''+#id")
    public Menu selectById(String id) throws Exception{
        return menuMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void deleteById(String id) throws Exception{
        menuMapper.deleteById(id);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Menu> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return menuMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Menu> dataList(PageData pd) throws Exception{
        return menuMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return menuMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return menuMapper.findDataList(pd);
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    @Autowired
    private MenuTreeService menuTreeService;
    @Autowired
    private CoderuleService coderuleService;

    public void deleteByIds(String[] ids) throws Exception{
        menuMapper.deleteByIds(ids);
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
//    public List<LinkedHashMap> getColumnList() throws Exception{
//        return menuMapper.getColumnList();
//    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return menuMapper.getDataListPage(pd, pg);
    }

    /**
     * 生成菜单编码
     *
     * 创建人：陈刚
     * 创建时间：2018-08-02
     *
     * @param companyID  公司ID-组织架构ID
     * @return
     */
//    public String createCoder(String companyID) {
//        //(企业编号+前缀字符+日期字符+流水号)-(company+prefix+date+code)
//        //(无需+前缀字符+无需+流水号)-W000142
//        CoderuleEntity object = new CoderuleEntity();
//        //tableName 业务名称(表名)
//        object.setTableName("vmes_menu");
//        //companyID 公司ID
//        object.setCompanyID(companyID);
//        //length 指定位数(5)
//        object.setLength(Common.CODE_RULE_LENGTH_DEFAULT);
//        //firstName 第一个编码名称
//        object.setFirstName("prefix");
//
//        //separator 分隔符
//        //object.setSeparator("-");
//        //filling 填充字符(0)
//        object.setFilling(Common.CODE_RULE_DEFAULT_FILLING);
//
//        //isNeedPrefix 是否需要前缀
//        object.setIsNeedPrefix(Boolean.TRUE);
//        //prefix 前缀字符
//        object.setPrefix("M");
//
//        return coderuleService.findCoderule(object);
//    }
    /**
     * 批量修改菜单信息为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void updateDisableByIds(String[] ids) throws Exception {
        menuMapper.updateDisableByIds(ids);
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     * @param object
     * @return
     */
    public String checkColumnByAdd(Menu object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

//        if (object.getPid() == null || object.getPid().trim().length() == 0) {
//            msgBuf.append("pid为空或空字符串！");
//        }
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "菜单名称");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-31
     * @param object
     * @return
     */
    public String checkColumnByEdit(Menu object) {
        if (object == null) {return new String();}

        StringBuffer msgBuf = new StringBuffer();
        String column_isnull = "({0})输入为空或空字符串，({0})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;

        if (object.getId() == null || object.getId().trim().length() == 0) {
            msgBuf.append("id为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
//        if (object.getPid() == null || object.getPid().trim().length() == 0) {
//            msgBuf.append("pid为空或空字符串！");
//        }
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "菜单名称");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    /**
     * 查询菜单表(vmes_menu)
     * @param object
     * @return
     */
    public List<Menu> findMenuList(PageData object) {
        if (object == null) {return null;}

        List<Menu> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    /**
     * 查询菜单表(vmes_menu)
     * @param object
     * @return
     */
    public Menu findMenu(PageData object) {
        if (object == null) {return null;}

        List<Menu> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    /**
     * 根据ID-查询菜单表(vmes_menu)
     * @param id
     * @return
     */
    public Menu findMenuById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Menu> objectList = this.findMenuList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }

    /**
     * 根据pid-查询菜单表(vmes_menu)
     * @param pid
     * @return
     */
    public List<Menu> findMenuListByPid(String pid) {
        List<Menu> objectList = new ArrayList<Menu>();
        if (pid == null || pid.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        objectList = this.findMenuList(findMap);

        return objectList;
    }

    /**
     * 菜单名称同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param name  (不可为空)
     * @return
     *     true : 菜单名称存在名称相同
     *     false: 菜单名称不存在名称相同(默认值)
     */
    public boolean isExistByName(String pid, String id, String name) {
        if (pid == null || pid.trim().length() == 0) {return false;}
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        findMap.put("name", name);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Menu> objectList = this.findMenuList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    /**
     * 根据菜单对象<Menu>当前菜单节点下面所有节点生成树形结构
     * 1. <Menu>对象为空时-生成整棵树-(pid:=root)开始-菜单级别(0-5)
     * 2. <Menu>对象is not null
     * (允许为空)Menu.id         当前菜单id
     * (允许为空)Menu.companyId  当前菜单(companyId)-公司id
     * (允许为空)Menu.name       当前菜单名称
     * (允许为空)Menu.layer      当前菜单级别
     * 3. (id,companyId,name,layer) 不可同时为空
     * id is not null id对应的菜单+id下面所有子菜单
     * id is null (companyId,name,layer) is not null
     * <p>
     * <p>
     * <p>
     * 创建人：陈刚
     * 创建时间：2018-07-18
     *
     * @param menu 允许为空-<Menu>对象(vmes_menu:系统菜单表)
     * @return
     */
    public Tree<Menu> findMenuTree(Menu menu) {
        Menu findObj = null;

        if (menu == null) {
            findObj = new Menu();
            findObj.setPid("root");
        } else if (menu != null) {
            //1. 参数非空判断
            if (menu.getId() != null && menu.getId().trim().length() > 0) {
                findObj = new Menu();
                findObj.setId(menu.getId().trim());
            } else if (menu.getCompanyId() != null && menu.getCompanyId().trim().length() > 0
                    && menu.getName() != null && menu.getName().trim().length() > 0
                    && menu.getLayer() != null
                    ) {
                findObj = new Menu();
                findObj.setCompanyId(menu.getCompanyId().trim());
                findObj.setName(menu.getName().trim());
                findObj.setLayer(menu.getLayer());
            }
        }

        if (findObj == null) {
            throw new RestException("", "参数错误:Menu(id,companyId,name,layer) 参数为空或空字符串，请与管理员联系！");
        }

        //2. 根据参数查询(vmes_menu:系统部门表)--获得返回树结构根节点
        //isdisable:是否禁用(0:已禁用 1:启用)
        findObj.setIsdisable("1");

        PageData pageData = HttpUtils.entity2PageData(findObj, new PageData());
        List<Menu> objectList = this.findMenuList(pageData);
        if (objectList == null || objectList.size() == 0) {
            String msgStr = "参数错误:Menu(pid,code,name,layer) 查询无数据，请与管理员联系！";
            throw new RestException("", msgStr);
        }
        Menu root_obj = objectList.get(0);

        //3. 生成部门树
        menuTreeService.initialization();
        menuTreeService.findMenuTree(root_obj.getId());

        return null;
    }

    /**
     * 获取菜单id字符串-(','分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-07-19
     *
     */
    public String findMenuidByMenuList(List<Menu> objectList) {
        StringBuffer strBuf = new StringBuffer();
        if (objectList == null || objectList.size() == 0) {return strBuf.toString();}

        for (Menu dept : objectList) {
            strBuf.append(dept.getId().trim());
            strBuf.append(",");
        }

        String strTemp = strBuf.toString();
        if (strTemp.trim().length() > 0 && strTemp.lastIndexOf(",") != -1) {
            strTemp = strTemp.substring(0, strTemp.lastIndexOf(","));
            return strTemp;
        }

        return strBuf.toString();
    }

    /**
     * 获取菜单最大级别-遍历菜单List<Menu>
     *
     * 创建人：陈刚
     * 创建时间：2018-07-24
     * @param objectList
     * @return
     */
    public Integer findMaxLayerByMenuList(List<Menu> objectList) {
        if (objectList == null || objectList.size() == 0) {return null;}
        int maxLayer = 0;

        for (Menu object : objectList) {
            if (object.getLayer() != null && object.getLayer().intValue() > maxLayer) {
                maxLayer = object.getLayer().intValue();
            }
        }

        if (maxLayer > 0) {return Integer.valueOf(maxLayer);}
        return null;
    }

    public Integer findMaxSerialNumberByPid(String pid) {
        if (pid == null || pid.trim().length() == 0) {return Integer.valueOf(0);}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Menu> objectList = null;
        try {
            objectList = this.findMenuList(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (objectList != null && objectList.size() > 0) {return Integer.valueOf(objectList.size());}

        return Integer.valueOf(0);
    }

    public Menu id2MenuByLayer(String id, Integer layer, Menu objectDB) {
        if (objectDB == null) {objectDB = new Menu();}
        if (id == null || id.trim().length() == 0) {return objectDB;}
        if (layer == null) {return objectDB;}

        if (0 == layer.intValue()) {
            objectDB.setId0(id);
        } else if (1 == layer.intValue()) {
            objectDB.setId1(id);
        } else if (2 == layer.intValue()) {
            objectDB.setId2(id);
        } else if (3 == layer.intValue()) {
            objectDB.setId3(id);
        } else if (4 == layer.intValue()) {
            objectDB.setId4(id);
        } else if (layer.intValue() == 5) {
            objectDB.setId5(id);
        }

        return objectDB;
    }

    public Menu paterObject2ObjectDB(Menu paterObject, Menu objectDB) {
        if (objectDB == null) {objectDB = new Menu();}
        if (paterObject == null) {return objectDB;}

        if (paterObject.getId0() != null && paterObject.getId0().trim().length() > 0) {
            objectDB.setId0(paterObject.getId0().trim());
        }
        if (paterObject.getId1() != null && paterObject.getId1().trim().length() > 0) {
            objectDB.setId1(paterObject.getId1().trim());
        }
        if (paterObject.getId2() != null && paterObject.getId2().trim().length() > 0) {
            objectDB.setId2(paterObject.getId2().trim());
        }
        if (paterObject.getId3() != null && paterObject.getId3().trim().length() > 0) {
            objectDB.setId3(paterObject.getId3().trim());
        }
        if (paterObject.getId4() != null && paterObject.getId4().trim().length() > 0) {
            objectDB.setId4(paterObject.getId4().trim());
        }
        if (paterObject.getId5() != null && paterObject.getId5().trim().length() > 0) {
            objectDB.setId5(paterObject.getId5().trim());
        }

        return objectDB;
    }

    public Menu clearMenuByPath(Menu objectDB) {
        if (objectDB == null) {objectDB = new Menu();}

        objectDB.setId0(null);
        objectDB.setId1(null);
        objectDB.setId2(null);
        objectDB.setId3(null);
        objectDB.setId4(null);
        objectDB.setId5(null);
        objectDB.setLayer(null);

        return objectDB;
    }

    public Menu object2objectDB(Menu object, Menu objectDB) {
        if (objectDB == null) {objectDB = new Menu();}
        if (object == null) {return objectDB;}

        objectDB.setPid(object.getPid());
        objectDB.setName(object.getName());
        objectDB.setUrl(object.getUrl());
        objectDB.setIsdisable(object.getIsdisable());

        //serialNumber 菜单顺序
        if (object.getSerialNumber() != null) {
            objectDB.setSerialNumber(object.getSerialNumber());
        }

        return objectDB;
    }

    /**
     * check菜单ID是否允许删除
     * 菜单ID(菜单按钮)-是否使用
     *
     * 创建人：陈刚
     * 创建时间：2018-07-30
     * @param ids
     * @return
     */
    public String checkDeleteMenuByIds(String ids) {
        if (ids == null || ids.trim().length() == 0) {return new String();}

        String msgTemp = "第 {0} 行: 菜单在({1})中使用不可禁用！" + Common.SYS_ENDLINE_DEFAULT;
        StringBuffer msgBuf = new StringBuffer();

        String[] menuid_arry = ids.split(",");
        PageData findMap = new PageData();
        for (int i = 0; i < menuid_arry.length; i++) {
            String menuid = menuid_arry[i];

            findMap.put("isdisable", "1");
            findMap.put("roleId", menuid);
            findMap.put("mapSize", Integer.valueOf(findMap.size()));

            //当前菜单ID(菜单按钮)
            List<MenuButton> list = menuButtonService.findMenuButtonList(findMap);
            if (list != null && list.size() > 0) {
                String msg_3 = MessageFormat.format(msgTemp, (i+1), "菜单按钮");
                msgBuf.append(msg_3);
            }
        }

        return  msgBuf.toString();
    }


    @Override
    public ResultModel listPageMenus(PageData pd,Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map<String, Object> mapObj = new HashMap<String, Object>();

        //1. 查询遍历List列表
        List<Column> columnList = columnService.findColumnList("menu");
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
    public void exportExcelMenus(PageData pd,Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        String ids = pd.getString("ids");
        String queryColumn = pd.getString("queryColumn");
        List<Column> columnList = columnService.findColumnList("menu");
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
        String fileName = "ExcelMenu";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel addMenu(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        Menu menuObj = (Menu)HttpUtils.pageData2Entity(pageData, new Menu());
        if (menuObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 菜单对象Menu 异常！");
            return model;
        }

        String msgStr = this.checkColumnByAdd(menuObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Menu>
        String pid = "root";
        if (menuObj.getPid() != null && menuObj.getPid().trim().length() > 0) {
            pid = menuObj.getPid().trim();
        }

        Menu paterObj = null;
        if ("root".equals(pid)) {
            List<Menu> objList = this.findMenuListByPid(pid);
            if (objList != null && objList.size() > 0) {paterObj = objList.get(0);}
        } else {
            paterObj = this.findMenuById(pid);
        }

        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ menuObj.getPid() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (菜单名称)在同一层名称不可重复
        if (this.isExistByName(menuObj.getPid(), null, menuObj.getUrl())) {
            String msgTemp = "上级菜单名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "页面路径: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            String str_isnull = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    menuObj.getName());
            model.putCode(Integer.valueOf(1));
            model.putMsg(str_isnull);
            return model;
        }

        //3. 添加菜单
        String id = Conv.createUuid();
        menuObj.setId(id);
        menuObj = this.id2MenuByLayer(id,
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                menuObj);
        menuObj = this.paterObject2ObjectDB(paterObj, menuObj);

        //获取菜单编码
        //String code = menuService.createCoder("1");
        //menuObj.setCode(code);

        //设定上级节点ID
        menuObj.setPid(paterObj.getId());

        //设置菜单级别
        menuObj.setLayer(Integer.valueOf(paterObj.getLayer().intValue() + 1));
        //设置菜单默认显示顺序
        if (menuObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumberByPid(menuObj.getPid());
            menuObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }

        this.save(menuObj);
        return model;
    }

    @Override
    public ResultModel updateMenu(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");

            return model;
        }

        Menu menuObj = (Menu)HttpUtils.pageData2Entity(pageData, new Menu());
        if (menuObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 菜单对象Menu 异常！");

            return model;
        }

        String msgStr = this.checkColumnByEdit(menuObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Menu>
        String pid = "root";
        if (menuObj.getPid() != null && menuObj.getPid().trim().length() > 0) {
            pid = menuObj.getPid().trim();
        }

        Menu paterObj = null;
        if ("root".equals(pid)) {
            List<Menu> objList = this.findMenuListByPid(pid);
            if (objList != null && objList.size() > 0) {paterObj = objList.get(0);}
        } else {
            paterObj = this.findMenuById(pid);
        }
        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ menuObj.getPid() + ")系统中无数据，请与管理员联系！");

            return model;
        }

        //2. (菜单名称)在同一层名称不可重复
        if (this.isExistByName(menuObj.getPid(), menuObj.getId(), menuObj.getUrl())) {
            String msgTemp = "上级菜单名称: {0}" + Common.SYS_ENDLINE_DEFAULT +
                    "页面路径: {1}" + Common.SYS_ENDLINE_DEFAULT +
                    "在系统中已经重复！" + Common.SYS_ENDLINE_DEFAULT;
            msgTemp = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    menuObj.getName());
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgTemp);
            return model;
        }

        //3. 修改菜单属性值
        Menu menuDB = this.findMenuById(menuObj.getId());
        menuDB = this.object2objectDB(menuObj, menuDB);
        menuDB = this.clearMenuByPath(menuDB);
        menuDB = this.id2MenuByLayer(menuDB.getId(),
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                menuDB);
        menuDB = this.paterObject2ObjectDB(paterObj, menuDB);

        //设定上级节点ID
        menuDB.setPid(paterObj.getId());
        //设置菜单级别
        menuDB.setLayer(Integer.valueOf(paterObj.getLayer().intValue() + 1));
        //设置菜单默认显示顺序
        if (menuObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumberByPid(menuObj.getPid());
            menuObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }
        this.update(menuDB);
        return model;
    }

    @Override
    public ResultModel updateDisableMenu(PageData pageData) throws Exception {
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

        //2. 当前菜单ID(菜单按钮)中是否使用中
        msgStr = this.checkDeleteMenuByIds(id);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //3. 修改菜单(禁用)状态
        Menu objectDB = this.findMenuById(id);
        objectDB.setIsdisable(isdisable);
        objectDB.setUdate(new Date());
        this.update(objectDB);
        return model;
    }

    @Override
    public ResultModel deleteMenus(PageData pageData) throws Exception {
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

//        //2. 当前菜单ID(菜单按钮)中是否使用中
//        String msgStr = menuService.checkDeleteMenuByIds(id_str);
//        if (msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(msgStr);
//            return model;
//        }

        for (int i = 0; i < id_arry.length; i++) {
            String menuId = id_arry[i];
            try {
                //1. 删除(vmes_menu_button)菜单按钮表
                menuButtonService.deleteMenuButtonByMenuId(menuId);

                //2. 删除(vmes_role_menu)角色菜单表
                roleMenuService.deleteRoleMenuByMenuId(menuId);

                //3. 删除(vmes_user_defined_menu)用户主页表
                userDefinedMenuService.deleteUserDefinedMenuByMenuId(menuId);
            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }
        }

        //4. 删除(vmes_menu)菜单表
        this.deleteByIds(id_arry);

        return model;
    }

    @Override
    public ResultModel importExcelMenus(MultipartFile file) throws Exception {
        ResultModel model = new ResultModel();
        try {
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

            //1. Excel文件数据dataMapLst -->(转换) ExcelEntity (属性为导入模板字段)
            //2. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
            //3. Excel导入字段-名称唯一性判断-在Excel文件中
            //4. Excel导入字段-名称唯一性判断-在业务表中判断
            //5. List<ExcelEntity> --> (转换) List<业务表DB>对象
            //6. 遍历List<业务表DB> 对业务表添加或修改

        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }
        return model;
    }

    /**
     * 验证当前用户(用户id,角色id) 是否配置有角色，角色菜单
     * 1. 验证当前用户，角色id是否为空
     * 2. 验证当前用户角色id，是否关联有菜单
     *
     * @param userId  当前用户id
     * @param roleId  当前用户角色id
     * @throws ApplicationException
     */
    public void checkMeunByUserRole(String userId, String roleId) throws ApplicationException {
        if (userId == null || userId.trim().length() == 0) {
            throw new ApplicationException("用户id为空或空字符串");
        }
        User user = userService.findUserById(userId);

        //(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        String userType = user.getUserType();

        //非超级管理员账号-验证角色是否为空
        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType)) {
            //1. 验证角色是否为空
            String msgTemp_1 = "用户姓名({0}) 用户账号({1}) 没有配置角色，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
            if (roleId == null || roleId.trim().length() == 0) {
                String msgStr = MessageFormat.format(msgTemp_1,
                        user.getUserName(),
                        user.getUserCode());
                throw new ApplicationException(msgStr);
            }
        }

        //2.验证(用户,角色)系统菜单
        Role role = roleService.findRoleById(roleId);
        String queryStr = "";

        //(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType) && roleId != null && roleId.trim().length() > 0) {
            roleId = StringUtil.stringTrimSpace(roleId);

            String strTemp = "'" + roleId.replace(",", "','" + ",") + "'";
            queryStr = "b.role_id in (" + strTemp + ")";
        }

        PageData findMap = new PageData();
        if (queryStr.trim().length() > 0) {
            findMap.put("queryStr", queryStr);
            findMap.put("isdisable", "1");
        }
        findMap.put("menuIsdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        String msgTemp_2 = "用户姓名({0}) 用户账号({1}) 绑定的角色名称({2}) 该角色没有绑定菜单，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;

        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType) && roleId != null && roleId.trim().length() > 0) {
            List<Map<String, Object>> mapList = roleMenuService.findRoleMenuMapList(findMap);
            if (mapList == null || mapList.size() == 0) {
                String msgStr = MessageFormat.format(msgTemp_2,
                        user.getUserName(),
                        user.getUserCode(),
                        role.getName());
                throw new ApplicationException(msgStr);
            }
        }

    }





    @Override
    public ResultModel treeMeuns(PageData pageData) throws Exception {
        //1. 获取当前登录用户所有角色ID
        // 用户角色(当前用户)-(角色ID','分隔的字符串)
        ResultModel model = new ResultModel();
        String roleIds = (String)pageData.get("roleIds");
        Role role = roleService.findRoleById(roleIds);

        String userRole = "";
        String userType = (String)pageData.get("userType");

        List<TreeEntity> mapList = new ArrayList<>();

        //(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType) && roleIds != null && roleIds.trim().length() > 0) {
            userRole = roleIds;
            userRole = StringUtil.stringTrimSpace(userRole);

            //2. 获取当前用户角色所有菜单List
            String queryStr = "";
            if (userRole != null && userRole.trim().length() > 0) {
                String strTemp = "'" + userRole.replace(",", "','" + ",") + "'";
                queryStr = "b.role_id in (" + strTemp + ")";
            }

            PageData findMap = new PageData();
            if (queryStr.trim().length() > 0) {
                findMap.put("queryStr", queryStr);
                findMap.put("isdisable", "1");
            }
            //vmes_role_menu ADD INDEX IDX_ROLE_MENU(索引)
            findMap.put("menuIsdisable", "1");
            //findMap.put("orderStr", "b.layer asc,b.serial_number asc");
            findMap.put("mapSize", Integer.valueOf(findMap.size()));

            mapList = roleMenuService.getRoleMenuMapList(findMap);
            if (mapList == null || mapList.size() == 0) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("当前登录用户id:" + pageData.getString("cuser") + Common.SYS_ENDLINE_DEFAULT +
                        "1.没有配置角色" + Common.SYS_ENDLINE_DEFAULT +
                        "2.角色没有绑定菜单" + Common.SYS_ENDLINE_DEFAULT);
                return model;
            }

        } else {
            PageData pd = new PageData();
            mapList = roleMenuService.getNoRoleMenuMapList(pd);
        }

        TreeEntity nodeObject = new TreeEntity();
        nodeObject.setId(Common.SYS_MENU_MAP.get("root"));
        nodeObject.setPid("root");
        TreeUtil.createMenuTree(nodeObject,mapList);

        List<TreeEntity> treeList = nodeObject.getChildren();

        if (treeList == null || treeList.size() == 0) {
            String msgStr = "登录用户角色没有绑定菜单或绑定菜单异常，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
            if (role != null) {
                String msgTemp_2 = "角色编码({0})角色名称({1}) 该角色没有绑定菜单或绑定菜单异常，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
                msgStr = MessageFormat.format(msgTemp_2,
                        role.getCode(),
                        role.getName());

                model.putCode(Integer.valueOf(1));
                model.putMsg(msgStr);
                return model;
            }
        }

        String treeJsonStr = YvanUtil.toJson(treeList);
        //System.out.println("treeJsonStr: " + treeJsonStr);
        model.putResult(treeJsonStr);

        return model;
    }




//    @Override
//    public ResultModel treeMeuns(PageData pageData) throws Exception {
//        //1. 获取当前登录用户所有角色ID
//        // 用户角色(当前用户)-(角色ID','分隔的字符串)
//        ResultModel model = new ResultModel();
//        String roleIds = (String)pageData.get("roleIds");
//        Role role = roleService.findRoleById(roleIds);
//
//        String userRole = "";
//        String userType = (String)pageData.get("userType");
//
//        List<Menu> menuList = new ArrayList<>();
//        Integer maxLayer = Integer.valueOf(0);
//
//        //(userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
//        if (!Common.DICTIONARY_MAP.get("userType_admin").equals(userType) && roleIds != null && roleIds.trim().length() > 0) {
//            userRole = roleIds;
//            userRole = StringUtil.stringTrimSpace(userRole);
//
//            //2. 获取当前用户角色所有菜单List
//            String queryStr = "";
//            if (userRole != null && userRole.trim().length() > 0) {
//                String strTemp = "'" + userRole.replace(",", "','" + ",") + "'";
//                queryStr = "b.role_id in (" + strTemp + ")";
//            }
//
//            PageData findMap = new PageData();
//            if (queryStr.trim().length() > 0) {
//                findMap.put("queryStr", queryStr);
//                findMap.put("isdisable", "1");
//            }
//            //vmes_role_menu ADD INDEX IDX_ROLE_MENU(索引)
//            findMap.put("menuIsdisable", "1");
//            //findMap.put("orderStr", "b.layer asc,b.serial_number asc");
//            findMap.put("mapSize", Integer.valueOf(findMap.size()));
//
//            List<Map<String, Object>> mapList = roleMenuService.findRoleMenuMapList(findMap);
//            if (mapList == null || mapList.size() == 0) {
//                model.putCode(Integer.valueOf(1));
//                model.putMsg("当前登录用户id:" + pageData.getString("cuser") + Common.SYS_ENDLINE_DEFAULT +
//                        "1.没有配置角色" + Common.SYS_ENDLINE_DEFAULT +
//                        "2.角色没有绑定菜单" + Common.SYS_ENDLINE_DEFAULT);
//                return model;
//            }
//
//            menuList = roleMenuService.mapList2MenuList(mapList, new ArrayList<Menu>());
//        } else {
//            PageData pd = new PageData();
//            pd.put("isQueryAll","true");
//            menuList = this.dataList(pd);
//        }
//
//        //遍历菜单List<Menu>-获取菜单最大级别
//        if (menuList != null && menuList.size() > 0) {
//            maxLayer = this.findMaxLayerByMenuList(menuList);
//        }
//
//        //3. 生成菜单树
//        menuTreeService.initialization();
//        menuTreeService.findMenuTreeByList(menuList, maxLayer);
//        List<TreeEntity> treeList = menuTreeService.creatMenuTree(maxLayer, null, null);
//        if (treeList == null || treeList.size() == 0) {
//            String msgStr = "登录用户角色没有绑定菜单或绑定菜单异常，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
//            if (role != null) {
//                String msgTemp_2 = "角色编码({0})角色名称({1}) 该角色没有绑定菜单或绑定菜单异常，请与管理员联系！" + Common.SYS_ENDLINE_DEFAULT;
//                msgStr = MessageFormat.format(msgTemp_2,
//                        role.getCode(),
//                        role.getName());
//
//                model.putCode(Integer.valueOf(1));
//                model.putMsg(msgStr);
//                return model;
//            }
//        }
//
//        String treeJsonStr = YvanUtil.toJson(treeList);
//        //System.out.println("treeJsonStr: " + treeJsonStr);
//        model.putResult(treeJsonStr);
//
//        return model;
//    }

    public List<Map<String, Object>> listMenuKeyByApp(String roleId, String appMenuId) throws Exception{
        PageData findMap = new PageData();
        findMap.put("roleId", roleId);
        findMap.put("appMenuId", appMenuId);

        return menuMapper.listMenuKeyByApp(findMap);
    }
}



