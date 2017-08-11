package com.liujun.gen.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.liujun.gen.App;
import com.liujun.gen.bean.BaseParam;
import com.liujun.gen.logic.GeneratorFactory;
import com.liujun.gen.util.DbUtil;



public class CodeGenUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 333171224914398861L;
	private JTextArea log;
	private Container container;
	private JPanel toppanel;
	private String dirdef;
	private String packagedef;
	private String authordef;
	private String urlprefixdef;
	private String urlsuffixdef;
	
	private JTextField dirtf;
	private JTextField moduleNametf;
	private JTextField modulePackagetf;
	private JTextField authortf;
	private JTextField tabletf;
	private JTextField beantf;
	private JTextField urlprefixtf;
	private JTextField urlsuffixtf;
	private BaseParam param = new BaseParam();
	public CodeGenUI() {
		super("java后台代码产生器");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		log = new JTextArea("");
		container = this.getContentPane();
		toppanel = new JPanel();
		toppanel.setLayout(new GridLayout(7, 1));
		container.add(toppanel, BorderLayout.NORTH);
		container.add(log, BorderLayout.CENTER);
		ResourceBundle rb = ResourceBundle.getBundle(App.propertiesFile);
		dirdef = rb.getString("maindir");
		packagedef = rb.getString("packagename");
		authordef = rb.getString("author");
		urlprefixdef = rb.getString("urlprefix");
		urlsuffixdef = rb.getString("urlsuffix");
		
		param.setPageclass(rb.getString("pageclass"));
		
		initMainDir();
		initModuleName();
		initModulePackage();
		initAutuorName();
		initBeanModel();
		initURLModel();
		initGenButton();
	}
	private void initMainDir() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("生成目录:");
		p.add(t);
		final JTextField tf = new JTextField(25);
		tf.setText(dirdef);
		dirtf = tf;
		p.add(tf);
		JButton j = new JButton("选择目录");
		p.add(j);
		j.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setSelectedFile(new File(dirtf.getText()));
				chooser.setApproveButtonText("确定");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 设置只选择目录
				int returnVal = chooser.showOpenDialog(CodeGenUI.this);
				System.out.println("returnVal=" + returnVal);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					tf.setText(chooser.getSelectedFile().getPath());
				}
			}
		});
		toppanel.add(p);
	}
	private void initModuleName() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("模块名称:");
		p.add(t);
		final JTextField tf = new JTextField(33);
		moduleNametf = tf;
		p.add(tf);

		toppanel.add(p);
	}

	private void initModulePackage() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("模块包名:");
		p.add(t);
		final JTextField tf = new JTextField(33);
		modulePackagetf = tf;
		tf.setText(packagedef);
		p.add(tf);
		toppanel.add(p);
	}
	private void initAutuorName() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("作者名字:");
		p.add(t);
		final JTextField tf = new JTextField(33);
		authortf = tf;
		tf.setText(authordef);
		p.add(tf);

		toppanel.add(p);
	}

	private void initBeanModel() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("表名:");
		p.add(t);
		final JTextField tf = new JTextField(14);
		tabletf = tf;
		p.add(tf);
		JLabel t1 = new JLabel();
		t1.setText("bean名称:");
		p.add(t1);
		final JTextField tf1 = new JTextField(14);
		beantf = tf1;
		p.add(tf1);
		toppanel.add(p);
	}
	private void initURLModel() {
		JPanel p = new JPanel();
		JLabel t = new JLabel();
		t.setText("URL前缀:");
		p.add(t);
		final JTextField tf = new JTextField(14);
		urlprefixtf = tf;
		tf.setText(urlprefixdef);
		p.add(tf);
		JLabel t1 = new JLabel();
		t1.setText("url后缀:");
		p.add(t1);
		final JTextField tf1 = new JTextField(14);
		urlsuffixtf = tf1;
		tf1.setText(urlsuffixdef);
		p.add(tf1);
		toppanel.add(p);
	}
	public void initGenButton() {
		JPanel p = new JPanel();
		final JButton jb = new JButton("生成");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					jb.setEnabled(false);
					param.setAutherName(authortf.getText());
					param.setBeanName(beantf.getText());
					param.setCreatedir(dirtf.getText());
					param.setModelName(moduleNametf.getText());
					param.setPackageName(modulePackagetf.getText());
					param.setTableName(tabletf.getText());
					param.setUrlprefix(urlprefixtf.getText());
					param.setUrlsuffix(urlsuffixtf.getText());
					if(param.checkdata()){
						GeneratorFactory.createCenerator().createAllFile(param, DbUtil.loadfield(param.getTableName()));
						JOptionPane.showMessageDialog(null, "生成成功，请刷新项目", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}finally{
					jb.setEnabled(true);
				}
			}
		});
		p.add(jb);
		toppanel.add(p);
	}
}
