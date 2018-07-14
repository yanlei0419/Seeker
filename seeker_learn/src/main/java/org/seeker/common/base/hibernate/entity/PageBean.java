package org.seeker.common.base.hibernate.entity;

import java.io.Serializable;
import java.util.List;


public class PageBean implements Serializable {
	private String name;
	private List lstPo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getLstPo() {
		return lstPo;
	}
	public void setLstPo(List lstPo) {
		this.lstPo = lstPo;
	}

}
