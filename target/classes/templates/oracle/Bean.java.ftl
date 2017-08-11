package ${param.packageName}.${param.modelName}.bean;
/**
  * ${param.beanName}实体类
  *@author ${param.autherName}
  *
  */
public class ${param.beanName}{

	<#list fieldlist as list>
		<#if list.columnComment??>
		/**
		  *${list.columnComment}
		  */
		</#if>
    	private ${list.javaType} ${list.columnName?lower_case};
	</#list>
	
	<#list fieldlist as list>
    	public ${list.javaType} get${list.columnName?lower_case?cap_first}(){
    		return ${list.columnName?lower_case};
    	}
    	public void set${list.columnName?lower_case?cap_first}(${list.javaType} ${list.columnName?lower_case}){
    		this.${list.columnName?lower_case} = ${list.columnName?lower_case};
    	}
	</#list>
}