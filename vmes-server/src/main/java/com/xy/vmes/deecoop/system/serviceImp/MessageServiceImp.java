package com.xy.vmes.deecoop.system.serviceImp;


import com.xy.vmes.deecoop.system.dao.MessageMapper;
import com.xy.vmes.entity.Message;
import com.xy.vmes.deecoop.system.service.MessageService;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Column;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.yvan.Conv;

/**
* 说明：vmes_message:系统公告 实现类
* 创建人：陈刚 自动创建
* 创建时间：2019-04-18
*/
@Service
@Transactional(readOnly = false)
public class MessageServiceImp implements MessageService {


    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ColumnService columnService;
    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2019-04-18
    */
    @Override
    public void save(Message message) throws Exception{
        message.setId(Conv.createUuid());
        message.setCdate(new Date());
        message.setUdate(new Date());
        messageMapper.insert(message);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public Message selectById(String id) throws Exception{
        return messageMapper.selectById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public List<Message> selectByColumnMap(Map columnMap) throws Exception{
        List<Message> warehouseCheckDetailList =  messageMapper.selectByMap(columnMap);
        return warehouseCheckDetailList;
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void update(Message object) throws Exception{
        object.setUdate(new Date());
        messageMapper.updateById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void updateAll(Message object) throws Exception{
        object.setUdate(new Date());
        messageMapper.updateAllColumnById(object);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void deleteById(String id) throws Exception{
        messageMapper.deleteById(id);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        messageMapper.deleteByIds(ids);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        messageMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        messageMapper.updateToDisableByIds(ids);
    }

    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public List<Message> dataList(PageData pd) throws Exception{
        return messageMapper.dataList(pd);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-12-05
     */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return messageMapper.getDataListPage(pd,pg);
    }
    public List<Map> getDataListPage(PageData pd) throws Exception {
        return messageMapper.getDataListPage(pd);
    }

    public List<Map> findListMessage(PageData pd) throws Exception {
        return messageMapper.findListMessage(pd);
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
    public List<Message> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<Message>();
        }

        return this.dataList(pageData);
    }

    /**
    *
    * @param pd    查询参数对象PageData
    * @param pg    分页参数对象Pagination
    * @return      返回对象ResultModel
    * @throws Exception
    */
    public ResultModel listPageMessages(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        ResultModel model = new ResultModel();
        Map result = new HashMap();
        List<Column> columnList = columnService.findColumnList("message");
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

//        String userType = pd.getString("userType");
//        if (Common.DICTIONARY_MAP.get("userType_admin").equals(userType)) {
//            //用户类型-超级管理员 必须是本人创建纪录
//            pd.put("currentCompanyId", null);
//        } else {
//            //企业用户
//            pd.put("cuser", null);
//        }
        pd.put("cuser", null);

        Map<String, Object> titleMap = ColumnUtil.findTitleMapByColumnList(columnList);
        List<Map> varList = this.getDataListPage(pd,pg);
        List<Map> varMapList = ColumnUtil.getVarMapList(varList,titleMap);


        result.put("hideTitles",titleMap.get("hideTitles"));
        result.put("titles",titleMap.get("titles"));
        result.put("varList",varMapList);
        result.put("pageData", pg);
        model.putResult(result);
        return model;
    }

    public ResultModel addMessage(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        Message message = (Message) HttpUtils.pageData2Entity(pageData, new Message());

        String companyId = pageData.getString("currentCompanyId");
        message.setCompanyId(companyId);

        this.save(message);
        return model;
    }

    public ResultModel updateMessage(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        String id = pageData.getString("id");
        if (id == null || id.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("id为空或空字符串！");
            return model;
        }
        String title = pageData.getString("title");
        if (title == null || title.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("标题为空或空字符串，标题为必填项不可为空！");
            return model;
        }

        String content = "";
        if (pageData.getString("content") != null && pageData.getString("content").trim().length() > 0) {
            content = pageData.getString("content").trim();
        }
        String remark = "";
        if (pageData.getString("remark") != null && pageData.getString("remark").trim().length() > 0) {
            remark = pageData.getString("remark").trim();
        }

        Message messageEidt = new Message();
        messageEidt.setId(id);
        messageEidt.setTitle(title);
        messageEidt.setContent(content);
        messageEidt.setRemark(remark);

        this.update(messageEidt);
        return model;
    }

    public ResultModel updateIsShowMessage(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();

        String id = (String)pageData.get("id");
        if (id == null || id.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("id为空或空字符串！");
            return model;
        }

        String isShow = (String)pageData.get("isShow");
        if (isShow == null || isShow.trim().length() == 0) {
            model.putCode("1");
            model.putMsg("是否发布为空或空字符串！");
            return model;
        }

        Message messageEidt = new Message();
        messageEidt.setId(id);
        messageEidt.setIsShow(isShow);

        this.update(messageEidt);
        return model;
    }

    public ResultModel deleteMessage(PageData pageData)throws Exception {
        ResultModel model = new ResultModel();

        String ids = pageData.getString("ids");
        if(StringUtils.isEmpty(ids)){
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }

        ids = StringUtil.stringTrimSpace(ids);
        String[] id_arry = ids.split(",");
        if (id_arry.length == 0) {
            model.putCode("1");
            model.putMsg("未勾选删除记录，请重新选择！");
            return model;
        }

        this.deleteByIds(id_arry);
        return model;
    }


}



