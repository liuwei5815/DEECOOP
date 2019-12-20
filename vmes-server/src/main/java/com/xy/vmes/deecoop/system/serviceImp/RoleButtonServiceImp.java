package com.xy.vmes.deecoop.system.serviceImp;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.common.util.DateFormat;
import com.xy.vmes.deecoop.system.dao.RoleButtonMapper;
import com.xy.vmes.entity.*;
import com.xy.vmes.deecoop.system.service.RoleButtonService;
import com.yvan.HttpUtils;
import com.yvan.PageData;
import com.xy.vmes.common.util.StringUtil;
import com.yvan.platform.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.yvan.Conv;

/**
* 说明：vmes_role_button:角色按钮 实现类
* 创建人：陈刚 自动创建
* 创建时间：2018-07-30
*/
@Service
@Transactional(readOnly = false)
public class RoleButtonServiceImp implements RoleButtonService {


    @Autowired
    private RoleButtonMapper roleButtonMapper;

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void save(RoleButton roleButton) throws Exception{
        roleButton.setId(Conv.createUuid());
        roleButton.setCdate(new Date());
        roleButton.setUdate(new Date());
        roleButtonMapper.insert(roleButton);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void update(RoleButton roleButton) throws Exception{
        roleButton.setUdate(new Date());
        roleButtonMapper.updateById(roleButton);
    }

    /**
     * 创建人：陈刚 自动创建，禁止修改
     * 创建时间：2018-07-30
     */
    @Override
    public void updateAll(RoleButton roleButton) throws Exception{
        roleButton.setUdate(new Date());
        roleButtonMapper.updateAllColumnById(roleButton);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    //@Cacheable(cacheNames = "roleButton", key = "''+#id")
    public RoleButton selectById(String id) throws Exception{
        return roleButtonMapper.selectById(id);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public void deleteById(String id) throws Exception{
        roleButtonMapper.deleteById(id);
    }


    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<RoleButton> dataListPage(PageData pd,Pagination pg) throws Exception{
        if(pg==null){
            pg =  HttpUtils.parsePagination(pd);
        }
        return roleButtonMapper.dataListPage(pd,pg);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<RoleButton> dataList(PageData pd) throws Exception{
        return roleButtonMapper.dataList(pd);
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<LinkedHashMap> findColumnList() throws Exception{
        return roleButtonMapper.findColumnList();
    }

    /**
    * 创建人：陈刚 自动创建，禁止修改
    * 创建时间：2018-07-30
    */
    @Override
    public List<Map> findDataList(PageData pd) throws Exception{
        return roleButtonMapper.findDataList(pd);
    }

    public void deleteByColumnMap(Map columnMap) throws Exception {
        roleButtonMapper.deleteByMap(columnMap);
    }
    /*****************************************************以上为自动生成代码禁止修改，请在下面添加业务代码**************************************************/
    public List<RoleButton> findRoleButtonList(PageData object) {
        List<RoleButton> objectList = new ArrayList<RoleButton>();
        if (object == null) {return objectList;}

        try {
            objectList = this.dataList(object);
        } catch (Exception e) {
            throw new RestException("", e.getMessage());
        }

        return objectList;
    }
    /**
     * 根据当前角色ID-删除角色按钮
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void deleteRoleButtonByRoleId(String roleId) throws Exception {
        if (roleId == null || roleId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("role_id", roleId);

        this.deleteByColumnMap(mapObject);
    }

    public void deleteRoleButtonByButtonId(String buttonId) throws Exception {
        if (buttonId == null || buttonId.trim().length() == 0) {return;}

        Map<String, Object> mapObject = new HashMap<String, Object>();
        mapObject.put("button_id", buttonId);

        this.deleteByColumnMap(mapObject);
    }

    /**
     * 添加角色按钮
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void addRoleButtonByMeunIds(String roleId, String buttonIds) {
        if (roleId == null || roleId.trim().length() == 0) {return;}
        if (buttonIds == null || buttonIds.trim().length() == 0) {return;}

        buttonIds = StringUtil.stringTrimSpace(buttonIds);
        String[] strArry = buttonIds.split(",");
        for (int i = 0; i < strArry.length; i++) {
            String buttonID = strArry[i];
            RoleButton objectDB = new RoleButton();
            objectDB.setRoleId(roleId);
            objectDB.setButtonId(buttonID);
            try {
                this.save(objectDB);
            } catch (Exception e) {
                throw new RestException("", e.getMessage());
            }
        }
    }

    /**
     * 修改禁用属性(isdisable)
     * 根据角色ID-修改角色按钮
     * 创建人：陈刚
     * 创建时间：2018-07-31
     */
    public void updateDisableByRoleId(String roleId) {
        roleButtonMapper.updateDisableByRoleId(roleId);
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-27
     */
    public List<Map<String, Object>> listMenuButtonMapByRole(PageData pd) {
        return roleButtonMapper.listMenuButtonMapByRole(pd);
    }

    /**
     * 创建人：陈刚
     * 创建时间：2018-08-27
     */
    public MenuButton mapObject2MenuButton(Map<String, Object> mapObject, MenuButton object) {
        if (object == null) {object = new MenuButton();}
        if (mapObject == null) {return object;}

        //a.id id,
        if (mapObject.get("id") != null) {
            object.setId(mapObject.get("id").toString().trim());
        }
        //a.company_id companyId,
        if (mapObject.get("companyId") != null) {
            object.setCompanyId(mapObject.get("companyId").toString().trim());
        }
        //a.menu_id menuId,
        if (mapObject.get("menuId") != null) {
            object.setMenuId(mapObject.get("menuId").toString().trim());
        }
        //a.code code,
        if (mapObject.get("code") != null) {
            object.setCode(mapObject.get("code").toString().trim());
        }
        //a.value value,
        if (mapObject.get("value") != null) {
            object.setValue(mapObject.get("value").toString().trim());
        }

        //a.name name,
        if (mapObject.get("name") != null) {
            object.setName(mapObject.get("name").toString().trim());
        }
        //a.serial_number serialNumber,
        if (mapObject.get("serialNumber") != null) {
            try {
                object.setSerialNumber(Integer.valueOf(mapObject.get("serialNumber").toString().trim()) );
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //a.name_en nameEn,
        if (mapObject.get("nameEn") != null) {
            object.setNameEn(mapObject.get("nameEn").toString().trim());
        }
        //a.isdisable buttonIsdisable,
        if (mapObject.get("buttonIsdisable") != null) {
            object.setIsdisable(mapObject.get("buttonIsdisable").toString().trim());
        }
        //a.cdate cdate,
        if (mapObject.get("cdate") != null) {
            String dateStr = mapObject.get("cdate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //a.cuser cuser,
        if (mapObject.get("cuser") != null) {
            object.setCuser(mapObject.get("cuser").toString().trim());
        }
        //a.udate udate,
        if (mapObject.get("udate") != null) {
            String dateStr = mapObject.get("udate").toString().trim();
            Date date = DateFormat.dateString2Date(dateStr, DateFormat.DEFAULT_DATETIME_FORMAT);
            if (date != null) {
                object.setCdate(date);
            }
        }
        //a.uuser uuser,
        if (mapObject.get("uuser") != null) {
            object.setUuser(mapObject.get("uuser").toString().trim());
        }

        return object;
    }

    public MenuButtonEntity menuButton2ButtonsEntity(MenuButton button, MenuButtonEntity entity) {
        if (entity == null) {entity = new MenuButtonEntity();}
        if (button == null) {return entity;}

        //id 按钮id
        entity.setId(button.getId());
        //name 按钮名称
        String name = "";
        if (button.getName() != null && button.getName().trim().length() > 0) {
            name = button.getName().trim();
        }
        entity.setName(name);

        //nameEn英文名称
        String nameEn = "";
        if (button.getNameEn() != null && button.getNameEn().trim().length() > 0) {
            nameEn = button.getNameEn().trim();
        }
        entity.setNameEn(nameEn);

        //serialNumber按钮顺序
        entity.setSerialNumber(button.getSerialNumber());

        //是否禁用(0:已禁用 1:启用) 数据字典:sys_isdisable
        String isdisable = "0";
        if (button.getIsdisable() != null && button.getIsdisable().trim().length() > 0) {
            isdisable = button.getIsdisable().trim();
        }
        entity.setIsdisable(isdisable);

        return entity;
    }

    /**
     * 角色按钮ListList<Map<String, Object>>转换成-按钮结构体List<MenuButtonEntity>
     * @param mapList  角色菜单List<Map<String, Object>>
     * @param buttonList 树结构体List<MenuButtonEntity>
     * @return
     */
    public List<MenuButtonEntity> roleButtonList2ButtonList(List<Map<String, Object>> mapList, List<MenuButtonEntity> buttonList) {
        if (buttonList == null) {buttonList = new ArrayList<MenuButtonEntity>();}
        if (mapList == null || mapList.size() == 0) {return buttonList;}

        //遍历mapList-buttonList
        for (Map<String, Object> mapObj : mapList) {
            MenuButton button = this.mapObject2MenuButton(mapObj, null);
            MenuButtonEntity entity = this.menuButton2ButtonsEntity(button, null);
            //当前节点-是否绑定角色(1:绑定 0:未绑定)
            entity.setIsBindRole("1");

            //当前菜单是否绑定角色
            // 判断条件: 角色id(roleId)是否存在--
            if (mapObj.get("roleId") != null && mapObj.get("roleId").toString().trim().length() > 0) {
                entity.setIsBindRole("0");
            }

            buttonList.add(entity);
        }

        return buttonList;
    }

    /**
     * 获取按钮ID(','逗号分隔的字符串)
     * 创建人：陈刚
     * 创建时间：2018-09-14
     *
     * @param objectList
     * @return
     */
    public String findButtonIdsByRoleButtonList(List<RoleButton> objectList) {
        if (objectList == null || objectList.size() == 0) {return new String();}

        StringBuffer strBuf = new StringBuffer();
        for (RoleButton object : objectList) {
            if (object.getButtonId() != null && object.getButtonId().trim().length() > 0)  {
                strBuf.append(object.getButtonId().trim());
                strBuf.append(",");
            }
        }

        String strTemp = strBuf.toString();
        if (strTemp.trim().length() > 0 && strTemp.lastIndexOf(",") != -1) {
            strTemp = strTemp.substring(0, strTemp.lastIndexOf(","));
            return strTemp;
        }

        return strBuf.toString();
    }

}



