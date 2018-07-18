package org.seeker.deal.tenpay.action;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.opensymphony.xwork2.ModelDriven;
import org.seeker.common.base.struts2.BaseAction;
import org.seeker.common.util.DateUtil;
import org.seeker.deal.alipay.util.UtilTools;
import org.seeker.deal.entity.TenpayEntity;
import org.seeker.deal.tenpay.RequestHandler;
import org.seeker.deal.tenpay.config.TenpayConfig;
import org.seeker.deal.util.DealUtil;


public class TenpayAction extends BaseAction implements ModelDriven<TenpayEntity> {
	
	private TenpayEntity po;
	
	/**
	 * 提交财付通的表单
	 * @return
	 * @throws Exception
	 */
	public String submitTenpay() throws Exception{
		response.setCharacterEncoding("utf-8");
		po.setTotal_fee(DealUtil.decryptBASE64(po.getTotal_fee()));//解码金额
		po.setTotal_fee(""+Double.valueOf(""+Double.valueOf(po.getTotal_fee())*100).intValue());
		po.setPartner(TenpayConfig.partner);
//		map.put("partner",TenpayConfig.partner ); // 商户号
//		map.put("out_trade_no",out_trade_no); // 商家订单号
//		map.put("total_fee",total_fee); // 商品金额,以分为单位
		String returnUrl=request.getParameter("returnUrl");
		//po.setReturn_url(TenpayConfig.return_url);
		po.setReturn_url(returnUrl==null?TenpayConfig.return_url:returnUrl);//如果传递就用，不传递就不用
//		map.put("return_url", TenpayConfig.return_url); // 交易完成后跳转的URL
		String notifyUrl=request.getParameter("notifyUrl");
		po.setNotify_url((notifyUrl==null||notifyUrl.equals(""))?TenpayConfig.notify_url:notifyUrl);
//		map.put("notify_url", TenpayConfig.notify_url); // 接收财付通通知的URL
//		map.put("body",body ); // 商品描述
		//判断签名
		if(!DealUtil.validSignKey(request)){
			return null;//不通过不提交
		}
		po.setBank_type("WX");
//		map.put("bank_type", "DEFAULT"); // 银行类型 WX
		po.setSpbill_create_ip(request.getRemoteAddr());
//		map.put("spbill_create_ip", request.getRemoteAddr()); // 用户的公网ip
		po.setFee_type("1");
//		map.put("fee_type", "1");//币种

		// 系统可选参数
		po.setSign_type(TenpayConfig.sign_type);
		po.setService_version("1.0");
		po.setInput_charset(TenpayConfig.input_charset);
		po.setSign_key_index("1");
//		map.put("sign_type", TenpayConfig.sign_type);
//		map.put("service_version", "1.0");
//		map.put("input_charset", TenpayConfig.input_charset);
//		map.put("sign_key_index", "1");

		// 业务可选参数
		po.setAttach("");
		
//		map.put("attach", "");
//		map.put("product_fee", "0.1");//商品费用
//		map.put("transport_fee", "0");//物流费用
		po.setTime_start(DateUtil.getTime());
//		map.put("time_start", UtilDate.getOrderNum());//交易起始时间
//		map.put("time_expire", "");
//
//		map.put("buyer_id", "");//买方帐号
//		map.put("goods_tag", "");//商品标记  打折产品
		// map.put("agentid", "");
		// map.put("agent_type", "");
		
		Map<String, String> map = UtilTools.getThisMap(po);
		// 创建支付请求对象
		RequestHandler reqHandler = new RequestHandler(map);

		// 请求的url
		String requestUrl = reqHandler.getRequestURL();

		// 获取debug信息,建议把请求和debug信息写入日志，方便定位问题
		String debuginfo = reqHandler.getDebugInfo();
		UtilTools.sys("requestUrl:", requestUrl);
		UtilTools.sys("debuginfo:", debuginfo);
		request.setAttribute("form", requestUrl);
		return "submitFrom";
	}
	
	/**
	 * 查询财务通
	 * @return
	 * @throws Exception
	 */
	public String selTenpayStatus() throws Exception{
		SortedMap<String, String> map = new TreeMap<String, String>();

		map.put("partner", TenpayConfig.partner); // 商户号
		//out_trade_no和transaction_id至少一个必填，同时存在时transaction_id优先
		map.put("out_trade_no",request.getParameter("out_trade_no"));    	    	//商家订单号
		map.put("input_charset", TenpayConfig.input_charset);
		// reqHandler.setParameter("transaction_id",		// "1900000109201101270026218385"); //财付通交易单号
		map.put("spbill_create_ip", request.getRemoteAddr()); // 用户的公网ip
		
		
		po=DealUtil.getSelTenpayResult(map);
		return NONE;
	}
	public String getTenpayStatus() throws Exception{
		//获取返回参数
		String retcode = (String)request.getParameter("retcode");
		String trade_state = (String)request.getParameter("trade_state");
		String trade_mode =  (String)request.getParameter("trade_mode");
		UtilTools.sys("retcode", retcode);
		UtilTools.sys("trade_state", trade_state);
		UtilTools.sys("trade_mode", trade_mode);
		return NONE;
	}

	
	public String processTenpayUrl() throws Exception {
		return NONE;
	}

	public void setPo(TenpayEntity po) {
		this.po = po;
	}

	public TenpayEntity getModel() {
		if (po == null) {
			po = new TenpayEntity();
		}
		return po;
	}

	public TenpayEntity getPo() {
		return po;
	}
	
	
}
