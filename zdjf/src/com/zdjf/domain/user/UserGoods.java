package com.zdjf.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户兑换
 * @author csh
 *
 */
public class UserGoods implements Serializable {

	private static final long serialVersionUID = 5564504974805821696L;

	protected Long id;
	protected Long user_id;      //int default NULL comment '用户ID',
	protected Long product_id;     //int default NULL comment '产品ID',
	protected int goods_type;     //tinyint default NULL comment '产品类型 2 加息券 3红包',
	protected Long relation_id;    //int default NULL comment '产品关联ID',
	protected Double price;      //decimal(11,2) default NULL comment '兑换单价',
	protected Long num;      //int default NULL comment '兑换数量',
	protected Double amount;     //decimal(11,2) default NULL comment '兑换总价',
	protected Double derate_amount;    //decimal(11,2) default NULL comment '优惠减免',
	protected Double pay_amount;     //decimal(11,2) default NULL comment '实际支付',
	protected int status;     //tinyint default NULL comment '状态',
	protected String remark;     //varchar(500) default NULL comment '备注',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected Date update_time;    //datetime default NULL comment '修改时间',
	protected Long uid;			//bigint(20) default NULL COMMENT '原uid',
	protected Long goods_id;		//bigint(20) default NULL COMMENT '原goods_id',
	protected Long old_relation_id;	// bigint(20) default NULL COMMENT '原产品关联ID',
	
	public UserGoods(){
		super();
	}
	
	public UserGoods(Long id){
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

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public int getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}

	public Long getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDerate_amount() {
		return derate_amount;
	}

	public void setDerate_amount(Double derate_amount) {
		this.derate_amount = derate_amount;
	}

	public Double getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
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

	public Long getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Long goods_id) {
		this.goods_id = goods_id;
	}

	public Long getOld_relation_id() {
		return old_relation_id;
	}

	public void setOld_relation_id(Long old_relation_id) {
		this.old_relation_id = old_relation_id;
	}
	
	
}
