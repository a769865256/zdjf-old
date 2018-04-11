package com.zdjf.dao.fund;

import java.util.Map;

import com.zdjf.domain.fund.CoinGoods;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

public interface ICoinGoodsDao extends IDaoSupport {
	Page page(Page page, CoinGoods coinGoods);
	Page page(Page page, Map<String, Object> map);
}
