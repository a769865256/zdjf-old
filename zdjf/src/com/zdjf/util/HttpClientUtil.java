package com.zdjf.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	private static final String UTF_8 = "UTF-8";
	private static final String TO_ENCODING = "UTF-8";

	private static final Logger LOGGER = Logger.getLogger(HttpClientUtil.class);
	
	public static String post(String url, Map<String, Object> parameterMap) {
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
//          EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
	
	public static String post(String url, Map<String, String> headers, Map<String, Object> parameterMap) {
        String result = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            if (parameterMap != null) {
                for (Entry<String, Object> entry : parameterMap.entrySet()) {
                    String name = entry.getKey();
                    String value = ConvertUtils.convert(entry.getValue());
                    if (StringUtils.isNotEmpty(name)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    }
                }
            }
            
            if (headers != null) {  
                Set<String> keys = headers.keySet();  
                for (Iterator<String> i = keys.iterator(); i.hasNext();) {  
                    String key = (String) i.next();  
                    httpPost.addHeader(key, headers.get(key));  
                }  
            }  
            //httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            if(!nameValuePairs.isEmpty()){
                HttpEntity entity = new StringEntity(nameValuePairs.get(0).getValue(), "UTF-8");
                httpPost.setEntity(entity);
            }
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity,"UTF-8");
//          EntityUtils.consume(httpEntity);
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return result;
    }
	
	public static String sendMessage(String url, Object bodyParams) {
		CloseableHttpClient httpclient = null;
		HttpPost httpPost = null;
		CloseableHttpResponse closeableHttpResponse = null;
		try {
			httpclient = HttpClients.createDefault();
			httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(bodyParams.toString(),TO_ENCODING);
			httpPost.setEntity(entity);
			httpPost.setHeader(new BasicHeader("SOAPAction", "\"\""));
			httpPost.setConfig(getConfig());
			closeableHttpResponse = httpclient.execute(httpPost);
			HttpEntity httpEntity = closeableHttpResponse.getEntity();
			return EntityUtils.toString(httpEntity, TO_ENCODING);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			try {
				if (closeableHttpResponse != null) {
					closeableHttpResponse.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		return null;
	}

	private static RequestConfig getConfig() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(5000).setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000).build();
		return requestConfig;
	}
}
