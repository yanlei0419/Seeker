package org.seeker.mima;

//import com.dataparse.java.ConsumerJava;
//import com.dataparse.jni.ConsumerJNI;
//import com.dataparse.jni.DataInteract;
//import com.dataparse.jni.GetDataFromJNI;
//import com.minaServer.codec.ByteArrayCodecFactory;
//import com.minaServer.hander.RawData;
//import com.minaServer.hander.ServerHandler;

/** 
 * @author	Shuni Tang
 * @date	2016年9月5日 上午11:36:41 
 * @parameter	startup minaServer
 */

public class Server {
//	private IoAcceptor acceptor;
//	private ServerHandler serverhandler;
//	public static BlockingQueue<RawData> Rqueue; 
//	public static BlockingQueue<String> Squeue; 
//	public static HashMap<IoSession , String> sessionmap = new HashMap<IoSession , String>(); 
//	private  ExecutorService cES;
//	private Thread  getDateFromJNI;
//	private DataInteract datainteract;
//	
//	public void start(){//start from default ServerConfig parameters
//		acceptor= new NioSocketAcceptor(ServerConfig.getInstance().NIOTHREADCOUNT);//set the number of NIO threads
//		serverhandler=new ServerHandler();
//		acceptor.setHandler(serverhandler);
//		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, ServerConfig.getInstance().IDLETIME);//set idletime of session
//		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter((ProtocolCodecFactory) new ByteArrayCodecFactory()));//set codec
//		try{
//			acceptor.bind(new InetSocketAddress(ServerConfig.getInstance().SERVERPORT));//绑定端口
//		}catch(Exception e){
//			
//		}
//
//		Rqueue = new LinkedBlockingQueue<RawData>(ServerConfig.getInstance().RBUFFERSIZE);//initialize the receive buffer
//		Squeue = new LinkedBlockingQueue<String>(ServerConfig.getInstance().SBUFFERSIZE);//initialize the send buffer
//		initConsumer(ServerConfig.getInstance().PARSESTYLE);
//		//System.out.println("@@@@");
//	}
//	
//	public void start(ConfigPojo parameters){//start from input ServerConfig Parameters
//		acceptor= new NioSocketAcceptor(parameters.getNIOthreadcoun());
//		serverhandler=new ServerHandler();
//		acceptor.setHandler(serverhandler);
//		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, parameters.getIdletime());//set idletime of session
//		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter((ProtocolCodecFactory) new ByteArrayCodecFactory()));
//		Rqueue = new LinkedBlockingQueue<RawData>(parameters.getRbuffersize());//initialize the receive buffer
//		Squeue = new LinkedBlockingQueue<String>(parameters.getSbuffersize());//initialize the send buffer
//		initConsumer(parameters.getParsingStyle());
//	}
//	
//	public void destory(){
//		acceptor.dispose();
//		Rqueue=null;
//		Squeue=null;
//		sessionmap=null;
//		cES=null;
//		getDateFromJNI.destroy();
//		if(datainteract!=null)
//			datainteract.DecodeExit();
//	}
//	
//	public void initConsumer(int parsingStyle){// "1" for Electric vehicle,"2" for diesel vehicles
//		int cthreadcount=ServerConfig.getInstance().CTHREADCOUNT;
//		if(1==parsingStyle)
//		{
//			cES= Executors.newFixedThreadPool(cthreadcount);
//			for (int i = 0; i < cthreadcount; i++) 
//			{
//				cES.submit(new ConsumerJNI(Rqueue));
//			}
//			getDateFromJNI=new Thread(new GetDataFromJNI());
//			getDateFromJNI.start();
//			datainteract=new DataInteract();
//			datainteract.DecodeInit(800);
//			datainteract.DecodeSetupBound(4096);
//		}
//		if(2==parsingStyle)
//		{
//			cES= Executors.newFixedThreadPool(cthreadcount);
//			for (int i = 0; i < cthreadcount; i++) 
//			{
//				cES.submit(new ConsumerJava(Rqueue));
//			}
//		}
//		
//	}
//
//	public static void main(String args[]){
//	}
}

