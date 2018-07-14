package org.vegetto.deal.tenpay.config;

import org.vegetto.deal.util.WebConstants;


public class TenpayConfig {
	
	// 合作身份者ID，
	public final static String partner = WebConstants.getAppconfigValue("tenpay_partner");
	// 商户的私钥
	public final static String key = WebConstants.getAppconfigValue("tenpay_key");
	/**
	 * 收款账号
	 */
	public final static String seller_email= WebConstants.getAppconfigValue("temp_seller_email");
	/**
     * 财付通提供给商户的服务接入网关URL(新)
     */
	public final static String tenpayURL= WebConstants.getAppconfigValue("tenpayURL");


	
	
	//在这里配置回调信息
	public static String show_url  = WebConstants.getAppconfigValue("paymentReturnServer")+WebConstants.getAppconfigValue("alipayCallbackShowURL");
	/**
	 * 服务器异步通知页面路径
	 * 支付宝服务器主动通知商户网站里指定的页面http路径。
	 *demo: http://api.test.alipay.net/atinterface/receive_notify.htm
	 */
	public static String notify_url = WebConstants.getAppconfigValue("paymentServer")+WebConstants.getAppconfigValue("alipayCallbackNotifyURL");
	/**
	 * 页面跳转同步通知页面路径
	 * 支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径。
	 */
	public static String return_url = WebConstants.getAppconfigValue("paymentReturnServer")+WebConstants.getAppconfigValue("alipayCallbackReturnURL");;
	
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "../logs/zfb.log";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	public static String input_charset_UPPER = "UTF-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	
	public static String payment_type = "1";
	
	public static String alipayMethodType="get";
	
	public static String alipayButtonName="财付通";

}
