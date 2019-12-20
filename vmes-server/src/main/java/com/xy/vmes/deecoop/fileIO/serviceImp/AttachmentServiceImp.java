package com.xy.vmes.deecoop.fileIO.serviceImp;


import com.yvan.common.util.Common;
import com.xy.vmes.deecoop.fileIO.dao.AttachmentMapper;
import com.xy.vmes.entity.Attachment;
import com.xy.vmes.deecoop.fileIO.service.AttachmentService;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Column;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.*;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_attachment:附件管理 实现类
* 创建人：刘威 自动创建
* 创建时间：2019-05-13
*/
@Service
@Transactional(readOnly = false)
public class AttachmentServiceImp implements AttachmentService {


    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private ColumnService columnService;
    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void save(Attachment attachment) throws Exception{
        attachment.setId(Conv.createUuid());
        attachment.setCdate(new Date());
        attachment.setUdate(new Date());
        attachmentMapper.insert(attachment);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void update(Attachment attachment) throws Exception{
        attachment.setUdate(new Date());
        attachmentMapper.updateById(attachment);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void updateAll(Attachment attachment) throws Exception{
        attachment.setUdate(new Date());
        attachmentMapper.updateAllColumnById(attachment);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    //@Cacheable(cacheNames = "attachment", key = "''+#id")
    public Attachment selectById(String id) throws Exception{
        return attachmentMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void deleteById(String id) throws Exception{
        attachmentMapper.deleteById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        attachmentMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Attachment> dataListPage(PageData pd,Pagination pg) throws Exception{
        return attachmentMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Attachment> dataList(PageData pd) throws Exception{
        return attachmentMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return attachmentMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return attachmentMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        attachmentMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Attachment> selectByColumnMap(Map columnMap) throws Exception{
    List<Attachment> attachmentList =  attachmentMapper.selectByMap(columnMap);
        return attachmentList;
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return attachmentMapper.getColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return attachmentMapper.getDataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        List<Map> mapList = new ArrayList<Map>();
        if (pd == null) {return mapList;}

        if (pg == null) {
            return attachmentMapper.getDataListPage(pd);
        } else if (pg != null) {
            return attachmentMapper.getDataListPage(pd,pg);
        }

        return mapList;
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        attachmentMapper.updateToDisableByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-05-13
    */
    @Override
    public void updateByDefined(PageData pd)throws Exception{
        attachmentMapper.updateByDefined(pd);
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
    public List<Attachment> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<Attachment>();
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
    public ResultModel listPageAttachments(PageData pd,Pagination pg) throws Exception{
        ResultModel model = new ResultModel();

        List<Column> columnList = columnService.findColumnList("attachment");
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
    * @param pg    分页参数对象Pagination
    * @throws Exception
    */
    public void exportExcelAttachments(PageData pd,Pagination pg) throws Exception{

        List<Column> columnList = columnService.findColumnList("attachment");
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
            fileName = "ExcelAttachment";
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
    public ResultModel importExcelAttachments(MultipartFile file) throws Exception{
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

    @Override
    public int deleteFile(String url) throws Exception {
        try { //上传文件的绝对路径
            String absolutePath = "";
            String os = System.getProperty("os.name");
            if(os != null && os.indexOf("Windows") >= 0) {
                String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
                if(path.indexOf(":") != 1){
                    path = File.separator + path;
                }
                //本地window环境:文件根路径
                absolutePath = path+"vmes-file/";
            } else {
                //真实linux环境:文件根路径
                absolutePath = Common.SYS_LINUX_FILE_ROOT;
            }
            absolutePath = absolutePath + url;
            FileUtils.deleteFile(absolutePath);
            return 1;
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public String uploadFile(String fileDir, MultipartFile file) throws Exception {
        //上传文件的绝对路径
        String absolutePath = "";

        String os = System.getProperty("os.name");
        if(os != null && os.indexOf("Windows") >= 0) {
            String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../../../").replaceAll("file:/", "").replaceAll("%20", " ").trim();
            if(path.indexOf(":") != 1){
                path = File.separator + path;
            }
            //本地window环境:文件根路径
            absolutePath = path+"vmes-file/";
        } else {
            //真实linux环境:文件根路径
            absolutePath = Common.SYS_LINUX_FILE_ROOT;
        }

        String relativePath = "fileUpload/"+fileDir;//上传文件的相对路径
//        String[] includesuffixs = new String[3];//上传的文件类型，包括jpg、png、jpeg
//        includesuffixs[0]="jpg";
//        includesuffixs[1]="png";
//        includesuffixs[2]="jpeg";
        String fileUrl = FileUploadUtils.uploadFile(file,absolutePath,relativePath, Long.valueOf(5),null);

        return fileUrl;
    }
}



