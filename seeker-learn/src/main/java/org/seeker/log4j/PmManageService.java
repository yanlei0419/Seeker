package org.seeker.log4j;
//
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.apache.log4j.MDC;
//import org.dom4j.DocumentException;
//
//import com.cbhb.opi.comm.constant.pm.CommandType;
//import com.cbhb.opi.comm.data.HmData;
//import com.cbhb.opi.pm.dao.sign.SignDao;
//import com.cbhb.opi.pm.log.LoggerFactory;
//import com.cbhb.opi.pm.service.channel.ChannelFlowService;
//import com.cbhb.opi.pm.service.mapper.RespcodeService;
//import com.cbhb.opi.pm.service.mapper.UnifiedService;
//import com.cbhb.opi.pm.service.order.PmBuyOrderManageService;
//import com.cbhb.opi.pm.service.order.PmInvoiceNoModifyService;
//import com.cbhb.opi.pm.service.order.PmInvoiceOrderService;
//import com.cbhb.opi.pm.service.order.PmReturnOrderService;
//import com.cbhb.opi.pm.service.order.PmSpecialOrderService;
//import com.cbhb.opi.pm.service.product.BatchSetProductService;
//import com.cbhb.opi.pm.service.product.PmProductInfoManagerService;
//import com.cbhb.opi.pm.service.product.PmSaleRateSetService;
//import com.cbhb.opi.pm.service.product.PmSpecialSaleRateSetService;
//import com.cbhb.opi.pm.service.product.ProductInfoDeleteService;
//import com.cbhb.opi.pm.service.product.ProductInfoSetService;
//import com.cbhb.opi.pm.service.query.AcctNoQueryService;
//import com.cbhb.opi.pm.service.query.CreateOrUpdateFileService;
//import com.cbhb.opi.pm.service.query.ErrorProductInfoQueryService;
//import com.cbhb.opi.pm.service.query.GoldExtractObjectManageServer;
//import com.cbhb.opi.pm.service.query.NoteQueryManageService;
//import com.cbhb.opi.pm.service.query.NoticeManagerService;
//import com.cbhb.opi.pm.service.query.PmBuyManagerService;
//import com.cbhb.opi.pm.service.query.PmDealWeightManagerService;
//import com.cbhb.opi.pm.service.query.PmInvoiceQueryService;
//import com.cbhb.opi.pm.service.query.PmOrderQueryService;
//import com.cbhb.opi.pm.service.query.PmTakeGoldQueryService;
//import com.cbhb.opi.pm.service.query.PrivilegeValueManageService;
//import com.cbhb.opi.pm.service.query.ProductAlarmInfoManageService;
//import com.cbhb.opi.pm.service.query.ProductPrivilegePriceManageService;
//import com.cbhb.opi.pm.service.query.SpecialOrderDetailQueryService;
//import com.cbhb.opi.pm.service.query.SpecialOrderQueryService;
//import com.cbhb.opi.pm.service.query.StockManagerService;
//import com.cbhb.opi.pm.service.query.StoragePriceQueryService;
//import com.cbhb.opi.pm.service.query.SzGoldProductInfoService;
//import com.cbhb.opi.pm.service.setting.AcctNoSettingService;
//import com.cbhb.opi.pm.service.setting.DealWeightSetService;
//import com.cbhb.opi.pm.service.setting.GoldExtractDeleteService;
//import com.cbhb.opi.pm.service.setting.GoldExtractSetService;
//import com.cbhb.opi.pm.service.setting.OpenClosedTimeService;
//import com.cbhb.opi.pm.service.setting.PmStoragePriceService;
//import com.cbhb.opi.pm.service.setting.PrivilegeSettingService;
//import com.cbhb.opi.pm.service.setting.SpecialOrderPriceSetService;
//import com.cbhb.opi.pm.service.settlement.DayEndResultService;
//import com.cbhb.opi.pm.service.settlement.PmBuyErrorOrderService;
//import com.cbhb.opi.pm.service.settlement.PmErrorBuyTodayService;
//import com.cbhb.opi.pm.service.settlement.ReCheckBalanceService;
//import com.cbhb.opi.pm.service.settlement.ReSendListFileService;
//import com.cbhb.opi.pm.service.settlement.SettlementService;
//import com.cbhb.opi.pm.service.sign.SignService;
//import com.cbhb.opi.pm.service.specialSale.SpecialSaleService;
//import com.cbhb.opi.pm.service.stock.ErrorBuyOrderService;
//import com.cbhb.opi.pm.vo.sign.SignVo;
//import com.cbhb.opi.pmtq.util.StringFormat;
//import com.cbhb.opiWeb.mapper.PropertiesUtils;
//import com.cbhb.opiWeb.mapper.ValidateUtils;
//import com.cbhb.opiWeb.mapper.XmlUtils;
///**
// * @author msqi
// */
//public class PmManageService {
//	private static Logger logger = Logger.getLogger(PmManageService.class);
//	/*****************************************************************************
//	 Prototype    	: 路由管理
//	 Description  	: 根据接收报文中的命令执行不同的操作 (报文格式：报文长度+报文体)
//	 Input        	: String message 报文信息
//	 Output       	: String 发送给客户端的报文
//	 Return Value 	: 
//	 Calls        	: 
//	 Called By    	: 
//	 
//	 History        :说明历史
//	 1.Date         : 2015/2/5
//	   Author       : msqi
//	   Modification : Created function
//	*****************************************************************************/
//	@SuppressWarnings("unchecked")
//	public String manageRoute(String message){
//		RespcodeService respcodeService = new RespcodeService(); // 响应码业务类
//		long start = System.currentTimeMillis();
//		String msgHeader=""; // 请求报文消息头
//		String msgBody=""; // 请求报文消息体
//		HmData requestHeadHd = null; // 请求头
//		HmData requestBodyHd = null; // 请求体
//		HmData responseHeadHd = null; // 响应头
//		HmData responseBodyHd = null; // 响应体，调用Service方法后的返回结果
//		String rspTrxref = ""; // 响应流水号
//		Logger businessLogger = null; // 业务日志类
//		String resxml=""; // 返回报文
//		try {
//			logger.info("请求报文：" + message);
//			
//			//前端调用存款接口报文
//			if(message!=null && message.length()>0 && message.indexOf("<")==66){
//				
//				String tempMessage = message.substring(8);
//				//Brc
//				int idx = tempMessage.indexOf("<Brc>");
//				int length = tempMessage.substring(idx).indexOf("</>");
//				String orgId = tempMessage.substring(idx+5,idx+length);
//				//operateNo
//				String operateNo = tempMessage.substring(8,14);
//				Logger logger=LoggerFactory.newBusinessLoggerInstance(orgId, "PMB002", operateNo);
//				MDC.put("className", PmManageService.class);
//				logger.info("====================================start==========================================");
//				logger.info("经判断，此报文为直接与存款系统通信报文");
//				logger.info("请求报文：" + message);
//				
//				UnifiedService unifiedService = new UnifiedService();
//				resxml = unifiedService.manage(message);
//				
//				long end = System.currentTimeMillis();
//				long resTime = end - start;
//				if(logger != null){
//					MDC.put("className",PmManageService.class);
//					logger.info("共处理" + resTime + "ms,响应报文：" + resxml);
//					logger.info("====================================end============================================");
//				}
//				MDC.remove("operateNo");
//				MDC.remove("className");
//				
//				return resxml;  
//				
//			}
//			
//			msgHeader = StringFormat.splitStr(message).get(0).toString();
//			msgBody = StringFormat.splitStr(message).get(1).toString();
//			logger.info("解析后消息头为:" + msgHeader);
//			logger.info("解析后消息体为:" + msgBody);
//			requestHeadHd = XmlUtils.xml2Map(msgBody, "Head");
//			
//			logger.info("解析后请求头为:" + requestHeadHd);
//			
//			// 请求头校验
//			logger.info("开始请求头校验");
//			Map validateRequestHeadResult = ValidateUtils.validateRequestHead(requestHeadHd);
//			if(((Boolean)validateRequestHeadResult.get("validateResult")).booleanValue()){
//				// 请求头校验通过后
//				logger.info("请求头校验通过");
//				// 渠道流水表查询
//				logger.info("开始判重：渠道【" + requestHeadHd.get("channel").toString() + "】、流水号【" + requestHeadHd.get("reqTrxref").toString() + "】");
//				ChannelFlowService channelFlowService = new ChannelFlowService();
//				String rspTrxrefResult = channelFlowService.findChannelFlow(requestHeadHd.get("channel").toString(), requestHeadHd.get("reqTrxref").toString());
//				if(rspTrxrefResult != null) {
//					// 获得原响应流水号
//					rspTrxref = rspTrxrefResult;
//					requestHeadHd.put("rspTrxref", rspTrxref);
//				} else {
//					// 生成响应流水号
//					rspTrxref = channelFlowService.getNewRspTrxref();
//					requestHeadHd.put("rspTrxref", rspTrxref);
//					// 向数据库插入一条数据，存入渠道号、请求流水、响应流水、交易状态（0：未处理）
//					channelFlowService.saveNewChannelFlow(requestHeadHd.get("channel").toString(), requestHeadHd.get("reqTrxref").toString(), requestHeadHd.get("rspTrxref").toString(), requestHeadHd.get("operateNo").toString());
//				}
////				rspTrxrefResult=null;
//				// 无原请求，或原请求未处理成功
//				if(rspTrxrefResult == null){
//					logger.info("判重结束：无原请求，或原请求未处理成功");
//					requestBodyHd = XmlUtils.xml2Map(msgBody, "Body");
//					logger.info("解析后请求体为:" + requestBodyHd);
//					
//					String command=requestHeadHd.get("operateNo").toString(); // 获得接口号
//					businessLogger = LoggerFactory.newBusinessLoggerInstance(requestHeadHd.get("orgId"), requestHeadHd.get("tellerNo"), command); // 业务日志
//					//校验该接口是否需要账号签约
//					boolean ckagree=checkAgree(command, requestBodyHd, businessLogger);
//					if(ckagree){
//						MDC.put("className", PmManageService.class);
//						businessLogger.info("====================================start==========================================");
//						businessLogger.info("请求报文：" + message);
//						if(CommandType.PRODUCT_LIST_SEARCH.equals(command)){ // 产品列表查询
//							
//							logger.info("下发请求报文到【产品列表查询】业务处理类");
////							产品信息查询 chen
//							PmProductInfoManagerService manageService=new PmProductInfoManagerService();
//							responseBodyHd=manageService.findProjectInfo(requestBodyHd,requestHeadHd);
//						
//						}else if(CommandType.PRODUCT_INFO_SEARCH.equals(command)){ // 产品明细查询
//
//							logger.info("下发请求报文到【产品明细查询】业务处理类");
//							//						根据产品代码 查询产品详细信息chen
//							PmProductInfoManagerService manageService=new PmProductInfoManagerService();
//							requestBodyHd.putAll(requestHeadHd);
//							responseBodyHd=manageService.findProjectInfoByProductNo(requestBodyHd);
//							
//						}else if(CommandType.PRODUCT_INFO_DXB_SEARCH.equals(command)){ // 产品列表查询(根据产品代码、币种、销售方式)
//
//							logger.info("下发请求报文到【产品列表查询】根据产品代码、币种、销售方式业务处理类");
//							//						根据产品代码 查询产品详细信息chen
//							PmProductInfoManagerService manageService=new PmProductInfoManagerService();
//							requestBodyHd.putAll(requestHeadHd);
//							responseBodyHd=manageService.findProjectInfoByDXB(requestBodyHd,requestHeadHd);
//							
//						}else if(CommandType.STOCK_EXPRESSNO_LIST.equals(command)){ // 库存列表
//
//							logger.info("下发请求报文到【库存列表查询】");
//							//						库存列表chen
//							StockManagerService stockService = new StockManagerService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = stockService.findExpressNoList(requestBodyHd,requestHeadHd);
//							
//						}else if(CommandType.ZH_PRODUCT_LIST.equals(command)){ // 产品列表(账户贵金属)查询
//							
//							logger.info("下发请求报文到【产品列表(账户贵金属)查询】业务处理类");
////							根据产品代码 查询产品详细信息chen
//							PmProductInfoManagerService manageService=new PmProductInfoManagerService();
//							responseBodyHd=manageService.findProjectInfoByCondiction(requestBodyHd,requestHeadHd);
//							
//						}else if(CommandType.REGISTER_LIST_SEARCH.equals(command)){ // 选购单列表查询
//							logger.info("下发请求报文到【选购单列表查询】业务处理类");
////							选购单列表查询 chen
//							PmBuyManagerService manageService=new PmBuyManagerService();
//							responseBodyHd=manageService.findPmBuy(requestHeadHd,requestBodyHd);
//							
//					    }else if(CommandType.REGISTER_INFO_SEARCH.equals(command)){ // 选购单明细查询
//					    	
//					    	logger.info("下发请求报文到【选购单明细查询】业务处理类");
//					    	//选购单明细查询 chen
//					    	PmBuyManagerService manageService=new PmBuyManagerService();
//					    	responseBodyHd=manageService.findPmBuyDetail(requestHeadHd,requestBodyHd);
//					    	
//					    }else if(CommandType.INVOICE_SEARCH.equals(command)){ // 发票修改查询
//
//					    	logger.info("下发请求报文到【发票修改查询】业务处理类");
//				                	 //选购单明细查询 chen INVOICE_SEARCH
//				                  //	HmData bodyHd = XmlUtils.getMapForFrontXml2(msgBody);
//				                 	PmInvoiceQueryService manageService=new PmInvoiceQueryService();
//				                	responseBodyHd=manageService.findPmInvoice(requestBodyHd,requestHeadHd);
//				                	
//					    }else if(CommandType.STOCK_JY_SEARCH.equals(command)){ // 
//				        
//					    	logger.info("下发请求报文到【贵金属实物明细查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	String path=PropertiesUtils.getProperty("FILE_PATH");
//					    	requestBodyHd.put("FILEPATH", path);
//					    	responseBodyHd=manageService.findStockJY(requestBodyHd,requestHeadHd);
//					    
//					    }else if(CommandType.ORDER_LIST_SEARCH.equals(command)){ // 订单列表查询
//				        
//					    	logger.info("下发请求报文到【订单列表查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=manageService.findPmOrderByInStock(requestBodyHd,requestHeadHd);
//					    
//					    }else if(CommandType.RZ_STOCK_INFORM.equals(command)){ // 日终库存通知
//					    
//					    	logger.info("下发请求报文到【日终库存通知】业务处理类");
//					    	StockManagerService stockmanager=new StockManagerService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = stockmanager.rzStock(requestBodyHd);
//
//					    }else if(CommandType.RZ_STOCK_WAIT_SEND.equals(command)){ // 库存待发货
//					    	
//					    	logger.info("下发请求报文到【库存待发货】业务处理类");
//					    	//add by chen 20150612
//					    	StockManagerService stockmanager=new StockManagerService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = stockmanager.waitStock(requestBodyHd);
//
//					    }else if(CommandType.ORDER_INFO_SEARCH.equals(command)){ // 订单明细查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【订单明细查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=manageService.findPmOrderDetailByOrderNo(requestBodyHd);
//					    	
//					    }else if(CommandType.STOCK_ORDER_INFO_SEARCH.equals(command)){ // 库存订单明细查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【库存订单明细查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=manageService.findStockOrderDetail(requestBodyHd);
//					    	
//					    }else if(CommandType.FILEDOWLOAD.equals(command)){ // 生成/下载文件
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【生成/下载文件】业务处理类");
//					    	CreateOrUpdateFileService manageService=new CreateOrUpdateFileService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	String path=PropertiesUtils.getProperty("FILE_PATH");
//					    	requestBodyHd.put("FILEPATH", path);
//					    	responseBodyHd=manageService.createOrUpdate(requestBodyHd,requestHeadHd);
//					    	
//					    }else if(CommandType.REGISTER_RESULT.equals(command)){ // 选购及账务清单查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【选购及账务清单查询】业务处理类");
//					    	PmBuyManagerService manageService=new PmBuyManagerService();
//							responseBodyHd=manageService.findRegisterResult(requestHeadHd,requestBodyHd);
//					    	
//					    }else if(CommandType.REGISTER_RESULT_DETAIL.equals(command)){ // 选购及账务清单明细查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【选购及账务清单明细查询】业务处理类");
//					    	PmBuyManagerService manageService=new PmBuyManagerService();
//							responseBodyHd=manageService.findRegisterResultDetail(requestHeadHd,requestBodyHd);
//					    	
//					    }else if(CommandType.DIRECTORY_SEARCH.equals(command)){ // 登记薄查询
//					    	logger.info("下发请求报文到【登记薄查询】业务处理类");
//					    	NoteQueryManageService noteService = new NoteQueryManageService();
//					    	requestBodyHd.put("queryOrgId", (String)requestBodyHd.get("orgId"));
//					    	requestBodyHd.putAll(requestHeadHd);
//							//调用业务处理方法查询出结果集
//					    	responseBodyHd = noteService.queryNoteList(requestBodyHd);
//					    	
//					    }else if(CommandType.SPECIAL_ORDER_INFO_SEARCH.equals(command)){ // 异常自购/退款订单明细查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常自购/退款订单明细查询】业务处理类");
//					    	
//					    }else if(CommandType.STOCK_DIRECTORY_INFO_SEARCH.equals(command)){ // 库存贵金属交易明细登记薄查询
//
//					    	logger.info("下发请求报文到【库存贵金属交易明细登记薄查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//					    	String path=PropertiesUtils.getProperty("FILE_PATH");
//					    	requestBodyHd.put("FILEPATH", path);
//					    	responseBodyHd=manageService.findPmAccountByCondiction(requestBodyHd,requestHeadHd);
//					    
//					    }else if(CommandType.ORDER_STATUS_SEARCH.equals(command)){ // 贵金属订单状态查询
//					    
//					    	logger.info("下发请求报文到【贵金属订单状态查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//				        	responseBodyHd=manageService.findPmOrderByCondiction(requestBodyHd,requestHeadHd);
//
//					    }else if(CommandType.ORDER_STATUS_DETAIL_SEARCH.equals(command)){ // 贵金属订单状态详细查询
//					    
//					    	logger.info("下发请求报文到【贵金属订单状态详细查询】业务处理类");
//					    	PmOrderQueryService manageService=new PmOrderQueryService();
//				        	responseBodyHd=manageService.findPmOrderDetailByOrderNo(requestBodyHd,requestHeadHd);
//
//					    }else if(CommandType.ORDER_HISTORY_STATUS_SEARCH.equals(command)){ // 贵金属订单历史状态查询
//				        
//					    	logger.info("下发请求报文到【贵金属订单历史状态查询】业务处理类");
//				        	PmOrderQueryService manageService=new PmOrderQueryService();
//				        	String path=PropertiesUtils.getProperty("FILE_PATH");
//					    	requestBodyHd.put("FILEPATH", path);
//					        responseBodyHd=manageService.findPmStockHisByCondiction(requestBodyHd,requestHeadHd);
//					    
//					    }else if(CommandType.ACCOUNT_SEARCH.equals(command)){ // 账务查询
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【账务查询】业务处理类");
//
//					    }else if(CommandType.SETTLEMENT_SEARCH.equals(command)){ // 清算信息查询
//
//					    	logger.info("下发请求报文到【清算信息查询】业务处理类");
//					    	SettlementService settlementService = new SettlementService();
//					    	responseBodyHd = settlementService.settlementInfo(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    }else if(CommandType.SHENZHEN_PRODUCT_INFO_SEARCH.equals(command)){ // 深圳黄金产品明细查询
//					    	
//					    	logger.info("下发请求报文到【深圳黄金产品明细查询】业务处理类");
//					    	SzGoldProductInfoService szGoldProductInfo = new SzGoldProductInfoService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = szGoldProductInfo.querySzGoldInfo(requestBodyHd);
//					    
//					    }else if(CommandType.BUY.equals(command)){ // 选购
//					    	
//					    	logger.info("下发请求报文到【选购】业务处理类");
//					    	PmBuyOrderManageService buyService = new PmBuyOrderManageService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = buyService.savePmBuyOrder(requestBodyHd,1);
//					    
//					    }else if(CommandType.BUY_PAY.equals(command)){ // 选购支付
//					    	
//					    	logger.info("下发请求报文到【选购支付】业务处理类");
//					    	PmBuyOrderManageService buyService = new PmBuyOrderManageService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = buyService.savePmBuyOrder(requestBodyHd,2);
//					    
//					    }else if(CommandType.REVOKE.equals(command)){ // 订单撤销/选购撤销
//					    	
//					    	logger.info("下发请求报文到【订单撤销/选购撤销】业务处理类");
//					    	PmBuyOrderManageService buyService = new PmBuyOrderManageService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = buyService.savePmBuyOrder(requestBodyHd,3);
//					    
//					    }else if(CommandType.GOLD.equals(command)){ // 提金实物
//					    	
//					    	logger.info("下发请求报文到【提金实物】业务处理类");
//					    	PmBuyOrderManageService buyService = new PmBuyOrderManageService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = buyService.savePmBuyOrder(requestBodyHd,4);
//					    
//					    }else if(CommandType.SPECIAL_ORDER_BUY_REGISTRATION.equals(command)){ // 异常自购登记
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常自购登记】业务处理类");
//					    
//					    	PmSpecialOrderService specialService = new PmSpecialOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = specialService.orderSpecialHandle(requestBodyHd,1);
//					    	
//					    }else if(CommandType.SPECIAL_ORDER_BUY_APPLY.equals(command)){ // 异常自购申请
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常自购申请】业务处理类");
//					    
//					    	PmSpecialOrderService specialService = new PmSpecialOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = specialService.orderSpecialHandle(requestBodyHd,2);
//					    	
//					    }else if(CommandType.SPECIAL_ORDER_REFUND_PAY.equals(command)){ // 异常自购支付
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常自购支付】业务处理类");
//					    	
//					    	PmSpecialOrderService buyService = new PmSpecialOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = buyService.orderSpecialHandle(requestBodyHd,3);
//					    
//					    }else if(CommandType.SPECIAL_ORDER_REFUND_REGISTRATION.equals(command)){ // 异常退款登记
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常退款登记】业务处理类");
//					    
//					    }else if(CommandType.SPECIAL_ORDER_REFUND_APPLY.equals(command)){ // 异常退款申请
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常退款申请】业务处理类");
//					    
//					    }else if(CommandType.SPECIAL_ORDER_BUY_PAY.equals(command)){ // 异常退款支付
//							// TODO 调用业务Service类的相关方法， 返回结果放入responseBodyHd
//					    	logger.info("下发请求报文到【异常退款支付】业务处理类");
//					    
//					    }else if(CommandType.INVOICE_MODIFY.equals(command)){ // 发票修改
//					    	
//					    	logger.info("下发请求报文到【发票修改】业务处理类");
//					    	PmInvoiceOrderService pmInvoiceService = new PmInvoiceOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = pmInvoiceService.savePmInvoiceOrder(requestBodyHd);
//					    
//					    }else if(CommandType.STOCK_IN_OUT.equals(command)){ // 出入库
//					    	
//					    	logger.info("下发请求报文到【出入库】业务处理类");
//					    	StockManagerService stockService = new StockManagerService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = stockService.inOrOutStock(requestBodyHd);
//					    
//					    }else if(CommandType.FACTORY_INFORM.equals(command)){ // 厂家通知
//					    	
//					    	logger.info("下发请求报文到【厂家通知】业务处理类");
//					    	NoticeManagerService noticeService=new NoticeManagerService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = noticeService.notices(requestBodyHd);
//
//					    }else if(CommandType.PRODUCT_INFO_SETTING.equals(command)){ // 产品信息设置
//					    	
//					    	logger.info("下发请求报文到【产品信息设置】业务处理类");
//					    	ProductInfoSetService productInfoSet = new ProductInfoSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = productInfoSet.setProductInfo(requestBodyHd);
//					    	
//					    }else if(CommandType.PRODUCT_IGNORE_WARNING.equals(command)){ // 产品忽略预警
//					    	
//					    	logger.info("下发请求报文到【产品忽略预警】业务处理类");
//					    	ProductAlarmInfoManageService productAlarmInfo = new ProductAlarmInfoManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = productAlarmInfo.productAlarmInfo(requestBodyHd);
//
//					    }else if(CommandType.PRODUCT_DELETE.equals(command)){ // 产品删除
//					    	
//					    	logger.info("下发请求报文到【产品删除】业务处理类");
//					    	ProductInfoDeleteService productInfodelete = new ProductInfoDeleteService ();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = productInfodelete.deleteProductInfo(requestBodyHd);
//					    	
//					    }else if(CommandType.DEAL_WEIGHT_SETTING.equals(command)){ // 交易重量设置
//					    	
//					    	logger.info("下发请求报文到【交易重量设置】业务处理类");
//					    	DealWeightSetService dealWeightSetService = new DealWeightSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = dealWeightSetService.setDealWeight(requestBodyHd);
//					    	
//					    }else if(CommandType.DEAL_WEIGHT_SEARCH.equals(command)){ // 交易重量查询
//					    	
//					    	logger.info("下发请求报文到【交易重量查询】业务处理类");
//					    	PmDealWeightManagerService dealWeightService = new PmDealWeightManagerService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = dealWeightService.queryPmDealWeight(requestBodyHd);
//					    
//					    }else if(CommandType.DEAL_PRIVILEGE_SETTING.equals(command)){ // 优惠交易点差设置
//					    	
//					    	logger.info("下发请求报文到【优惠交易点差设置】业务处理类");
//					    	PrivilegeSettingService privilegeSettingService = new PrivilegeSettingService();
//					    	requestBodyHd.put("settingOrgId", (String)requestBodyHd.get("orgId"));
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = privilegeSettingService.saveSetting(requestBodyHd);
//					    
//					    }else if(CommandType.DEAL_PRIVILEGE_SEARCH.equals(command)){ // 优惠交易点差查询
//
//					    	logger.info("下发请求报文到【优惠交易点差查询】业务处理类");
//					    	PrivilegeValueManageService privilegeValue = new PrivilegeValueManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					        responseBodyHd = privilegeValue.queryPrivilegeValue(requestBodyHd);
//
//					    }else if(CommandType.GOLD_EX_OBJ_SETTING.equals(command)){ // 提金实物标的设置
//
//					    	logger.info("下发请求报文到【提金实物标的设置】业务处理类");
//					    	GoldExtractSetService goldExtractSet = new GoldExtractSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = goldExtractSet.setGoldExtract(requestBodyHd);
//					    
//					    }else if(CommandType.GOLD_EX_OBJ_SEARCH.equals(command)){ // 提金实物标的查询
//
//					    	logger.info("下发请求报文到【提金实物标的查询】业务处理类");
//					    	GoldExtractObjectManageServer goldExtract = new GoldExtractObjectManageServer();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = goldExtract.querygoldExtractObject(requestBodyHd);
//					    
//					    }else if(CommandType.PRO_PRICE_PRIVILEGE_SETTING.equals(command)){ // 产品销售优惠价格率设置
//
//					    	logger.info("下发请求报文到【产品销售优惠价格率设置】业务处理类");
//					    	PmSaleRateSetService pmSaleRateSetService = new PmSaleRateSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = pmSaleRateSetService.savePmSaleRate(requestBodyHd);
//					    	
//					    }else if(CommandType.SPECIAL_PRO_PRICE_PRIVILEGE_SETTING.equals(command)){ // 特殊产品销售优惠价格率设置
//					    	
//					    	logger.info("下发请求报文到【特殊产品销售优惠价格率设置】业务处理类");
//					    	PmSpecialSaleRateSetService pmSpecialSaleRateSetService = new PmSpecialSaleRateSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = pmSpecialSaleRateSetService.saveSpecialPmSaleRate(requestBodyHd);
//					    
//					    }else if(CommandType.PRO_PRICE_PRIVILEGE_SEARCH.equals(command)){ // 产品销售优惠价格率查询
//					    	
//					    	logger.info("下发请求报文到【产品销售优惠价格率查询】业务处理类");
//					    	ProductPrivilegePriceManageService productPrivilegePrice = new ProductPrivilegePriceManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					        responseBodyHd = productPrivilegePrice.queryProductPrivilegePrice(requestBodyHd);
//					    	
//					    }else if(CommandType.STORAGE_PRICE_SETTING.equals(command)){ // 仓储免费期限与超期仓储费设置
//					    	
//					    	logger.info("下发请求报文到【仓储免费期限与超期仓储费设置】业务处理类");
//					    	PmStoragePriceService service=new PmStoragePriceService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.setPmStoragePrice(requestBodyHd,businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.SPECIAL_STORAGE_PRICE_SETTING.equals(command)){ // 特殊产品仓储免费期限与超期仓储费设置
//					    	
//					    	logger.info("下发请求报文到【特殊产品仓储免费期限与超期仓储费设置】业务处理类");
//					    	PmStoragePriceService service=new PmStoragePriceService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.setTSPmStoragePrice(requestBodyHd,businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.PM_STORAGE_PRICE_EVENT_S.equals(command)){ // 仓储免费期限与超期仓储费历史查询
//					    	
//					    	logger.info("下发请求报文到【仓储免费期限与超期仓储费查询】业务处理类");
//					    	StoragePriceQueryService storageQueryService = new StoragePriceQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = storageQueryService.queryStorageEventPriceInfo(requestBodyHd);
//					    
//					    }else if(CommandType.STORAGE_PRICE_SEARCH.equals(command)){ // 仓储免费期限与超期仓储费查询
//					    	
//					    	logger.info("下发请求报文到【仓储免费期限与超期仓储费查询】业务处理类");
//					    	StoragePriceQueryService storageQueryService = new StoragePriceQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = storageQueryService.queryStoragePriceInfo(requestBodyHd);
//					    
//					    }else if(CommandType.SPECIAL_PRICE_SETTING.equals(command)){ //特品价格设置
//					    	logger.info("下发请求报文到【特品价格设置】业务处理类");
//					    	SpecialOrderPriceSetService  specialOrderDetailSet=new SpecialOrderPriceSetService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialOrderDetailSet.setSpecialOrderPriceService(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.GOLD_PING_LIST.equals(command)){ // 提金平仓(账户贵金属)查询
//					    	
//					    	logger.info("下发请求报文到【提金平仓(账户贵金属)查询】业务处理类");
//					    	//选购单明细查询 chen
//					    	PmBuyManagerService manageService=new PmBuyManagerService();
//					    	responseBodyHd=manageService.findGoldPingPmbuy(requestBodyHd,requestHeadHd);
//					    	
//					    }else if(CommandType.DAY_END_JOB_RESULT_SEARCH.equals(command)){ // 日终结果查询
//					    	
//					    	logger.info("下发请求报文到【日终结果查询】业务处理类");
//					    	DayEndResultService dayEndResultService = new DayEndResultService();
//					    	responseBodyHd = dayEndResultService.searchDayEndResult(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.RECHECK_ACCOUNT.equals(command)){ // 重新对账
//					    
//					    	logger.info("下发请求报文到【重新对账】业务处理类");
//					    	ReCheckBalanceService reCheckBalanceService = new ReCheckBalanceService();
//					    	responseBodyHd = reCheckBalanceService.againAccount(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.RESEND_LIST.equals(command)){ // 手动发送清单
//					    	
//					    	logger.info("下发请求报文到【手动发送清单】业务处理类");
//					    	ReSendListFileService reSendListFileService = new ReSendListFileService();
//					    	responseBodyHd = reSendListFileService.sendListFile(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    }else if(CommandType.RESETTLEMENT.equals(command)){ // 手动资金清算
//
//					    	logger.info("下发请求报文到【手动资金清算】业务处理类");
//					    	SettlementService settlementService = new SettlementService();
//					    	responseBodyHd = settlementService.reSettlement(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    }else if(CommandType.EXCEPTION_HANDLE.equals(command)){ // 对账异常选购单处理
//					    	
//					    	logger.info("下发请求报文到【对账异常选购单处理】业务处理类");
//					    	PmBuyErrorOrderService service=new PmBuyErrorOrderService(businessLogger);
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.handlyBuy(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    }else if(CommandType.RETURN_GOODS.equals(command)){ // 退货处理
//					    	
//					    	logger.info("下发请求报文到【退货处理】业务处理类");
//					    	PmReturnOrderService returnService = new PmReturnOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = returnService.savePmReturnOrder(requestBodyHd);
//					    	
//					    }else if(CommandType.INVOIVE_MODIFY_NO.equals(command)){ // 更新发票编号
//					    	
//					    	logger.info("下发请求报文到【更新发票编号】业务处理类");
//					    	PmInvoiceNoModifyService modifyService = new PmInvoiceNoModifyService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//保存订单
//					    	responseBodyHd = modifyService.savePmInvoiceNo(requestBodyHd);
//					    	
//					    } else if(CommandType.ERROR_BUY_LIST.equals(command)){ // 对账不符选购单列表
//					    	
//					    	logger.info("下发请求报文到【对账不符选购单列表】业务处理类");
//					    	PmBuyErrorOrderService service=new PmBuyErrorOrderService(businessLogger);
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.buyList(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.ERROR_BUY_DETAIL.equals(command)){ // 对账不符选购单明细
//					    	
//					    	logger.info("下发请求报文到【对账不符选购单明细】业务处理类");
//					    	PmBuyErrorOrderService service=new PmBuyErrorOrderService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.buyDetail(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.TAKE_GOLD_SEARCH.equals(command)){ // 提金实物查询
//					    	
//					    	logger.info("下发请求报文到【提金实物查询】业务处理类");
//					    	PmTakeGoldQueryService service=new PmTakeGoldQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.queryTakeGoldInfo(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }  else if(CommandType.ACCT_NO_SETTING.equals(command)){ // 账号设置 added by liliang at 20150727
//					    	
//					    	logger.info("下发请求报文到【账号设置】业务处理类");
//					    	AcctNoSettingService service=new AcctNoSettingService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.setAcctNo(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }  else if(CommandType.ACCT_NO_LIST.equals(command)){ // 账号查询 added by liliang at 20150727
//					    	
//					    	logger.info("下发请求报文到【账号查询】业务处理类");
//					    	AcctNoQueryService service=new AcctNoQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.queryAcctNoInfo(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.SET_OPEN_CLOSED.equals(command)){ // 开闭市时间设置
//					    	logger.info("下发请求报文到【开闭市时间设置】业务处理类");
//					    	OpenClosedTimeService service=new OpenClosedTimeService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.setOpenClosedTime(requestBodyHd,businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.FIND_OPEN_CLOSED.equals(command)){ // 开闭市时间查询
//					    	logger.info("下发请求报文到【开闭市时间查询】业务处理类");
//					    	OpenClosedTimeService service=new OpenClosedTimeService();
////					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.findOpenClosedTime(requestHeadHd,businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.BATCH_SET_PRODUCT.equals(command)){ // 批量设置可售产品
//					    	logger.info("下发请求报文到【批量设置可售产品】业务处理类批量设置可售产品");
//					    	BatchSetProductService  batchSetProductService=new BatchSetProductService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = batchSetProductService.batchSetProcduct(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.FIND_RECVBRX.equals(command)){ // “收货机构”查询
//					    	logger.info("下发请求报文到【收货机构查询】业务处理类");
//					    	PmBuyOrderManageService service=new PmBuyOrderManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.findRecvbrx(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.GOLD_EXTRACT_DELETE.equals(command)){ // 删除提金实物标的
//					    	logger.info("下发请求报文到【删除提金实物标的】业务处理类");
//					    	GoldExtractDeleteService  goldExtractDeleteService=new GoldExtractDeleteService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd = goldExtractDeleteService.deleteGoldExtractService(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.QUERY_RECV_ORG_INFO.equals(command)){ //收货机构地址查询 
//					    	logger.info("下发请求报文到【收货机构地址查询】业务处理类");
//					    	PmBuyOrderManageService service=new PmBuyOrderManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.queryRecvOrgInfo(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.SEARCH_RECV_ORG_INFO.equals(command)){ // 提货网点机构信息查询
//					    	logger.info("下发请求报文到【提货网点机构信息查询】业务处理类");
//					    	PmBuyOrderManageService service=new PmBuyOrderManageService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.searchRecvOrgInfo(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.CAN_SALE_SEARCH.equals(command)){ // 可售查询
//					    	logger.info("下发请求报文到【可售查询】业务处理类");
//					    	OpenClosedTimeService service=new OpenClosedTimeService();
////					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.ifSaling(requestHeadHd,businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    } else if(CommandType.ERROR_BUY_LIST_TODAY.equals(command)){ // 当日异常选购单列表查询
//					    	logger.info("下发请求报文到【当日异常选购单列表查询】业务处理类");
//					    	PmErrorBuyTodayService pmErrorBuyTodayService = new PmErrorBuyTodayService();
//					    	responseBodyHd = pmErrorBuyTodayService.queryErrorBuyList(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    } else if(CommandType.ERROR_BUY_DETAIL_TODAY.equals(command)){ // 当日异常选购单明细查询
//					    	logger.info("下发请求报文到【当日异常选购单明细查询】业务处理类");
//					    	PmErrorBuyTodayService pmErrorBuyTodayService = new PmErrorBuyTodayService();
//					    	responseBodyHd = pmErrorBuyTodayService.queryErrorBuyDetail(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    } else if(CommandType.ERROR_BUY_HANDLE_TODAY.equals(command)){ // 当日异常选购单处理
//					    	logger.info("下发请求报文到【当日异常选购单处理】业务处理类");
//					    	PmErrorBuyTodayService pmErrorBuyTodayService = new PmErrorBuyTodayService();
//					    	responseBodyHd = pmErrorBuyTodayService.handleErrorBuy(requestHeadHd, requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    	
//					    }else if(CommandType.AGREE_UNAGREE.equals(command)){ //签约/解约
//					    	logger.info("下发请求报文到【签约/解约】业务处理类");
//					    	SignService signService=new SignService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=signService.sign(requestBodyHd, businessLogger);
//					    	responseBodyHd.remove("TRXREF");
//					    	responseBodyHd.remove("TRX_STATUS");
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.QUERY_SIGN_HIS.equals(command)){ //签约解约历史记录查询
//					    	logger.info("下发请求报文到【签约解约历史记录查询】业务处理类");
//					    	SignService signService=new SignService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=signService.queryAcctNoInfoHis(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.CK_AGREE.equals(command)){ //检查账号是否签约
//					    	logger.info("下发请求报文到【检查账号是否签约】业务处理类");
//					    	SignService signService=new SignService();
//					    	responseBodyHd=signService.checkSign(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);
//					    
//					    }else if(CommandType.SPECIAL_ORDER_STATUS_MOD.equals(command)){ // 异常存货订单状态修改
//					    	logger.info("下发请求报文到【异常存货订单状态修改】业务处理类");
//					    	PmReturnOrderService returnService = new PmReturnOrderService();
//					    	//将请求头报文与请求体报文合并
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	//异常存货订单状态修改
//					    	responseBodyHd = returnService.specialOrderStatusModify(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.QUERY_SPECIAL_ORDER.equals(command)){ //可进行特品销售/提金订单列表查询
//					    	logger.info("下发请求报文到【可进行特品销售/提金订单列表查询】业务处理类");
//					    	SpecialSaleService specialSaleService=new SpecialSaleService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialSaleService.querySecialSale(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);					    
//					    }else if(CommandType.QUERY_SPECIAL_ORDER_DETAIL.equals(command)){ //特品销售订单明细查询
//					    	logger.info("下发请求报文到【特品销售订单明细查询】业务处理类");
//					    	SpecialSaleService specialSaleService=new SpecialSaleService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialSaleService.querySpecialOrderDetail(requestBodyHd, businessLogger);
//					    	MDC.put("className", PmManageService.class);					    
//					    }else if(CommandType.SPECIAL_SALE.equals(command)){ // 特品销售
//					    	logger.info("下发请求报文到【特品销售】业务处理类");
//					    	SpecialSaleService specialSaleService=new SpecialSaleService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialSaleService.specialSaleOrTake(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);			
//					    	
//					    }else if(CommandType.SPECIAL_SALE_TAKE.equals(command)){ // 特品销售提金实物
//					    	logger.info("下发请求报文到【特品销售提金实物】业务处理类");
//					    	SpecialSaleService specialSaleService=new SpecialSaleService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialSaleService.specialSaleOrTake(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);		
//					    
//					    }else if(CommandType.ERROR_ORDER_LIST.equals(command)){ // 遗失实物金库存列表查询
//					    	
//					    	logger.info("下发请求报文到【遗失实物金库存列表查询】业务处理类");
//					    	ErrorBuyOrderService service=new ErrorBuyOrderService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.getListDate(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.ERROR_ORDER_DETAIL.equals(command)){ // 遗失实物金库存明细查询
//					    	
//					    	logger.info("下发请求报文到【遗失实物金库存明细查询】业务处理类");
//					    	ErrorBuyOrderService service=new ErrorBuyOrderService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.getEntityDate(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.ERROR_ORDER_HANDLER.equals(command)){ // 遗失实物金库存结算
//					    	
//					    	logger.info("下发请求报文到【仓储免费期限与超期仓储费设置】业务处理类");
//					    	ErrorBuyOrderService service=new ErrorBuyOrderService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.handleErrorBuyOrder(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.ERROR_PRODUCT_INFO_LIST.equals(command)){ // 异常产品列表查询
//					    	
//					    	logger.info("下发请求报文到【异常产品列表查询】业务处理类");
//					    	ErrorProductInfoQueryService  service=new ErrorProductInfoQueryService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.getListData(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.ERROR_PRODUCT_INFO_DETAIL.equals(command)){ // 异常产品明细查询
//					    	
//					    	logger.info("下发请求报文到【 异常产品明细查询】业务处理类");
//					    	ErrorProductInfoQueryService  service=new ErrorProductInfoQueryService(businessLogger);
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=service.getEntityData(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else if(CommandType.SPECIAL_ORDER_LIST_QUERY.equals(command)){ //设置特品价格列表查询
//					    	logger.info("下发请求报文到【设置特品价格列表查询】业务处理类");
//					    	SpecialOrderQueryService  specialOrderQueryService=new SpecialOrderQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialOrderQueryService.querySpecialOrderList(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);		
//					    }else if(CommandType.SPECIAL_ORDER_DETAIL_QUERY.equals(command)){ //设置特品价格明细查询
//					    	logger.info("下发请求报文到【设置特品价格明细查询】业务处理类");
//					    	SpecialOrderDetailQueryService  specialOrderDetailQueryService=new SpecialOrderDetailQueryService();
//					    	requestBodyHd.putAll(requestHeadHd);
//					    	responseBodyHd=specialOrderDetailQueryService.querySpecialOrderDetailList(requestBodyHd);
//					    	MDC.put("className", PmManageService.class);
//					    }else{ // 未找到对应的请求接口
//					    	logger.info("渠道【"+requestHeadHd.get("channel")+"】请求接口【" + command + "】无效");
//					    	responseBodyHd = new HmData();
//					    	responseBodyHd.put("RspCode","920007");
////					    	responseBodyHd.put("RspMsg","报文域号格式错");
//					    	responseBodyHd.put("RspMsg",respcodeService.getRespcodeVo("920007",null).getRespMsg());
//					    }
//						
//						logger.info("业务处理类处理请求完成，返回的响应体为：" + responseBodyHd);
//						// 判断请求是否处理成功
//						String newDealState = "";
//						if(responseBodyHd != null){
//							Object rspCode = responseBodyHd.get("RspCode");
//							if(rspCode != null && "000000".equals(rspCode.toString())){
//								newDealState = "1";
//							} else {
//								newDealState = "2";
//							}
//						}else{
//							newDealState = "2";
//						}
//						
//						// 更新渠道流水表对应数据的处理状态
//						channelFlowService.updateChannelFlow(requestHeadHd.get("channel").toString(), requestHeadHd.get("reqTrxref").toString(), requestHeadHd.get("rspTrxref").toString(), newDealState);
//					}else{
//						//账号签约校验未通过
//						logger.info("账号签约校验未通过");
//						responseBodyHd = new HmData();
//						responseBodyHd.put("RspCode","500998");
//						responseBodyHd.put("RspMsg","账号签约校验未通过");
//					}
//				}else{
//					// 原请求已处理成功
//					logger.info("判重结束：渠道【" + requestHeadHd.get("channel") + "】，流水号【" + requestHeadHd.get("reqTrxref") + "】，请求已处理过并且已处理成功，接口直接返回处理成功！");
//					responseBodyHd = new HmData();
//					responseBodyHd.put("RspCode","000000");
////					responseBodyHd.put("RspMsg","交易处理成功");
//					responseBodyHd.put("RspMsg",respcodeService.getRespcodeVo("000000",null).getRespMsg());
//				}
//			}else{
//				// 请求头检验失败后
//				logger.info("请求头校验失败：" + validateRequestHeadResult.get("validateMsg"));
//				responseBodyHd = new HmData();
//				responseBodyHd.put("RspCode", validateRequestHeadResult.get("validateCode"));
//				responseBodyHd.put("RspMsg", validateRequestHeadResult.get("validateMsg"));
//			}
//			
//		} catch(DocumentException e){
//			logger.error("报文非法，解析异常！", e);
//			responseBodyHd = new HmData();
//			responseBodyHd.put("RspCode","920001");
////			responseBodyHd.put("RspMsg","报文非法，无法解析");
//			try {
//				responseBodyHd.put("RspMsg",respcodeService.getRespcodeVo("920001",null).getRespMsg());
//			} catch (Exception e1) {
//				logger.error("获得响应码信息失败", e1);
//			}
//		}catch (Exception e) {
//			logger.error("服务器异常！", e);
//			responseBodyHd = new HmData();
//			responseBodyHd.put("RspCode","970008");
////			responseBodyHd.put("RspMsg","服务器端系统错误");
//			try {
//				responseBodyHd.put("RspMsg",respcodeService.getRespcodeVo("970008",null).getRespMsg() + e.getMessage());
//			} catch (Exception e1) {
//				logger.error("获得响应码信息失败", e1);
//			}
//		}
//		
//		// 组装响应头
//		responseHeadHd = XmlUtils.responseHeadHd(requestHeadHd);
//		resxml = XmlUtils.map2Xml(responseHeadHd, responseBodyHd);
//		
//		long end = System.currentTimeMillis();
//		long resTime = end - start;
//		logger.info("共处理" + resTime + "ms,响应报文：" + resxml);
//		if(businessLogger != null){
//			MDC.put("className",PmManageService.class);
//			businessLogger.info("共处理" + resTime + "ms,响应报文：" + resxml);
//			businessLogger.info("====================================end============================================");
//		}
//		MDC.remove("operateNo");
//		MDC.remove("className");
//		return resxml;  
//	}
//	
//	/*****************************************************************************
//	 Prototype    	: 校验账号签约
//	 Description  	: 根据传入的接口号校验账号是否签约
//	 Input        	: HmData requestBodyHd 请求体
//	 				  String command 接口号
//	 Output       	: boolean true:通过 false:不通过
//	 Return Value 	: 
//	 Calls        	: 
//	 Called By    	: 
//	 
//	 History        :说明历史
//	 1.Date         : 2015/12/23
//	   Author       : msqi
//	   Modification : Created function
//	*****************************************************************************/
//	public boolean checkAgree(String command,HmData requestBodyHd,Logger logger) throws Exception{
//		SignDao signDao=new SignDao();
//		boolean ck=true;
//		try {
//			logger.info("开始校验接口是否需要校验账号签约");
//			boolean flag=false;
//			String command_str=PropertiesUtils.getProperty("COMMANDS");
//			if(StringUtils.isNotBlank(command_str)){
//				String[] commands=command_str.split("\\|");
//				for (String string : commands) {
//					if(command.equals(string)){
//						flag=true;
//					}
//				}
//			}
//			if(flag){
//				logger.info("接口号>>>"+command+",需要校验账号签约");
//				String payAc="";
//				SignVo signVo=new SignVo();
//				int cflag=1;
//				logger.info("开始校验账号是否已签约");
//				if(CommandType.REGISTER_LIST_SEARCH.equals(command)){
//					if(requestBodyHd.get("type").toString().equals("4")){
//						payAc=requestBodyHd.get("payAc").toString().trim();
//						logger.info("账号>>>"+payAc);
//						signVo.setAcctNo(payAc);
//						cflag=signDao.checkSign(signVo, logger);
//						
//					}
//				}else{
//					payAc=requestBodyHd.get("payAc").toString().trim();
//					logger.info("账号>>>"+payAc);
//					signVo.setAcctNo(payAc);
//					cflag=signDao.checkSign(signVo, logger);
//				}
//				if(cflag==0){
//					MDC.put("className", PmManageService.class);			
//					logger.info("账号未签约");
//					ck=false;
//				}
//			}else{
//				logger.info("接口号>>>"+command+",不需要校验账号签约");
//			}
//		} catch (Exception e) {
//			logger.error("校验账号是否签约异常");
//			throw new Exception(e);
//		}
//		return ck;
//	}
//}
