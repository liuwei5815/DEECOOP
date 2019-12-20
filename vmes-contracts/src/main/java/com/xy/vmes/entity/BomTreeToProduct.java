package com.xy.vmes.entity;

import java.math.BigDecimal;

/**
 * 说明：BomTree表关联货品表 实体类
 * BomTreeToProductMapper.findBomTreeProductList
 *
 * @author 陈刚
 * @date 2019-09-27
 */
public class BomTreeToProduct extends BomTree {
    //BomTree
    //当前节点序号
    private Integer index;
    //当前节点序号字符串
    private String indexCode;
    //根节点至当前节点序号字符串
    private String pathIndexCode;

    //ratio 用料比例
    //pathRatio 根节点至当前节点用料比例乘积
    private BigDecimal pathRatio;

    //货品表
    private String productCode;
    private String productName;
    private String productSpec;
    private String productGenre;
    private String productGenreName;
    private String productUnit;
    private String productUnitName;

    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductSpec() {
        return productSpec;
    }
    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }
    public String getProductGenre() {
        return productGenre;
    }
    public void setProductGenre(String productGenre) {
        this.productGenre = productGenre;
    }
    public String getProductGenreName() {
        return productGenreName;
    }
    public void setProductGenreName(String productGenreName) {
        this.productGenreName = productGenreName;
    }
    public String getProductUnit() {
        return productUnit;
    }
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }
    public String getProductUnitName() {
        return productUnitName;
    }
    public void setProductUnitName(String productUnitName) {
        this.productUnitName = productUnitName;
    }
    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
    public String getIndexCode() {
        return indexCode;
    }
    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }
    public String getPathIndexCode() {
        return pathIndexCode;
    }
    public void setPathIndexCode(String pathIndexCode) {
        this.pathIndexCode = pathIndexCode;
    }
    public BigDecimal getPathRatio() {
        return pathRatio;
    }
    public void setPathRatio(BigDecimal pathRatio) {
        this.pathRatio = pathRatio;
    }
}
