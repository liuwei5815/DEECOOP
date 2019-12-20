package com.xy.vmes.deecoop.system.service;

import com.xy.vmes.entity.Coderule;
import com.xy.vmes.entity.CoderuleEntity;
import com.yvan.PageData;

import java.util.List;
import java.util.Map;

/**
* 说明：vmes_coderule:系统编码规则表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-07-26
*/
public interface CoderuleService {


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void save(Coderule coderule) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void update(Coderule coderule) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    void updateAll(Coderule coderule) throws Exception;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<Coderule> dataList(PageData pd) throws Exception;
    /**
     * 创建人：陈刚
     * 创建时间：2018-07-26
     */
    List<Map<String, Object>> findDataList(PageData pd) throws Exception;

    /**
     * 获取最新系统流水号
     * @param companyId  企业id
     * @param tableName  业务名称(表名)
     * @return
     */
//    Coderule findNewCodeByCoderule(String companyId, String tableName, String type);

    /**
     * 获取最新系统流水号
     * @param companyId  企业id
     * @param tableName  业务名称(表名)
     * @param dayString  日期(yyyyMMdd)
     * @return
     */
//    Coderule findNewCodeByCoderule(String companyId, String tableName, String type, String dayString);

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-26
     */
//    Integer updateCoderule(PageData pd) throws Exception;

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
//    void deleteByColumnMap(Map columnMap) throws Exception;

    ////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取业务编号-根据通用编码规则(企业编号+前缀字符+日期字符+流水号)
     *
     * 参数说明:
     *   <SysCoderuleEntity>object
     *     (不可为空)SysCoderuleEntity.tableName  业务名称(表名)
     *     (不可为空)SysCoderuleEntity.companyID  公司ID
     *     (不可为空)SysCoderuleEntity.firstName  第一个编码名称
     *     (不可为空)SysCoderuleEntity.type       类别(表名:=produce_workflow 有意义)
     *     (允许为空)SysCoderuleEntity.separator  分隔符
     *     (允许为空)SysCoderuleEntity.filling    填充字符
     *     (允许为空)SysCoderuleEntity.length     指定位数
     *     (允许为空)SysCoderuleEntity.prefix     前缀字符
     *     (允许为空)SysCoderuleEntity.dateFormat 日期格式
     *
     *     (允许为空)SysCoderuleEntity.isNeedCompany
     *     (允许为空)SysCoderuleEntity.isNeedPrefix
     *     (允许为空)SysCoderuleEntity.isNeedDate
     *     (允许为空)SysCoderuleEntity.isNeedCode
     *
     * @param object  生成编码-通用编码规则需要的参数对象
     * @return
     */
    String findCoderule(CoderuleEntity object);//@

    /**
     * 获取业务编号-根据通用编码规则(企业编号+前缀字符+日期字符+流水号)
     * 按照当天日期(yyyy-MM-dd)流水号递增
     *
     * 参数说明:
     *   <SysCoderuleEntity>object
     *     (不可为空)SysCoderuleEntity.tableName  业务名称(表名)
     *     (不可为空)SysCoderuleEntity.companyID  公司ID
     *     (不可为空)SysCoderuleEntity.firstName  第一个编码名称
     *     (不可为空)SysCoderuleEntity.type       类别(表名:=produce_workflow 有意义)
     *     (允许为空)SysCoderuleEntity.separator  分隔符
     *     (允许为空)SysCoderuleEntity.filling    填充字符
     *     (允许为空)SysCoderuleEntity.length     指定位数
     *     (允许为空)SysCoderuleEntity.prefix     前缀字符
     *     (允许为空)SysCoderuleEntity.dateFormat 日期格式
     *
     *     (允许为空)SysCoderuleEntity.isNeedCompany
     *     (允许为空)SysCoderuleEntity.isNeedPrefix
     *     (允许为空)SysCoderuleEntity.isNeedDate
     *     (允许为空)SysCoderuleEntity.isNeedCode
     *
     * @param object  生成编码-通用编码规则需要的参数对象
     * @return
     */
    String findCoderuleByDate(CoderuleEntity object);

    ////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取流水号：前缀+6位流水号，如P000001
     * 创建人：刘威
     * @param companyID   公司ID
     * @param tableName   表名
     * @param prefix      前缀名称
     * @return
     */
    String createCoder(String companyID,String tableName,String prefix);

    /**
     * 获取流水号：企业编码+6位流水号，如DEECOOP000001
     * 创建人：刘威
     * @param companyID   公司ID
     * @param tableName   表名
     * @return
     */
    String createCoder(String companyID,String tableName);//@

    /**
     * 获取流水号：前缀+日期（yyyyMMdd）+5位流水号，如P20180808001
     * 创建人：刘威
     * @param companyID   公司ID
     * @param tableName   表名
     * @param dateFormat  日期格式
     * @param prefix      前缀名称
     * @return
     */
    String createCoderCdateByDate(String companyID, String tableName, String dateFormat, String prefix);

    /**
     * 获取流水号：前缀+短日期(yyyy 后2位) +5位流水号，如PC18080800001
     * 例如: 短日期(2019) 短日期(19)
     * @param companyID
     * @param tableName
     * @param prefix
     * @return
     */
    String createCoderCdateOnShortYearByDate(String companyID, String tableName, String prefix);

    /**
     * 获取流水号：前缀+短日期(yyyy 后2位) 指定流水位数
     * 例如: 短日期(2019) 短日期(19)
     * @param companyID
     * @param tableName
     * @param prefix
     * @param length
     * @return
     */
    String createCoderCdateOnShortYearByDate(String companyID, String tableName, String prefix, Integer length);
}



