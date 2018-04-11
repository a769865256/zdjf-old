package com.zdjf.domain.advertise;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告位管理
 * @author csh
 *
 */
public class Advertise implements Serializable {

	private static final long serialVersionUID = 9042309784944486274L;

	protected Long id;      
	protected int web_site;     //tinyint not null default 1 comment '站点: 1.web 2.移动端',
	protected int position;     //tinyint not null default 1 comment '存放位置: 1.首页',
	protected String title;      //varchar(255) not null default '' comment '标题',
	protected String alt;      //varchar(255) not null default '' comment '图片alt属性',
	protected String image_url;    //varchar(255) not null comment '图片存放地址',
	protected String href_url;     //varchar(255) not null comment '图片超链接',
	protected int order_number;   //int not null comment '序号',
	protected int is_show;      //tinyint not null default 1 comment '是否显示：1、显示；2、不显示',
	protected Date create_time;    //datetime not null comment '创建时间',
	protected Date update_time;    //datetime default NULL comment '更新时间',
	protected int view_count;     //int default 0 comment '访问量',
	
	public Advertise(){
		super();
	}
	
	public Advertise(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getWeb_site() {
		return web_site;
	}

	public void setWeb_site(int web_site) {
		this.web_site = web_site;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getHref_url() {
		return href_url;
	}

	public void setHref_url(String href_url) {
		this.href_url = href_url;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public int getIs_show() {
		return is_show;
	}

	public void setIs_show(int is_show) {
		this.is_show = is_show;
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

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	
	
	
}
