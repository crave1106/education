package com.crave.edu.util;

import com.crave.edu.http.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HttpUtil {

    private static final Logger logger = Logger.getLogger(HttpUtil.class);

    private static CloseableHttpClient client = null;

    private static RequestConfig config = null;

    /**
     * 获取真实IP地址
     */
    public static String getRealIp(HttpServletRequest request) {
        String ipAddress;
        //ipAddress = this.getRequest().getRemoteAddr();
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ipAddress = inet.getHostAddress();
                } else {
                    ipAddress = "127.0.0.1";
                }
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static byte[] xmlPost(String url, String xml) throws IOException {
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entityParams = new StringEntity(xml, "utf-8");
            httpPost.setEntity(entityParams);
            httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
            client = new SSLClient();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                return EntityUtils.toByteArray(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
            if (client != null) {
                client.close();
            }
        }
        return null;
    }



    public static CloseableHttpClient getConnection() {
        if (client == null) {
            HttpClientBuilder httpBuilder = HttpClients.custom();
            client = httpBuilder.build();
            RequestConfig.Builder builder = RequestConfig.custom();
            builder.setConnectTimeout(5000);
            builder.setSocketTimeout(5000);
            builder.setConnectionRequestTimeout(5000);
            config = builder.build();
        }
        return client;
    }

    public static String doGet(String url) {
        if (!url.startsWith("http")) {
            url = "http://" + url;
        }
        HttpGet get = new HttpGet(url);
        HttpClient client = getConnection();
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String message = EntityUtils.toString(entity, "utf-8");
                //System.out.println(message);
                return message;
            } else {
                System.out.println("请求失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return null;
    }
}
