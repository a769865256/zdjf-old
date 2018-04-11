package com.zdjf.domain.product;

import java.io.Serializable;
import java.util.Date;

/**
 * 抢标活动表
 * @author csh
 *
 */
public class ProductBuyRob implements Serializable {

	private static final long serialVersionUID = 1026030442806015656L;
	
	protected Long id;
	protected Long buy_id;     //int default NULL comment '购买ID',
	protected Long user_id;      //int default NULL comment '用户ID',
	protected Long product_id;     //int default NULL comment '产品ID',
	protected int rob_type;     //tinyint default NULL comment '抢标称谓: 1.菜鸟先飞 2日行千里 3.风驰电掣 4.一掷千金 5.金榜题名 6.马到功成',
	protected Double coin;       //decimal(11,2) default NULL comment '获得的正经值',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected String remark;     //varchar(255) default NULL comment '备注',

	public ProductBuyRob(){
		super();
	}
	
	public ProductBuyRob(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(Long buy_id) {
		this.buy_id = buy_id;
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

	public int getRob_type() {
		return rob_type;
	}

	public void setRob_type(int rob_type) {
		this.rob_type = rob_type;
	}

	public Double getCoin() {
		return coin;
	}

	public void setCoin(Double coin) {
		this.coin = coin;
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
	
	
}
