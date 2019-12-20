package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.entity.Department;
import com.xy.vmes.entity.TreeEntity;
import com.xy.vmes.deecoop.system.service.DepartmentService;
import com.xy.vmes.deecoop.system.service.DepartmentTreeService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.xy.vmes.common.util.StringUtil;
import com.yvan.platform.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 当前部门节点和该部门节点下所有子部门-的树形结构
 * 1. 查询(vmes_department:系统部门表)-查询次数(当前部门节点-最低层叶子部门节点)层数
 * 2. 每次递归查询pid-(通过','逗号分隔的字符串)-得到下一层部门节点List集合-存入对应的层的List结构体中
 * 3. 递归结束条件()
 *
 * 部门树结构生成
 * 创建人：陈刚
 * 创建时间：2018-07-19
 */
@Service
public class DepartmentTreeServiceImp implements DepartmentTreeService {

    @Autowired
    private DepartmentService departmentService;

    //最大部门级别-系统最大支持6层-部门级别[0-5](0:默认层)
    private Integer maxLayer;
    //递归计数器-递归执行次数
    private Integer count;
    //当前递归执行所在层
    private Integer execute_layer;

    private List<Department> list_0;
    private List<Department> list_1;
    private List<Department> list_2;
    private List<Department> list_3;
    private List<Department> list_4;
    private List<Department> list_5;

    /**
     * 初始化方法
     * 创建人：陈刚
     * 创建时间：2018-07-19
     */
    public void initialization() {
        this.maxLayer = Integer.valueOf(6);
        this.count = Integer.valueOf(0);
        this.execute_layer = Integer.valueOf(-1);

        list_0 = new ArrayList<Department>();
        list_1 = new ArrayList<Department>();
        list_2 = new ArrayList<Department>();
        list_3 = new ArrayList<Department>();
        list_4 = new ArrayList<Department>();
        list_5 = new ArrayList<Department>();
    }

    /**
     * 根据部门对象<Department>当前部门节点下面所有节点生成树形结构
     * 查询次数: 从当前节点到最低层叶子节点-总共部门层级数
     *
     * 1. 该方法为递归调用
     * 2. 递归执行次数: count := 0 获取当前节点--
     * 3. 根据(pids)获取下一层<Department>List
     * 4. 递归结束条件(递归执行次数 > 6 or 查询无子节点)
     * 5. 递归调用结束后生成(list_0,list_1,...,list_5)结构体
     *
     * @param pids  (','分隔的字符串)
     * @return
     * @throws RestException
     *
     * 创建人：陈刚
     * 创建时间：2018-07-19
     *
     */
    public void findDeptTree(String pids) {
        if (pids == null || pids.trim().length() == 0) {
            throw new RestException("", "参数错误:部门pid为空或空字符串！");
        }

        //1. count := 0 获取当前节点<Department>(vmes_department:系统部门表)对象
        Department findObj = new Department();
        if (count == 0) {
            try {
                //isdisable:是否禁用(1:已禁用 0:启用)
                findObj.setIsdisable("0");
                findObj.setId(pids);
                PageData pageData = HttpUtils.entity2PageData(findObj, new PageData());
                Department deptObj = departmentService.findDepartment(pageData);
                if (deptObj == null) {
                    return;
                }
                if (deptObj.getLayer() == null || deptObj.getLayer() == -1) {
                    throw new RestException("", "参数错误:部门id:" + pids + "部门级别为空或等于-1，请与管理员联系！");
                }

                //放入List结构体中
                List<Department> objectList = new ArrayList<Department>();
                objectList.add(deptObj);
                this.execute_layer = deptObj.getLayer();
                this.findLayerList(objectList, this.execute_layer);

                //execute_layer递归执行所在层 +1
                this.execute_layer = Integer.valueOf(this.execute_layer.intValue() + 1);
                //执行次数+1
                this.count = Integer.valueOf(this.count.intValue() + 1);

            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }
        }

        //2. 根据pids获取下一层<Department>List
        pids = StringUtil.stringTrimSpace(pids);
        pids = "'" + pids.replace(",", "','") + "'";
        String pidQuery = "pid in (" + pids + ")";

        PageData pageData = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        pageData.put("isdisable", "1");
        pageData.put("queryStr", pidQuery);
        pageData.put("mapSize", Integer.valueOf(pageData.size()));

        List<Department> childList = null;
        try {
            childList = departmentService.findDepartmentList(pageData);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        //当前childList 放入List结构体中
        this.findLayerList(childList, this.execute_layer);
        //execute_layer递归执行所在层 +1
        this.execute_layer = Integer.valueOf(this.execute_layer.intValue() + 1);

        //执行次数+1
        this.count = Integer.valueOf(this.count.intValue() + 1);

        //子部门<Department>List-生成id字符串(','分隔的字符串)
        String chid_ids = departmentService.findDeptidByDeptList(childList);

        //递归结束条件: 递归执行次数 > 6 or 查询无子节点
        if (count > 6 || childList == null || childList.size() == 0) {
            return;
        } else {
            //递归调用: findDeptTree()
            this.findDeptTree(chid_ids);
        }
    }

    /**
     * 根据已知的部门List<Department>-生成树形结构
     * 1. 该方法为递归调用
     * 2. 递归结束条件(部门级别layer == 0)
     * 3. 递归调用结束后生成(list_0,list_1,...,list_5)结构体
     *
     * @param deptList
     * @param layer
     */
    public void findDeptTreeByDeptList(List<Department> deptList, Integer layer) {
        if (deptList == null || deptList.size() == 0) {return;}
        if (layer == null) {return;}

        //获得每一层的id字符串Map
        Map<String, String> mapObj = new LinkedHashMap<String, String>();
        for (Department object : deptList) {
            if (layer.intValue() == 0) {
                String id = object.getId0();
                mapObj.put(id, id);
            } else if (layer.intValue() == 1) {
                String id = object.getId1();
                mapObj.put(id, id);
            } else if (layer.intValue() == 2) {
                String id = object.getId2();
                mapObj.put(id, id);
            } else if (layer.intValue() == 3) {
                String id = object.getId3();
                mapObj.put(id, id);
            } else if (layer.intValue() == 4) {
                String id = object.getId4();
                mapObj.put(id, id);
            } else if (layer.intValue() == 5) {
                String id = object.getId5();
                mapObj.put(id, id);
            }
        }

        //遍历Map获得id字符串(通过','逗号分隔的字符串)
        StringBuffer ids = new StringBuffer();
        for (Iterator iterator = mapObj.keySet().iterator(); iterator.hasNext();) {
            String mapKey = (String) iterator.next();
            ids.append(mapKey);
            ids.append(",");
        }
        String idsTemp = ids.toString();
        //去掉最后一个','
        if (idsTemp.lastIndexOf(",") != -1) {
            idsTemp = idsTemp.substring(0, idsTemp.lastIndexOf(","));
        }

        String id_str = StringUtil.stringTrimSpace(idsTemp);
        id_str = "'" + id_str.replace(",", "','") + "'";
        String pidQuery = "id in (" + id_str + ")";

        //查询部门表-获得每一层的id-部门集合List<Department>
        PageData pageData = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        pageData.put("isdisable", "1");
        pageData.put("queryStr", pidQuery);
        pageData.put("mapSize", Integer.valueOf(pageData.size()));

        List<Department> objList = null;
        try {
            objList = departmentService.findDepartmentList(pageData);
            //按照(Department.serialNumber)部门排列序号-升序排序
            this.orderAcsBySerialNumber(objList);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        //当前objList 放入List结构体中
        this.findLayerList(objList, layer);

        //执行次数+1
        this.count = Integer.valueOf(this.count.intValue() + 1);

        //递归结束条件(部门级别layer == 0)
        if (layer == 0) {
            return;
        } else {
            //递归调用: findDeptTreeByDeptList()
            this.findDeptTreeByDeptList(deptList, (layer - 1));
        }
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-19
     */
    public TreeEntity dept2Tree(Department dept, TreeEntity tree) {
        if (tree == null) {tree = new TreeEntity();}
        if (dept == null) {return tree;}

        //(必须)title当前节点名称
        if (dept.getName() != null && dept.getName().trim().length() > 0) {
            tree.setName(dept.getName().trim());
        }
        //(必须)path当前节点编码
        if (dept.getCode() != null && dept.getCode().trim().length() > 0) {
            tree.setUrl(dept.getCode().trim());
        }
        //id 当前节点ID
        if (dept.getId() != null && dept.getId().trim().length() > 0) {
            tree.setId(dept.getId().trim());
        }
        //pid当前节点父ID
        if (dept.getPid() != null && dept.getPid().trim().length() > 0) {
            tree.setPid(dept.getPid().trim());
        }
        //layer 当前节点-部门级别
        if (dept.getLayer() != null) {
            tree.setLayer(dept.getLayer());
        }
        //icon当前节点图标

        return tree;
    }


    public List<Department> getList_0() {
        return list_0;
    }

    public void setList_0(List<Department> list_0) {
        this.list_0 = list_0;
    }

    public List<Department> getList_1() {
        return list_1;
    }

    public void setList_1(List<Department> list_1) {
        this.list_1 = list_1;
    }

    public List<Department> getList_2() {
        return list_2;
    }

    public void setList_2(List<Department> list_2) {
        this.list_2 = list_2;
    }

    public List<Department> getList_3() {
        return list_3;
    }

    public void setList_3(List<Department> list_3) {
        this.list_3 = list_3;
    }

    public List<Department> getList_4() {
        return list_4;
    }

    public void setList_4(List<Department> list_4) {
        this.list_4 = list_4;
    }

    public List<Department> getList_5() {
        return list_5;
    }

    public void setList_5(List<Department> list_5) {
        this.list_5 = list_5;
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

    private void findLayerList(List<Department> objectList, Integer execute_layer) {
        if (objectList == null || objectList.size() == 0) {return;}
        if (execute_layer == null || -1 == execute_layer.intValue()) {return;}

        if (0 == execute_layer.intValue()) {this.setList_0(objectList);}
        if (1 == execute_layer.intValue()) {this.setList_1(objectList);}
        if (2 == execute_layer.intValue()) {this.setList_2(objectList);}
        if (3 == execute_layer.intValue()) {this.setList_3(objectList);}
        if (4 == execute_layer.intValue()) {this.setList_4(objectList);}
        if (5 == execute_layer.intValue()) {this.setList_5(objectList);}
    }
}
