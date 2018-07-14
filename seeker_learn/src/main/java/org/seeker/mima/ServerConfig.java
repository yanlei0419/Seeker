package org.vegetto.mima;
/** 
 * @author  Shuni Tang
 * @date 	2016年9月5日 上午11:33:54 
 * @parameter 	the class including of default settings
 */
public class ServerConfig {
	private ServerConfig(){
	}
	
	private static ServerConfig uniqueInstance;
	public static ServerConfig getInstance(){//use Singleton Pattern需要实例化该类的时候才将其实例化
		if(uniqueInstance==null)
			uniqueInstance=new ServerConfig();
		return uniqueInstance;
	}
	
	public static final short SERVERPORT=1800;//the default server port
	public static final int RBUFFERSIZE=200000;//the size of receive buffer
	public static final int SBUFFERSIZE=200000;//the size of send buffer
	public static final short CTHREADCOUNT=5;//the number of consumer thread
	public static final short PTHREADCOUNT=2;//the number of producer thread
	public static final short NIOTHREADCOUNT=5;//the number of NIO thread
	public static final int IDLETIME=6000;//unit second
	public static  int PARSESTYLE=2;// "1" for Electric vehicle,"2" for diesel vehicles
	public static int getPARSESTYLE() {
		return PARSESTYLE;
	}
	public static void setPARSESTYLE(int pARSESTYLE) {
		PARSESTYLE = pARSESTYLE;
	}

/*	public ConfigPojo parameters;//parameters from foreground,which access from user input
	public ConfigPojo getParameters() {
		return parameters;
	}

	public void setParameters(ConfigPojo parameters) {
		this.parameters = parameters;
	}*/
}
