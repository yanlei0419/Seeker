package org.vegetto.common.base.license.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

// 读取 系统配置文件properties
public class LicenseConfigReader {
	private static final Map<String, String> properties = new Hashtable<String, String>();
	static {
		initBean("/license/license.properties");
	}
	public static Map<String, String> getProperties(){
		return properties;
	}

	/**
	 * 读取指定的配置文件，生成其中配置的bean的实例，并且放到 <code>beans</code> 中
	 * 
	 * @param cfgFile
	 */
	private static void initBean(String cfgFile) {
		// 加载配置文件 Properties props
		Properties props = loadProperties(cfgFile);
		// 循环props, 生成配置的bean的实例
		for (Object obj : props.keySet()) {
			String key = (String) obj;
			String val = props.getProperty(key);
			try {
				properties.put(key, val);
			} catch (Exception e) {
				throw new RuntimeException("初始化【val=" + val + "】失败", e);
			}
		}
	}
	
	/**
	 * 读取指定的配置文件，生成其中配置的bean的实例，并且放到 <code>beans</code> 中
	 * 
	 * @param cfgFile
	 */
	private static void initStr(String cfgFile) {
		// 加载配置文件 Properties props
		Properties props = loadProperties(cfgFile);
		// 循环props, 生成配置的查询字符串
		for (Object obj : props.keySet()) {
			String key = (String) obj;
			try {
				properties.put(key, props.getProperty(key));
			} catch (Exception e) {
				throw new RuntimeException("初始化【className=" + key + "】失败", e);
			}
		}
	}
	
	

	private static Properties loadProperties(String cfgFile) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = LicenseConfigReader.class.getResourceAsStream(cfgFile);
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException("加载配置文件失败：" + cfgFile, e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getPorperty(String key) {
		return properties.get(key);
	}


}
