package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.Employee;
import com.xy.vmes.entity.User;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_user:系统用户表 接口类
* 创建人：刘威 自动生成
* 创建时间：2018-07-26
*/
public interface UserService {


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void save(User user) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void update(User user) throws Exception;

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-26
     */
    void updateAll(User user) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    void deleteById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    User selectById(String id) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<User> dataListPage(PageData pd,Pagination pg) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<User> dataList(PageData pd) throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<LinkedHashMap> findColumnList() throws Exception;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-26
    */
    List<Map> findDataList(PageData pd) throws Exception;


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
//    /**
//     * 生成用户编码
//     *
//     * 创建人：陈刚
//     * 创建时间：2018-07-26
//     *
//     * @param companyID  公司ID-组织架构ID
//     * @return
//     */
//    String createCoder(String companyID);

    /**
     * 创建人：刘威
     * 创建时间：2018-07-23
     */
//    void deleteByIds(String[] ids) throws Exception;

    /**
     * 判断用户手机号是否存在
     *
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    boolean isExistMobile(PageData pd) throws Exception;//@

    /**
     * 批量修改用户信息为禁用状态
     *
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
//    void updateToDisableByIds(String[] ids) throws Exception;

    /**
     * 创建人：刘威
     * 创建时间：2018-08-03
     */
//    void updateToDisableByEmployIds(String[] ids) throws Exception;

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
//    List<LinkedHashMap> getColumnList() throws Exception;

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    List<Map> getDataList(PageData pd) throws Exception;

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    List<Map> getDataListPage(PageData pd, Pagination pg) throws Exception;

    /**
     * 创建人：刘威
     * 创建时间：2018-08-03
     */
    ResultModel createUserAndRole(PageData pd, Employee employee ) throws Exception;//@

    User findUser(PageData object);//@
    User findUserById(String id);//@
    Boolean isExistUserByUserCode(String id, String userCode);//@
//    boolean isExistByMobile(String id, String mobile);

    List<User> findUserList(PageData object);

    /**
     * 获取企业管理员
     *
     * @param companyID  企业id
     * @return
     */
    User findCompanyAdmin(String companyID);//@

    /**
     * 批量修改(企业管理员)为禁用状态
     *
     * 创建人：陈刚
     * 创建时间：2018-08-06
     */
//    void updateDisableByCompanyIds(String[] companyIds);

    /**
     * 创建人：刘威
     * 创建时间：2018-08-03
     */
    ResultModel selectCountUserNum(PageData pd) throws Exception;

    ResultModel addUser(PageData pd) throws Exception;

    ResultModel updateUser(PageData pd) throws Exception;
    ResultModel updateUserByPassword(PageData pd) throws Exception;

    ResultModel updatePasswords(PageData pd) throws Exception;

    ResultModel updateEmployeeUserUnbind(PageData pd) throws Exception;

    ResultModel updateEmployeeUserBind(PageData pd) throws Exception;

    ResultModel deleteUsers(PageData pd) throws Exception;

    ResultModel listPageUsers(PageData pd) throws Exception;

    void exportExcelUsers(PageData pd,Pagination pg) throws Exception;
    ResultModel importExcelUser(MultipartFile file) throws Exception;

//    Integer findUserCountByCompanyId(String companyId);
}



