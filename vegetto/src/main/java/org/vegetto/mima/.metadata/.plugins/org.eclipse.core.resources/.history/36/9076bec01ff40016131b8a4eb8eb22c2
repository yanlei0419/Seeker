package org.vegetto.common.base.db.entity;

import org.vegetto.coding.util.CodeUtil;

public class TableColumns {
	private String colName;//字段名称
	private String colLowerName;//小写字段名称
	private String colUpperName;//大写字段名称
	private String colLabel;//字段别名
	private String colDisplaySize;//字段长度
	private String typeName;//字典类型
	private int isNull;//是否可以为null  0-不可以为空  1-可以为空
	private int scale;//精度  数字有用
	private String key;//主键
	
	private String schemaName;
	private boolean searchable;
	private boolean isReadOnly;
	private int precision;
	
	private String className;//字典类型名
	private String descName;//注释
	
	
	
	public TableColumns() {
		super();
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getColLowerName() {
		return colLowerName;
	}
	public void setColLowerName(String colLowerName) {
		this.colLowerName = colLowerName;
	}
	public String getColUpperName() {
		return colUpperName;
	}
	public void setColUpperName(String colUpperName) {
		this.colUpperName = colUpperName;
	}
	public String getColLabel() {
		return colLabel;
	}
	public void setColLabel(String colLabel) {
		this.colLabel = colLabel;
	}
	public String getColDisplaySize() {
		return colDisplaySize;
	}
	public void setColDisplaySize(String colDisplaySize) {
		this.colDisplaySize = colDisplaySize;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getIsNull() {
		return isNull;
	}
	public void setIsNull(int isNull) {
		this.isNull = isNull;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isReadOnly() {
		return isReadOnly;
	}
	public void setIsReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getDescName() {
		return descName;
	}
	public void setDescName(String descName) {
		this.descName = descName;
	}
	
	public String toString(){
		return  "ColumnName>>" + this.colName+ "\n" + "ColumnName>>" + CodeUtil.wordCaseConvert(this.colName, false) + "\n" + "ColumnLabel>>" + this.colLabel + "\n" + 
		"ColumnDisplaySize>>" + this.colDisplaySize+ "\n" + "ColumnTypeName>>" + this.typeName + "\n" + 
		"isReadOnly>>" +this.isReadOnly + "\n" + "isNullable>>" + this.isNull + "\n" + "SchemaName>>" + this.schemaName + "\n" + "Precision>>" + this.precision + "\n" + "Scale>>" +this.scale + "\n" + "Searchable>>" +this.schemaName + "\n" + "";
		
	}
	
}
