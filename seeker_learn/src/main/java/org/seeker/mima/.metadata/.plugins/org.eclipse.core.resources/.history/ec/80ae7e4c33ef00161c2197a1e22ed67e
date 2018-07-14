package org.vegetto.common.base.id;

import org.apache.log4j.Logger;

public final class IDUtil {
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static String getNextID(String paramString) {
		if (paramString == null) {
			paramString = "000-";
		} else if (paramString.trim().length() > 12) {
			paramString = paramString.trim().substring(0, 13) + "-";
		} else {
			paramString = paramString + "-";
		}
		return paramString + UUIDFactory.generateUUID();
	}

	public static boolean isCanDelete(String paramString) {
		return paramString.indexOf("-00000000-0000-0000-0000-0000000000") == -1;
	}

	public static void main(String[] paramArrayOfString) {
		System.out.println(getNextID("00123-"));
	}
}