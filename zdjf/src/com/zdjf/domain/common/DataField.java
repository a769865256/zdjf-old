package com.zdjf.domain.common;

import java.io.Serializable;

public class DataField implements Serializable{
	private static final long serialVersionUID = 6038761260835458401L;

	public DataField(){
		super();
	}
	
	 /** 字段标识 */
    private String data_field_id = "";

    /** 字段描述 */
    private String data_field_desc = "";

    /** 涉及表名 */
    private String table_name = "";

    /** 涉及表字段 */
    private String field_name = "";

    /** 排序 */
    private Integer num = 0;

    /** 状态：0:停用 1启用 */
    private Integer status = 0;

    /** 字段值 */
    private String data_field_value = "";

    /** 字段名称 */
    private String data_field_name = "";

	public String getData_field_id() {
		return data_field_id;
	}

	public void setData_field_id(String data_field_id) {
		this.data_field_id = data_field_id;
	}

	public String getData_field_desc() {
		return data_field_desc;
	}

	public void setData_field_desc(String data_field_desc) {
		this.data_field_desc = data_field_desc;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getData_field_value() {
		return data_field_value;
	}

	public void setData_field_value(String data_field_value) {
		this.data_field_value = data_field_value;
	}

	public String getData_field_name() {
		return data_field_name;
	}

	public void setData_field_name(String data_field_name) {
		this.data_field_name = data_field_name;
	}

    
}
