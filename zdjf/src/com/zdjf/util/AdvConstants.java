/*
 * AdvConstants.java
 * Copyright by sxcf
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年9月10日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.util;

import java.math.BigDecimal;

public final class AdvConstants {

    /** 站点：1、web；2、ios；3、android； */
    public final static String FN_WEB_SITE = "站点";

    /** 存放位置：1、首页； */
    public final static String FN_POSITION = "存放位置";

    /** 标题 */
    public final static String FN_TITLE = "标题";

    /** 图片alt属性 */
    public final static String FN_ALT = "图片alt属性";

    /** 图片存放地址 */
    public final static String FN_IMAGE_URL = "图片";

    /** 图片超链接 */
    public final static String FN_HREF_URL = "图片超链接";

    /** 序号 */
    public final static String FN_ORDER_NUMBER = "图片排序";

    /** 是否显示：1、显示；2、不显示 */
    public final static String FN_SHOW = "是否显示";

    /** 广告位ID */
    public final static String FIELD_ADVERTISE_ID = "advertiseId";

    /** 站点：1、web；2、ios；3、android； */
    public final static String FIELD_WEB_SITE = "webSite";

    /** 存放位置：1、首页； */
    public final static String FIELD_POSITION = "position";

    /** 标题 */
    public final static String FIELD_TITLE = "title";

    /** 图片alt属性 */
    public final static String FIELD_ALT = "alt";

    /** 图片存放地址 */
    public final static String FIELD_IMAGE_URL = "imageUrl";

    /** 图片超链接 */
    public final static String FIELD_HREF_URL = "hrefUrl";

    /** 序号 */
    public final static String FIELD_ORDER_NUMBER = "orderNumber";
    
    /**排序*/
    public static final BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0");

    /** 是否显示：1、显示；2、不显示 */
    public final static String FIELD_SHOW = "show";

    /** 请求参数：站点 */
    public final static String REQ_PARAM_WEB_SITE = "webSite";

    /** 请求参数：存放位置 */
    public final static String REQ_PARAM_POSITION = "position";

    /** 请求参数：是否显示 */ 
    public final static String REQ_PARAM_SHOW = "show";

    /** 请求参数：是否显示 */
    public final static String REQ_PARAM_ID = "id";

    /** 请求参数：广告位ID */
    public final static String REQ_PARAM_ADVERTISE_ID = "advertiseId";

    /** 字段标识：站点 */
    public final static String DATA_FIELD_WEB_SITE = "advertise_web_site";

    /** 字段标识：存放位置 */
    public final static String DATA_FIELD_POSITION = "advertise_position";

    /** 字段标识：是否显示 */
    public final static String DATA_FIELD_SHOW = "advertise_is_show";
}
