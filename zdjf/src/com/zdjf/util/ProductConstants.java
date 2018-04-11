package com.zdjf.util;

/**
 * 理财项目常量类
 * @author shuc
 * @version 2016年2月18日17:48:20
 * @see ProductConstants
 */
public class ProductConstants {

	/** 项目统计：初始化 */
	public final static Double ZERO= 0.0;

	/** 请求参数：导出检查 */
	public final static String REQ_PARAM_EXPORT_CHECK = "exportcheck";

	/** 请求参数：查询参数 */
	public final static String REQ_PARAM_SEARCH_PARAM = "searchParam";

	/** 请求参数：查询参数 */
	public final static String REQ_PARAM_NAME = "name";

	/** SQL参数：limitFrom */
	public final static String SQL_PARAM_LIMIT = "limit";

	/** 请求参数：查询类型 */
	public final static String REQ_PARAM_SEARCH_TYPE = "searchType";

	/** 请求参数：理财项目状态 */
	public final static String REQ_PARAM_PRODUCT_STATUS = "status";

	/** 请求参数：开始时间 */
	public final static String REQ_PARAM_START_DATE = "startDate";

	/** 请求参数：结束时间 */
	public final static String REQ_PARAM_END_DATE = "endDate";

	/** 请求参数：开始时间 */
	public final static String REQ_PARAM_ISSUE_START_DATE = "issueStartDate";

	/** 请求参数：结束时间 */
	public final static String REQ_PARAM_ISSUE_END_DATE = "issueEndDate";

	/** 请求参数：转让金额 */
	public final static String REQ_PARAM_MONEY_MIN = "moneyMin";

	/** 请求参数：转让金额 */
	public final static String REQ_PARAM_MONEY_MAX = "moneyMax";

	/** 请求参数：收益剩余天数 */
	public final static String REQ_PARAM_INCOME_DAYS_MIN = "incomeDaysMin";

	/** 请求参数：收益剩余天数 */
	public final static String REQ_PARAM_INCOME_DAYS_MAX = "incomeDaysMax";

	/** page */
	public final static String PAGE = "page";

	/** pageSize */
	public final static String PAGE_SIZE = "pageSize";

	/** 借款人种类 */
	public final static int LENDER = 1;
	
	/** 出借人种类 */
	public final static int LOANER = 2;
	
	/** 产品文件类型 */
	public class FileType {
		/** 抵押、信用相关图片 */
		public final static int INFO = 1;
		
		/** 律师意见图片 */
		public final static int SUGGEST = 2;
		
		/** 合同文件图片 */
		public final static int CONTRACT = 3;
		
		/** 其它文件图片 */
		public final static int OTHER = 4;
	}
	
	/** 产品状态 */
	public class ProductStatus {
		/** 编辑中 */
		public final static int EDIT = 1;
		/** 审核中 */
		public final static int SUBMIT = 11;
		/** 发标中 */
		public final static int BID = 2;
		/** 待发布 */
		public final static int WAIT_ISSUE = 3;
		/** 预募集 */
		public final static int WILL_TRYING = 31;
		/** 投资中 */
		public final static int TRYING = 4;
		/** 履约中 */
		public final static int COMPLETE = 5;
		/** 已还款 */
		public final static int RETURNED = 6;
	}
	
	/** 出借人类型 */
	public class LendType {
		/** 个人 */
		public final static int PERSON = 1;
	
		/** 企业 */
		public final static int COMPANY = 2;
	}
	
	/** 还本付息方式 */
	public class ReturnMethod {
		/** 一次性还本付息 */
		public final static int ONCE = 1;
		
		/** 按月计息 */
		public final static int MONTH = 2;
	}
}
