package com.xy.vmes.common.util;

import com.xy.vmes.entity.Column;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class ColumnUtil {

    /**
     * 查询结果集List<Map>-按照栏位列表Map的显示顺序-重构结果集List<Map>
     *
     * @param columnList  栏位列表Map<栏位编码, 栏位名称>
     * @param dataList   业务查询结果集Map<栏位编码, Object>
     * @return
     */
    public static List<LinkedHashMap<String, String>> modifyDataList(List<Column> columnList, List<Map> dataList) {
        List<LinkedHashMap<String, String>> dataMapList = new ArrayList<LinkedHashMap<String, String>>();
        if(dataList == null) {return dataMapList;}

        //1. 获取(第一行:栏位编码 第二行: 栏位名称)
        LinkedHashMap<String, String> columnCodeMap = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> columnNameMap = new LinkedHashMap<String, String>();
        for (Column column : columnList) {
            if("0".equals(column.getIshide())){
                columnNameMap.put(column.getTitleKey()+"_hide", column.getTitleName());
                columnCodeMap.put(column.getTitleKey()+"_hide", column.getTitleKey());
            }else {
                columnNameMap.put(column.getTitleKey(), column.getTitleName());
                columnCodeMap.put(column.getTitleKey(), column.getTitleKey());
            }
        }
        dataMapList.add(columnCodeMap);
        dataMapList.add(columnNameMap);

        //获取栏位值
        for (Map dataMap : dataList) {
            LinkedHashMap<String, String> columnValueMap = new LinkedHashMap<String, String>();
            for (Column column : columnList) {
                if(column!=null){
                    String columnMapKey = column.getTitleKey();
                    if(!StringUtils.isEmpty(columnMapKey)){
                        String dataValue = "";
                        Object object = dataMap.get(columnMapKey);
                        if (object != null) {
                            dataValue = object.toString();
                        }

                        if("0".equals(column.getIshide())){
                            columnValueMap.put(columnMapKey+"_hide", dataValue);
                        }else {
                            columnValueMap.put(columnMapKey, dataValue);
                        }


                    }
                }

            }
            dataMapList.add(columnValueMap);
        }

        return dataMapList;
    }

    public static List<Column> listAllhideByColumnList(List<Column> columnList) {
        if (columnList == null) {columnList = new ArrayList<Column>();}
        for (Column column : columnList) {
            //是否隐藏(0:隐藏 1:显示)
            column.setIshide("0");
            column.setSerialNumber(Integer.valueOf(99));
        }
        return columnList;
    }

    public static LinkedHashMap<String, Column> columnList2ColumnMap(List<Column> columnList, LinkedHashMap<String, Column> columnMap) {
        if (columnMap == null) {columnMap = new LinkedHashMap<String, Column>();}
        if (columnList == null || columnList.size() == 0) {return columnMap;}

        for (Column column : columnList) {
            String titleKey = column.getTitleKey();
            columnMap.put(titleKey, column);
        }
        return columnMap;
    }

    public static List<Column> modifyColumnList(String fieldCode, LinkedHashMap<String, Column> columnMap) {
        List<Column> newColumnList = new ArrayList<Column>();

        if (fieldCode == null || fieldCode.trim().length() == 0) {return newColumnList;}
        if (columnMap == null || columnMap.size() == 0) {return newColumnList;}

        List<Column> inColumnList = new ArrayList<Column>();
        String[] fieldArry = fieldCode.split(",");
        for (int i = 0; i < fieldArry.length; i++) {
            String field = fieldArry[i].trim();
            Column column = new Column();
            if (columnMap.get(field) != null) {
                column = columnMap.get(field).clone();
                //是否隐藏(0:隐藏 1:显示)
                column.setIshide("1");
                column.setSerialNumber(Integer.valueOf(i+1));
                inColumnList.add(column);
            } else if (columnMap.get(field) == null) {
                column.setTitleKey(field);
                column.setTitleName(field);
                column.setIshide("1");
                column.setSerialNumber(Integer.valueOf(i+1));
            }
            newColumnList.add(column);
        }

        for (Iterator iterator = columnMap.keySet().iterator(); iterator.hasNext();) {
            String mapKey = iterator.next().toString().trim();
            Column column = columnMap.get(mapKey);
            boolean isInColumn = false;
            for (int i = 0; i < inColumnList.size(); i++) {
                Column inColumn = inColumnList.get(i);
                if (mapKey.equals(inColumn.getTitleKey())) {
                    isInColumn = true;
                    break;}
            }
            if (!isInColumn) {
                newColumnList.add(column);
            }
        }

        return newColumnList;
    }

    /**
     * 根据List<Column>，获取业务列表Map
     * @param columnList
     *
     * @return
     */
    public static Map<String, Object> findTitleMapByColumnList(List<Column> columnList) {
        Map<String, Object> mapObject = new HashMap<String, Object>();
        if (columnList == null || columnList.size() == 0) {return mapObject;}

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        List<String> titlesHideList = new ArrayList<String>();
        Map<String, String> varModelMap = new HashMap<String, String>();

        for (Column column : columnList) {
            if("0".equals(column.getIshide())){
                titlesHideList.add(column.getTitleKey());
            }
            LinkedHashMap titlesLinkedMap = new LinkedHashMap();
            titlesLinkedMap.put(column.getTitleKey(),column.getTitleName());
            varModelMap.put(column.getTitleKey(),"");
            titlesList.add(titlesLinkedMap);
        }

        mapObject.put("hideTitles",titlesHideList);
        mapObject.put("titles",titlesList);
        mapObject.put("varModel",varModelMap);

        return mapObject;
    }


    public static List<Map> getVarMapList(List<Map> varList,Map<String, Object> titleMap) {
        List<Map> varMapList = new ArrayList();
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, Object> varMap = new HashMap<String, Object>();
                varMap.putAll((Map<String, String>)titleMap.get("varModel"));
                for (Map.Entry<String, Object> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMap.put("hideTitles", new ArrayList<String>());
                varMap.put("titles", new ArrayList<LinkedHashMap>());
                varMap.put("children",new ArrayList());
                varMapList.add(varMap);
            }
        }

        return varMapList;
    }

    public static void orderAcsBySerialNumber(List<Column> objectList) {
        Collections.sort(objectList, new Comparator<Object>() {
            public int compare(Object arg0, Object arg1) {
                Column object_0 = (Column)arg0;
                Column object_1 = (Column)arg1;
                return object_0.getSerialNumber().compareTo(object_1.getSerialNumber());
            }
        });
    }

    public static Map getTitleList(List<Column> columnList){
        Map result = new HashMap();
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
            }
        }
        result.put("hideTitles",titlesHideList);
        result.put("titles",titlesList);
        result.put("varModel",varModelMap);
        return result;
    }
}
