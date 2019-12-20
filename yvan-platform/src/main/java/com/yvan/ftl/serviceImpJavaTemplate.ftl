package ${classPath};


import com.xy.vmes.${projectName}.dao.${objectName}Mapper;
import com.xy.vmes.entity.${objectName};
import com.xy.vmes.service.${objectName}Service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Column;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.yvan.Conv;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
* 说明：${TITLE} 实现类
* 创建人：${author} 自动创建
* 创建时间：${nowDate?string("yyyy-MM-dd")}
*/
@Service
@Transactional(readOnly = false)
public class ${objectName}ServiceImp implements ${objectName}Service {


    @Autowired
    private ${objectName}Mapper ${objectNameLower}Mapper;
    @Autowired
    private ColumnService columnService;
    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void save(${objectName} ${objectNameLower}) throws Exception{
        ${objectNameLower}.setId(Conv.createUuid());
        ${objectNameLower}.setCdate(new Date());
        ${objectNameLower}.setUdate(new Date());
        ${objectNameLower}Mapper.insert(${objectNameLower});
    }


    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void update(${objectName} ${objectNameLower}) throws Exception{
        ${objectNameLower}.setUdate(new Date());
        ${objectNameLower}Mapper.updateById(${objectNameLower});
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void updateAll(${objectName} ${objectNameLower}) throws Exception{
        ${objectNameLower}.setUdate(new Date());
        ${objectNameLower}Mapper.updateAllColumnById(${objectNameLower});
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    //@Cacheable(cacheNames = "${objectNameLower}", key = "''+#id")
    public ${objectName} selectById(String id) throws Exception{
        return ${objectNameLower}Mapper.selectById(id);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void deleteById(String id) throws Exception{
        ${objectNameLower}Mapper.deleteById(id);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        ${objectNameLower}Mapper.deleteByIds(ids);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<${objectName}> dataListPage(PageData pd,Pagination pg) throws Exception{
        return ${objectNameLower}Mapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<${objectName}> dataList(PageData pd) throws Exception{
        return ${objectNameLower}Mapper.dataList(pd);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return ${objectNameLower}Mapper.findColumnList();
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return ${objectNameLower}Mapper.findDataList(pd);
    }


    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        ${objectNameLower}Mapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<${objectName}> selectByColumnMap(Map columnMap) throws Exception{
    List<${objectName}> ${objectNameLower}List =  ${objectNameLower}Mapper.selectByMap(columnMap);
        return ${objectNameLower}List;
    }


    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return ${objectNameLower}Mapper.getColumnList();
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return ${objectNameLower}Mapper.getDataList(pd);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        List<Map> mapList = new ArrayList<Map>();
        if (pd == null) {return mapList;}

        if (pg == null) {
            return ${objectNameLower}Mapper.getDataListPage(pd);
        } else if (pg != null) {
            return ${objectNameLower}Mapper.getDataListPage(pd,pg);
        }

        return mapList;
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        ${objectNameLower}Mapper.updateToDisableByIds(ids);
    }

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    @Override
    public void updateByDefined(PageData pd)throws Exception{
        ${objectNameLower}Mapper.updateByDefined(pd);
    }
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
    *
    * @param pageData    查询参数对象<HashMap>
    * @param isQueryAll  是否查询全部
    *   true: 无查询条件返回表全部结果集
    *   false: (false or is null)无查询条件-查询结果集返回空或
    *
    * @return
    * @throws Exception
    */
    public List<${objectName}> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<${objectName}>();
        }

        return this.dataList(pageData);
    }

    /**
    *
    * @param pd    查询参数对象PageData
    * @return      返回对象ResultModel
    * @throws Exception
    */
    public ResultModel listPage${objectName}s(PageData pd) throws Exception{
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("${modelCode}");
        if (columnList == null || columnList.size() == 0) {
            model.putCode("1");
            model.putMsg("数据库没有生成TabCol，请联系管理员！");
            return model;
        }

        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }
        Map<String, Object> titleMap = ColumnUtil.findTitleMapByColumnList(columnList);

        //设置查询排序方式
        //pd.put("orderStr", "a.cdate asc");
        String orderStr = pd.getString("orderStr");
        if (orderStr != null && orderStr.trim().length() > 0) {
            pd.put("orderStr", orderStr);
        }

        //是否需要分页 true:需要分页 false:不需要分页
        Map result = new HashMap();
        String isNeedPage = pd.getString("isNeedPage");
        Pagination pg = HttpUtils.parsePagination(pd);
        if ("false".equals(isNeedPage)) {
            pg = null;
        } else {
            result.put("pageData", pg);
        }

        List<Map> varList = this.getDataListPage(pd,pg);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);

        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);
        model.putResult(result);
        return model;
    }

    /**
    * 导出
    * @param pd    查询参数对象PageData
    * @throws Exception
    */
    public void exportExcel${objectName}s(PageData pd) throws Exception{

        List<Column> columnList = columnService.findColumnList("${modelCode}");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }
        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
        }

        //根据查询条件获取业务数据List
        String ids = pd.getString("ids");
        String queryStr = "";
        if (ids != null && ids.trim().length() > 0) {
            ids = StringUtil.stringTrimSpace(ids);
            ids = "'" + ids.replace(",", "','") + "'";
            queryStr = "id in (" + ids + ")";
        }
        pd.put("queryStr", queryStr);
        List<Map> dataList = this.getDataListPage(pd, null);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "Excel${objectName}";
        }

        //导出文件名-中文转码
        fileName = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
        ExcelUtil.excelExportByDataList(response, fileName, dataMapList);
    }


    /**
    * 导入
    * @return      返回对象ResultModel
    * @throws Exception
    */
    public ResultModel importExcel${objectName}s(MultipartFile file) throws Exception{
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



