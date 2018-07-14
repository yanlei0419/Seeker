package org.vegetto.sys.datadic.po;


import java.io.Serializable;
import org.seeker.common.base.entity.Page;

/**
 * 数据字典实体类
 * 	自动生成，对应sys_datadic表
 * @Date 2015-03-09 17:08:50
 */
public class DataDicPo extends Page implements Serializable {
	
	/**对应数据库字段*/
	private String id;	//主键id	主键id
	private String typecode;	//类别code	类别code    关联sys_datadic_type表的code字段
	private String code;	//code	code
	private String name;	//字典名称	字典名称
	private String content;	//描述	描述
	private String isSystem;	//是否为系统默认	是否为系统默认
	private Integer seq;	//排序	排序
	private String isDelete;	//是否已删除	是否已删除
	private String createBy;	//创建人	创建人
	private String createTime;	//创建时间	创建时间
	private String updateBy;	//修改人	修改人
	private String updateTime;	//修改时间	修改时间
	
	/**拓展属性*/
	private String typeName;//typecode的拓展属性，对应sys_datadic_type表的name字段
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsSystem() {
		return isSystem;
	}
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}