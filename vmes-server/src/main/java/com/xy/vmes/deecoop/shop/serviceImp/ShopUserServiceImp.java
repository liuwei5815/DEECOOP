package com.xy.vmes.deecoop.shop.serviceImp;


import com.xy.vmes.deecoop.shop.dao.ShopUserMapper;
import com.xy.vmes.entity.ShopUser;
import com.xy.vmes.deecoop.shop.service.ShopUserService;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.ColumnUtil;
import com.xy.vmes.common.util.StringUtil;
import com.xy.vmes.entity.Column;
import com.xy.vmes.deecoop.system.service.ColumnService;
import com.yvan.ExcelUtil;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.cache.RedisClient;
import com.yvan.common.util.Common;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

import com.yvan.Conv;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
* 说明：vmes_shop_user:商城平台用户管理 实现类
* 创建人：刘威 自动创建
* 创建时间：2019-12-23
*/
@Service
@Transactional(readOnly = false)
public class ShopUserServiceImp implements ShopUserService {


    @Autowired
    private ShopUserMapper shopUserMapper;
    @Autowired
    private ColumnService columnService;
    @Autowired
    RedisClient redisClient;
    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void save(ShopUser shopUser) throws Exception{
        shopUser.setId(Conv.createUuid());
        shopUser.setCdate(new Date());
        shopUser.setUdate(new Date());
        shopUserMapper.insert(shopUser);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void update(ShopUser shopUser) throws Exception{
        shopUser.setUdate(new Date());
        shopUserMapper.updateById(shopUser);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void updateAll(ShopUser shopUser) throws Exception{
        shopUser.setUdate(new Date());
        shopUserMapper.updateAllColumnById(shopUser);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    //@Cacheable(cacheNames = "shopUser", key = "''+#id")
    public ShopUser selectById(String id) throws Exception{
        return shopUserMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void deleteById(String id) throws Exception{
        shopUserMapper.deleteById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void deleteByIds(String[] ids) throws Exception{
        shopUserMapper.deleteByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<ShopUser> dataListPage(PageData pd,Pagination pg) throws Exception{
        return shopUserMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<ShopUser> dataList(PageData pd) throws Exception{
        return shopUserMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return shopUserMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return shopUserMapper.findDataList(pd);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        shopUserMapper.deleteByMap(columnMap);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<ShopUser> selectByColumnMap(Map columnMap) throws Exception{
    List<ShopUser> shopUserList =  shopUserMapper.selectByMap(columnMap);
        return shopUserList;
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return shopUserMapper.getColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return shopUserMapper.getDataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public List<Map> getDataListPage(PageData pd,Pagination pg) throws Exception{
        List<Map> mapList = new ArrayList<Map>();
        if (pd == null) {return mapList;}

        if (pg == null) {
            return shopUserMapper.getDataListPage(pd);
        } else if (pg != null) {
            return shopUserMapper.getDataListPage(pd,pg);
        }

        return mapList;
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void updateToDisableByIds(String[] ids)throws Exception{
        shopUserMapper.updateToDisableByIds(ids);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2019-12-23
    */
    @Override
    public void updateByDefined(PageData pd)throws Exception{
        shopUserMapper.updateByDefined(pd);
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
    public List<ShopUser> findDataList(PageData pageData, Boolean isQueryAll) throws Exception {
        int pageDataSize = 0;
        if (pageData != null && pageData.size() > 0) {
            pageDataSize = pageData.size();
        }

        if ((isQueryAll == null || true != isQueryAll.booleanValue()) && pageDataSize == 0) {
            return new ArrayList<ShopUser>();
        }

        return this.dataList(pageData);
    }

    /**
    *
    * @param pd    查询参数对象PageData
    * @return      返回对象ResultModel
    * @throws Exception
    */
    public ResultModel listPageShopUsers(PageData pd) throws Exception{
        ResultModel model = new ResultModel();
        List<Column> columnList = columnService.findColumnList("ShopUser");
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
        Pagination pg = HttpUtils.parsePagination(pd);
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
    * @throws Exception
    */
    public void exportExcelShopUsers(PageData pd) throws Exception{

        List<Column> columnList = columnService.findColumnList("ShopUser");
        if (columnList == null || columnList.size() == 0) {
            throw new RestException("1","数据库没有生成TabCol，请联系管理员！");
        }
        //获取指定栏位字符串-重新调整List<Column>
        String fieldCode = pd.getString("fieldCode");
        if (fieldCode != null && fieldCode.trim().length() > 0) {
            columnList = columnService.modifyColumnByFieldCode(fieldCode, columnList);
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
        List<Map> dataList = this.getDataListPage(pd, null);

        //查询数据转换成Excel导出数据
        List<LinkedHashMap<String, String>> dataMapList = ColumnUtil.modifyDataList(columnList, dataList);
        HttpServletResponse response = HttpUtils.currentResponse();

        //查询数据-Excel文件导出
        String fileName = pd.getString("fileName");
        if (fileName == null || fileName.trim().length() == 0) {
            fileName = "ExcelShopUser";
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
    public ResultModel importExcelShopUsers(MultipartFile file) throws Exception{
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
    public ResultModel login(PageData pd) throws Exception {
        ResultModel model = new ResultModel();
        String type = pd.get("type").toString();
        if(StringUtils.isEmpty(type)){
            model.putCode(Integer.valueOf(1));
            model.putMsg("登录方式不能为空！");
            return model;
        }

        String code = pd.get("code").toString();
        if(StringUtils.isEmpty(code)){
            model.putCode(Integer.valueOf(1));
            model.putMsg("验证码不能为空！");
            return model;
        }
        String username = pd.get("username").toString();
        if(StringUtils.isEmpty(username)){
            model.putCode(Integer.valueOf(1));
            model.putMsg("用户账号/手机号不能为空！");
            return model;
        }
        String password = pd.get("password").toString();

        if("password".equals(type)){
            //1、密码不能为空
            if(StringUtils.isEmpty(password)){
                model.putCode(Integer.valueOf(1));
                model.putMsg("密码不能为空！");
                return model;
            }
            //2、判断图片验证码是否过期
            String currentCode = pd.get("code").toString().trim();
            String oldCode = redisClient.get(pd.get("username").toString().trim());
            if (!currentCode.equalsIgnoreCase(oldCode)) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("验证码输入错误或已经过期，请重新输入验证码！");
                return model;
            }
            //3、判断用户账号、手机号是否存在

            //3、校验密码是否正确

            //4、redis中创建sessionID，并且返回用户信息

        }else if("sms".equals(type)){
            //1、判断短信验证码是否过期
            String currentCode = pd.get("code").toString().trim();
            String oldCode = redisClient.get(pd.get("username").toString().trim());
            if (!currentCode.equalsIgnoreCase(oldCode)) {
                model.putCode(Integer.valueOf(1));
                model.putMsg("验证码输入错误或已经过期，请重新输入验证码！");
                return model;
            }
            //2、判断手机号是否存在


            //1）、不存在，自动注册账号

            //2）、存在，则登录成功

            //3、redis中创建sessionID，并且返回用户信息




        }


        return model;
    }

    @Override
    public ResultModel logout(PageData pd) throws Exception {
        return null;
    }

    @Override
    public ResultModel changePassword(PageData pd) throws Exception {
        return null;
    }



    @Override
    public ResultModel loginCode(PageData pageData) throws Exception {
        ResultModel model = new ResultModel();
        String code = this.drawImg(new ByteArrayOutputStream());

        //Redis-验证码-缓存1分钟(60 * 1000)
        String key = Conv.createUuid() + ":" + Common.REDIS_SECURITY_CODE;
        redisClient.setWithExpireTime(key, code, Common.REDIS_SECURITYCODE_LONG);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("code", code);
        dataMap.put("key", key);
        model.putResult(dataMap);
        return model;
    }

    private String drawImg(ByteArrayOutputStream output){
        String code = "";
        for(int i=0; i<4; i++){
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int)x, (int)baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return code;
    }


    private char randomChar(){
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }
}



