package com.zdjf.domain.user;

import java.util.List;

/**
 * 用户
 * 
 * @author chenrg
 * 
 */
public class UserVo extends User {

	private static final long serialVersionUID = 9036039240553758147L;

	private List<User> userList;

	public UserVo() {
		super();
	}

	public UserVo(Long id) {
		super();
		this.id = id;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
