package ${classPath};


import com.xy.vmes.entity.${objectName};
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：${TITLE} 接口类
* 创建人：${author} 自动生成
* 创建时间：${nowDate?string("yyyy-MM-dd")}
*/
public interface ${objectName}Service {


    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void save(${objectName} ${objectNameLower}) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void update(${objectName} ${objectNameLower}) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void updateAll(${objectName} ${objectNameLower}) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void deleteByIds(String[] ids) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    ${objectName} selectById(String id) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<${objectName}> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<${objectName}> dataList(PageData pd) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<Map> findDataList(PageData pd) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<${objectName}> selectByColumnMap(Map columnMap) throws Exception;


    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<LinkedHashMap> getColumnList() throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void updateToDisableByIds(String[] ids)throws Exception;

    /**
    * 创建人：${author} 自动创建，禁止修改
    * 创建时间：${nowDate?string("yyyy-MM-dd")}
    */
    void updateByDefined(PageData pd)throws Exception;

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
    List<${objectName}> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;

    /**
    * 分页查询
    * @param pd    查询参数对象PageData
    * @return      返回对象ResultModel
    * @throws Exception
    */
    ResultModel listPage${objectName}s(PageData pd) throws Exception;

    /**
    * 导出
    * @param pd    查询参数对象PageData
    * @throws Exception
    */
    void exportExcel${objectName}s(PageData pd) throws Exception;

    /**
    * 导入
    * @return      返回对象ResultModel
    * @throws Exception
    */
    ResultModel importExcel${objectName}s(MultipartFile file) throws Exception;
}



