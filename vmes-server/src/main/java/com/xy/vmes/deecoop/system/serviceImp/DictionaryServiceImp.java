package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.*;
import com.xy.vmes.deecoop.system.dao.DictionaryMapper;
import com.xy.vmes.entity.*;
import com.xy.vmes.entity.Dictionary;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.DictionaryDeleteCheckService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.xy.vmes.deecoop.system.service.UserService;
import com.yvan.Conv;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.cache.RedisClient;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;


/**
* 说明：数据字典 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-07-31
*/
@Service
@Transactional(readOnly = false)
public class DictionaryServiceImp implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private DictionaryDeleteCheckService dictionaryDeleteCheckService;

    @Autowired
    private ColumnService columnService;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void save(Dictionary dictionary) throws Exception{
        dictionary.setCdate(new Date());
        dictionary.setUdate(new Date());
        dictionaryMapper.insert(dictionary);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void update(Dictionary dictionary) throws Exception{
        dictionary.setUdate(new Date());
        dictionaryMapper.updateById(dictionary);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public void updateAll(Dictionary dictionary) throws Exception{
        dictionary.setUdate(new Date());
        dictionaryMapper.updateAllColumnById(dictionary);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    //@Cacheable(cacheNames = "dictionary", key = "''+#id")
    public Dictionary selectById(String id) throws Exception{
        return dictionaryMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void deleteById(String id) throws Exception{
        dictionaryMapper.deleteById(id);
    }
    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-23
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        dictionaryMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Dictionary> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return dictionaryMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Dictionary> dataList(PageData pd) throws Exception{
        return dictionaryMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return dictionaryMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return dictionaryMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        dictionaryMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-31
    */
    @Override
    public List<Dictionary> selectByColumnMap(Map columnMap) throws Exception{
    List<Dictionary> dictionaryList =  dictionaryMapper.selectByMap(columnMap);
        return dictionaryList;
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return dictionaryMapper.getColumnList();
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return dictionaryMapper.getDataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return dictionaryMapper.getDataListPage(pd,pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-31
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        dictionaryMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    private Map<String, String> keyNameMap;
    private Map<String, String> nameKeyMap;

    @Override
    public Map<String, String> getKeyNameMap() {
        return keyNameMap;
    }

    @Override
    public Map<String, String> getNameKeyMap() {
        return nameKeyMap;
    }


    public void createBusinessMap() {
        this.keyNameMap = new HashMap<String, String>();
        this.nameKeyMap = new HashMap<String, String>();
    }

    @Override
    public void implementBusinessMapByParentID(String parentId, String companyId) {
        this.createBusinessMap();

        PageData findMap = new PageData();
        if (parentId != null && parentId.trim().length() > 0) {
            findMap.put("pid", parentId.trim());
        }
        if (companyId != null && companyId.trim().length() > 0) {
            findMap.put("currentCompanyId", companyId.trim());
        }
        //是否启用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Dictionary> objectList = this.findDictionaryList(findMap);
        if (objectList == null || objectList.size() == 0) {return;}

        for (Dictionary object : objectList) {
            String mapKey = object.getId();
            String mapName = object.getName();
            if (mapName != null && mapName.trim().length() > 0) {
                this.keyNameMap.put(mapKey, mapName);
                this.nameKeyMap.put(mapName, mapKey);
            }
        }
    }

    public void implementBusinessMapByParentID(String parentId, String companyId, String idNotin) {
        this.createBusinessMap();

        PageData findMap = new PageData();
        if (parentId != null && parentId.trim().length() > 0) {
            findMap.put("pid", parentId.trim());
        }
        if (companyId != null && companyId.trim().length() > 0) {
            findMap.put("currentCompanyId", companyId.trim());
        }
        if (idNotin != null && idNotin.trim().length() > 0) {
            findMap.put("idNotin", idNotin.trim());
        }

        //是否启用(0:已禁用 1:启用)
        //findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Dictionary> objectList = this.findDictionaryList(findMap);
        if (objectList == null || objectList.size() == 0) {return;}

        for (Dictionary object : objectList) {
            String mapKey = object.getId();
            String mapName = object.getName();
            if (mapName != null && mapName.trim().length() > 0) {
                this.keyNameMap.put(mapKey, mapName);
                this.nameKeyMap.put(mapName, mapKey);
            }
        }
    }

    public void implementBusinessMapByAreaPath() {
        this.createBusinessMap();

        Dictionary dictionary = this.findDictionaryById(Common.DICTIONARY_MAP.get("area"));

        List<Map<String, Object>> mapList = this.findDictionaryListByPathName(null,
                "true",
                dictionary.getId(),
                dictionary.getLayer(),
                Integer.valueOf(dictionary.getLayer().intValue() + 1));

        if (mapList == null || mapList.size() == 0) {return;}
        for (Map<String, Object> mapObject : mapList) {
            String id = (String)mapObject.get("id");
            String pathName = (String)mapObject.get("pathName");
            if (pathName != null && pathName.trim().length() > 0) {
                this.keyNameMap.put(id, pathName);
                this.nameKeyMap.put(pathName, id);
            }
        }
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-08-01
     */
    @Override
    public List<TreeEntity>  getTreeList(PageData pd)throws Exception{
        return  dictionaryMapper.getTreeList(pd);
    }

    public Dictionary findDictionary(PageData object) {
        if (object == null) {return null;}

        List<Dictionary> objectList = this.findDictionaryList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }
    public Dictionary findDictionaryById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findDictionary(findMap);
    }

    public List<Dictionary> findDictionaryList(PageData object) {
        if (object == null) {return null;}

        List<Dictionary> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<Dictionary> findDictionaryListByPid(String pid) {
        List<Dictionary> objectList = new ArrayList<Dictionary>();
        if (pid == null || pid.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findDictionaryList(findMap);
    }

    /**
     * 字典名称同一层级是否相同
     *
     * @param pid   (不可为空)
     * @param id    (允许为空)-(添加时is null, 修改时 is not null)
     * @param name  (不可为空)
     * @return
     *     true : 组织名称存在名称相同
     *     false: 组织名称不存在名称相同(默认值)
     */
    public boolean isExistByName(String pid, String id, String name, String currentCompanyId) {
        if (pid == null || pid.trim().length() == 0) {return false;}
        if (name == null || name.trim().length() == 0) {return false;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        findMap.put("name", name);
        findMap.put("currentCompanyId",currentCompanyId);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Dictionary> objectList = this.findDictionaryList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    public Dictionary id2DictionaryByLayer(String id, Integer layer, Dictionary objectDB) {
        if (objectDB == null) {objectDB = new Dictionary();}
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
        } else if (5 == layer.intValue()) {
            objectDB.setId5(id);
        }
        return objectDB;
    }

    public Dictionary paterObject2ObjectDB(Dictionary paterObject, Dictionary objectDB) {
        if (objectDB == null) {objectDB = new Dictionary();}
        if (paterObject == null) {return objectDB;}

        if (paterObject.getId() != null && paterObject.getId().trim().length() > 0) {
            objectDB.setPid(paterObject.getId().trim());
        }
        if (paterObject.getLayer() != null) {
            objectDB.setLayer(Integer.valueOf(paterObject.getLayer().intValue() + 1));
        }
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
    public Dictionary clearDictionaryByPath(Dictionary objectDB) {
        if (objectDB == null) {objectDB = new Dictionary();}

        objectDB.setId0(null);
        objectDB.setId1(null);
        objectDB.setId2(null);
        objectDB.setId3(null);
        objectDB.setId4(null);
        objectDB.setId5(null);
        objectDB.setLayer(null);

        return objectDB;
    }

    public Dictionary object2objectDB(Dictionary object, Dictionary objectDB) {
        if (objectDB == null) {objectDB = new Dictionary();}
        if (object == null) {return objectDB;}

        objectDB.setPid(object.getPid());
        objectDB.setName(object.getName());
        objectDB.setRemark(object.getRemark());
        objectDB.setIsdisable(object.getIsdisable());
        if (object.getSerialNumber() != null) {
            objectDB.setSerialNumber(object.getSerialNumber());
        }
        return objectDB;
    }

    public Integer findMaxSerialNumber(String pid) {
        if (pid == null || pid.trim().length() == 0) {return Integer.valueOf(0);}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Dictionary> objectList = this.findDictionaryList(findMap);
        if (objectList != null && objectList.size() > 0) {
            return Integer.valueOf(objectList.size());
        }

        return Integer.valueOf(0);
    }

    /**
     * check字典列表List<Dictionary>是否允许删除
     * 当前字典节点下是否含有子节点
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param ids
     * @return
     */
    public String checkDeleteDictionaryByIds(String ids, String companyId) throws Exception {
        if (ids == null || ids.trim().length() == 0) {return new String();}

        String msgTemp_1 = "勾选数据 第 {0} 行：存在子节点不可删除禁用！" + Common.SYS_ENDLINE_DEFAULT;
        String msgTemp_2 = "勾选数据 第 {0} 行：{1}不可删除禁用！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer msgBuf = new StringBuffer();
        String[] id_arry = ids.split(",");
        for (int i = 0; i < id_arry.length; i++) {
            String id = id_arry[i];
            List<Dictionary> childList = this.findDictionaryListByPid(id);
            //1. 查询当前字典节点下是否含有子节点
            if (childList != null && childList.size() > 0) {
                String msg_Str = MessageFormat.format(msgTemp_1, (i+1));
                msgBuf.append(msg_Str);
            }

            //2. 当前字典id 查询相关业务表
            String msgStr = dictionaryDeleteCheckService.checkDeleteDictionary(id, companyId);
            if (msgStr != null && msgStr.trim().length() > 0) {
                String msg_Str = MessageFormat.format(msgTemp_2, (i+1), msgStr);
                msgBuf.append(msg_Str);
            }

        }

        return  msgBuf.toString();
    }

    public String findDictionaryIdById(String id, Integer layer, String prefix) {
        if (id == null || id.trim().length() == 0) {return null;}
        if (prefix == null) {prefix = new String();}

        if (layer == null) {
            Dictionary object = this.findDictionaryById(id);
            if (object == null || object.getLayer() == null) {return null;}
            layer = object.getLayer();
        }

        prefix = prefix.trim();
        String queryStr = "";
        if (0 == layer.intValue()) {
            queryStr = prefix + "id_0 = '" + id + "'";
        } else if (1 == layer.intValue()) {
            queryStr = prefix + "id_1 = '" + id + "'";
        } else if (2 == layer.intValue()) {
            queryStr = prefix + "id_2 = '" + id + "'";
        } else if (3 == layer.intValue()) {
            queryStr = prefix + "id_3 = '" + id + "'";
        } else if (4 == layer.intValue()) {
            queryStr = prefix + "id_4 = '" + id + "'";
        } else if (5 == layer.intValue()) {
            queryStr = prefix + "id_5 = '" + id + "'";
        } else if (6 == layer.intValue()) {
            queryStr = prefix + "id_6 = '" + id + "'";
        }

        return queryStr;
    }

    @Override
    public ResultModel addDictionary(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Dictionary dictionary = (Dictionary) HttpUtils.pageData2Entity(pd, new Dictionary());

        String sessionID = pd.getString("sessionID");
        User user = RedisUtils.getUserInfoBySessionID(redisClient,sessionID);
        if(user == null){
            user = userService.selectById(pd.getString("currentUserId"));
        }

        //默认pid:= 字典表根节点
        String pid = Common.DICTIONARY_MAP.get("root");
        if (dictionary.getPid() != null && dictionary.getPid().trim().length() > 0) {
            pid = dictionary.getPid().trim();
        }

        //pid 获取父节点对象<Department>
        Dictionary paterObj = dictionaryService.findDictionaryById(pid);
        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ pid + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (字典名称)在同一层名称不可重复
        if (dictionaryService.isExistByName(pid, null, dictionary.getName(), pd.getString("currentCompanyId"))) {
            String msgTemp = "上级字典名称: {0}{2}字典名称: {1}{2}在系统中已经重复！{2}";
            String str_isnull = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    dictionary.getName(),
                    Common.SYS_ENDLINE_DEFAULT);
            model.putCode(Integer.valueOf(1));
            model.putMsg(str_isnull);
            return model;
        }

        //3. 创建字典信息
        String id = Conv.createUuid();
        dictionary.setId(id);
        dictionary.setCompanyId(user.getCompanyId());

        //userType_admin:超级管理员 userType_company:企业管理员 userType_employee:普通用户 userType_outer:外部用户)
        //isglobal: 0：否  1：是
        if(Common.DICTIONARY_MAP.get("userType_admin").equals(user.getUserType())){
            dictionary.setIsglobal("1");//超级管理员创建的数据字典都是全局设置
        } else {
            dictionary.setIsglobal("0");//非全局设置
        }

        dictionary = dictionaryService.id2DictionaryByLayer(id,
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                dictionary);
        dictionary = dictionaryService.paterObject2ObjectDB(paterObj, dictionary);

        //设置默认部门顺序
        if (dictionary.getSerialNumber() == null) {
            Integer maxCount = dictionaryService.findMaxSerialNumber(dictionary.getPid());
            dictionary.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }
        dictionaryService.save(dictionary);
        return model;
    }

    @Override
    public ResultModel updateDictionary(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        Dictionary dictionary = (Dictionary)HttpUtils.pageData2Entity(pd, new Dictionary());

        dictionary.setRemark("");
        String remark = pd.getString("remark");
        if (remark != null && remark.trim().length() > 0) {
            dictionary.setRemark(remark);
        }

        //pid 获取父节点对象<Department>
        Dictionary paterObj = dictionaryService.findDictionaryById(dictionary.getPid());
        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ dictionary.getPid() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (字典名称)在同一层名称不可重复
        if (dictionaryService.isExistByName(dictionary.getPid(), dictionary.getId(), dictionary.getName(),pd.getString("currentCompanyId"))) {
            String msgTemp = "上级字典名称: {0}{2}字典名称: {1}{2}在系统中已经重复！{2}";
            String str_isnull = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    dictionary.getName(),
                    Common.SYS_ENDLINE_DEFAULT);
            model.putCode(Integer.valueOf(1));
            model.putMsg(str_isnull);
            return model;
        }

        //3. 修改部门信息
        Dictionary dictionaryDB = dictionaryService.findDictionaryById(dictionary.getId());
        dictionaryDB = dictionaryService.object2objectDB(dictionary, dictionaryDB);
        dictionaryDB = dictionaryService.clearDictionaryByPath(dictionaryDB);
        dictionaryDB = dictionaryService.id2DictionaryByLayer(dictionaryDB.getId(),
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                dictionaryDB);
        dictionaryDB = dictionaryService.paterObject2ObjectDB(paterObj, dictionaryDB);

//        //设置默认部门顺序
//        if (dictionary.getSerialNumber() == null) {
//            Integer maxCount = dictionaryService.findMaxSerialNumber(dictionary.getPid());
//            dictionaryDB.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
//        }
        dictionaryDB.setUuser((String)pd.get("uuser"));
        dictionaryService.update(dictionaryDB);
        return model;
    }

    @Override
    public ResultModel updateDisableDictionary(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String companyId = pageData.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("企业id为空或空字符串");
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

        //2. 当前组织节点下是否含有(子节点-岗位)
        msgStr = dictionaryService.checkDeleteDictionaryByIds(id, companyId);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //3. 修改组织架构(禁用)状态
        Dictionary objectDB = dictionaryService.findDictionaryById(id);
        objectDB.setIsdisable(isdisable);
        objectDB.setUdate(new Date());
        objectDB.setUuser((String)pageData.get("uuser"));
        dictionaryService.update(objectDB);
        return model;
    }

    @Override
    public ResultModel deleteDictionarys(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String ids = pd.getString("ids");
        if(StringUtils.isEmpty(ids)){
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }

        String companyId = pd.getString("currentCompanyId");
        if (companyId == null || companyId.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("企业id为空或空字符串");
            return model;
        }

        String id_str = StringUtil.stringTrimSpace(ids);
        String msgStr = dictionaryService.checkDeleteDictionaryByIds(id_str, companyId);
        if (msgStr != null && msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        String[] id_arry = id_str.split(",");
        if(id_arry.length>0){
            dictionaryService.deleteByIds(id_arry);
        }
        return model;
    }

    @Override
    public ResultModel listPageDictionarys(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map result = new HashMap();
//        List<LinkedHashMap> titles = new ArrayList<LinkedHashMap>();
        List<Column> columnList = columnService.findColumnList("dictionary");
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
//                if(entry.getKey().indexOf("_hide")>0){
//                    titlesLinkedMap.put(entry.getKey().replace("_hide",""),entry.getValue());
//                    titlesHideList.add(entry.getKey().replace("_hide",""));
//                    varModelMap.put(entry.getKey().replace("_hide",""),"");
//                }else{
//                    titlesLinkedMap.put(entry.getKey(),entry.getValue());
//                    varModelMap.put(entry.getKey(),"");
//                }
//                titlesList.add(titlesLinkedMap);
            }
        }
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);

        List<Map> varMapList = new ArrayList();
        List<Map> varList = dictionaryService.getDataListPage(pd,pg);
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

    @Override
    public void exportExcelDictionarys(PageData pd) throws Exception {
        List<Column> columnList = columnService.findColumnList("dictionary");
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

        Pagination pg = HttpUtils.parsePagination(pd);
        pg.setSize(100000);
        List<Map> dataList = dictionaryService.getDataListPage(pd, pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);

        HttpServletResponse response  = HttpUtils.currentResponse();
        //查询数据-Excel文件导出
        //String fileName = "Excel数据字典数据导出";
        String fileName = "ExcelDictionary";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel treeDictionarys(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        //获取全部字典树-查询条件
        pd.put("isdisable", "1");
        pd.put("queryStr", " and company_id = '"+pd.get("currentCompanyId")+"' or  isopen = '1'");

        //获取指定节点及该节点下子节点-字典树-查询条件
        String pid = null;
        String dictionaryKey = pd.getString("dictionaryKey");
        String cascade = pd.getString("cascade");
        if (dictionaryKey != null && dictionaryKey.trim().length() > 0 && Common.DICTIONARY_MAP.get(dictionaryKey) != null) {
            pid = Common.DICTIONARY_MAP.get(dictionaryKey).trim();
            if("true".equals(cascade)){
                pd.put("id1", pid);
                pd.put("queryStr", null);
            }else {
                pd.put("pid", pid);
                pd.put("selfQueryStr", "id = '" + pid + "'");
                pd.put("queryStr", null);
            }
        }

        List<TreeEntity> treeList = dictionaryService.getTreeList(pd);
        TreeEntity treeObj = TreeUtil.switchTree(pid, treeList);
//        String treeJsonStr = YvanUtil.toJson(treeObj);
//        System.out.println("treeJsonStr: " + treeJsonStr);

        Map result = new HashMap();
        result.put("treeList", treeObj);
        result.put("options", treeObj.getChildren());
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel getDictionarys(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String dictionaryKey = pd.getString("dictionaryKey");
        String id = Common.DICTIONARY_MAP.get(dictionaryKey);

        String isglobal = pd.getString("isglobal");
        String queryStr = pd.getString("queryStr");

        pd.put("isdisable", "1");
        //是否只显示当前层
        String isNeetOneLayer = null;
        if (pd.getString("isNeetOneLayer") != null && pd.getString("isNeetOneLayer").trim().length() > 0) {
            isNeetOneLayer = pd.getString("isNeetOneLayer").trim();
        }

        if ("true".equals(isNeetOneLayer)) {
            String companyId = pd.getString("currentCompanyId");
            pd.put("companyId", companyId);
            pd.put("selfQueryStr", "id = '" + id + "'");
            pd.put("pid", id);
            if(StringUtils.isEmpty(isglobal) || "0".equals(isglobal)){
                //pd.put("queryStr", "  and company_id = '"+pd.get("currentCompanyId")+"'  and ( id = '"+id+"' or id_1 = '"+id+"'  )  " + queryStr);
                pd.put("isglobal", null);
            }else if("1".equals(isglobal)){
                //pd.put("queryStr", " and isglobal = '"+pd.get("isglobal")+"'  and ( id = '"+id+"' or id_1 = '"+id+"'  ) " + queryStr);
                pd.put("companyId", null);
            }

        } else if (!"true".equals(isNeetOneLayer)) {
            if(StringUtils.isEmpty(isglobal)||"0".equals(isglobal)){
                String companyId = pd.getString("currentCompanyId");
                pd.put("companyId", companyId);
                pd.put("queryStr", " and ( id = '"+id+"' or id_1 = '"+id+"'  )  " + queryStr);
            }else if("1".equals(isglobal)){
                pd.put("queryStr", "  and isglobal = '"+pd.get("isglobal")+"'  and ( id = '"+id+"' or id_1 = '"+id+"'  ) " + queryStr);
            }
            pd.put("selfQueryStr", "id = '" + id + "'");
        }

        List<TreeEntity> treeList = dictionaryService.getTreeList(pd);
        TreeEntity treeObj = TreeUtil.switchTree(id, treeList);

        dealWithTreeEntityChildren(treeObj);

        Map result = new HashMap();
        result.put("options", treeObj.getChildren());
        model.putResult(result);
        return model;
    }

    public void dealWithTreeEntityChildren(TreeEntity treeObj){
        if(treeObj!=null&&treeObj.getChildren()!=null&&treeObj.getChildren().size()>0){
            for(int i=0;i<treeObj.getChildren().size();i++){
                dealWithTreeEntityChildren(treeObj.getChildren().get(i));
            }
        } else {
            treeObj.setChildren(null);
        }
    }

    @Override
    public ResultModel dataListDictionarys(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String dictionaryKey = pd.getString("dictionaryKey");
        String pid = Common.DICTIONARY_MAP.get(dictionaryKey);
        String queryStr =  pd.getString("queryStr")!=null?pd.getString("queryStr"):"";
        pd.put("queryStr","(isdisable = '1' and pid = '"+pid+"' "+queryStr+" )");
        List<Map> dictionaryList = dictionaryService.findDataList(pd);
        model.putResult(dictionaryList);
        return model;
    }

    public List<Map<String, Object>> findDictionaryListByPathName(String companyId, String isNeedCompany, String id, Integer id_layer, Integer quert_layer) {
        PageData findMap = this.idAndLayer2QueryId(id, id_layer, null);

        if (companyId != null && companyId.trim().length() > 0) {
            findMap.put("companyId", companyId);
        }

        if ("true".equals(isNeedCompany)) {
            if (companyId == null || companyId.trim().length() == 0) {
                findMap.put("companyId", Common.DEPARTMENT_ROOT_ID);
            }
        }

        if (quert_layer != null) {
            findMap.put("pathLayer", quert_layer.toString());
        }
        findMap.put("orderStr", "dic.layer asc");

        List<Map<String, Object>> mapList = null;
        try {
            mapList = dictionaryMapper.findDictionaryListByPathName(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mapList == null || mapList.size() == 0) {return null;}

        for (Map<String, Object> mapObject : mapList) {
            String pathId = new String();
            String pathName = new String();

            if (quert_layer == null) {
                String id0 = (String)mapObject.get("id0");
                if (id0 != null && id0.trim().length() > 0) {
                    pathId = pathId + id0 + "-";
                }
                String dname0 = (String)mapObject.get("dname0");
                if (dname0 != null && dname0.trim().length() > 0) {
                    pathName = pathName + dname0 + "-";
                }

                String id1 = (String)mapObject.get("id1");
                if (id1 != null && id1.trim().length() > 0) {
                    pathId = pathId + id1 + "-";
                }
                String dname1 = (String)mapObject.get("dname1");
                if (dname1 != null && dname1.trim().length() > 0) {
                    pathName = pathName + dname1 + "-";
                }

                String id2 = (String)mapObject.get("id2");
                if (id2 != null && id2.trim().length() > 0) {
                    pathId = pathId + id2 + "-";
                }
                String dname2 = (String)mapObject.get("dname2");
                if (dname2 != null && dname2.trim().length() > 0) {
                    pathName = pathName + dname2 + "-";
                }

                String id3 = (String)mapObject.get("id3");
                if (id3 != null && id3.trim().length() > 0) {
                    pathId = pathId + id3 + "-";
                }
                String dname3 = (String)mapObject.get("dname3");
                if (dname3 != null && dname3.trim().length() > 0) {
                    pathName = pathName + dname3 + "-";
                }

                String id4 = (String)mapObject.get("id4");
                if (id4 != null && id4.trim().length() > 0) {
                    pathId = pathId + id4 + "-";
                }
                String dname4 = (String)mapObject.get("dname4");
                if (dname4 != null && dname4.trim().length() > 0) {
                    pathName = pathName + dname4 + "-";
                }

                String id5 = (String)mapObject.get("id5");
                if (id5 != null && id5.trim().length() > 0) {
                    pathId = pathId + id5 + "-";
                }
                String dname5 = (String)mapObject.get("dname5");
                if (dname5 != null && dname5.trim().length() > 0) {
                    pathName = pathName + dname5 + "-";
                }

                String id6 = (String)mapObject.get("id6");
                if (id6 != null && id6.trim().length() > 0) {
                    pathId = pathId + id6 + "-";
                }
                String dname6 = (String)mapObject.get("dname6");
                if (dname6 != null && dname6.trim().length() > 0) {
                    pathName = pathName + dname6 + "-";
                }
            } else if (quert_layer != null) {
                if (0 >= quert_layer.intValue()) {
                    String id0 = (String)mapObject.get("id0");
                    if (id0 != null && id0.trim().length() > 0) {
                        pathId = pathId + id0 + "-";
                    }
                    String dname0 = (String)mapObject.get("dname0");
                    if (dname0 != null && dname0.trim().length() > 0) {
                        pathName = pathName + dname0 + "-";
                    }
                }

                if (1 >= quert_layer.intValue()) {
                    String id1 = (String)mapObject.get("id1");
                    if (id1 != null && id1.trim().length() > 0) {
                        pathId = pathId + id1 + "-";
                    }
                    String dname1 = (String)mapObject.get("dname1");
                    if (dname1 != null && dname1.trim().length() > 0) {
                        pathName = pathName + dname1 + "-";
                    }
                }

                if (2 >= quert_layer.intValue()) {
                    String id2 = (String)mapObject.get("id2");
                    if (id2 != null && id2.trim().length() > 0) {
                        pathId = pathId + id2 + "-";
                    }
                    String dname2 = (String)mapObject.get("dname2");
                    if (dname2 != null && dname2.trim().length() > 0) {
                        pathName = pathName + dname2 + "-";
                    }
                }

                if (3 >= quert_layer.intValue()) {
                    String id3 = (String)mapObject.get("id3");
                    if (id3 != null && id3.trim().length() > 0) {
                        pathId = pathId + id3 + "-";
                    }
                    String dname3 = (String)mapObject.get("dname3");
                    if (dname3 != null && dname3.trim().length() > 0) {
                        pathName = pathName + dname3 + "-";
                    }
                }

                if (4 >= quert_layer.intValue()) {
                    String id4 = (String)mapObject.get("id4");
                    if (id4 != null && id4.trim().length() > 0) {
                        pathId = pathId + id4 + "-";
                    }
                    String dname4 = (String)mapObject.get("dname4");
                    if (dname4 != null && dname4.trim().length() > 0) {
                        pathName = pathName + dname4 + "-";
                    }
                }

                if (5 >= quert_layer.intValue()) {
                    String id5 = (String)mapObject.get("id5");
                    if (id5 != null && id5.trim().length() > 0) {
                        pathId = pathId + id5 + "-";
                    }
                    String dname5 = (String)mapObject.get("dname5");
                    if (dname5 != null && dname5.trim().length() > 0) {
                        pathName = pathName + dname5 + "-";
                    }
                }

                if (6 >= quert_layer.intValue()) {
                    String id6 = (String)mapObject.get("id6");
                    if (id6 != null && id6.trim().length() > 0) {
                        pathId = pathId + id6 + "-";
                    }
                    String dname6 = (String)mapObject.get("dname6");
                    if (dname6 != null && dname6.trim().length() > 0) {
                        pathName = pathName + dname6 + "-";
                    }
                }
            }

            //去掉最后一个'-'
            if (pathId.lastIndexOf("-") != -1) {
                pathId = pathId.substring(0, pathId.lastIndexOf("-"));
            }
            mapObject.put("pathId", pathId);

            //去掉最后一个'-'
            if (pathName.lastIndexOf("-") != -1) {
                pathName = pathName.substring(0, pathName.lastIndexOf("-"));
            }
            mapObject.put("pathName", pathName);
        }

        return mapList;
    }

    private PageData idAndLayer2QueryId(String id, Integer layer, PageData mapObject) {
        if (mapObject == null) {mapObject = new PageData();}
        if (id == null || id.trim().length() == 0) {return mapObject;}
        if (layer == null) {return mapObject;}

        if (0 == layer.intValue()) {
            mapObject.put("id0", id);
        } else if (1 == layer.intValue()) {
            mapObject.put("id1", id);
        } else if (2 == layer.intValue()) {
            mapObject.put("id2", id);
        } else if (3 == layer.intValue()) {
            mapObject.put("id3", id);
        } else if (4 == layer.intValue()) {
            mapObject.put("id4", id);
        } else if (5 == layer.intValue()) {
            mapObject.put("id5", id);
        } else if (6 == layer.intValue()) {
            mapObject.put("id6", id);
        }

        return mapObject;
    }
}



