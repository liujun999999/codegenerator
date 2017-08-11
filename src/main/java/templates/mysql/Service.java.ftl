package ${param.packageName}.${param.modelName}.service;
import java.util.Map;
import ${param.packageName}.${param.modelName}.bean.${param.beanName};
/**
  *
  *@author ${param.autherName}
  *
  */
public interface ${param.beanName}Service{

	int deleteByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case});

    int insertSelective(${param.beanName} record);

    ${param.beanName} selectByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case});

    int updateByPrimaryKeySelective(${param.beanName} record);
    
    Map<String,Object> queryForDatagrid(${param.beanName} record,int page,int rows);
}