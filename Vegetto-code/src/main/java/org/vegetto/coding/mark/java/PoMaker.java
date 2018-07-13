package org.vegetto.coding.mark.java;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.vegetto.coding.pdm.xml.MyColumn;
import org.vegetto.coding.pdm.xml.MyTable;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.file.WriteFileUtil;


public class PoMaker {
	
	// 生成po
	public static void createPo(MyTable table, String filePath) throws IOException {
		// 1 po文件的字符串
		// 2 生成文件
		
		List<String> allProperties = new ArrayList<String>();
		List<String> allFields = new ArrayList<String>();
		List<String> dateFields = new ArrayList<String>();
		List<String[]> allPropertiesAndFields = new ArrayList<String[]>();
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		//TODO po-包名 po
		sb.append("package "+InitCodeConfig.pack+ table.getPackagePath() +".po;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("import java.io.Serializable;"    +"\r\n");
		//TODO po-修改 po
		sb.append("import org.vegetto.common.base.entity.Page;"    +"\r\n");
		sb.append(""    +"\r\n");
		sb.append("/**"    +"\r\n");
		sb.append(" * "+ table.getClassDesc() +"实体类"    +"\r\n");
		sb.append(" * 	自动生成，对应"+ table.getTableName() +"表"    +"\r\n");
		sb.append(" * @Date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())    +"\r\n");
		//TODO po-作者
//		sb.append(" * @author yanlei "    +"\r\n");
		sb.append(" */"    +"\r\n");
		sb.append("public class "+ table.getClassName() +"Po extends Page implements Serializable {"    +"\r\n");
		sb.append("	"    +"\r\n");
		sb.append("	/**对应数据库字段*/"    +"\r\n");
		
		// 输出po的属性
		// sb.append(" private String travelId;//编号");
		Map<String, MyColumn> columnMap = table.getColumnMap();// 获得字段信息
		List<MyColumn> relateList = new ArrayList<MyColumn>();// 用来保存有关联信息的列
		List<MyColumn> dateList = new ArrayList<MyColumn>();// 用来保存日期类型的列
		
		Set<Map.Entry<String, MyColumn>> set = columnMap.entrySet();
		Iterator it = set.iterator();
		while(it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>)it.next();
			MyColumn column = entry.getValue();
			
			String pNameUp = CodeUtil.caseConversionInitial(column.getPropertyName(),true);//首字母大写的PropertyName
			String pNameLo = CodeUtil.caseConversionInitial(column.getPropertyName(),false);//首字母小写的PropertyName
			
			// 格式如 【private String realName;//真实姓名 该属性为用户的正是姓名】
			//sb.append("	private "+ typedic.get(column.getPropertyType()) +" "+ column.getPropertyName() +"="+ valdic.get(column.getPropertyType()) +";//"+column.getPropertyCnName()+"	"+column.getPropertyDesc());
			sb.append("	private "+column.getClassType()+" "+ pNameLo +";	//"+column.getPropertyCnName()+"	"+column.getPropertyDesc());
			
			// 关联信息不为空时，添加到relateInfoList
			if(column.getRelateInfo()!=null && !column.getRelateInfo().equals("")) {
				relateList.add(column);
				// 关联信息的格式：拓展属性名>取值表名>本字段对应取值表的字段名(一般为PK键)>取值表中的取值字段名
				// 如：OperatorName>User>Id>RealName
				String[] arr =  column.getRelateInfo().split(InitCodeConfig.defaultSplitSign);
				sb.append("    关联"+arr[1]+"表的"+arr[2]+"字段");
			}
			// 日期类型的
			if(column.getPropertyType().equals("date")) {
				dateFields.add(pNameLo);
				dateList.add(column);
			}
			sb.append("\r\n");
			
			// 保存信息
			allProperties.add(pNameLo);
			allFields.add(column.getFieldName());
			String[] s = {pNameLo,column.getFieldName(),column.getClassType()};
			allPropertiesAndFields.add(s);
			
			//创建get set 方法，此处创建了保存在一个变量中，插入在文本的后面
			sbGeterSeter.append(createGetAndSet(column.getClassType(),pNameUp,true));
		}
		
		sb.append("	"    +"\r\n");
		sb.append("	/**拓展属性*/"    +"\r\n");
		// 输出拓展属性
		// 1 如果有时间类型的属性，输出按照时间段搜索的拓展标记
		for(MyColumn column : dateList) {
			String start = column.getPropertyName()+"_Begin";
			String end = column.getPropertyName()+"_End";
			// 要判断一下是否有重名的属性
			if(columnMap.get(start)!=null || columnMap.get(end)!=null) {
				int index = 1;
				int index1 = 1;
				start = column.getPropertyName()+"_Begin"+index++;
				end = column.getPropertyName()+"_End"+index1++;
				while(columnMap.get(start)==null && columnMap.get(end)==null) {
					break;
				}
			}
			start = CodeUtil.caseConversionInitial(start, false);
			end = CodeUtil.caseConversionInitial(end, false);
			sb.append("	private String "+ start +";//搜索开始时间："+column.getPropertyCnName()+"	"+column.getPropertyDesc()    +"\r\n");
			sb.append("	private String "+ end +";//搜索结束时间："+column.getPropertyCnName()+"	"+column.getPropertyDesc()    +"\r\n");
			allProperties.add(start);
			allProperties.add(end);
			sbGeterSeter.append(createGetAndSet("String",start,false));
			sbGeterSeter.append(createGetAndSet("String",end,false));
			
		}
		// 2 输出关联其他表的属性
		for(MyColumn column : relateList) {
			// 关联信息的格式：拓展属性名>取值表名>本字段对应取值表的字段名(一般为PK键)>取值表中的取值字段名
			// 如：OperatorName>User>Id>RealName
			String[] arr = column.getRelateInfo().split(InitCodeConfig.defaultSplitSign);
			if(arr.length==4) {
				sb.append("	private "+column.getClassType() +" "+ CodeUtil.caseConversionInitial(arr[0],false) +";//"+column.getPropertyName()+"的拓展属性，对应"+arr[1]+"表的"+arr[3]+"字段"    +"\r\n");
				allProperties.add(CodeUtil.caseConversionInitial(arr[0],false));
				sbGeterSeter.append(createGetAndSet(column.getClassType(),arr[0],false));
			}
		}
		
		sb.append("	"    +"\r\n");
		sb.append(sbGeterSeter.toString());
		
		sb.append("	"    +"\r\n");
		sb.append("}");
		
		//TODO po-path
		filePath += InitCodeConfig.src_path+table.getPackagePath().replaceAll("\\.", "/")+"/po/";
		WriteFileUtil.writeFile(filePath,  table.getClassName()+"Po.java",sb.toString());
	}

	// for createpo : 生成allProperties等 保存在list中的字符串 转为……"",""……格式
	private static String list2Text(List<String> l) {
		String rtn = "";
		for (String s : l)
			rtn += ("\"" + s + "\",");
		return rtn.equals("") ? rtn : rtn.substring(0, rtn.length() - 1);
	}

	// for createpo : 生成_allPropertiesAndFields属性用
	private static String listArr2Text(List<String[]> l) {
		String rtn = "";
		for (String[] arr : l)
			rtn += "{\"" + arr[0] + "\",\"" + arr[1] + "\",\"" + arr[2] + "\"},";
		return rtn.equals("") ? rtn : rtn.substring(0, rtn.length() - 1);
	}
	//创建get 和 set方法 addToModifiedFields:是否在set方法中把该属性增加到ModifiedFiel变量中
	private static String createGetAndSet(String pType, String pName, boolean addToModifiedFields) {
		String pNameUp = CodeUtil.caseConversionInitial(pName, true);
		String pNameLo = CodeUtil.caseConversionInitial(pName, false);
		StringBuffer sbGeterSeter = new StringBuffer();
		sbGeterSeter.append("");
		sbGeterSeter.append("	public "+ pType +" get"+ pNameUp +"() {"    +"\r\n");
		sbGeterSeter.append("		return "+ pNameLo +";"    +"\r\n");
		sbGeterSeter.append("	}"    +"\r\n");
		sbGeterSeter.append("	public void set"+pNameUp+"("+ pType +" "+pNameLo+") {"    +"\r\n");
		sbGeterSeter.append("		this."+pNameLo+" = "+pNameLo+";"    +"\r\n");
		sbGeterSeter.append("	}"    +"\r\n");
		return sbGeterSeter.toString();
	}
}
