package org.vegetto.deal.alipay.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.seeker.common.util.XmlTools;
import org.vegetto.deal.alipay.config.AlipayConfig;
import org.vegetto.deal.alipay.util.AlipaySubmit;
import org.vegetto.deal.alipay.util.UtilTools;
import org.vegetto.deal.entity.AlipayEntity;
import org.vegetto.deal.util.WebConstants;

/* *
 *类名：AlipayService
 *功能：支付宝各接口构造类
 *详细：构造支付宝各接口请求参数
 *版本：3.2
 *修改日期：2011-03-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayService {
    
    /**
     * 支付宝提供给商户的服务接入网关URL(新)
     */
    private static final String ALIPAY_GATEWAY_NEW = WebConstants.getAppconfigValue("alipayURL");

    /**
     * 构造支付宝单笔交易查询接口
     * @param sParaTemp 请求参数集合
     * @return 表单提交HTML信息
     * @throws Exception 
     */
    public static String single_trade_query(Map<String, String> sParaTemp) {
    	//增加基本配置
        sParaTemp.put("service", "single_trade_query");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);      
        
        String xml="";;
		try {
			xml = AlipaySubmit.sendPostInfo(sParaTemp,ALIPAY_GATEWAY_NEW);
			 UtilTools.sys("xml", xml);
		} catch (Exception e) {
			UtilTools.sys("!!!", "支付宝请求数据失败");
			e.printStackTrace();
		}
       
        return xml;
    }
    /**
     * 获取支付宝查询订单之后的状态
     * @param sParaTemp
     * @return
     */
    public static AlipayEntity getSelAlipayResult(Map<String, String> sParaTemp) {
    	AlipayEntity entity=new AlipayEntity();
    	try {
			String xml =AlipayService.single_trade_query(sParaTemp);
			Document doc =XmlTools.getDocumentByString(xml);
			Element e=doc.getRootElement();
			Element ele=e.element("is_success");
			if("T".equals(ele.getText())){
				ele=e.element("response");
				ele=ele.element("trade");
				entity=(AlipayEntity) XmlTools.xmlResolve(ele, entity);
				entity.setIs_success("T");
			}else{
				ele=e.element("error");
				entity.setIs_success("F");
				entity.setError(ele.getTextTrim());
			}
			UtilTools.showObj(entity);
			return entity;
		} catch (Exception e) {
			UtilTools.sys("!!!", "数据转换失败");
			e.printStackTrace();
		}
		
    	return null;
    }
    /**
     * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数
     * 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
     * @return 时间戳字符串
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //构造访问query_timestamp接口的URL串
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // 截取部分不需要解析的信息
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // 判断是否有成功标示
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
}
