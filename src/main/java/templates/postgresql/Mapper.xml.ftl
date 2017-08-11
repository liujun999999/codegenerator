<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${param.packageName}.${param.modelName}.mapper.${param.beanName}Mapper">
  <sql id="Base_Column_List">
    <#list fieldlist as list>
   		${list.columnName}<#sep>,</#sep>
	</#list>
  </sql>
  <select id="selectByPrimaryKey" parameterType="${param.primaryJavaType}" resultType="${param.packageName}.${param.modelName}.bean.${param.beanName}">
    select 
    <include refid="Base_Column_List" />
    from ${param.tableName}
    where ${param.primaryKey} = ${r'#{'}${param.primaryKey},jdbcType=${param.primaryType}}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="${param.primaryJavaType}">
    delete from ${param.tableName}
    where ${param.primaryKey} = ${r'#{'}${param.primaryKey},jdbcType=${param.primaryType}}
  </delete>
  <insert id="insertSelective" parameterType="${param.packageName}.${param.modelName}.bean.${param.beanName}">
    insert into ${param.tableName} 
    <trim prefix="(" suffix=")" suffixOverrides="," >
    <#list fieldlist as list>
    	<#if list.columnKey??>
    	<#else>
    	<if test="${list.columnName?lower_case} != null" >
	    	${list.columnName},
	    </if>
   		</#if>
	</#list>
	</trim>
    <trim prefix="values (" suffix=")" suffixOverrides="," >
    <#list fieldlist as list>
	<#if list.columnKey??>
	<#else>
    	<if test="${list.columnName?lower_case} != null" >
		${r'#{'}${list.columnName?lower_case},jdbcType=${list.jdbcType}},
	   </if>
	</#if>
	</#list>
    </trim>
  </insert>
  <update id="updateByPrimaryKeySelective" parameterType="${param.packageName}.${param.modelName}.bean.${param.beanName}">
    update ${param.tableName}
    <set>
    <#list fieldlist as list>
		<#if list.columnKey??>
		<#else>
		    <if test="${list.columnName?lower_case} != null">
		        ${list.columnName} = ${r'#{'}${list.columnName?lower_case},jdbcType=${list.jdbcType}},
		    </if>
		</#if>
	</#list>
    </set>
    where ${param.primaryKey} = ${r'#{'}${param.primaryKey},jdbcType=${param.primaryType}}
  </update>
  <select id="queryForDatagrid" resultType="${param.packageName}.${param.modelName}.bean.${param.beanName}" parameterType="${param.pageclass}" >
    select <include refid="Base_Column_List" />
    from ${param.tableName} a
    order by a.${param.primaryKey}
     limit ${r'#{'}rows} offset ${r'#{'}rows}*(${r'#{'}page}-1)
  </select>
  <select id="queryForDatagridCnt" resultType="int" parameterType="${param.packageName}.${param.modelName}.bean.${param.beanName}">
    select count(*)
    from ${param.tableName}
  </select>
</mapper>