package com.xy.vmes.deecoop.base.serviceImp;

import com.xy.vmes.deecoop.base.service.ProductService;
import com.xy.vmes.deecoop.base.service.ProductUnitExcelService;
import com.xy.vmes.deecoop.base.service.ProductUnitService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.xy.vmes.entity.Dictionary;
import com.xy.vmes.entity.Product;
import com.xy.vmes.entity.ProductUnit;
import com.yvan.Conv;
import com.yvan.PageData;
import com.yvan.common.util.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * 货品单价-Excel导入 Service实现类
 * 创建人：陈刚
 * 创建时间：2019-10-25
 */
@Service
@Transactional(readOnly = false)
public class ProductUnitExcelServiceServiceImp implements ProductUnitExcelService {
    @Autowired
    private ProductUnitService productUnitService;

    @Autowired
    private ProductService productService;
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;

    private Map<String, Map<String, String>> dictionaryMap = new HashMap<String, Map<String, String>>();

    /**
     * 导入字段非空判断-(客户单价)数字判断-(货品属性)字典表判断
     * 必填字段: 货品名称,规格型号,货品属性,单位,单价
     *
     * @param objectList
     * @param companyId
     * @param index
     * @param maxShowRow
     * @return
     */
    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                         String companyId,
                                         Integer index,
                                         Integer maxShowRow) {
        StringBuffer strBuf = new StringBuffer();

        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        this.dictionaryMap.clear();
        //获取 货品属性 字典Map productGenre
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productGenre") , null);
        this.dictionaryMap.put("genreKeyNameMap", dictionaryService.getKeyNameMap());
        this.dictionaryMap.put("genreNameKeyMap", dictionaryService.getNameKeyMap());

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的数字(大于零的整数或小数)！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error_1 = "第 {0} 行: {1}:{2} 输入错误，该{1}在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;
        int maxRow = 0;

        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

//            //customerName 客户名称
//            String customerName = mapObject.get("customerName");
//            if (customerName == null || customerName.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "客户名称");
//                strBuf.append(str_isnull);
//            }

            //sourceCode 企业货品编码 允许为空
            //productName 货品名称
            String productName = mapObject.get("productName");
            if (productName == null || productName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "货品名称");
                strBuf.append(str_isnull);
            }

            //productSpec 规格型号
            String productSpec = mapObject.get("productSpec");
            if (productSpec == null || productSpec.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "规格型号");
                strBuf.append(str_isnull);
            }

            //productPictureCode 图号 允许为空
            //productGenreName 货品属性
            String productGenreName = mapObject.get("productGenreName");
            if (productGenreName == null || productGenreName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "货品属性");
                strBuf.append(str_isnull);
            }

            //productUnitName 单位
            String productUnitName = mapObject.get("productUnitName");
            if (productUnitName == null || productUnitName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "单位");
                strBuf.append(str_isnull);
            }

            //productPrice 单价
            String productPrice = mapObject.get("productPrice");
            if (productPrice == null || productPrice.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "单价");
                strBuf.append(str_isnull);
            } else if (productPrice != null && productPrice.trim().length() > 0) {
                productPrice = productPrice.trim();
                try {
                    //全数字
                    BigDecimal bigDecimal = new BigDecimal(productPrice);
                    //四舍五入到2位小数
                    bigDecimal = bigDecimal.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
                    mapObject.put("productPrice", bigDecimal.toString());

                } catch (NumberFormatException e) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，请输入正确的数字(大于零的整数，或大于零的(1,2)位小数)！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "单价",
                            productPrice);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                }
            }

            //productGenreName 货品属性
            if (productGenreName != null && productGenreName.trim().length() > 0) {
                productGenreName = productGenreName.trim();
                if (this.dictionaryMap != null && this.dictionaryMap.get("genreNameKeyMap") != null) {
                    Map<String, String> productGenreNameKeyMap = this.dictionaryMap.get("genreNameKeyMap");
                    if (productGenreNameKeyMap.get(productGenreName) == null) {
                        //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，该{1}在系统中不存在！"
                        String str_isnull = MessageFormat.format(msg_column_error_1,
                                (i+index_int),
                                "货品属性",
                                productGenreName);
                        strBuf.append(str_isnull);

                        maxRow = maxRow + 1;
                        if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                    } else {
                        mapObject.put("productGenre", productGenreNameKeyMap.get(productGenreName));
                    }
                }
            }
        }

        return strBuf.toString();
    }

    /**
     * 添加系统基础表
     * 1. 货品表
     * 2. 字典表(货品类型,货品单位)
     * @param objectList
     * @param companyId
     */
    public void addSystemBaseTableImportExcel(List<LinkedHashMap<String, String>> objectList,
                                              String companyId,
                                              String userId,
                                              Map<String, String> sysUnitMap,
                                              Map<String, String> sysProductTypeMap,
                                              Map<String, String> sysProductMap) throws Exception {
        //1. 查询字典表(货品单位)-系统中已经存在的单位比较-不存在添加字典表(货品单位)
        Map<String, String> excelUnitMap = this.findUnitMapByExcel(objectList);
        this.addUnit(excelUnitMap, sysUnitMap, companyId, userId);

        //2. 查询字典表(货品类型)-系统中已经存在的单位比较-不存在添加字典表(货品类型)
        Map<String, String> excelProductTypeMap = this.findProductTypeMapByExcel(objectList);
        this.addProductType(excelProductTypeMap, sysProductTypeMap, companyId, userId);

        //3. 查询货品表(货品名称_规格型号)-系统中已经存在的货品比较-不存在添加货品表(货品单位)
        sysProductMap = this.findProductMapBySystem(companyId, sysProductMap);
        Map<String, Map<String, String>> excelProductMap = this.findProductMapByExcel(objectList);
        this.addProductData(excelProductMap, sysProductMap, sysProductTypeMap, companyId, userId);
    }

    /**
     * 添加货品单位数据(vmes_product_unit)
     * 表(vmes_product_unit) 字段说明：
     * product_id:    货品id
     * unit:          单位id
     * product_price: 货品单价
     * type:          单位类型 (1:计量单位 0:计价单位)
     * isdefault:     是否默认 (1:默认 0:非默认)
     *
     * 根据Excel导入数据
     * @param objectList
     * @throws Exception
     */
    public void addProductUnitImportExcel(List<LinkedHashMap<String, String>> objectList,
                                          String companyId,
                                          String userId,
                                          Map<String, String> sysUnitMap,
                                          Map<String, String> sysProductMap) throws Exception {
        //获取当前企业全部货品单位Map结构体-查询货品单位表(vmes_product_unit)
        //货品单位Map结构体: <货品id_单位id, 货品单位表id>
        Map<String, String> sysProductUnitMap = this.findProductUnitMapBySystem(companyId);


        //获取货品单位Map结构体-根据Excel导入数据
        //货品单位Map结构体: <货品id_单位id, ProductUnit>
        Map<String, ProductUnit> excelProductUnitMap = findProductUnitMapByExcelList(objectList,
                sysUnitMap,
                sysProductMap);

        //获取当前企业全部货品计量单位
        //货品计量单位Map结构体: <货品id, 计量单位id>
        Map<String, String> sysProductProdUnitMap = this.findProductProdUnitMapBySystem(companyId);

        //获取当前企业全部货品默认单位
        //货品默认单位Map结构体: <货品id, 默认单位id>
        Map<String, String> sysProductDefaultUnitMap = this.findProductDefaultUnitMapBySystem(companyId);

        if (excelProductUnitMap != null) {
            for (Iterator iterator = excelProductUnitMap.keySet().iterator(); iterator.hasNext();) {
                //mapKey: 货品id_单位id
                String prod_unit_mapKey = (String)iterator.next();
                ProductUnit excelObject = excelProductUnitMap.get(prod_unit_mapKey);

                //货品id
                String productId = excelObject.getProductId();
                //货品单位id
                String unit = excelObject.getUnit();

                //(货品id_单位id) 在(vmes_product_unit)中存在
                if (sysProductUnitMap != null && sysProductUnitMap.get(prod_unit_mapKey) != null) {
                    String productUnitId = sysProductUnitMap.get(prod_unit_mapKey);

                    ProductUnit editObject = new ProductUnit();
                    editObject.setId(productUnitId);
                    editObject.setProductPrice(excelObject.getProductPrice());
                    editObject.setUuser(userId);

                    //判断当前货品id-是否计量单位
                    if (sysProductProdUnitMap != null && sysProductProdUnitMap.get(productId) == null) {
                        //设定计量单位
                        //单位类型 (1:计量单位 0:计价单位)
                        editObject.setType("1");

                        //npFormula 计量单位转换计价单位
                        editObject.setNpFormula("P=N");
                        editObject.setN2pIsScale(Common.SYS_ISSCALE_TRUE);
                        editObject.setN2pDecimalCount(Integer.valueOf(Common.SYS_NUMBER_FORMAT_2));

                        //pnFormula 计价单位转换计量单位
                        editObject.setPnFormula("N=P");
                        editObject.setP2nIsScale(Common.SYS_ISSCALE_TRUE);
                        editObject.setP2nDecimalCount(Integer.valueOf(Common.SYS_NUMBER_FORMAT_2));

                        //设定货品计量单位Map结构体: <货品id, 计量单位id>
                        sysProductProdUnitMap.put(productId, unit);
                    } else if (sysProductProdUnitMap != null
                        && sysProductProdUnitMap.get(productId) != null
                        && !sysProductProdUnitMap.get(productId).equals(unit)
                    ) {
                        //设定计价单位
                        //单位类型 (1:计量单位 0:计价单位)
                        editObject.setType("0");
                    }

                    //判断当前货品id-是否默认单位
                    if (sysProductDefaultUnitMap != null && sysProductDefaultUnitMap.get(productId) == null) {
                        //设定默认单位 是否默认 (1:默认 0:非默认)
                        editObject.setIsdefault("1");
                        //设定货品默认单位Map结构体: <货品id, 默认单位id>
                        sysProductDefaultUnitMap.put(productId, unit);
                    } else if (sysProductDefaultUnitMap != null
                        && sysProductDefaultUnitMap.get(productId) != null
                        && !sysProductDefaultUnitMap.get(productId).equals(unit)
                    ) {
                        //设定默认单位 是否默认 (1:默认 0:非默认)
                        editObject.setIsdefault("0");
                    }

                    productUnitService.update(editObject);

                } else if (sysProductUnitMap != null && sysProductUnitMap.get(prod_unit_mapKey) == null) {
                    ProductUnit addObject = excelObject;
                    addObject.setUuser(userId);

                    //npFormula 计量单位转换计价单位
                    addObject.setNpFormula("P=N");
                    addObject.setN2pIsScale(Common.SYS_ISSCALE_TRUE);
                    addObject.setN2pDecimalCount(Integer.valueOf(Common.SYS_NUMBER_FORMAT_2));

                    //pnFormula 计价单位转换计量单位
                    addObject.setPnFormula("N=P");
                    addObject.setP2nIsScale(Common.SYS_ISSCALE_TRUE);
                    addObject.setP2nDecimalCount(Integer.valueOf(Common.SYS_NUMBER_FORMAT_2));

                    //单位类型 默认计量单位 (1:计量单位 0:计价单位)
                    addObject.setType("1");
                    //判断当前货品id-是否计量单位
                    if (sysProductProdUnitMap != null && sysProductProdUnitMap.get(productId) == null) {
                        //设定计量单位
                        //单位类型 (1:计量单位 0:计价单位)
                        addObject.setType("1");

                        //设定货品计量单位Map结构体: <货品id, 计量单位id>
                        sysProductProdUnitMap.put(productId, unit);
                    } else if (sysProductProdUnitMap != null
                        && sysProductProdUnitMap.get(productId) != null
                        && !sysProductProdUnitMap.get(productId).equals(unit)
                    ) {
                        //设定计价单位
                        //单位类型 (1:计量单位 0:计价单位)
                        addObject.setType("0");
                    }

                    //设定默认单位 默认添加为(1:默认:属性) 是否默认 (1:默认 0:非默认)
                    addObject.setIsdefault("1");
                    //判断当前货品id-是否默认单位
                    if (sysProductDefaultUnitMap != null && sysProductDefaultUnitMap.get(productId) == null) {
                        //设定默认单位 是否默认 (1:默认 0:非默认)
                        addObject.setIsdefault("1");
                        //设定货品默认单位Map结构体: <货品id, 默认单位id>
                        sysProductDefaultUnitMap.put(productId, unit);
                    } else if (sysProductDefaultUnitMap != null
                        && sysProductDefaultUnitMap.get(productId) != null
                        && !sysProductDefaultUnitMap.get(productId).equals(unit)
                    ) {
                        //设定默认单位 是否默认 (1:默认 0:非默认)
                        addObject.setIsdefault("0");
                    }

                    productUnitService.save(addObject);

                    //货品单位Map结构体: <货品id_单位id, 货品单位表id>
                    //mapKey: 货品id_单位id
                    String prod_unit = productId + "_" + unit;
                    sysProductUnitMap.put(prod_unit, addObject.getId());
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> findUnitMapByExcel(List<LinkedHashMap<String, String>> objectList) {
        Map<String, String> warehouseMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return warehouseMap;}

        for (Map<String, String> mapObject : objectList) {
            //productUnitName 货品单位
            String productUnitName = mapObject.get("productUnitName");

            warehouseMap.put(productUnitName, productUnitName);
        }

        return warehouseMap;
    }

    /**
     *
     * @param excelUnitMap Excel导入文件-单位列Map<单位名称, 单位id>
     * @param sysUnitMap  系统字典表Map<单位名称, 单位id>
     * @param companyId   企业id
     * @param userId      用户id
     * @throws Exception
     */
    private void addUnit(Map<String, String> excelUnitMap,
                         Map<String, String> sysUnitMap,
                         String companyId,
                         String userId) throws Exception {
        if (excelUnitMap == null) {return;}

        //1.(货品单位名称) 与系统字典表结构体比较 是否存在
        List<String> addUnitList = new ArrayList();
        for (Iterator iterator = excelUnitMap.keySet().iterator(); iterator.hasNext();) {
            //货品单位名称
            String mapKey = iterator.next().toString().trim();
            if (sysUnitMap.get(mapKey) == null) {
                addUnitList.add(mapKey);
            }
        }

        if (addUnitList.size() > 0) {
            String pid = Common.DICTIONARY_MAP.get("productUnit");
            //productUnit: 0ae6e79890db490585e13f34bf00ea4b 货品单位
            com.xy.vmes.entity.Dictionary paterObj = dictionaryService.findDictionaryById(pid);

            for (String productUnitName : addUnitList) {
                //创建字典信息
                com.xy.vmes.entity.Dictionary addDictionary = new com.xy.vmes.entity.Dictionary();
                addDictionary.setId(Conv.createUuid());
                addDictionary.setCompanyId(companyId);
                addDictionary.setCuser(userId);
                addDictionary.setName(productUnitName);

                //isglobal: 0：否  1：是
                //0:非全局设置 1:是全局(超级管理员创建的数据字典都是全局设置)
                addDictionary.setIsglobal("0");

                addDictionary = dictionaryService.id2DictionaryByLayer(addDictionary.getId(),
                        Integer.valueOf(paterObj.getLayer().intValue() + 1),
                        addDictionary);
                addDictionary = dictionaryService.paterObject2ObjectDB(paterObj, addDictionary);

                //设置默认部门顺序
                if (addDictionary.getSerialNumber() == null) {
                    Integer maxCount = dictionaryService.findMaxSerialNumber(addDictionary.getPid());
                    addDictionary.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
                }
                dictionaryService.save(addDictionary);

                //系统字典表Map<单位名称, 单位id>
                sysUnitMap.put(productUnitName, addDictionary.getId());
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Map<String, String> findProductTypeMapByExcel(List<LinkedHashMap<String, String>> objectList) {
        Map<String, String> productTypeMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return productTypeMap;}

        for (Map<String, String> mapObject : objectList) {
            //typeName 货品类型(允许为空)
            String typeName = mapObject.get("typeName");
            if (typeName != null && typeName.trim().length() > 0) {
                productTypeMap.put(typeName, typeName);
            }
        }

        return productTypeMap;
    }

    /**
     *
     * @param excelProductTypeMap Excel导入文件-货品类型列Map<货品类型名称, 货品类型id>
     * @param sysProductTypeMap   系统字典表Map<货品类型名称, 货品类型id>
     * @param companyId   企业id
     * @param userId      用户id
     * @throws Exception
     */
    private void addProductType(Map<String, String> excelProductTypeMap,
                                Map<String, String> sysProductTypeMap,
                                String companyId,
                                String userId) throws Exception {
        if (excelProductTypeMap == null) {return;}

        //1.(货品类型名称) 与系统字典表结构体比较 是否存在
        List<String> addProductTypeList = new ArrayList();
        for (Iterator iterator = excelProductTypeMap.keySet().iterator(); iterator.hasNext();) {
            //货品类型名称
            String mapKey = iterator.next().toString().trim();
            if (sysProductTypeMap.get(mapKey) == null) {
                addProductTypeList.add(mapKey);
            }
        }

        if (addProductTypeList.size() > 0) {
            //货品类型:productType: a39ac4c1e02e45788eb03a52a5e9a972
            String pid = Common.DICTIONARY_MAP.get("productType");
            com.xy.vmes.entity.Dictionary paterObj = dictionaryService.findDictionaryById(pid);

            for (String productTypeName : addProductTypeList) {
                //创建字典信息
                com.xy.vmes.entity.Dictionary addDictionary = new Dictionary();
                addDictionary.setId(Conv.createUuid());
                addDictionary.setCompanyId(companyId);
                addDictionary.setCuser(userId);
                addDictionary.setName(productTypeName);

                //isglobal: 0：否  1：是
                //0:非全局设置 1:是全局(超级管理员创建的数据字典都是全局设置)
                addDictionary.setIsglobal("0");

                addDictionary = dictionaryService.id2DictionaryByLayer(addDictionary.getId(),
                        Integer.valueOf(paterObj.getLayer().intValue() + 1),
                        addDictionary);
                addDictionary = dictionaryService.paterObject2ObjectDB(paterObj, addDictionary);

                //设置默认部门顺序
                if (addDictionary.getSerialNumber() == null) {
                    Integer maxCount = dictionaryService.findMaxSerialNumber(addDictionary.getPid());
                    addDictionary.setSerialNumber(Integer.valueOf(maxCount.intValue() + 1));
                }
                dictionaryService.save(addDictionary);

                //系统字典表Map<货品类型名称, 货品类型id>
                sysProductTypeMap.put(productTypeName, addDictionary.getId());
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取货品Map结构体
     * 货品Map结构体: <货品名称_规格型号, 货品id>
     *
     * @param companyId
     * @return
     */
    private Map<String, String> findProductMapBySystem(String companyId, Map<String, String> sysProductMap) throws Exception {
        if (companyId == null || companyId.trim().length() == 0) {return sysProductMap;}

        //查询货品表 vmes_product
        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //是否启用(0:已禁用 1:启用)
        findMap.put("isdisable", "1");
        findMap.put("mapSize", Integer.valueOf(findMap.size()));
        List<Product> productList = productService.findProductList(findMap);
        if (productList == null || productList.size() == 0) {return sysProductMap;}

        for (Product product : productList) {
            //name 货品名称
            String name = product.getName();
            //spec 规格型号
            String spec = product.getSpec();
            //id 货品id
            String id = product.getId();

            String mapKey = name + "_" + spec;
            sysProductMap.put(mapKey, id);
        }

        return sysProductMap;
    }

    private Map<String, Map<String, String>> findProductMapByExcel(List<LinkedHashMap<String, String>> objectList) {
        Map<String, Map<String, String>> productMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return productMap;}

        for (Map<String, String> mapObject : objectList) {
            Map<String, String> productValueMap = new HashMap();

            //sourceCode 企业货品编码(允许为空)
            if (mapObject.get("sourceCode") != null) {
                productValueMap.put("sourceCode", mapObject.get("sourceCode").trim());
            }

            //productName 货品名称
            String productName = mapObject.get("productName");
            productValueMap.put("productName", productName);

            //productSpec 规格型号
            String productSpec = mapObject.get("productSpec");
            productValueMap.put("productSpec", productSpec);

            //productPictureCode 图号
            String productPictureCode = mapObject.get("productPictureCode");
            productValueMap.put("productPictureCode", productPictureCode);

            //productGenreName 货品属性
            String productGenreName = mapObject.get("productGenreName");
            productValueMap.put("productGenreName", productGenreName);

            //typeName 货品类型(允许为空)
            if (mapObject.get("typeName") != null) {
                productValueMap.put("typeName", mapObject.get("typeName").trim());
            }

            //mapKey := 货品名称_规格型号
            String mapKey = productName + "_" + productSpec;
            productMap.put(mapKey, productValueMap);
        }

        return productMap;
    }

    /**
     *
     * @param excelProductMap Excel导入文件-(货品名称,规格型号)列Map<货品名称_规格型号, 货品属性Map>
     * @param sysProductMap   系统货品表Map<货品名称_规格型号, 货品id>
     * @param companyId
     * @param userId
     * @throws Exception
     */
    private void addProductData(Map<String, Map<String, String>> excelProductMap,
                            Map<String, String> sysProductMap,
                            Map<String, String> sysProductTypeMap,
                            String companyId,
                            String userId) throws Exception {
        if (excelProductMap == null) {return;}

        //1.(货品名称_规格型号) 与系统货品表结构体比较 是否存在
        List<String> addProductList = new ArrayList();
        for (Iterator iterator = excelProductMap.keySet().iterator(); iterator.hasNext();) {
            //mapKey:货品名称_规格型号
            String mapKey = iterator.next().toString().trim();
            if (sysProductMap.get(mapKey) == null) {
                addProductList.add(mapKey);
            }
        }

        if (addProductList.size() > 0) {
            for (String productKey : addProductList) {
                //添加产品表(vmes_product)
                Product addProduct = new Product();
                addProduct.setId(Conv.createUuid());
                addProduct.setCompanyId(companyId);
                addProduct.setCuser(userId);

                Map<String, String> productMap = excelProductMap.get(productKey);
                //productName 货品名称
                String productName = productMap.get("productName");
                addProduct.setName(productName);

                //productSpec 规格型号
                String productSpec = productMap.get("productSpec");
                addProduct.setSpec(productSpec);

                //productPictureCode 图号
                String productPictureCode = productMap.get("productPictureCode");
                if (productPictureCode != null) {
                    addProduct.setPictureCode(productPictureCode.trim());
                }

                //productGenreName 货品属性
                String productGenreName = productMap.get("productGenreName");
                if (productGenreName != null && productGenreName.trim().length() > 0) {
                    productGenreName = productGenreName.trim();
                    if (this.dictionaryMap != null && this.dictionaryMap.get("genreNameKeyMap") != null) {
                        Map<String, String> genreNameKeyMap = this.dictionaryMap.get("genreNameKeyMap");
                        if (genreNameKeyMap.get(productGenreName) != null) {
                            addProduct.setGenre(genreNameKeyMap.get(productGenreName));
                        }
                    }
                }

                //sourceCode 企业货品编码(允许为空)
                String sourceCode = productMap.get("sourceCode");
                if (sourceCode != null && sourceCode.trim().length() > 0) {
                    addProduct.setSourceCode(sourceCode.trim());
                }

                //type 货品类型id(字典表id)
                String type = new String();

                //typeName 货品类型(允许为空)
                String typeName = productMap.get("typeName");
                if (typeName != null && typeName.trim().length() > 0
                        && sysProductTypeMap != null && sysProductTypeMap.get(typeName) != null
                        ) {
                    type = sysProductTypeMap.get(typeName).trim();
                }
                addProduct.setType(type);

                //创建货品编码
                String code = coderuleService.createCoder(companyId,"vmes_product","P");
                addProduct.setCode(code);

                //生成货品二维码
                String qrcode = fileService.createQRCode("product", addProduct.getId());
                if (qrcode != null && qrcode.trim().length() > 0) {
                    addProduct.setQrcode(qrcode);
                }
                productService.save(addProduct);

                //系统货品表Map<货品名称_规格型号, 货品id>
                sysProductMap.put(productKey, addProduct.getId());
            }
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取货品单位Map结构体
     * 货品单位Map结构体: <货品id_单位id, 货品单位表id>
     *
     * @param companyId
     * @return
     */
    private Map<String, String> findProductUnitMapBySystem(String companyId) throws Exception {
        Map<String, String> sysProductUnitMap = new HashMap<>();
        if (companyId == null || companyId.trim().length() == 0) {return sysProductUnitMap;}

        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //是否启用(0:已禁用 1:启用)
        findMap.put("productUnitIsdisable", "1");
        List<Map> mapList = productUnitService.getDataListPage(findMap);

        if (mapList != null && mapList.size() > 0) {
            for (Map<String, String> mapObject : mapList) {
                String id = mapObject.get("id");

                //productId 货品id
                String productId = mapObject.get("productId");

                //punit 货品单位id
                String punit = mapObject.get("punit");

                //货品id_单位id
                String mapKey = productId + "_" + punit;

                sysProductUnitMap.put(mapKey, id);
            }
        }

        return sysProductUnitMap;
    }

    /**
     * 获取当前企业全部货品计量单位Map结构体
     * 货品计量单位Map结构体: <货品id, 计量单位id>
     *
     * @param companyId
     * @return
     * @throws Exception
     */
    private Map<String, String> findProductProdUnitMapBySystem(String companyId) throws Exception {
        Map<String, String> sysProductProdUnitMap = new HashMap<>();
        if (companyId == null || companyId.trim().length() == 0) {return sysProductProdUnitMap;}

        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //productUnitType 单位类型 (1:计量单位 0:计价单位)
        findMap.put("productUnitType", "1");
        //是否启用(0:已禁用 1:启用)
        findMap.put("productUnitIsdisable", "1");
        List<Map> mapList = productUnitService.getDataListPage(findMap);

        if (mapList != null && mapList.size() > 0) {
            for (Map<String, String> mapObject : mapList) {
                //productId 货品id
                String productId = mapObject.get("productId");

                //punit 计量单位id
                String punit = mapObject.get("punit");

                sysProductProdUnitMap.put(productId, punit);
            }
        }

        return sysProductProdUnitMap;
    }

    /**
     * 获取当前企业全部货品默认单位Map结构体
     * 货品默认单位Map结构体: <货品id, 默认单位id>
     *
     * @param companyId
     * @return
     * @throws Exception
     */
    private Map<String, String> findProductDefaultUnitMapBySystem(String companyId) throws Exception {
        Map<String, String> sysProductDefaultUnitMap = new HashMap<>();
        if (companyId == null || companyId.trim().length() == 0) {return sysProductDefaultUnitMap;}

        PageData findMap = new PageData();
        findMap.put("companyId", companyId);
        //productUnitIsdefault 是否默认 (1:默认 0:非默认)
        findMap.put("productUnitIsdefault", "1");
        //是否启用(0:已禁用 1:启用)
        findMap.put("productUnitIsdisable", "1");
        List<Map> mapList = productUnitService.getDataListPage(findMap);

        if (mapList != null && mapList.size() > 0) {
            for (Map<String, String> mapObject : mapList) {
                //productId 货品id
                String productId = mapObject.get("productId");

                //punit 默认单位id
                String punit = mapObject.get("punit");

                sysProductDefaultUnitMap.put(productId, punit);
            }
        }

        return sysProductDefaultUnitMap;
    }

    /**
     * 获取货品单位Map结构体-根据Excel导入数据
     * 货品单位Map结构体: <货品id_单位id, ProductUnit>
     *
     * @return
     */
    public Map<String, ProductUnit> findProductUnitMapByExcelList(List<LinkedHashMap<String, String>> objectList,
                                                                  Map<String, String> sysUnitMap,
                                                                  Map<String, String> sysProductMap) {
        Map<String, ProductUnit> excelProductUnitMap = new LinkedHashMap<>();
        if (objectList == null || objectList.size() == 0) {return excelProductUnitMap;}

        for (int i = 0; i < objectList.size(); i++) {
            ProductUnit object = new ProductUnit();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //productName 货品名称
            String productName = mapObject.get("productName");
            //productSpec 规格型号
            String productSpec = mapObject.get("productSpec");
            String productMapKey = productName + "_" + productSpec;

            String productId = new String();
            if (sysProductMap != null && sysProductMap.get(productMapKey) != null) {
                productId = sysProductMap.get(productMapKey);
            }
            object.setProductId(productId);

            //productUnitName 单位
            String productUnitName = mapObject.get("productUnitName");
            String productUnitId = new String();
            if (sysUnitMap != null && sysUnitMap.get(productUnitName) != null) {
                productUnitId = sysUnitMap.get(productUnitName);
            }
            object.setUnit(productUnitId);

            //productPrice 客户单价
            BigDecimal productPrice = BigDecimal.valueOf(0D);
            String productPriceStr = mapObject.get("productPrice");
            if (productPriceStr != null && productPriceStr.trim().length() > 0) {
                try {
                    productPrice = new BigDecimal(productPriceStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                //四舍五入到2位小数
                productPrice = productPrice.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
            }
            object.setProductPrice(productPrice);

            //客户货品单位 (货品id_单位id)
            String prodUnitKey = productId + "_" + productUnitId;
            excelProductUnitMap.put(prodUnitKey, object);
        }

        return excelProductUnitMap;
    }

}
