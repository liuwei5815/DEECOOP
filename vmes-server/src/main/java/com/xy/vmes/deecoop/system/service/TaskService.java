package com.xy.vmes.deecoop.system.service;


import com.xy.vmes.entity.Task;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_task:系统执行表(任务代办) 接口类
* 创建人：陈刚 自动生成
* 创建时间：2019-01-30
*/
public interface TaskService {

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void save(Task object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void update(Task object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void updateAll(Task object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    Task selectById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    List<Task> selectByColumnMap(Map columnMap) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    void deleteTableByTask(String companyId) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    List<Task> dataList(PageData pd) throws Exception;//@
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

    /**
     *   true: 无查询条件返回表全部结果集
     *   false: (false or is null)无查询条件-查询结果集返回空或
     *
     * @return
     * @throws Exception
     */
//    List<Task> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;

//    Task findTask(PageData object) throws Exception;
//    Task findTaskById(String id) throws Exception;
//    Task findTaskByBusinessId(String businessId) throws Exception;
//    List<Task> findTaskList(PageData object) throws Exception;

//    Task warehouseInDtl2Task(PageData pd);
//    Task warehouseCheckDtl2Task(PageData pd);
//
//    Task createTaskByWarehouseOut(String businessId, String executorId, String cuser);
//    Task createTaskByWarehouseMove(String businessId, String executorId, String cuser);

    /////////////////////////////////////////////////////////////////////////////////////////////////
    /**
    * 分页查询
    * @param pd    查询参数对象PageData
    * @param pg    分页参数对象Pagination
    * @return      返回对象ResultModel
    * @throws Exception
    */
    ResultModel listPageTasks(PageData pd,Pagination pg) throws Exception;

    /**
    * 导出
    * @param pd    查询参数对象PageData
    * @param pg    分页参数对象Pagination
    * @throws Exception
    */
    void exportExcelTasks(PageData pd,Pagination pg) throws Exception;

    /**
    * 导入
    * @return      返回对象ResultModel
    * @throws Exception
    */
    ResultModel importExcelTasks(MultipartFile file) throws Exception;
}



