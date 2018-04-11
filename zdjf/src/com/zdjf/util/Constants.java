package com.zdjf.util;

import java.io.File;
import java.math.BigDecimal;

/**
 * 常量类
 * @author chensh
 *
 */
public class Constants {
	public static final String USER_SESSION = "USER_SESSION";	
	
	public static final String API_KEY = "3QvlI7PArDBTa@4YC%efmFxU";
	
	public static final Double WITHDRAW_FREE = 1000.0;//手续费
	
	public static final Double SIGN_TOTLE_YESTERDAY = 1000.0;//手续费
	
	public static final Double SIGN_TOTLE_YESTERDAY_TEN = 10000.0;//手续费
	
	public static final String MEMO = "zdjf";
	
	public static final String BID_TYPE="BORROW";
	
	public static final int OPEN_ACCOUNT_KEY = 5;
	
	public static final int HOST_PAY_KEY = 1000*30;
	
	 /** 数字正则 */
    public static final String IS_NUMBERIC_REG = "[0-9]+";

    /** 身份证加密KEY */
    public static final String IDCARD_CODE_KEY = "idcard_code_key";

    /** 静态文件必加路径 */
    public static final String STATIC_FILE_DIR = "/static";

    /** 文件上传路径 */
    public static final String FILE_UPLOAD_DIR = "/upload";

    /** IM100002=笔待初审提现等待处理！ */
    public final static String IM100002 = "IM100002";

    /** IM100003=笔待复审提现等待处理！ */
    public final static String IM100003 = "IM100003";

    /** IM100004=加减币提现 */
    public final static String IM100004 = "IM100004";

    /** CM100001=必须输入 */
    public final static String CM100001 = "CM100001";

    /** CM100002=必须选择 */
    public final static String CM100002 = "CM100002";

    /** CM100003=必须大于0 */
    public final static String CM100003 = "CM100003";

    /** CM100004=必须是数字 */
    public final static String CM100004 = "CM100004";

    /** CM100005=选择值异常 */
    public final static String CM100005 = "CM100005";

    /** CM100006=必须上传 */
    public final static String CM100006 = "CM100006";

    /** CM100007=格式不正确 */
    public final static String CM100007 = "CM100007";

    /** CM100008=必须是0或者正整数 */
    public final static String CM100008 = "CM100008";

    /** CM100009=数据已被修改，请刷新页面查看最新数据！ */
    public final static String CM100009 = "CM100009";

    /** CM100010=Excel导出数据超出最大记录数（6W行），如果继续导出则最大导出前6W行，是否继续导出？ */
    public final static String CM100010 = "CM100010";

    /** CM100011=Excel导出失败！ */
    public final static String CM100011 = "CM100011";

    /** CM100012=该数据已经被删除或不存在！ */
    public final static String CM100012 = "CM100012";

    /** CM100013=参数异常（ID），请刷新或重新操作！ */
    public final static String CM100013 = "CM100013";

    /** CM100014=新增数据失败！ */
    public final static String CM100014 = "CM100014";

    /** CM100015=更新数据失败！ */
    public final static String CM100015 = "CM100015";

    /**内容过长*/
    public final static String CM_CONTENT_TOO_LONG = "CM100016";

    /**删除失败*/
    public final static String CM_DELETE_FAILED = "CM100017";

    /** IM100016=笔追加保证金待审核等待处理！ */
    public final static String IM100016 = "IM100016";

    /** IM100017=笔盈利提取待审核等待处理！ */
    public final static String IM100017 = "IM100017";

    /** IM100018=笔待平仓等待处理！ */
    public final static String IM100018 = "IM100018";

    /** IM100019=笔待清算等待处理！ */
    public final static String IM100019 = "IM100019";

    /** IM100020=笔即将到期等待处理！ */
    public final static String IM100020 = "IM100020";

    /** IM100021=笔待开户等待处理！ */
    public final static String IM100021 = "IM100021";

    /** IM100022=笔待审核等待处理！ */
    public final static String IM100022 = "IM100022";

    /**反馈未处理*/
    public final static String IM_OPINION_UNTREATED = "IM100023";

    /** EM400001=请选择文件后上传 */
    public final static String FILE_NULL_MSG = "EM400001";

    /** EM400002=文件上传失败，请重试 */
    public final static String FILE_UPLOAD_NG_MSG = "EM400002";

    /**排序值有误*/
    public static final String EM_ORDER_NUM_INVALID = "EM100019";

    /**排序操作失败*/
    public static final String EM_ORDER_FAIL = "EM100020";

    /** 重定向 */
    public final static String REDIRECT = "redirect:";

    /** 请求参数：省份 */
    public final static String REQ_PARAM_PROVINCE = "province";

    /** 请求参数：城市 */
    public final static String REQ_PARAM_CITY = "city";

    /** 请求参数：区县 */
    public final static String REQ_PARAM_COUNTY = "county";

    /** 请求参数：当前页码 */
    public final static String REQ_PARAM_CURR_PAGE = "currPage";

    /** 请求参数：每页显示条数 */
    public final static String  REQ_PARAM_LIMIT = "limit";

    /** 请求参数：开始时间 */
    public final static String REQ_PARAM_START_DATE = "startDate";

    /** 请求参数：结束时间 */
    public final static String REQ_PARAM_END_DATE = "endDate";

    /** 请求参数：过期开始时间 */
    public final static String REQ_PARAM_START_DATE_FINISH = "startDateFinish";

    /** 请求参数：过期结束时间 */
    public final static String REQ_PARAM_END_DATE_FINISH = "endDateFinish";

    /** 请求参数：支付开始时间 */
    public final static String REQ_PARAM_PAY_START_DATE = "payStartDate";

    /** 请求参数：支付结束时间 */
    public final static String REQ_PARAM_PAY_END_DATE = "payEndDate";

    /** 请求参数：查询参数 */
    public final static String REQ_PARAM_SEARCH_PARAM = "searchParam";

    /** 请求参数：查询产品名 */
    public final static String REQ_PARAM_PRODUCT_NAME = "productName";

    /** 请求参数：查询类型 */
    public final static String REQ_PARAM_SEARCH_TYPE = "searchType";

    /** 请求参数：更新时间 */
    public final static String REQ_PARAM_UPDATE_TIME = "updateTime";

    /** 请求参数：数据字典标识 */
    public final static String REQ_PARAM_DATA_FIELD_ID = "dataFieldId";

    /** Excel最大记录数 */
    public final static long EXCEL_MAX_COUNT = 60000;

    /** 默认每页显示条数 */
    public final static int LIMIT_COUNT = 15;

    /** 默认页数*/
    public final static String PN = "1";

    /** SQL参数：limitFrom */
    public final static String SQL_PARAM_LIMIT_FROM = "limitFrom";

    /** SQL参数：limitTo */
    public final static String SQL_PARAM_LIMIT_TO = "limitTo";

    /** 返回JSON对象属性：处理状态 */
    public final static String JK_STATUS = "status";

    /** 返回JSON对象属性：错误信息 */
    public final static String JK_ERROR_MSG = "errorMsg";

    /** 返回JSON对象属性：查看模式 */
    public final static String JK_VIEW_MODE = "viewmode";

    /** 返回JSON对象属性：详细信息 */
    public final static String JK_DETAIL = "detail";

    /** 返回JSON对象属性：管理员信息 */
    public final static String JK_ADMIN = "admin";

    /** 返回JSON对象属性：权限列表 */
    public final static String JK_AUTHS = "auths";

    /** 返回JSON对象属性：银行卡列表 */
    public final static String JK_BANKS = "banks";

    /** 加密算法：MD5 */
    public final static String ALGORITHM_MD5 = "MD5";

    /** 英文符号：问号 */
    public final static String STR_ASK = "?";

    /** 英文符号：等号 */
    public final static String STR_EQUAL = "=";

    /** 英文符号：与 */
    public final static String STR_AND = "&";

    /** 英文符号：冒号 */
    public final static String STR_COMMA = ":";

    /** 英文符号：逗号 */
    public final static String STR_COLON = ",";

    /** 英文符号：点 */
    public final static String STR_DOC = ".";

    /** HTTP参数：接受 */
    public final static String ACCEPT = "Accept";

    /** HTTP参数：接受内容 */
    public final static String ACCEPT_CONTENT = "*/*";

    /** HTTP参数：链接 */
    public final static String CONNECTION = "Connection";

    /** HTTP参数：链接内容 */
    public final static String CONNECTION_CONTENT = "Keep-Alive";

    /** HTTP参数：用户代理 */
    public final static String USER_AGENT = "User-Agent";

    /** HTTP参数：用户代理内容 */
    public final static String USER_AGENT_CONTENT = "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)";

    /** 字符集GBK */
    public final static String CHARSET_GBK = "GBK";

    /** 字符集UTF-8 */
    public final static String CHARSET_UTF8 = "UTF-8";

    /** 字符集ISO-8859-1 */
    public final static String CHARSET_ISO_8859_1 = "ISO-8859-1";

    /** DOUBLE数字100 */
    public final static double DOUBLE_A_HUNDRED = 100;

    /** Double数字-1 */
    public final static double DOUBLE_NEG_ONE = -1;

    /** 数字0 */
    public final static double DOUBLE_ZERO = 0;

    /** 数字-1 */
    public final static long LONG_NEG_ONE = -1;

    /** 数字0 */
    public final static long LONG_ZERO = 0;

    /** 数字-1 */
    public final static int NUM_NEG_ONE = -1;

    /** 数字-2 */
    public final static int NUM_NEG_TWO = -2;

    /** 数字0 */
    public final static int NUM_ZERO = 0;

    /** 数字1 */
    public final static int NUM_ONE = 1;

    /** 数字2 */
    public final static int NUM_TWO = 2;

    /** 数字3 */
    public final static int NUM_THREE = 3;

    /** 数字4 */
    public final static int NUM_FOUR = 4;

    /** 数字5 */
    public final static int NUM_FIVE = 5;

    /** 数字6 */
    public final static int NUM_SIX = 6;

    /** 数字7 */
    public final static int NUM_SEVEN = 7;

    /** 数字8 */
    public final static int NUM_EIGHT = 8;

    /** 数字9 */
    public final static int NUM_NINE = 9;

    /** 数字10 */
    public final static int NUM_TEN = 10;

    /** 数字15 */
    public final static int NUM_15 = 15;

    /** 数字18 */
    public final static int NUM_18 = 18;
    /** 数字18 */
    public final static int NUM_20 = 20;

    /** 数字100 */
    public final static int NUM_A_HUNDRED = 100;

    /** 一个自然月天数 */
    public final static int MONTH_DAYS = 30;

    /** 字符串换行符 */
    public final static String BREAK_LINE = "\n";

    /** 字符串-1 */
    public final static String STR_NEG_ONE = "-1";

    /** 字符串1 */
    public final static String STR_ONE = "1";

    /** 字符串0 */
    public final static String STR_ZERO = "0";

    /** 空字符串 */
    public final static String NOTHING = "";

    /** 错误信息资源文件 */
    public final static String ERROR_MSG_FILE = "messages_cn_ZH";

    /** 属性资源文件 */
    public final static String PROPERTY_FILE = "property";

    /** 前台内容的数据格式 */
    public final static String CONTENT_TYPE = "text/plain; charset=UTF-8";

    /** 布尔true */
    public final static Boolean BOOL_TRUE = true;

    /** 布尔false */
    public final static Boolean BOOL_FALSE = false;

    /** 手机号码正则校验字符串 */
    public final static String PHONE_REG = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /** QQ号码正则校验字符串 */
    public final static String QQ_REG = "[1-9][0-9]{4,14}";

    /** 0或正整数正则校验字符串 */
    public final static String POSITIVE_INTEGER_REG = "^(0|[1-9]\\d*)$";

    /** 风控Json字符串 */
    public final static String RISK_JSON = "risk_json";

    /** 时间格式化 */
    public final static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式化 */
    public final static String DATE_FORMAT = "yyyy-MM-dd";

    /** 接口返回JSON KEY：results */
    public final static String JK_RESULTS = "results";

    /** 接口返回JSON KEY：records */
    public final static String JK_RECORDS = "records";

    /** 接口返回JSON KEY：errcode */
    public final static String JK_ERRCODE = "errcode";

    /** 接口返回JSON KEY：errmsg */
    public final static String JK_ERR_MSG = "errmsg";

    /** 接口返回JSON KEY：total */
    public final static String JK_TOTAL = "total";

    /** accessToken */
    public static final String ACCESS_TOKEN = "accessToken";

    /** EXCEL导出头部字段 */
    public static final String EXCEL_FIELD = "field";

    /** EXCEL导出头部字段名称 */
    public static final String EXCEL_FIELD_NAME = "name";

    /** 方法前缀get */
    public static final String METHOD_GET = "get";

    /** Excel导出文件夹 */
    public static final String EXCEL_EXPORT_DIR = "download" + File.separator
                                                  + "excel" + File.separator;

    /**排序*/
    public static final String REQ_PARAM_ORDER_NUM = "orderNum";

    public static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0");

    public static final String REQ_PARAM_REAL_NAME = "realName";

    public static final String REQ_PARAM_UID = "uid";
    
    public static final String REQ_PARAM_PHONE="phone";

    public static final String REQ_SUCCESS = "success";
    
    //host_role 2018-1-17
    public static final String HOST_ROLE_INVESTOR="INVESTOR";
    public static final String HOST_ROLE_BORROWER="BORROWER";
    public static final String HOST_ROLE_ASSURER="ASSURER";
    public static final String HOST_ROLE_DEALER="DEALER";
    //identity_type
    public static final String IDENTITY_TYPE="UID";
    public static final String SET_NAME_TRUE="set_name^True";

}
