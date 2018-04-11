package com.zdjf.domain.activity;

import java.io.Serializable;
import java.util.Date;

public class AwardRec implements Serializable {

    private static final long serialVersionUID = 1073288922396135127L;

    private Integer id;

    private Long user_id;

    private int reg_source;

    private int activity_type;

    private int award_type;

    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getReg_source() {
        return reg_source;
    }

    public void setReg_source(int reg_source) {
        this.reg_source = reg_source;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public int getAward_type() {
        return award_type;
    }

    public void setAward_type(int award_type) {
        this.award_type = award_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}