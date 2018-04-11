package com.zdjf.dao.user;

import java.util.Map;

import com.zdjf.domain.user.UserCoupon;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IUserCouponDao extends IDaoSupport {
	Page page(Page page, UserCoupon userCoupon);
	Page page(Page page, Map<String, Object> map);
}
