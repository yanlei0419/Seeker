package org.vegetto.deal.alipay.action;

import java.util.HashMap;
import java.util.Map;

import org.vegetto.common.base.struts2.BaseAction;
import org.vegetto.deal.alipay.config.AlipayConfig;
import org.vegetto.deal.alipay.util.AlipaySubmit;
import org.vegetto.deal.alipay.util.UtilTools;
import org.vegetto.deal.entity.AlipayEntity;
import org.vegetto.deal.util.DealUtil;

import com.opensymphony.xwork2.ModelDriven;

public class AlipayAtion extends BaseAction implements ModelDriven<AlipayEntity>{
	
	/**
	 * 提交支付包订单信息
	 * @return
	 * @throws Exception
	 */
	public String submitAlipay() throws Exception{
		response.setCharacterEncoding("utf-8");
		//支付类型
		this.po.setPayment_type("1");
		//必填，不能修改
		//服务器异步通知页面路径
		String notifyUrl=request.getParameter("notifyUrl");
		this.po.setNotify_url((notifyUrl==null||notifyUrl.equals(""))?AlipayConfig.notify_url:notifyUrl);
		//需http://格式的完整路径，不能加?id=123这类自定义参数
		//页面跳转同步通知页面路径
		String returnUrl=request.getParameter("returnUrl");
		//this.po.setReturn_url(AlipayConfig.return_url);
		this.po.setReturn_url(returnUrl==null?AlipayConfig.return_url:returnUrl);
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

		//卖家支付宝帐户
		String seller_email = AlipayConfig.seller_email;
			//String.valueOf(request.getParameter("WIDseller_email"));
		this.po.setSeller_email(seller_email);
		//必填
		//商户订单号
//		String out_trade_no = String.valueOf(request.getParameter("out_trade_no"));
//		po.setOut_trade_no(out_trade_no);
		//商户网站订单系统中唯一订单号，必填
		//订单名称
//		String subject = String.valueOf(request.getParameter("WIDsubject"));
//		po.setSubject(subject);
		//必填
		//付款金额
//		String total_fee = String.valueOf(request.getParameter("WIDtotal_fee"));
//		po.setTotal_fee(total_fee);
		//必填
		//订单描述
//		String body = String.valueOf(request.getParameter("WIDbody"));
//		po.setBody(body);
		//判断签名
		if(!DealUtil.validSignKey(request)){
			return null;//不通过不提交
		}
		po.setTotal_fee(DealUtil.decryptBASE64(po.getTotal_fee()));//解码金额
		
		//默认网银
		String defaultbank = "";
			//String.valueOf(request.getParameter("WIDdefaultbank"));
		po.setDefaultbank(defaultbank);
		//必填，银行简码请参考接口技术文档

		//默认支付方式
		this.po.setPaymethod("bankPay");
		//必填
		//商品展示地址
		this.po.setShow_url(AlipayConfig.show_url);
		//需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html

		//防钓鱼时间戳
		String anti_phishing_key = "";
		po.setAnti_phishing_key(anti_phishing_key);
		//若要使用请调用类文件submit中的query_timestamp函数

		//客户端的IP地址
		this.po.setExter_invoke_ip(request.getRemoteAddr());
		//非局域网的外网IP地址，如：221.0.0.1
		
		//提交类型
		this.po.setService("create_direct_pay_by_user");
		//合作者id
		this.po.setPartner(AlipayConfig.partner);
		//编码类型
		this.po.set_input_charset(AlipayConfig.input_charset);
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = UtilTools.getThisMap(po);
		UtilTools.showObj(po);
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认支付");
//		PrintWriter out=response.getWriter();
		//out.println(sHtmlText);
		request.setAttribute("form", sHtmlText);
		return "submitFrom";
	}
	
	/**
	 * 查看订单的交易状态
	 * @return
	 * @throws Exception
	 */
	public String getAlipayStatus() throws Exception{
		request.setCharacterEncoding("utf-8");
		Map<String, String >  map=new HashMap<String, String>();
		//商户网站已经付款完成的商户网站订单号
		String out_trade_no = request.getParameter("out_trade_no"); 
		po.setOut_trade_no(out_trade_no);
		//已经付款完成的支付宝交易号，与商户网站订单号out_trade_no相对应
		String trade_no = request.getParameter("trade_no");
		po.setTrade_no(trade_no);
        //out_trade_no、trade_no须至少填写一项
		
		String trade_status = new String(request.getParameter("trade_status"));
		if(trade_status.equals("TRADE_FINISHED")){
			//判断该笔订单是否在商户网站中d已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
			//注意：
			//该种交易状态只在两种情况下出现
			//1、开通了普通即时到账，买家付款成功后。
			//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
		} else if (trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
			//注意：
			//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
		}
		UtilTools.sys("return >>>>trade_status", trade_status);
		
		map=UtilTools.getThisMap(po);
		//查询结果的实体bean
		po=DealUtil.getSelAlipayResult(map);
		return NONE;
	}
	
	private AlipayEntity po;

	public void setPo(AlipayEntity po) {
		this.po = po;
	}

	public AlipayEntity getModel() {
		if(po==null){
			po=new AlipayEntity();
		}
		return po;
	}

}
