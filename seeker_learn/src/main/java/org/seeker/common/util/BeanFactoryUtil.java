package org.seeker.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BeanFactoryUtil {

	
	
	/**
	 * 通过传入的类获取该属性的set方法和注解的名称
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Method> toBeanWriteMethodMap(Class type) throws Exception {
		Map<String, Method> beanMap = new HashMap<String, Method>();
		String fieldName = "";
		PropertyDescriptor[] props = propertyDescriptors(type);
		for (int i = 0; i < props.length; i++) {
			Method m = props[i].getWriteMethod();
			fieldName = props[i].getName();// 属性名称
			if (!"".equals(fieldName)) {
				beanMap.put(fieldName, m);
			}
		}
		return beanMap;
	}
	/**
	 * 通过传入的类获取该属性的set方法和注解的名称
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Method> toBeanWriteMethodMap(Object obj) throws Exception {
		Class type=obj.getClass();
		return toBeanWriteMethodMap(type);
	}

	/**
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	public static PropertyDescriptor[] propertyDescriptors(Class<?> c) throws IntrospectionException {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(c);
			return beanInfo.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断是什么类型的属性 8种数据类型
	 * 
	 * @param value
	 *            数据库的值
	 * @param type
	 *            累的类别
	 * @return
	 */
	private static boolean isCompatibleType(Object value, Class<?> type) {
		if ((value == null) || (type.isInstance(value))) {
			return true;
		}
		if ((type.equals(Integer.TYPE)) && (Integer.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Long.TYPE)) && (Long.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Double.TYPE)) && (Double.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Float.TYPE)) && (Float.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Short.TYPE)) && (Short.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Byte.TYPE)) && (Byte.class.isInstance(value))) {
			return true;
		}
		if ((type.equals(Character.TYPE)) && (Character.class.isInstance(value))) {
			return true;
		}
		return (type.equals(Boolean.TYPE)) && (Boolean.class.isInstance(value));
	}

	/**
	 * @param rs
	 * @param index
	 * @param propType
	 * @return
	 * @throws SQLException
	 */
	private Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
		if ((!propType.isPrimitive()) && (rs.getObject(index) == null)) {
			return null;
		}

		if (propType.equals(String.class)) {
			return rs.getString(index);
		}
		if ((propType.equals(Integer.TYPE)) || (propType.equals(Integer.class))) {
			return Integer.valueOf(rs.getInt(index));
		}
		if ((propType.equals(Boolean.TYPE)) || (propType.equals(Boolean.class))) {
			return Boolean.valueOf(rs.getBoolean(index));
		}
		if ((propType.equals(Long.TYPE)) || (propType.equals(Long.class))) {
			return Long.valueOf(rs.getLong(index));
		}
		if ((propType.equals(Double.TYPE)) || (propType.equals(Double.class))) {
			return Double.valueOf(rs.getDouble(index));
		}
		if ((propType.equals(Float.TYPE)) || (propType.equals(Float.class))) {
			return Float.valueOf(rs.getFloat(index));
		}
		if ((propType.equals(Short.TYPE)) || (propType.equals(Short.class))) {
			return Short.valueOf(rs.getShort(index));
		}
		if ((propType.equals(Byte.TYPE)) || (propType.equals(Byte.class))) {
			return Byte.valueOf(rs.getByte(index));
		}
		if (propType.equals(Timestamp.class)) {
			return rs.getTimestamp(index);
		}
		if (propType.equals(SQLXML.class)) {
			return rs.getSQLXML(index);
		}
		return rs.getObject(index);
	}

	/**
	 * 实例化传入的类
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 * @throws SQLException
	 */
	private static <T> T newInstance(Class<T> c) throws SQLException {
		try {
			return c.newInstance();
		} catch (InstantiationException e) {
			throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
		}
	}

	/**
	 * 
	 * @param target
	 *            目标类
	 * @param prop
	 *            属性方法
	 * @param value
	 *            数据库的值
	 * @throws SQLException
	 */
	public static void callSetter(Object target, Method setter, Object value) throws SQLException {
		if (setter == null) {
			return;
		}

		Class[] params = setter.getParameterTypes();
		try {
			if ((value instanceof java.util.Date)) {
				String targetType = params[0].getName();
				if ("java.sql.Date".equals(targetType)) {
					value = new java.sql.Date(((java.util.Date) value).getTime());
				} else if ("java.sql.Time".equals(targetType)) {
					value = new Time(((java.util.Date) value).getTime());
				} else if ("java.sql.Timestamp".equals(targetType)) {
					value = new Timestamp(((java.util.Date) value).getTime());
				}
			}
			if (isCompatibleType(value, params[0]))
				setter.invoke(target, new Object[] { value });
			else {
				throw new SQLException("Cannot set " + setter.getName() + ": incompatible types, cannot convert " + value.getClass().getName() + " to " + params[0].getName());
			}

		} catch (IllegalArgumentException e) {
			throw new SQLException("Cannot set " + setter.getName() + ": " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new SQLException("Cannot set " + setter.getName() + ": " + e.getMessage());
		} catch (InvocationTargetException e) {
			throw new SQLException("Cannot set " + setter.getName() + ": " + e.getMessage());
		}
	}

}
