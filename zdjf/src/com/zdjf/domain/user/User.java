package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author chenrg
 * 
 */
public class User implements Serializable {
	private static final long serialVersionUID = -5307254768226221989L;

	protected Long id;   //int not null auto_increment comment '用户ID',
	protected String user_name;            //varchar(128) comment '用户名',
	protected String passwd;               //varchar(128) comment '密码',
	protected String pay_passwd;           //varchar(128) comment '支付密码',
	protected String invite_code;          //varchar(10) default NULL comment '邀请码',
	protected String phone;                //varchar(16) default NULL comment '手机号',
	protected String real_name;            //varchar(24) default NULL comment '真实姓名',
	protected String idcard_no;            //varchar(24) default NULL comment '身份证号码',
	protected int real_name_auth;       //tinyint not null default 0 comment '实名认证 1：审核通过   2:  处理中 ，-1：审核未通过 ',
	protected Date idcard_auth_time;     //datetime default NULL comment '审核时间',
	protected String pay_account;          //varchar(64) default NULL comment '第三方支付账号',
	protected int sex;                  //tinyint not null default 0 comment '1：男 2：女',
	protected Date update_time;          //datetime default NULL comment '修改时间',
	protected Date create_time;          //datetime default NULL comment '创建时间',
	protected String inviter_phone;        //varchar(16) default NULL comment '邀请人手机号',
	protected String birthday;             //varchar(4) default NULL comment '生日:MMdd格式',
	protected int new_hand;            //tinyint not null default 1 comment '新用户: 1是 2否',
	protected int return_status;        //tinyint not null default 1 comment '用户回款状态: 1.未回过款 2.已回过款',
	protected String remark;               //varchar(255) default NULL comment '备注',
	protected int user_type;            //tinyint not null default 1 comment '用户类型:1普通用户 2出借人 3直投借款人',
	protected String invite_source;        //varchar(100) default NULL comment '注册邀请来源:weixin(微信),qzone(qq空间),sinawbo(新浪微博),qwbo(腾讯微博)',
	protected int status;               //tinyint not null default 1 comment '状态: 1有效 2无效 ',
	protected int reg_source;           //tinyint not null default 1 comment '注册来源: 1web 2iOS 3android 4weixin 5其他',
	protected Date last_login_time;      //datetime comment '最后一次登录时间',
	protected String login_ip;             //varchar(16) comment '最后登陆ip',
	protected String reg_ip;               //varchar(16) comment '注册ip',
	protected int open_account;			//tinyint(3) default NULL COMMENT '认证通过 5   其他 数字是次数',
	protected Date open_account_time;	//datetime default NULL COMMENT '会员开通时间',
	protected String encrypt_phone;		//加密手机号	
	protected int sign_num;				// tinyint(4) default '0' COMMENT '当日签到可用次数',
	protected Date sign_data;			// tinyint(4) default '0' COMMENT '当日签到可用次数',
	
	public User(){
		super();
	}
	
	public User(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIdcard_no() {
		return idcard_no;
	}

	public void setIdcard_no(String idcard_no) {
		this.idcard_no = idcard_no;
	}

	public int getReal_name_auth() {
		return real_name_auth;
	}

	public void setReal_name_auth(int real_name_auth) {
		this.real_name_auth = real_name_auth;
	}

	public Date getIdcard_auth_time() {
		return idcard_auth_time;
	}

	public void setIdcard_auth_time(Date idcard_auth_time) {
		this.idcard_auth_time = idcard_auth_time;
	}

	public String getPay_account() {
		return pay_account;
	}

	public void setPay_account(String pay_account) {
		this.pay_account = pay_account;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getInviter_phone() {
		return inviter_phone;
	}

	public void setInviter_phone(String inviter_phone) {
		this.inviter_phone = inviter_phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getNew_hand() {
		return new_hand;
	}

	public void setNew_hand(int new_hand) {
		this.new_hand = new_hand;
	}

	public int getReturn_status() {
		return return_status;
	}

	public void setReturn_status(int return_status) {
		this.return_status = return_status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public String getInvite_source() {
		return invite_source;
	}

	public void setInvite_source(String invite_source) {
		this.invite_source = invite_source;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReg_source() {
		return reg_source;
	}

	public void setReg_source(int reg_source) {
		this.reg_source = reg_source;
	}

	public Date getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}
	public String getPay_passwd() {
		return pay_passwd;
	}

	public void setPay_passwd(String pay_passwd) {
		this.pay_passwd = pay_passwd;
	}

	public int getOpen_account() {
		return open_account;
	}

	public void setOpen_account(int open_account) {
		this.open_account = open_account;
	}

	public Date getOpen_account_time() {
		return open_account_time;
	}

	public void setOpen_account_time(Date open_account_time) {
		this.open_account_time = open_account_time;
	}

	public String getEncrypt_phone() {
		return encrypt_phone;
	}

	public void setEncrypt_phone(String encrypt_phone) {
		this.encrypt_phone = encrypt_phone;
	}

	public int getSign_num() {
		return sign_num;
	}

	public void setSign_num(int sign_num) {
		this.sign_num = sign_num;
	}

	public Date getSign_data() {
		return sign_data;
	}

	public void setSign_date(Date sign_data) {
		this.sign_data = sign_data;
	}
	
	
}
