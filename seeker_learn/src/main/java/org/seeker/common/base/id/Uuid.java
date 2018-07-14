package org.seeker.common.base.id;

import java.util.UUID;

public class Uuid {

	public static synchronized String getUUID(){
		return UUID.randomUUID().toString();
	}

}
