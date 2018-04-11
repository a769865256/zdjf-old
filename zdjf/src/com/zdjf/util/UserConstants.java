/*
 * UserConstans.java
 * Copyright by ddy
 * Description：
 * Modified By：xiaowen
 * Modified Time：2015年8月2日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.util;

/**
 *  会员常量类
 * 
 * @author xiaowen
 * @version 2015年8月2日
 * @see UserConstants
 * @since
 */
public class UserConstants {

    /** 活动红包类型代码 */
    public final static Integer ACTIVITY_GIFT_TYPE_CODE = 9;

    /** 活动加息券类型代码 */
    public final static Integer ACTIVITY_COUPON_TYPE_CODE = 5;

    /** EM400001=参数“会员ID”必须传入 */
    public final static String EM400001 = "EM400001";

    /** 请求参数：会员ID */
    public final static String REQ_PARAM_UID = "uid";

    /** 请求参数：真实姓名 */
    public final static String REQ_PARAM_REAL_NAME = "realName";

    /** 请求参数：身份证号码 */
    public final static String REQ_PARAM_IDCARD_NO = "idcardNo";

    /** 请求参数：性别 */
    public final static String REQ_PARAM_GENDER = "gender";

    /** 会员信息接口URL */
    public final static String ASS_USER_SEARCH = "ass_user_search";

    /** 会员详情接口URL */
    public static final String ASS_USER_DETAIL = "ass_user_detail";

    /** 实名认证接口URL */
    public static final String ASS_VERIFY = "ass_verify";

    /** 会员详情接口URL参数 */
    public static final String ASS_USER_DETAIL_PARAM = "{uid}";

    /** EM200001=解析Json结果异常->Json格式不正确： */
    public final static String EM200001 = "EM200001";

    /** EM200002=解析Json结果异常->未包含errorcode：*/
    public final static String EM200002 = "EM200002";

    /** EM200003=解析Json结果异常->errorcode不为0： */
    public final static String EM200003 = "EM200003";

    /** EM200004=解析Json结果异常->未包含results： */
    public final static String EM200004 = "EM200004";

    /** EM200005=解析Json结果异常->未包含total： */
    public final static String EM200005 = "EM200005";

    /** EM200006=解析Json结果异常->转换数组异常 */
    public final static String EM200006 = "EM200006";

    /** EM700001=参数异常（用户ID），请刷新界面后重新操作！ */
    public final static String EM700001 = "EM700001";

    /** EM700002=状态值选择不合法，请重新操作！ */
    public final static String EM700002 = "EM700002";

    /** EM700003=用户数据已被更新，请刷新界面后重新操作！ */
    public final static String EM700003 = "EM700003";

    /** EM700004=是否参加注册送礼活动值选择不合法，请重新操作！ */
    public final static String EM700004 = "EM700004";

    /** EM700005=回访是否有效值选择不合法，请重新操作！ */
    public final static String EM700005 = "EM700005";

    /** EM700006=所属公司值选择不合法，请重新操作！ */
    public final static String EM700006 = "EM700006";

    /** EM700007=实名认证失败！ */
    public final static String EM700007 = "EM700007";

    /** EM700008=实名认证失败，（真实姓名）异常！ */
    public final static String EM700008 = "EM700008";

    /** EM700009=实名认证失败，（身份证号码）异常！ */
    public final static String EM700009 = "EM700009";

    /** EM700010=实名认证失败，（银行代码）异常！ */
    public final static String EM700010 = "EM700010";

    /** EM700011=实名认证失败，（银行卡号）异常！ */
    public final static String EM700011 = "EM700011";

    /** EM700012=实名认证失败，更新实名认证表失败！ */
    public final static String EM700012 = "EM700012";

    /** EM700013=实名认证失败，更新实名认证字段失败！ */
    public final static String EM700013 = "EM700013";

    /** EM700014=实名认证失败，更新绑定状态失败！ */
    public final static String EM700014 = "EM700014";

    /** EM700015=删除实名认证记录失败！ */
    public final static String EM700015 = "EM700015";

    /** EM700016=更新用户实名认证状态失败！ */
    public final static String EM700016 = "EM700016";

    /** EM700017=删除用户银行卡记录失败！ */
    public final static String EM700017 = "EM700017";

    /** EM700018=更新实名认证备注失败！ */
    public final static String EM700018 = "EM700018";

    /**条数设定*/
    public final static String PAGE_SIZE = "page_size";

    /**当前页*/
    public final static String PAGE = "page";

    /**请求参数中page*/
    public final static String USER_PAGE = "user_page";

    /** 投资明细page */
    public final static String INVEST_PAGE = "invest_page";

    /** 资金流水page */
    public final static String FUNDS_PAGE = "funds_page";

    /** 银行卡page */
    public final static String BANK_PAGE = "bank_page";

    /**接口请求参数*/
    public static final String[] USER_SEARCH_PARAMS = {"realName", "phone"};

    /**接口返回JSON KEY:user*/
    public static final String JK_USER = "user";

    /**SQL参数：status*/
    public static final String SQL_PARAM_STATUS = "status";

    /**订单已支付状态：2*/
    public static final Integer RECORD_PAID = 1;

    /** SQL参数：limitFrom */
    public final static String REQ_PARAM_LIMIT = "limit";

    /** 请求参数：认证开始时间 */
    public final static String REQ_PARAM_AUTH_START_DATE = "authStartDate";

    /** 请求参数：认证结束时间 */
    public final static String REQ_PARAM_AUTH_END_DATE = "authEndDate";

    /** 请求参数：用户状态列表 */
    public final static String REQ_PARAM_USER_STATUS = "userStatus";

    /** 请求参数：用户类型 */
    public final static String REQ_PARAM_USER_TYPE = "userType";

    /** 请求参数：用户备注 */
    public final static String REQ_PARAM_USER_REMARK = "remark";

    /** 请求参数：回访是否有效 */
    public final static String REQ_PARAM_CALLBACK_VALID = "callbackValid";

    /** 请求参数：所属公司 */
    public final static String REQ_PARAM_INSIDE_COMP = "insideComp";

    /** 请求参数：是否参加注册送礼活动 */
    public final static String REQ_PARAM_REGIST_LOTTERY = "registLottery";

    /** 字段标识：用户状态 */
    public final static String DATA_FIELD_USER_LOGIN_STATUS = "user_login_status";

    /** 字段标识：用户类型 */
    public final static String DATA_FIELD_USER_TYPE = "user_type";

    /** 字段标识：回访是否有效 */
    public final static String DATA_FIELD_USER_ATTACH_CALLBACK_VALID = "user_attach_callback_valid";

    /** 字段标识：所属公司 */
    public final static String DATA_FIELD_USER_ATTACH_INSIDE_COMP = "user_attach_inside_comp";

    /** 字段标识：是否参加注册送礼活动 */
    public final static String DATA_FIELD_USER_ATTACH_REGIST_LOTTERY = "user_attach_regist_lottery";

    /** 请求参数：短信状态 */
    public final static String REQ_PARAM_SMS_STATUS = "smsStatus";

    /** 请求参数：短信类型 */
    public final static String REQ_PARAM_SMS_TYPE = "smsType";

    /** 字段标识：短信状态 */
    public final static String DATA_FIELD_SMS_STATUS = "sms_status";

    /** 字段标识：短信类型 */
    public final static String DATA_FIELD_SMS_TYPE = "sms_type";

    /** 请求字段：昵称 */
    public final static String FD_NICK_NAME = "nickName";

    /** 请求字段名称：昵称 */
    public final static String FN_NICK_NAME = "昵称";

    /** 请求字段：头像 */
    public final static String FD_HEAD_IMAGE = "headImage";

    /** 请求字段名称：头像 */
    public final static String FN_HEAD_IMAGE = "头像";

    /** 请求参数：转让金额 */
    public final static String REQ_PARAM_MONEY_MIN = "moneyMin";

    /** 请求参数：转让金额 */
    public final static String REQ_PARAM_MONEY_MAX = "moneyMax";

    /** 默认男头像 */
    public static final String[] DEF_HEAD_PHOTO_MAN = {
        "static/user/imgs/01.jpg", "static/user/imgs/02.jpg"};

    /** 默认女头像 */
    public static final String[] DEF_HEAD_PHOTO_WOMAN = {
        "static/user/imgs/03.jpg", "static/user/imgs/04.jpg"};

    /** 请求字段：备注 */
    public final static String BANK_CARD_PARAM_REMARK = "remark";

    /** 请求字段：银行代码 */
    public final static String BANK_CARD_PARAM_BANK_ALIAS = "bankAlias";

    /** 请求字段：银行代码 */
    public final static String BANK_CARD_PARAM_BANK_CODE = "bankCode";

    /** 请求字段：银行卡号 */
    public final static String BANK_CARD_PARAM_BANK_NO = "bankNo";

    /** 请求字段：银行卡号 */
    public final static String BANK_CARD_PARAM_BANK_CARD_NUMBER = "bankCardNumber";

    /** 请求字段： 是否充值*/
    public static final String REQ_PARAM_IS_RECHARGED = "isRecharged";

    /** 请求字段：实名认证申请ID*/
    public static final String REQ_PARAM_APPROVE_ID = "approveId";

    /** 请求字段： 实名认证备注*/
    public static final String REQ_PARAM_APPROVE_REMARK = "remark";

    /** 实名认证： 省份*/
    public static final String APPROVE_PARAM_PROVINCE = "province";

    /** 实名认证： 生日*/
    public static final String APPROVE_PARAM_BIRTHDAY = "birthday";

    /** 实名认证： 地址代码*/
    public static final String APPROVE_PARAM_ADDRESS_CODE = "addressCode";

    /**请求参数：电话*/
    public static final String REQ_PARAM_PHONE = "phone";
}
