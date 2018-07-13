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
import org.vegetto.coding.util.CodeDefaultConfig;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.coding.util.InitCodeConfig;
import org.vegetto.coding.util.file.WriteFileUtil;

@SuppressWarnings("unchecked")
public class DaoImplMaker {
	private static String classNameUp ;
	private static String classNameLo ;
	private static String boClassName;
	private static List<MyColumn> relateList = null;// 用来保存有关联信息的列
	
	private static StringBuffer sb_share =null;// list count get可共享的输出代码
	private static StringBuffer sb_share_po =null;// list count get可共享的输出代码
	private static StringBuffer sb_share_search =null;// list count
	
	
	private static Map<String, MyColumn> columnMap=null ;// 获得字段信息
	private static Set<Map.Entry<String, MyColumn>> set =null;
	private static 	int index = 1;// 用来输出t1 t2 这样的表别名，防止重名
	
	private static String douhao = ",";// 用于控制是否输出逗号
	private static int count = 1;// 用来记录是第几次循环
	
	private static Iterator it =null;
	
	public static Map typedic = CodeDefaultConfig.typedic;
	public static Map valdic = CodeDefaultConfig.valdic;
	// 生成DAO
	public static void createDaoImpl(MyTable table, String filePath) throws IOException {
		// 1 po文件的字符串
		// 2 生成文件

		StringBuffer sb = new StringBuffer();
		StringBuffer sbGeterSeter = new StringBuffer();
		classNameUp = CodeUtil.caseConversionInitial(table.getClassName(), true);
		classNameLo = CodeUtil.caseConversionInitial(table.getClassName(), false);
		boClassName = classNameUp + "Po";
		
		sb_share = new StringBuffer("");// list count get可共享的输出代码
		sb_share_po = new StringBuffer("");// list count get可共享的输出代码
		sb_share_search = new StringBuffer("");// list count
		
		 relateList = new ArrayList<MyColumn>();

		// TODO daoImpl-包名-daoImpl
		sb.append("package " + InitCodeConfig.pack + table.getPackagePath() + ".dao.jdbc;" + "\r\n");
		sb.append("" + "\r\n");
		sb.append("import java.sql.SQLException;" + "\r\n");
		sb.append("import java.util.ArrayList;" + "\r\n");
		sb.append("import java.util.List;" + "\r\n");
		sb.append("" + "\r\n");
		//
		sb.append(CodeUtil.getDaoJdbcPage());
		
		// TODO daoImpl
		sb.append("import " + InitCodeConfig.pack + table.getPackagePath() + ".dao." + classNameUp + "Dao;" + "\r\n");
		sb.append("import " + InitCodeConfig.pack + table.getPackagePath() + ".po." + classNameUp + "Po;" + "\r\n");
		sb.append("" + "\r\n");
		sb.append("/**" + "\r\n");
		sb.append(" * " + table.getClassDesc() + "DaoImpl" + "\r\n");
		sb.append(" * 	自动生成" + "\r\n");
		sb.append(" * @Date " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n");
		// TODO daoImpl-作者-daoImpl
		sb.append(" * @author yanlei" + "\r\n");
		sb.append(" */" + "\r\n");
		sb.append("public class " + classNameUp + "DaoImpl extends BaseDao implements " + classNameUp + "Dao {" + "\r\n");
		sb.append("	" + "\r\n");

		sb.append(createList(table));
		sb.append(createListCount(table));
		sb.append(createPo(table));
		sb.append(createAdd(table));
		sb.append(createUpdate(table));
		sb.append(createDel(table));
		
		sb.append("	" + "\r\n");
		sb.append("}");
		// TODO daoImpl-path
		filePath += InitCodeConfig.src_path + table.getPackagePath().replaceAll("\\.", "/") + "/dao/jdbc/";
		
		WriteFileUtil.writeFile(filePath,  table.getClassName()+"DaoImpl.java",sb.toString());
	}

	private static StringBuffer createList(MyTable table) {
		StringBuffer sb = new StringBuffer();
		sb.append("	/**" + "\r\n");
		sb.append("	 * 获取不分页列表" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public List<" + boClassName + "> getListNonPaged(" + boClassName + " po) throws SQLException {" + "\r\n");
		sb.append("		po.setBegin(-1);" + "\r\n");
		sb.append("		po.setEnd(-1);" + "\r\n");
		sb.append("		return getList(po);" + "\r\n");
		sb.append("	}" + "\r\n");

		sb.append("	/**" + "\r\n");
		sb.append("	 * 获取列表" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public List<" + boClassName + "> getList(" + boClassName + " po) throws SQLException {" + "\r\n");

		sb.append("		List paramList = new ArrayList();" + "\r\n");
		sb.append("		String sort = po.getSort()!=null&&!po.getSort().equals(\"\") ? po.getSort() : \"Id\";//用于排序用的字段名" + "\r\n");
		sb.append("		String order = po.getOrder()!=null&&!po.getOrder().equals(\"\") ? po.getOrder() : \"desc\";//排序方式，默认倒叙" + "\r\n");
		sb.append("		String sql =" + "\r\n");

		
															// get可共享的输出 查询条件 代码

		// sb_share.append("				\"select * from (\"+ " +"\r\n");
		sb_share.append("				\"    select \"+" + "\r\n");
		sb_share_po.append("				\"    select \"+" + "\r\n");
		// sb_share.append("				\"        row_number() over(order by t."+caseConversionInitial(table.getPrimaryKey(),true)+" desc) rn,\"+"
		// +"\r\n");
		sb_share.append("				\"        row_number() over(order by t.\"+ sort +\" \"+ order +\") rn,\"+" + "\r\n");

		
		columnMap = table.getColumnMap();// 获得字段信息
		set = columnMap.entrySet();
		it = set.iterator();
		douhao = ",";// 用于控制是否输出逗号
		int count = 1;// 用来记录是第几次循环
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			String pNameUp = CodeUtil.caseConversionInitial(column.getPropertyName(), true);// 首字母大写的PropertyName
			String pNameLo = CodeUtil.caseConversionInitial(column.getPropertyName(), false);// 首字母小写的PropertyName

			// 关联信息不为空时，添加到relateInfoList
			if (column.getRelateInfo() != null && !column.getRelateInfo().equals("")) {
				relateList.add(column);
				// 关联信息的格式：拓展属性名>取值表名>本字段对应取值表的字段名(一般为PK键)>取值表中的取值字段名
				// 如：OperatorName>User>Id>RealName
				String[] arr = column.getRelateInfo().split(">");
			}
			if (count++ == set.size() && relateList.size() == 0)
				douhao = "";

			String pType = typedic.get(column.getPropertyType()).toString();// 属性的类型
			if (pType.equals("Date")) {
				sb_share.append("				\"        to_char(t." + pNameLo + ",'yyyy-MM-dd HH24:mi:ss') " + pNameLo + douhao + "\"+" + "\r\n");
				sb_share_po.append("				\"        to_char(t." + pNameLo + ",'yyyy-MM-dd HH24:mi:ss') " + pNameLo + douhao + "\"+" + "\r\n");
			} else {
				sb_share.append("				\"        t." + pNameLo + douhao + "\"+" + "\r\n");
				sb_share_po.append("				\"        t." + pNameLo + douhao + "\"+" + "\r\n");
			}

			// 如果是查询字段，要输出查询条件(这里输出的只是where后面的 and 部分 )
			if (column.getShowStyle().contains(InitCodeConfig.searchSign)) {
				sb_share_search.append("			if(StringUtils.isNotNull(po.get" + pNameUp + "()){" + "\r\n");
				sb_share_search.append("				sql += \" and t." + pNameUp + " = ? \";" + "\r\n");
				sb_share_search.append("				paramList.add(po.get" + pNameUp + "());" + "\r\n");
				if (pType.equals("String")) {
					sb_share_search.append("				//下面为模糊查询" + "\r\n");
					sb_share_search.append("				//sql += \" and t." + pNameUp + " like '%\"+ po.get" + pNameUp + "() +\"%' \";" + "\r\n");
				}
				sb_share_search.append("			}" + "\r\n");
			}
		}
	
		douhao = ",";// 用于控制是否输出逗号
		count = 1;// 用来记录是第几次循环
		for (MyColumn column : relateList) {
			index = 1;
			//arr[0] sql查询字段别名  arr[1] 表明  arr[2] 关联查询字段  arr[3] 关联表的字段
			String[] arr = column.getRelateInfo().split("@@");
			if (count++ == relateList.size())
				douhao = "";
			if (arr.length == 4) {
				int flag=index++;
				sb_share.append("				\"        t" + flag + "." + arr[3] + " as " + arr[0] + douhao + "\"+" + "\r\n");
				sb_share_po.append("				\"        t" + flag + "." + arr[3] + " as " + arr[0] + douhao + "\"+" + "\r\n");
			}
		}

		sb_share.append("				\"    from " + table.getTableName() + " t \"+" + "\r\n");
		sb_share_po.append("				\"    from " + table.getTableName() + " t \"+" + "\r\n");
		index = 1;// 用来输出t1 t2 这样的表别名，防止重名
		for (MyColumn column : relateList) {
			int flag = index++;
			String[] arr = column.getRelateInfo().split("@@");
			if (arr.length == 4) {
				sb_share.append("				\"    left join " + arr[1] + " t" + flag + " on t" + flag + "." + arr[2] + "=t." + column.getFieldName() + "\"+" + "\r\n");
				sb_share_po.append("				\"    left join " + arr[1] + " t" + flag + " on t" + flag + "." + arr[2] + "=t." + column.getFieldName() + "\"+" + "\r\n");
			}
		}

		sb.append(sb_share.toString());
		sb.append("				\"    where 1=1 \";" + "\r\n");

		// 写入查询条件
		sb.append(sb_share_search.toString());

		// sb.append("		sql += \") where rn>=0 \";" +"\r\n");
		//		
		// //分页条件
		// sb.append("		if(po.getBegin()>0){" +"\r\n");
		// sb.append("			sql+=\" and rn>=? \";" +"\r\n");
		// sb.append("			paramList.add(po.getBegin());" +"\r\n");
		// sb.append("		}" +"\r\n");
		// sb.append("		if(po.getEnd()>0){" +"\r\n");
		// sb.append("			sql+=\" and rn<=? \";" +"\r\n");
		// sb.append("			paramList.add(po.getEnd());" +"\r\n");
		// sb.append("		}" +"\r\n");
		//		
		// sb.append("		Object [] param = paramList.toArray();" +"\r\n");

		// sb.append("		rtn = super.executeQuery(sql, new BeanListHandler(po.getClass()));"
		// +"\r\n");
		// sb.append("		return super.executeQuery(sql, param, new BeanListHandler(po.getClass()));"
		// +"\r\n");
		sb.append("		return super.executePageQuery(sql, paramList, new BeanListHandler(po.getClass()),po);" + "\r\n");

		sb.append("	}" + "\r\n");
		return sb;
	}
	private static StringBuffer  createPo(MyTable table) {
		StringBuffer sb=new StringBuffer();
		// TODO po
		sb.append("	/**" + "\r\n");
		sb.append("	 * 获得" + table.getClassName() + "Po" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public " + boClassName + " get" + classNameUp + "(" + boClassName + " po) throws SQLException {" + "\r\n");
		sb.append("		" + boClassName + " rtn = null;" + "\r\n");
		sb.append("		String sql = " + "\r\n");
		sb.append(	sb_share_po.toString());
		sb.append("				\" where t." + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "=? \"; " + "\r\n");
//		sb.append("				\" )\";" + "\r\n");
		sb.append("		rtn = (" + boClassName + ") super.executeQueryObject(sql, new Object []{po.get" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "()}, new BeanHandler(po.getClass()));" + "\r\n");
		sb.append("		return rtn;" + "\r\n");
		sb.append("	}" + "\r\n");
		return sb;
	}
	private static StringBuffer  createListCount(MyTable table) {
		StringBuffer sb=new StringBuffer();
		//TODO count
		sb.append("	/**" + "\r\n");
		sb.append("	 * 获取记录数" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public int getCount(" + boClassName + " po) throws SQLException {" + "\r\n");
		// sb.append("		int rtn = 0;" +"\r\n");
		sb.append("		List paramList = new ArrayList();" + "\r\n");
		sb.append("		String sql =" + "\r\n");
		sb.append("			\"    select count(*) \"+" + "\r\n");
		sb.append("			\"    from " + table.getTableName() + " t \"+" + "\r\n");
		index = 1;// 用来输出t1 t2 这样的表别名，防止重名
		for (MyColumn column : relateList) {
			int flag = index++;
			String[] arr = column.getRelateInfo().split(">");
			if (arr.length == 4) {
				sb.append("			\"    left join " + arr[1] + " t" + flag + " on t" + flag + "." + arr[2] + "=t." + column.getFieldName() + "\"+" + "\r\n");
			}
		}

		sb.append("			\"    where 1=1 \";" + "\r\n");

		// 写入查询条件
		sb.append(sb_share_search.toString());

		sb.append("		Object [] param = paramList.toArray();" + "\r\n");
		sb.append("		return super.executeCount(sql, param);" + "\r\n");

		// sb.append("		rtn = super.executeCount(sql);" +"\r\n");
		// sb.append("		return rtn;" +"\r\n");

		sb.append("	}" + "\r\n");
		return sb;
	}

	private static StringBuffer createAdd(MyTable table) {
		StringBuffer sb=new StringBuffer();
		
		// TODO add
		sb.append("	/**" + "\r\n");
		sb.append("	 * 插入" + table.getClassName() + "Po" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public int add" + classNameUp + "(" + boClassName + " po) throws SQLException {" + "\r\n");
		sb.append("		int rtn = 0;" + "\r\n");
		sb.append("		String sql =" + "\r\n");
		sb.append("				\"insert into " + table.getTableName() + " (\" +" + "\r\n");
		set = columnMap.entrySet();
		it = set.iterator();
		douhao = ",";// 用于控制是否输出逗号
		count = 1;// 用来记录是第几次循环

		StringBuilder sb_wh = new StringBuilder();// 保存要输出的问号
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			String pNameUp = CodeUtil.caseConversionInitial(column.getPropertyName(), true);// 首字母大写的PropertyName
			String pNameLo = CodeUtil.caseConversionInitial(column.getPropertyName(), false);// 首字母小写的PropertyName
			if (count++ == set.size())
				douhao = "";
			sb.append("						\" " + column.getFieldName() + " " + douhao + "\" +" + "\r\n");
			sb_wh.append(" ? " + douhao);
		}

		sb.append("				\" ) values (" + sb_wh + ")\";" + "\r\n");
		sb.append("		Object [] param = new Object [] { " + "\r\n");

		set = columnMap.entrySet();
		it = set.iterator();
		douhao = ",";// 用于控制是否输出逗号
		count = 1;// 用来记录是第几次循环
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			String pNameUp = CodeUtil.caseConversionInitial(column.getPropertyName(), true);// 首字母大写的PropertyName
			String pNameLo = CodeUtil.caseConversionInitial(column.getPropertyName(), false);// 首字母小写的PropertyName
			if (count++ == set.size())
				douhao = "";
			String pType = typedic.get(column.getPropertyType()).toString();// 属性的类型
			if (table.getPrimaryKey().toLowerCase().equals(column.getFieldName().toLowerCase())) {
				sb.append("			new Uuid().getUUID()" + douhao + "	\r\n");
				/*
				 * }else if(pType.equals("Date")) {
				 * sb.append("						\"  to_date('\"+po.get"
				 * +pNameUp+"()+\"','yyyy-MM-dd HH24:mi:ss') "+douhao+"\"+"
				 * +"\r\n"); }else if(pType.equals("Integer") ||
				 * pType.equals("Double")){
				 * sb.append("						\" \"+ po.get"+pNameUp+"() +\""+douhao+"\"+"
				 * +"\r\n");
				 */
			} else {// String
				sb.append("			po.get" + pNameUp + "()" + douhao + "	\r\n");
			}
		}

		sb.append("		}; " + "\r\n");

		/*
		 * set = columnMap.entrySet(); it = set.iterator();
		 * douhao=",";//用于控制是否输出逗号 count=1;//用来记录是第几次循环 while(it.hasNext()) {
		 * Map.Entry<String, MyColumn> entry = (Map.Entry<String,
		 * MyColumn>)it.next(); MyColumn column = entry.getValue(); String
		 * pNameUp =
		 * caseConversionInitial(column.getPropertyName(),true);//首字母大写的PropertyName
		 * String pNameLo =
		 * caseConversionInitial(column.getPropertyName(),false)
		 * ;//首字母小写的PropertyName if(count++==set.size()) douhao=""; String pType
		 * = typedic.get(column.getPropertyType()).toString();//属性的类型
		 * if(table.getPrimaryKey
		 * ().toLowerCase().equals(column.getFieldName().toLowerCase())) {
		 * sb.append("						\" '\"+ new Uuid().getUUID() +\"'"+douhao+"\"+"
		 * +"\r\n"); }else if(pType.equals("Date")) {
		 * sb.append("						\"  to_date('\"+po.get"
		 * +pNameUp+"()+\"','yyyy-MM-dd HH24:mi:ss') "+douhao+"\"+" +"\r\n");
		 * }else if(pType.equals("Integer") || pType.equals("Double")){
		 * sb.append("						\" \"+ po.get"+pNameUp+"() +\""+douhao+"\"+"
		 * +"\r\n"); }else {//String
		 * sb.append("						\" '\"+ po.get"+pNameUp+"() +\"'"+douhao+"\"+"
		 * +"\r\n"); } } sb.append("						\")\"; " +"\r\n");
		 */

		sb.append("		rtn = super.executeUpdate(sql, param);" + "\r\n");
		sb.append("		return rtn;" + "\r\n");
		sb.append("	}" + "\r\n");
		
		return sb;
	}

	private static StringBuffer createUpdate(MyTable table) {
		StringBuffer sb=new StringBuffer();
		// TODO update
		sb.append("	/**" + "\r\n");
		sb.append("	 * 更新" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public int update" + classNameUp + "(" + boClassName + " po) throws SQLException {" + "\r\n");
		sb.append("		int rtn = 0;" + "\r\n");

		sb.append("		String sql =" + "\r\n");
		sb.append("				\"update " + table.getTableName() + " set \" +" + "\r\n");

		set = columnMap.entrySet();
		it = set.iterator();
		douhao = ",";// 用于控制是否输出逗号
		count = 1;// 用来记录是第几次循环
		StringBuilder sb_canshu = new StringBuilder();
		while (it.hasNext()) {
			Map.Entry<String, MyColumn> entry = (Map.Entry<String, MyColumn>) it.next();
			MyColumn column = entry.getValue();
			String pNameUp = CodeUtil.caseConversionInitial(column.getPropertyName(), true);// 首字母大写的PropertyName
			String pNameLo = CodeUtil.caseConversionInitial(column.getPropertyName(), false);// 首字母小写的PropertyName
			if (count++ == set.size())
				douhao = "";
			if (table.getPrimaryKey().toLowerCase().equals(column.getFieldName().toLowerCase())) {
				continue;
			} else {// String
				// sb.append("						\" "+column.getFieldName()+"= '\"+ po.get"+pNameUp+"() +\"'"+douhao+"\"+"
				// +"\r\n");
				sb.append("						\" " + column.getFieldName() + "=?" + douhao + "\"+" + "\r\n");
				sb_canshu.append("			po.get" + pNameUp + "()," + "\r\n");
			}
		}
		// sb.append("				\" where "+caseConversionInitial(table.getPrimaryKey(),true)+"='\"+po.get"+caseConversionInitial(table.getPrimaryKey(),true)+"()+\"'\";"
		// +"\r\n");
		sb.append("				\" where " + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "=?\";	\r\n");

		sb.append("		Object [] param = new Object [] { " + "\r\n");
		sb.append(sb_canshu + "	\r\n");
		sb.append("			po.get" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "()	\r\n");
		sb.append("		}; " + "\r\n");

		/*
		 * set = columnMap.entrySet(); it = set.iterator();
		 * douhao=",";//用于控制是否输出逗号 count=1;//用来记录是第几次循环 while(it.hasNext()) {
		 * Map.Entry<String, MyColumn> entry = (Map.Entry<String,
		 * MyColumn>)it.next(); MyColumn column = entry.getValue(); String
		 * pNameUp =
		 * caseConversionInitial(column.getPropertyName(),true);//首字母大写的PropertyName
		 * String pNameLo =
		 * caseConversionInitial(column.getPropertyName(),false)
		 * ;//首字母小写的PropertyName if(count++==set.size()) douhao=""; String pType
		 * = typedic.get(column.getPropertyType()).toString();//属性的类型
		 * if(table.getPrimaryKey
		 * ().toLowerCase().equals(column.getFieldName().toLowerCase())) {
		 * continue; }else if(pType.equals("Date")) {
		 * sb.append("						\" "+column
		 * .getFieldName()+"= to_date('\"+po.get"+pNameUp
		 * +"()+\"','yyyy-MM-dd HH24:mi:ss')\" +\""+douhao+"\"+" +"\r\n"); }else
		 * if(pType.equals("Integer") || pType.equals("Double")){
		 * sb.append("						\" "
		 * +column.getFieldName()+"= \"+ po.get"+pNameUp+"() +\""+douhao+"\"+"
		 * +"\r\n"); }else {//String
		 * sb.append("						\" "+column.getFieldName()+
		 * "= '\"+ po.get"+pNameUp+"() +\"'"+douhao+"\"+" +"\r\n"); } }
		 * sb.append
		 * ("				\" where "+caseConversionInitial(table.getPrimaryKey(),
		 * true)+"='\"+po.get"
		 * +caseConversionInitial(table.getPrimaryKey(),true)+"()+\"'\";"
		 * +"\r\n");
		 */

		sb.append("		rtn = super.executeUpdate(sql, param);" + "\r\n");
		sb.append("		return rtn;" + "\r\n");
		sb.append("	}" + "\r\n");


		return sb;
	}
	
	private static StringBuffer createDel(MyTable table){
		StringBuffer sb=new StringBuffer();
		// TODO del
		sb.append("	/**" + "\r\n");
		sb.append("	 * 删除" + "\r\n");
		sb.append("	 */" + "\r\n");
		sb.append("	public int delete" + classNameUp + "(" + boClassName + " po) throws SQLException {" + "\r\n");
		sb.append("		int rtn = 0;" + "\r\n");
		sb.append("		String sql =\"delete from " + table.getTableName() + " where " + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "=?\";" + "\r\n");

		sb.append("		Object [] param = new Object [] { " + "\r\n");
		sb.append("			po.get" + CodeUtil.caseConversionInitial(table.getPrimaryKey(), true) + "()" + "\r\n");
		sb.append("		};" + "\r\n");

		sb.append("		rtn = super.executeUpdate(sql, param);" + "\r\n");
		sb.append("		return rtn;" + "\r\n");
		sb.append("	}" + "\r\n");
		return sb;
	}
}
