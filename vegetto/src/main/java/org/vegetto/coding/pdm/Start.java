package org.vegetto.coding.pdm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.vegetto.coding.pdm.maker.MakerTool;
import org.vegetto.coding.pdm.pdm.PdmTool;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.pdm.xml.MyTables;
import org.vegetto.coding.pdm.xml.XmlTool;
import org.vegetto.coding.util.InitCodeConfig;
import org.xml.sax.SAXException;

public class Start {

	/**
	 * @Descrption 
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 * @Version 1.0
	 */
	public static void main(String[] args) throws IOException, SAXException {
		//1 解析pdm文件，创建一个xml文件(pdm_path:pdm文件路径， xml_path：要生成的xml文件路径)
		PdmTool.pdm2Xml(InitCodeConfig.pdm_path, InitCodeConfig.xml_path);
		
		//2 解析xml文件，保存到一个MyTables对象中
		MyTables mts = XmlTool.xml2MyTables(InitCodeConfig.xml_path);
		
		//输出一下提示信息
		S("");
		S("-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=");
		S("需要生成的表有：");
		for(MyTable mt : mts.getTables()) {
			S("	"+mt.getTableDesc()+"	-"+mt.getTableName());
		}
		S("");
		
		
		//3 生成Java类 Jsp页面 配置文件 
		S("开始生成");
		Set pacakge_set = new HashSet();//用来记录一个包结构是否已经存在，以避免struts spring文件被覆盖（已存在的话，则在原来的文件中追加）
		for(MyTable mt : mts.getTables()) {
			MakerTool.startCreate(mt, InitCodeConfig.jsp_path, pacakge_set.contains(mt.getPackagePath()));
			pacakge_set.add(mt.getPackagePath());
		}
		S("生成完毕，请查看>>>"+InitCodeConfig.jsp_path);
		S("-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=");
	}
	
	public static void S(Object o) {
		System.out.println(o);
	}

}
