/*
 * RequestUtils.java
 * Copyright by ddy
 * Description：
 * Modified By：Administrator
 * Modified Time：2015年7月30日
 * Modified Number：
 * Modified Contents：
 */

package com.zdjf.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RequestUtils {

    /** 日志管理 */
    private static final Logger logger = LoggerFactory.getLogger(
        RequestUtils.class);

    /**
     * Description: 服务器真实路径<br>
     * 
     * @param req
     * @param dir
     * @return String
     */
    public static String serverRealPath(HttpServletRequest req, String dir) {
        return req.getSession().getServletContext().getRealPath(dir);
    }

//    /**
//     * Description: 返回是否是真实服务器<br>
//     * 
//     * @return boolean
//     */
//    public static boolean isRealServer() {
//        // 获取服务器域名
//        String serverName = PropertyUtils.getInstance().getValue(
//           "");
//        // 前端地址
//        String real = PropertyUtils.getInstance().getValue(
//            "");
//        return serverName.contains(real);
//    }

    /**
     * Description: 获取服务器地址<br>
     * 
     * @param req
     * @return String
     */
    public static String serverPath(HttpServletRequest req) {
        // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:
        return req.getScheme() + "://" + req.getServerName() + ":"
               + req.getServerPort() + req.getContextPath() + "/";
    }

//    /**
//     * Description: 返回请求URL+AccessToken<br>
//     * 
//     * @param key
//     * @param accessToken
//     * @return String
//     */
//    public static String accessTokenUrl(String key, String accessToken) {
//        if (!StrUtils.emptyJudge(accessToken)) {
//            return PropertyUtils.getInstance().getString(key);
//        }
//        return PropertyUtils.getInstance().getString(key) + Constants.STR_ASK
//               + Constants.ACCESS_TOKEN + Constants.STR_EQUAL + accessToken;
//    }

    /**
     * Description: 发送Post请求<br>
     * 
     * @param url 请求链接
     * @param jsonStr 请求参数Json字符串
     * @return String 返回内容Json字符串
     */
    public static String postJson(String url, String jsonStr) {
        CloseableHttpClient client = null;
        String rtnJson = Constants.NOTHING;
        try {
            // POST请求对象
            HttpPost post = new HttpPost(url);
            // 设置请求和传输超时时间
            RequestConfig config = RequestConfig.custom().setSocketTimeout(
                2000).setConnectTimeout(2000).build();
            // 配置设定
            post.setConfig(config);
            // 设定参数并转换字符集
            post.setEntity(new StringEntity(jsonStr, Constants.CHARSET_UTF8));
            // 实例化HttpClient
            client = HttpClients.createDefault();
            // 执行请求
            HttpResponse response = client.execute(post);
            // 获得返回的HttpEntity对象
            HttpEntity entity = response.getEntity();
            // 异常判断
            if (entity != null) {
                // 获得返回Json字符串
                rtnJson = EntityUtils.toString(entity, Constants.CHARSET_UTF8);
            }
            post.releaseConnection();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            closeClient(client);
        }
        return rtnJson;
    }

//    /**
//     * Description: Json参数生成<br>
//     * 
//     * @param jsonStr
//     * @return List<? extends NameValuePair>
//     */
//    public static List<? extends NameValuePair> createParams(String jsonStr) {
//        // 先转换为Map类型
//        Map<String, String> map = new HashMap<String, String>(
//            Constants.NUM_TEN);
//        // Gson对象实例化
//        Gson gson = new Gson();
//        // 返回类型指定
//        Type type = new TypeToken<HashMap<String, String>>() {}.getType();
//        // 转换Json
//        try {
//            map = gson.fromJson(jsonStr, type);
//        } catch (JsonSyntaxException e) {
//            logger.error(
//                MsgUtils.getInstance().getMsg(FinancialConstants.EM200006)
//                         + jsonStr);
//        }
//        // 参数集合定义并实例化
//        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(
//            map.size());
//        // 遍历map
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            params.add(
//                new BasicNameValuePair(entry.getKey(), entry.getValue()));
//        }
//        // 返回转换结果
//        return params;
//    }

    /**
     * Description: 关闭HttpClient<br>
     * 
     * @param client
     */
    private static void closeClient(CloseableHttpClient client) {
        try {
            // 参数异常判断
            if (null != client) {
                // 关闭HttpClient
                client.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Description:初始化返回JSON <br>
     * 
     * @return Map<String, Object> 
     * @see
     */
    public static Map<String, Object> initBackJson() {
        // 构造返回JsonMap
        Map<String, Object> json = new HashMap<String, Object>(
            Constants.NUM_TEN);
        // 处理状态
        json.put(Constants.JK_STATUS, Constants.STR_ONE);
        // 错误信息
        json.put(Constants.JK_ERROR_MSG, Constants.NOTHING);
        // 详细信息
        json.put(Constants.JK_DETAIL, null);
        // 返回JsonMap
        return json;
    }

    /**
     * Description: 取得请求中的参数<br>
     * 
     * @param request
     * @param key
     * @return String
     * @see
     */
    public static String getReqString(HttpServletRequest request, String key) {
        // 入参异常判断
        if (request == null || !StrUtils.emptyJudge(key)) {
            return Constants.NOTHING;
        }
        // 取得请求中的值
        String val = request.getParameter(key);
        // 对值进行异常判断
        if (!StrUtils.emptyJudge(val)) {
            return Constants.NOTHING;
        }
        return val;
    }

    /**
     * Description: 取得请求中的参数并判断是否转换字符集<br>
     * 
     * @param request
     * @param key
     * @param needConv
     * @return String
     * @see
     */
    public static String getReqString(HttpServletRequest request, String key,
                                      boolean needConv) {
        // 入参异常判断
        if (request == null || !StrUtils.emptyJudge(key)) {
            return Constants.NOTHING;
        }
        // 取得请求中的值
        String val = request.getParameter(key);
        // 对值进行异常判断
        if (!StrUtils.emptyJudge(val)) {
            return Constants.NOTHING;
        }
        // 是否需要转换UTF-8
        if (!needConv) {
            return val;
        }
        String utf8Str = Constants.NOTHING;
        try {
            utf8Str = URLDecoder.decode(val, Constants.CHARSET_UTF8);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        return utf8Str;
    }

    /**
     * Description: 取得请求中的参数数组<br>
     * 
     * @param request
     * @param key
     * @return String
     * @see
     */
    public static String[] getReqArray(HttpServletRequest request, String key) {
        String[] vals = new String[] {};
        // 入参异常判断
        if (request == null || !StrUtils.emptyJudge(key)) {
            return vals;
        }
        // 取得请求中的值
        vals = request.getParameterValues(key);
        // 对值进行异常判断
        if (null == vals || vals.length == Constants.NUM_ZERO) {
            return new String[]{};
        }
        return vals;
    }


    /**
     * 获取用户真实IP（在使用nginx转发的情况下使用此方法获取真实IP）
     * @param request
     * @return
    */
    public static String getIp(HttpServletRequest request) {
        String forwards = request.getHeader("x-forwarded-for");
        if (!StrUtils.emptyJudge(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("Proxy-Client-IP");
        }
        if (!StrUtils.emptyJudge(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!StrUtils.emptyJudge(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getRemoteAddr();
        }
        if (!StrUtils.emptyJudge(forwards) || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("X-Real-IP");
        }
        if (forwards != null && forwards.trim().length() > 0) {
            int index = forwards.indexOf(',');
            return (index != -1) ? forwards.substring(0, index) : forwards;
        }
        return forwards;
//          return request.getHeader("X-Real-IP");
    }
}
