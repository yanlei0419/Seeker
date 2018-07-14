package org.seeker.common.base.tools;
//package org.seeker.base;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.seeker.common.until.UtilTools;
//
//public class ResultToEnitiyByT<T> {
//	
//	
//	
//	// 声明一个类型对象用于接收传入的具体类型
//	private Class<T> persistentClass;
//	
//	static{
//		
//	}
//
//	 通过反射技术获取传入的具体的类
//	@SuppressWarnings("unchecked")
//	public ResultToEnitiyByT() {
//		super();
//		this.persistentClass = ((Class<T>) ((ParameterizedType) this.getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0]);
//	}
//
//	
//	
//	/**
//	 * 将数据库查询的结果转化成实体bean
//	 * @param rs  查询出来的结果集
//	 * @param clazz  实体bean
//	 * @return
//	 */
//	public  List<T> getObjectByResult(ResultSet rs){
//		List<T> list=new ArrayList<T>();
//		try {
//			while (rs.next()) {
//				//实例化一个实体
//				T obj=this.persistentClass.newInstance();
//				//获取该类的全部属性
//				Field[] fields =clazz.getDeclaredFields();
//					Map<String, String> map = new HashMap<String, String>();
//					ResultSetMetaData rsmd = rs.getMetaData();
//					int count = rsmd.getColumnCount();
//					for (int i = 1; i <= count; i++) {
//						String colName = rsmd.getColumnName(i).toLowerCase();
//						map.put(colName,
//								rs.getString(colName) == null ? "" : rs
//										.getString(colName));
//					}
//				}
//				for (int i = 0; i < fields.length; i++) {
//					String name = fields[i].getName(); // 获取节点的名字
//					String type =fields[i].getGenericType().toString();//获取字段的类别
//					//字符串类型
//					if("class java.lang.String".equals(type)){
//						String value=rs.getString(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					//数字整形
//					if("class java.lang.Byte".equals(type)){
//						byte value=rs.getByte(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					if("class java.lang.Integer".equals(type)){
//						int value=rs.getInt(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					if("class java.lang.Short".equals(type)){
//						short value=rs.getShort(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					
//					if("class java.lang.Long".equals(type)){
//						long value=rs.getLong(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					//浮点型
//					if("class java.lang.Float".equals(type)){
//						float value=rs.getFloat(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					
//					if("class java.lang.Double".equals(type)){
//						double value=rs.getDouble(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					//布尔型
//					if("class java.lang.Boolean".equals(type)){
//						boolean value=rs.getBoolean(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					//时间类型
//					if("class java.sql.Date".equals(type)){
//						Date value=rs.getDate(name);
//						name = name.substring(0, 1).toUpperCase() + name.substring(1); 
//						// 将属性的首字符大写，方便构造get，set方法
//						Method m =clazz.getMethod("set"+name,String.class);
//						m.invoke(obj, value);
//					}
//					//TODO 目前使用这些数据类型作为实体类的, 以后在用再添加
//				}
//				//将整理好的实体放入集合传出去
//				list.add(obj);
//			}
//			return list;
//		}catch (InstantiationException e) {
//			UtilTools.print("创建实体出问题");
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			UtilTools.print("创建实体出问题");
//			e.printStackTrace();
//		}catch (SQLException e) {
//			UtilTools.print("访问结果集字段出问题");
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			UtilTools.print("获取方法出问题");
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			UtilTools.print("获取方法出问题");
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			UtilTools.print("执行方法出问题");
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			UtilTools.print("执行方法出问题");
//			e.printStackTrace();
//		} 
//		return null;
//	}
//}
