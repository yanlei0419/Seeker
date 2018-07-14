package org.vegetto.properties;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class SysConfig extends Properties {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(SysConfig.class);
	private SysConfig() {}
	
	private static SysConfig prop=new SysConfig();
	static{
		init("/sys.properties");
		init("/jdbc.properties");
	}
	
	private static void init(String cfgFile) {
		try {
			InputStream in = SysConfig.class.getResourceAsStream(cfgFile);
			prop.load(in);
			in.close();
			System.out.println(prop);
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
