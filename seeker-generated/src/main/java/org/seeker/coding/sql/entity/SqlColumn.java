package org.seeker.coding.sql.entity;

public class SqlColumn {
	private String colName;//字段名称
	private String colLowerName;//小写字段名称
	private String colUpperName;//大写字段名称
	private String colLabel;//字段别名
	private String colDisplaySize;//字段长度
	private String typeName;//字典类型
	private String isNull;//是否可以为null  0-不可以为空  1-可以为空
	private String scale;//精度  数字有用
	private String key;//主键
	
	private String className;//字典类型名
	private String descName;//注释
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
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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

}
