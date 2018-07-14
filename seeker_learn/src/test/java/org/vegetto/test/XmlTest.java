package org.vegetto.test;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.seeker.common.util.XmlTools;
import org.vegetto.xml.OptionItem;
import org.vegetto.xml.Promotion;


public class XmlTest  {
	private static Logger logger = LogManager.getLogger(XmlTest.class.getName());

	public static void getPromotion() throws Exception{
		String path="promotions.xml";
		Promotion promotion=new Promotion();
		//读取xml文档
		Document doc=XmlTools.getDocByXmlPath(path);
		//获取第一层节点
		Element e=doc.getRootElement();
		String className=promotion.getClass().getName();
		
		String[] arrayStr=className.split("\\.");
		//查找第二层节点特定的值
		List<Element> promotions=e.elements(arrayStr[arrayStr.length-1]);
		
		for (int i = 0; i < promotions.size(); i++) {
			System.out.println("====================================");
			promotion=(Promotion) XmlTools.xmlResolve(promotions.get(i),promotion);
			System.out.println(promotion.getAllowBooking());
			System.out.println(promotion.getBeginTime());
			System.out.println(promotion.getDescription());
			System.out.println(promotion.getEffectiveTime());
			System.out.println(promotion.getEndTime());
			System.out.println(promotion.getPromotionId());
			System.out.println("====================================");
		}
	}
	public static void getOptionItem() throws Exception{
		//String path="D:\format.xml";
		String path="optionItems.xml";
		OptionItem optionItem=new OptionItem();
		//读取xml文档
		Document doc=XmlTools.getDocByXmlPath(path);
		//获取第一层节点
		Element e=doc.getRootElement();
		String className=optionItem.getClass().getName();
		
		String[] arrayStr=className.split("\\.");
		//查找第二层节点特定的值
		List<Element> optionItems=e.elements(arrayStr[arrayStr.length-1]);
		
		for (int i = 0; i < optionItems.size(); i++) {
			System.out.println("====================================");
			optionItem=(OptionItem) XmlTools.xmlResolve(optionItems.get(i),optionItem);
			System.out.println(optionItem.getValue());
			System.out.println(optionItem.getText());
			System.out.println("====================================");
		}
		
	}
	public static void main(String[] args) throws Exception {
//		String page=Promotion.class.getCanonicalName();
//		String str1=Promotion.class.getName();
//		String str2=Promotion.class.getSimpleName();
//		System.out.println(str1);
//		System.out.println(page);
//		System.out.println(str2);
		
		String path="org/vegetto/common/xml/doc/promotions.xml";

//		logger.info(path);
		//读取xml文档
		Document doc=XmlTools.getDocByXmlPath(path);
		//获取第一层节点
		Element e=doc.getRootElement();
//		System.out.println(e.asXML());
//		Element ele=e.element("promotions");错误根节点为promotions
		//e=e.element("Promotion");
		List<Promotion> ps=(List<Promotion>) XmlTools.xmlResolveList(e,Promotion.class);
//		System.out.println(e.element("promotions").getText());
		
		String xmlStr="<promotions></promotions>";
		
		Document doc1=DocumentHelper.createDocument();
		Element topE=doc1.addElement("promotions");
		String xml="";
		for (Promotion p : ps) {
			logger.info(p.toString());
//			System.out.println(p.toString());
			xml=XmlTools.createObjElement(p, topE).asXML() ;
			logger.info(xml);
		}
//		System.out.println(xml);
//		OptionItem option=new OptionItem();
//		option.setText("32");
//		option.setValue("12");
//		System.out.println(newE.asXML());
//		option.setText("45");
//		option.setValue("156");
//		newE=XmlTools.createObjElement(option, topE);
//		System.out.println(newE.asXML());
		//getOptionItem();
		//	getPromotion();
	}
	
}
