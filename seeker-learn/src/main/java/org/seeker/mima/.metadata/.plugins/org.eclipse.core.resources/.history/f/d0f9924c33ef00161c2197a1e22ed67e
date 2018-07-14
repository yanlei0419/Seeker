package org.vegetto.common.base.entity;

import java.io.Serializable;

public abstract class Page implements Serializable {
	
	/**
	 * 开始
	 */
	private Integer start;
	/**
	 * 开始
	 */
	private Integer begin;
	/**
	 * 结束
	 */
	private Integer end;
	/**
	 * 排序字段
	 */
	private String order="";
	/**
	 * 升序还是降序
	 */
	private String sort="";
	
	/**
	 * ids 数组一般用于批量删除数据或者批量修改数据
	 */
	private String[] ids;
	
	
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Page() {
		super();
	}
	
}
