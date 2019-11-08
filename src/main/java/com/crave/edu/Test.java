package com.crave.edu;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    public static final String[] names = new String[]{
            "赵雅薇  ",
            "刘  怡 ",
            "杨雨薇",
            "陈  泽",
            "王安厦",
            "张  璐",
            "阎  婧 ",
            "何粤萌 ",
            "曹  越  ",
            "周卓斌 ",
            "陈  悦",
            "陈静宜",
            "张一默",
            "张婧琦",
            "张楚晗 ",
            "王宇翀 ",
            "孙  阳  ",
            "张  玮 ",
            "张翔杰",
            "刘  奇",
            "吴  超",
            "鹿  璐",
            "朱  莹 ",
            "范哲涵 ",
            "陈  雅  ",
            "原  帅 ",
            "任一菲",
            "王伟嘉",
            "张  宇",
            "王彦玢",
            "薛嗣圣 ",
            "陶  然 ",
            "赵  萌  ",
            "卢宇佳 ",
            "高聪儒",
            "王静书",
            "胡书婷",
            "王  妍",
            "原  音 ",
            "高树勋 ",
            "李  峥  ",
            "吴霄萌 ",
            "刘  斐",
            "郭子木",
            "武毅鹏",
            "王  艳",
            "张喜佳 ",
            "王奕达 ",
            "申  宁  ",
            "申  婷 ",
            "马晓璇",
            "王  旭",
            "成思思",
            "丁守怡",
            "侯志鹏",
            "秦  俊 ",
            "穆  洁  ",
            "王  婕 ",
            "智安然",
            "阎  淳",
            "田  钊",
            "任新宇",
            "王轶哲 ",
            "安  敏 ",
            "段  璇  ",
            "韩婷婷 ",
            "赵晓春",
            "李希晶",
            "张佳雯",
            "刘叶妮",
            "刘  美 ",
            "苏  华 ",
            "吴  凡  ",
            "渠沛然 ",
            "刘  瑶",
            "李晰月",
            "郭  莹",
            "段文杰",
            "边  婧 ",
            "刘雯雯 ",
            "崔  静  ",
            "杨  阳 ",
            "李  晨",
            "李畅翔",
            "兰  坤",
            "毛博阳",
            "甄  锐 ",
            "许  远 ",
            "卞珂心  ",
            "霍荪蕙 ",
            "钟  博",
            "许  静",
            "牛子儒",
            "赵  彤",
            "赵韵洁 ",
            "王  晓 ",
            "庞  辰  ",
            "张一鹏 ",
            "王  鹏",
            "李  苒",
            "梁卯星",
            "张一宇",
            "郭  蕊 ",
            "刘  鑫 ",
            "智颖君  ",
            "李  祎 ",
            "张贵扬",
            "赵慧萍",
            "叶雨辰",
            "高  放",
            "连  晨",
            "杨  婧 ",
            "韩  琨  ",
            "张  鹏 ",
            "韩  琨",
            "王一男",
            "徐乙旗",
            "苏  烜",
            "武  月 ",
            "高  峰 ",
            "马小雪  ",
            "田  均 ",
            "郭瑞峰",
            "梁  卉",
            "蔡  婕",
            "王  宁",
            "田  婧 ",
            "张  璐 ",
            "张  婧  ",
            "梁  婷 ",
            "毕云洁",
            "晋  扬",
            "侯  博",
            "贾  然",
            "牛琳琳 ",
            "白  杨 ",
            "张宏红  ",
            "刘  静 ",
            "殷泽耀",
            "周文彬",
            "李昊阳",
            "蒋  毅",
            "杨  熙 ",
            "薛莉凡 ",
            "张鹏九  ",
            "贾  奇 ",
            "曹晓霞",
            "王  月",
            "宁智超",
            "杨晓磊",
            "李汉华 ",
            "高  琼 ",
            "李杨林子",
            "包辰兰",
            "张雯",
            "翟丽丽",
            "方琦璐",
            "薛松",
            "宋春莹",
            "吴慧聪",
            "唐亚琼",
            "袁欣然",
            "张玥",
            "常冬",
            "孟晨",
            "杨博",
            "杜晨",
            "杨柳",
            "孙中雯",
            "李雅淑",
            "周维钰",
            "王桐",
            "王瑶",
            "周磊",
            "王丽",
            "边玥",
            "柳笛",
            "高红爱",
            "韩坤琦",
            "石秋雨",
            "李欢",
            "邱世明",
            "范庆瑶",
            "赵学良",
            "李梦真",
            "房立航",
            "李雪纯",
            "陈雪",
            "王硕",
            "高剑",
            "吕雨璇",
            "朱言蹊",
            "周琰",
            "程铖",
            "袁田",
            "林泽",
            "李杨",
            "张好",
            "赵丹",
            "马珺洁",
            "朱晓檬",
            "邢丹丹",
            "鲁雯",
            "马晓钰",
            "韩爽",
            "张宁",
            "李杨",
            "张泓玮",
            "李瑾博",
            "陈萌",
            "王虎",
            "段妍",
            "白璐",
            "张捷",
            "贾祥",
            "赵宇宁",
            "林雪涛",
            "王晨",
            "石涛",
            "杨晓林",
            "赵佳莹",
            "程莹",
            "侯頔",
            "魏萍",
            "吕可",
            "邓爱丽",
            "范鹿",
            "任婵",
            "邢成龙",
            "郭文岳",
            "李皓",
            "韩汶彤",
            "刘旭",
            "郝楠",
            "赵磊",
            "赵莹",
            "郑欣",
            "李金淼",
            "宗泽",
            "杨卉",
            "王鹏",
            "张昕烨",
            "王炜烨",
            "孙颖",
            "马玉龙",
            "黄璐思",
            "张逸菲",
            "李思伟",
            "贾晨",
            "康璐玮",
            "曹雪",
            "彭悦",
            "杨镕溶",
            "王萌",
            "乔木",
            "王媛",
            "张强",
            "冷子欣",
            "孙健",
            "杨帆",
            "曲阳",
            "刘婷",
            "颜婧",
            "李菲"
    };

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url="http://wx.wei-ben.com/2019/Jul/practice_write/api/practice_write.php?a=applyInfoAdd";
        for (int i=0;i<100;i++){
            run(url, i);
        }
    }

    public static void run(String url,int i){
        new Thread("test"+i){
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    String j = "0000";
                    if (i < 10){
                        j = "000"+i+"";
                    }else if (i < 100){
                        j = "00"+i+"";
                    } else if (i < 1000){
                        j = "0"+i+"";
                    }else if(i < 10000){
                        j = i+"";
                    }


                    String phone = "1523342"+j;
                    String param = "name="+URLEncoder.encode(names[new Random().nextInt(245) + 1])+"&phone="+phone+"&school="+URLEncoder.encode("永田实验小学")+"&age=111&campus="+URLEncoder.encode("知音人钢琴培训艺术中心(盛龙路万象天成店)")+"&campus_id=460&lev="+URLEncoder.encode("一级");
                    String data=doPost(url, param);
                    //请求回来的数据
                    System.out.println(unicodeDecode(data)+"，数量："+i+"，代理名："+getName());
                }
            }
        }.start();

    }

    /**
     * @Title: unicodeEncode
     * @Description: unicode编码
     * @param string
     * @return
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * @Title: unicodeDecode
     * @Description: unicode解码
     * @return
     */
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

    public static String doPost(String httpUrl, String param) {

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
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            connection.setRequestProperty("Cookie", " name=test; sex=0; country=%11111; openid=oeCpj5jkQXBh1Kv6epb12312312312; imgURL=1111; PHPSESSID=a57npt1istsvikspj7hc8nhaf5");
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
