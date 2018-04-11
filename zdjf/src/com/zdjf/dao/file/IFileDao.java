package com.zdjf.dao.file;

import com.zdjf.domain.file.File;
import com.zdjf.frame.dataaccess_api.IDaoSupport;
import com.zdjf.frame.dataaccess_api.Page;

//借款人
public interface IFileDao extends IDaoSupport{

	Page page(Page page, File file);
}
