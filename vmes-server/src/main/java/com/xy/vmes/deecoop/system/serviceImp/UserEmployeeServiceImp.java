package com.xy.vmes.deecoop.system.serviceImp;

import com.xy.vmes.deecoop.system.dao.UserEmployeeMapper;
import com.xy.vmes.entity.Employee;
import com.xy.vmes.entity.User;
import com.xy.vmes.deecoop.system.service.UserEmployeeService;
import com.yvan.PageData;
import com.yvan.platform.RestException;
import com.xy.vmes.common.util.DateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserEmployeeServiceImp implements UserEmployeeService {
    @Autowired
    private UserEmployeeMapper viewUserEmployeeMapper;

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     *
     * @param object
     */
    @Override
    public List<Map<String, Object>> findViewUserEmployList(PageData object) {
        List<Map<String, Object>> objectList = new ArrayList<Map<String, Object>>();
        if (object == null) {return objectList;}

        try {
            objectList = viewUserEmployeeMapper.findViewUserEmployList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }

    public Map<String, Object> findViewUserEmployByUserId(String userId) {
        if (userId == null || userId.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("userID", userId);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Map<String, Object>> objectList = this.findViewUserEmployList(findMap);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }
        return null;
    }

    public Map<String, Object> findViewUserEmployByEmployID(String employID) {
        if (employID == null || employID.trim().length() == 0) {return null;}

        PageData findMap = new PageData();
        findMap.put("employID", employID);
        findMap.put("mapSize", Integer.valueOf(findMap.size()));

        List<Map<String, Object>> objectList = this.findViewUserEmployList(findMap);
        if (objectList != null && objectList.size() > 0) {
            return objectList.get(0);
        }
        return null;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     *
     * @param mapObject
     * @param object
     */
    @Override
    public User mapObject2User(Map<String, Object> mapObject, User object) {
        if (object == null) {object = new User();}
        if (mapObject == null) {return object;}

        //userID--id
        if (mapObject.get("userID") != null) {
            object.setId(mapObject.get("userID").toString().trim());
        }
        //员工ID userEmployID
        if (mapObject.get("userEmployID") != null) {
            object.setEmployId(mapObject.get("userEmployID").toString().trim());
        }
        //userDeptID
        if (mapObject.get("userDeptID") != null) {
            object.setDeptId(mapObject.get("userDeptID").toString().trim());
        }
        //公司ID-组织架构 userCompanyID
        if (mapObject.get("userCompanyID") != null) {
            object.setCompanyId(mapObject.get("userCompanyID").toString().trim());
        }
        //账号(系统登录账号) userCode
        if (mapObject.get("userCode") != null) {
            object.setUserCode(mapObject.get("userCode").toString().trim());
        }

        //密码(MD5加密)userPassword
        if (mapObject.get("userPassword") != null) {
            object.setPassword(mapObject.get("userPassword").toString().trim());
        }
        //用户微信ID userOpenID
        if (mapObject.get("userOpenID") != null) {
            object.setOpenId(mapObject.get("userOpenID").toString().trim());
        }
        //手机号码 userMobile
        if (mapObject.get("userMobile") != null) {
            object.setMobile(mapObject.get("userMobile").toString().trim());
        }
        //邮箱地址 userEmail
        if (mapObject.get("userEmail") != null) {
            object.setEmail(mapObject.get("userEmail").toString().trim());
        }
        //姓名 userName
        if (mapObject.get("userName") != null) {
            object.setUserName(mapObject.get("userName").toString().trim());
        }

        //是否禁用 userIsdisable
        if (mapObject.get("userIsdisable") != null) {
            object.setIsdisable(mapObject.get("userIsdisable").toString().trim());
        }
        //创建时间 userCdate
        if (mapObject.get("userCdate") != null) {
            String dateStr = mapObject.get("userCdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //创建人账号 userCuser
        if (mapObject.get("userCuser") != null) {
            object.setCuser(mapObject.get("userCuser").toString().trim());
        }

        //修改时间 userUdate
        if (mapObject.get("userUdate") != null) {
            String dateStr = mapObject.get("userUdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setUdate(date);
            }
        }
        //修改人账号 userUuser
        if (mapObject.get("userUuser") != null) {
            object.setUuser(mapObject.get("userUuser").toString().trim());
        }
        //预留字段 userColumn1
        if (mapObject.get("userColumn1") != null) {
            object.setColumn1(mapObject.get("userColumn1").toString().trim());
        }
        //预留字段 userColumn2
        if (mapObject.get("userColumn2") != null) {
            object.setColumn2(mapObject.get("userColumn2").toString().trim());
        }
        //预留字段 userColumn3
        if (mapObject.get("userColumn3") != null) {
            object.setColumn3(mapObject.get("userColumn3").toString().trim());
        }
        //userType用户类型(0:超级管理员1:企业管理员2:普通用户)
        if (mapObject.get("userType") != null) {
            object.setUserType(mapObject.get("userType").toString().trim());
        }

        return object;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-20
     *
     * @param mapObject
     * @param object
     */
    @Override
    public Employee mapObject2Employee(Map<String, Object> mapObject, Employee object) {
        if (object == null) {object = new Employee();}
        if (mapObject == null) {return object;}

        //employID;
        if (mapObject.get("employID") != null) {
            object.setId(mapObject.get("employID").toString().trim());
        }
        //员工编号 employCode
        if (mapObject.get("employCode") != null) {
            object.setCode(mapObject.get("employCode").toString().trim());
        }
        //员工类型 employType
        if (mapObject.get("employType") != null) {
            object.setType(mapObject.get("employType").toString().trim());
        }
        //系统用户ID employUserID
        if (mapObject.get("employUserID") != null) {
            object.setUserId(mapObject.get("employUserID").toString().trim());
        }
        //公司ID-组织架构 employCompanyID
        if (mapObject.get("employCompanyID") != null) {
            object.setCompanyId(mapObject.get("employCompanyID").toString().trim());
        }
        //员工姓名 employName
        if (mapObject.get("employName") != null) {
            object.setName(mapObject.get("employName").toString().trim());
        }
        //员工英文名 employNameEn
        if (mapObject.get("employNameEn") != null) {
            object.setNameEn(mapObject.get("employNameEn").toString().trim());
        }

        //员工照片 photo
        if (mapObject.get("photo") != null) {
            object.setPhoto(mapObject.get("photo").toString().trim());
        }
        //员工图片 icon
        if (mapObject.get("icon") != null) {
            object.setIcon(mapObject.get("icon").toString().trim());
        }
        //手机号码 mobile
        if (mapObject.get("mobile") != null) {
            object.setMobile(mapObject.get("mobile").toString().trim());
        }
        //邮箱地址 email
        if (mapObject.get("email") != null) {
            object.setEmail(mapObject.get("email").toString().trim());
        }
        //性别 sex
        if (mapObject.get("sex") != null) {
            object.setSex(mapObject.get("sex").toString().trim());
        }

        //出生日期 birthday
        if (mapObject.get("birthday") != null) {
            String dateStr = mapObject.get("birthday").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setBirthday(date);
            }
        }
        //入职日期 entryDate
        if (mapObject.get("entryDate") != null) {
            String dateStr = mapObject.get("entryDate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setEntryDate(date);
            }
        }
        //离职日期 leaveDate
        if (mapObject.get("leaveDate") != null) {
            String dateStr = mapObject.get("leaveDate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setLeaveDate(date);
            }
        }
        //籍贯 nativePlace
        if (mapObject.get("nativePlace") != null) {
            object.setNativePlace(mapObject.get("nativePlace").toString().trim());
        }
        //政治面貌 political
        if (mapObject.get("political") != null) {
            object.setPolitical(mapObject.get("political").toString().trim());
        }
        //身份证号 identityNumber
        if (mapObject.get("identityNumber") != null) {
            object.setIdentityNumber(mapObject.get("identityNumber").toString().trim());
        }

        //婚姻状况 marital
        if (mapObject.get("marital") != null) {
            object.setMarital(mapObject.get("marital").toString().trim());
        }
        //是否禁用 employIsdisable
        if (mapObject.get("employIsdisable") != null) {
            object.setIsdisable(mapObject.get("employIsdisable").toString().trim());
        }
        //创建时间 employCdate
        if (mapObject.get("employCdate") != null) {
            String dateStr = mapObject.get("employCdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //创建人账号
        if (mapObject.get("employCuser") != null) {
            object.setCuser(mapObject.get("employCuser").toString().trim());
        }
        //修改时间 employUdate
        if (mapObject.get("employUdate") != null) {
            String dateStr = mapObject.get("employUdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setUdate(date);
            }
        }

        //修改人账号 employUuser
        if (mapObject.get("employUuser") != null) {
            object.setUuser(mapObject.get("employUuser").toString().trim());
        }
        //备注 remark
        if (mapObject.get("employRemark") != null) {
            object.setRemark(mapObject.get("employRemark").toString().trim());
        }
        //是否开通用户 isOpenUser
        if (mapObject.get("isOpenUser") != null) {
            object.setIsOpenUser(mapObject.get("isOpenUser").toString().trim());
        }

        //预留字段 employColumn1
        if (mapObject.get("employColumn1") != null) {
            object.setColumn1(mapObject.get("employColumn1").toString().trim());
        }
        //预留字段 employColumn2
        if (mapObject.get("employColumn2") != null) {
            object.setColumn2(mapObject.get("employColumn2").toString().trim());
        }

        //预留字段 employColumn3
        if (mapObject.get("employColumn3") != null) {
            object.setColumn3(mapObject.get("employColumn3").toString().trim());
        }

        return object;
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-07-24
     */
    public Map<String, Object> userEmployMap2RedisMap(Map<String, Object> objectMap, Map<String, Object> redisMap) {
        if (redisMap == null) {redisMap = new HashMap<String, Object>();}
        if (objectMap == null || objectMap.size() == 0) {return redisMap;}

//        //用户信息(userID,userCode,companyID,
//        if (objectMap.get("userID") != null && objectMap.get("userID").toString().trim().length() > 0) {
//            redisMap.put(Common.REDIS_USER_ID, objectMap.get("userID").toString().trim());
//        }
//        if (objectMap.get("userCode") != null && objectMap.get("userCode").toString().trim().length() > 0) {
//            redisMap.put(Common.REDIS_USER_CODE, objectMap.get("userCode").toString().trim());
//        }
//        if (objectMap.get("userCompanyID") != null && objectMap.get("userCompanyID").toString().trim().length() > 0) {
//            redisMap.put(Common.REDIS_COMPANYID, objectMap.get("userCompanyID").toString().trim());
//        }
//
//        //员工信息(employID,employName,)
//        if (objectMap.get("employID") != null && objectMap.get("employID").toString().trim().length() > 0) {
//            redisMap.put(Common.REDIS_EMPLOYID, objectMap.get("employID").toString().trim());
//        }
//        if (objectMap.get("employName") != null && objectMap.get("employName").toString().trim().length() > 0) {
//            redisMap.put(Common.REDIS_EMPLOYNAME, objectMap.get("employName").toString().trim());
//        }

        return null;
    }
}
