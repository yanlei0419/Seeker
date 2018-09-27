//package org.vegetto.common.excel.util;
//
//import java.beans.BeanInfo;
//import java.beans.IntrospectionException;
//import java.beans.Introspector;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.SQLXML;
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import Excel;
//import org.vegetto.mapper.util.UtilTools;
//
///**
// * 组装实体bean
// */
//@Deprecated
//public class BeanFactory {
//
//	/**
//	 * 基础的数据类型
//	 */
//	private static final Map<Class<?>, Object> primitiveDefaults = new HashMap();
//	static {
//		primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
//		primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
//		primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
//		primitiveDefaults.put(Float.TYPE, Float.valueOf(0.0F));
//		primitiveDefaults.put(Double.TYPE, Double.valueOf(0.0D));
//		primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
//		primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
//		primitiveDefaults.put(Character.TYPE, Character.valueOf('\000'));
//	}
//	/**
//	 * 查询结果对照bean 的结果
//	 */
//	private final Map<String, String> columnToPropertyOverrides;
//
//	public BeanFactory() {
//		this(new HashMap());
//	}
//
//	public  BeanFactory(Map<String, String> columnToPropertyOverrides) {
//		if (columnToPropertyOverrides == null) {
//			throw new IllegalArgumentException("columnToPropertyOverrides map cannot be null");
//		}
//		this.columnToPropertyOverrides = columnToPropertyOverrides;
//	}
//	
//	/**
//	 * 通过传入的类获取该属性的set方法和注解的名称
//	 * @param type
//	 * @return
//	 * @throws Exception
//	 */
//	public Map<String, Method> entityMethodToMap(Class type) throws Exception{
//		Map<String, Method> beanMap=new HashMap<String, Method>();
//		String fieldName="";
//		PropertyDescriptor[] props = propertyDescriptors(type);
//		for (int i = 0; i < props.length; i++) {
//			Method m=props[i].getWriteMethod();
//			fieldName=props[i].getName();//属性名称
//			if(!"".equals(fieldName)){
//				Excel excel=type.getDeclaredField(fieldName).getAnnotation(Excel.class);
//				beanMap.put(excel.exportName(), m);
//			}
//		}
//		return beanMap;
//	}
//
//	public <T> List<T> toListEntity(HSSFWorkbook book, Class<T> type) throws SQLException {
//		List results = new ArrayList();
//		HSSFSheet sheet = book.getSheetAt(0);
//		// 得到第一面的所有行
//		Iterator<Row> row = sheet.rowIterator();
//
//		// 得到第一行，也就是标题行
//		Row title = row.next();
//		if (title == null) {
//			return results;
//		}
//		// 得到第一行的所有列
//		Iterator<Cell> cellTitle = title.cellIterator();
//		// 将标题的文字内容放入到一个map中。
//		Map titlemap = new HashMap();
//		// 从标题第一列开始
//		int i = 0;
//		// 循环标题所有的列
//		while (cellTitle.hasNext()) {
//			Cell cell = cellTitle.next();
//			String value = cell.getStringCellValue();
//			titlemap.put(i, value);
//			i = i + 1;
//		}
//
//		PropertyDescriptor[] props = propertyDescriptors(type);
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int[] columnToProperty = mapColumnsToProperties(rsmd, props);
//		do {
//			results.add(createEntity(rs, type, props, columnToProperty));
//		} while (rs.next());
//
//		return results;
//	}
//
//	/**
//	 * 将数据库查询的结果转化成实体bean
//	 * 
//	 * @param rs
//	 *            查询出来的结果集
//	 * @param clazz
//	 *            实体bean
//	 * @return
//	 * @throws SQLException
//	 */
//	public <T> T toEntity(ResultSet rs, Class<T> type) throws SQLException {
//		// 获取bean的全部方法
//		PropertyDescriptor[] props = propertyDescriptors(type);
//
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int[] columnToProperty = mapColumnsToProperties(rsmd, props);
//
//		return createEntity(rs, type, props, columnToProperty);
//	}
//
//	/**
//	 * 将数据库查询的结果转化成实体bean
//	 * 
//	 * @param rs
//	 *            查询出来的结果集
//	 * @param clazz
//	 *            实体bean
//	 * @return
//	 * @throws SQLException
//	 */
//	public <T> T createEntity(ResultSet rs, Class<T> type, PropertyDescriptor[] props, int[] columnToProperty) throws SQLException {
//		T bean = newInstance(type);
//
//		for (int i = 1; i < columnToProperty.length; i++) {
//			if (columnToProperty[i] == -1) {
//				continue;
//			}
//			PropertyDescriptor prop = props[columnToProperty[i]];
//			Class propType = prop.getPropertyType();
//			Object value = processColumn(rs, i, propType);
//
//			if ((propType != null) && (value == null) && (propType.isPrimitive())) {
//				value = primitiveDefaults.get(propType);
//			}
//
//			callSetter(bean, prop, value);
//		}
//
//		return bean;
//	}
//
//	/**
//	 * 
//	 * @param target
//	 *            目标类
//	 * @param prop
//	 *            属性方法
//	 * @param value
//	 *            数据库的值
//	 * @throws SQLException
//	 */
//	private void callSetter(Object target, PropertyDescriptor prop, Object value) throws SQLException {
//		Method setter = prop.getWriteMethod();
//		if (setter == null) {
//			return;
//		}
//
//		Class[] params = setter.getParameterTypes();
//		try {
//			if ((value instanceof java.util.Date)) {
//				String targetType = params[0].getName();
//				if ("java.sql.Date".equals(targetType)) {
//					value = new java.sql.Date(((java.util.Date) value).getTime());
//				} else if ("java.sql.Time".equals(targetType)) {
//					value = new Time(((java.util.Date) value).getTime());
//				} else if ("java.sql.Timestamp".equals(targetType)) {
//					value = new Timestamp(((java.util.Date) value).getTime());
//				}
//
//			}
//
//			if (isCompatibleType(value, params[0]))
//				setter.invoke(target, new Object[] { value });
//			else {
//				throw new SQLException("Cannot set " + prop.getName() + ": incompatible types, cannot convert " + value.getClass().getName() + " to " + params[0].getName());
//			}
//
//		} catch (IllegalArgumentException e) {
//			throw new SQLException("Cannot set " + prop.getName() + ": " + e.getMessage());
//		} catch (IllegalAccessException e) {
//			throw new SQLException("Cannot set " + prop.getName() + ": " + e.getMessage());
//		} catch (InvocationTargetException e) {
//			throw new SQLException("Cannot set " + prop.getName() + ": " + e.getMessage());
//		}
//	}
//
//	/**
//	 * 实例化传入的类
//	 * 
//	 * @param <T>
//	 * @param c
//	 * @return
//	 * @throws SQLException
//	 */
//	protected <T> T newInstance(Class<T> c) throws SQLException {
//		try {
//			return c.newInstance();
//		} catch (InstantiationException e) {
//			throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
//		} catch (IllegalAccessException e) {
//			throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
//		}
//	}
//
//	/**
//	 * 获取查询结果和bean属性数量
//	 * 
//	 * @param rsmd
//	 * @param props
//	 * @return
//	 * @throws SQLException
//	 */
//	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd, PropertyDescriptor[] props) throws SQLException {
//		int cols = rsmd.getColumnCount();
//		int[] columnToProperty = new int[cols + 1];
//		Arrays.fill(columnToProperty, -1);
//
//		for (int col = 1; col <= cols; col++) {
//			String columnName = rsmd.getColumnLabel(col);
//			if ((null == columnName) || (0 == columnName.length())) {
//				columnName = rsmd.getColumnName(col);
//			}
//			String propertyName = (String) this.columnToPropertyOverrides.get(columnName);
//			if (propertyName == null) {
//				propertyName = columnName;
//			}
//			for (int i = 0; i < props.length; i++) {
//				if (propertyName.equalsIgnoreCase(props[i].getName())) {
//					columnToProperty[col] = i;
//					break;
//				}
//			}
//		}
//
//		return columnToProperty;
//	}
//
//	/**
//	 * @param c
//	 * @return
//	 * @throws SQLException
//	 */
//	private PropertyDescriptor[] propertyDescriptors(Class<?> c) throws SQLException {
//		BeanInfo beanInfo = null;
//		try {
//			beanInfo = Introspector.getBeanInfo(c);
//		} catch (IntrospectionException e) {
//			throw new SQLException("Bean introspection failed: " + e.getMessage());
//		}
//
//		return beanInfo.getPropertyDescriptors();
//	}
//
//	/**
//	 * 判断是什么类型的属性 8种数据类型
//	 * 
//	 * @param value
//	 *            数据库的值
//	 * @param type
//	 *            累的类别
//	 * @return
//	 */
//	public boolean isCompatibleType(Object value, Class<?> type) {
//		if ((value == null) || (type.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Integer.TYPE)) && (Integer.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Long.TYPE)) && (Long.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Double.TYPE)) && (Double.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Float.TYPE)) && (Float.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Short.TYPE)) && (Short.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Byte.TYPE)) && (Byte.class.isInstance(value))) {
//			return true;
//		}
//		if ((type.equals(Character.TYPE)) && (Character.class.isInstance(value))) {
//			return true;
//		}
//		return (type.equals(Boolean.TYPE)) && (Boolean.class.isInstance(value));
//	}
//
//	public Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
//		if ((!propType.isPrimitive()) && (rs.getObject(index) == null)) {
//			return null;
//		}
//
//		if (propType.equals(String.class)) {
//			return rs.getString(index);
//		}
//		if ((propType.equals(Integer.TYPE)) || (propType.equals(Integer.class))) {
//			return Integer.valueOf(rs.getInt(index));
//		}
//		if ((propType.equals(Boolean.TYPE)) || (propType.equals(Boolean.class))) {
//			return Boolean.valueOf(rs.getBoolean(index));
//		}
//		if ((propType.equals(Long.TYPE)) || (propType.equals(Long.class))) {
//			return Long.valueOf(rs.getLong(index));
//		}
//		if ((propType.equals(Double.TYPE)) || (propType.equals(Double.class))) {
//			return Double.valueOf(rs.getDouble(index));
//		}
//		if ((propType.equals(Float.TYPE)) || (propType.equals(Float.class))) {
//			return Float.valueOf(rs.getFloat(index));
//		}
//		if ((propType.equals(Short.TYPE)) || (propType.equals(Short.class))) {
//			return Short.valueOf(rs.getShort(index));
//		}
//		if ((propType.equals(Byte.TYPE)) || (propType.equals(Byte.class))) {
//			return Byte.valueOf(rs.getByte(index));
//		}
//		if (propType.equals(Timestamp.class)) {
//			return rs.getTimestamp(index);
//		}
//		if (propType.equals(SQLXML.class)) {
//			return rs.getSQLXML(index);
//		}
//
//		return rs.getObject(index);
//	}
//
// }
