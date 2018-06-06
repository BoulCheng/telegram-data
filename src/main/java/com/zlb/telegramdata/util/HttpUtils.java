package com.zlb.telegramdata.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpUtils {
    private static Integer connectionRequestTimeout = Integer.valueOf(3000);
    private static Integer socketTimeOut = Integer.valueOf(3000);
    private static Integer connectTimeout = Integer.valueOf(3000);


    public static String doPost(String url, Map params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            //创建参数列表
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            post.setEntity(stringEntity);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    post.addHeader(e.getKey(), e.getValue());
                }
            }
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            JSON.toJSONString(httpResponse);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String doPut(String url, Map params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(url);
            //创建参数列表
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpPut.setEntity(stringEntity);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    httpPut.addHeader(e.getKey(), e.getValue());
                }
            }
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
            JSON.toJSONString(httpResponse);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String doDelete(String url, Map params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
            //创建参数列表
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            httpDeleteWithBody.setEntity(stringEntity);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    httpDeleteWithBody.addHeader(e.getKey(), e.getValue());
                }
            }
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpDeleteWithBody);
            JSON.toJSONString(httpResponse);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String doGet(String url, Map<String,String> params, Map<String, String> header) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            StringBuffer sb = new StringBuffer();
            for (Map.Entry<String, String> e : params.entrySet()) {
                if (e.getValue() != null && !"".equals(e.getValue())) {
                    sb.append(e.getKey()).append("=").append(e.getValue()).append("&");
                }
            }
            HttpGet httpGet = new HttpGet(url+"?"+sb);
            //创建参数列表
            StringEntity stringEntity = new StringEntity(JSON.toJSONString(params), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            //httpGet.setEntity(stringEntity);
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> e : header.entrySet()) {
                    httpGet.addHeader(e.getKey(), e.getValue());
                }
            }
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            JSON.toJSONString(httpResponse);
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    return EntityUtils.toString(entity);
                }
            } finally {
                httpResponse.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}