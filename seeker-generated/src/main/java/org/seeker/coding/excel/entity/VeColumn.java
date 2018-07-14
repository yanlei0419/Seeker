package org.seeker.coding.excel.entity;

import org.seeker.coding.pdm.xml.MyColumn;
import org.seeker.coding.util.InitCodeConfig;
import org.seeker.coding.util.StringUtils;
import org.seeker.coding.util.excel.Excel;

public class VeColumn {
	@Excel(exportName = "字段名称")
	private String tableColName;// 属性名称

	@Excel(exportName = "Class类型")
	private String classType;// 属性类别

	@Excel(exportName = "主键")
	private String isKey;

	@Excel(exportName = "显示名称")
	private String pageName;// 页面显示名称

	@Excel(exportName = "Sql类型")
	private String sqlType;// 属性类别

	@Excel(exportName = "注释")
	private String propertyDesc;// 属性排序

	@Excel(exportName = "默认值")
	private String fieldDefaultValue;// 默认值

	@Excel(exportName = "是否列表显示")
	private String showStyle;// 页面是否为

	@Excel(exportName = "是否页面显示")
	private String editStyle;

	@Excel(exportName = "是否验证")
	private String checkStyle;//

	@Excel(exportName = "字段别名")
	private String fieldName;// 列表显示名称

	@Excel(exportName = "注释内容")
	private String fieldDesc;// 字段描述

	@Excel(exportName = "字段长度")
	private String fieldLength;// 页面长度

	@Excel(exportName = "是否验证")
	private String fieldIsNull;// 是否为null

	@Excel(exportName = "关联表")
	private String relateInfo;// arr[1]

	@Excel(exportName = "关联字段")
	private String relateName;// arr[2]

	@Excel(exportName = "查询字段别名")
	private String relateFieldName;// arr[0]

	@Excel(exportName = "关联查询字段")
	private String relateTabName;// arr[1]

	@Excel(exportName = "查询字段")
	private String searchName;

	public String getEditStyle() {
//		if ("N".equals(this.editStyle) || "n".equals(this.editStyle)) {
//			return "hidden";
//		}
		return this.showStyle;
		// if(this.editStyle==null||"".equals(this.editStyle)){
		// return "text";
		// }else if("Y".equals(this.editStyle)||"y".equals(this.editStyle)){
		// }else if("N".equals(this.editStyle)||"n".equals(this.editStyle)){
		// return "hidden";
		// }
		// return editStyle;
	}

	public void setEditStyle(String editStyle) {
		this.editStyle = editStyle;
	}

	public String getRelateName() {
		return relateName;
	}

	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}

	public String getRelateFieldName() {
		return relateFieldName;
	}

	public void setRelateFieldName(String relateFieldName) {
		this.relateFieldName = relateFieldName;
	}

	public String getSearchName() {
		if (StringUtils.isNotNull(this.searchName)) {
			if ("T".equals(this.searchName) || "t".equals(this.searchName)) {
				return InitCodeConfig.searchSign;
			}
		}
		return "";
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getTableColName() {
		return tableColName;
	}

	public void setTableColName(String tableColName) {
		this.tableColName = tableColName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getPropertyDesc() {
		if (StringUtils.isNull(this.propertyDesc)) {
			this.propertyDesc = this.pageName;
		}
		return propertyDesc;
	}

	public void setPropertyDesc(String propertyDesc) {
		this.propertyDesc = propertyDesc;
	}

	public String getFieldDefaultValue() {
		return fieldDefaultValue;
	}

	public void setFieldDefaultValue(String fieldDefaultValue) {
		this.fieldDefaultValue = fieldDefaultValue;
	}

	public String getShowStyle() {
		return this.showStyle;
//		if ("N".equals(this.showStyle) || "n".equals(this.showStyle)) {
//			return "hidden";
//		} else {
//			return "text";
//		}
		// if(this.showStyle!=null||"".equals(this.showStyle)){
		// return "text";
		// }else if("Y".equals(this.showStyle)||"y".equals(this.showStyle)){
		// return "text";
		// }else if("N".equals(this.showStyle)||"n".equals(this.showStyle)){
		// return "hidden";
		// }
		// return this.showStyle;
	}

	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}

	public String getCheckStyle() {
		if (StringUtils.isNotNull(this.checkStyle)) {
			if ("T".equals(this.checkStyle) || "t".equals(this.checkStyle)) {
				return InitCodeConfig.searchSign;
			}
		}
		return "";
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}

	public String getRelateInfo() {
		return relateInfo;
	}

	public void setRelateInfo(String relateInfo) {
		this.relateInfo = relateInfo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		if (StringUtils.isNull(this.fieldDesc)) {
			this.fieldDesc = this.pageName;
		}
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

	public String getIsKey() {
		if (StringUtils.isNull(this.isKey)) {
			return "N";
		} else if ("Y".equals(this.isKey) || "y".equals(this.isKey)) {
			return "Y";
		}
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public String getRelateTabName() {
		return relateTabName;
	}

	public void setRelateTabName(String relateTabName) {
		this.relateTabName = relateTabName;
	}

	public MyColumn getMyColumn() {
		MyColumn p = new MyColumn();

		if (StringUtils.isNotNull(this.getRelateInfo())) {
			p.setRelateInfo(this.getRelateFieldName() + InitCodeConfig.defaultSplitSign + this.getRelateInfo() + InitCodeConfig.defaultSplitSign + this.getRelateName() + InitCodeConfig.defaultSplitSign + this.getRelateTabName());
		}
		p.setPropertyCnName(this.getPageName());
		p.setPropertyDesc(this.getPropertyDesc());
		p.setPropertyName(this.getTableColName());
		p.setPropertyType(this.getClassType());

		p.setFieldName(this.getTableColName());

		p.setEditStyle(this.getCheckStyle() + this.getEditStyle());
		p.setShowStyle(this.getSearchName() + this.getShowStyle());

		p.setFieldDefaultValue(this.getFieldDefaultValue());
		p.setFieldDesc(this.getFieldDesc());
		p.setFieldIsNull(this.getFieldIsNull());
		p.setFieldLength(this.getFieldIsNull());

		p.setClassType(this.getClassType());
		return p;
	}
}
