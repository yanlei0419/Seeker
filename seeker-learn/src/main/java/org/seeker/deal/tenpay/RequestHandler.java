package org.seeker.deal.tenpay;

import org.seeker.deal.tenpay.config.TenpayConfig;
import org.seeker.deal.tenpay.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 请求处理类
 * 请求处理类继承此类，重写createSign方法即可。
 * @author miklchen
 *
 */
public class RequestHandler {
	
	/** 网关url地址 */
	private String gateUrl= TenpayConfig.tenpayURL;
	
	/** 密钥 */
	private final String key= TenpayConfig.key;
	

	/** 请求的参数 */
	private Map<String,String> parameters;
	
	public RequestHandler(){
		parameters=new HashMap<String, String>();
		debugInfo="";
	}
	/** debug信息 */
	private String debugInfo;
	



	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
	public void setParameter(String key,String value) {
		this.parameters.put(key, value);
	}

	/**
	*初始化函数。
	*/
	public void init() {
		//nothing to do
	}

	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}
	public String getGateUrl() {
		return gateUrl;
	}

	public RequestHandler(Map<String, String> parameters) {
		super();
		this.parameters = parameters;
	}

	
	

	/**
	*获取debug信息
	*/
	public String getDebugInfo() {
		return debugInfo;
	}
	
	/**
	 * 获取带参数的请求URL
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public String getRequestURL() throws UnsupportedEncodingException {
		
		this.createSign();
		
		StringBuffer sb = new StringBuffer();
		String enc = this.parameters.get("input_charset");
		List<String> keys =new ArrayList<String>(this.parameters.keySet());
		keys=this.getKeySort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String k = keys.get(i);
			String v = this.parameters.get(k);
			if(!"spbill_create_ip".equals(k)) {
				sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
			} else {
				sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
			}
		}
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		
		return this.gateUrl + "?" + reqPars;
		
	}
	
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign() {
		StringBuffer sb = new StringBuffer();
		List<String> keys =new ArrayList<String>(this.parameters.keySet());
		keys=this.getKeySort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String k = keys.get(i);
			String v = this.parameters.get(k);
			if(null != v && !"".equals(v) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.key);
		
		String enc = this.parameters.get("input_charset");
		String sign = MD5Util.MD5Encode(sb.toString(), enc);
		
		this.parameters.put("sign", sign);
		
		//debug信息
		this.setDebugInfo(sb.toString() + " => sign:" + sign);
		
	}
	
	/**
	*设置debug信息
	*/
	protected void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	private List<String> getKeySort(List<String> list){
		Collections.sort(list);
		return list;
	}
	 
}
