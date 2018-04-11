package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

import com.zdjf.util.DateUtil;

/**
 * 用户购买理财记录
 * @author csh
 *
 */
public class ProductBuy implements Serializable {

	private static final long serialVersionUID = 3186179947596229945L;

	protected Long id;
	protected Long user_id;      //int not null comment '用户ID',
	protected String phone;      //varchar(12) default NULL comment '用户手机',
	protected Long product_id;     //bigint not null comment '产品ID',
	protected String product_name;   //varchar(50) default NULL comment '产品名称',
	protected int type;       //tinyint default NULL comment '类型: 1:理财产品',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Date pay_time;     //datetime default NULL comment '支付时间',
	protected Date close_time;     //datetime default NULL comment '结算时间',
	protected Date return_date;    //date default NULL comment '预计回款日期',
	protected Date start_date;     //date default NULL comment '计息开始日期',
	protected Date will_end_date;    //date default NULL comment '预计计息结束日期',
	protected Date end_date;     //date default NULL comment '计息结束日期',
	protected int will_income_days;   //int default NULL comment '预计计息天数',
	protected int income_days;    //int default NULL comment '计息总天数',
	protected int status;     //tinyint default NULL comment '状态：1 已支付 0 已投标  -1 未支付  -2 失败  -3 过期 -4 取消 -5 失效  2 已回款 -6 订单确认 7募集中 -7流标',
	protected int bid_status;     //tinyint default 2 comment '是否投标: 1是 2否',
	protected Double product_interest;   //decimal(6,2) default NULL comment '年化收益',
	protected Long user_coupon_id;   //bigint default NULL comment '加息券ID',
	protected Long user_gift_id;   //bigint default NULL comment '红包ID',
	protected Double amount;     //decimal(20,2) default NULL comment '本金',
	protected Double will_income;    //decimal(20,2) default NULL comment '预计收益',
	protected Double incomed;      //decimal(20,2) default NULL comment '收益',
	protected Double fee;      //decimal(8,2) default NULL comment '管理手续费',
	protected Double coin;       //decimal(20,2) default NULL comment '正经值抵扣',
	protected Double act_pay_money;    //decimal(20,2) default NULL comment '订单实际支付金额',
	protected int reconciliation_id;  //int default NULL comment '每次付款投标流水',
	protected int req_source;     //tinyint default 1 comment '请求来源: 1.web 2.iOS 3:安卓 4.微信 5.自动投标',
	protected int first_buy_flag;   //tinyint not null default 2 comment '是否为用户的第一笔投资:1.是 2.否',
	protected Long freeze_trx_id;    //bigint default NULL comment '冻结标识',
	protected String trade_no;		//varchar(64) default NULL COMMENT '订单号',
	protected String inviter_phone;	//varchar(12) default NULL COMMENT '邀请人手机号',
	protected Long buy_id;			//bigint(20) default NULL COMMENT '原buy_id',
	protected Long uid;				//bigint(20) default NULL COMMENT '原uid',
	protected Long order_id;		// bigint(20) default NULL COMMENT '原order_id',
	protected int is_debt;
	protected String dis_days;
	

	public ProductBuy(){
		super();
	}
	
	public ProductBuy(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getPay_time() {
		return pay_time;
	}

	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	public Date getClose_time() {
		return close_time;
	}

	public void setClose_time(Date close_time) {
		this.close_time = close_time;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getWill_end_date() {
		return will_end_date;
	}

	public void setWill_end_date(Date will_end_date) {
		this.will_end_date = will_end_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getWill_income_days() {
		return will_income_days;
	}

	public void setWill_income_days(int will_income_days) {
		this.will_income_days = will_income_days;
	}

	public int getIncome_days() {
		return income_days;
	}

	public void setIncome_days(int income_days) {
		this.income_days = income_days;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBid_status() {
		return bid_status;
	}

	public void setBid_status(int bid_status) {
		this.bid_status = bid_status;
	}

	public Double getProduct_interest() {
		return product_interest;
	}

	public void setProduct_interest(Double product_interest) {
		this.product_interest = product_interest;
	}

	public Long getUser_coupon_id() {
		return user_coupon_id;
	}

	public void setUser_coupon_id(Long user_coupon_id) {
		this.user_coupon_id = user_coupon_id;
	}

	public Long getUser_gift_id() {
		return user_gift_id;
	}

	public void setUser_gift_id(Long user_gift_id) {
		this.user_gift_id = user_gift_id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getWill_income() {
		return will_income;
	}

	public void setWill_income(Double will_income) {
		this.will_income = will_income;
	}

	public Double getIncomed() {
		return incomed;
	}

	public void setIncomed(Double incomed) {
		this.incomed = incomed;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getCoin() {
		return coin;
	}

	public void setCoin(Double coin) {
		this.coin = coin;
	}

	public Double getAct_pay_money() {
		return act_pay_money;
	}

	public void setAct_pay_money(Double act_pay_money) {
		this.act_pay_money = act_pay_money;
	}

	public int getReconciliation_id() {
		return reconciliation_id;
	}

	public void setReconciliation_id(int reconciliation_id) {
		this.reconciliation_id = reconciliation_id;
	}

	public int getReq_source() {
		return req_source;
	}

	public void setReq_source(int req_source) {
		this.req_source = req_source;
	}

	public int getFirst_buy_flag() {
		return first_buy_flag;
	}

	public void setFirst_buy_flag(int first_buy_flag) {
		this.first_buy_flag = first_buy_flag;
	}

	public Long getFreeze_trx_id() {
		return freeze_trx_id;
	}

	public void setFreeze_trx_id(Long freeze_trx_id) {
		this.freeze_trx_id = freeze_trx_id;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getInviter_phone() {
		return inviter_phone;
	}

	public void setInviter_phone(String inviter_phone) {
		this.inviter_phone = inviter_phone;
	}

	public Long getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	public int getIs_debt() {
		return is_debt;
	}

	public void setIs_debt(int is_debt) {
		this.is_debt = is_debt;
	}

	public String getDis_days() {
		if(getWill_end_date() !=null){
			int days = DateUtil.incomeDays(getWill_end_date(), new Date());
			if(days<0)
				days = 0;
			dis_days = days + "";
		}
		return dis_days;
	}

	public void setDis_days(String dis_days) {
		this.dis_days = dis_days;
	}
	
	
}
