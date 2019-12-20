package com.xy.vmes.entity;


import java.math.BigDecimal;
import java.util.*;

/**
 * 说明：(组织架构,功能菜单,字典管理)-通用树形结构对象-与界面树形展示属性相关
 * 创建人：陈刚
 * 创建时间：2018-07-18
 */
public class TreeEntity implements Cloneable {
    //树形结构基本属性
    //当前节点ID
    private String id;
    //当前节点父ID
    private String pid;
    //(必须)当前节点名称
    private String name;
    //(必须)是否禁用(0:已禁用 1:启用)
    private String isdisable;
    //(必须)数据权限类型(0：显示个人数据  1：显示部门数据  2：显示所有数据（默认）)
    private String dataType;
    //当前节点级别
    private Integer layer;
    //节点顺序
    private Integer serialNumber = Integer.valueOf(0);

    //菜单树
    //当前节点编码
    private String url;
    //当前节点图标
    private String icon;
    //当前节点-是否绑定角色(true:绑定 false:未绑定)
    private Boolean isBindRole;
    //当前节点是否选中 (true:选中 false:未选中)
    private Boolean isChecked;

    //岗位树(属性)"dept" 部门 "post" 岗位
    private String type;
    private String deptName;
    private String postName;
    //设备名称 部门设备树
    private String eqptName;

    //数据字典属性
    private String label;
    private String value;

    //BomID
    private String bomId;
    private String bomTreeId;
    private String pathId;
    private BigDecimal ratio;
    private BigDecimal sumRatio;
    private BigDecimal splitCount;

    //产品信息
    private String code;
    private String spec;
    private String genre;
    private String unit;
    private String edate;
    private BigDecimal totalCount;
    private BigDecimal assembledCount;
    private BigDecimal stockCount;
    private BigDecimal maxCount;
    private BigDecimal expectCount;
    private BigDecimal lackCount;
    private BigDecimal planCount;
    private BigDecimal count;
    //子集表头信息
    private List<String> hideTitles;
    private List<LinkedHashMap> titles;

    private List<TreeEntity> lackList;

    //仓库树
    //virtual_genre:虚拟库-属性
    private String virtualGenre;
    //根节点到本节点路径ID pathId
    //根节点到本节点路径名称
    private String pathName;

    //(必须)当前节点子节点
    private List<TreeEntity> children = new ArrayList<TreeEntity>();

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPlanCount() {
        return planCount;
    }

    public void setPlanCount(BigDecimal planCount) {
        this.planCount = planCount;
    }

    public BigDecimal getSumRatio() {
        return sumRatio;
    }

    public void setSumRatio(BigDecimal sumRatio) {
        this.sumRatio = sumRatio;
    }

    public BigDecimal getSplitCount() {
        return splitCount;
    }

    public void setSplitCount(BigDecimal splitCount) {
        this.splitCount = splitCount;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public List<TreeEntity> getLackList() {
        return lackList;
    }

    public void setLackList(List<TreeEntity> lackList) {
        this.lackList = lackList;
    }

    public List<String> getHideTitles() {
        return hideTitles;
    }

    public void setHideTitles(List<String> hideTitles) {
        this.hideTitles = hideTitles;
    }

    public List<LinkedHashMap> getTitles() {
        return titles;
    }

    public void setTitles(List<LinkedHashMap> titles) {
        this.titles = titles;
    }

    public BigDecimal getLackCount() {
        return lackCount;
    }

    public void setLackCount(BigDecimal lackCount) {
        this.lackCount = lackCount;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getAssembledCount() {
        return assembledCount;
    }

    public void setAssembledCount(BigDecimal assembledCount) {
        this.assembledCount = assembledCount;
    }

    public BigDecimal getStockCount() {
        return stockCount;
    }

    public void setStockCount(BigDecimal stockCount) {
        this.stockCount = stockCount;
    }

    public BigDecimal getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(BigDecimal maxCount) {
        this.maxCount = maxCount;
    }

    public BigDecimal getExpectCount() {
        return expectCount;
    }

    public void setExpectCount(BigDecimal expectCount) {
        this.expectCount = expectCount;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public Integer getLayer() {
        return layer;
    }
    public void setLayer(Integer layer) {
        this.layer = layer;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public List<TreeEntity> getChildren() {
        return children;
    }
    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }
    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    public String getIsdisable() {
        return isdisable;
    }
    public void setIsdisable(String isdisable) {
        this.isdisable = isdisable;
    }
    public Boolean getIsBindRole() {
        return isBindRole;
    }
    public void setIsBindRole(Boolean isBindRole) {
        this.isBindRole = isBindRole;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getPostName() {
        return postName;
    }
    public void setPostName(String postName) {
        this.postName = postName;
    }
    public TreeEntity clone() {
        TreeEntity object = null;
        try{
            object = (TreeEntity)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return object;
    }
    public String getBomId() {
        return bomId;
    }
    public void setBomId(String bomId) {
        this.bomId = bomId;
    }
    public String getPathId() {
        return pathId;
    }
    public void setPathId(String pathId) {
        this.pathId = pathId;
    }
    public String getBomTreeId() {
        return bomTreeId;
    }
    public void setBomTreeId(String bomTreeId) {
        this.bomTreeId = bomTreeId;
    }
    public String getVirtualGenre() {
        return virtualGenre;
    }
    public void setVirtualGenre(String virtualGenre) {
        this.virtualGenre = virtualGenre;
    }
    public String getPathName() {
        return pathName;
    }
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }
    public Boolean getIsChecked() {
        return isChecked;
    }
    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getEqptName() {
        return eqptName;
    }

    public void setEqptName(String eqptName) {
        this.eqptName = eqptName;
    }
}
