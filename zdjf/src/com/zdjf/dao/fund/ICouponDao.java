package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.Coupon;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICouponDao extends IDaoSupport {
	Page page(Page page,Coupon coupon);
	Page page(Page page, Map<String,Object> map);

}
