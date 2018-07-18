package org.seeker.deal.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.seeker.common.base.id.Uuid;
import org.seeker.common.util.XmlTools;

import org.seeker.deal.alipay.services.AlipayService;
import org.seeker.deal.alipay.util.UtilTools;
import org.seeker.deal.alipay.util.httpClient.HttpProtocolHandler;
import org.seeker.deal.alipay.util.httpClient.HttpRequest;
import org.seeker.deal.alipay.util.httpClient.HttpResponse;
import org.seeker.deal.alipay.util.httpClient.HttpResultType;
import org.seeker.deal.entity.AlipayEntity;
import org.seeker.deal.entity.TenpayEntity;
import org.seeker.deal.tenpay.RequestHandler;
import org.seeker.deal.tenpay.ResponseHandler;
import org.seeker.deal.tenpay.client.ClientResponseHandler;
import org.seeker.deal.tenpay.client.TenpayHttpClient;
import org.seeker.deal.tenpay.config.TenpayConfig;
import org.seeker.deal.tenpay.util.MD5Util;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 完成支付宝和财付通查询
 * 
 * @author: yanlei
 * @version: 0.1
 * @Date: 2014-03-30
 */
public class DealUtil {

	/**
	 *@author YanLei 20140329 查询支付宝订单接口
	 * @param sParaTemp
	 *            查询的参数 商户网站已经付款完成的商户网站订单号 -- out_trade_no
	 *            已经付款完成的支付宝交易号，与商户网站订单号out_trade_no相对应 --trade_no 可以为null
	 * @return 返回查询结果的实体类
	 */
	public static AlipayEntity getSelAlipayResult(Map<String, String> sParaTemp) {
		return AlipayService.getSelAlipayResult(sParaTemp);
	}

	/**
	 * 财付通查询接口
	 * 
	 * @author YanLei 20140329
	 * @param sParaTemp
	 *            查询的参数
	 * @return 返回查询结果的实体类
	 * @throws Exception
	 */
	public static TenpayEntity getSelTenpayResult(Map<String, String> sParaTemp) throws Exception {
		TenpayEntity entity = new TenpayEntity();
		RequestHandler reqHandler = new RequestHandler(sParaTemp);
		reqHandler.setGateUrl("https://gw.tenpay.com/gateway/normalorderquery.xml");
		String requestUrl = reqHandler.getRequestURL();

		UtilTools.sys("requestUrl:", requestUrl);

		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.BYTES);
		// 设置编码集
		request.setCharset(TenpayConfig.input_charset);
		// request.setMethod(HttpRequest.METHOD_GET);
		// NameValuePair[] sPara={new NameValuePair("input_charset",
		// TenpayConfig.input_charset)};
		// request.setParameters(sPara);
		request.setUrl(requestUrl);

		HttpResponse response = httpProtocolHandler.execute(request);
		if (response == null) {
			return null;
		}
		String strResult = "";
		try {
			strResult = response.getStringResult();
			System.out.println(strResult);
			Document doc = XmlTools.getDocumentByString(strResult);
			entity = (TenpayEntity) XmlTools.xmlResolve(doc.getRootElement(), entity);
			UtilTools.showObj(entity);
		} catch (Exception e) {
			UtilTools.sys("tenpay", "请求失败");
			e.printStackTrace();
		}

		return entity;
	}

	/**
	 * @description 获取财付通订单号
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public static String getTennyPayOrderNo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String partner = TenpayConfig.partner;
		String key = TenpayConfig.key;
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(key);
		String out_trade_no = "";
		if (resHandler.isTenpaySign()) {
			// 通知id
			String notify_id = resHandler.getParameter("notify_id");
			// 创建请求对象
			RequestHandler queryReq = new RequestHandler(new HashMap<String, String>());
			// 通信对象
			TenpayHttpClient httpClient = new TenpayHttpClient();
			// 应答对象
			ClientResponseHandler queryRes = new ClientResponseHandler();

			// 通过通知ID查询，确保通知来至财付通
			queryReq.init();
			// queryReq.setKey(key);
			queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
			queryReq.setParameter("partner", partner);
			queryReq.setParameter("notify_id", notify_id);

			// 通信对象
			httpClient.setTimeOut(5);
			// 设置请求内容
			httpClient.setReqContent(queryReq.getRequestURL());
			System.out.println("queryReq:" + queryReq.getRequestURL());

			// 后台调用
			if (httpClient.call()) {
				// 设置结果参数
				queryRes.setContent(httpClient.getResContent());
				System.out.println("queryRes:" + httpClient.getResContent());
				queryRes.setKey(key);

				// 获取返回参数
				String retcode = queryRes.getParameter("retcode");
				String trade_state = queryRes.getParameter("trade_state");
				String trade_mode = queryRes.getParameter("trade_mode");

				// 判断签名及结果
				if (queryRes.isTenpaySign() && "0".equals(retcode) && "0".equals(trade_state) && "1".equals(trade_mode)) {

					System.out.println("订单查询成功");
					// 取结果参数做业务处理
					System.out.println("out_trade_no:" + queryRes.getParameter("out_trade_no") + " transaction_id:" + queryRes.getParameter("transaction_id"));
					System.out.println("trade_state:" + queryRes.getParameter("trade_state") + " total_fee:" + queryRes.getParameter("total_fee"));
					// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
					System.out.println("discount:" + queryRes.getParameter("discount") + " time_end:" + queryRes.getParameter("time_end"));

					// 商户订单号
					out_trade_no = queryRes.getParameter("out_trade_no");

					// 财付通订单号
					String transaction_id = queryRes.getParameter("transaction_id");

					// 金额,以分为单位
					String total_fee = queryRes.getParameter("total_fee");

					// 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
					String discount = queryRes.getParameter("discount");

					// ------------------------------
					// 处理业务开始
					// ------------------------------

					// ------------------------------
					// 处理业务完毕
					// ------------------------------
					System.out.println("支付成功");
				}
				// else{
				// //错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				// System.out.println("查询验证签名失败或业务错误");
				// System.out.println("retcode:" +
				// queryRes.getParameter("retcode")+
				// " retmsg:" + queryRes.getParameter("retmsg"));
				// System.out.println("通知查询签名失败或业务参数错误，支付失败");
				// }
			}
			// else {
			// System.out.println("后台调用通信失败");
			// System.out.println(httpClient.getResponseCode());
			// System.out.println(httpClient.getErrInfo());
			// System.out.println("订单通知查询失败");
			// //有可能因为网络原因，请求已经处理，但未收到应答。
			// }
		}
		// else {
		// System.out.println("认证签名失败");
		// }
		// 获取debug信息,建议把debug信息写入日志，方便定位问题
		String debuginfo = resHandler.getDebugInfo();
		System.out.println("debuginfo:" + debuginfo);
		return out_trade_no;
	}

	/**
	 * 通过 action 传入request ,response 来确定,财付通和支付宝返回的值 yanlei
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static TenpayEntity getReturnUrlTenpay(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("partner", TenpayConfig.partner); // 商户号
		try {
			sParaTemp.put("out_trade_no", getTennyPayOrderNo(request, response));
		} catch (Exception e) {
			UtilTools.sys("out_trade_no", "订单号没有获取到");
			e.printStackTrace();
		} // 商家订单号
		sParaTemp.put("input_charset", TenpayConfig.input_charset);
		sParaTemp.put("spbill_create_ip", request.getRemoteAddr()); // 用户的公网ip

		TenpayEntity entity = null;
		try {
			entity = getSelTenpayResult(sParaTemp);
		} catch (Exception e) {
			UtilTools.sys("TenpayEntity", "财付通返回实体bean封装出问题");
			e.printStackTrace();
		}
		return entity;

	}

	/**
	 * 财付通订单号不能超过32位
	 */
	public static String getUUID() {
		return MD5Util.MD5Encode(new Uuid().getUUID() + new Date().getTime(), "utf-8");
	}

	/**
	 * @description BASE64加密
	 * @param str
	 * @return
	 */
	public static String encryptBASE64(String str) {
		String result = (new BASE64Encoder()).encodeBuffer(str.getBytes());
		// 去掉换行符
		return filter(result);
	}

	/**
	 * @description BASE64解密
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		char ln = 10;// 解密的时候再加上换行符
		byte[] bytes = (new BASE64Decoder()).decodeBuffer((key + ln));
		return new String(bytes);
	}

	public static String decryptBASE64BySearch(String key) throws Exception {
		if (key == null || "".equals(key) || "null".equals(key)) {
			return "";
		}
		byte[] bytes = (new BASE64Decoder()).decodeBuffer((key));
		return new String(bytes);
	}

	/**
	 * @description 验证传递信息是否被修改
	 * @param request
	 * @return
	 */
	public static boolean validSignKey(HttpServletRequest request) {
		String money64 = request.getParameter("total_fee");
		String orderId = request.getParameter("out_trade_no");
		String subject = request.getParameter("subject");// 订单名称
		String body = request.getParameter("body");// 订单描述
		String notifyUrl = request.getParameter("notifyUrl");
		String returnUrl = request.getParameter("returnUrl");// 订单描述
		String signKey = request.getParameter("signKey");
		String nowKey = MD5Util.MD5Encode(subject + orderId + money64 + body + returnUrl + notifyUrl + "KEYSIGN", "utf-8");// 签名密匙
		return signKey.equals(nowKey);
	}

	/**
	 * @description 签名信息
	 * @param subject
	 *            订单名称
	 * @param orderId
	 *            订单编号
	 * @param money64
	 *            加密的钱数
	 * @param body
	 *            订单描述
	 * @param returnUrl
	 *            同步返回地址
	 * @param notifyUrl
	 *            异步通知地址
	 * @return
	 */
	public static String keySign(String subject, String orderId, String money64, String body, String returnUrl, String notifyUrl) {
		String allValues = subject + orderId + money64 + body + returnUrl + notifyUrl;// 所有的值都加入签名防止连接注入
		return MD5Util.MD5Encode(allValues + "KEYSIGN", "utf-8");// 签名密匙
	}

	/**
	 * @description 去掉换行符 10：换行符 13：回车
	 * @param str
	 * @return
	 */
	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}

}
