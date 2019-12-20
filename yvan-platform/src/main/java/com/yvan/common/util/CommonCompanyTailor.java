package com.yvan.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业定制化配置常量类
 */
public class CommonCompanyTailor {
    //通过企业管理员角色-配置企业定制化
    public static final Map<String, String> SYS_COMPANY_ROLE = new HashMap<String, String>() {{
        //套餐F:(仓库(简版)-文成-含有货品价格) 角色id: e0af062de24046a385f9319dbad8d875
        //定制功能: 仓库(简版)-含有货品价格(文成)
        put("e0af062de24046a385f9319dbad8d875", "e0af062de24046a385f9319dbad8d875");
    }};


}
