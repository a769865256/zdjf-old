package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

import com.zdjf.util.StrUtils;

/**
 * 借款人
 * @author csh
 *
 */
public class Loaner implements Serializable {

	private static final long serialVersionUID = 851806604278452400L;
	
	protected Long id;                   
	protected int loaner_type;          //tinyint default NULL comment '借款人类型:1.个人 2.企业',
	protected String name;                 //varchar(20) default NULL comment '姓名/法人',
	protected String phone;                //varchar(11) default NULL comment '手机号码',
	protected String idcard;               //varchar(18) default NULL comment '身份证号',
	protected int marital;              //tinyint default NULL comment '婚姻状况: 1.已婚 -1.未婚',
	protected String address;              //varchar(200) default NULL comment '地址',
	protected String comp_alias;           //varchar(20) default NULL comment '企业简称',
	protected String comp_name;            //varchar(100) default NULL comment '企业名称',
	protected String comp_code;            //varchar(100) default NULL comment '营业执照',
	protected Date reg_date;            //date default NULL comment '注册日期',
	protected Double reg_money;            //decimal(20,2) default NULL comment '注册资本',
	protected String comp_address;         //varchar(200) default NULL comment '企业地址',
	protected int status;               //tinyint default NULL comment '状态:1有效 2.无效',
	protected Date create_time;          //datetime default NULL comment '创建时间',
	protected String remark;               //varchar(500) default NULL comment '备注',
	protected int user_id;					//default NULL COMMENT '用户id',
	protected int age;
	protected String sex;
	
	public Loaner(){
		super();
	}
	
	public Loaner(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getLoaner_type() {
		return loaner_type;
	}

	public void setLoaner_type(int loaner_type) {
		this.loaner_type = loaner_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public int getMarital() {
		return marital;
	}

	public void setMarital(int marital) {
		this.marital = marital;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComp_alias() {
		return comp_alias;
	}

	public void setComp_alias(String comp_alias) {
		this.comp_alias = comp_alias;
	}

	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public String getComp_code() {
		return comp_code;
	}

	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Double getReg_money() {
		return reg_money;
	}

	public void setReg_money(Double reg_money) {
		this.reg_money = reg_money;
	}

	public String getComp_address() {
		return comp_address;
	}

	public void setComp_address(String comp_address) {
		this.comp_address = comp_address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAge() {
		return StrUtils.idcardToAge(this.idcard);
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return StrUtils.idcardToSexChn(this.idcard);
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
