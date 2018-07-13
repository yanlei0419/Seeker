package org.vegetto.coding.pdm.maker.java;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.common.util.WriteFileUtil;

public class DaoMaker {
	// 生成DAO
	public static void createDAO(MyTable table, String filePath) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Po";
		
		//TODO dao-包名Dao
		sb.append("package "+InitCodeConfig.pack+ table.getPackagePath() +".dao;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import java.util.List;"    +"\r\n");
		sb.append("import java.sql.SQLException;"    +"\r\n");
		sb.append(""    +"\r\n");
		//TODO dao
		sb.append("import "+InitCodeConfig.pack+ table.getPackagePath() +".po."+classNameUp +"Po;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("/**"    +"\r\n");
		sb.append(" * "+ table.getClassDesc() +"Dao"    +"\r\n");
		sb.append(" * 	自动生成"    +"\r\n");
		sb.append(" * @Date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())    +"\r\n");
		//TODO dao-作者-dao
//		sb.append(" * @author yanlei"    +"\r\n");
		sb.append(" */"    +"\r\n");
		sb.append("public interface "+ classNameUp +"Dao {"    +"\r\n");
		sb.append("	"    +"\r\n");
		
		sb.append("	List<"+boClassName+"> getListNonPaged("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	List<"+boClassName+"> getList("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	int getCount("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	"+boClassName+" get"+classNameUp+"("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	int add"+classNameUp+"("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	int update"+classNameUp+"("+boClassName+" po) throws SQLException;"    +"\r\n");
		sb.append("	int delete"+classNameUp+"("+boClassName+" po) throws SQLException;"    +"\r\n");
		
		
		sb.append("	"    +"\r\n");
		sb.append("}");
		//TODO dao-path
		filePath +=InitCodeConfig.src_path+table.getPackagePath().replaceAll("\\.", "/")+"/dao/";
		WriteFileUtil.writeFile(filePath,  table.getClassName()+"Dao.java",sb.toString());
	}

}
