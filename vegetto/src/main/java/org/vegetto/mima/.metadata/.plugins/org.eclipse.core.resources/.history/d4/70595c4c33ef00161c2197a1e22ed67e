package org.vegetto.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author Administrator
 * 
 */
public class XmlTools {
	private static Logger logger = LogManager.getLogger(XmlTools.class.getName());

	// private final static String file_path="org/ve/xml/doc/";

	/**
	 * 将请求地址解析成Document
	 * 
	 * @author yanlei
	 * @param url
	 *            请求的地址
	 * @return
	 * @throws MalformedURLException
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static Document getUrlReturn(String url) throws MalformedURLException, DocumentException, IOException {
		StringBuffer result = new StringBuffer();
		// 获取一个读取xml类
		SAXReader reader = new SAXReader();
		// 解析url地址的xml文件
		Document doc = reader.read(new URL(url).openStream());
		return doc;

	}

	/**
	 * @author yanlei 读取地址xml,并将文件内容解析称document实体
	 * @param xml
	 *            xml所在的地址
	 * @return
	 */
	public static Document getDocumentByString(String xml) throws Exception {
		Document doc = DocumentHelper.parseText(xml);
		return doc;

	}

	/**
	 * @author yanlei 获取document对象
	 * @param path
	 *            文件的名称
	 * @return
	 */
	public static Document getDocByXmlPath(String path) throws Exception {
		SAXReader reader = new SAXReader();
		String url = XmlTools.class.getClassLoader().getResource(path).toURI().getPath();
		// String
		// url=XmlTools.class.getClassLoader().getResource(file_path+path).toURI().getPath();
		// System.out.println(url);
		File file = null;
		// 将地址转换成file实体类
		file = new File(url);
		Document doc = null;
		try {
			doc = reader.read(file);
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
		return doc;
	}

	/**
	 * 创建element对象
	 * 
	 * @author yanlei
	 * @param e
	 *            根节点
	 * @param node
	 *            节点的名称
	 * @param value
	 *            节点内容
	 * @return
	 */
	public Element createElement(Element e, String node, String value, List<String> attr, List<String> attrName) {
		Element newElement = e.addElement(node);
		newElement.setText(value);
		if (attr.size() > 0 && attrName.size() > 0 && attr.size() == attrName.size()) {
			for (int i = 0; i < attr.size(); i++) {
				newElement.addAttribute(attr.get(i), attrName.get(i));
			}
		}
		return newElement;
	}

	/**
	 * 创建element对象
	 * 
	 * @author yanlei
	 * @param e
	 *            根节点
	 * @param node
	 *            节点的名称
	 * @param value
	 *            节点内容
	 * @return
	 */
	public Element createElement(Element e, String node, String value) {
		Element newElement = e.addElement(node);
		newElement.setText(value);
		return newElement;
	}

	/**
	 * 将element节点转换成实体类传出去
	 * 
	 * @author yanlei
	 * @param e
	 *            节点内容
	 * @param obj
	 *            将要转换的实体类
	 * @return
	 * @throws Exception
	 */
	public static Object xmlResolve(Element e, Object obj) throws Exception {
		// 实体类是否存在不存在 创建一个
		if (obj == null) {
			obj.getClass().newInstance();
		}
		// 获取类的全部属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName(); // 获取类属性的名称
			String value = e.element(name).getText();// 获取节点内容
			// 拼set方法的名称
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method m = obj.getClass().getMethod("set" + methodName, String.class);
			m.invoke(obj, value);
		}
		return obj;
	}

	/**
	 * 
	 * @param fields
	 *            累的属性
	 * @param obj
	 * @param e
	 * @return
	 */
	private static Object setObjValueByElement(Field[] fields, Object obj, Element e) {
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName(); // 获取类属性的名称
			String value = e.element(name).getText();// 获取节点内容
			// 拼set方法的名称
			String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method m = null;
			try {
				m = obj.getClass().getMethod("set" + methodName, String.class);
				m.invoke(obj, value);
			} catch (SecurityException e1) {
				BaseUtils.error("获取方法出错误", logger);
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				BaseUtils.error("获取方法出错误", logger);
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				BaseUtils.error("执行方法错误", logger);
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				BaseUtils.error("执行方法出错误", logger);
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				BaseUtils.error("执行方法出错误", logger);
				e1.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * 将element节点转换成实体类传出去
	 * 
	 * @author yanlei
	 * @param e
	 *            节点内容
	 * @param obj
	 *            将要转换的实体类
	 * @return
	 * @throws Exception
	 */
	public static Object xmlResolve(Element e, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		return xmlResolve(e, obj);
	}

	/**
	 * 将element节点转换成List传出去
	 * 
	 * @author yanlei
	 * @param e
	 *            节点内容
	 * @param obj
	 *            将要转换的实体类
	 * @return
	 * @throws Exception
	 */
	public static List xmlResolveList(Element e, Object obj) throws Exception {
		List list = new ArrayList();
		List<Element> eles = e.elements();
		Object ve = null;
		for (int i = 0; i < eles.size(); i++) {
			ve = xmlResolve(eles.get(i), obj);
			list.add(ve);
		}
		return list;
	}

	/**
	 * 将element节点转换成List传出去
	 * 
	 * @author yanlei
	 * @param e
	 *            节点内容
	 * @param clazz
	 *            类名称
	 * @return
	 * @throws Exception
	 */
	public static List xmlResolveList(Element e, Class clazz) throws Exception {
		Object obj = clazz.newInstance();
		return xmlResolveList(e, obj);
	}

	/**
	 * 将实体类转换成element节点
	 * 
	 * @author yanlei
	 * @param obj
	 *            传入的实体类
	 * @param parse
	 *            父节点
	 * @return
	 * @throws Exception
	 */
	public static Element createObjElement(Object obj, Element parse) throws Exception {
		if (parse == null) {
			return null;
		}
		String className = obj.getClass().getSimpleName();// 获取类的名称
		// String className=obj.getClass().getName();
		// String[] arrayStr=className.split("\\.");
		// 创建传入实体类名称的节点
		Element e = parse.addElement(className);
		// obj不存在创建一个
		if (obj == null) {
			obj.getClass().newInstance();
		}
		// 获取obj的全部类属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName(); // 获取类属性的名称
			Element sonEle = e.addElement(name);// 创建当前属性的节点
			// 拼取当前属性get方法
			String mothodName = name.substring(0, 1).toUpperCase() + name.substring(1);
			Method m = obj.getClass().getMethod("get" + mothodName);
			String value = (String) m.invoke(obj);
			sonEle.setText(value);
		}
		return parse;
	}

	/**
	 * doc2String 将xml文档内容转为String
	 * 
	 * @return 字符串
	 * @param document
	 */
	public static String doc2String(Document document) {
		String s = "";
		try {
			// 使用输出流来进行转化
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 使用GB2312编码
			OutputFormat format = new OutputFormat("  ", true, "GB2312");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	/**
	 * doc2XmlFile 将Document对象保存为一个xml文件到本地
	 * 
	 * @return true:保存成功 flase:失败
	 * @param filename
	 *            保存的文件名
	 * @param document
	 *            需要保存的document对象
	 */
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/* 将document中的内容写入文件中 */
			// 默认为UTF-8格式，指定为"GB2312"
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GB2312");
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * string2XmlFile 将xml格式的字符串保存为本地文件，如果字符串格式不符合xml规则，则返回失败
	 * 
	 * @return true:保存成功 flase:失败
	 * @param filename
	 *            保存的文件名
	 * @param str
	 *            需要保存的字符串
	 */
	public static boolean string2XmlFile(String str, String filename) {
		boolean flag = true;
		try {
			Document doc = DocumentHelper.parseText(str);
			flag = doc2XmlFile(doc, filename);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * load 载入一个xml文档
	 * 
	 * @return 成功返回Document对象，失败返回null
	 * @param uri
	 *            文件路径
	 */
	public static Document load(String filename)   {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	public static Document load(InputStream is) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}
	// Document document = DocumentHelper.createDocument(); // 寤虹珛document瀵硅薄
	// Element rootElement = document.addElement("ROOT"); // 寤虹珛鏍硅妭鐐�
	// rootElement.addComment("This is a xml file"); // 娣诲姞娉ㄩ噴
	// Element elements = rootElement.addElement("Elements"); // 鍦ㄦ牴鑺傜偣涓嬫坊鍔犳柊鑺傜偣
	// elements.addAttribute("flag", "true"); // 涓鸿妭鐐规坊鍔犲睘鎬у唴瀹�
	// Element element = elements.addElement("element"); // 娣诲姞瀛愯妭鐐�
	// element.setText("ElementContent"); // 涓哄瓙鑺傜偣娣诲姞鍐呭

}
