package com.xy.vmes.deecoop.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Task;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_task:系统执行表(任务代办) Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2019-01-30
*/
@Mapper
@Repository
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void deleteByIds(String[] ids);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    void updateToDisableByIds(String[] ids);

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    List<Task> dataList(PageData pd);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-30
     */
    List<Map> getDataListPage(PageData pd, Pagination pg);
}


