package com.zdjf.domain.file;

import java.io.Serializable;
import java.util.Date;

public class Guarantee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6966054475415255167L;
	
	
	protected Long id;
	protected Long from_id;      //int default NULL comment '理财ID',
	protected String from_table;     //varchar(128) comment '来源表',
	protected String guarantee_name;    //varchar(100) default NULL comment '文件名称',
	protected String guarantee_desc;    //varchar(500) default NULL comment '文件描述',
	//protected String file_url;     //varchar(500) default NULL comment '文件地址',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected int guarantee_num;     //int default 1 comment '图片顺序',
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFrom_id() {
		return from_id;
	}
	public void setFrom_id(Long from_id) {
		this.from_id = from_id;
	}
	public String getFrom_table() {
		return from_table;
	}
	public void setFrom_table(String from_table) {
		this.from_table = from_table;
	}
	public String getGuarantee_name() {
		return guarantee_name;
	}
	public void setGuarantee_name(String guarantee_name) {
		this.guarantee_name = guarantee_name;
	}
	public String getGuarantee_desc() {
		return guarantee_desc;
	}
	public void setGuarantee_desc(String guarantee_desc) {
		this.guarantee_desc = guarantee_desc;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getGuarantee_num() {
		return guarantee_num;
	}
	public void setGuarantee_num(int guarantee_num) {
		this.guarantee_num = guarantee_num;
	}
	
	
	
	

}
