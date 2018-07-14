package org.seeker.common.base.license.config;

import org.seeker.common.util.DateUtil;

public class LicenseConfig {

	public static String outFilePath =LicenseConfigReader.getPorperty("outFilePath");
	public static String inFilePath = LicenseConfigReader.getPorperty("inFilePath");
	public static String fileName=LicenseConfigReader.getPorperty("fileName");
	public final static String licName = fileName+".lic";
	public final static String keyName = fileName+".key";

	
	public final static String ExpiredDate = LicenseConfigReader.getPorperty("ExpiredDate");// license过期时间
	public final static String CreateTime = DateUtil.getTime();// 创建时间
	public final static String LastAccessTime = DateUtil.getTime();// 最后启动时间
	public final static String SystemName = LicenseConfigReader.getPorperty("SystemName");// 系统名称
	public final static String MacAddress = LicenseConfigReader.getPorperty("MacAddress");// mac地址
	public final static String RegistrationState = LicenseConfigReader.getPorperty("RegistrationState");// 是否注册版本Y N

	public final static String Customer = LicenseConfigReader.getPorperty("Customer");// 客户名称
	public final static String Company = LicenseConfigReader.getPorperty("Company");// 名称
	public final static String MajorVersion = LicenseConfigReader.getPorperty("MajorVersion");// 版本好
	public final static String ResidueDegree = LicenseConfigReader.getPorperty("ResidueDegree");// 最大重启次数
	public final static String MinorVersion = LicenseConfigReader.getPorperty("MinorVersion");// 版本好

	public final static String AuthorName = "YanLei";// 作者姓名
	public final static String email = "yanlei0419@163.com";// 邮箱
}
