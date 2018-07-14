package org.seeker.deal.entity;

public class TenpayEntity {
	/** 必填字段	 */
	/** 订单编号唯一性 */
	private String out_trade_no;
	/** 订单支付金额,以分为单位	 */
	private String total_fee;
	/** 订单描述	 */
	private String body;
	/** 商户号	 */
	private String partner;
	/** 交易完成后跳转的URL	 */
	private String return_url;
	/** 接收财付通通知的URL	 */
	private String notify_url;
	/** 签名	 */
	private String sign;
	/** 币种	 */
	private String fee_type;
	/** 用户公网ip	 */
	private String spbill_create_ip;
	
	
	/** 可谓空字段	 */
	/** 签名方式 签名类型，取值：MD5、RSA，默认：MD5	 */
	private String sign_type;
	/** 接口版本 1.0	 */
	private String service_version;
	/** 字符编码,取值：GBK、UTF-8，默认：GBK	 */
	private String input_charset;
	/** 多密钥支持的密钥序号，默认1	 */
	private String sign_key_index;
	/** 银行类型 DEFAULT	 */
	private String bank_type;
	/** 附加数据，原样返回	 */
	private String attach;
	/** 买方财付通id	 */
	private String buyer_id;
	/** 交易开始时间	 */
	private String time_start;
	/** 交易结束时间	 */
	private String time_expire;
	/** 商品费用	 */
	private String product_fee;
	/** 物流费用	 */
	private String transport_fee;
	/** 商品标记	 */
	private String goods_tag;
	
	/** 返回参数	 */
	
	
	/** 支付不成功原因	 */
	private String retmsg;
	/** 交易模式	 */
	private String trade_mode;
	/** 支付状态  0成功  其他为失败	 */
	private String trade_state;
	/** 银行订单号	 */
	private String bank_billno;
	/** 财付通的订单编号	 */
	private String transaction_id;
	/** 是否分账	 false无退款，true退款 */
	private String is_split;
	/** 是否退款 false无退款，true退款	 */
	private String is_refund;
	/** 支付完成时间	 */
	private String time_end;
	/** 返回码状态 返回状态码，0表示成功，其他未定义	 */
	private String retcode;
	
	public String getRetmsg() {
		return retmsg;
	}
	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}
	public String getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(String tradeMode) {
		trade_mode = tradeMode;
	}
	public String getTrade_state() {
		return trade_state;
	}
	public void setTrade_state(String tradeState) {
		trade_state = tradeState;
	}
	public String getBank_billno() {
		return bank_billno;
	}
	public void setBank_billno(String bankBillno) {
		bank_billno = bankBillno;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transactionId) {
		transaction_id = transactionId;
	}
	public String getIs_split() {
		return is_split;
	}
	public void setIs_split(String isSplit) {
		is_split = isSplit;
	}
	public String getIs_refund() {
		return is_refund;
	}
	public void setIs_refund(String isRefund) {
		is_refund = isRefund;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String timeEnd) {
		time_end = timeEnd;
	}
	public String getRetcode() {
		return retcode;
	}
	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}
	public TenpayEntity() {
		super();
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String outTradeNo) {
		out_trade_no = outTradeNo;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String totalFee) {
		total_fee = totalFee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String returnUrl) {
		return_url = returnUrl;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notifyUrl) {
		notify_url = notifyUrl;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String feeType) {
		fee_type = feeType;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbillCreateIp) {
		spbill_create_ip = spbillCreateIp;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String signType) {
		sign_type = signType;
	}
	public String getService_version() {
		return service_version;
	}
	public void setService_version(String serviceVersion) {
		service_version = serviceVersion;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String inputCharset) {
		input_charset = inputCharset;
	}
	public String getSign_key_index() {
		return sign_key_index;
	}
	public void setSign_key_index(String signKeyIndex) {
		sign_key_index = signKeyIndex;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bankType) {
		bank_type = bankType;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyerId) {
		buyer_id = buyerId;
	}
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String timeStart) {
		time_start = timeStart;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String timeExpire) {
		time_expire = timeExpire;
	}
	public String getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(String productFee) {
		product_fee = productFee;
	}
	public String getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(String transportFee) {
		transport_fee = transportFee;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goodsTag) {
		goods_tag = goodsTag;
	}

}
