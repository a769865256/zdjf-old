/*
 * UserListBean.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月6日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.domain.user;


import java.util.Date;

import com.zdjf.util.Constants;



/**
 *  注册会员列表实体
 * 
 * @author guanhj
 * @version 2015年9月6日
 * @see UserListBean
 * @since
 */
public class UserListBean {

    /** 用户ID */
    private Long uid = Constants.LONG_ZERO;

    /** 用户名 */
    private String userName = Constants.NOTHING;

    /** 用户性别 */
    private Integer gender = 1;

    /** 真实姓名 */
    private String realName = Constants.NOTHING;

    /** 邮箱 */
    private String email = Constants.NOTHING;

    /** 手机 */
    private String phone = Constants.NOTHING;

    /** 手机 */
    private String phoneFull = Constants.NOTHING;

    /** 邀请人手机 */
    private String inviterPhone = Constants.NOTHING;

    /** 邀请人数 */
    private Integer inviters = Constants.NUM_ZERO;

    /** 状态值 */
    private Integer status = Constants.NUM_ZERO;

    /** 可用余额 */
    private Double balance = Constants.DOUBLE_ZERO;

    
	/** 状态名称 */
    private String statusName = Constants.NOTHING;

    /** 备注 */
    private String remark = Constants.NOTHING;

    private String regSource = Constants.NOTHING;
    
    private String regSourceName = Constants.NOTHING;

    /** 充值总额 */
    private Double recharged = Constants.DOUBLE_ZERO;

    private Double totalRecharged = Constants.DOUBLE_ZERO;

    /** 注册时间 */
    private Date createTime = null;

    /** 注册渠道 */
    private String spreadChannelName = Constants.NOTHING;

    /** 查询汇付余额关联ID */
    private Long payAccount = Constants.LONG_ZERO;

    /** 累计投资金额 */
    private Double grandTotalInvested = Constants.DOUBLE_ZERO;

    /** 首次投资金额 */
    private Double firstInvested = Constants.DOUBLE_ZERO;

    /** 累计提现金额 */
    private Double withdrawed = Constants.DOUBLE_ZERO;

    /** 投资次数 */
    private Integer amount = Constants.NUM_ZERO;

    /** 最近投资时间 */
    private Date payTime = null;

    /** 最近充值时间 */
    private Date rechargeTime = null;

    /** 渠道名称 */
    private String sourceName =Constants.NOTHING;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Double getBalance() {
  		return balance;
  	}


  	public void setBalance(Double balance) {
  		this.balance = balance;
  	}


    /**
     * @return Returns the uid.
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid The uid to set.
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return Returns the realName.
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName The realName to set.
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;

    }

    public String getPhoneFull() {
        return phoneFull;
    }

    public void setPhoneFull(String phoneFull) {
        this.phoneFull = phoneFull;
    }

    /**
     * @return Returns the inviterPhone.
     */
    public String getInviterPhone() {
        return inviterPhone;
    }

    /**
     * @param inviterPhone The inviterPhone to set.
     */
    public void setInviterPhone(String inviterPhone) {
        this.inviterPhone = inviterPhone;
    }

    /**
     * @return Returns the inviters.
     */
    public Integer getInviters() {
        return inviters;
    }

    /**
     * @param inviters The inviters to set.
     */
    public void setInviters(Integer inviters) {
        this.inviters = inviters;
    }

    /**
     * @return Returns the status.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return Returns the statusName.
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName The statusName to set.
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * @return Returns the createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime The createTime to set.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return Returns the spreadChannelName.
     */
    public String getSpreadChannelName() {
        return spreadChannelName;
    }

    /**
     * @param spreadChannelName The spreadChannelName to set.
     */
    public void setSpreadChannelName(String spreadChannelName) {
        this.spreadChannelName = spreadChannelName;
    }

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

    public Double getWithdrawed() {
        return withdrawed;
    }

    public void setWithdrawed(Double withdrawed) {
        this.withdrawed = withdrawed;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRegSourceName() {
		return regSourceName;
	}

	public void setRegSourceName(String regSourceName) {
		this.regSourceName = regSourceName;
	}

	public Double getRecharged() {
		return recharged;
	}

	public void setRecharged(Double recharged) {
		this.recharged = recharged;
	}

	public Double getTotalRecharged() {
		return totalRecharged;
	}

	public void setTotalRecharged(Double totalRecharged) {
		this.totalRecharged = totalRecharged;
	}

    public Long getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(Long payAccount) {
        this.payAccount = payAccount;
    }

    public Double getGrandTotalInvested() {
        return grandTotalInvested;
    }

    public void setGrandTotalInvested(Double grandTotalInvested) {
        this.grandTotalInvested = grandTotalInvested;
    }

    public Double getFirstInvested() {
        return firstInvested;
    }

    public void setFirstInvested(Double firstInvested) {
        this.firstInvested = firstInvested;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}