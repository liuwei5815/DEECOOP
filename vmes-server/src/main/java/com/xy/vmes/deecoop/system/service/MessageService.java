package com.xy.vmes.deecoop.system.service;

import com.xy.vmes.entity.Message;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_message:系统公告 接口类
* 创建人：陈刚 自动生成
* 创建时间：2019-04-18
*/
public interface MessageService {

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2019-04-18
    */
    void save(Message message) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void update(Message object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void updateAll(Message object) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void updateToDisableByIds(String[] ids)throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void deleteById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void deleteByIds(String[] ids) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void deleteByColumnMap(Map columnMap) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    Message selectById(String id) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    List<Message> selectByColumnMap(Map columnMap) throws Exception;

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    List<Message> dataList(PageData pd) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;
//    List<Map> getDataListPage(PageData pd) throws Exception;
    List<Map> findListMessage(PageData pd) throws Exception;

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
//    List<Message> findDataList(PageData pageData, Boolean isQueryAll) throws Exception;

    ResultModel listPageMessages(PageData pd, Pagination pg) throws Exception;
    ResultModel addMessage(PageData pd) throws Exception;
    ResultModel updateMessage(PageData pd) throws Exception;
    ResultModel updateIsShowMessage(PageData pageData) throws Exception;
    ResultModel deleteMessage(PageData pd)throws Exception;
}



