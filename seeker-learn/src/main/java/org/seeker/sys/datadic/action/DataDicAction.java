package org.seeker.sys.datadic.action;

import com.opensymphony.xwork2.ModelDriven;
import org.seeker.common.base.spring3.SpringContextUtil;
import org.seeker.common.base.struts2.BaseAction;
import org.seeker.common.util.json.JsonUtil;
import org.seeker.sys.datadic.biz.DataDicBiz;
import org.seeker.sys.datadic.po.DataDicPo;

import java.io.PrintWriter;
import java.util.List;

/**
 * 数据字典Action
 * 	自动生成
 * @Date 2015-03-09 17:08:50
 * @author yanlei
 */
public class DataDicAction extends BaseAction implements ModelDriven<DataDicPo> {
//	private String ids[];
	private DataDicPo po = new DataDicPo();
//	public String[] getIds() {
//		return ids;
//	}
//	public void setIds(String[] ids) {
//		this.ids = ids;
//	}
	public DataDicPo getPo() {
		return po;
	}
	public void setPo(DataDicPo po) {
		this.po = po;
	}
	public DataDicPo getModel() {
		return (DataDicPo)initPage(po);
	}
	
	public String getList() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		List<DataDicPo> list=biz.getList(po);
		int count = biz.getCount(po);
		String result=JsonUtil.toJSONStringByFastjson(count,list);
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String toDataDicDetail() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		po=biz.getDataDic(po);
		request.setAttribute("DataDicPo",po);
		return "toDetail";
	}
	
	
	public String toDataDicUpdate() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		po=biz.getDataDic(po);
		request.setAttribute("DataDicPo",po);
		return "toUpdate";
	}
	
	
	public String updateDataDic() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		biz.updateDataDic(po);
		return "toList";
	}
	
	
	public String addDataDic() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		biz.addDataDic(po);
		return "toList";
	}
	
	
	public String deleteDataDic() throws Exception {
		response.setCharacterEncoding("utf-8");
		DataDicBiz biz=(DataDicBiz)SpringContextUtil.getBean("DataDicBiz");
		DataDicPo[] pos = new DataDicPo[po.getIds().length];
		PrintWriter out = response.getWriter();
		for (int i = 0; i < pos.length; i++) {
			pos[i] = new DataDicPo();
			pos[i].setId(po.getIds()[i]);
		}
		int result=biz.deleteDataDic(pos);
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