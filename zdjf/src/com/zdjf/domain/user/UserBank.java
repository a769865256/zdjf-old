package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户银行卡记录表
 * @author csh
 *
 */
public class UserBank implements Serializable {

	private static final long serialVersionUID = 7930164047526148349L;
	
	protected Long id;
	protected Long user_id;                //int not null default 0 comment '用户ID',
	protected String bank_no;              //varchar(30) not null comment '银行卡号',
	protected String bank_alias;           //varchar(20) default NULL comment '银行名称',
	protected String card_user_name;       //varchar(20) default NULL comment '银行卡持有人姓名',
	protected String phone;                //varchar(20) default NULL comment '预留手机',
	protected int status;                  // tinyint default NULL comment '绑定状态：1 成功，2 处理中，3：审核失败  4:无效',
	protected String remark;               //varchar(200) default NULL comment '备注',
	protected Date create_time;          //datetime default NULL comment '创建时间',
	protected int type;                 //tinyint default 2 comment '银行卡类型: 1.快捷支付 2.普通',
	protected Long uid;					// bigint(20) default NULL COMMENT '原uid',
	protected String bank_open;			// varchar(255) default NULL COMMENT '开户行名称',
	protected Date update_time;          //datetime comment '更新时间（只有解绑的时候才有更新时间）',
	protected String province;			//varchar(16) default NULL COMMENT '省',
	protected String city;				//varchar(16) default NULL COMMENT '市',
	protected String request_no;		//varchar(32) default NULL COMMENT '唯一标识',
	protected String card_id;			//varchar(32) default NULL COMMENT '绑定银行卡',
	protected String ticket;			//varchar(64) default NULL COMMENT '绑定',
	protected String unbind_ticket;		//varchar(64) default NULL COMMENT '解绑',
	protected String card_attribute;	//varchar(8) default NULL COMMENT '卡属性：C个人 ，B 企业',
	
	
	
	public UserBank(){
		super();
	}
	
	public UserBank(Long id){
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

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public String getBank_alias() {
		return bank_alias;
	}

	public void setBank_alias(String bank_alias) {
		this.bank_alias = bank_alias;
	}

	public String getCard_user_name() {
		return card_user_name;
	}

	public void setCard_user_name(String card_user_name) {
		this.card_user_name = card_user_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getBank_open() {
		return bank_open;
	}

	public void setBank_open(String bank_open) {
		this.bank_open = bank_open;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getUnbind_ticket() {
		return unbind_ticket;
	}

	public void setUnbind_ticket(String unbind_ticket) {
		this.unbind_ticket = unbind_ticket;
	}

	public String getCard_attribute() {
		return card_attribute;
	}

	public void setCard_attribute(String card_attribute) {
		this.card_attribute = card_attribute;
	}
	
	
	
}
