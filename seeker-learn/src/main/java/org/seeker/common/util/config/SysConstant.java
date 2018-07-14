package org.seeker.common.util.config;


/**
 * 系统默认参数配置 一般读取 sys.priperties文件
 * @author yanlei
 */
public class SysConstant {
	/**
	 * 项目名称
	 */
	public static final String projectName=SysConfig.get("projectName");
	/**
	 * web服务器 session 用户名称
	 */
	public static final String sessionUserName=SysConfig.get("sessionUserName");
	/**
	 * 数据库类型
	 */
	public static final String jdbcType = SysConfig.get("jdbcType");
	/**
	 * 默认分隔符
	 */
	public static final String defaultSplitSign="@@";

}
