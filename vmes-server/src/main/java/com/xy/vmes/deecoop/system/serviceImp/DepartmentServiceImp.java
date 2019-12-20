package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.deecoop.system.dao.DepartmentMapper;
import com.xy.vmes.deecoop.system.service.*;
import com.xy.vmes.entity.*;
import com.yvan.*;
import com.yvan.common.util.Common;
import com.yvan.platform.RestException;

import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.*;

/**
* 说明：vmes_department:系统部门表 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-07-23
*/
@Service
@Transactional(readOnly = false)
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private CoderuleService coderuleService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private DepartmentExcelService departmentExcelService;
    @Autowired
    private EmployPostService employPostService;

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public void save(Department department) throws Exception{
        department.setCdate(new Date());
        departmentMapper.insert(department);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public void update(Department department) throws Exception{
        department.setUdate(new Date());
        departmentMapper.updateById(department);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-23
     */
    @Override
    public void updateAll(Department department) throws Exception{
        department.setUdate(new Date());
        departmentMapper.updateAllColumnById(department);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
//    @Cacheable(cacheNames = "department", key = "''+#id")
    public Department selectById(String id) throws Exception{
        return departmentMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public void deleteById(String id) throws Exception{
        departmentMapper.deleteById(id);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public List<Department> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return departmentMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public List<Department> dataList(PageData pd) throws Exception{
        return departmentMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return departmentMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-23
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return departmentMapper.findDataList(pd);
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    private Map<String, String> deptKeyNameMap;
    private Map<String, String> deptNameKeyMap;

    public Map<String, String> getDeptKeyNameMap() {
        return deptKeyNameMap;
    }
    public Map<String, String> getDeptNameKeyMap() {
        return deptNameKeyMap;
    }
    public void createDeptMap() {
        this.deptKeyNameMap = new HashMap<String, String>();
        this.deptNameKeyMap = new HashMap<String, String>();
    }
    public void implementDeptMapByParentID(String parentId) {
        this.createDeptMap();
        if (parentId == null || parentId.trim().length() == 0) {return;}

        List<Department> deptList = this.findDepartmentListByPid(parentId);
        if (deptList == null || deptList.size() == 0) {return;}
        for (Department object : deptList) {
            String deptID = object.getId();
            String deptName = object.getName();
            if (deptName != null && deptName.trim().length() > 0) {
                this.deptKeyNameMap.put(deptID, deptName);
                this.deptNameKeyMap.put(deptName, deptID);
            }
        }
    }

    public void implementDeptMapByCompanyId(String companyId) {
        this.createDeptMap();
        if (companyId == null || companyId.trim().length() == 0) {return;}

        List<Map<String, Object>> mapList = this.findDepartmentListByDeptPathName(companyId);
        if (mapList == null || mapList.size() == 0) {return;}
        for (Map<String, Object> mapObject : mapList) {
            String deptId = (String)mapObject.get("id");
            String pathName = (String)mapObject.get("pathName");
            if (pathName != null && pathName.trim().length() > 0) {
                this.deptKeyNameMap.put(deptId, pathName);
                this.deptNameKeyMap.put(pathName, deptId);
            }
        }
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-08-01
     */
    @Override
    public List<TreeEntity>  getTreeList(PageData pd)throws Exception{
        return  departmentMapper.getTreeList(pd);
    }



    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-23
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        departmentMapper.deleteByIds(ids);
    }

    @Autowired
    private DepartmentTreeService deptTreeService;

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return departmentMapper.getColumnList();
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-08
     */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return departmentMapper.getDataListPage(pd, pg);
    }

    /**
     * 批量修改组织架构信息为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-07-27
     */
    public void updateDisableByIds(String[] ids) throws Exception {
        departmentMapper.updateDisableByIds(ids);
    }

    /**
     * 生成部门编码
     *
     * 创建人：陈刚
     * 创建时间：2018-07-27
     *
     * @param companyID  公司ID-组织架构ID
     * @return
     */
    public String createCoder(String companyID) {
        //(企业编号+前缀字符+日期字符+流水号)-(company+prefix+date+code)
        //(无需+前缀字符+无需+流水号)-W000142
        CoderuleEntity object = new CoderuleEntity();
        //tableName 业务名称(表名)
        object.setTableName("vmes_department");
        //companyID 公司ID
        object.setCompanyID(companyID);
        //length 指定位数(5)
        object.setLength(Common.CODE_RULE_LENGTH_DEFAULT);
        //firstName 第一个编码名称
        object.setFirstName("prefix");

        //separator 分隔符
        //object.setSeparator("-");
        //filling 填充字符(0)
        object.setFilling(Common.CODE_RULE_DEFAULT_FILLING);

        //isNeedPrefix 是否需要前缀
        object.setIsNeedPrefix(Boolean.TRUE);
        //prefix 前缀字符
        object.setPrefix("D");

        return coderuleService.findCoderule(object);
    }

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

        if (object.getPid() == null || object.getPid().trim().length() == 0) {
            msgBuf.append("pid为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "组织名称");
            msgBuf.append(str_isnull);
        }
//        if (object.getSerialNumber() == null) {
//            String str_isnull = MessageFormat.format(column_isnull, "显示顺序");
//            msgBuf.append(str_isnull);
//        }
        if (object.getDeptType() == null || object.getDeptType().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "组织类型");
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
        if (object.getPid() == null || object.getPid().trim().length() == 0) {
            msgBuf.append("pid为空或空字符串！");
            msgBuf.append(Common.SYS_ENDLINE_DEFAULT);
        }
        if (object.getName() == null || object.getName().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "部门名称");
            msgBuf.append(str_isnull);
        }
//        if (object.getSerialNumber() == null) {
//            String str_isnull = MessageFormat.format(column_isnull, "显示顺序");
//            msgBuf.append(str_isnull);
//        }
        if (object.getDeptType() == null || object.getDeptType().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "组织类型");
            msgBuf.append(str_isnull);
        }
        if (object.getIsdisable() == null || object.getIsdisable().trim().length() == 0) {
            String str_isnull = MessageFormat.format(column_isnull, "是否禁用");
            msgBuf.append(str_isnull);
        }

        return msgBuf.toString();
    }

    /**
     * 组织名称同一层级是否相同
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
        findMap.put("name", name);
        if (id != null && id.trim().length() > 0) {
            findMap.put("id", id);
            findMap.put("isSelfExist", "true");
        }
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = this.findDepartmentList(findMap);
        if (objectList != null && objectList.size() > 0) {return true;}

        return false;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-18
     */
    public Department findDepartment(PageData object) {
        if (object == null) {return null;}

        List<Department> objectList = null;
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
    public Department findDepartmentById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = this.findDepartmentList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }
    public Department findDepartmentByRoot() {
        PageData findMap = new PageData();
        findMap.put("pid", "root");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = this.findDepartmentList(findMap);
        if (objectList != null && objectList.size() > 0) {return objectList.get(0);}

        return null;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-18
     */
    public List<Department> findDepartmentList(PageData object) {
        if (object == null) {return null;}

        List<Department> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<Department> findDepartmentListByPid(String pid) {
        List<Department> objectList = new ArrayList<Department>();
        if (pid == null || pid.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        objectList = this.findDepartmentList(findMap);

        return objectList;
    }

    public Integer findMaxSerialNumber(String pid) {
        if (pid == null || pid.trim().length() == 0) {return Integer.valueOf(0);}

        PageData findMap = new PageData();
        findMap.put("pid", pid);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Department> objectList = null;
        try {
            objectList = this.findDepartmentList(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (objectList != null && objectList.size() > 0) {
            return Integer.valueOf(objectList.size());
        }

        return Integer.valueOf(0);
    }

    /**
     * 根据部门对象<Department>当前部门节点下面所有节点生成树形结构
     * 1. <Department>对象为空时-生成整棵树-(pid:=root)开始-部门级别(0-5)
     * 2. <Department>对象is not null
     * (允许为空)Department.id    当前部门id
     * (允许为空)Department.code  当前部门编码
     * (允许为空)Department.name  当前部门名称
     * (允许为空)Department.id1   当前部门(id_1)-公司id
     * (允许为空)Department.layer 当前部门级别
     * 3. (id,id1,code,name,layer) 不可同时为空
     * pid is not null pid对应的部门+pid下面所有子部门
     * pid is null (code,layer) is not null or (name,layer) is not null
     * <p>
     * <p>
     * <p>
     * 创建人：陈刚
     * 创建时间：2018-07-18
     *
     * @param detp 允许为空-<Department>对象(vmes_department:系统部门表)
     * @return
     */
    public Tree<Department> findTree(Department detp) {
        Department findObj = null;

        if (detp == null) {
            findObj = new Department();
            findObj.setPid("root");
        } else if (detp != null) {
            //1. 参数非空判断
            if (detp.getId() != null && detp.getId().trim().length() > 0) {
                findObj = new Department();
                findObj.setId(detp.getId().trim());
            } else if (detp.getId1() != null && detp.getId1().trim().length() > 0
                    && detp.getCode() != null && detp.getCode().trim().length() > 0
                    && detp.getLayer() != null
                    ) {
                findObj = new Department();
                findObj.setId1(detp.getId1().trim());
                findObj.setCode(detp.getCode().trim());
                findObj.setLayer(detp.getLayer());
            } else if (detp.getId1() != null && detp.getId1().trim().length() > 0
                    && detp.getName() != null && detp.getName().trim().length() > 0
                    && detp.getLayer() != null
                    ) {
                findObj = new Department();
                findObj.setId1(detp.getId1().trim());
                findObj.setName(detp.getName().trim());
                findObj.setLayer(detp.getLayer());
            } else if (detp.getId1() != null && detp.getId1().trim().length() > 0
                    && detp.getCode() != null && detp.getCode().trim().length() > 0
                    && detp.getName() != null && detp.getName().trim().length() > 0
                    && detp.getLayer() != null
                    ) {
                findObj = new Department();
                findObj.setId1(detp.getId1().trim());
                findObj.setCode(detp.getCode().trim());
                findObj.setName(detp.getName().trim());
                findObj.setLayer(detp.getLayer());
            }
        }

        if (findObj == null) {
            throw new RestException("", "参数错误:Department(pid,id_1,code,name,layer) 参数为空或空字符串，请与管理员联系！");
        }

        //2. 根据参数查询(vmes_department:系统部门表)--获得返回树结构根节点
        //isdisable:是否禁用(0:已禁用 1:启用)
        findObj.setIsdisable("1");

        PageData pageData = HttpUtils.entity2PageData(findObj, new PageData());
        List<Department> objectList = this.findDepartmentList(pageData);
        if (objectList == null || objectList.size() == 0) {
            String msgStr = "参数错误:Department(pid,code,name,layer) 查询无数据，请与管理员联系！";
            throw new RestException("", msgStr);
        }
        Department root_obj = objectList.get(0);

        //3. 生成部门树
        deptTreeService.initialization();
        deptTreeService.findDeptTree(root_obj.getId());

        return null;
    }

    /**
     * 获取部门id字符串-(','分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-07-19
     *
     */
    public String findDeptidByDeptList(List<Department> objectList) {
        StringBuffer strBuf = new StringBuffer();
        if (objectList == null || objectList.size() == 0) {return strBuf.toString();}

        for (Department dept : objectList) {
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
     * 获取部门最大级别-遍历部门List<Department>
     *
     * 创建人：陈刚
     * 创建时间：2018-07-24
     * @param objectList
     * @return
     */
    public Integer findMaxLayerByDeptList(List<Department> objectList) {
        if (objectList == null || objectList.size() == 0) {return null;}
        int maxLayer = 0;

        for (Department object : objectList) {
            if (object.getLayer() != null && object.getLayer().intValue() > maxLayer) {
                maxLayer = object.getLayer().intValue();
            }
        }

        if (maxLayer > 0) {return Integer.valueOf(maxLayer);}
        return null;
    }

    /**
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     * @param objectList
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
    public Map<String, String> findLongNameCode(List<Department> objectList) {
        Map<String, String> objectMap = new HashMap<String, String>();
        if (objectList == null || objectList.size() == 0) {return objectMap;}

        //按照(Department.layer)升序排序
        this.orderAcsByLayer(objectList);

        StringBuffer LongNameBuf = new StringBuffer();
        StringBuffer LongCodeBuf = new StringBuffer();
        for (Department object : objectList) {
            if (object.getName() != null && object.getName().trim().length() > 0) {
                LongNameBuf.append(object.getName().trim());
                LongNameBuf.append("-");
            }
            if (object.getCode() != null && object.getCode().trim().length() > 0) {
                LongCodeBuf.append(object.getCode().trim());
                LongCodeBuf.append("-");
            }
        }

        String nameTemp = LongNameBuf.toString().trim();
        if (nameTemp.lastIndexOf("-") != -1) {
            nameTemp = nameTemp.substring(0, nameTemp.lastIndexOf("-"));
        }
        objectMap.put("LongName", nameTemp);

        String codeTemp = LongCodeBuf.toString().trim();
        if (codeTemp.lastIndexOf("-") != -1) {
            codeTemp = codeTemp.substring(0, codeTemp.lastIndexOf("-"));
        }
        objectMap.put("LongCode", codeTemp);

        return objectMap;
    }

    /**
     * 根据父节点<Department>对象-(id_0,id_1,...,id_5)
     * 查询获得组织架构路径List<Department>
     *
     * @param paterObject
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
    public List<Department> findPathListByPater(Department paterObject) {
        List<Department> objectList = new ArrayList<Department>();
        if (paterObject == null) {return objectList;}

        StringBuffer ids = new StringBuffer();
        for (int i = 0; i <= 5; i++) {
            if (i == 0 && paterObject.getId0() != null && paterObject.getId0().trim().length() > 0) {
                ids.append(paterObject.getId0().trim());
                ids.append(",");
            } else if (i == 1 && paterObject.getId1() != null && paterObject.getId1().trim().length() > 0) {
                ids.append(paterObject.getId1().trim());
                ids.append(",");
            } else if (i == 2 && paterObject.getId2() != null && paterObject.getId2().trim().length() > 0) {
                ids.append(paterObject.getId2().trim());
                ids.append(",");
            } else if (i == 3 && paterObject.getId3() != null && paterObject.getId3().trim().length() > 0) {
                ids.append(paterObject.getId3().trim());
                ids.append(",");
            } else if (i == 4 && paterObject.getId4() != null && paterObject.getId4().trim().length() > 0) {
                ids.append(paterObject.getId4().trim());
                ids.append(",");
            } else if (i == 5 && paterObject.getId5() != null && paterObject.getId5().trim().length() > 0) {
                ids.append(paterObject.getId5().trim());
                ids.append(",");
            }
        }

        String idsTemp = ids.toString();
        //去掉最后一个','
        if (idsTemp.lastIndexOf(",") != -1) {
            idsTemp = idsTemp.substring(0, idsTemp.lastIndexOf(","));
        }
        String id_str = "'" + idsTemp.replace(",", "','") + "'";
        String pidQuery = "id in (" + id_str + ")";

        //查询部门表-获得每一层的id-部门集合List<Department>
        PageData pageData = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        pageData.put("isdisable", "1");
        pageData.put("queryStr", pidQuery);
        pageData.put("mapSize", Integer.valueOf(pageData.size()));

        return this.findDepartmentList(pageData);
    }

    /**
     * 根据父pid父节点<Department>对象-(id_0,id_1,...,id_5)-查询获得组织架构路径List<Department>
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     *
     * @param pid
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
    public Map<String, String> findLongNameCodeByPid(String pid) {
        if (pid == null || pid.trim().length() == 0) {return new HashMap<String, String>();}

        //根据父pid获取父节点<Department>对象
        Department paterObject = this.findDepartmentById(pid);
        //查询获得组织架构路径List<Department>
        List<Department> pathList = this.findPathListByPater(paterObject);

        return this.findLongNameCode(pathList);
    }

    /**
     * 根据父节点<Department>对象-(id_0,id_1,...,id_5)-查询获得组织架构路径List<Department>
     * 遍历List<Department>-获取(长名称,长编码)- 通过'-'练级的字符串
     *
     * @param Pater
     * @return Map<key, String>
     *     "LongName"
     *     "LongCode"
     */
    public Map<String, String> findLongNameCodeByPater(Department Pater) {
        if (Pater == null) {return new HashMap<String, String>();}
        List<Department> pathList = this.findPathListByPater(Pater);
        return this.findLongNameCode(pathList);
    }

    public Department paterObject2ObjectDB(Department paterObject, Department objectDB) {
        if (objectDB == null) {objectDB = new Department();}
        if (paterObject == null) {return objectDB;}

        if (paterObject.getId() != null && paterObject.getId().trim().length() > 0) {
            objectDB.setPid(paterObject.getId().trim());
        }

        String paterLongName = "";
        if (paterObject.getLongName() != null && paterObject.getLongName().trim().length() > 0) {
            paterLongName = paterObject.getLongName().trim();
        }
        if (objectDB.getName() != null && objectDB.getName().trim().length() > 0) {
            objectDB.setLongName(paterLongName + "-" + objectDB.getName());
        }

        String paterLongCode = "";
        if (paterObject.getLongCode() != null && paterObject.getLongCode().trim().length() > 0) {
            paterLongCode = paterObject.getLongCode().trim();
        }
        if (objectDB.getCode() != null && objectDB.getCode().trim().length() > 0) {
            objectDB.setLongCode(paterLongCode + "-" + objectDB.getCode());
        }
        //设置部门级别
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

    public Department id2DepartmentByLayer(String id, Integer layer, Department objectDB) {
        if (objectDB == null) {objectDB = new Department();}
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

    public Department clearDepartmentByPath(Department objectDB) {
        if (objectDB == null) {objectDB = new Department();}

        objectDB.setId0(null);
        objectDB.setId1(null);
        objectDB.setId2(null);
        objectDB.setId3(null);
        objectDB.setId4(null);
        objectDB.setId5(null);

        objectDB.setLongCode(null);
        objectDB.setLongName(null);
        objectDB.setLayer(null);

        return objectDB;
    }

    public Department object2objectDB(Department object, Department objectDB) {
        if (objectDB == null) {objectDB = new Department();}
        if (object == null) {return objectDB;}

        objectDB.setPid(object.getPid());
        objectDB.setName(object.getName());
        objectDB.setDeptType(object.getDeptType());
        objectDB.setIsdisable(object.getIsdisable());

        if (object.getSerialNumber() != null) {
            objectDB.setSerialNumber(object.getSerialNumber());
        }
        return objectDB;
    }

    /**
     * check部门列表List<Department>是否允许删除
     * 当前组织节点下是否含有子节点
     * 当前节点下是否含有岗位
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     * @param ids
     * @return
     */
    public String checkDeleteDeptByIds(String ids) {
        if (ids == null || ids.trim().length() == 0) {return new String();}

        String msgTemp_1 = "第 {0} 行: 存在子企业或子部门不可禁用！" + Common.SYS_ENDLINE_DEFAULT;
        String msgTemp_2 = "第 {0} 行: 存在岗位不可禁用！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer msgBuf = new StringBuffer();
        String[] id_arry = ids.split(",");
        for (int i = 0; i < id_arry.length; i++) {
            String id = id_arry[i];
            List<Department> childList = this.findDepartmentListByPid(id);
            //1. 查询当前组织节点下是否含有子节点
            if (childList != null && childList.size() > 0) {
                String msg_Str = MessageFormat.format(msgTemp_1, (i+1));
                msgBuf.append(msg_Str);
            }

            //2. 当前节点下是否含有岗位
            List<Post> postList = postService.findPostListByDeptId(id);
            if (postList != null && postList.size() > 0) {
                String msg_Str = MessageFormat.format(msgTemp_2, (i+1));
                msgBuf.append(msg_Str);
            }
        }

        return  msgBuf.toString();
    }

    /**
     * 获取部门级联查询ID
     *
     * @param id     部门id
     * @param layer  部门级别
     * @param prefix 前缀
     * @return
     */
    public String findDeptidById(String id, Integer layer, String prefix) {
        if (id == null || id.trim().length() == 0) {return null;}
        if (prefix == null) {prefix = new String();}

        if (layer == null) {
            Department dept = this.findDepartmentById(id);
            if (dept == null || dept.getLayer() == null) {return null;}
            layer = dept.getLayer();
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
        }

        return queryStr;
    }

    /**
     * 递归调用-添加(系统组织架构)vmes_department
     *   企业id  --> id_1
     *   一级部门 --> id_2
     *   二级部门 --> id_3
     *   三级部门 --> id_4
     *
     * @param cuser       创建人id
     * @param parent      父节点对象
     * @param excelEntity 组织类型
     * @param nameList    部门名称
     * @param count       递归执行次数
     */
    public String addBusinessByNameList(String cuser,
                                        Department parent,
                                        DeptExcelEntity excelEntity,
                                        List<String> nameList,
                                        int count) {
        //1. 获取部门名称-从一级部门开始(根节点)
        String nodeName = nameList.get(nameList.size() - count);
        if (nodeName == null || nodeName.trim().length() == 0) {return new String();}

        //2. 根据(pid:父节点ID)获取当前层所有节点
        this.implementDeptMapByParentID(parent.getId());
        Map<String, String> nameKeyMap = this.getDeptNameKeyMap();

        //获取当前节点<Department>对象
        String id = "";
        Department deptObj = new Department();
        if (nameKeyMap.get(nodeName.trim()) == null) {
            id = Conv.createUuid();
            deptObj.setId(id);
            //组织类型(1:公司 2:部门)
            deptObj.setOrganizeType("2");
            //获取部门编码
            String code = this.createCoder(parent.getId1());
            deptObj.setCode(code);
            deptObj.setName(nodeName);
            deptObj.setDeptType(excelEntity.getDeptType());
            deptObj.setCuser(cuser);
            deptObj = this.id2DepartmentByLayer(id,
                    Integer.valueOf(parent.getLayer().intValue() + 1),
                    deptObj);
            deptObj = this.paterObject2ObjectDB(parent, deptObj);

//            //获取(长名称,长编码)- 通过'-'连接的字符串
//            Map<String, String> longNameCodeMpa = this.findLongNameCodeByPater(parent);
//            if (longNameCodeMpa != null
//                    && longNameCodeMpa.get("LongName") != null
//                    && longNameCodeMpa.get("LongName").trim().length() > 0
//                    ) {
//                deptObj.setLongName(longNameCodeMpa.get("LongName").trim() + "-" + nodeName);
//            }
//            if (longNameCodeMpa != null
//                    && longNameCodeMpa.get("LongCode") != null
//                    && longNameCodeMpa.get("LongCode").trim().length() > 0
//                    ) {
//                deptObj.setLongCode(longNameCodeMpa.get("LongCode").trim() + "-" + deptObj.getCode());
//            }

            //设置默认部门顺序
            if (deptObj.getSerialNumber() == null) {
                Integer maxCount = this.findMaxSerialNumber(deptObj.getPid());
                deptObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
            }

            deptObj.setName(nodeName);
            if (excelEntity.getRemark() != null && excelEntity.getRemark().trim().length() > 0) {
                deptObj.setRemark(excelEntity.getRemark().trim());
            }

            try {
                //添加部门<Department>对象
                this.save(deptObj);

                //创建负责人(岗位)
                String companyId = deptObj.getId1();
                String code_1 = coderuleService.createCoder(companyId,"vmes_post","P");
                Post post_1 = new Post();
                post_1.setDeptId(deptObj.getId());
                post_1.setName("负责人");
                post_1.setCompanyId(companyId);
                post_1.setCode(code_1);
                post_1.setCuser(cuser);
                post_1.setRemark("负责人(岗位)-创建部门-系统自动创建");
                postService.save(post_1);

                //创建员工(岗位)
                String code_2 = coderuleService.createCoder(companyId,"vmes_post","P");
                Post post_2 = new Post();
                post_2.setDeptId(deptObj.getId());
                post_2.setName("员工");
                post_2.setCompanyId(companyId);
                post_2.setCode(code_2);
                post_2.setCuser(cuser);
                post_2.setRemark("员工(岗位)-创建部门-系统自动创建");
                postService.save(post_2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            id = nameKeyMap.get(nodeName.trim()).trim();
            deptObj = this.findDepartmentById(id);
        }

        if (0 == (count - 1)) {
            return id;
        } else {
            count = count - 1;
            return addBusinessByNameList(cuser, deptObj, excelEntity, nameList, count);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //重写排序方法: 按照(Department.serialNumber)升序排序
    private void orderAcsBySerialNumber(List<Department> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                Department object_0 = (Department)arg0;
                Department object_1 = (Department)arg1;
                return object_0.getSerialNumber().compareTo(object_1.getSerialNumber());
            }
        });
    }

    //重写排序方法: 按照(Department.layer)升序排序
    private void orderAcsByLayer(List<Department> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                Department object_0 = (Department)arg0;
                Department object_1 = (Department)arg1;
                return object_0.getLayer().compareTo(object_1.getSerialNumber());
            }
        });
    }

    @Override
    public ResultModel treeDepartments(PageData pd) throws Exception {
        ResultModel model = new ResultModel();

        String deptId = null;
        if (pd.get("currentCompanyId") != null && pd.get("currentCompanyId").toString().trim().length() > 0) {
            deptId = ((String)pd.get("currentCompanyId")).trim();
        }

        List<TreeEntity> treeList = this.getTreeList(pd);
        TreeEntity treeObj = TreeUtil.switchTree(deptId, treeList);
        String treeJsonStr = YvanUtil.toJson(treeObj);
        System.out.println("treeJsonStr: " + treeJsonStr);

        Map result = new HashMap();
        result.put("treeList", treeObj);
        model.putResult(result);
        return model;
    }

    @Override
    public ResultModel addDepartment(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        Department deptObj = (Department)HttpUtils.pageData2Entity(pageData, new Department());
        if (deptObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 组织对象Department 异常！");
            return model;
        }

        String msgStr = this.checkColumnByAdd(deptObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Department>
        Department paterObj = this.findDepartmentById(deptObj.getPid());
        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ deptObj.getPid() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (部门名称)在同一层名称不可重复
        if (this.isExistByName(deptObj.getPid(), null, deptObj.getName())) {
            String msgTemp = "上级部门名称: {0}{2}部门名称: {1}{2}在系统中已经重复！{2}";
            String str_isnull = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    deptObj.getName(),
                    Common.SYS_ENDLINE_DEFAULT);
            model.putCode(Integer.valueOf(1));
            model.putMsg(str_isnull);
            return model;
        }


        //3. 创建部门信息
        String id = Conv.createUuid();
        deptObj.setId(id);
        //组织类型(1:公司 2:部门)
        deptObj.setOrganizeType("2");
        deptObj.setCuser(pageData.getString("cuser"));

        //获取部门编码
        String companyID = pageData.getString("currentCompanyId");
        String code = this.createCoder(companyID);
        deptObj.setCode(code);

        deptObj = this.id2DepartmentByLayer(id,
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                deptObj);
        deptObj = this.paterObject2ObjectDB(paterObj, deptObj);


//        //获取(长名称,长编码)- 通过'-'连接的字符串
//        Map<String, String> longNameCodeMpa = departmentService.findLongNameCodeByPater(paterObj);
//        if (longNameCodeMpa != null
//            && longNameCodeMpa.get("LongName") != null
//            && longNameCodeMpa.get("LongName").trim().length() > 0
//        ) {
//            deptObj.setLongName(longNameCodeMpa.get("LongName").trim() + "-" + deptObj.getName());
//        }
//        if (longNameCodeMpa != null
//            && longNameCodeMpa.get("LongCode") != null
//            && longNameCodeMpa.get("LongCode").trim().length() > 0
//        ) {
//            deptObj.setLongCode(longNameCodeMpa.get("LongCode").trim() + "-" + deptObj.getCode());
//        }

        //设置默认部门顺序
        if (deptObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumber(deptObj.getPid());
            deptObj.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }
        this.save(deptObj);

        //4. 创建部门岗位信息
        String companyId = deptObj.getId1();
        //负责人(岗位)
        String code_1 = coderuleService.createCoder(companyId,"vmes_post","P");
        Post post_1 = new Post();
        post_1.setDeptId(deptObj.getId());
        post_1.setName("负责人");
        post_1.setCompanyId(companyId);
        post_1.setCode(code_1);
        post_1.setCuser(pageData.getString("cuser"));
        post_1.setRemark("负责人(岗位)-创建部门-系统自动创建");
        postService.save(post_1);

        //员工(岗位)
        String code_2 = coderuleService.createCoder(companyId,"vmes_post","P");
        Post post_2 = new Post();
        post_2.setDeptId(deptObj.getId());
        post_2.setName("员工");
        post_2.setCompanyId(companyId);
        post_2.setCode(code_2);
        post_2.setCuser(pageData.getString("cuser"));
        post_2.setRemark("员工(岗位)-创建部门-系统自动创建");
        postService.save(post_2);

        return model;
    }

    @Override
    public ResultModel updateDepartment(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        Department deptObj = (Department)HttpUtils.pageData2Entity(pageData, new Department());
        if (deptObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：Map 转 组织对象Department 异常！");
            return model;
        }

        String msgStr = this.checkColumnByEdit(deptObj);
        if (msgStr.trim().length() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgStr);
            return model;
        }

        //pid 获取父节点对象<Department>
        Department paterObj = this.findDepartmentById(deptObj.getPid());
        if (paterObj == null) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("(pid:"+ deptObj.getPid() + ")系统中无数据，请与管理员联系！");
            return model;
        }

        //2. (部门名称)在同一层名称不可重复
        if (this.isExistByName(deptObj.getPid(), deptObj.getId(), deptObj.getName())) {
            String msgTemp = "上级部门名称: {0}{2}部门名称: {1}{2}在系统中已经重复！{2}";
            msgTemp = MessageFormat.format(msgTemp,
                    paterObj.getName(),
                    deptObj.getName(),
                    Common.SYS_ENDLINE_DEFAULT);
            model.putCode(Integer.valueOf(1));
            model.putMsg(msgTemp);
            return model;
        }

        //3. 修改部门信息
        Department deptDB = this.findDepartmentById(deptObj.getId());
        deptDB = this.object2objectDB(deptObj, deptDB);
        deptDB = this.clearDepartmentByPath(deptDB);
        deptDB = this.id2DepartmentByLayer(deptDB.getId(),
                Integer.valueOf(paterObj.getLayer().intValue() + 1),
                deptDB);
        deptDB = this.paterObject2ObjectDB(paterObj, deptDB);

//        //获取(长名称,长编码)- 通过'-'连接的字符串
//        Map<String, String> longNameCodeMpa = departmentService.findLongNameCodeByPater(paterObj);
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongName") != null
//                && longNameCodeMpa.get("LongName").trim().length() > 0
//                ) {
//            deptDB.setLongName(longNameCodeMpa.get("LongName").trim() + "-" + deptDB.getName());
//        }
//        if (longNameCodeMpa != null
//                && longNameCodeMpa.get("LongCode") != null
//                && longNameCodeMpa.get("LongCode").trim().length() > 0
//                ) {
//            deptDB.setLongCode(longNameCodeMpa.get("LongCode").trim() + "-" + deptDB.getCode());
//        }

        //设置默认部门顺序
        if (deptObj.getSerialNumber() == null) {
            Integer maxCount = this.findMaxSerialNumber(deptObj.getPid());
            deptDB.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
        }

        deptDB.setRemark("");
        if (deptObj.getRemark() != null && deptObj.getRemark().trim().length() > 0) {
            deptDB.setRemark(deptObj.getRemark().trim());
        }

        this.update(deptDB);

        return model;
    }

    @Override
    public ResultModel updateDisableDept(PageData pageData) throws Exception {
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

//        //2. 当前组织节点下是否含有(子节点-岗位)
//        msgStr = this.checkDeleteDeptByIds(id);
//        if (msgStr != null && msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(msgStr);
//            return model;
//        }

        Department objectDB = this.findDepartmentById(id);
        PageData pd = new PageData();
        pd.put("pid",objectDB.getId());
        pd.put("isdisable","1");
        pd.put("isQueryAll","true");
        List<Department> deptList = this.dataList(pd);
        pd.put("deptId",objectDB.getId());
        List<Post> postList = postService.dataList(pd);

        if("0".equals(isdisable)){
            if(deptList!=null&&deptList.size()>0){
                model.putCode(Integer.valueOf(1));
                model.putMsg("当前部门下有未封存部门，请封存下级部门后再封存当前部门！");
                return model;
            }
            if(postList!=null&&postList.size()>0){
                for(int i=0;i<postList.size();i++){
                    Post post = postList.get(i);
                    pd.put("postId",post.getId());
                    List<EmployPost> employPostList = employPostService.dataList(pd);
                    if(employPostList!=null&&employPostList.size()>0){
                        model.putCode(Integer.valueOf(1));
                        model.putMsg("当前部门下有在职员工，不能封存当前部门！");
                        return model;
                    }
                }
            }
        }


        //3. 修改组织架构(禁用)状态
        objectDB.setIsdisable(isdisable);
        objectDB.setUdate(new Date());
        this.update(objectDB);

        if(postList!=null&&postList.size()>0){
            for(int i=0;i<postList.size();i++) {
                Post post = postList.get(i);
                post.setIsdisable(isdisable);
                postService.update(post);
            }
        }
        return model;
    }

    @Override
    public ResultModel deleteDepartments(PageData pageData) throws Exception {
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

//        //2. 当前组织节点下是否含有(子节点-岗位)
//        String msgStr = this.checkDeleteDeptByIds(id_str);
//        if (msgStr != null && msgStr.trim().length() > 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg(msgStr);
//            return model;
//        }
        ArrayList postIdList = new ArrayList();
        if(id_arry!=null&&id_arry.length>0){
            for(int i=0;i<id_arry.length;i++){
                String id = id_arry[i];
                Department objectDB = this.findDepartmentById(id);
                PageData pd = new PageData();
                pd.put("pid",objectDB.getId());
                pd.put("isdisable","1");
                pd.put("isQueryAll","true");
                List<Department> deptList = this.dataList(pd);
                pd.put("deptId",objectDB.getId());
                List<Post> postList = postService.dataList(pd);
                if(deptList!=null&&deptList.size()>0){
                    model.putCode(Integer.valueOf(1));
                    model.putMsg("选择的部门下有未封存部门，请封存下级部门后再封存当前部门！");
                    return model;
                }
                if(postList!=null&&postList.size()>0){
                    for(int j=0;j<postList.size();j++){
                        Post post = postList.get(j);
                        postIdList.add(post.getId());
                        pd.put("postId",post.getId());
                        List<EmployPost> employPostList = employPostService.dataList(pd);
                        if(employPostList!=null&&employPostList.size()>0){
                            model.putCode(Integer.valueOf(1));
                            model.putMsg("选择的部门下有在职员工，不能封存当前部门！");
                            return model;
                        }
                    }
                }
            }
        }

        this.updateDisableByIds(id_arry);
        if(postIdList!=null&&postIdList.size()>0){
            String[] postId_array = (String[])postIdList.toArray(new String[postIdList.size()]);
            if(postId_array!=null&&postId_array.length>0){
                postService.updateToDisableByIds(postId_array);
            }
        }
        return model;
    }

    //删除部门
    public ResultModel deleteDepartment(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        String id = (String)pageData.get("id");
        if (id == null || id.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("部门id为空或空字符串！");
            return model;
        }

        //判断该部门下是否含有子部门
        List<Department> deptList = this.findDepartmentListByPid(id);
        if (deptList != null && deptList.size() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("该部门含有子部门部门不允许删除，请删除所有子部门！");
            return model;
        }

        //判断该部门下是否含有用户
        PageData findMap = new PageData();
        findMap.put("deptId", id);
        //是否禁用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<User> userList = userService.findUserList(findMap);
        if (userList != null && userList.size() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("该部门含有用户，请在(系统-用户管理)删除该部门的全部用户！");
            return model;
        }

        //判断该部门下的员工
        findMap = new PageData();
        findMap.put("deptId", id);
        List<Map> employeeList = employeeService.getDataListPage(findMap);
        if (employeeList != null && employeeList.size() > 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("该部门含有员工，请在(人事-员工管理)删除该部门的全部员工！");
            return model;
        }

        //删除该部门
        this.deleteById(id);

        //删除部门岗位
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("dept_id", id);
        postService.deleteByColumnMap(columnMap);

        return model;
    }

    @Override
    public ResultModel listPageDepartments(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("department");
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

        //默认isdisable:=1
        pd.put("isdisable", Common.SYS_DEFAULT_ISDISABLE_1);
        String isdisableByQuery = pd.getString("isdisableByQuery");
        if (isdisableByQuery != null && isdisableByQuery.trim().length() > 0) {
            pd.put("isdisable", isdisableByQuery);
        }

        Map<String, Object> titleMap = ColumnUtil.findTitleMapByColumnList(columnList);
        List<Map> varMapList = new ArrayList();
        List<Map> varList = this.getDataListPage(pd,pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll((Map<String, String>)titleMap.get("varModel"));
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);
        result.put("pageData", pg);
        model.putResult(result);
        return model;
    }

    @Override
    public void exportExcelDepartments(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("department");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //根据查询条件获取业务数据List

        String ids = pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "a.id in (" + ids + ")";
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
            fileName = "ExcelDepartment";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel importExcelDepartments(MultipartFile file) throws Exception {
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

        //1. Excel文件数据dataMapLst -->(转换) ExcelEntity (属性为导入模板字段)
        List<DeptExcelEntity> excelList = departmentExcelService.mapList2ImportExcelList(dataMapLst, null);
        if (excelList == null || excelList.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("Excel导入文件为空，请填写需要导入的数据！");
            return model;
        }

        //2. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        HttpServletRequest httpRequest = HttpUtils.currentRequest();
        String companyId = (String)httpRequest.getParameter("companyId");
        String msgStr = departmentExcelService.checkColumnImportExcel(excelList,
                companyId,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            StringBuffer msgBuf = new StringBuffer();
            msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
            msgBuf.append(msgStr.trim());
            msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //3. Excel导入字段-名称唯一性判断-在Excel文件中
        msgStr = departmentExcelService.checkExistImportExcelBySelf(excelList, Integer.valueOf(3), Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            StringBuffer msgBuf = new StringBuffer();
            msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
            msgBuf.append(msgStr.trim());
            msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //4. Excel导入字段-名称唯一性判断-在业务表中判断
        msgStr = departmentExcelService.checkExistImportExcelByDatabase(
                excelList,
                companyId,
                Integer.valueOf(3),
                Common.SYS_IMPORTEXCEL_MESSAGE_MAXROW);
        if (msgStr != null && msgStr.trim().length() > 0) {
            StringBuffer msgBuf = new StringBuffer();
            msgBuf.append("Excel导入失败！" + Common.SYS_ENDLINE_DEFAULT);
            msgBuf.append(msgStr.trim());
            msgBuf.append("请核对后再次导入" + Common.SYS_ENDLINE_DEFAULT);

            model.putCode(Integer.valueOf(1));
            model.putMsg(msgBuf.toString());
            return model;
        }

        //5. List<ExcelEntity> --> (转换) List<业务表DB>对象
        //6. 遍历List<业务表DB> 对业务表添加或修改
        String userId = (String)httpRequest.getParameter("userId");
        departmentExcelService.addImportExcelByList(excelList,
                userId,
                companyId);

        //"Excel数据导入成功，共成功导入({0})条！"
        model.putMsg(MessageFormat.format("Excel数据导入成功，共成功导入({0})条！", excelList.size()));
        return model;
    }

    public List<Map<String, Object>> findDepartmentListByDeptPathName(String companyId) {
        if (companyId == null || companyId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //组织类型(1:公司 2:部门)
        findMap.put("organizeType", "2");
        findMap.put("orderStr", "dept.layer asc");

        List<Map<String, Object>> mapList = null;
        try {
            mapList = departmentMapper.findDepartmentListByPathName(findMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mapList == null || mapList.size() == 0) {return null;}

        for (Map<String, Object> mapObject : mapList) {
            String pathId = new String();
            String pathName = new String();

//            //默认-智能云管家
//            String did0 = (String)mapObject.get("did0");
//            if (did0 != null && did0.trim().length() > 0) {
//                pathId = pathId + did0 + "-";
//            }
//            String dname0 = (String)mapObject.get("dname0");
//            if (dname0 != null && dname0.trim().length() > 0) {
//                pathName = pathName + dname0 + "-";
//            }
//
//            //企业名称
//            String did1 = (String)mapObject.get("did1");
//            if (did1 != null && did1.trim().length() > 0) {
//                pathId = pathId + did1 + "-";
//            }
//            String dname1 = (String)mapObject.get("dname1");
//            if (dname1 != null && dname1.trim().length() > 0) {
//                pathName = pathName + dname1 + "-";
//            }

            String did2 = (String)mapObject.get("did2");
            if (did2 != null && did2.trim().length() > 0) {
                pathId = pathId + did2 + "-";
            }
            String dname2 = (String)mapObject.get("dname2");
            if (dname2 != null && dname2.trim().length() > 0) {
                pathName = pathName + dname2 + "-";
            }

            String did3 = (String)mapObject.get("did3");
            if (did3 != null && did3.trim().length() > 0) {
                pathId = pathId + did3 + "-";
            }
            String dname3 = (String)mapObject.get("dname3");
            if (dname3 != null && dname3.trim().length() > 0) {
                pathName = pathName + dname3 + "-";
            }

            String did4 = (String)mapObject.get("did4");
            if (did4 != null && did4.trim().length() > 0) {
                pathId = pathId + did4 + "-";
            }
            String dname4 = (String)mapObject.get("dname4");
            if (dname4 != null && dname4.trim().length() > 0) {
                pathName = pathName + dname4 + "-";
            }

            String did5 = (String)mapObject.get("did5");
            if (did5 != null && did5.trim().length() > 0) {
                pathId = pathId + did5 + "-";
            }
            String dname5 = (String)mapObject.get("dname5");
            if (dname5 != null && dname5.trim().length() > 0) {
                pathName = pathName + dname5 + "-";
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
}



