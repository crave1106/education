package com.crave.edu.util;

import com.crave.edu.http.SSLClient;
import org.apache.commons.lang3.StringUtils;
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
import java.io.*;
import java.net.*;
import java.util.Map;

public class HttpUtil {
    public static final String POST = "POST";
    public static final String GET = "GET";

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

    public static String doPost(String httpUrl, String param, Map<String,String> requestProperty) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            if (null != requestProperty){
                for(String key : requestProperty.keySet()){
                    connection.setRequestProperty(key, requestProperty.get(key));
                }
            }
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
}
