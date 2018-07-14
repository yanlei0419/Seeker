package org.vegetto.sys.datadic.po;


import java.io.Serializable;
import org.seeker.common.base.entity.Page;

/**
 * 数据字典类别实体类
 * 	自动生成，对应sys_datadic_type表
 * @Date 2015-03-09 17:08:51
 */
public class DataDicTypePo extends Page implements Serializable {
	
	/**对应数据库字段*/
	private String id;	//主键id	主键id
	private String code;	//code	code
	private String name;	//字典名称	字典名称
	private String content;	//描述	描述
	private Integer seq;	//排序	排序
	
	/**拓展属性*/
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
}