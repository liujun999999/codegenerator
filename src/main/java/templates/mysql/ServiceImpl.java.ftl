package ${param.packageName}.${param.modelName}.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${param.packageName}.${param.modelName}.bean.${param.beanName};
import ${param.packageName}.${param.modelName}.mapper.${param.beanName}Mapper;
import ${param.packageName}.${param.modelName}.service.${param.beanName}Service;
import ${param.pageclass};
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
/**
  *
  *@author ${param.autherName}
  *
  */
@Service
public class ${param.beanName}ServiceImpl implements ${param.beanName}Service {
	@Autowired
	private ${param.beanName}Mapper ${param.beanName?lower_case}Mapper;
	
	@Override
	public int deleteByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case}) {
		return ${param.beanName?lower_case}Mapper.deleteByPrimaryKey(${param.primaryKey?lower_case});
	}

	@Override
	public int insertSelective(${param.beanName} record) {
		return ${param.beanName?lower_case}Mapper.insertSelective(record);
	}

	@Override
	public ${param.beanName} selectByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case}) {
		return ${param.beanName?lower_case}Mapper.selectByPrimaryKey(${param.primaryKey?lower_case});
	}

	@Override
	public int updateByPrimaryKeySelective(${param.beanName} record) {
		return ${param.beanName?lower_case}Mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Map<String, Object> queryForDatagrid(${param.beanName} record, int page, int rows) {
		Page p = new Page();
		p.setRecord(record);
		p.setPage((page-1)*rows);
		p.setRows(rows);
		int total = ${param.beanName?lower_case}Mapper.queryForDatagridCnt(record);
		List<${param.beanName}> list = null;
		if(total>0){
			list = ${param.beanName?lower_case}Mapper.queryForDatagrid(p);
		}else{
			list = new ArrayList<>();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list==null?new ArrayList<>():list);
		return map;
	}

}
