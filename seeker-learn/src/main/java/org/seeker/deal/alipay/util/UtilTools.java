package org.seeker.deal.alipay.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UtilTools {
	/**
	 * 用于显示
	 * @param name
	 * @param value
	 */
	public static void  sys(String name,String value){
//		System.out.println("===================================start======================================================");
//		System.out.println("== 	时间 : "+DateUtil.getTime());
//		System.out.println("== 	"+name +">>>>>>"+value);
//		System.out.println("====================================end======================================================");
	}
	
	/**
	 * 支付包参数组装
	 * @return 参数的Map集合
	 */
	public static Map<String, String> getThisMap(Object obj){
		Map<String, String> map =new HashMap<String, String>();
		try {
		//获取该类的全部属性
			Field[] fields =obj.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName(); // 获取节点的名字
				String objName = name.substring(0, 1).toUpperCase() + name.substring(1); 
				Method m = obj.getClass().getMethod("get"+objName);
				Object temp=m.invoke(obj);
				if(temp!=null&&!"".equals(temp.toString())){
					map.put(name, m.invoke(obj).toString());
				}
			}
		}catch (Exception e) {
			UtilTools.sys(obj.getClass().getName(), "转换Map出问题");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 显示实体类的属性
	 * @param obj
	 * @throws Exception
	 */
	public static void showObj(Object obj) throws Exception{
		//获取该类的全部属性
		Field[] fields =obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName(); // 获取节点的名字
			name = name.substring(0, 1).toUpperCase() + name.substring(1); 
			Method m = obj.getClass().getMethod("get"+name);
			Object temp=m.invoke(obj);
			if(temp!=null){
				sys(name,temp.toString());
			}
		}
	}
	
	public static String changeString(String str){
		if(str==null||"null".equals(str)){
			str="";
		}
		return str;
	}
}
