package com.britecloud.marketingcloud.utils;

import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class ImgUtil {
	
	 private static Logger log = Logger.getLogger(ImgUtil.class.getName());
	public static String saveImg(String imgBase64) throws IOException {
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        
        
        //服务器地址
        String localPathStr = "/"+Const.getServerPath() + "static/image/" + datePath + "/";
        //本地
//        String localPathStr = Const.getServerPath() + "static/image/" + datePath + "/";
//        String localPathStr = "http://10.254.203.165:18080/znmh_TP/" + "static/image/" + datePath + "/";

        File localPath = new File(localPathStr);
        if (!localPath.exists() && !localPath.isDirectory()) {
            localPath.mkdirs();
        }

        String extension = "jpg";
        String random = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

        String imgName = new Date().getTime() + random + "." + extension;
        log.info("imgName: " + imgName);

        try {
            File file = new File(localPathStr + imgName);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            BASE64Decoder decoder = new BASE64Decoder();

            byte[] b = decoder.decodeBuffer(imgBase64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();

            return "static/image/" + datePath + "/" + imgName;
        } catch (Exception e) {
            return null;
        }
    }
}
