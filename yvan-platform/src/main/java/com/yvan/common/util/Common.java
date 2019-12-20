package com.yvan.common.util;

import java.util.*;

public class Common {
    //linux系统:文件系统根路径
    public final static String SYS_LINUX_FILE_ROOT = "/home/vmes/htdocs/web.ouhaicloud.com/";

    //系统应用自定义异常-错误码(ErrorCode)
    //系统通用编码规则 version 锁
    public final static String SYS_CODERULE_ERRORCODE = "0001";
    //库存变更 version 锁
    public final static String SYS_STOCKCOUNT_ERRORCODE = "0002";

    //系统表默认 isdisable 值
    public final static String SYS_DEFAULT_ISDISABLE_1 = "1";
    public final static String SYS_DEFAULT_ISDISABLE_0 = "0";

    //设备-设备传感器指标(设备联网)
    //设备传感器-最大指标个数(100)-与表(vmes_sensor_source,vmes_sensor_source_history)字段对应 (A0001..A0100 B0001..B0100)
    public final static Integer SYS_EQUIPMENT_SENSORTARGET_TARGETCODE_MAXCOUNT = Integer.valueOf(100);
    //设备传感器-指标编号-(A0001..A0100 B0001..B0100)
    public final static Integer SYS_EQUIPMENT_SENSORTARGET_TARGETCODE_MAXDIGIT = Integer.valueOf(5);

    //仓库类型(复杂版仓库 简版仓库)
    //复杂版仓库
    public final static String SYS_WAREHOUSE_COMPLEX = "warehouseByComplex";
    //简版仓库
    public final static String SYS_WAREHOUSE_SIMPLE = "warehouseBySimple";

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public final static String REDIS_SECURITY_CODE = "securityCode";
    public final static String DEPARTMENT_ROOT_ID = "b6ff76cb95f711e884ad00163e105f05";
    //public final static String REDIS_EMPLOY = "employ";
    //public final static String REDIS_DEPT = "dept";
    //public final static String REDIS_USERROLE = "userRole";
    //public final static String REDIS_USERMENU = "userMenu";
    //public final static String REDIS_USERBUTTON = "userButton";
    public final static String REDIS_USERLOGINMAP = "userLoginMap";

    //用户登录-验证码-redis有效时间(单位:毫秒)
    public final static int REDIS_SECURITYCODE_LONG = 1 * 60*1000;
    //用户登录-sessionID-redis有效时间(单位:毫秒)
    public final static int REDIS_SESSIONID_LONG = 30*60*1000;


    //系统默认密码: 123
    public final static String DEFAULT_PASSWORD = "123";

    //java代码中的换行符
    public final static String SYS_ENDLINE_JAVA = "\n";
    //html中的换行符
    public final static String SYS_ENDLINE_HTML = "<br/>";
    //系统默认换行符 "\n"
    public final static String SYS_ENDLINE_DEFAULT = SYS_ENDLINE_JAVA;

    public static Integer SYS_IMPORTEXCEL_MESSAGE_MAXROW  = Integer.valueOf(50);

    //系统小数四舍五入-位数
    public static final int SYS_NUMBER_FORMAT_2 = 2;
    public static final int SYS_NUMBER_FORMAT_3 = 3;
    public static final int SYS_NUMBER_FORMAT_4 = 4;
    //系统小数四舍五入-默认2位小数
    public static final int SYS_NUMBER_FORMAT_DEFAULT = SYS_NUMBER_FORMAT_2;
    //系统(价格)四舍五入-默认4位小数
    public static final int SYS_PRICE_FORMAT_DEFAULT = SYS_NUMBER_FORMAT_4;

    //系统是否需要四舍五入 Y:需要四舍五入
    public static final String SYS_ISSCALE_TRUE = "Y";
    //系统是否需要四舍五入 N:无需四舍五入
    public static final String SYS_ISSCALE_FALSE = "N";

    //(仓库)盘点
    //盘点明细状态(0:待派单 1:执行中 2:审核中 3:已完成 -1:已取消)
    public static final Map<String, String> SYS_WAREHOUSE_CHECK_DETAIL_STATE = new HashMap<String, String>() {{
        put("0", "待派单");
        put("1", "执行中");
        put("2", "审核中");
        put("3", "已完成");
        put("-1", "已取消");
    }};

    //订单状态(0:待提交 1:待审核 2:待发货 3:已发货 4:已完成 -1:已取消)
    public static final Map<String, String> SYS_SALE_ORDER_STATE = new HashMap<String, String>() {{
        put("0", "待提交");
        put("1", "待审核");
        put("2", "待发货");
        put("3", "已发货");
        put("4", "已完成");
        put("-1", "已取消");
    }};
//    //订单明细状态(0:待提交 1:待审核 2:待生产 3:待出库 4:待发货 5:已发货 6:已完成 -1:已取消)
//    public static final Map<String, String> SYS_SALE_ORDER_DETAIL_STATE = new HashMap<String, String>() {{
//        put("0", "待提交");
//        put("1", "待审核");
//        put("2", "待生产");
//        put("3", "待出库");
//        put("4", "待发货");
//        put("5", "已发货");
//        put("6", "已完成");
//        put("-1", "已取消");
//    }};

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final List<Integer> SYS_DAYOFWEEK_LIST = new ArrayList<Integer>() {{
        add(Integer.valueOf(Calendar.SUNDAY));
        add(Integer.valueOf(Calendar.MONDAY));
        add(Integer.valueOf(Calendar.TUESDAY));
        add(Integer.valueOf(Calendar.WEDNESDAY));
        add(Integer.valueOf(Calendar.THURSDAY));
        add(Integer.valueOf(Calendar.FRIDAY));
        add(Integer.valueOf(Calendar.SATURDAY));
    }};

    //当前日期星期几
    public static final Map<Integer, String> SYS_DAYOFWEEK_TO_WEEKNAME  = new HashMap<Integer, String>() {{
        put(Integer.valueOf(Calendar.SUNDAY), "星期日");
        put(Integer.valueOf(Calendar.MONDAY), "星期一");
        put(Integer.valueOf(Calendar.TUESDAY), "星期二");
        put(Integer.valueOf(Calendar.WEDNESDAY), "星期三");
        put(Integer.valueOf(Calendar.THURSDAY), "星期四");
        put(Integer.valueOf(Calendar.FRIDAY), "星期五");
        put(Integer.valueOf(Calendar.SATURDAY), "星期六");
    }};

    //周的名称
    public static final Map<String, String> SYS_WEEKNAME = new HashMap<String, String>() {{
        put("SUNDAY", "星期日");
        put("MONDAY", "星期一");
        put("TUESDAY", "星期二");
        put("WEDNESDAY", "星期三");
        put("THURSDAY", "星期四");
        put("FRIDAY", "星期五");
        put("SATURDAY", "星期六");
    }};

    public static final Map<String, Integer> SYS_WEEK_DAYOFWEEK = new HashMap<String, Integer>() {{
        put("SUNDAY", Integer.valueOf(Calendar.SUNDAY));
        put("MONDAY", Integer.valueOf(Calendar.MONDAY));
        put("TUESDAY", Integer.valueOf(Calendar.TUESDAY));
        put("WEDNESDAY", Integer.valueOf(Calendar.WEDNESDAY));
        put("THURSDAY", Integer.valueOf(Calendar.THURSDAY));
        put("FRIDAY", Integer.valueOf(Calendar.FRIDAY));
        put("SATURDAY", Integer.valueOf(Calendar.SATURDAY));
    }};

    //当前月第几周
    public static final Map<Integer, String> SYS_WEEK_WEEKINMONTH = new HashMap<Integer, String>() {{
        put(Integer.valueOf(1), "第一");
        put(Integer.valueOf(2), "第二");
        put(Integer.valueOf(3), "第三");
        put(Integer.valueOf(4), "第四");
        put(Integer.valueOf(5), "第五");
    }};


    ///////////////--编码规则--//////////////////////////////////////////////////////////////////////////////////////////////////
    //业务编码规则-分隔符
    //public static final String CODE_RULE_SEPARATOR = "-";
    //业务编码规则-(默认)填充字符
    public static final String CODE_RULE_DEFAULT_FILLING = "0";
    //业务编码规则-最大业务流水号长度(5位)
    public static final int CODE_RULE_LENGTH_6 = 6;
    public static final int CODE_RULE_LENGTH_5 = 5;
    public static final int CODE_RULE_LENGTH_4 = 4;
    //业务编码规则-最小业务流水号长度(3位)
    public static final int CODE_RULE_LENGTH_3 = 3;

    public static final int CODE_RULE_LENGTH_DEFAULT = CODE_RULE_LENGTH_5;

    //业务编码规则-销售订单-最小业务流水号长度(3位)
    public static final int CODE_RULE_SALEORDER_LENGTH_DEFAULT = CODE_RULE_LENGTH_3;
    //业务编码规则-销售发货单-最小业务流水号长度(3位)
    public static final int CODE_RULE_SALEDELIVER_LENGTH_DEFAULT = CODE_RULE_LENGTH_3;
    //业务编码规则-销售(无订单)退货单-最小业务流水号长度(3位)
    public static final int CODE_RULE_SALEWARESRETREAT_LENGTH_DEFAULT = CODE_RULE_LENGTH_3;
    //业务编码规则-销售退货单-最小业务流水号长度(3位)
    public static final int CODE_RULE_SALERETREAT_LENGTH_DEFAULT = CODE_RULE_LENGTH_3;

    //业务编码规则-前补零
    public static final String CODE_RULE_BEFORE = "before";
    //业务编码规则-后补零
    public static final String CODE_RULE_AFTER = "after";

    public static final String CODE_RULE_DATEFORMAT_1 = "yyMMdd";
    public static final String CODE_RULE_DATEFORMAT_2 = "yyyyMMdd";
    public static final String CODE_RULE_DATEFORMAT_DEFAULT = CODE_RULE_DATEFORMAT_2;

    //(订单,订单明细)-业务编号前缀
    public static final String CODE_RULE_PREFIX_ORDER = "D";
    public static final String CODE_RULE_PREFIX_ORDERDETAIL = "D";


    //通用编码规则-企业编号+前缀+yyMMdd+流水号
    //0:企业编号
    //1:编码字符串前缀
    //2:日期字符串
    //3:编码流水号
    public static final String CODE_RULE_DEFAULT = "{0}{1}{2}{3}";


    public static final String FIRST_NAME_COMPANY = "company";
    public static final String FIRST_NAME_PREFIX = "prefix";
    public static final String FIRST_NAME_DATE = "date";
    public static final String FIRST_NAME_CODE = "code";

    ///////////////--系统日志--///////////////////////////////////////////////////////////////////
    //Controller类名与业务表对应关系
    public static final Map<String, String> SYSLOGINFO_CLASSNAME2TABLENAME_MAP = new HashMap<String, String>() {{
        //put("类名称", "表名");
        put("DepartmentController", "组织架构");
        put("CompanyController", "企业账号申请");
        put("DictionaryController", "数据字典");
        put("EmployeeController", "员工管理");
        put("MenuButtonController", "管理按钮");
        put("MenuController", "菜单管理");
        put("PostController", "职位管理");
        put("RoleController", "角色权限");
        put("UserController", "用户管理");
        put("MainPageController", "主页及菜单自定义");
        put("UserLoginController", "用户登录");

        //仓库
        put("WarehouseController", "仓库配置");
        put("WarehouseInitialController", "仓库初始化");
        put("WarehouseCheckController", "盘点单");
        put("WarehouseCheckExecutorController", "盘点单-派单");
        put("WarehouseCheckExecuteController", "盘点单执行");

        put("WarehouseInController", "入库管理");
        put("WarehouseInExecutorController", "入库派单");
        put("WarehouseInExecuteController", "入库执行");

        put("WarehouseMoveController", "移库管理");
        put("WarehouseOutController", "出库管理");

        //销售
        put("SaleOrderController", "订单提交");
        put("SaleReceiveController", "应收款管理");
        put("SaleDeliverController", "发货管理");
        put("SaleRetreatController", "退货管理");
        put("SaleInvoiceController", "开票管理");

        //采购
        put("BomController", "齐套分析");
        put("PurchasePlanController", "采购计划管理");
        put("PurchaseOrderController", "采购订单管理");
        put("PurchasePaymentRecordController", "采购应付款");

    }};

    //Controller操作方法前缀
    public static final Map<String, String> SYSLOGINFO_METHODPREFIX_MAP = new HashMap<String, String>() {{
        put("add", "add");
        put("update", "update");
        put("delete", "delete");
    }};

    ///////////////--Dictionary字典表大类ID映射--///////////////////////////////////////////////////////////////////
    public static final Map<String, String> DICTIONARY_MAP = new HashMap<String, String>() {{

        //字典表根节点
        put("root", "8421e4f093a44f029dddbc4ab13068bf");

        //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型
        put("userType", "744f2d88c9f647d0a4d967a714193850");
            //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型-超级管理员
            put("userType_admin", "6839818aecfc41be8f367e62502dfde4");

            //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型-企业管理员
            put("userType_company", "2fb9bbee46ca4ce1913f3a673a7dd68f");

            //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型-普通用户
            put("userType_employee", "69726efa45044117ac94a33ab2938ce4");

            //userType: 744f2d88c9f647d0a4d967a714193850 //用户类型-外部用户
            put("userType_outer", "028fb82cfbe341b1954834edfa2fc18d");

        //political: 015cecdb7fdd450c8a21c7c97d406aa4 //政治面貌
        put("political", "015cecdb7fdd450c8a21c7c97d406aa4");

        //companyType: 0d779980a3ca4e69ac393a93bca84659 //企业性质
        put("companyType", "0d779980a3ca4e69ac393a93bca84659");

        //deptType: 8421e4f093a44f029dddbc4ab13068be //部门类型
        put("deptType", "8421e4f093a44f029dddbc4ab13068be");

        //基础-产品物料
        //productGenre: 4c4bd1b59954438c8bf56facd97a0c7e 货品属性
        put("productGenre", "4c4bd1b59954438c8bf56facd97a0c7e");
            //备件:productGenreSpare:384cfd1329e648618b5a237ce1038bab
            put("productGenreSpare", "384cfd1329e648618b5a237ce1038bab");

        //productType: a39ac4c1e02e45788eb03a52a5e9a972 货品类型
        put("productType", "a39ac4c1e02e45788eb03a52a5e9a972");
        //基础-产品物料(计量单位-结算单位)-公用一个字典id
        //productUnit: 0ae6e79890db490585e13f34bf00ea4b 货品计量单位
        put("productUnit", "0ae6e79890db490585e13f34bf00ea4b");

        //基础-客户供应商
        //customerGenre:b166cc9397744f0cbbea3244647305ee(字典id)  客户供应商属性(字典名称)
        put("customerSupplierGenre", "b166cc9397744f0cbbea3244647305ee");
            //customerGenre:  df7cb67fca4148bc9632c908e4a7fdea(字典id)  客户(字典名称)
            put("customerGenre", "df7cb67fca4148bc9632c908e4a7fdea");

            //supplierGenre: 49c0a7ebcb4c4175bd5195837a6a9a13(字典id)  供应商(字典名称)
            put("supplierGenre", "49c0a7ebcb4c4175bd5195837a6a9a13");

        //supplierType:a50dcf66b14a440282eed9e26c1d9482 (字典id)  客户类型  (字典名称)
        put("customerType", "a50dcf66b14a440282eed9e26c1d9482");

        //supplierType:d28640b12a454246b172c49a604a89f5(字典id)  供应商类型(字典名称)
        put("supplierType", "d28640b12a454246b172c49a604a89f5");

        //equipmentType:7d24edc83dcf4619b618bf0b0eba2851(字典id)  设备类型(字典名称)
        put("equipmentType", "7d24edc83dcf4619b618bf0b0eba2851");

        //e8d9461fbe704fdcbe2ee88efb310f2c(字典id)  省直辖市(字典名称)
        put("province", "e8d9461fbe704fdcbe2ee88efb310f2c");

        //仓库货位表(vmes_warehouse_position)
        put("warehouseRoot", "df930aaecb7111e884ad00163e105f05");
            //实体库
            put("warehouseEntity", "2d75e49bcb9911e884ad00163e105f05");
            //虚拟库
            put("warehouseVirtual", "56f5e83dcb9911e884ad00163e105f05");
            //备件库名称定义
            put("warehouseSpareName", "备件库");

        //db46547d1bcb4c14baa228db1e8aaffe(字典id)  部门货位名称(字典名称)
        put("departmentPosition", "db46547d1bcb4c14baa228db1e8aaffe");
            //364398458ba149eeb6418def062dc93c 物料区
            put("deptProduct", "364398458ba149eeb6418def062dc93c");
            //edb488c6b89e44dd98749e208bf51cc1 待检区
            put("deptCheck", "edb488c6b89e44dd98749e208bf51cc1");
            //1e1585114d2246beabecaae868e62d37 合格区
            put("deptStandard", "1e1585114d2246beabecaae868e62d37");
            //d723af8e2a884e6abb9487c4701a0e7c 不合格区
            put("deptNotStandard", "d723af8e2a884e6abb9487c4701a0e7c");
            //a42f32ef9d39476e932e1e30b77fefbb 其他
            put("deptOther", "a42f32ef9d39476e932e1e30b77fefbb");

        //d7fc4c594c5a4c8e81266c52cbf30110(字典id)  仓库类型(字典名称)
        put("warehouseType", "d7fc4c594c5a4c8e81266c52cbf30110");

        //550e0457a3d34149ba3199d90b0ae198(字典id)  入库类型(字典名称)
        put("warehouseInType", "550e0457a3d34149ba3199d90b0ae198");
            //生产入库 2c4a104aedc046848455f5fa6e3da87c
            put("produceIn", "2c4a104aedc046848455f5fa6e3da87c");
            //生产退料 8e35209f19804e94940f076c8d71b955
            put("produceRetreatIn", "8e35209f19804e94940f076c8d71b955");

            //销售退货入库 81907167d5c8498692e6c4f3694c5cfa
            put("saleRetreatIn", "81907167d5c8498692e6c4f3694c5cfa");
            //销售变更退货入库 d1c6dc9aa3b045dbabff2d5e1e253c22
            put("saleChangeRetreatIn", "d1c6dc9aa3b045dbabff2d5e1e253c22");

            //采购入库 d78ceba5beef41f5be16f0ceee775399
            put("purchaseIn", "d78ceba5beef41f5be16f0ceee775399");

            //c396683796d54b8693b522a2c0ad2793 维修领料退回入库
            put("repairRetreatIn", "c396683796d54b8693b522a2c0ad2793");
            //d9c9eb85db0d4c8faa09ddc2b8173859 保养领料退回入库
            put("maintainRetreatIn", "d9c9eb85db0d4c8faa09ddc2b8173859");

            //备件入库 4ac4616c7b254950af24e0c8eda0c6f7
            put("spareIn", "4ac4616c7b254950af24e0c8eda0c6f7");
            //虚拟库入库 3ba2e016ac78464eb947c727ff41faf0
            put("virtualIn", "3ba2e016ac78464eb947c727ff41faf0");
            //盘盈:盘点入库 4d89ccb1e64f499cbdc6409f173f5407
            put("checkIn", "4d89ccb1e64f499cbdc6409f173f5407");

            //外协入库 064dda15d44d4f8fa6330c5c7e46300e
            //组装入库 80ac00503a1d41e5926be50f93c32c54


        //20c09785a7b54ec280d10c988be21929(字典id)  出库类型(字典名称)
        put("warehouseOutType", "20c09785a7b54ec280d10c988be21929");
            //9459be975cd94ada8443cdf32f52c2be 销售发货出库
            put("saleOut", "9459be975cd94ada8443cdf32f52c2be");
            //cb0a55d81e754cd3b59715d225dab087 生产领料出库
            put("produceOut", "cb0a55d81e754cd3b59715d225dab087");
            //4cba5d3815644b26920777512a20474b 采购退货出库
            put("purchaseOut", "4cba5d3815644b26920777512a20474b");
            //609fa44c031847569a1d9e2516be4b41 组装出库
            put("assembleOut", "609fa44c031847569a1d9e2516be4b41");
            //fa51ae2e17a9409d822fc4c9192d652c 维修领料出库
            put("repairReceiveOut", "fa51ae2e17a9409d822fc4c9192d652c");
            //8bcbc84893cf46daabbd2522bee482ad 保养领料出库
            put("maintainReceiveOut", "8bcbc84893cf46daabbd2522bee482ad");
            //备件出库 12e84fefddc449a78cc3bf8075475823
            put("spareOut", "12e84fefddc449a78cc3bf8075475823");
            //虚拟库出库 9c7be2410a0045c8963f9273fe6da067
            put("virtualOut", "9c7be2410a0045c8963f9273fe6da067");
            //盘亏:盘点出库 55bdf3529c3c463489670a46c2651c1e
            put("checkOut", "55bdf3529c3c463489670a46c2651c1e");
            //报废处理:报废出库 def4b13175dc44b08d7a0315b811700e
            put("scrapOut", "def4b13175dc44b08d7a0315b811700e");
            //办公用品出库 f2b0aaceff914acd8636700a111ca8f0
            put("officeOut", "f2b0aaceff914acd8636700a111ca8f0");

//        //a804369b392c4035bc766cc536b29d35 备件库入库类型
//        put("warehouseInTypeBySpare", "a804369b392c4035bc766cc536b29d35");
//            //1df17a08d9c243beb20621967e517f95 维保退库
//            put("repairRetreatInBySpare", "1df17a08d9c243beb20621967e517f95");
//            //d21439b31f624a74b8cf0e60cd264bbd 采购入库
//            put("purchaseInBySpare", "d21439b31f624a74b8cf0e60cd264bbd");
//            //071aa97f4b97460eb5c2f9f16a9463a2 手动入库
//
//        //e3a230b566714fa885fca490486098f7 备件库出库类型
//        put("warehouseOutTypeBySpare", "e3a230b566714fa885fca490486098f7");
//            //e1a4ccb5a2d44dc79668d18de6983582 领料出库
//            put("receiveOutBySpare", "e1a4ccb5a2d44dc79668d18de6983582");
//            //e4e41774648b47febce36e08d4649e58 采购退货出库
//            put("purchaseOutBySpare", "e4e41774648b47febce36e08d4649e58");
//            //4bb95a5b9b4e470eb1b43d87fd89671c 手动出库

        //95c01e57c01e4b869a1940b8ded3c315(移库类型id)  移库类型(字典名称)
        put("warehouseMoveType", "95c01e57c01e4b869a1940b8ded3c315");

        //a66d180a1a3346a1a3f4f224baf4ada8(退单原因id)  退单原因(字典名称)
        put("warehouseRebackBill", "a66d180a1a3346a1a3f4f224baf4ada8");

        //ab57102457484aa88d70cb805c0a796c(采购原因id)  采购原因(字典名称)
        put("purchaseReason", "ab57102457484aa88d70cb805c0a796c");

        //e8d9461fbe704fdcbe2ee88efb310f2c(地区id)  地区(字典名称)
        put("area", "e8d9461fbe704fdcbe2ee88efb310f2c");

        //566b1b81bb6343be9e68bdbe1a460916 售购采销退货类型
        put("retreatType", "566b1b81bb6343be9e68bdbe1a460916");

            //f69839bbf2394846a65894f0da120df9 退货退款
            put("retreatRefund", "f69839bbf2394846a65894f0da120df9");
            //c90c2081328c427e8d65014d98335601 退货换货
            put("retreatChange", "c90c2081328c427e8d65014d98335601");

        //设备保养方式: 7ef6384e92a343ccb839112a5d59b2fe (vmes_dictionary.id) 设备保养计划(模块)
        put("equipmentMaintainMode", "7ef6384e92a343ccb839112a5d59b2fe");
            //maintainModeCustom ee66976e1b3d453bae8839e6e9458b2f 自定义
            put("maintainModeCustom", "ee66976e1b3d453bae8839e6e9458b2f");
            //maintainModePeriod 9a05a30aa81e4637b498703b14cde8b1 按周期
            put("maintainModePeriod", "9a05a30aa81e4637b498703b14cde8b1");
    }};

    ///////////////--菜单名称--///////////////////////////////////////////////////////////////////
    //Map<菜单key, 菜单id>
    public static final Map<String, String> SYS_MENU_MAP = new HashMap<String, String>() {{
        //root 节点(智能云管家) pid := root
        put("root", "8b678e8195f711e884ad00163e105f05");

        //home: '主页',
        put("home", "97c8c8150a7746e190a7a440b2119e02");

        //******销售******
        //order: '销售',
        put("sale", "94caec1bca7e4131b16bfcee9b1351e2");

        //******生产******
        //product: '生产',
        put("product", "73883f69b4034b7f89e9d59bc4919a6a");

        //******仓库******
        //warehouseByComplex: '仓库',(复杂版仓库)
        put("warehouseByComplex", "15a6c4ca92fe42a0a82320287538b727");
            //基础 base ff1330a1a96246c0b041451b3212bbee
            put("base", "ff1330a1a96246c0b041451b3212bbee");
                //库位配置 position e4fd9624f7b94e499dd2b7015b3df332
                put("position", "e4fd9624f7b94e499dd2b7015b3df332");
                //库位初始化 () bb00131c20b64b1da92321b0f71ef129

        //warehouseBySimple '仓库'(简版仓库)
        put("warehouseBySimple", "5abe8f434e114a87a73b85ed74bc78e7");

        //table: '报表',
        put("table", "156b96f3245d4a5ba45ca3c2e67fd803");

        //******采购******
        //purchase: 采购
        put("purchase", "3f5e1bcd2d3745998773413ccbded554");

        //******人事******
        //emp: '人事',
        put("emp", "a962ac5d572d46c38d75d644974abc80");
            //department: '组织管理',
            put("department", "b0f2e472c3b84a3eba7839cb3ebc01b8");
            //post: '岗位管理',
            put("post", "7a8e501d59544e4fa61636d2b7813751");
            //employee: '员工管理',
            put("employee", "093a0bc9346044afaa814ea17a74d073");

        //******质量******
        //quality: '质量',
        put("quality", "c93a3ca8d720434dbee9a87482931772");

        //******系统设置******
        //system: '系统设置',
        put("system", "9446a05883cf4669aecd710e663aea39");
            //company: '企业账号申请',
            put("company", "55004941ca4440e09d01975829f797b0");
            //user: '用户管理',
            put("user", "59de75701c53408a857f0179fcaa7a09");
            //menumanage: '菜单管理',
            put("menumanage", "6eb9766b1396425b84cd5cd7d3bb464b");
            //template: '系统开发模板',
            put("template", "dccbd97d7b404c0ab5b76b36b2521d8d");
            //dictionary: '数据字典',
            put("dictionary", "8e7fef00aedc4da880b4c06399f47ce1");
            //role: '角色权限',
            put("role", "33112d776e7140e98002df71785de23c");
            //auths: '权限管理',
            //accountHome: '自定义主页',
            put("accountHome", "e1206343ddc94ec4b072adedb4ba40aa");
            //logInfo: '操作日志',
            put("logInfo", "cba143d6d51b4d4288071eb3bad56606");

        //******基础******
        //base: '基础',
        put("base", "be55b2f9cb6b4b6e8b77b8a9d4514b24");
            //product: 产品物料,
            put("product", "9675556924de4a34bd14e9420311e751");
            //equipment: 设备信息,
            put("equipment", "02e604fde496494993dfa4f6d7511c84");
            //customer: 客户供应商,
            put("customer", "66628bbd6bdd4f47a903feaec2feab59");

        //******设备******
        put("equipment", "77692fa6a7ae4b4aabcef8d10d7671f4");
            //equipmentRepair 设备维修 bfbb17fb01e44c648b1938b0e131202c
            put("equipmentRepair", "bfbb17fb01e44c648b1938b0e131202c");
            //equipmentMaintain 设备保养 c9d0c50536c74470b990887d939a2041
            put("equipmentMaintain", "c9d0c50536c74470b990887d939a2041");

        //******App 手机主页面菜单******
        put("app", "ab8a6ef2480f4fa0a6445524a33a8181");
    }};

    //warehouseByComplex:复杂版仓库:15a6c4ca92fe42a0a82320287538b727(菜单id)
    //warehouseBySimple:简版仓库:5abe8f434e114a87a73b85ed74bc78e7(菜单id)
    public static final Map<String, String> SYS_WAREHOUSE_NAMEKEY_MAP = new HashMap<String, String>() {{
        put(SYS_WAREHOUSE_COMPLEX, SYS_MENU_MAP.get("warehouseByComplex"));
        put(SYS_WAREHOUSE_SIMPLE, SYS_MENU_MAP.get("warehouseBySimple"));
    }};

    //关于仓库(出库,入库)反查业务数据(发货单,采购单)
    public static final Map<String, String> SYS_WAREHOUSE_MENU_MAP = new HashMap<String, String>() {{
        //sale:94caec1bca7e4131b16bfcee9b1351e2: 销售
        put("94caec1bca7e4131b16bfcee9b1351e2", "销售");

        //purchase:3f5e1bcd2d3745998773413ccbded554: 采购
        put("3f5e1bcd2d3745998773413ccbded554", "采购");

        //equipment:77692fa6a7ae4b4aabcef8d10d7671f4 设备
        put("77692fa6a7ae4b4aabcef8d10d7671f4", "设备");

    }};

//    //收费菜单项目
//    public static final Map<String, String> SYS_MENU_PAYMENT_MAP = new HashMap<String, String>() {{
//        //sale: '销售',
//        put("sale", "94caec1bca7e4131b16bfcee9b1351e2");
//        //product: '生产',
//        put("product", "73883f69b4034b7f89e9d59bc4919a6a");
//        //warehouse: '仓库',
//        put("warehouse", "15a6c4ca92fe42a0a82320287538b727");
//        //purchase: 采购
//        put("purchase", "3f5e1bcd2d3745998773413ccbded554");
//    }};
}
