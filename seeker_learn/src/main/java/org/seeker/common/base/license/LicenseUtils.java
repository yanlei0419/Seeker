package org.seeker.common.base.license;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * @author yanlei
 *
 */
public class LicenseUtils {
	protected static Logger logger = Logger.getLogger(LicenseUtils.class);
	
	public static void Verify(Logger log){
		try {
			String className = "org.vegetto.common.base.license.";
			className = className + "LicenseValidate";
			Class cls = Class.forName(className);
			Object instance = cls.newInstance();
			String methodName = "LicenseValidate";
			Method localMethod = cls.getMethod(methodName, new Class[0]);
			localMethod.invoke(instance);
		} catch (Exception e) {
			log.error("license验证失败", e);
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void Verify(){
		try {
			String className = "org.vegetto.common.base.license.";
			className = className + "LicenseValidate";
			Class cls = Class.forName(className);
			Object instance = cls.newInstance();
			String methodName = "LicenseValidate";
			Method localMethod = cls.getMethod(methodName, new Class[0]);
			localMethod.invoke(instance);
		} catch (Exception e) {
			logger.error("license验证失败", e);
			e.printStackTrace();
			System.exit(0);
		}
	}

}
