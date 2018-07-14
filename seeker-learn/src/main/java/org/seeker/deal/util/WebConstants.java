package org.seeker.deal.util;

import java.io.IOException;
import java.util.Properties;

public  class WebConstants {

	public static Properties webAppConfigProperties = new Properties();
	
	static{
		try {
			WebConstants.webAppConfigProperties.load(WebConstants.class.getClassLoader().getResourceAsStream("deal.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException("网站配置文件不存在,请检查");
		}
	}
	/**
	 * 获取网站配置信息
	 * @param key
	 * @return
	 */
	public static String getAppconfigValue(String key) {
		return webAppConfigProperties.getProperty(key.toString());
	}
	
	/**
	 * 运行环境是否是测试环境模式还是生产环境模式
	 * 测试环境模式：1 页面会弹出验证码提示框；2 支付宝支付始终支付0.01元人民币
	 * 生产环境模式：1 页面不会弹出验证码提示框；2 支付宝支付始终按实际金额
	 * @return PRODUCTION,返回ture，其他则返回false
	 */
	public static boolean runASProductionMode(){
		String mode = getAppconfigValue("currentRunMode");
		if(mode != null && mode.equals("PRODUCTION")){
			return true;
		}
		return false;
	}
	
	public enum PublicResult{
		to404,tologin,login,toportal
	}
	public enum OrderInfoType{
		Room,Sail
	}
	/**
	 * 会员卡支付返回的状态
	 */
	public enum MemberCardPayReturnStatus{
		MEMBER_IS_NO_EXISTS,//（会员不存在）
		WAITING_ACTIVATION,//（会员状态为等待激活）
		DISABLED,//（会员状态为禁用）
		RISTRICTED,//（会员状态为限制使用）
		FROZEN,//（会员状态为冻结	）
		LOCKED,//（会员状态为冻结	）
		SUCCESS,//（成功）
		SUCCESS_AND_UPGRADE,//(消费成功，并且会员已经升级)
		EXCEPTION,//（异常）
		INPUT_WRONG_PASSWORD,//（输入密码错误）
		NO_ENOUGH_BALANCE,//（会员储值余额不够）
	}
	
	
}
