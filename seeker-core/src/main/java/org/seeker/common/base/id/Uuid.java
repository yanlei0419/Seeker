package org.seeker.common.base.id;

import java.util.UUID;

import org.apache.log4j.Logger;

public class Uuid {

	protected Logger logger = Logger.getLogger(this.getClass().getName());
//	private UUID uuid;
	
//	public Uuid(){
//		uuid=new Uuid()
//	}
	public synchronized String getUUID(){
		return UUID.randomUUID().toString();
	}

}
