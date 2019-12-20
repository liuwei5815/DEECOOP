package com.xy.vmes.deecoop.base.serviceImp;

import com.xy.vmes.deecoop.base.service.CustomerExcelService;
import com.xy.vmes.deecoop.base.service.CustomerService;
import com.xy.vmes.deecoop.fileIO.service.FileService;
import com.xy.vmes.deecoop.system.service.CoderuleService;
import com.xy.vmes.deecoop.system.service.DictionaryService;
import com.yvan.common.util.Common;
import com.xy.vmes.entity.Customer;
import com.yvan.Conv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerExcelServiceImp implements CustomerExcelService {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private FileService fileService;
    @Autowired
    private CoderuleService coderuleService;

    public String checkColumnImportExcel(List<LinkedHashMap<String, String>> objectList,
                                         String companyId,
                                         String userId,
                                         Integer index,
                                         Integer maxShowRow) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        int maxRow = 0;
        int index_int = 1;
        if (index != null) {
            index_int = index.intValue();
        }

        int maxShowRow_int = 20;
        if (maxShowRow != null) {
            maxShowRow_int = maxShowRow.intValue();
        }

        //获取全部 客户供应商属性
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("customerSupplierGenre"), null);
        Map<String, String> genreNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 客户类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("customerType"), companyId);
        Map<String, String> customerTypeNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 供应商类型
        dictionaryService.implementBusinessMapByParentID(Common.DICTIONARY_MAP.get("supplierType"), companyId);
        Map<String, String> supplierTypeNameKeyMap = dictionaryService.getNameKeyMap();

        //获取全部 全国区域名称
        dictionaryService.implementBusinessMapByAreaPath();
        Map<String, String> areaPathNameKeyMap = dictionaryService.getNameKeyMap();

        String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！" + Common.SYS_ENDLINE_DEFAULT;
        String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！" + Common.SYS_ENDLINE_DEFAULT;

        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < objectList.size(); i++) {
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            //companyId 企业ID
            mapObject.put("companyId", companyId);
            mapObject.put("userId", userId);

            //name 名称
            String name = mapObject.get("name");
            if (name == null || name.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "名称");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            }

            //genreName 属性
            String genreName = mapObject.get("genreName");
            if (genreName == null || genreName.trim().length() == 0) {
                //String msg_column_isnull = "第 {0} 行: ({1})输入为空或空字符串，({1})是必填字段不可为空！"
                String str_isnull = MessageFormat.format(msg_column_isnull,
                        (i+index_int),
                        "属性");
                strBuf.append(str_isnull);

                maxRow = maxRow + 1;
                if (maxShowRow_int <= maxRow) {return strBuf.toString();}
            } else if (genreName != null && genreName.trim().length() > 0) {
                if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "属性",
                            genreName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (genreNameKeyMap != null && genreNameKeyMap.size() > 0 && genreNameKeyMap.get(genreName) != null) {
                    //genre 属性id
                    mapObject.put("genre", genreNameKeyMap.get(genreName));
                }
            }

            //typeName 类型
            String typeName = mapObject.get("typeName");
            if (typeName != null && typeName.trim().length() > 0) {
                //genre 属性id
                String genre = mapObject.get("genre");

                //客户
                if (Common.DICTIONARY_MAP.get("customerGenre").equals(genre)
                        && customerTypeNameKeyMap != null && customerTypeNameKeyMap.size() > 0 && customerTypeNameKeyMap.get(typeName) == null
                ) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            genreName + "类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (customerTypeNameKeyMap != null && customerTypeNameKeyMap.size() > 0 && customerTypeNameKeyMap.get(typeName) != null) {
                    //type 类型id
                    mapObject.put("type", customerTypeNameKeyMap.get(typeName));
                }

                //供应商
                if (Common.DICTIONARY_MAP.get("supplierGenre").equals(genre)
                        && supplierTypeNameKeyMap != null && supplierTypeNameKeyMap.size() > 0 && supplierTypeNameKeyMap.get(typeName) == null
                ) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            genreName + "类型",
                            typeName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (supplierTypeNameKeyMap != null && supplierTypeNameKeyMap.size() > 0 && supplierTypeNameKeyMap.get(typeName) != null) {
                    //type 类型id
                    mapObject.put("type", supplierTypeNameKeyMap.get(typeName));
                }
            }

            //provinceName 地区
            String provinceName = mapObject.get("provinceName");
            if (provinceName != null && provinceName.trim().length() > 0) {
                if (areaPathNameKeyMap != null && areaPathNameKeyMap.size() > 0 && areaPathNameKeyMap.get(provinceName) == null) {
                    //String msg_column_error = "第 {0} 行: {1}:{2} 输入错误，在系统中不存在！"
                    String str_error = MessageFormat.format(msg_column_error,
                            (i+index_int),
                            "地区",
                            provinceName);
                    strBuf.append(str_error);

                    maxRow = maxRow + 1;
                    if (maxShowRow_int <= maxRow) {return strBuf.toString();}
                } else if (areaPathNameKeyMap != null && areaPathNameKeyMap.size() > 0 && areaPathNameKeyMap.get(provinceName) != null) {
                    //province 区域id
                    mapObject.put("province", areaPathNameKeyMap.get(provinceName));
                }
            }

            //remark 备注
        }

        return strBuf.toString();
    }

    public String checkExistImportExcelBySelf(List<LinkedHashMap<String, String>> objectList,
                                              Integer index,
                                              Integer maxShowRow) {
        return null;
    }

    public String checkExistImportExcelByDatabase(List<LinkedHashMap<String, String>> objectList,
                                                  Integer index,
                                                  Integer maxShowRow) {
        return null;
    }

    public void addImportExcelByList(List<LinkedHashMap<String, String>> objectList) {
        if (objectList == null || objectList.size() == 0) {return;}

        for (int i = 0; i < objectList.size(); i++) {
            Customer customer = new Customer();
            LinkedHashMap<String, String> mapObject = objectList.get(i);

            String userId = mapObject.get("userId");
            customer.setCuser(userId);

            //companyId 企业ID
            String companyId = mapObject.get("companyId");
            customer.setCompanyId(companyId);

            //name 名称
            String name = mapObject.get("name");
            customer.setName(name);

            //genreName 属性 genre 属性id
            String genre = mapObject.get("genre");
            customer.setGenre(genre);

            //typeName 类型 type 类型id
            String type = mapObject.get("type");
            customer.setType(type);

            //provinceName 地区  province 区域id
            String province = mapObject.get("province");
            customer.setProvince(province);

            //remark 备注
            String remark = mapObject.get("remark");
            if (remark != null && remark.trim().length() > 0) {
                customer.setRemark(remark.trim());
            }

            try {
                //获取客户供应商编码
                String code = coderuleService.createCoder(companyId,"vmes_customer","C");
                customer.setCode(code);

                //生成客户供应商二维码
                customer.setId(Conv.createUuid());
                String qrcode = fileService.createQRCode("customer", customer.getId());
                if (qrcode != null && qrcode.trim().length() > 0) {
                    customer.setQrcode(qrcode);
                }
                customerService.save(customer);

                //System.out.println("第" + (i+1) + "行：添加成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
