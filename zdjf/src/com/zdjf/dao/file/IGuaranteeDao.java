package com.zdjf.dao.file;

import com.zdjf.domain.file.Guarantee;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

//借款人
public interface IGuaranteeDao extends IDaoSupport{

	Page page(Page page, Guarantee guarantee);
}
