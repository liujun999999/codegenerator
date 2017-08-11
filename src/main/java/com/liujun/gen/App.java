package com.liujun.gen;

import com.liujun.gen.ui.CodeGenUI;

public class App {
	public static String propertiesFile;
	
	public static void main(String[] args) {
		if(args.length>0){
			propertiesFile = args[0];
		}
		if(propertiesFile == null || propertiesFile.equals("")){
			propertiesFile = "jdbc";
		}
		CodeGenUI ui = new CodeGenUI();
		ui.setVisible(true);
	}

}
