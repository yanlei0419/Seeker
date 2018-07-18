package org.seeker.common.util;

import org.apache.log4j.Logger;
import org.seeker.common.base.entity.OperationPo;
import org.seeker.common.base.entity.UserPo;
import org.seeker.common.util.config.SysConstant;
import org.seeker.common.util.print.PrintUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class BaseUtils extends PrintUtils {
	protected static Logger logger = Logger.getLogger(BaseUtils.class.getName());
	
	/**
	 * @param request
	 * @return
	 */
	public static  UserPo getCurUser(HttpServletRequest request){
		UserPo loginUser = (UserPo)request.getSession().getAttribute(SysConstant.sessionUserName);
		if(loginUser==null){
			return null;
		}
		return loginUser;
	}
	

	
	public static String changeStringUtf8(String str){
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			print("字符集转换错误");
			logger.error("字符集转换错误!!!![changeStringUtf8]");
		}
		return "";
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
			print(obj.getClass().getName(), "转换Map出问题");
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
				print(name,temp.toString());
			}
		}
	}
	
	/**
	 * 添加当前创建用户信息
	 */
	public static OperationPo setCreateUserAndTime(HttpServletRequest request ,OperationPo po){
		UserPo loginUser = (UserPo)request.getSession().getAttribute(SysConstant.sessionUserName);
		if(loginUser==null){
			logger.error("添加当前创建用户信息出问题!!!![setCreateUserAndTime]");
			return po;
		}
		po.setCreateBy(loginUser.getId());
		po.setCreateTime(DateUtil.getTime());
		po.setCreateDate(DateUtil.getDate());
		return po;
	}
	/**
	 *添加修改用户信息
	 */
	public static OperationPo setUpdateUserAndTime(HttpServletRequest request ,OperationPo po){
		UserPo loginUser = (UserPo)request.getSession().getAttribute(SysConstant.sessionUserName);
		if(loginUser==null){
			logger.error("添加当前创建用户信息出问题!!!![setUpdateUserAndTime]");
			return po;
		}
		po.setUpdateBy(loginUser.getId());
		po.setUpdateTime(DateUtil.getTime());
		po.setUpdateDate(DateUtil.getDate());
		return po;
	}
	/**
	 *添加修改用户信息
	 */
	public static OperationPo setModifyUserAndTime(HttpServletRequest request ,OperationPo po){
		UserPo loginUser = (UserPo)request.getSession().getAttribute(SysConstant.sessionUserName);
		if(loginUser==null){
			logger.error("添加修改用户信息出问题!!!![setModifyUserAndTime]");
			return po;
		}
		po.setModifyBy(loginUser.getId());
		po.setModifyTime(DateUtil.getTime());
		po.setModifyDate(DateUtil.getDate());
		return po;
	}
	public static void fomatMoney(){
		String[] chs={"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
		String[] danwei={"分","元","整"};
		String[] weishu={"拾","佰","仟","万","亿"};
	}

	

	
	
	
	
}
