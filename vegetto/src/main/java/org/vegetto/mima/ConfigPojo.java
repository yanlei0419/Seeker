package org.vegetto.mima;

/**
 * @author Shuni Tang
 * @date 2016年9月6日 下午7:00:31
 * @parameter configuration parameter bean
 */

public class ConfigPojo {
	private short serverport;// server port
	private int rbuffersize;// the size of receive buffer
	private int sbuffersize;// the size of send buffer
	private short cthreadcount;// the number of consumer thread
	private short pthreadcount;// the number of producer thread
	private short NIOthreadcoun;// the number of NIO thread
	private int idletime;// unit second
	private int parsingStyle;// "1" for Electric vehicle,"2" for diesel vehicles

	public short getServerport() {
		return serverport;
	}

	public void setServerport(short serverport) {
		this.serverport = serverport;
	}

	public int getRbuffersize() {
		return rbuffersize;
	}

	public void setRbuffersize(int rbuffersize) {
		this.rbuffersize = rbuffersize;
	}

	public int getSbuffersize() {
		return sbuffersize;
	}

	public void setSbuffersize(int sbuffersize) {
		this.sbuffersize = sbuffersize;
	}

	public short getCthreadcount() {
		return cthreadcount;
	}

	public void setCthreadcount(short cthreadcount) {
		this.cthreadcount = cthreadcount;
	}

	public short getPthreadcount() {
		return pthreadcount;
	}

	public void setPthreadcount(short pthreadcount) {
		this.pthreadcount = pthreadcount;
	}

	public short getNIOthreadcoun() {
		return NIOthreadcoun;
	}

	public void setNIOthreadcoun(short nIOthreadcoun) {
		NIOthreadcoun = nIOthreadcoun;
	}

	public int getIdletime() {
		return idletime;
	}

	public void setIdletime(int idletime) {
		this.idletime = idletime;
	}

	public int getParsingStyle() {
		return parsingStyle;
	}

	public void setParSingstyle(int parsingStyle) {
		this.parsingStyle = parsingStyle;
	}
}
