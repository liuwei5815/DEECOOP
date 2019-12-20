package com.xy.vmes.deecoop.base.serviceImp;

import com.xy.vmes.deecoop.base.service.ProductExcelService;
import org.springframework.stereotype.Service;

@Service
public class ProductExcelServiceImp implements ProductExcelService {
//    @Autowired
//    private DictionaryService dictionaryService;
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private ProductUnitService productUnitService;
//    @Autowired
//    private ProductUnitPriceService productUnitPriceService;
//
//    @Autowired
//    private FileService fileService;
//    @Autowired
//    private CoderuleService coderuleService;
//
//    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
//                                  String companyId,
//                                  String userId,
//                                  Integer index,
//                                  Integer maxShowRow) {
//        if (objectList == null || objectList.size() == 0) {return new String();}
//
//        int maxRow = 0;
//        int index_int = 1;
//        if (index != null) {
//            index_int = index.intValue();
//        }
//
//        int maxShowRow_int = 20;
//        if (maxShowRow != null) {
//            maxShowRow_int = maxShowRow.intValue();
//        }
//
//        //获取全部 货品属性
//        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productGenre"), null);
//        Map<String, String> genreNameKeyMap = dictionaryService.getNameKeyMap();
//        //获取全部 计量单位
//        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productUnit"), companyId);
//        Map<String, String> unitNameKeyMap = dictionaryService.getNameKeyMap();
//        //获取全部 货品类型
//        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("productType"), companyId);
//        Map<String, String> typeNameKeyMap = dictionaryService.getNameKeyMap();
//
//        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
//        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;
//        String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！" + Common.SYS_ENDLINE_DEFAULT;
//        String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！" + Common.SYS_ENDLINE_DEFAULT;
//
//        StringBuffer strBuf = new StringBuffer();
//        for (int i = 0; i < objectList.size(); i++) {
//            LinkedHashMap<String, String> mapObject = objectList.get(i);
//
//            //companyId 企业ID
//            mapObject.put("companyId", companyId);
//            mapObject.put("userId", userId);
//
//            //name 货品名称
//            String name = mapObject.get("name");
//            if (name == null || name.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "货品名称");
//                strBuf.append(str_isnull);
//
//                maxRow = maxRow + 1;
//                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//            }
//
//            //spec 规格型号
//            String spec = mapObject.get("spec");
//            if (spec == null || spec.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "规格型号");
//                strBuf.append(str_isnull);
//
//                maxRow = maxRow + 1;
//                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//            }
//
//            //genreName 货品属性
//            String genreName = mapObject.get("genreName");
//            if (genreName == null || genreName.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "货品属性");
//                strBuf.append(str_isnull);
//
//                maxRow = maxRow + 1;
//                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//            } else if (genreName != null && genreName.trim().length() > 0) {
//                if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) == null) {
//                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
//                    String str_error = MessageFormat.format(msg_column_error,
//                            (i+index_int),
//                            "货品属性",
//                            genreName);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//                } else if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) != null) {
//                    //genre 货品属性id
//                    mapObject.put("genre", genreNameKeyMap.get(genreName));
//                }
//            }
//
//            //unitName 计量单位
//            String unitName = mapObject.get("unitName");
//            if (unitName == null || unitName.trim().length() == 0) {
//                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
//                String str_isnull = MessageFormat.format(msg_column_isnull,
//                        (i+index_int),
//                        "计量单位");
//                strBuf.append(str_isnull);
//
//                maxRow = maxRow + 1;
//                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//            } else if (unitName != null && unitName.trim().length() > 0) {
//                if (unitNameKeyMap != null && unitNameKeyMap.size() > 0 && unitNameKeyMap.get(unitName) == null) {
//                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
//                    String str_error = MessageFormat.format(msg_column_error,
//                            (i+index_int),
//                            "计量单位",
//                            unitName);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//                } else if (unitNameKeyMap != null && unitNameKeyMap.size() > 0 && unitNameKeyMap.get(unitName) != null) {
//                    //unit 计量单位id
//                    mapObject.put("unit", unitNameKeyMap.get(unitName));
//                }
//            }
//
//            //typeName 货品类型
//            String typeName = mapObject.get("typeName");
//            if (typeName != null && typeName.trim().length() > 0) {
//                if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) == null) {
//                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
//                    String str_error = MessageFormat.format(msg_column_error,
//                            (i+index_int),
//                            "货品类型",
//                            typeName);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
//                } else if (typeNameKeyMap != null && typeNameKeyMap.size() > 0 && typeNameKeyMap.get(typeName) != null) {
//                    //type 货品类型id
//                    mapObject.put("type", typeNameKeyMap.get(typeName));
//                }
//            }
//
//            //validityDays 保质期(天) 零的正整数
//            String validityDays = mapObject.get("validityDays");
//            if (validityDays != null && validityDays.trim().length() > 0) {
//                try {
//                    BigDecimal validityDays_big = new BigDecimal(validityDays);
//                    if (validityDays_big.toString().indexOf(".") != -1) {
//                        //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
//                        String str_error = MessageFormat.format(msg_column_validityDays_error,
//                                (i + index_int),
//                                "保质期",
//                                validityDays);
//                        strBuf.append(str_error);
//
//                        maxRow = maxRow + 1;
//                        if (maxShowRow_int <= maxRow) {
//                            return strBuf.toString();
//                        }
//                    } else if (validityDays_big.doubleValue() <= 0D) {
//                        //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
//                        String str_error = MessageFormat.format(msg_column_validityDays_error,
//                                (i + index_int),
//                                "保质期",
//                                validityDays);
//                        strBuf.append(str_error);
//
//                        maxRow = maxRow + 1;
//                        if (maxShowRow_int <= maxRow) {
//                            return strBuf.toString();
//                        }
//                    } else {
//                        //validityDays 保质期(天)
//                        mapObject.put("validityDays", validityDays_big.toBigInteger().toString());
//                    }
//                } catch (NumberFormatException e) {
//                    //String msg_column_validityDays_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的正整数！"
//                    String str_error = MessageFormat.format(msg_column_validityDays_error,
//                            (i + index_int),
//                            "保质期",
//                            validityDays);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {
//                        return strBuf.toString();
//                    }
//                }
//            }
//
//            //price 单价
//            String price = mapObject.get("price");
//            if (price != null && price.trim().length() > 0) {
//                try {
//                    BigDecimal price_big = new BigDecimal(price);
//                    if (price_big.doubleValue() <= 0D) {
//                        //String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！"
//                        String str_error = MessageFormat.format(msg_column_price_error,
//                                (i + index_int),
//                                "单价",
//                                price);
//                        strBuf.append(str_error);
//
//                        maxRow = maxRow + 1;
//                        if (maxShowRow_int <= maxRow) {
//                            return strBuf.toString();
//                        }
//                    } else {
//                        //price 单价 四舍五入到2位小数
//                        price_big = price_big.setScale(Common.SYS_NUMBER_FORMAT_DEFAULT, BigDecimal.ROUND_HALF_UP);
//                        mapObject.put("price", price_big.toString());
//                    }
//                } catch (NumberFormatException e) {
//                    //String msg_column_price_error = "第 {0} 行: {1}:{2} 输入错误，请输大于零的整数或小数！"
//                    String str_error = MessageFormat.format(msg_column_price_error,
//                            (i + index_int),
//                            "单价",
//                            price);
//                    strBuf.append(str_error);
//
//                    maxRow = maxRow + 1;
//                    if (maxShowRow_int <= maxRow) {
//                        return strBuf.toString();
//                    }
//                }
//            }
//
//        }
//
//        return strBuf.toString();
//    }
//
////    public String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,
////                                       Integer index,
////                                       Integer maxShowRow) {
////        return null;
////    }
////
////    public String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,
////                                           Integer index,
////                                           Integer maxShowRow) {
////        return null;
////    }
//
//    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
//        if (objectList == null || objectList.size() == 0) {return;}
//
//        for (int i = 0; i < objectList.size(); i++) {
//            Product product = new Product();
//            LinkedHashMap<String, String> mapObject = objectList.get(i);
//
//            String userId = mapObject.get("userId");
//            product.setCuser(userId);
//
//            //companyId 企业ID
//            String companyId = mapObject.get("companyId");
//            product.setCompanyId(companyId);
//
//            //name 货品名称
//            String name = mapObject.get("name");
//            product.setName(name);
//
//            //spec 规格型号
//            String spec = mapObject.get("spec");
//            product.setSpec(spec);
//
//            //genreName 货品属性 genre 货品属性id
//            String genre = mapObject.get("genre");
//            product.setGenre(genre);
//
//            //typeName 货品类型 type 货品类型id
//            String type = mapObject.get("type");
//            product.setType(type);
//
//            //validityDays 保质期(天)
//            String validityDays_str = mapObject.get("validityDays");
//            if (validityDays_str != null && validityDays_str.trim().length() > 0) {
//                product.setValidityDays(new BigDecimal (validityDays_str));
//            }
//
//            //remark 备注
//            String remark = mapObject.get("remark");
//            if (remark != null && remark.trim().length() > 0) {
//                product.setRemark(remark.trim());
//            }
//
//            //unitName 计量单位 unit 计量单位id
//            String unit = mapObject.get("unit");
//
//            try {
//                //获取产品编码
//                String code = coderuleService.createCoder(companyId,"vmes_product","P");
//                product.setCode(code);
//
//                //生成产品二维码
//                product.setId(Conv.createUuid());
//                String qrcode = fileService.createQRCode("product", product.getId());
//                if (qrcode != null && qrcode.trim().length() > 0) {
//                    product.setQrcode(qrcode);
//                }
//
//                //添加 货品表
//                productService.save(product);
//
//                //添加 vmes_product_unit
//                ProductUnit productUnit = productService.product2ProductUnit(product, null, unit);
//                productUnitService.save(productUnit);
//
//                //price 单价
//                String price = mapObject.get("price");
//                if (price != null && price.trim().length() > 0) {
//                    product.setPrice(new BigDecimal(price));
//                    ProductUnitPrice productUnitPrice = productService.product2ProductUnitPrice(product, null,unit);
//                    productUnitPriceService.save(productUnitPrice);
//                }
//
//                //System.out.println("第" + (i+1) + "行：添加成功！");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
