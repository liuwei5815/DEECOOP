package com.xy.vmes.deecoop.system.service;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xy.vmes.entity.MenuButton;
import com.yvan.PageData;
import com.yvan.springmvc.ResultModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* 说明：vmes_menu_button:菜单按钮表 接口类
* 创建人：陈刚 自动生成
* 创建时间：2018-08-03
*/
public interface MainPageService {

//
    ResultModel changePassWord(PageData pd) throws Exception;

    ResultModel changePageStyle(PageData pd) throws Exception;

    ResultModel saveUserDefinedMenu(PageData pd) throws Exception;

    ResultModel listUserDefinedMenu(PageData pd) throws Exception;

    ResultModel listRoleMeunAll(PageData pageData) throws Exception;
}



