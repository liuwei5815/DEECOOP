package com.xy.vmes.deecoop.system.controller;

import com.xy.vmes.deecoop.system.service.UserEmployeeService;
import com.xy.vmes.deecoop.system.service.UserLoginService;
import com.xy.vmes.deecoop.system.service.UserService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.yvan.RedisUtils;
import com.yvan.cache.RedisClient;
import com.yvan.platform.RestException;
import com.yvan.springmvc.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;


/**
 * 说明：用户登录退出系统 Controller
 * 创建人：自动创建
 * 创建时间：2018-07-20
 */
@RestController
@Slf4j
public class UserLoginController {
    private Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserEmployeeService userEmployService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    RedisClient redisClient;

    /**
     * 系统用户登录(web,手机)端-通用登录接口
     * 登录类型(loginType)
     *   手机端:app
     *   web端:web
     * Redis缓存Key:(uuid:用户ID:deecoop)
     *
     * 1. (用户账号, 密码MD5)-查询(v_vmes_user_employee)
     * 2. (用户账号, 密码MD5)-系统中存在
     * 3. 生成新的Redis会话(生成新的uuid)
     * 4. 生成新的uuid-与Redis缓存中的Key比较
     * 5. 生成新的uuid-与Redis缓存中的历史(uuid)-不同(在其他终端登录)
     * 6. 清空历史Redis缓存Key(系统用户ID)z字符串匹配
     * 7. 生成新的uuid-生成新的Redis缓存数据
     * 8. 缓存业务数据
     *
     * 用户信息(userID,userCode,companyID,)
     * 员工信息(employID,employName,postID(主岗),deptID,)
     * 当前用户角色(userRoles-角色ID','分隔的字符串)
     * 当前用户菜单树Json(userMenuTree-当前用户所有角色关联的模块-生成菜单树Json字符串)
     *
     * 返回值 <ResultModel>
     *     ResultModel.code
     *     ResultModel.msg
     *     ResultModel.result
     *     ResultModel.sessionID(Redis缓存Key:uuid:用户ID:企业ID:deecoop:userLoginMap)

     * (手机端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:app)
     * (web端)Redis缓存Key:   (uuid:用户ID:企业ID:deecoop:userLoginMap:web)
     * Redis缓存Value: JsonString--Map<String, String>
     *     userID:    用户ID
     *     userCode:  系统账号
     *     userName:  姓名
     *     companyId: 企业ID
     *     deptId:    部门ID
     *     postId:    岗位ID
     *     roleIds:   角色ID
     *     userMenu:  用户菜单树
     *     userMain:  用户主页
     *
     * 创建人：陈刚
     * 创建时间：2018-07-20
     */
    @PostMapping("/system/userLogin/loginIn")
    public ResultModel loginIn() throws Exception {
        logger.info("################userLogin/loginIn 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = userLoginService.loginIn(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/loginIn 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 1. 生成4位验证码
     * 2. 验证码-Redis缓存Key:(securityCode)
     * 3. 验证码-返回页面
     * 返回值 <ResultModel>
     *     ResultModel.code
     *     ResultModel.msg
     *     ResultModel.result<Map<String, Object>>
     *         securityCode:验证码
     *         securityCodeKey:(uuid:securityCode)
     *
     * 创建人：陈刚
     * 创建时间：2018-07-24
     */
    @PostMapping("/system/userLogin/createSecurityCode")
    public ResultModel createSecurityCode()  throws Exception {
        logger.info("################userLogin/createSecurityCode 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = userLoginService.createSecurityCode(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/createSecurityCode 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 修改系统用户登录密码
     *
     * 创建人：陈刚
     * 创建时间：2018-07-25
     *
     * @return
     */
    @PostMapping("/system/userLogin/changePassWord")
    @Transactional(rollbackFor=Exception.class)
    public ResultModel changePassWord() throws Exception {
        logger.info("################userLogin/changePassWord 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = userLoginService.changePassWord(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/changePassWord 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 系统用户找回密码
     *
     * 创建人：陈刚
     * 创建时间：2018-07-25
     * @return
     */
    @PostMapping("/system/userLogin/findPassWord")
    public ResultModel findPassWord() throws Exception {
        logger.info("################userLogin/findPassWord 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = userLoginService.findPassWord(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/findPassWord 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    /**
     * 系统用户退出系统
     *
     * 创建人：陈刚
     * 创建时间：2018-07-25
     * @return
     */
    @PostMapping("/system/userLogin/loginOut")
    public ResultModel loginOut() throws Exception {
        logger.info("################userLogin/loginOut 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        PageData pageData = HttpUtils.parsePageData();
        ResultModel model = userLoginService.loginOut(pageData);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/loginOut 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }

    ////////////////////////////////////////////////////////////////////////////
    //测试代码

    /**
     * 获取全部含有(userID)的Redis缓存key
     * Redis缓存Key:(uuid:用户ID:企业ID:deecoop:userLoginMap)
     * @return
     */
//    @GetMapping("/system/userLogin/test_findAllRedisKeyByUserID")
//    public ResultModel test_findAllRedisKeyByUserID() {
//        logger.info("################userLogin/test_findAllRedisKeyByUserID 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        ResultModel model = new ResultModel();
//        PageData pageData = HttpUtils.parsePageData();
//
//        //1. 非空判断
//        if (pageData == null || pageData.size() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("参数错误：用户登录参数(pageData)为空！");
//            return model;
//        }
//
//        String userID = (String)pageData.get("userID");
//        if (userID == null || userID.trim().length() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("userID为空或空字符串！");
//            return model;
//        }
//
//        Jedis jedis = redisClient.getJedisPool().getResource();
//        if (jedis == null) {
//            throw new RestException("", "Redis 缓存错误(jedis is null)，请与管理员联系！");
//        }
//
//        StringBuffer msgBuf = new StringBuffer();
//        try {
//            String strTemp = ":" + userID;
//            Set<String> keySet = jedis.keys("*" + strTemp + "*");
//            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
//                String key = (String) iterator.next();
//                msgBuf.append(key);
//                msgBuf.append("--");
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//
//        model.putMsg(msgBuf.toString());
//        Long endTime = System.currentTimeMillis();
//        logger.info("################userLogin/test_findAllRedisKeyByUserID 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }

    /**
     * 获取全部含有(uuid)的Redis缓存key
     * Redis缓存Key:(uuid:用户ID:企业ID:deecoop:userLoginMap)
     * @return
     */
//    @GetMapping("/system/userLogin/test_findAllRedisKeyByUuid")
//    public ResultModel test_findAllRedisKeyByUuid() {
//        logger.info("################userLogin/test_findAllRedisKeyByUuid 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        ResultModel model = new ResultModel();
//        PageData pageData = HttpUtils.parsePageData();
//
//        //1. 非空判断
//        if (pageData == null || pageData.size() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("参数错误：用户登录参数(pageData)为空！");
//            return model;
//        }
//
//        String uuid = (String)pageData.get("uuid");
//        if (uuid == null || uuid.trim().length() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("uuid为空或空字符串！");
//            return model;
//        }
//
//        Jedis jedis = redisClient.getJedisPool().getResource();
//        if (jedis == null) {
//            throw new RestException("", "Redis 缓存错误(jedis is null)，请与管理员联系！");
//        }
//
//        StringBuffer msgBuf = new StringBuffer();
//        try {
//            Set<String> keySet = jedis.keys(uuid + "*");
//            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
//                String key = (String) iterator.next();
//                msgBuf.append(key);
//                msgBuf.append("--");
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//
//        model.putMsg(msgBuf.toString());
//        Long endTime = System.currentTimeMillis();
//        logger.info("################userLogin/test_findAllRedisKeyByUuid 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }

    /**
     * 获取全部含有(uuid)的Redis缓存key
     * Redis缓存Key:(uuid:用户ID:企业ID:deecoop:userLoginMap)
     * @return
     */
    @GetMapping("/system/userLogin/test_findAllRedisKeyByCodeKey")
    public ResultModel test_findAllRedisKeyByCodeKey() {
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String codeKey = (String)pageData.get("codeKey");
        if (codeKey == null || codeKey.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("codeKey为空或空字符串！");
            return model;
        }

        String codeValue = (String)pageData.get("codeValue");
        if (codeValue == null || codeValue.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("codeValue为空或空字符串！");
            return model;
        }

        Jedis jedis = redisClient.getJedisPool().getResource();
        if (jedis == null) {
            throw new RestException("", "Redis 缓存错误(jedis is null)，请与管理员联系！");
        }

        StringBuffer msgBuf = new StringBuffer();
        try {
            String strTemp = "";
            if ("uuid".equals(codeKey)) {
                strTemp = codeValue;
            } else if ("userID,companyID,deecoop,userLoginMap".indexOf(codeKey) != -1 ) {
                strTemp = ":" + codeValue;
            } else {
                strTemp = codeValue;
            }

            Set<String> keySet = jedis.keys("*" + strTemp + "*");
            for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                msgBuf.append(key);
                msgBuf.append("--");
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        model.putMsg(msgBuf.toString());
        return model;
    }

    @GetMapping("/system/userLogin/test_removeRedisKeyByCodeKey")
    public ResultModel test_removeRedisKeyByCodeKey() {
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String codeKey = (String)pageData.get("codeKey");
        if (codeKey == null || codeKey.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("codeKey为空或空字符串！");
            return model;
        }

        String codeValue = (String)pageData.get("codeValue");
        if (codeValue == null || codeValue.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("codeValue为空或空字符串！");
            return model;
        }

        String loginType = (String)pageData.get("loginType");
        if (loginType == null || loginType.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("loginType为空或空字符串！");
            return model;
        }

        if ("uuid".equals(codeKey)) {
            RedisUtils.removeByUuid(redisClient, codeValue);
        } else if ("userID".equals(codeKey)) {
            RedisUtils.removeByUserID(redisClient, codeValue, loginType);
        } else {
            RedisUtils.removeByCode(redisClient, codeValue);
        }

        return model;
    }



    /**
     * 获取全部含有(uuid)的Redis缓存key
     * Redis缓存Key:(uuid:用户ID:企业ID:deecoop:userLoginMap)
     * @return
     */
//    @GetMapping("/system/userLogin/test_removeRedisKeyByUserID")
//    public ResultModel test_removeRedisKeyByUserID() {
//        logger.info("################userLogin/test_removeRedisKeyByUserID 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        ResultModel model = new ResultModel();
//        PageData pageData = HttpUtils.parsePageData();
//
//        //1. 非空判断
//        if (pageData == null || pageData.size() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("参数错误：用户登录参数(pageData)为空！");
//            return model;
//        }
//
//        String userID = (String)pageData.get("userID");
//        if (userID == null || userID.trim().length() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("userID为空或空字符串！");
//            return model;
//        }
//
//        RedisUtils.removeByUserID(redisClient, userID);
//        Long endTime = System.currentTimeMillis();
//        logger.info("################userLogin/test_removeRedisKeyByUserID 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }

    /**
     * 获取全部含有(uuid)的Redis缓存key
     * Redis缓存Key:(uuid:用户ID:企业ID:deecoop:userLoginMap)
     * @return
     */
//    @GetMapping("/system/userLogin/test_removeRedisKeyByUuid")
//    public ResultModel test_removeRedisKeyByUuid() {
//        logger.info("################userLogin/test_removeRedisKeyByUuid 执行开始 ################# ");
//        Long startTime = System.currentTimeMillis();
//        ResultModel model = new ResultModel();
//        PageData pageData = HttpUtils.parsePageData();
//
//        //1. 非空判断
//        if (pageData == null || pageData.size() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("参数错误：用户登录参数(pageData)为空！");
//            return model;
//        }
//
//        String uuid = (String)pageData.get("uuid");
//        if (uuid == null || uuid.trim().length() == 0) {
//            model.putCode(Integer.valueOf(1));
//            model.putMsg("uuid为空或空字符串！");
//            return model;
//        }
//
//        RedisUtils.removeByUuid(redisClient, uuid);
//        Long endTime = System.currentTimeMillis();
//        logger.info("################userLogin/test_removeRedisKeyByUuid 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
//        return model;
//    }

    @GetMapping("/system/userLogin/test_findRedisJsonStringBySessionID")
    public ResultModel test_findRedisJsonStringBySessionID() {
        logger.info("################userLogin/test_findRedisJsonStringBySessionID 执行开始 ################# ");
        Long startTime = System.currentTimeMillis();
        ResultModel model = new ResultModel();
        PageData pageData = HttpUtils.parsePageData();

        //1. 非空判断
        if (pageData == null || pageData.size() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("参数错误：用户登录参数(pageData)为空！");
            return model;
        }

        String sessionID = (String)pageData.get("sessionID");
        if (sessionID == null || sessionID.trim().length() == 0) {
            model.putCode(Integer.valueOf(1));
            model.putMsg("uuid为空或空字符串！");
            return model;
        }

        String jsonString = RedisUtils.getRedisJsonStringBySessionID(redisClient, sessionID);
        model.putMsg(jsonString);
        Long endTime = System.currentTimeMillis();
        logger.info("################userLogin/test_findRedisJsonStringBySessionID 执行结束 总耗时"+(endTime-startTime)+"ms ################# ");
        return model;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////

}
