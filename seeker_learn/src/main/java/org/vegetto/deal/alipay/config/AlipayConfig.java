package org.vegetto.deal.alipay.config;

import org.vegetto.deal.util.WebConstants;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
//	public final static String partner = "2088901057080473";
//	public final static String partner = "2088201564809153";
	public final static String partner = WebConstants.getAppconfigValue("apipay_partner");
	// 商户的私钥
//	public final static String key = "67vwurw557i7eabdoyuj9v4kqlat8igj";
//	public final static String key = "umz4aea6g97skeect0jtxigvjkrimd0o";
	public final static String key = WebConstants.getAppconfigValue("apipay_key");
	/**
	 * 收款账号
	 */
//	public final static String seller_email="zhifubao001@wanda.cn";
//	public final static String seller_email="alipay-test12@alipay.com";
	public final static String seller_email= WebConstants.getAppconfigValue("seller_email");
	
	public final static String alipayURL=WebConstants.getAppconfigValue("alipayURL");

	
	
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
	
	public static String alipayButtonName="使用支付宝支付";

}
