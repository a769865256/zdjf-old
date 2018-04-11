package com.zdjf.domain.file;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件
 * @author csh
 *
 */
public class File implements Serializable {

	private static final long serialVersionUID = 6011356611829337858L;

	protected Long id;
	protected Long from_id;      //int default NULL comment '理财ID',
	protected String from_table;     //varchar(128) comment '来源表',
	protected int file_type;    //tinyint default NULL comment '文件类型: 1.抵押、信用相关图片 2.律师意见图片 3.合同文件图片 4.其它文件图片 ',
	protected String file_name;    //varchar(100) default NULL comment '文件名称',
	protected String file_desc;    //varchar(500) default NULL comment '文件描述',
	protected String file_url;     //varchar(500) default NULL comment '文件地址',
	protected Date create_time;    //datetime default NULL comment '创建时间',
	protected int file_num;     //int default 1 comment '图片顺序',
	protected int file_width;     //int default 0 comment '宽度',
	protected int file_height;    //int default 0 comment '高度',
	
	public File(){
		super();
	}
	
	public File(Long id){
		super();
		this.id = id;
	}

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

	public int getFile_type() {
		return file_type;
	}

	public void setFile_type(int file_type) {
		this.file_type = file_type;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_desc() {
		return file_desc;
	}

	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}

	public String getFile_url() {
		if(null!=file_url&&file_url.indexOf("www.zdjfu.com")>0){
			file_url.replace("www.zdjfu.com", "172.16.1.115:8080/zdjf");
		}
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public int getFile_width() {
		return file_width;
	}

	public void setFile_width(int file_width) {
		this.file_width = file_width;
	}

	public int getFile_height() {
		return file_height;
	}

	public void setFile_height(int file_height) {
		this.file_height = file_height;
	}
	
	
	
}
