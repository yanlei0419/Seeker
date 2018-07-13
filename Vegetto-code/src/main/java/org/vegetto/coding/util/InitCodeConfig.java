package org.vegetto.coding.util;


//定义一些解析用到的常量

public class InitCodeConfig {
	/**
	 * pdm文件位置的基础配置
	 */
	//pdm文件路径(完整的硬盘路径,不能包含中文字符)
	public static final String pdm_path = JCodeReader.get("inPath")+JCodeReader.get("intPdmFileName"); 
	//生成的xml文件路径(完成的硬盘路径,不能包含中文字符)
	public static final String xml_path = JCodeReader.get("outPath")+"tables.xml";
	//生成的jsp\java文件路径(完成的硬盘路径)
	public static final String jsp_path = JCodeReader.get("outPath");
	
	//pdm文件路径(完整的硬盘路径,不能包含中文字符)
	public static final String excel_path = JCodeReader.get("inPath")+JCodeReader.get("intExcelFileName"); 
	
	/**
	 * pdm输出文件的基础配置
	 */
	
	
	//包的默认路径
	public static final String pack=JCodeReader.get("package");
	//默认路径
	public static final String config_path=JCodeReader.get("configpath");
	//默认路径
	public static final String src_path=JCodeReader.get("srcpath");
	//默认路径
	public static final String web_path=JCodeReader.get("webpath");
	
	
	/**
	 * 形成文件的配置
	 */
	//1 PDM 2 Excel
	public static final String type="1";
	
	
	/**
	 * 生成代码主要的包名
	 */
	public static final String pdmPageName="org.vegetto.coding.pdm.pdm.";
	public static final String xmlPageName="org.vegetto.coding.pdm.xml.";
	
	/**
	 * 查询标识符
	 */
	public static final String searchSign="*";
	/**
	 * 关联表的分割副
	 */
//	public static final String defaultSplitSign="@@";
	public static final String defaultSplitSign=":";
	
	/**
	 * excel空行
	 */
	public static final int excelNullNext=Integer.valueOf(JCodeReader.get("excelCount"));
	
	
}
