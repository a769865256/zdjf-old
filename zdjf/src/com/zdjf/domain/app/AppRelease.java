package com.zdjf.domain.app;

import java.io.Serializable;
import java.util.Date;

/**
 * APP版本控制
 * @author csh
 *
 */
public class AppRelease implements Serializable {

	private static final long serialVersionUID = -6420113211870999112L;

	protected Long id;
	protected String release_version;  //varchar(255) default NULL comment '发布版本',
	protected String sub_version;    //varchar(255) default NULL comment '子版本',
	protected String release_content;  //varchar(500) default NULL comment '发布内容',
	protected int is_force;
	protected int is_release;     //int default -1 comment '是否1:已发布 -1未发布',
	protected String down_url;     //varchar(255) default NULL comment '下载地址',
	protected String release_channel;  //varchar(255) default NULL comment '发布渠道',
	protected Date re_release_time;  //datetime default NULL comment '预发布时间',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	
	public AppRelease(){
		super();
	}
	
	public AppRelease(Long id){
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelease_version() {
		return release_version;
	}

	public void setRelease_version(String release_version) {
		this.release_version = release_version;
	}

	public String getSub_version() {
		return sub_version;
	}

	public void setSub_version(String sub_version) {
		this.sub_version = sub_version;
	}

	public String getRelease_content() {
		return release_content;
	}

	public void setRelease_content(String release_content) {
		this.release_content = release_content;
	}

	public int getIs_release() {
		return is_release;
	}

	public void setIs_release(int is_release) {
		this.is_release = is_release;
	}

	public String getDown_url() {
		return down_url;
	}

	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}

	public String getRelease_channel() {
		return release_channel;
	}

	public void setRelease_channel(String release_channel) {
		this.release_channel = release_channel;
	}

	public Date getRe_release_time() {
		return re_release_time;
	}

	public void setRe_release_time(Date re_release_time) {
		this.re_release_time = re_release_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getIs_force() {
		return is_force;
	}

	public void setIs_force(int is_force) {
		this.is_force = is_force;
	}
	
	
}
