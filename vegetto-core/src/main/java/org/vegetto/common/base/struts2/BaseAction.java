package org.vegetto.common.base.struts2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.vegetto.common.base.entity.Page;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletContextAware, ServletRequestAware, ServletResponseAware, SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3564987933234961851L;
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	private ServletContext servletContext;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	private Map<String, Object> session;
	protected int rows = 10;
	protected int page = 1;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Object initPage(Page page) {
		try {
			this.rows = Integer.parseInt(this.request.getParameter("rows"));
		} catch (Exception e) {
			this.rows = 10;
		}
		try {
			this.page = Integer.parseInt(this.request.getParameter("page"));
		} catch (Exception e) {
			this.page = 1;
		}
		page.setBegin(this.rows * (this.page - 1) + 1);
		page.setEnd(this.rows * this.page);

		return page;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Object getReqAttribute(String name) {
		return request.getAttribute(name);
	}

	public void setReqAttribute(String name, Object obj) {
		request.setAttribute(name, obj);
	}

}
