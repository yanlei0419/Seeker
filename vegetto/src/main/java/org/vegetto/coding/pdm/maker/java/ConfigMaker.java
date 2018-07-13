package org.vegetto.coding.pdm.maker.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.common.util.WriteFileUtil;

public class ConfigMaker {
	public static void createConfig(MyTable table, String filePath, boolean is_package_exist) throws IOException {
		createSpringXml(table, filePath, is_package_exist);
		createStrutsXml(table, filePath, is_package_exist);
	}

	// 生成SpringXml
	public static void createSpringXml(MyTable table, String filePath, boolean is_package_exist) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件

		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(), true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(), false);
		String boClassName = classNameUp + "Bo";
		//TODO config-path
		filePath += InitCodeConfig.src_path+ table.getPackagePath().replaceAll("\\.", "/") + "/config/";
		
		// 判断是否是需要追加
		StringBuffer sb_exist = new StringBuffer();//保存源文件中的内容
		if (is_package_exist) {
			BufferedReader input = new BufferedReader(new FileReader(filePath + "spring3.xml"));
			String s = "";
			while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
				if(s.contains("</beans>")){//读取到有结束标签的那一行
					break;
				}
				sb_exist.append(s+"\r\n");
			}
			sb.append(sb_exist);//源文件中信息保存到sb变量中
			sb.append("" + "\r\n");
		//不是追加的，先添加头
		}else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\r\n");
			sb.append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\" xmlns:context=\"http://www.springframework.org/schema/context\" xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd\">" + "\r\n");
		}
		//TODO config_bean
		sb.append("	<bean id=\"" + classNameUp + "Biz\" class=\""+InitCodeConfig.pack + table.getPackagePath() + ".biz.impl." + classNameUp + "BizImpl\">" + "\r\n");
		sb.append("		<property name=\"dao\" ref=\"" + classNameUp + "Dao\" />" + "\r\n");
		sb.append("	</bean>" + "\r\n");
		sb.append("	<bean id=\"" + classNameUp + "Dao\" class=\"" +InitCodeConfig.pack+ table.getPackagePath() + ".dao.jdbc." + classNameUp + "DaoImpl\" />" + "\r\n");
		sb.append("</beans>" + "\r\n");
		
		sb.append("" + "\r\n");
		
		sb.append("<!--" + "\r\n");
		sb.append("	请在spring.xml中引入本文件:" + "\r\n");
		//TODO config-bean
		sb.append("	<import resource=\""+InitCodeConfig.config_path + table.getPackagePath().replaceAll("\\.", "/") + "/config/spring3.xml\"/>" + "\r\n");
		sb.append("-->" + "\r\n");

		

		WriteFileUtil.writeFile(filePath, "spring3.xml",sb.toString());
	}

	// 生成StrutsXml
	public static void createStrutsXml(MyTable table, String filePath, boolean is_package_exist) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件

		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(), true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(), false);
		String boClassName = classNameUp + "Bo";
		filePath += InitCodeConfig.src_path + table.getPackagePath().replaceAll("\\.", "/") + "/config/";
		
		
		// 判断是否是需要追加
		StringBuffer sb_exist = new StringBuffer();//保存源文件中的内容
		if (is_package_exist) {
			BufferedReader input = new BufferedReader(new FileReader(filePath + "struts2.xml"));
			String s = "";
			while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
				if(s.contains("</struts>")){//读取到有结束标签的那一行
					break;
				}
				sb_exist.append(s+"\r\n");
			}
			sb.append(sb_exist);//源文件中信息保存到sb变量中
			sb.append("" + "\r\n");
		//不是追加的，先添加头
		}else {
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\r\n");
			sb.append("<!DOCTYPE struts PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN\" \"http://struts.apache.org/dtds/struts-2.3.dtd\">" + "\r\n");
			sb.append("<struts>" + "\r\n");
		}
		
		sb.append("	<package name=\"struts2_" + classNameUp + "\" extends=\"struts-default\">" + "\r\n");
		//TODO config-strtus
		sb.append("		<action name=\"" + classNameUp + "Manage_*\" class=\""+InitCodeConfig.pack + table.getPackagePath() + ".action." + classNameUp + "Action\" method=\"{1}\">" + "\r\n");
		sb.append("			<result name=\"toList\" type=\"redirect\">" + table.getUrlPath().replaceAll("\\.", "/") + "/list.jsp</result>" + "\r\n");
		sb.append("			<result name=\"add\" type=\"redirect\">" + table.getUrlPath().replaceAll("\\.", "/") + "/list.jsp</result>" + "\r\n");
		sb.append("			<result name=\"toUpdate\" type=\"dispatcher\">" + table.getUrlPath().replaceAll("\\.", "/") + "/update.jsp</result>" + "\r\n");
		sb.append("			<result name=\"toDetail\" type=\"dispatcher\">" + table.getUrlPath().replaceAll("\\.", "/") + "/detail.jsp</result>" + "\r\n");
		sb.append("		</action>" + "\r\n");
		sb.append("	</package>" + "\r\n");
		sb.append("</struts>" + "\r\n");

		sb.append("" + "\r\n");

		sb.append("<!--" + "\r\n");
		sb.append("	请在struts.xml中引入本文件:" + "\r\n");
		//TODO config-struts
		sb.append("	<include file=\""+InitCodeConfig.config_path + table.getPackagePath().replaceAll("\\.", "/") + "/config/struts2.xml\"/>" + "\r\n");
		sb.append("-->" + "\r\n");
		
		WriteFileUtil.writeFile(filePath, "struts2.xml",sb.toString());
		
	}

}
