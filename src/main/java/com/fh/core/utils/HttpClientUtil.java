package com.fh.core.utils;



import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    /**
     * put请求
     * @param url
     * @param headMap
     * @param param
     * @return
     */
    public static String sendPut(String url, Map<String,String> headMap,Map<String,String> param) {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //输入网址
        HttpPut httpPut = new HttpPut(url);
        if(headMap!=null && headMap.size()>0){
            //组装头信息
            Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpPut.addHeader(key,value);
            }
        }

        //组装参数信息
        if(param!=null && param.size()>0){
            Gson gson = new Gson();
            String paramJson = gson.toJson(param);
            StringEntity entity = new StringEntity(paramJson, "utf-8");
            entity.setContentType("application/json");
            httpPut.setEntity(entity);
        }
        CloseableHttpResponse response = null;
        String result = "";
        try {
            response = client.execute(httpPut);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response!=null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(httpPut!=null){
                httpPut.releaseConnection();
            }if(client!=null){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    /**
     * get请求
     * @param url
     * @param headMap
     * @param param
     * @return
     */

    public static String sendGet(String url, Map<String,String> headMap,Map<String,String> param) {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //组装参数信息
        if(param!=null && param.size()>0){
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, String>> paramMap = param.entrySet().iterator();
            while(paramMap.hasNext()){
                Map.Entry<String, String> next = paramMap.next();
                String key = next.getKey();
                String value = next.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            /*param.forEach((x,y)->paramList.add(new BasicNameValuePair(x, y)));*/
            try {
                //转换类型
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "utf-8");
                String paramInfo = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                //发送get请求用？传参
                url += "?" + paramInfo;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        //输入网址
        HttpGet httpGet = new HttpGet(url);
        if(headMap!=null && headMap.size()>0){
            //组装头信息
            Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpGet.addHeader(key,value);
            }
            /*headMap.forEach((x,y)->httpGet.addHeader(x,y));*/
        }
        CloseableHttpResponse response = null;
        String result = "";

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response!=null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(httpGet!=null){
                httpGet.releaseConnection();
            }if(client!=null){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    /**
     * post请求
     * @param url
     * @param headMap
     * @param param
     * @return
     */
    public static String sendPost(String url, Map<String,String> headMap,Map<String,String> param) {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //输入网址
        HttpPost httpPost = new HttpPost(url);
        if(headMap!=null && headMap.size()>0){
            //组装头信息
            Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpPost.addHeader(key,value);
            }
            /*headMap.forEach((x,y)->httpPost.addHeader(x,y));*/
        }
        CloseableHttpResponse response = null;
        String result = "";
        //组装参数信息
        if(param!=null && param.size()>0){
            Iterator<Map.Entry<String, String>> paramMap = param.entrySet().iterator();
            List<NameValuePair> paranList = new ArrayList<NameValuePair>();
            while(paramMap.hasNext()){
                Map.Entry<String, String> next = paramMap.next();
                String key = next.getKey();
                String value = next.getValue();
                paranList.add(new BasicNameValuePair(key, value));
            }

            try {
                //转换类型
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paranList, "utf-8");
                httpPost.setEntity(urlEncodedFormEntity);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        }
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response!=null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(httpPost!=null){
                httpPost.releaseConnection();
            }if(client!=null){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    /**
     * 删除
     * @param url
     * @param headMap
     * @return
     */
    public static String sendDelete(String url, Map<String,String> headMap,Map<String,String> param) {
        //打开浏览器
        CloseableHttpClient client = HttpClientBuilder.create().build();
        //组装参数信息
        if(param!=null && param.size()>0){
            Iterator<Map.Entry<String, String>> paramMap = param.entrySet().iterator();
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            while(paramMap.hasNext()){
                Map.Entry<String, String> next = paramMap.next();
                String key = next.getKey();
                String value = next.getValue();
                paramList.add(new BasicNameValuePair(key, value));
            }
            try {
                //转换类型
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(paramList, "utf-8");
                String paramInfo = EntityUtils.toString(urlEncodedFormEntity, "utf-8");
                //发送get请求用？传参
                url += "?" + paramInfo;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        //输入网址
        HttpDelete httpDelete = new HttpDelete(url);
        if(headMap!=null && headMap.size()>0){
            //组装头信息
            Iterator<Map.Entry<String, String>> iterator = headMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                String key = next.getKey();
                String value = next.getValue();
                httpDelete.addHeader(key,value);
            }
        }
        CloseableHttpResponse response = null;
        String result = "";

        try {
            response = client.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response!=null){
                try {
                    response.close();
                    response = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if(httpDelete!=null){
                httpDelete.releaseConnection();
            }if(client!=null){
                try {
                    client.close();
                    client = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }
}
