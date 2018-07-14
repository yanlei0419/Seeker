//package org.vegetto.common.base.hibernate.db;
//
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistryBuilder;
//@Deprecated
//public class HibernateSessionFactory {
//
//	// 步骤1：创建一个静态的字符串常量用于存放Hibernate核心配置文件的路径
//	private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";
//	// 步骤2：创建一个Configure类对象，用于获取Hibernate核心配置文件中的属性信息
//	private static Configuration configuration = new Configuration();
//	// 步骤3：创建一个SessionFactory接口对象，用于获取SessionFactory连接工厂
//	private static SessionFactory sessionFactory = null;
//	// 步骤4：创建一个ThreadLocal本地线程池，用于动态装在session连接对象
//	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
//	
//	// 步骤5：创建一个空的构造方法
//	public HibernateSessionFactory() {
//		super();
//	}
//	
//	// 步骤6：创建一个静态块，用于获取连接工厂对象
//	static{
//		try{
//			// 步骤6-1：通过configuration对象的configure方法读取Hibernate框架的核心配置文件
//			configuration.configure(CONFIG_FILE_LOCATION);
//			// 步骤6-2：创建一个serviceRegistryBuilder对象用于读取配置文件中的属性
//			ServiceRegistryBuilder registryBuilder = new ServiceRegistryBuilder();
//			registryBuilder.applySettings(configuration.getProperties());
//			// 步骤6-3：根据以上获取的对象创建一个sessionFactory
//			sessionFactory = configuration.buildSessionFactory(registryBuilder.buildServiceRegistry());
//		} catch (Exception e){
//			e.printStackTrace();
//			System.err.println("%%% Error create SessionFactory %%%");
//		}		
//	}
//	
//	// 步骤7： 创建一个getSession方法，用于获取数据连接对象
//	public static Session getSession() throws HibernateException {
//		// 步骤7-1：从ThreadLocal本地线程池中获取一个Session连接对象
//		Session session = threadLocal.get();
//		// 步骤7-2：判断session是否有效
//		if( session == null || !session.isOpen() ){
//			// 重新从sessionFactory中获取一个session
//			session = sessionFactory.openSession();
//		}
//		// 步骤7-3：最终获取到的session对象再次放入到ThreadLocal本地线程池中进行保护
//		threadLocal.set(session);
//		return session;
//	}
//	
//	// 步骤8：创建一个方法closeSession，用于关闭数据库连接对象
//	public static void closeSession() throws HibernateException {
//		// 步骤8-1：从本地线程池中获取一个session对象
//		Session session = threadLocal.get();
//		// 步骤8-2：关闭session
//		if( session != null ){
//			session.close();
//		}
//		// 步骤8-3：将本地线程池置空 
//		threadLocal.set(null);
//	}
//
//	public static Configuration getConfiguration() {
//		return configuration;
//	}
//
//	public static void setConfiguration(Configuration configuration) {
//		HibernateSessionFactory.configuration = configuration;
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}	
//}
