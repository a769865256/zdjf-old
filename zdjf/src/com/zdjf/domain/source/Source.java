package com.zdjf.domain.source;

import java.io.Serializable;
import java.util.Date;

/**
 * 推广渠道
 * @author csh
 *
 */
public class Source implements Serializable {

	private static final long serialVersionUID = 112413908834508291L;
	
	protected Long id;       
	protected int source_platform;  //tinyint default NULL comment '所属平台',
	protected String source_code;    //varchar(100) comment '渠道代码',
	protected String source_name;    //varchar(100) default NULL comment '渠道名称',
	protected String source_url;     //varchar(200) default NULL comment '渠道链接',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected String remark;     //varchar(200) default NULL comment '备注',
	protected Long source_id;	//bigint(20) default NULL COMMENT '原渠道id',

	public Source(){
		super();
	}
	
	public Source(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSource_platform() {
		return source_platform;
	}

	public void setSource_platform(int source_platform) {
		this.source_platform = source_platform;
	}

	public String getSource_code() {
		return source_code;
	}

	public void setSource_code(String source_code) {
		this.source_code = source_code;
	}

	public String getSource_name() {
		return source_name;
	}

	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}

	public String getSource_url() {
		return source_url;
	}

	public void setSource_url(String source_url) {
		this.source_url = source_url;
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

	public Long getSource_id() {
		return source_id;
	}

	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}
	
	
	
}
