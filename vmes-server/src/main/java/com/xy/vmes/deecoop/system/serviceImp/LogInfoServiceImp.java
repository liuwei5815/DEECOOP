package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.yvan.common.util.Common;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.dao.LogInfoMapper;
import com.xy.vmes.entity.Column;
import com.xy.vmes.entity.LogInfo;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.xy.vmes.deecoop.system.service.LogInfoService;
import com.yvan.Conv;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 说明：操作日志 实现类
 * 创建人：刘威 自动创建
 * 创建时间：2018-08-28
 */
@Service
@Transactional(readOnly = false)
public class LogInfoServiceImp implements LogInfoService {

    @Autowired
    private LogInfoMapper logInfoMapper;
    @Autowired
    private ColumnService columnService;



    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void save(LogInfo logInfo) throws Exception {
        logInfo.setId(Conv.createUuid());
        logInfo.setCdate(new Date());
        logInfo.setUdate(new Date());
        logInfoMapper.insert(logInfo);
    }


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void update(LogInfo logInfo) throws Exception {
        logInfo.setUdate(new Date());
        logInfoMapper.updateById(logInfo);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void updateAll(LogInfo logInfo) throws Exception {
        logInfo.setUdate(new Date());
        logInfoMapper.updateAllColumnById(logInfo);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    //@Cacheable(cacheNames = "logInfo", key = "''+#id")
    public LogInfo selectById(String id) throws Exception {
        return logInfoMapper.selectById(id);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void deleteById(String id) throws Exception {
        logInfoMapper.deleteById(id);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception {
        logInfoMapper.deleteByIds(ids);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<LogInfo> dataListPage(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return logInfoMapper.dataListPage(pd, pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<LogInfo> dataList(PageData pd) throws Exception {
        return logInfoMapper.dataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception {
        return logInfoMapper.findColumnList();
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception {
        return logInfoMapper.findDataList(pd);
    }


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception {
        logInfoMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<LogInfo> selectByColumnMap(Map columnMap) throws Exception {
        List<LogInfo> logInfoList = logInfoMapper.selectByMap(columnMap);
        return logInfoList;
    }


    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception {
        return logInfoMapper.getDataList(pd);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return logInfoMapper.getDataListPage(pd, pg);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-08-28
     */
    @Override
    public void updateToDisableByIds(String[] ids) throws Exception {
        logInfoMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * 获取调用方法名称前缀：
     * 根据Controller调用方法全路径名称(com.xy.vmes.deecoop.system.controller.DepartmentController.addDepartment())
     *
     * @param methodPath
     * @return
     */
    @Override
    public String findMethodPrefix(String methodPath) {
        if (methodPath == null || methodPath.trim().length() == 0) {
            return new String();
        }
        if (methodPath.indexOf("throws java.lang.Exception") != -1) {
            methodPath = methodPath.replace("throws java.lang.Exception", "");
        }

        int maxLength = methodPath.length();
        int beginIndex = methodPath.lastIndexOf(".") + 1;

        //1. 获取调用方法名称
        String method = new String();
        if (beginIndex < maxLength) {
            method = methodPath.substring(beginIndex, maxLength);
        }
        if (method.trim().length() == 0) {
            return new String();
        }

        //2. 获取调用方法前缀
        Map<String, String> methodPrefixMap = Common.SYSLOGINFO_METHODPREFIX_MAP;
        for (Iterator iterator = methodPrefixMap.keySet().iterator(); iterator.hasNext(); ) {
            String mapKey = (String) iterator.next();
            String mapValue = methodPrefixMap.get(mapKey);
            if (method.indexOf(mapKey) != -1) {
                return mapValue;
            }
        }

        return new String();
    }

    /**
     * 获取业务表名：
     * 根据Controller调用方法全路径名称(com.xy.vmes.deecoop.system.controller.DepartmentController.addDepartment())
     *
     * @param methodPath
     * @return
     */
    @Override
    public String findTable(String methodPath) {
        if (methodPath == null || methodPath.trim().length() == 0) {
            return new String();
        }

        Map<String, String> classnameMap = Common.SYSLOGINFO_CLASSNAME2TABLENAME_MAP;
        for (Iterator iterator = classnameMap.keySet().iterator(); iterator.hasNext(); ) {
            String mapKey = (String) iterator.next();
            String mapValue = classnameMap.get(mapKey);
            if (methodPath.indexOf(mapKey) != -1) {
                return mapValue;
            }
        }

        return new String();
    }

    /**
     * 创建新的日志对象<Loginfo>
     *
     * @return
     */
    @Override
    public LogInfo createLoginfo(LogInfo object) {
        if (object == null) {
            object = new LogInfo();
        }
        object.setCdate(new Date());
        return object;
    }

    @Override
    public ResultModel listPageLogInfos(PageData pd, Pagination pg) throws Exception {
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();

        String cdate = pd.getString("cdate");
        if(!StringUtils.isEmpty(cdate)){
            String[] cdateArr = cdate.replace("[","").replace("]","").split(",");
            if(cdateArr!=null&&cdateArr.length>0){
                pd.put("startDate",cdateArr[0]);
                pd.put("endDate",cdateArr[1]);
            }
        }



        Map result = new HashMap();

        List<Column> columnList = columnService.findColumnList("logInfo");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        List<LinkedHashMap> titlesList = new ArrayList<LinkedHashMap>();
        ArrayList<String> strings = new ArrayList<>();
        List<String> titlesHideList = strings;
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

        List<Map> varMapList = new ArrayList();
        List<Map> varList = this.getDataListPage(pd,pg);
        if(varList!=null&&varList.size()>0){
            for(int i=0;i<varList.size();i++){
                Map map = varList.get(i);
                Map<String, String> varMap = new HashMap<String, String>();
                varMap.putAll(varModelMap);
                for (Map.Entry<String, String> entry : varMap.entrySet()) {
                    varMap.put(entry.getKey(),map.get(entry.getKey())!=null?map.get(entry.getKey()).toString():"");
                }
                varMapList.add(varMap);
            }
        }
        result.put("varList",varMapList);
        result.put("pageData", pg);

        model.putResult(result);
        return model;
    }

    @Override
    public void exportExcelLogInfos (PageData pd) throws Exception{
        String ids = (String)pd.get("ids");
        String queryColumn = (String)pd.get("queryColumn");

        //2. 获取业务列表List<Map<栏位Key, 栏位名称>>
        List<Column> columnList = columnService.findColumnList("logInfo");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }

        //3. 根据查询条件获取业务数据List
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        if (queryColumn != null && queryColumn.trim().length() > 0) {
            queryStr = queryStr + queryColumn;
        }

        PageData findMap = new PageData();
        //业务表查询条件 findMap.put("查询字段名称", "Value");
        findMap.put("queryStr", queryStr);

        Pagination pg = HttpUtils.parsePagination(pd);
        List<Map> dataList = this.getDataList(findMap);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);

        HttpServletResponse response  = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        //String fileName = "Excel文件导出";
        String fileName = "ExcelLogInfo";
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }

    @Override
    public ResultModel importExcelLogInfos(MultipartFile file) throws Exception {
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
        //2. Excel导入字段(非空,数据有效性验证[数字类型,字典表(大小)类是否匹配])
        //3. Excel导入字段-名称唯一性判断-在Excel文件中
        //4. Excel导入字段-名称唯一性判断-在业务表中判断
        //5. List<ExcelEntity> --> (转换) List<业务表DB>对象
        //6. 遍历List<业务表DB> 对业务表添加或修改
        return model;
    }
}



