package org.vegetto.coding.pdm.xml;

public class MyColumn {
	private String propertyName;//属性名称
	private String propertyCnName;//页面显示名称
	private String propertyType;//属性类别
	private String propertyDesc;//属性排序
	private String fieldDefaultValue;//属性默认值
	private String showStyle;//显示类型
	private String editStyle;//
	private String relateInfo;
	private String fieldName;//列表显示名称
	private String fieldDesc;//页面排序
	private String fieldLength;//页面长度
	private String fieldIsNull;//是否为null
	
	private String classType;//po层字段的类别
	
	
	
	public String getClassType() {
		if(classType==null||"".equals(classType)){
			this.classType="String";
		}
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getPropertyCnName() {
		return propertyCnName;
	}
	public void setPropertyCnName(String propertyCnName) {
		this.propertyCnName = propertyCnName;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyDesc() {
		return propertyDesc;
	}
	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}
	public String getShowStyle() {
		return showStyle;
	}
	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}
	public String getEditStyle() {
		return editStyle;
	}
	public void setEditStyle(String editStyle) {
		this.editStyle = editStyle;
	}
	public String getRelateInfo() {
		return relateInfo;
	}
	public void setRelateInfo(String relateInfo) {
		this.relateInfo = relateInfo;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getFieldIsNull() {
		return fieldIsNull;
	}
	public void setFieldIsNull(String fieldIsNull) {
		this.fieldIsNull = fieldIsNull;
	}
	public String getFieldDefaultValue() {
		return fieldDefaultValue;
	}
	public void setFieldDefaultValue(String fieldDefaultValue) {
		this.fieldDefaultValue = fieldDefaultValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String toString() {
		return "	propertyName="+propertyName+"	propertyCnName="+propertyCnName+"	propertyType="+propertyType+"" +
				"	propertyDesc=" +propertyDesc+"	showStyle="+showStyle+"	editStyle="+editStyle+"	relateInfo="+relateInfo+"" +
				"	fieldName="+fieldName+"	fieldDesc="+fieldDesc+"	fieldLength="+fieldLength+"	fieldIsNull="+fieldIsNull+"	fieldDefaultValue="+fieldDefaultValue;
	}
}
