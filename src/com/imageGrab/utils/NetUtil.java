/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author Eric
 */
public class NetUtil {

    public static String getPageSource(String pageUrl) throws IOException {
        URL url = new URL(pageUrl);
        URLConnection urlConn = url.openConnection(); // 打开网站链接s
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8")); // 实例化输入流，并获取网页代码
        String s; // 依次循环，至到读的值为空
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        return sb.toString();
    }

    public static String getPageSourceByHTTPClient(String pageUrl, String host, String referer, String userAgent) throws IOException {
        HttpClient client = new HttpClient();
        if (host != null) {
            client.getHostConfiguration().setHost(host, 80, "http");
        }
        GetMethod getMethod = new GetMethod(pageUrl);
        if (referer != null) {
            getMethod.setRequestHeader("Referer", referer);
        }
        if (userAgent != null) {
            getMethod.setRequestHeader("User-Agent", userAgent);
        }
        getMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        getMethod.setRequestHeader("DK_AJAX_REQUEST", "ajax-reqeust");
        getMethod.setRequestHeader("Origin", "http://www.chuntu.cc");
        client.executeMethod(getMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(getMethod.getResponseBodyAsStream(), "UTF-8"));
        String s; // 依次循环，至到读的值为空
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        getMethod.releaseConnection();
        return s;
    }

    public static String getPageSourceByHTTPClientPost(String pageUrl, String host, String referer, String userAgent, Map paras) throws IOException {
        HttpClient client = new HttpClient();
        if (host != null) {
            client.getHostConfiguration().setHost(host, 80, "http");
        }
        PostMethod postMethod = new PostMethod(pageUrl);
        if (paras != null) {
            Set keySet = paras.keySet();
            for (Iterator it = keySet.iterator(); it.hasNext();) {
                String s = (String) it.next();
                postMethod.addParameter(s, (String) paras.get(s));
            }
        }
        if (referer != null) {
            postMethod.setRequestHeader("Referer", referer);
        }
        if (userAgent != null) {
            postMethod.setRequestHeader("User-Agent", userAgent);
        }
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        postMethod.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        client.executeMethod(postMethod);
        BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "UTF-8"));
        String s; // 依次循环，至到读的值为空
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        reader.close();
        postMethod.releaseConnection();
        return s;
    }
}
