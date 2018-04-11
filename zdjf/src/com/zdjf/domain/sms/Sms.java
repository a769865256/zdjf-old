package com.zdjf.domain.sms;

import java.io.Serializable;
import java.util.Date;

/**
 * 短信表
 * @author csh
 *
 */
public class Sms implements Serializable {

	private static final long serialVersionUID = 1116166168552890815L;

	protected Long id;
	protected String phone;      //varchar(15) default NULL comment '手机',
	protected String content;     //varchar(200) default NULL comment '短信内容',
	protected String code;       //varchar(10) default NULL comment '验证码',
	protected int used;       //tinyint default 2 comment '是否使用:1是 2否',
	protected int sms_type;     //tinyint default NULL comment '短信类型 1: 注册码 2: 找回登陆密码 3: 还款通知  4:购买理财产品5: 提现提醒 6:站内提醒',
	protected String source_ip;    //char(15) default NULL comment '发送IP',
	protected Date create_time;   // datetime default NULL comment '创建时间',
	protected int status;     //tinyint default NULL comment '状态:1已发送 2未发送 -1发送失败 0已提交',
	protected String remark;     //text comment '备注',
	
	protected String product;
	protected String amount;
	protected String income;
	
	public Sms(){
		super();
	}
	
	public Sms(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getSms_type() {
		return sms_type;
	}

	public void setSms_type(int sms_type) {
		this.sms_type = sms_type;
	}

	public String getSource_ip() {
		return source_ip;
	}

	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}
}
