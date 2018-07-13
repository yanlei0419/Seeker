package org.vegetto.coding.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.vegetto.coding.pdm.maker.MakerTool;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.pdm.xml.MyTables;
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
//		String path="d:/";
		String path=Start.class.getClass().getResource("/").getPath()+"coding"+File.separatorChar;
		String savePath =  path+"exceltemp.xls";
		File file = new File(savePath);
		FileInputStream in = new FileInputStream(file);
		MyTables tabs =CodeExcel.toExcelByIs(in);
		//3 生成Java类 Jsp页面 配置文件 
		S("开始生成");
		Set pacakge_set = new HashSet();//用来记录一个包结构是否已经存在，以避免struts spring文件被覆盖（已存在的话，则在原来的文件中追加）
		for(MyTable mt : tabs.getTables()) {
			MakerTool.startCreate(mt, InitCodeConfig.jsp_path, pacakge_set.contains(mt.getPackagePath()));
			pacakge_set.add(mt.getPackagePath());
			S("表>>>"+mt.getClassName());
		}
		S("生成完毕，请查看>>>"+InitCodeConfig.jsp_path);
		S("-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=");
	}
	
	public static void S(Object o) {
		System.out.println(o);
	}

}
