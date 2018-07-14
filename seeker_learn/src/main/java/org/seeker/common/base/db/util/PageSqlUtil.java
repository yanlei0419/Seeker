package org.seeker.common.base.db.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.seeker.common.base.db.DBConnectionHandler;
import org.seeker.common.base.entity.Page;

@SuppressWarnings("unchecked")
public class PageSqlUtil {
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static String  toPageUtil(String sql, Page po, List paramList, String type){
		String pageSql="";
		//判断什么数据库
		if(DBConnectionHandler.toDataNameLower().contains("oracle")&&DBConnectionHandler.toDataNameUpper().contains("ORACLE")){
			pageSql=toOracleSql(sql, po, paramList);
		}else if(DBConnectionHandler.toDataNameLower().contains("db2")&&DBConnectionHandler.toDataNameUpper().contains("DB2")){
			pageSql=toDb2Sql(sql, po, paramList);
		}else if(DBConnectionHandler.toDataNameLower().contains("mysql")&&DBConnectionHandler.toDataNameUpper().contains("MYSQL")){
			pageSql=sql+toMySql(po);
		}else{
			pageSql=toPageSql(sql, po, paramList);
		}
		return pageSql;
	}
	public static String  toPageUtil(String sql,Page po, List paramList){
		return toPageUtil(sql, po, paramList, "");
	}
	private static String toPageSql(String sql,Page po, List paramList){
		String pageSql="select * from ("+ sql+")" +
		  " where rn>=0 ";
		if(po.getBegin()>0){
			pageSql+=" and rn>=? ";
			paramList.add(po.getBegin());
		}
		if(po.getEnd()>0){
			pageSql+=" and rn<=? ";
			paramList.add(po.getEnd());
		}
		return pageSql;
	}
	
	private static String  toMySql(Page po){
		return " limit "+(po.getBegin()-1)+","+(po.getEnd()-po.getBegin()+1);
	}
	
	private static String toDb2Sql(String sql,Page po, List paramList){
		return toPageSql(sql, po, paramList);
	}
	
	private static String toOracleSql(String sql,Page po, List paramList){
		return toPageSql(sql, po, paramList);
	}
	
	

}
