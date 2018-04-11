package com.zdjf.dao.user;

import java.util.Map;

import com.zdjf.domain.user.WithdrawCoupons;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface IWithdrawCouponsDao extends IDaoSupport {
	Page page(Page page, WithdrawCoupons withdrawCoupons);
	Page page(Page page, Map<String, Object> map);
}
