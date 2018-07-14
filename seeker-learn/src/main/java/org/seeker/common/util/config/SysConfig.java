package org.seeker.common.util.config;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

// 读取 系统配置文件properties

public class SysConfig extends Properties {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SysConfig.class);
	private SysConfig() {}
	
	private static SysConfig prop=new SysConfig();
	/**
	 * 装载配置信息
	 * @author yanlei
	 */
	static{
		init("/sys.properties");
	}
	
	/**
	 * 初始化文件名配置信息
	 * @param cfgFile
	 */
	private static void init(String cfgFilePath) {
		try {
			InputStream in = SysConfig.class.getResourceAsStream(cfgFilePath);
			prop.load(in);
			in.close();
			System.out.println(prop);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 获取Properties对象配置信息
	 * @return
	 */
	public static Properties getProp() {
		return prop;
	}
	
	/**
	 * 通过key获取配置信息
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return prop.getProperty(key);
	}
	
}
