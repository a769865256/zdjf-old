package com.zdjf.domain.notice;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告表
 * @author csh
 *
 */
public class Notice implements Serializable {

	private static final long serialVersionUID = 7411621617152008288L;
	
	protected Long id;       
	protected String title;      //varchar(255) not null comment '标题',
	protected String keywords;     //varchar(255) not null comment '关键字',
	protected String content;      //text not null comment '内容',
	protected int type;       //tinyint not null comment '分类: 1.平台公告、2.投资讲堂、3.关于我们、4.政策法规、5.安全保障、6.媒体报道、7.招贤纳士、8.联系我们',
	protected Date create_time;    //datetime not null comment '添加时间',
	protected Date update_time;    //datetime not null comment '更新时间',
	protected String link;       //varchar(255) not null comment '链接地址',
	protected int is_show;      //tinyint not null default 1 comment '是否显示：1显示；2隐藏',
	protected int order_number;   //int default NULL comment '数字越大，排位越靠前',
	protected String web_desc;     //varchar(200) default NULL comment '网页描述',
	protected String description;     //varchar(200) default NULL comment '描述',
	protected String source;     //varchar(50) default '',
	protected String image_url;    //varchar(255) default NULL comment '图片地址',
	protected int view_count;     //int default 1 comment '访问量',
	protected int web_site;     //tinyint default 1 comment '所属站点: 1.web 2.移动端',
	protected int view_initial;   //int default 0 comment '后台设置初始访问量',

	public Notice(){
		super();
	}
	
	public Notice(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getIs_show() {
		return is_show;
	}

	public void setIs_show(int is_show) {
		this.is_show = is_show;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public String getWeb_desc() {
		return web_desc;
	}

	public void setWeb_desc(String web_desc) {
		this.web_desc = web_desc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	public int getWeb_site() {
		return web_site;
	}

	public void setWeb_site(int web_site) {
		this.web_site = web_site;
	}

	public int getView_initial() {
		return view_initial;
	}

	public void setView_initial(int view_initial) {
		this.view_initial = view_initial;
	}
	
	
	
}
