package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.common.util.TreeUtil;
import com.xy.vmes.entity.Menu;
import com.xy.vmes.entity.TreeEntity;
import com.xy.vmes.deecoop.system.service.MenuTreeService;
import com.xy.vmes.deecoop.system.service.MenuService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.xy.vmes.common.util.StringUtil;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuTreeServiceImp implements MenuTreeService {
    @Autowired
    private MenuService menuService;

    //最大部门级别-系统最大支持6层-部门级别[0-5](0:默认层)
    private Integer maxLayer;
    //递归计数器-递归执行次数
    private Integer count;
    //当前递归执行所在层
    private Integer execute_layer;

    private List<Menu> list_0;
    private List<Menu> list_1;
    private List<Menu> list_2;
    private List<Menu> list_3;
    private List<Menu> list_4;
    private List<Menu> list_5;

    /**
     * 初始化方法
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void initialization() {
        this.maxLayer = Integer.valueOf(6);
        this.count = Integer.valueOf(0);
        this.execute_layer = Integer.valueOf(-1);

        list_0 = new ArrayList<Menu>();
        list_1 = new ArrayList<Menu>();
        list_2 = new ArrayList<Menu>();
        list_3 = new ArrayList<Menu>();
        list_4 = new ArrayList<Menu>();
        list_5 = new ArrayList<Menu>();
    }

    /**
     * 根据菜单对象<Menu>当前菜单节点下面所有节点生成树形结构
     * 查询次数: 从当前节点到最低层叶子节点-总共菜单层级数
     *
     * 1. 该方法为递归调用
     * 2. 递归执行次数: count := 0 获取当前节点--
     * 3. 根据(pids)获取下一层<Menu>List
     * 4. 递归结束条件(递归执行次数 > 6 or 查询无子节点)
     * 5. 递归调用结束后生成(list_0,list_1,...,list_5)结构体
     *
     * @param pids  (','分隔的字符串)
     * @return
     * @throws RestException
     *
     * 创建人：陈刚
     * 创建时间：2018-07-31
     *
     */
    public void findMenuTree(String pids) {
        if (pids == null || pids.trim().length() == 0) {
            throw new RestException("", "参数错误:部门pid为空或空字符串！");
        }

        //1. count := 0 获取当前节点<Menu>(vmes_Menu:系统部门表)对象
        Menu findObj = new Menu();
        if (count == 0) {
            try {
                //isdisable:是否禁用(0:已禁用 1:启用)
                findObj.setIsdisable("1");
                findObj.setId(pids);
                PageData pageData = HttpUtils.entity2PageData(findObj, new PageData());
                Menu deptObj = menuService.findMenu(pageData);
                if (deptObj == null) {
                    return;
                }
                if (deptObj.getLayer() == null || deptObj.getLayer() == -1) {
                    throw new RestException("", "参数错误:部门id:" + pids + "部门级别为空或等于-1，请与管理员联系！");
                }

                //放入List结构体中
                List<Menu> objectList = new ArrayList<Menu>();
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

        //2. 根据pids获取下一层<Menu>List
        pids = StringUtil.stringTrimSpace(pids);
        pids = "'" + pids.replace(",", "','") + "'";
        String pidQuery = "pid in (" + pids + ")";

        PageData pageData = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        pageData.put("isdisable", "1");
        pageData.put("queryStr", pidQuery);
        pageData.put("mapSize", Integer.valueOf(pageData.size()));
//
        List<Menu> childList = null;
        try {
            childList = menuService.findMenuList(pageData);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        //当前childList 放入List结构体中
        this.findLayerList(childList, this.execute_layer);
        //execute_layer递归执行所在层 +1
        this.execute_layer = Integer.valueOf(this.execute_layer.intValue() + 1);

        //执行次数+1
        this.count = Integer.valueOf(this.count.intValue() + 1);

        //子部门<Menu>List-生成id字符串(','分隔的字符串)
        String chid_ids = menuService.findMenuidByMenuList(childList);

        //递归结束条件: 递归执行次数 > 6 or 查询无子节点
        if (count > 6 || childList == null || childList.size() == 0) {
            return;
        } else {
            //递归调用: findDeptTree()
            this.findMenuTree(chid_ids);
        }
    }

    /**
     * 根据已知的菜单List<Menu>-生成树形结构
     * @param menuList
     * @param layer
     */
    public void findMenuTreeByList(List<Menu> menuList, Integer layer) {
        if (menuList == null || menuList.size() == 0) {return;}
        if (layer == null) {return;}

        //获得每一层的id字符串Map
        Map<String, String> mapObj = new LinkedHashMap<String, String>();
        for (Menu object : menuList) {
            if (object.getLayer().intValue() != layer.intValue()) {continue;}
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
            } else if (5 == layer.intValue()) {
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

        //查询部门表-获得每一层的id-部门集合List<Menu>
        PageData pageData = new PageData();
        //isdisable:是否禁用(0:已禁用 1:启用)
        pageData.put("isdisable", "1");
        pageData.put("queryStr", pidQuery);
        pageData.put("mapSize", Integer.valueOf(pageData.size()));

        List<Menu> objList = null;
        try {
            objList = menuService.findMenuList(pageData);
            //按照(Menu.serialNumber)部门排列序号-升序排序
            this.orderAcsBySerialNumber(objList);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        //当前objList 放入List结构体中
        this.findLayerList(objList, layer);

        //执行次数+1
        //this.count = Integer.valueOf(this.count.intValue() + 1);

        //递归结束条件(菜单级别layer == 1)
        if (layer == 0) {
            return;
        } else {
            //递归调用: findMenuTreeByList()
            this.findMenuTreeByList(menuList, (layer - 1));
        }
    }

    /**
     * 递归调用-生成菜单树
     * 1. 从树的最低层向上生成树结构
     * 2. 菜单树默认根节点(pid:root,layer:0,name:智能云管家)
     * 3. 菜单级别(1-5)
     * 4. 递归结束条件(菜单级别:=1)-(layer == 1)
     *
     * @param layer     最大级别
     * @param childMap  当前层-子结构体
     * @param treeList  菜单树-结构体
     * @return
     */
    public List<TreeEntity> creatMenuTree(Integer layer, Map<String, List<TreeEntity>> childMap, List<TreeEntity> treeList) {
        if (treeList == null) {treeList = new ArrayList<TreeEntity>();}
        if (layer == null) {return treeList;}

        //1. 获取当前层-菜单List<Menu>
        List<Menu> menuList = this.findListByLayer(layer);

        //递归结束条件(菜单级别layer == 0)
        if (layer == 0) {
            return treeList;
        } else if (menuList == null || menuList.size() == 0) {
            //递归调用: creatMenuTree()
            return this.creatMenuTree(Integer.valueOf(layer.intValue()-1), childMap, treeList);
        } else {
            //递归当前层所有节点List<TreeEntity>
            List<TreeEntity> nodeList = menuList2TreeList(menuList, null);

            //获取childMap--Map<pid, List<TreeEntity>>
            if (childMap == null || childMap.size() == 0) {
                //获取当前菜单层 Map<pid, List<TreeEntity>>
                childMap = this.treeList2Map(nodeList, null);
            } else if (childMap != null && childMap.size() > 0) {
                //chileMap 挂接到当前层节点上
                for (TreeEntity node : nodeList) {
                    String pid = node.getId();
                    List<TreeEntity> childList = childMap.get(pid);
                    if (childList != null && childList.size() > 0) {
                        node.setChildren(childList);
                    }
                }

                //生成新的childMap
                if (childMap != null && childMap.size() > 0) {childMap.clear();}
                childMap = this.treeList2Map(nodeList, childMap);
            }

            //递归调用: creatMenuTree()
            treeList = nodeList;
            return this.creatMenuTree(Integer.valueOf(layer.intValue()-1), childMap, treeList);
        }
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-19
     */
    public TreeEntity menu2Tree(Menu menu, TreeEntity tree) {
        if (tree == null) {tree = new TreeEntity();}
        if (menu == null) {return tree;}

        //(必须)title当前节点名称
        if (menu.getName() != null && menu.getName().trim().length() > 0) {
            tree.setName(menu.getName().trim());
        }
        //(必须)path当前菜单资源路径
        if (menu.getUrl() != null && menu.getUrl().trim().length() > 0) {
            tree.setUrl(menu.getUrl().trim());
        }
        //(必须)是否禁用(0:已禁用 1:启用)
        if (menu.getIsdisable() != null && menu.getIsdisable().trim().length() > 0) {
            tree.setIsdisable(menu.getIsdisable().trim());
        }

        //id 当前节点ID
        if (menu.getId() != null && menu.getId().trim().length() > 0) {
            tree.setId(menu.getId().trim());
        }
        //pid当前节点父ID
        if (menu.getPid() != null && menu.getPid().trim().length() > 0) {
            tree.setPid(menu.getPid().trim());
        }
        //serialNumber菜单顺序
        if (menu.getSerialNumber() != null) {
            tree.setSerialNumber(menu.getSerialNumber());
        }
        //layer 当前节点-部门级别
        if (menu.getLayer() != null) {
            tree.setLayer(menu.getLayer());
        }
        //icon当前节点图标
        if (menu.getIcon() != null && menu.getIcon().trim().length() > 0) {
            tree.setIcon(menu.getIcon().trim());
        }

        return tree;
    }

    /**
     * 遍历当前菜单层List<TreeEntity>-获取pid节点Map<pid, List<Menu>
     * @param menuList  当前菜单层List<TreeEntity>
     * @param nodeMap   Map<pid, List<TreeEntity>
     * @return
     */
    public Map<String, List<TreeEntity>> treeList2Map(List<TreeEntity> menuList, Map<String, List<TreeEntity>> nodeMap) {
        if (nodeMap == null) {nodeMap = new HashMap<String, List<TreeEntity>>();}
        if (menuList == null || menuList.size() == 0) {return nodeMap;}

        //1. 遍历菜单层List<TreeEntity>-生成Map<pid, List<TreeEntity>
        for (TreeEntity node : menuList) {
            String pid = node.getPid();
            List<TreeEntity> nodeChildList = nodeMap.get(pid);
            if(nodeChildList == null) {
                nodeChildList = new ArrayList<TreeEntity>();
                nodeMap.put(pid, nodeChildList);
            }
            nodeChildList.add(node);
        }

        if (nodeMap.size() == 0) {return nodeMap;}
        //2. 遍历Map<pid, List<TreeEntity> - TreeEntity.serialNumber 升序排列
        for (Iterator iterator = nodeMap.keySet().iterator(); iterator.hasNext();) {
            String mapKey = (String) iterator.next();
            List<TreeEntity> objectList = nodeMap.get(mapKey);
            this.orderAcsTreeBySerialNumber(objectList);
        }

        return nodeMap;
    }

    /**
     * 菜单List<Menu>转换成-树结构体List<TreeEntity>
     * @param menuList  菜单List<Menu>
     * @param treeList  树结构体List<TreeEntity>
     * @return
     */
    public List<TreeEntity> menuList2TreeList(List<Menu> menuList, List<TreeEntity> treeList) {
        if (treeList == null) {treeList = new ArrayList<TreeEntity>();}
        if (menuList == null || menuList.size() == 0) {return treeList;}

        for (Menu object : menuList) {
            TreeEntity node = this.menu2Tree(object, new TreeEntity());
            treeList.add(node);
        }
        this.orderAcsTreeBySerialNumber(treeList);

        return treeList;
    }

    public List<Menu> getList_0() {
        return list_0;
    }
    public void setList_0(List<Menu> list_0) {
        this.list_0 = list_0;
    }
    public List<Menu> getList_1() {
        return list_1;
    }
    public void setList_1(List<Menu> list_1) {
        this.list_1 = list_1;
    }
    public List<Menu> getList_2() {
        return list_2;
    }
    public void setList_2(List<Menu> list_2) {
        this.list_2 = list_2;
    }
    public List<Menu> getList_3() {
        return list_3;
    }
    public void setList_3(List<Menu> list_3) {
        this.list_3 = list_3;
    }
    public List<Menu> getList_4() {
        return list_4;
    }
    public void setList_4(List<Menu> list_4) {
        this.list_4 = list_4;
    }
    public List<Menu> getList_5() {
        return list_5;
    }
    public void setList_5(List<Menu> list_5) {
        this.list_5 = list_5;
    }

    ///////////////////////////////////////////////////////////////////////////
    //重写排序方法: 按照(Menu.serialNumber)升序排序
    private void orderAcsBySerialNumber(List<Menu> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                Menu object_0 = (Menu)arg0;
                Menu object_1 = (Menu)arg1;
                return object_0.getSerialNumber().compareTo(object_1.getSerialNumber());
            }
        });
    }
    private void orderAcsTreeBySerialNumber(List<TreeEntity> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                TreeEntity object_0 = (TreeEntity)arg0;
                TreeEntity object_1 = (TreeEntity)arg1;
                return object_0.getSerialNumber().compareTo(object_1.getSerialNumber());
            }
        });
    }

    private void findLayerList(List<Menu> objectList, Integer execute_layer) {
        if (objectList == null || objectList.size() == 0) {return;}
        if (execute_layer == null || -1 == execute_layer.intValue()) {return;}

        if (0 == execute_layer.intValue()) {
            this.setList_0(objectList);
        } else if (1 == execute_layer.intValue()) {
            this.setList_1(objectList);
        } else if (2 == execute_layer.intValue()) {
            this.setList_2(objectList);
        } else if (3 == execute_layer.intValue()) {
            this.setList_3(objectList);
        } else if (4 == execute_layer.intValue()) {
            this.setList_4(objectList);
        } else if (5 == execute_layer.intValue()) {
            this.setList_5(objectList);
        }
    }

    private List<Menu> findListByLayer(Integer execute_layer) {
        List<Menu> objectList = new ArrayList<Menu>();
        if (execute_layer == null || -1 == execute_layer.intValue()) {return objectList;}

        if (0 == execute_layer.intValue() && this.getList_0() != null) {
            return this.getList_0();
        } else if (1 == execute_layer.intValue() && this.getList_1() != null) {
            return this.getList_1();
        } else if (2 == execute_layer.intValue() && this.getList_2() != null) {
            return this.getList_2();
        } else if (3 == execute_layer.intValue() && this.getList_3() != null) {
            return this.getList_3();
        } else if (4 == execute_layer.intValue() && this.getList_4() != null) {
            return this.getList_4();
        } else if (5 == execute_layer.intValue() && this.getList_5() != null) {
            return this.getList_5();
        }

        return new ArrayList<Menu>();
    }

    @Override
    public ResultModel menuTreeLoad() throws Exception {
        ResultModel model = new ResultModel();

        PageData findMap = new PageData();
        findMap.put("isQueryAll", "true");

        List<Menu> objectList = menuService.findMenuList(findMap);
        for (Menu menu : objectList) {
            TreeEntity tree = this.menu2Tree(menu, null);
        }
        List<TreeEntity> treeList = this.menuList2TreeList(objectList,null);

        TreeEntity treeObject = TreeUtil.switchTree(null, treeList);
//        System.out.println("treeJson: " + YvanUtil.toJson(treeObject));
        return model;
    }
}
