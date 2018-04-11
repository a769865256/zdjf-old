package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

import com.zdjf.util.DateUtil;



/**
 * 产品项目
 * @author csh
 *
 */
public class Product implements Serializable{

	private static final long serialVersionUID = 2673506034004155785L;
	
	protected Long id;
	protected String product_code;        //varchar(100) default NULL comment '产品编号(大标题)',
	protected String product_name;        //varchar(100) default NULL comment '产品名称(小标题)',
	protected int status;               //tinyint default NULL comment '项目状态:1.编辑中 11.审核中 2.发标中 3.待发布 31.预募集 4.投资中 5.履约中 6.已还款 7.满标 8.流标',
	protected String debt_code;            //varchar(100) default NULL comment '债权编号',
	protected int is_serial;            //tinyint default NULL comment '是否分期: 1分期 -1不分期',
	protected String serial_no;            //varchar(10) default NULL comment '分期编号',
	protected int is_fresh;             //tinyint default NULL comment '是否新手项目: 1新手 -1非新手',
	protected int ensure_type;          //tinyint default NULL comment '担保方式:1.抵押 2.质押 3.信用',
	protected int mortgage_type;        //tinyint default NULL comment '抵押方式: 1.汽车 2.房产 3.其他',
	protected Date start_date;           //date default NULL comment '借款起始日期',
	protected Date will_end_date;        //date default NULL comment '预计结束日期',
	protected Date end_date;            //date default NULL comment '借款结束日期',
	protected Double debt_money;           //decimal(10,2) default NULL comment '债券总额(合同总金额)',
	protected Double money;                //decimal(10,2) default NULL comment '本次转让金额(项目金额)',
	protected Double sale_money;           //decimal(10,2) default 0.00 comment '已售金额',
	protected Double balance;              //decimal(10,2) default 0.00 comment '剩余金额',
	protected int buyer_count;         //int default 0 comment '购买人数',
	protected Double pay_min;              //decimal(10,2) default NULL comment '起投金额',
	protected Double pay_add;              //decimal(10,2) default NULL comment '递增金额',
	protected Double pay_max;              //decimal(10,2) default NULL comment '投资上限',
	protected Double income;               //decimal(5,2) default NULL comment '年化收益(%)',
	protected Double income_offline;       //decimal(5,2) default NULL comment '线下付息率(%)',
	protected int income_method;        //tinyint default NULL comment '计息方式: 1.T+0 2.T+1',
	protected int return_method;        //tinyint default NULL comment '还本付息方式: 1.按日计息，到期一次性还本付息 2.按日计息，按月付息，到期还本',
	protected int online;               //tinyint default NULL comment '是否在线: 1.在线 -1.不在线',
	protected String remark;        //varchar(200) default NULL comment '备注',
	protected long create_id;           //int default NULL comment '创建人',
	protected Date create_time;         //datetime default NULL comment '创建时间',
	protected long audit_id;             //bigint default NULL comment '审核人',
	protected Date audit_time;           //datetime default NULL comment '审核时间',
	protected Date issue_time;           //datetime default NULL comment '发布时间',
	protected Date full_time;            //datetime default NULL comment '募集截至时间',
	protected int order_no;             //int default NULL comment '排序号',
	protected String photo;                //varchar(200) default NULL comment '封面图片地址',
	protected Date will_issue_time;      //datetime default NULL comment '预设发布时间',
	protected Date will_show_time;       //datetime default NULL comment '预募集状态上线时间',
	protected Date act_show_time;        //datetime default NULL comment '实际上线时间',
	protected int advance_status;       //tinyint default 1 comment '提前还款状态: 1.默认(申请失败) 2.提交申请 3.申请成功',
	protected Date advance_date;         //date default NULL comment '提前还款时间',
	protected int is_send_msg;          //tinyint default 0 comment '是否推送消息0:不推送 1:推送',
	protected Double platform_interest;    //decimal(5,2) default 0.00 comment '平台贴息',
	protected int product_type;         //tinyint default 1 comment '项目类型:1债权项目 2直投项目',
	protected int return_days;          //int default 0 comment '直投项目收益天数',
	protected String label;				//default NULL COMMENT '标签图片地址',
	protected Long product_id;			//bigint(20) default NULL COMMENT '原product_id',
	protected int home_order;			//default NULL COMMENT '首页排序',
	protected int home_stats;			//default NULL COMMENT '是否首页展示',
	protected String status_text;		// 项目状态文字
	protected double speed;				//进度
	protected int incomeDays;			//收益天数
	protected Long loan_id;				//借款人ID
	protected String product_desc;		//项目描述
	protected String lend_use;			//借款用途及还款保障
	protected String protect_msg;		//财产保障信息
	protected String protect_mode ;		//保障措施 
	protected Long lender_id;			//int(10) default NULL COMMENT '出借人id',
	protected double can_buy_money;
	protected int incom_month;
	protected Date fill_time;
	protected Date loan_time;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Product() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Product(Long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDebt_code() {
		return debt_code;
	}
	public void setDebt_code(String debt_code) {
		this.debt_code = debt_code;
	}
	public int getIs_serial() {
		return is_serial;
	}
	public void setIs_serial(int is_serial) {
		this.is_serial = is_serial;
	}
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	public int getIs_fresh() {
		return is_fresh;
	}
	public void setIs_fresh(int is_fresh) {
		this.is_fresh = is_fresh;
	}
	public int getEnsure_type() {
		return ensure_type;
	}
	public void setEnsure_type(int ensure_type) {
		this.ensure_type = ensure_type;
	}
	public int getMortgage_type() {
		return mortgage_type;
	}
	public void setMortgage_type(int mortgage_type) {
		this.mortgage_type = mortgage_type;
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
	public Double getDebt_money() {
		return debt_money;
	}
	public void setDebt_money(Double debt_money) {
		this.debt_money = debt_money;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getSale_money() {
		return sale_money;
	}
	public void setSale_money(Double sale_money) {
		this.sale_money = sale_money;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public int getBuyer_count() {
		return buyer_count;
	}
	public void setBuyer_count(int buyer_count) {
		this.buyer_count = buyer_count;
	}
	public Double getPay_min() {
		return pay_min;
	}
	public void setPay_min(Double pay_min) {
		this.pay_min = pay_min;
	}
	public Double getPay_add() {
		return pay_add;
	}
	public void setPay_add(Double pay_add) {
		this.pay_add = pay_add;
	}
	public Double getPay_max() {
		return pay_max;
	}
	public void setPay_max(Double pay_max) {
		this.pay_max = pay_max;
	}
	public Double getIncome() {
		return income;
	}
	public void setIncome(Double income) {
		this.income = income;
	}
	public Double getIncome_offline() {
		return income_offline;
	}
	public void setIncome_offline(Double income_offline) {
		this.income_offline = income_offline;
	}
	public int getIncome_method() {
		return income_method;
	}
	public void setIncome_method(int income_method) {
		this.income_method = income_method;
	}
	public int getReturn_method() {
		return return_method;
	}
	public void setReturn_method(int return_method) {
		this.return_method = return_method;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public long getCreate_id() {
		return create_id;
	}
	public void setCreate_id(long create_id) {
		this.create_id = create_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public long getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(long audit_id) {
		this.audit_id = audit_id;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public Date getIssue_time() {
		return issue_time;
	}
	public void setIssue_time(Date issue_time) {
		this.issue_time = issue_time;
	}
	public Date getFull_time() {
		return full_time;
	}
	public void setFull_time(Date full_time) {
		this.full_time = full_time;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getWill_issue_time() {
		return will_issue_time;
	}
	public void setWill_issue_time(Date will_issue_time) {
		this.will_issue_time = will_issue_time;
	}
	public Date getWill_show_time() {
		return will_show_time;
	}
	public void setWill_show_time(Date will_show_time) {
		this.will_show_time = will_show_time;
	}
	public Date getAct_show_time() {
		return act_show_time;
	}
	public void setAct_show_time(Date act_show_time) {
		this.act_show_time = act_show_time;
	}
	public int getAdvance_status() {
		return advance_status;
	}
	public void setAdvance_status(int advance_status) {
		this.advance_status = advance_status;
	}
	public Date getAdvance_date() {
		return advance_date;
	}
	public void setAdvance_date(Date advance_date) {
		this.advance_date = advance_date;
	}
	public int getIs_send_msg() {
		return is_send_msg;
	}
	public void setIs_send_msg(int is_send_msg) {
		this.is_send_msg = is_send_msg;
	}
	public Double getPlatform_interest() {
		return platform_interest;
	}
	public void setPlatform_interest(Double platform_interest) {
		this.platform_interest = platform_interest;
	}
	public int getProduct_type() {
		return product_type;
	}
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	public int getReturn_days() {
		return return_days;
	}
	public void setReturn_days(int return_days) {
		this.return_days = return_days;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public int getHome_order() {
		return home_order;
	}

	public void setHome_order(int home_order) {
		this.home_order = home_order;
	}

	public int getHome_stats() {
		return home_stats;
	}

	public void setHome_stats(int home_stats) {
		this.home_stats = home_stats;
	}

	public String getStatus_text() {
		return status_text;
	}

	public void setStatus_text(String status_text) {
		this.status_text = status_text;
	}

	public double getSpeed() {
		return sale_money/money*100;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public void setIncomeDays(int incomeDays) {
		this.incomeDays =incomeDays;
				
	}
	
	public static Integer calIncomeDays(int income_method, Date payDate, Date end_date) {
		Date start_date = calIncomeStartDate(payDate, income_method);
		return DateUtil.between(start_date, end_date);
	}
	public static Date calIncomeStartDate(Date start_date, int income_method) {
		if(income_method == 2) {
			return DateUtil.dateAdd(start_date, 1, 1);// 开始日期，类型1天，增加一天
		}
		return start_date;
	}

	public Long getLender_id() {
		return lender_id;
	}

	public void setLender_id(Long lender_id) {
		this.lender_id = lender_id;
	}

	public double getCan_buy_money() {
		return can_buy_money;
	}

	public void setCan_buy_money(double can_buy_money) {
		this.can_buy_money = can_buy_money;
	}

	public int getIncom_month() {
		int days=calIncomeDays(this.income_method, new Date(), this.end_date);
		int month=0;
		if(days>0&&days<=40){
			month=1;
		}else if(days>45&&days<=65){
			month=2;
		}else if(days>70&&days<=95){
			month=3;
		}else if(days>180){
			month=6;
		}
		return month;
	}

	public void setIncom_month(int incom_month) {
		this.incom_month=incom_month;
		
	}
	public int getIncomeDays() {
		if (status == 4) {
			incomeDays = DateUtil.daysBetween(new Date(), end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}

			return incomeDays;
		}else if((status == 5||status == 6) && DateUtil.between(new Date(), end_date) >=0){
			incomeDays = DateUtil.daysBetween(start_date, end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else if(status == 3 || status == 11){
			if( DateUtil.between(new Date(), start_date) > 0){
				incomeDays = DateUtil.daysBetween(start_date, end_date);
			}else if(DateUtil.between(new Date(), start_date) <= 0){
				incomeDays = DateUtil.daysBetween(new Date(), end_date);
			}
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else if(status == 31){
			incomeDays = DateUtil.daysBetween(issue_time, end_date);
			if(income_method == 1){
				incomeDays += 1;
			}else if(income_method == 2){
				incomeDays += 0;
			}
			return incomeDays;
		}else {

			return -1;
		}
	}

	public Long getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getLend_use() {
		return lend_use;
	}

	public void setLend_use(String lend_use) {
		this.lend_use = lend_use;
	}

	public String getProtect_msg() {
		return protect_msg;
	}

	public void setProtect_msg(String protect_msg) {
		this.protect_msg = protect_msg;
	}

	public String getProtect_mode() {
		return protect_mode;
	}

	public void setProtect_mode(String protect_mode) {
		this.protect_mode = protect_mode;
	}

	public Date getFill_time() {
		return fill_time;
	}

	public void setFill_time(Date fill_time) {
		this.fill_time = fill_time;
	}

	public Date getLoan_time() {
		return loan_time;
	}

	public void setLoan_time(Date loan_time) {
		this.loan_time = loan_time;
	}

}
