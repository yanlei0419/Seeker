package org.vegetto.coding.pdm.xml;

import java.util.LinkedHashMap;
import java.util.Map;

import org.vegetto.coding.excel.entity.VeColumn;
import org.vegetto.coding.util.CodeUtil;
import org.vegetto.common.util.StringUtils;

public class MyTable {
	private String dbName;
	private String className;//类名称
	private String classDesc;//类描述
	private String tableName;//表明
	private String tableDesc;//表描述
	private String primaryKey;//主键
	private String packagePath;//包名
	private String urlPath;//访问地址或者 web地址
	
	private String classNameUp ;
	private String classNameLo ;
	private String poClassName;
	public String getClassNameUp() {
		if(StringUtils.isNotNull(this.getClassName())){
			return CodeUtil.caseConversionInitial(this.getClassName(),true);
		}
		return classNameUp;
	}

	public void setClassNameUp(String classNameUp) {
		this.classNameUp = classNameUp;
	}

	public String getClassNameLo() {
		if(StringUtils.isNotNull(this.getClassName())){
			return CodeUtil.caseConversionInitial(this.getClassName(),false);
		}
		return classNameLo;
	}

	public void setClassNameLo(String classNameLo) {
		this.classNameLo = classNameLo;
	}

	public String getPoClassName() {
		if(StringUtils.isNotNull(this.getClassName())){
			return  classNameUp+"Po";
		}
		return poClassName;
	}

	public void setPoClassName(String poClassName) {
		this.poClassName = poClassName;
	}

	//表字段pdm
	private Map<String, MyColumn> columnMap = new LinkedHashMap<String, MyColumn>();
	//excel字段
	private Map<String, VeColumn> excelColMap = new LinkedHashMap<String, VeColumn>();
	
	public void addExcelCol(VeColumn col) {
		this.excelColMap.put(col.getTableColName(), col);
	}
	
	public Map<String, VeColumn> getExcelColMap() {
		return excelColMap;
	}

	public void setExcelColMap(Map<String, VeColumn> excelColMap) {
		this.excelColMap = excelColMap;
	}

	public String classNameUp(){
		return CodeUtil.caseConversionInitial(this.getClassName(),true);
	}
	public String classNameLo(){
		return CodeUtil.caseConversionInitial(this.getClassName(),false);
	}
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String name) {
		dbName = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getPackagePath() {
		return packagePath;
	}
	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	//澧炲姞鍒楀埌table涓�
	public void addColumn(MyColumn t) {
		if(t != null) {
			columnMap.put(t.getPropertyName(), t);
		}
	}
	public Map<String, MyColumn> getColumnMap() {
		return columnMap;
	}
	public void setColumnMap(Map<String, MyColumn> columnMap) {
		this.columnMap = columnMap;
	}
	
	public String toString() {
		return "DBName="+dbName+"	className="+className+"	classDesc="+classDesc+"	tableName="+tableName+"	tableDesc="+tableDesc+"	primaryKey="+primaryKey+"	packagePath="+packagePath+"	urlPath="+urlPath;
	}
}
