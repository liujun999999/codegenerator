package com.liujun.gen.logic;

import java.util.List;

import com.liujun.gen.bean.BaseParam;
import com.liujun.gen.bean.TableField;

public interface Generator {
	public void createAllFile(BaseParam param,List<TableField> fieldlist);
	public void createFile(String templatepath,String filepath,BaseParam param,List<TableField> fieldlist);
}
