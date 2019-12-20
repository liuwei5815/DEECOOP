package com.xy.vmes.deecoop.system.serviceImp;


import com.xy.vmes.deecoop.system.dao.TaskMapper;
import com.xy.vmes.entity.*;
import com.xy.vmes.deecoop.system.service.TaskService;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.Conv;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_task:系统执行表(任务代办) 实现类
* 创建人：陈刚 自动创建
* 创建时间：2019-01-30
*/
@Service
@Transactional(readOnly = false)
public class TaskServiceImp implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private ColumnService columnService;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void save(Task object) throws Exception{
        object.setId(Conv.createUuid());
        object.setCdate(new Date());
        object.setUdate(new Date());
        taskMapper.insert(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public Task selectById(String id) throws Exception{
        return taskMapper.selectById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public List<Task> selectByColumnMap(Map columnMap) throws Exception{
        List<Task> warehouseCheckDetailList =  taskMapper.selectByMap(columnMap);
        return warehouseCheckDetailList;
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void update(Task object) throws Exception{
        object.setUdate(new Date());
        taskMapper.updateById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void updateAll(Task object) throws Exception{
        object.setUdate(new Date());
        taskMapper.updateAllColumnById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void deleteById(String id) throws Exception{
        taskMapper.deleteById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        taskMapper.deleteByIds(ids);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        taskMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        taskMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public void deleteTableByTask(String companyId) throws Exception {
        Map<String, String> columnMap = new HashMap<String, String>();
        columnMap.put("company_id", companyId);
        this.deleteByColumnMap(columnMap);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public List<Task> dataList(PageData pd) throws Exception{
        return taskMapper.dataList(pd);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return taskMapper.getDataListPage(pd,pg);
    }

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
    public List<Task> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<Task>();
        }

        return this.dataList(pageData);
    }

    public Task findTask(PageData object) throws Exception {
        List<Task> objectList = this.findTaskList(object);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }

        return null;
    }
    public Task findTaskById(String id) throws Exception {
        if (id == null || id.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("id", id);

        return this.findTask(findMap);
    }
    public Task findTaskByBusinessId(String businessId) throws Exception {
        if (businessId == null || businessId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("businessId", businessId);

        return this.findTask(findMap);
    }

    public List<Task> findTaskList(PageData object) throws Exception {
        return this.findDataList(object, null);
    }

//    public Task warehouseInDtl2Task(PageData pd) {
//        WarehouseInDetailEntity detailObj = pd.get("detail")==null?null:(WarehouseInDetailEntity)pd.get("detail");
//        Task taskObj = pd.get("task")==null?null:(Task)pd.get("task");
//        if (taskObj == null) {taskObj = new Task();}
//        if (detailObj == null) {return taskObj;}
//
//        taskObj.setBusinessId(detailObj.getId());
//        taskObj.setExecutorId(detailObj.getCuser());
//        taskObj.setTaskName(detailObj.getTaskName());
//        taskObj.setCompanyId(detailObj.getCompanyId());
//        //type:类型(1:入库 2:出库 3:盘点 4:移库)
//        taskObj.setType("1");
//        //state:执行状态(0:待执行 1:已完成 -1:已取消)
//        taskObj.setState("0");
//        taskObj.setCuser(detailObj.getCuser());
//
//        return taskObj;
//    }

    public Task createTaskByWarehouseOut(String businessId, String executorId, String cuser) {
        Task taskObj = new Task();

        taskObj.setBusinessId(businessId);
        taskObj.setExecutorId(executorId);
        taskObj.setCuser(cuser);
        //type:类型(1:入库 2:出库 3:盘点 4:移库)
        taskObj.setType("2");
        //state:执行状态(0:待执行 1:已完成 -1:已取消)
        taskObj.setState("0");

        return taskObj;
    }

    public Task createTaskByWarehouseMove(String businessId, String executorId, String cuser) {
        Task taskObj = new Task();

        taskObj.setBusinessId(businessId);
        taskObj.setExecutorId(executorId);
        taskObj.setCuser(cuser);
        //type:类型(1:入库 2:出库 3:盘点 4:移库)
        taskObj.setType("4");
        //state:执行状态(0:待执行 1:已完成 -1:已取消)
        taskObj.setState("0");

        return taskObj;
    }

//    public Task warehouseCheckDtl2Task(PageData pd) {
//
//        WarehouseCheckDetailEntity detailObj = pd.get("detail")==null?null:(WarehouseCheckDetailEntity)pd.get("detail");
//        Task taskObj = pd.get("task")==null?null:(Task)pd.get("task");
//        if (taskObj == null) {taskObj = new Task();}
//        if (detailObj == null) {return taskObj;}
//
//        taskObj.setBusinessId(detailObj.getId());
//        taskObj.setExecutorId(detailObj.getCuser());
//        taskObj.setTaskName(detailObj.getTaskName());
//        taskObj.setCompanyId(detailObj.getCompanyId());
//        //type:类型(1:入库 2:出库 3:盘点 4:移库)
//        taskObj.setType("3");
//        //state:执行状态(0:待执行 1:已完成 -1:已取消)
//        taskObj.setState("0");
//        taskObj.setCuser(detailObj.getCuser());
//
//        return taskObj;
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
    *
    * @param pd    查询参数对象PageData
    * @param pg    分页参数对象Pagination
    * @return      返回对象ResultModel
    * @throws Exception
    */
    public ResultModel listPageTasks(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();

        List<Column> columnList = columnService.findColumnList("task");
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
        Map result = new HashMap();
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

    /**
    * 导出
    * @param pd    查询参数对象PageData
    * @param pg    分页参数对象Pagination
    * @throws Exception
    */
    public void exportExcelTasks(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        List<Column> columnList = columnService.findColumnList("task");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
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
        pg.setSize(100000);
        List<Map> dataList = this.getDataListPage(pd, pg);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelTask";
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
    public ResultModel importExcelTasks(MultipartFile file) throws Exception{
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



