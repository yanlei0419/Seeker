package org.vegetto.common.base.tag;

import javax.servlet.jsp.tagext.TagSupport;

import org.vegetto.common.util.StringUtils;

@SuppressWarnings("serial")
public class BaseFromTag extends TagSupport{
	private String val;
	private String id;
	private String name;
	private String onChange;
	private String onClick;
	private String style;
	private String content;
	private String readyOnly;
	private String disabled;
	private String type;
	
	public String toFromType(){
		if(StringUtils.isNull(this.getType())){
			return "";
		}
		return " type=\""+this.getDisabled()+"\" ";
	}
	public String toFromDisabled(){
		if(StringUtils.isNull(this.getDisabled())){
			return "";
		}
		return " disabled=\""+this.getDisabled()+"\" ";
	}
	public String toFromReadyOnly(){
		if(StringUtils.isNull(this.getReadyOnly())){
			return "";
		}
		return " readyOnly=\""+this.getReadyOnly()+"\" ";
	}
	public String toFromId(){
		if(StringUtils.isNull(this.getId())){
			return "";
		}
		return " id=\""+this.getId()+"\" ";
	}
	public String toFormName(){
		if(StringUtils.isNull(this.getName())){
			return "";
		}
		return " name=\""+this.getName()+"\" ";
	}
	public String toFormValue(){
		if(StringUtils.isNull(this.getVal())){
			return "";
		}
		return " value=\""+this.getVal()+"\" ";
	}
	public String toFormOnChange(){
		if(StringUtils.isNull(this.getOnChange())){
			return "";
		}
		return " onChange=\""+this.getOnChange()+"\" ";
	}
	public String toFormOnClick(){
		if(StringUtils.isNull(this.getOnClick())){
			return "";
		}
		return " onClick=\""+this.getOnClick()+"\" ";
	}
	public String toFormStyle(){
		if(StringUtils.isNull(this.getStyle())){
			return "";
		}
		return " style=\""+this.getStyle()+"\" ";
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getReadyOnly() {
		return readyOnly;
	}
	public void setReadyOnly(String readyOnly) {
		this.readyOnly = readyOnly;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOnChange() {
		return onChange;
	}
	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}
	public String getOnClick() {
		return onClick;
	}
	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
