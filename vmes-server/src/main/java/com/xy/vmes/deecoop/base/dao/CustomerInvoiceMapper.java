package com.xy.vmes.deecoop.base.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.CustomerInvoice;
import com.yvan.PageData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_customer_invoice:客户开票信息 Mapper.java
* 创建人：陈刚 自动创建
* 创建时间：2019-01-09
*/
@Mapper
@Repository
public interface CustomerInvoiceMapper extends BaseMapper<CustomerInvoice> {

	/**
	* 创建人：陈刚 自动创建，禁止修改
	* 创建时间：2019-01-09
	*/
	void deleteByIds(String[] ids);

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2019-01-09
    */
    void updateToDisableByIds(String[] ids);

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-09
     */
    List<CustomerInvoice> dataList(PageData pd);

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2019-01-09
     */
    List<Map> getDataListPage(PageData pd, Pagination pg);

    void updateDefaultByCustomerId (PageData pd);
}


