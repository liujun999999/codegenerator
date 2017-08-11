package ${param.packageName}.${param.modelName}.mapper;

import ${param.packageName}.${param.modelName}.bean.${param.beanName};
import java.util.List;
import org.springframework.stereotype.Repository;
/**
  *
  *@author ${param.autherName}
  *
  */
@Repository
public interface ${param.beanName}Mapper {
    int deleteByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case});

    int insertSelective(${param.beanName} record);

    ${param.beanName} selectByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case});

    int updateByPrimaryKeySelective(${param.beanName} record);
    
    List<${param.beanName}> queryForDatagrid(${param.pageclass} page);
	int queryForDatagridCnt(${param.beanName} record);
}