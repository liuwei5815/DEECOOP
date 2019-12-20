package com.xy.vmes.deecoop.base.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.base.dao.ProductPropertyMapper;
import com.xy.vmes.entity.ProductProperty;
import com.xy.vmes.deecoop.base.service.ProductPropertyService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.YvanUtil;
import com.yvan.platform.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：vmes_product_property:产品属性 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-09-21
*/
@Service
@Transactional(readOnly = false)
public class ProductPropertyServiceImp implements ProductPropertyService {

    @Autowired
    private ProductPropertyMapper productPropertyMapper;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void save(ProductProperty productProperty) throws Exception{
        productProperty.setId(Conv.createUuid());
        productProperty.setCdate(new Date());
        productProperty.setUdate(new Date());
        productPropertyMapper.insert(productProperty);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void update(ProductProperty productProperty) throws Exception{
        productProperty.setUdate(new Date());
        productPropertyMapper.updateById(productProperty);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void updateAll(ProductProperty productProperty) throws Exception{
        productProperty.setUdate(new Date());
        productPropertyMapper.updateAllColumnById(productProperty);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    //@Cacheable(cacheNames = "productProperty", key = "''+#id")
    public ProductProperty selectById(String id) throws Exception{
        return productPropertyMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void deleteById(String id) throws Exception{
        productPropertyMapper.deleteById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        productPropertyMapper.deleteByIds(ids);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public List<ProductProperty> dataList(PageData pd) throws Exception{
        return productPropertyMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        productPropertyMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public List<ProductProperty> selectByColumnMap(Map columnMap) throws Exception{
    List<ProductProperty> productPropertyList =  productPropertyMapper.selectByMap(columnMap);
        return productPropertyList;
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-09-21
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return productPropertyMapper.getDataListPage(pd,pg);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    public ProductProperty findProductPropertyById(String id) {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        return this.findProductProperty(findMap);
    }

    public ProductProperty findProductProperty(PageData object) {
        if (object == null) {return null;}

        List<ProductProperty> objectList = this.findProductPropertyList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }

    @Override
    public List<ProductProperty> findProductPropertyList(PageData object) {
        if (object == null) {return null;}

        List<ProductProperty> objectList = null;
        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public List<ProductProperty> findProductPropertyListByProdId(String prodId) {
        List<ProductProperty> objectList = new ArrayList<ProductProperty>();
        if (prodId == null || prodId.trim().length() == 0) {return objectList;}

        PageData findMap = new PageData();
        findMap.put("prodId", prodId);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        objectList = this.findProductPropertyList(findMap);

        return objectList;
    }

    @Override
    public void deleteProdPropertyByProdId(String prodId) throws Exception {
        if (prodId == null || prodId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("prod_id", prodId);

        this.deleteByColumnMap(mapObject);
    }


//    public ProductProperty map2ProdProperty(Map<String, Object> mapObj, ProductProperty object) {
//        if (object == null) {object = new ProductProperty();}
//        if (mapObj == null) {return object;}
//
//        //{name:属性名称,value:属性值，remark:备注}
//        if (mapObj.get("name") != null && mapObj.get("name").toString().trim().length() > 0) {
//            object.setName(mapObj.get("name").toString().trim());
//        }
//        if (mapObj.get("value") != null && mapObj.get("value").toString().trim().length() > 0) {
//            object.setValue(mapObj.get("value").toString().trim());
//        }
//        if (mapObj.get("remark") != null && mapObj.get("remark").toString().trim().length() > 0) {
//            object.setRemark(mapObj.get("remark").toString().trim());
//        }
//
//        return object;
//    }
//
//    @Override
//    public List<ProductProperty> mapList2ProductPropertyList(List<Map<String, Object>> mapList) {
//        ArrayList<ProductProperty> objectList = new ArrayList<ProductProperty>();
//        if (mapList == null || mapList.size() == 0) {return objectList;}
//
//        for (Map<String, Object> mapObject : mapList) {
//            ProductProperty object = this.map2ProdProperty(mapObject, null);
//            objectList.add(object);
//        }
//
//        return objectList;
//    }

    @Override
    public void addProductProperty(String cuser, String prodId, List<ProductProperty> objectList) {
        if (prodId == null || prodId.trim().length() == 0) {return;}
        if (objectList == null || objectList.size() == 0) {return;}

        for (ProductProperty object : objectList) {
            object.setProdId(prodId);
            object.setCuser(cuser);
            try {
                this.save(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //获取自定义属性字符串-'_'分隔的字符串
    public String findPropertyValue(List<ProductProperty> objectList) {
        StringBuffer strBuf = new StringBuffer();
        if (objectList == null || objectList.size() == 0) {return new String();}

        for (int i = 0; i < objectList.size(); i++) {
            ProductProperty object = objectList.get(i);

            if (object.getValue() != null && object.getValue().trim().length() > 0) {
                strBuf.append(object.getValue().trim());

                if ((i+1) < objectList.size()) {
                    strBuf.append("_");
                }
            }
        }

        return strBuf.toString();
    }

    public Map<String, String> prodProperty2Map(ProductProperty object, Map<String, String> mapObject) {
        if (mapObject == null) {mapObject = new LinkedHashMap<String, String>();}
        if (object == null) {return mapObject;}

        //name 属性名称
        String name = "";
        if (object.getName() != null && object.getName().trim().length() > 0) {
            name = object.getName().trim();
        }
        mapObject.put("name", name);

        //value 属性值
        String value = "";
        if (object.getValue() != null && object.getValue().trim().length() > 0) {
            value = object.getValue().trim();
        }
        mapObject.put("value", value);

        //remark 备注
        //private String ;

        return mapObject;
    }


    public String prodPropertyList2JsonString(List<ProductProperty> objectList) {
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

        if (objectList != null && objectList.size() > 0) {
            for (ProductProperty object : objectList) {
                Map<String, String> mapObject = this.prodProperty2Map(object, null);
                mapList.add(mapObject);
            }
        }

        return YvanUtil.toJson(mapList);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    public Map<String, String> findProdPropertyJsonByProductPropertyList(List<ProductProperty> objectList) {
        if (objectList == null || objectList.size() == 0) {return new HashMap<String, String>();}

        Map<String, List<ProductProperty>> mapObject = this.findProdPropertyMap(objectList);
        return this.findProdPropertyJson(mapObject);
    }

    public Map<String, String> findProdPropertyNameByProductPropertyList(List<ProductProperty> objectList) {
        Map<String, String> mapObje = new HashMap<String, String>();
        if (objectList == null || objectList.size() == 0) {return mapObje;}

        Map<String, List<ProductProperty>> mapObject = this.findProdPropertyMap(objectList);
        for (Iterator iterator = mapObject.keySet().iterator(); iterator.hasNext();) {
            String mapKey = (String)iterator.next();
            List<ProductProperty> prodPropertyList = mapObject.get(mapKey);

            String propertyNames = "";
            if (prodPropertyList != null && prodPropertyList.size() > 0) {
                for (ProductProperty object : objectList) {
                    if (object.getName() != null && object.getName().trim().length() > 0) {
                        propertyNames = propertyNames + object.getName().trim() + ",";
                    }
                }
            }

            mapObje.put(mapKey, propertyNames);
        }

        return mapObje;
    }

    public Map<String, List<ProductProperty>> findProdPropertyMap(List<ProductProperty> objectList) {
        Map<String, List<ProductProperty>> mapObject = new HashMap<String, List<ProductProperty>>();

        if (objectList == null || objectList.size() == 0) {return mapObject;}
        for (ProductProperty object : objectList) {
            String prodId = object.getProdId();
            String mapKey = prodId;
            if (mapObject.get(mapKey) == null) {
                List<ProductProperty> prodPropertyList = new ArrayList<ProductProperty>();
                prodPropertyList.add(object);
                mapObject.put(mapKey, prodPropertyList);
            } else if (mapObject.get(mapKey) != null) {
                List<ProductProperty> prodPropertyList = mapObject.get(mapKey);
                prodPropertyList.add(object);
                mapObject.put(mapKey, prodPropertyList);
            }
        }

        return mapObject;
    }

    public Map<String, String> findProdPropertyJson(Map<String, List<ProductProperty>> mapObject) {
        Map<String, String> mapObj = new HashMap<String, String>();
        if (mapObject == null || mapObject.size() == 0) {return mapObj;}

        for (Iterator iterator = mapObject.keySet().iterator(); iterator.hasNext();) {
            String mapKey = (String)iterator.next();
            List<ProductProperty> prodPropertyList = mapObject.get(mapKey);
            if(prodPropertyList!=null&&prodPropertyList.size()>0){
                String jsonStr = this.prodPropertyList2JsonString(prodPropertyList);
                if (jsonStr != null && jsonStr.trim().length() > 0) {
                    mapObj.put(mapKey, jsonStr);
                }

                String showStr = "";
                for(int i=0;i<prodPropertyList.size();i++){
                    ProductProperty productProperty = prodPropertyList.get(i);
                    if(productProperty!=null){
                        if(showStr!=null&&showStr.length()>0){
                            showStr = showStr + ";" + productProperty.getName() + ":" + productProperty.getValue();
                        }else{
                            showStr = productProperty.getName() + ":" + productProperty.getValue();
                        }
                    }
                }
                if(showStr!=null&&showStr.trim().length()>0){
                    mapObj.put(mapKey+"_show", showStr);
                }
            }

        }

        return mapObj;
    }
}



