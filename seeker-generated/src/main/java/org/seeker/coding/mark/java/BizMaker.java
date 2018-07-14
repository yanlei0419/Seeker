package org.seeker.coding.mark.java;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.seeker.coding.pdm.xml.MyTable;
import org.seeker.coding.util.CodeUtil;
import org.seeker.coding.util.InitCodeConfig;
import org.seeker.coding.util.file.WriteFileUtil;

public class BizMaker {
	// 生成DAO
	public static void createBiz(MyTable table, String filePath) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String poClassName = classNameUp+"Po";
		
		//TODO biz-包名-biz
		sb.append("package "+ InitCodeConfig.pack+ table.getPackagePath() +".biz;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import java.util.List;"    +"\r\n");
		sb.append(""    +"\r\n");
		//TODO biz
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".po."+classNameUp+"Po;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("/**"    +"\r\n");
		sb.append(" * "+ table.getClassDesc() +"Biz"    +"\r\n");
		sb.append(" * 	自动生成"    +"\r\n");
		sb.append(" * @Date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())    +"\r\n");
		//TODO biz-作者-biz
//		sb.append(" * @author yanlei"    +"\r\n");
		sb.append(" */"    +"\r\n");
		sb.append("public interface "+ classNameUp +"Biz {"    +"\r\n");
		
		
		sb.append("	List<"+poClassName+"> getListNonPaged("+poClassName+" po);"    +"\r\n");
		sb.append("	List<"+poClassName+"> getList("+poClassName+" po);"    +"\r\n");
		sb.append("	int getCount("+poClassName+" po);"    +"\r\n");
		sb.append("	"+poClassName+" get"+classNameUp+"("+poClassName+" po);"    +"\r\n");
		sb.append("	int add"+classNameUp+"("+poClassName+" po);"    +"\r\n");
		sb.append("	int update"+classNameUp+"("+poClassName+" po);"    +"\r\n");
		sb.append("	int delete"+classNameUp+"("+poClassName+"[] pos);"    +"\r\n");
		
		
		sb.append("	"    +"\r\n");
		sb.append("}");
		//TODO biz-path
		filePath += InitCodeConfig.src_path+table.getPackagePath().replaceAll("\\.", "/")+"/biz/";
		WriteFileUtil.writeFile(filePath, table.getClassName()+"Biz.java",sb.toString());
	}

}
