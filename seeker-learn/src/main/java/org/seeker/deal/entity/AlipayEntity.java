package org.seeker.deal.entity;

import java.io.Serializable;

public class AlipayEntity implements Serializable {
	/**
	 * 
	 */
	private String service;
	/**
	 * 合作身份者ID，以2088开头由16位纯数字组成的字符串
	 * AlipayConfig.partner
	 */
	private String partner;
	/**
	 * 字符编码格式 目前支持 gbk 或 utf-8
	 * AlipayConfig.input_charset
	 */
	private String _input_charset;
	/**
	 * 支付类型 : 1
	 */
	private String payment_type;
	/**
	 * 必填，不能修改
	 * 服务器异步通知页面路径
	 * 需http://格式的完整路径，不能加?id=123这类自定义参数
	 * AlipayConfig.notify_url
	 */
	private String notify_url;
	/**
	 * 页面跳转同步通知页面路径
	 * AlipayConfig.return_url
	 */
	private String return_url;
	/**
	 * 卖家支付宝帐户
	 * 必填
	 */
	private String seller_email;
	/**
	 * 商户订单号
	 * 商户网站订单系统中唯一订单号，必填
	 */
	private String out_trade_no;
	/**
	 * 订单名称
	 * 必填
	 */
	private String subject;
	/**
	 * 付款金额
	 * 必填
	 */
	private String total_fee;
	/**
	 * 订单描述
	 */
	private String body;
	/**
	 * 默认支付方式
	 * bankPay 银行支付
	 * 必填
	 */
	private String paymethod;
	/**
	 * 默认网银
	 * 必填，银行简码请参考接口技术文档
	 */
	private String defaultbank;
	/**
	 * 商品展示地址
	 * AlipayConfig.show_url
	 */
	private String show_url;
	/**
	 * 防钓鱼时间戳
	 * 若要使用请调用类文件submit中的query_timestamp函数
	 */
	private String anti_phishing_key;
	/**
	 * 客户端的IP地址
	 * 非局域网的外网IP地址，如：221.0.0.1
	 */
	private String exter_invoke_ip;
	/**交易完成  只有用到参数*/
	 
	/**
	 * 查询是所用到的支付宝帐号
	 */
	private String trade_no;
	/**
	 * 订单返回结果
	 */
	private String is_success;
	/**
	 * 订单返回失败的原因
	 */
	private String error;
	
	/**
	 * 交易状态TRADE_FINISHED（普通即时到账的交易成功状态） TRADE_SUCCESS（开通了高级即时到账或机票分销产品后的支付成功状态）
	 */
	private String trade_status;
	
	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String tradeStatus) {
		trade_status = tradeStatus;
	}

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String isSuccess) {
		is_success = isSuccess;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String tradeNo) {
		trade_no = tradeNo;
	}
	
	

	public String toString(){
		return "return_url>>>>"+this.return_url+">>total_fee>>"+this.total_fee+">>body>>>>"+this.body;
	}
	
	public AlipayEntity() {
		super();
	}
	
	public AlipayEntity(String service, String partner, String inputCharset,
			String paymentType, String notifyUrl, String returnUrl,
			String sellerEmail, String outTradeNo, String subject,
			String totalFee, String body, String paymethod, String defaultbank,
			String showUrl, String antiPhishingKey, String exterInvokeIp,
			String tradestatus) {
		super();
		this.service = service;
		this.partner = partner;
		_input_charset = inputCharset;
		payment_type = paymentType;
		notify_url = notifyUrl;
		return_url = returnUrl;
		seller_email = sellerEmail;
		out_trade_no = outTradeNo;
		this.subject = subject;
		total_fee = totalFee;
		this.body = body;
		this.paymethod = paymethod;
		this.defaultbank = defaultbank;
		show_url = showUrl;
		anti_phishing_key = antiPhishingKey;
		exter_invoke_ip = exterInvokeIp;
		trade_status=tradestatus;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String get_input_charset() {
		return _input_charset;
	}
	public void set_input_charset(String inputCharset) {
		_input_charset = inputCharset;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String paymentType) {
		payment_type = paymentType;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String returnUrl) {
		return_url = returnUrl;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String sellerEmail) {
		seller_email = sellerEmail;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getDefaultbank() {
		return defaultbank;
	}
	public void setDefaultbank(String defaultbank) {
		this.defaultbank = defaultbank;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String showUrl) {
		show_url = showUrl;
	}
	public String getAnti_phishing_key() {
		return anti_phishing_key;
	}
	public void setAnti_phishing_key(String antiPhishingKey) {
		anti_phishing_key = antiPhishingKey;
	}
	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}
	public void setExter_invoke_ip(String exterInvokeIp) {
		exter_invoke_ip = exterInvokeIp;
	}
	

}
