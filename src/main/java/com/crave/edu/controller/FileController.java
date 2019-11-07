package com.crave.edu.controller;

import com.alibaba.fastjson.JSON;
import com.crave.edu.bean.ResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    public final static String UEDITOR_CONFIG = "{\n" +
            "    \"imageActionName\": \"uploadimage\",\n" +
            "    \"imageFieldName\": \"upfile\",\n" +
            "    \"imageMaxSize\": 2048000,\n" +
            "    \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "    \"imageCompressEnable\": true,\n" +
            "    \"imageCompressBorder\": 1600,\n" +
            "    \"imageInsertAlign\": \"none\",\n" +
            "    \"imageUrlPrefix\": \" \",\n" +
            "    \"imagePathFormat\": \"/wechat/file\",\n" +
            "\n" +
            "    \"scrawlActionName\": \"uploadscrawl\",\n" +
            "    \"scrawlFieldName\": \"upfile\",\n" +
            "    \"scrawlPathFormat\": \"/wechat/file\",\n" +
            "    \"scrawlMaxSize\": 2048000,\n" +
            "    \"scrawlUrlPrefix\": \"\",\n" +
            "    \"scrawlInsertAlign\": \"none\",\n" +
            "\n" +
            "    \"snapscreenActionName\": \"uploadimage\",\n" +
            "    \"snapscreenPathFormat\": \"/wechat/file\",\n" +
            "    \"snapscreenUrlPrefix\": \"\",\n" +
            "    \"snapscreenInsertAlign\": \"none\",\n" +
            "\n" +
            "    \"catcherLocalDomain\": [\"127.0.0.1\", \"localhost\", \"img.baidu.com\"],\n" +
            "    \"catcherActionName\": \"catchimage\",\n" +
            "    \"catcherFieldName\": \"source\",\n" +
            "    \"catcherPathFormat\": \"/wechat/file\",\n" +
            "    \"catcherUrlPrefix\": \"\",\n" +
            "    \"catcherMaxSize\": 2048000,\n" +
            "    \"catcherAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "\n" +
            "    \"videoActionName\": \"uploadvideo\",\n" +
            "    \"videoFieldName\": \"upfile\",\n" +
            "    \"videoPathFormat\": \"/wechat/file\",\n" +
            "    \"videoUrlPrefix\": \"\",\n" +
            "    \"videoMaxSize\": 102400000,\n" +
            "    \"videoAllowFiles\": [\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\"],\n" +
            "\n" +
            "    \"fileActionName\": \"uploadfile\",\n" +
            "    \"fileFieldName\": \"upfile\",\n" +
            "    \"filePathFormat\": \"/wechat/file\",\n" +
            "    \"fileUrlPrefix\": \"\",\n" +
            "    \"fileMaxSize\": 51200000,\n" +
            "    \"fileAllowFiles\": [\n" +
            "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
            "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
            "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
            "    ],\n" +
            "\n" +
            "    \"imageManagerActionName\": \"listimage\",\n" +
            "    \"imageManagerListPath\": \"/ueditor/jsp/upload/image/\",\n" +
            "    \"imageManagerListSize\": 20,\n" +
            "    \"imageManagerUrlPrefix\": \"\",\n" +
            "    \"imageManagerInsertAlign\": \"none\",\n" +
            "    \"imageManagerAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"],\n" +
            "\n" +
            "    \"fileManagerActionName\": \"listfile\",\n" +
            "    \"fileManagerListPath\": \"/ueditor/jsp/upload/file/\",\n" +
            "    \"fileManagerUrlPrefix\": \"\",\n" +
            "    \"fileManagerListSize\": 20,\n" +
            "    \"fileManagerAllowFiles\": [\n" +
            "        \".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\",\n" +
            "        \".flv\", \".swf\", \".mkv\", \".avi\", \".rm\", \".rmvb\", \".mpeg\", \".mpg\",\n" +
            "        \".ogg\", \".ogv\", \".mov\", \".wmv\", \".mp4\", \".webm\", \".mp3\", \".wav\", \".mid\",\n" +
            "        \".rar\", \".zip\", \".tar\", \".gz\", \".7z\", \".bz2\", \".cab\", \".iso\",\n" +
            "        \".doc\", \".docx\", \".xls\", \".xlsx\", \".ppt\", \".pptx\", \".pdf\", \".txt\", \".md\", \".xml\"\n" +
            "    ] \n" +
            "\n" +
            "}";

    public static final String  FILE_PATH_QRCODE = "/wechat/qrcode/";

    public static final String  FILE_PATH = "/wechat/file/";

    @PostMapping("/upload")
    @ResponseBody
    public ResponseBean fileUpload(@RequestParam("file")MultipartFile srcFile) {
        //前端没有选择文件，srcFile为空
        if(srcFile.isEmpty()) {
            return ResponseBean.getFail("请选择一个文件");
        }
        //选择了文件，开始上传操作
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:"+destFile.getAbsolutePath());
            //拼接子路径
            SimpleDateFormat sf_ = new SimpleDateFormat("yyyyMMddHHmmss");
            String times = sf_.format(new Date());
            File upload = new File(destFile.getAbsolutePath(), "/static"+FILE_PATH_QRCODE);
            //若目标文件夹不存在，则创建
            if(!upload.exists()) {
                upload.mkdirs();
            }
            System.out.println("完整的上传路径："+upload.getAbsolutePath()+"/"+srcFile);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            // 获得文件原始名称
            String fileName = srcFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            fileName = times + "."+ suffixName;

            Path path = Paths.get(upload.getAbsolutePath()+"/"+fileName);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
            return ResponseBean.getSuccess(FILE_PATH_QRCODE+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseBean.getFail("上传失败");
    }

    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(MultipartFile upfile, HttpServletRequest request, String action) {

        if (!StringUtils.isEmpty(action) && (action.equals("uploadimage") || action.equals("uploadfile") || action.equals("uploadvideo"))){
            Map<String, Object> images = images(upfile);
            return JSON.toJSONString(images);
        }
        return UEDITOR_CONFIG;
    }

    @RequestMapping("/ueupload")
    @ResponseBody
    public Map<String, Object> images (MultipartFile upfile){
        //选择了文件，开始上传操作
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:"+destFile.getAbsolutePath());
            //拼接子路径
            SimpleDateFormat sf_ = new SimpleDateFormat("yyyyMMddHHmmss");
            String times = sf_.format(new Date());
            File upload = new File(destFile.getAbsolutePath(), "/static"+FILE_PATH);
            //若目标文件夹不存在，则创建
            if(!upload.exists()) {
                upload.mkdirs();
            }
            System.out.println("完整的上传路径："+upload.getAbsolutePath()+"/"+upfile);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = upfile.getBytes();
            // 获得文件原始名称
            String fileName = upfile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            fileName = times + "."+ suffixName;
//            String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(""+new Random().nextInt(6)).concat(".").concat(ext);

            Path path = Paths.get(upload.getAbsolutePath()+"/"+fileName);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
            params.put("state", "SUCCESS");
            params.put("url", FILE_PATH+fileName);
            params.put("size", upfile.getSize());
            params.put("original", fileName);
            params.put("type", upfile.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
            params.put("state", "ERROR");
        }
        return params;
    }
}
