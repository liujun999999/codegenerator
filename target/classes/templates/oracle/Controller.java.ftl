package ${param.packageName}.${param.modelName}.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${param.packageName}.${param.modelName}.service.${param.beanName}Service;
import ${param.packageName}.${param.modelName}.bean.${param.beanName};
import com.hky.common.util.DateEditor;
import com.hky.common.util.ResultBean;

@Controller
public class ${param.beanName}Controller {
	@Autowired
	private ${param.beanName}Service ${param.beanName?lower_case}Service;

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		// 对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	@RequestMapping("${param.urlprefix}/${param.beanName?lower_case}/deleteByPrimaryKey.${param.urlsuffix}")
	@ResponseBody
	public String deleteByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case}){
		String reobj = "";
		try{
			${param.beanName?lower_case}Service.deleteByPrimaryKey(${param.primaryKey?lower_case});
			reobj = ResultBean.SUCCESS;
		}catch(Exception e){
			reobj = e.getMessage();
		}
		return reobj;
	}

	@RequestMapping("${param.urlprefix}/${param.beanName?lower_case}/insertSelective.${param.urlsuffix}")
	@ResponseBody
	public String insertSelective(${param.beanName} record){
		String reobj = "";
		try{
			${param.beanName?lower_case}Service.insertSelective(record);
			reobj = ResultBean.SUCCESS;
		}catch(Exception e){
			reobj = e.getMessage();
		}
		return reobj;
	}

	@RequestMapping("${param.urlprefix}/${param.beanName?lower_case}/selectByPrimaryKey.${param.urlsuffix}")
	@ResponseBody
	public Object selectByPrimaryKey(${param.primaryJavaType} ${param.primaryKey?lower_case}){
		Object reobj = null;
		try{
			reobj=${param.beanName?lower_case}Service.selectByPrimaryKey(${param.primaryKey?lower_case});
		}catch(Exception e){
			reobj = e.getMessage();
		}
		return reobj;
	}

	@RequestMapping("${param.urlprefix}/${param.beanName?lower_case}/updateByPrimaryKeySelective.${param.urlsuffix}")
	@ResponseBody
	public String updateByPrimaryKeySelective(${param.beanName} record){
		String reobj = "";
		try{
			${param.beanName?lower_case}Service.updateByPrimaryKeySelective(record);
			reobj = ResultBean.SUCCESS;
		}catch(Exception e){
			reobj = e.getMessage();
		}
		return reobj;
	}

	@RequestMapping("${param.urlprefix}/${param.beanName?lower_case}/queryForDatagrid.${param.urlsuffix}")
	@ResponseBody
	public Object queryForDatagrid(${param.beanName} record, int limit, int offset){
		Object reobj = null;
		try{
			reobj = ${param.beanName?lower_case}Service.queryForDatagrid(record,offset/10+1,limit);
		}catch(Exception e){
			reobj = e.getMessage();
		}
		return reobj;
	}
	
}
