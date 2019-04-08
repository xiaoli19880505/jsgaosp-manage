/** 
 * 项目名称:91营销云
 * 文件名：ShortURL.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class ShortURL {
    public static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ_";
    public static final int BASE = ALPHABET.length();
    private static final String URLHOST = "http://dwz.cn/create.php";

    public static String encode(long num) {
        StringBuilder str = new StringBuilder();
        while (num > 0) {
            str.insert(0, ALPHABET.charAt((int) (num % BASE)));
            num = num / BASE;
        }
        return str.toString();
    }

    public static String userEncode(String str) {
        return str;
    }

    public static String userDecode(String str) {
        return str;
    }

    public static long decode(String str) {

        long num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        }
        return num;
    }

    //使用百度的短网址转换服务
    public static String shortenUrl(String longUrl) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost();
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("url", longUrl));
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            post.setURI(new URI(URLHOST));
            CloseableHttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "utf8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // public static void main(String[] args) {
    // String companyId = IDUtils.getId();
    // String compaignId = IDUtils.getId();
    // String userId = "b2eb5eaa92d614be584de7cde84de3c6";
    //
    // String url1 = encode(Long.parseLong(companyId));
    // String url2 = encode(Long.parseLong(compaignId));
    // String url3 = userEncode((userId));
    //
    // String result = url1+"-"+url2+"-"+url3;
    //
    // String base64 = Base64.encode(result.getBytes());
    // System.out.println(result);
    // System.out.println(base64);
    //
    // String raw = new String(Base64.decode(base64) );
    // System.out.println(raw);
    //
    // }
}
