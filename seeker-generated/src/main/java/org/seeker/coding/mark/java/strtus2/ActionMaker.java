package org.seeker.coding.mark.java.strtus2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.seeker.coding.util.CodeUtil;
import org.seeker.coding.pdm.xml.MyTable;
import org.seeker.coding.util.InitCodeConfig;
import org.seeker.coding.util.file.WriteFileUtil;

public class ActionMaker {
	// 生成DAO
	public static void createAction(MyTable table, String filePath) throws IOException {
		// 1 bo文件的字符串
		// 2 生成文件
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		String classNameUp = CodeUtil.caseConversionInitial(table.getClassName(),true);
		String classNameLo = CodeUtil.caseConversionInitial(table.getClassName(),false);
		String boClassName = classNameUp+"Po";
		
		//TODO action-包名-action
		sb.append("package "+InitCodeConfig.pack+ table.getPackagePath() +".action;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import java.io.PrintWriter;"    +"\r\n");
		sb.append("import java.util.List;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import org.springframework.context.ApplicationContext;"    +"\r\n");
		sb.append(""    +"\r\n");
		//TODO action-修改-action
		sb.append("import  org.vegetto.common.util.json.JsonUtil;"    +"\r\n");
		sb.append("import  org.vegetto.common.base.spring3.SpringContextUtil;"    +"\r\n");
		sb.append("import  org.vegetto.common.base.struts2.BaseAction;"    +"\r\n");
		sb.append(""    +"\r\n");
		//TODO action
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".biz."+classNameUp+"Biz;"    +"\r\n");
		sb.append("import "+InitCodeConfig.pack+table.getPackagePath()+".po."+classNameUp+"Po;"    +"\r\n");
		sb.append("import com.opensymphony.xwork2.ModelDriven;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("/**"    +"\r\n");
		sb.append(" * "+ table.getClassDesc() +"Action"    +"\r\n");
		sb.append(" * 	自动生成"    +"\r\n");
		sb.append(" * @Date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())    +"\r\n");
		//	TODO action-作者-action
		sb.append(" * @author yanlei"    +"\r\n");
		sb.append(" */"    +"\r\n");
		sb.append("public class "+ classNameUp +"Action extends BaseAction implements ModelDriven<"+classNameUp+"Po> {"    +"\r\n");
		sb.append("	private "+classNameUp+"Po po = new "+classNameUp+"Po();"    +"\r\n");
//		sb.append("	private String ids[];"    +"\r\n");
//		
//		sb.append("	public String[] getIds() {"    +"\r\n");
//		sb.append("		return ids;"    +"\r\n");
//		sb.append("	}"    +"\r\n");
//		sb.append("	public void setIds(String[] ids) {"    +"\r\n");
//		sb.append("		this.ids = ids;"    +"\r\n");
//		sb.append("	}"    +"\r\n");
		sb.append("	public "+classNameUp+"Po getPo() {"    +"\r\n");
		sb.append("		return po;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	public void setPo("+classNameUp+"Po po) {"    +"\r\n");
		sb.append("		this.po = po;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	public "+classNameUp+"Po getModel() {"    +"\r\n");
		sb.append("		return ("+classNameUp+"Po)initPage(po);"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	"    +"\r\n");
		
		sb.append("	public String getList() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		List<"+classNameUp+"Po> list=biz.getList(po);"    +"\r\n");
		sb.append("		int count = biz.getCount(po);"    +"\r\n");
		sb.append("		String result=JsonUtil.toJSONStringByFastjson(count,list);"    +"\r\n");
		sb.append("		PrintWriter out = response.getWriter();"    +"\r\n");
		sb.append("		out.println(result);"    +"\r\n");
		sb.append("		out.flush();"    +"\r\n");
		sb.append("		out.close();"    +"\r\n");
		sb.append("		return null;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("	"    +"\r\n");
		
		
		sb.append("	public String to"+classNameUp+"Detail() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		po=biz.get"+classNameUp+"(po);"    +"\r\n");
		sb.append("		request.setAttribute(\""+classNameUp+"Po\",po);"    +"\r\n");
		sb.append("		return \"toDetail\";"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	public String to"+classNameUp+"Update() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		po=biz.get"+classNameUp+"(po);"    +"\r\n");
		sb.append("		request.setAttribute(\""+classNameUp+"Po\",po);"    +"\r\n");
		sb.append("		return \"toUpdate\";"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	public String update"+classNameUp+"() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		biz.update"+classNameUp+"(po);"    +"\r\n");
		sb.append("		return \"toList\";"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	public String add"+classNameUp+"() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		biz.add"+classNameUp+"(po);"    +"\r\n");
		sb.append("		return \"toList\";"    +"\r\n");
		sb.append("	}"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	public String delete"+classNameUp+"() throws Exception {"    +"\r\n");
		sb.append("		response.setCharacterEncoding(\"utf-8\");"    +"\r\n");
		sb.append("		"+classNameUp+"Biz biz=("+classNameUp+"Biz)SpringContextUtil.getBean(\""+classNameUp+"Biz\");"    +"\r\n");
		sb.append("		"+classNameUp+"Po[] pos = new "+classNameUp+"Po[po.getIds().length];"    +"\r\n");
		sb.append("		PrintWriter out = response.getWriter();"    +"\r\n");
		sb.append("		for (int i = 0; i < pos.length; i++) {"    +"\r\n");
		sb.append("			pos[i] = new "+classNameUp+"Po();"    +"\r\n");
		sb.append("			pos[i].set"+CodeUtil.caseConversionInitial(table.getPrimaryKey(),true)+"(po.getIds()[i]);"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		int result=biz.delete"+classNameUp+"(pos);"    +"\r\n");
		sb.append("		if(result>0){"    +"\r\n");
		sb.append("			out.println(\"删除成功!\");"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		else{"    +"\r\n");
		sb.append("			out.println(\"删除失败!\");"    +"\r\n");
		sb.append("		}"    +"\r\n");
		sb.append("		out.flush();"    +"\r\n");
		sb.append("		out.close();"    +"\r\n");
		sb.append("		return null;"    +"\r\n");
		sb.append("	}"    +"\r\n");
		
		sb.append("}");
		//TODO action-path
		filePath += InitCodeConfig.src_path+table.getPackagePath().replaceAll("\\.", "/")+"/action/";

		WriteFileUtil.writeFile(filePath,  table.getClassName()+"Action.java",sb.toString());
	}

}
