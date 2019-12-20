package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Message;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_message:系统公告 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2019-04-18
*/
@Mapper
@Repository
public interface MessageMapper extends BaseMapper<Message> {
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void deleteByIds(String[] ids);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    void updateToDisableByIds(String[] ids);
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    List<Message> dataList(PageData pd);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-04-18
     */
    List<Map> getDataListPage(PageData pd, Pagination pg);
    List<Map> getDataListPage(PageData pd);
    List<Map> findListMessage(PageData pd);
}


