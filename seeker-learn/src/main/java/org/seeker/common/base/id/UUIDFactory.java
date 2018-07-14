package org.seeker.common.base.id;

import org.apache.log4j.Logger;
import org.safehaus.uuid.UUIDGenerator;

public final class UUIDFactory {

	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static String generateUUID() {
		return UUIDGenerator.getInstance().generateTimeBasedUUID().toString().toUpperCase();
	}
	public static void main(String[] args) {
		System.out.println(UUIDFactory.generateUUID());
		System.out.println(new Uuid().getUUID().toUpperCase());
	}
}