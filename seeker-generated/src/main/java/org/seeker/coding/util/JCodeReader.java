package org.seeker.coding.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.seeker.coding.util.print.PrintUtils;

// 读取 properties
public class JCodeReader extends Properties {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(JCodeReader.class);
	private JCodeReader() {}
	
	private static JCodeReader prop=new JCodeReader();
	static{
		init("/coding/jCode.properties");
	}
	
	private static void init(String cfgFile) {
		try {
			InputStream in = JCodeReader.class.getResourceAsStream(cfgFile);
			prop.load(in);
			in.close();
			PrintUtils.info("获取配置文件信息["+cfgFile+"]>>>>"+prop);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public static Properties getProp() {
		return prop;
	}
	
	public static String get(String key){
		return prop.getProperty(key);
	}
	
}
