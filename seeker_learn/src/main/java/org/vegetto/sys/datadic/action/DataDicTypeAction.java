package org.vegetto.sys.datadic.action;

import java.io.PrintWriter;
import java.util.List;

import org.seeker.common.util.json.JsonUtil;
import org.seeker.common.base.spring3.SpringContextUtil;
import org.seeker.common.base.struts2.BaseAction;

import org.vegetto.sys.datadic.biz.DataDicTypeBiz;
import org.vegetto.sys.datadic.po.DataDicTypePo;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 数据字典类别Action
 * 	自动生成
 * @Date 2015-03-09 17:08:51
 * @author yanlei
 */
public class DataDicTypeAction extends BaseAction implements ModelDriven<DataDicTypePo> {
//	private String ids[];
	private DataDicTypePo po = new DataDicTypePo();
//	public String[] getIds() {
//		return ids;
//	}
//	public void setIds(String[] ids) {
//		this.ids = ids;
//	}
	public DataDicTypePo getPo() {
		return po;
	}
	public void setPo(DataDicTypePo po) {
		this.po = po;
	}
	public DataDicTypePo getModel() {
		return (DataDicTypePo)initPage(po);
	}
	
	public String getList() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		List<DataDicTypePo> list=biz.getList(po);
		int count = biz.getCount(po);
		String result=JsonUtil.toJSONStringByFastjson(count,list);
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String toDataDicTypeDetail() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		po=biz.getDataDicType(po);
		request.setAttribute("DataDicTypePo",po);
		return "toDetail";
	}
	
	
	public String toDataDicTypeUpdate() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		po=biz.getDataDicType(po);
		request.setAttribute("DataDicTypePo",po);
		return "toUpdate";
	}
	
	
	public String updateDataDicType() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		biz.updateDataDicType(po);
		return "toList";
	}
	
	
	public String addDataDicType() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		biz.addDataDicType(po);
		return "toList";
	}
	
	
	public String deleteDataDicType() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicTypeBiz biz=(DataDicTypeBiz)SpringContextUtil.getBean("DataDicTypeBiz");
		DataDicTypePo[] pos = new DataDicTypePo[po.getIds().length];
		PrintWriter out = response.getWriter();
		for (int i = 0; i < pos.length; i++) {
			pos[i] = new DataDicTypePo();
			pos[i].setId(po.getIds()[i]);
		}
		int result=biz.deleteDataDicType(pos);
		if(result>0){
			out.println("删除成功!");
		}
		else{
			out.println("删除失败!");
		}
		out.flush();
		out.close();
		return null;
	}
}