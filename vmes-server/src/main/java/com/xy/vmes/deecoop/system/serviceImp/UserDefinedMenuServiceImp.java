package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.deecoop.system.dao.UserDefinedMenuMapper;
import com.xy.vmes.entity.UserDefinedMenu;
import com.xy.vmes.deecoop.system.service.UserDefinedMenuService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：用户自定义菜单 实现类
* 创建人：刘威 自动创建
* 创建时间：2018-07-27
*/
@Service
@Transactional(readOnly = false)
public class UserDefinedMenuServiceImp implements UserDefinedMenuService {


    @Autowired
    private UserDefinedMenuMapper userDefinedMenuMapper;

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public void save(UserDefinedMenu userDefinedMenu) throws Exception{
        userDefinedMenu.setId(Conv.createUuid());
        userDefinedMenu.setCdate(new Date());
        userDefinedMenu.setUdate(new Date());
        userDefinedMenuMapper.insert(userDefinedMenu);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public void update(UserDefinedMenu userDefinedMenu) throws Exception{
        userDefinedMenu.setUdate(new Date());
        userDefinedMenuMapper.updateById(userDefinedMenu);
    }

    /**
     * 创建人：刘威 自动创建，禁止修改
     * 创建时间：2018-07-27
     */
    @Override
    public void updateAll(UserDefinedMenu userDefinedMenu) throws Exception{
        userDefinedMenu.setUdate(new Date());
        userDefinedMenuMapper.updateAllColumnById(userDefinedMenu);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    //@Cacheable(cacheNames = "userDefinedMenu", key = "''+#id")
    public UserDefinedMenu selectById(String id) throws Exception{
        return userDefinedMenuMapper.selectById(id);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public void deleteById(String id) throws Exception{
        userDefinedMenuMapper.deleteById(id);
    }


    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public List<UserDefinedMenu> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return userDefinedMenuMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public List<UserDefinedMenu> dataList(PageData pd) throws Exception{
        return userDefinedMenuMapper.dataList(pd);
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return userDefinedMenuMapper.findColumnList();
    }

    /**
    * 创建人：刘威 自动创建，禁止修改
    * 创建时间：2018-07-27
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return userDefinedMenuMapper.findDataList(pd);
    }


    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public void deleteUserDefinedMenuByUserId(String userId) throws Exception {
        if (userId == null || userId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("user_id", userId);

        this.deleteByColumnMap(mapObject);
    }
    public void deleteUserDefinedMenuByMenuId(String menuId) throws Exception {
        if (menuId == null || menuId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("menu_id", menuId);

        this.deleteByColumnMap(mapObject);
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    @Override
    public void deleteByColumnMap(Map columnMap) throws Exception{
        userDefinedMenuMapper.deleteByMap(columnMap);
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-07-31
     */
    @Override
    public List<UserDefinedMenu> selectByColumnMap(Map columnMap) throws Exception{
        List<UserDefinedMenu> userDefinedMenuList =  userDefinedMenuMapper.selectByMap(columnMap);
        return userDefinedMenuList;
    }


    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    @Override
    public List<LinkedHashMap> getColumnList() throws Exception{
        return userDefinedMenuMapper.getColumnList();
    }

    /**
     * 创建人：刘威
     * 创建时间：2018-07-26
     */
    @Override
    public List<Map> getDataList(PageData pd) throws Exception{
        return userDefinedMenuMapper.getDataList(pd);
    }

}



