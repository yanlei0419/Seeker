package org.vegetto.coding.mark.java;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.file.WriteFileUtil;

public class BizImplMaker {
	// 生成BizImpl
	public static void createBizImpl(MyTable table, String filePath) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Po";
		
		//TODO BizImpl-包名-BizImpl
		sb.append("package "+InitCodeConfig.pack+ table.getPackagePath() +".biz.impl;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import java.util.ArrayList;"    +"\r\n");
		sb.append("import java.util.List;"    +"\r\n");
		sb.append(""    +"\r\n");
		//TODO BizImpl-修改-bizImpl
		sb.append("import org.vegetto.common.base.db.DBConnection;"    +"\r\n");
		//TODO bizImpl
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".biz."+classNameUp+"Biz;"    +"\r\n");
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".dao."+classNameUp+"Dao;"    +"\r\n");
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".po."+classNameUp+"Po;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("/**"    +"\r\n");
		sb.append(" * "+ table.getClassDesc() +"BizImpl"    +"\r\n");
		sb.append(" * 	自动生成"    +"\r\n");
		sb.append(" * @Date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())    +"\r\n");
		//TODO BizImpl-作者-BizImpl
//		sb.append(" * @author yanlei"    +"\r\n");
		sb.append(" */"    +"\r\n");
		sb.append("public class "+ classNameUp +"BizImpl implements "+ classNameUp +"Biz {"    +"\r\n");
		sb.append(""    +"\r\n");
		
		sb.append("	private "+ classNameUp+"Dao" +" dao;"    +"\r\n");
		sb.append("	public void setDao("+ classNameUp +"Dao dao){"    +"\r\n");
		sb.append("		this.dao = dao;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append(""    +"\r\n");
		
		sb.append("	public List<"+boClassName+"> getListNonPaged("+boClassName+" po) {"    +"\r\n");
		sb.append("		List<"+boClassName+"> rtn = new ArrayList<"+boClassName+">();"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			rtn = dao.getListNonPaged(po);"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append(""    +"\r\n");
		
		sb.append("	public List<"+boClassName+"> getList("+boClassName+" po) {"    +"\r\n");
		sb.append("		List<"+boClassName+"> rtn = new ArrayList<"+boClassName+">();"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			rtn = dao.getList(po);"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append(""    +"\r\n");
		
		sb.append("	public int getCount("+boClassName+" po) {"    +"\r\n");
		sb.append("		int rtn = 0;"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			rtn = dao.getCount(po);"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		
		sb.append("	public "+boClassName+" get"+classNameUp+"("+boClassName+" po) {"    +"\r\n");
		sb.append("		"+boClassName+" rtn = new "+ boClassName +"();"    +"\r\n");
		sb.append("		try {"    +"\r\n");
//		sb.append("			DBConnection.beginTransaction();"    +"\r\n");
		sb.append("			rtn = dao.get"+classNameUp+"(po);"    +"\r\n");
//		sb.append("			DBConnection.commitTransaction();"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
//		sb.append("			DBConnection.rollbackTransaction();"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	public int add"+classNameUp+"("+boClassName+" po) {"    +"\r\n");
		sb.append("		int rtn = 0;"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			DBConnection.beginTransaction();"    +"\r\n");
		sb.append("			rtn = dao.add"+classNameUp+"(po);"    +"\r\n");
		sb.append("			DBConnection.commitTransaction();"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			DBConnection.rollbackTransaction();"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	public int update"+classNameUp+"("+boClassName+" po) {"    +"\r\n");
		sb.append("		int rtn = 0;"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			DBConnection.beginTransaction();"    +"\r\n");
		sb.append("			rtn = dao.update"+classNameUp+"(po);"    +"\r\n");
		sb.append("			DBConnection.commitTransaction();"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			DBConnection.rollbackTransaction();"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	public int delete"+classNameUp+"("+boClassName+"[] pos) {"    +"\r\n");
		sb.append("		int rtn = 0;"    +"\r\n");
		sb.append("		try {"    +"\r\n");
		sb.append("			DBConnection.beginTransaction();"    +"\r\n");
		
		sb.append("			for ("+classNameUp+"Po po : pos) {"    +"\r\n");
		sb.append("				dao.delete"+classNameUp+"(po);"    +"\r\n");
		sb.append("				rtn++;"    +"\r\n");
		sb.append("			}"    +"\r\n");
		
		sb.append("			DBConnection.commitTransaction();"    +"\r\n");
		sb.append("		} catch (Exception e) {"    +"\r\n");
		sb.append("			rtn = 0;"    +"\r\n");
		sb.append("			DBConnection.rollbackTransaction();"    +"\r\n");
		sb.append("			e.printStackTrace();"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		return rtn;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	"    +"\r\n");
		sb.append("}");
		//TODO bizImpl-path
		filePath += InitCodeConfig.src_path+table.getPackagePath().replaceAll("\\.", "/")+"/biz/impl/";
		WriteFileUtil.writeFile(filePath,  table.getClassName()+"BizImpl.java",sb.toString());
	}

}
