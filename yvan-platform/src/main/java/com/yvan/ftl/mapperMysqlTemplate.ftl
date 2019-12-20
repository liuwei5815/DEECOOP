<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
说明：${TITLE} Mapper.xml
创建人：${author} 自动创建
创建时间：${nowDate?string("yyyy-MM-dd")}
 -->
<mapper namespace="${classPath}.${objectName}Mapper">

    <!--表名 自动创建，禁止修改-->
    <sql id="tableName">
	${table}
    </sql>

    <!-- 字段 自动创建，禁止修改-->
    <sql id="Field">
	<#list fieldList as var>
		<#if var[7] == "否">
		${var[0]} ${var[1]},<!-- ${var[3]} -->
		</#if>
	</#list>
	<#list fieldList as var>
		<#if var[7] == "是">
		${var[0]} ${var[1]} <!-- ${var[1]} -->
		</#if>
	</#list>
    </sql>

    <!-- 字段值 自动创建，禁止修改-->
    <sql id="FieldValue">
	<#list fieldList as var>
		<#if var[7] == "否">
		${r"#{"}${var[1]}${r"}"},<!-- ${var[3]} -->
		</#if>
	</#list>
	<#list fieldList as var>
		<#if var[7] == "是">
		${r"#{"}${var[1]}${r"}"} <!-- ${var[1]} -->
		</#if>
	</#list>
    </sql>


    <!-- 字段名称  自动创建，禁止修改-->
    <sql id="Column">
    <#list fieldList as var>
        <#if var[7] == "否">
        '${var[3]}'  ${var[1]},
        </#if>
    </#list>
    <#list fieldList as var>
        <#if var[7] == "是">
        '${var[1]}' ${var[1]}
        </#if>
    </#list>
    </sql>

    <!-- 自动创建，禁止修改 -->
    <select id="findColumnList"  resultType="java.util.LinkedHashMap">
        select
        <include refid="Column"></include>
        from dual
    </select>

    <!-- 自动创建，禁止修改 -->
    <select id="findDataList"  parameterType="com.yvan.PageData"  resultType="java.util.Map">
        select
        <include refid="Field"></include>
        from
        <include refid="tableName"></include>
        <where>
            <!--queryStr 自定义sql查询语句-->
            <if test='queryStr != null and queryStr!=""' >
                and ${r"${"}queryStr${r"}"}
            </if>
        </where>
        <if test='orderStr != null and orderStr != ""' >
            order by ${r"${"}orderStr${r"}"}
        </if>
    </select>

    <!-- 列表 自动创建，禁止修改-->
    <select id="datalistPage" parameterType="com.yvan.PageData" resultType="com.xy.vmes.entity.${objectName}">
        select
        <include refid="Field"></include>
        from
        <include refid="tableName"></include>
        <where>
            <!--queryStr 自定义sql查询语句-->
            <if test='queryStr != null and queryStr!=""' >
                and ${r"${"}queryStr${r"}"}
            </if>
        </where>
    </select>


    <!-- 批量删除 自动创建，禁止修改-->
    <delete id="deleteByIds" parameterType="java.lang.String">
        delete from
        <include refid="tableName"></include>
        where
        id in
        <foreach item="item" index="index" collection="array" open="(" separator="," close=")">
		${r"#{item}"}
        </foreach>
    </delete>


    <!-- 自动创建，禁止修改-->
    <update id="updateToDisableByIds" parameterType="java.lang.String" >
        update
        <include refid="tableName"></include>
        set isdisable = '0' ,udate = now()
        where
        id in
        <foreach item="item" index="index" collection="array" open="(" separator="," close=")">
        ${r"#{item}"}
        </foreach>
    </update>

    <!-- 自动创建，禁止修改-->
    <update id="updateByDefined" parameterType="com.yvan.PageData" >
        update
        <include refid="tableName"></include>
        set  ${r"${"}updateStr${r"}"} ,udate = now()
        where
        ${r"${"}queryStr${r"}"}
    </update>


    <!-- ***************************************************以上为自动生成代码禁止修改，请在下面添加业务代码************************************************* -->
    <insert id="insertColumn" >
        delete from vmes_column where model_code = '${modelCode}' ;
        insert into vmes_column (
        id,model_code,title_key,title_name,serial_number,
        isdisable,ishide,isedit,ismust,cdate,
        cuser
        ) VALUES
    <#list fieldList as var>
        <#if var[7] == "否">
            (replace(uuid(), '-', ''),'${modelCode}','${var[1]}','${var[3]}',${var[8]}, '1','1','1','0',now(), 'admin'),
        </#if>
    </#list>
    <#list fieldList as var>
        <#if var[7] == "是">
            (replace(uuid(), '-', ''),'${modelCode}','${var[1]}','${var[1]}',${var[8]}, '1','1','1','0',now(), 'admin')
        </#if>
    </#list>
    </insert>


    <!-- 列表(全部) 自动创建，禁止修改-->
    <select id="dataList" parameterType="com.yvan.PageData" resultType="com.xy.vmes.entity.${objectName}">
        select
        <include refid="Field"></include>
        from
        <include refid="tableName"></include>
        <where>
            <!--isSelfExist 是否考虑自己在业务表中是否存在
                false: (false or is null) 无需考虑自己在业务表中是否存在
                true : 需要考虑自己在业务表中是否存在
            -->
            <if test='id != null and id != ""' >
                <choose>
                    <when test="'true' == isSelfExist">
                        <![CDATA[ and id <> ${r"#{"}id${r"}"} ]]>
                    </when>
                    <otherwise>
                        and id = ${r"#{"}id${r"}"}
                    </otherwise>
                </choose>
            </if>


            <!--queryStr 自定义sql查询语句-->
            <if test='queryStr != null and queryStr != ""'>
                and ${r"${"}queryStr${r"}"}
            </if>
        </where>
        <if test='orderStr != null and orderStr != ""' >
            order by ${r"${"}orderStr${r"}"}
        </if>
    </select>




    <!-- 字段 自动创建，可以修改-->
    <sql id="Field1">
    <#list fieldList as var>
        <#if var[7] == "否">
        ${var[0]} ${var[1]},<!-- ${var[3]} -->
        </#if>
    </#list>
    <#list fieldList as var>
        <#if var[7] == "是">
        ${var[0]} ${var[1]} <!-- ${var[1]} -->
        </#if>
    </#list>
    </sql>

    <!-- 字段值 自动创建，可以修改-->
    <!--
    <sql id="Column1">
    <#list fieldList as var>
        <#if var[7] == "否">
            '${var[3]}'  ${var[1]},
        </#if>
    </#list>
    <#list fieldList as var>
        <#if var[7] == "是">
            '${var[1]}' ${var[1]}_hide
        </#if>
    </#list>
    </sql>
     -->


    <!-- 自动创建，可以修改 -->
    <select id="getDataList"  parameterType="com.yvan.PageData"  resultType="java.util.Map">
        select
        <include refid="Field1"></include>
        from
        <include refid="tableName"></include>
        <where>
            <!--queryStr 自定义sql查询语句-->
            <if test='queryStr != null and queryStr != ""' >
                and ${r"${"}queryStr${r"}"}
            </if>
            <if test='currentCompanyId != null and currentCompanyId != ""'>
                and company_id LIKE CONCAT(CONCAT('%', ${r"#{"}currentCompanyId${r"}"}),'%')
            </if>
            <if test='code != null and code != ""'>
                and code LIKE CONCAT(CONCAT('%', ${r"#{"}code${r"}"}),'%')
            </if>
            <if test='name != null and name != ""'>
                and name LIKE CONCAT(CONCAT('%', ${r"#{"}name${r"}"}),'%')
            </if>
        </where>
        <if test='orderStr != null and orderStr != ""' >
            order by ${r"${"}orderStr${r"}"}
        </if>
    </select>

    <!-- 自动创建，可以修改 -->
    <select id="getDataListPage"  parameterType="com.yvan.PageData"  resultType="java.util.Map">
        select
        <include refid="Field1"></include>
        from
        <include refid="tableName"></include>
        <where>
            <!--queryStr 自定义sql查询语句-->
            <if test='queryStr != null and queryStr != ""' >
                and ${r"${"}queryStr${r"}"}
            </if>
            <if test='code != null and code != ""'>
                and code LIKE CONCAT(CONCAT('%', ${r"#{"}code${r"}"}),'%')
            </if>
            <if test='name != null and name != ""'>
                and name LIKE CONCAT(CONCAT('%', ${r"#{"}name${r"}"}),'%')
            </if>
        </where>
        <if test='orderStr != null and orderStr != ""' >
            order by ${r"${"}orderStr${r"}"}
        </if>
    </select>




</mapper>