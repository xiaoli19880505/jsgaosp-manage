/**
 *
 */
package com.britecloud.marketingcloud.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author Barrie
 */
public class Const {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    public static final String PATH_SEPARATOR = File.separator;


    public static String getServerPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//        path = "/" + path.substring(1, path.indexOf("/" + "classes")) + "/";
        
        //本地测试
        path =  path.substring(1, path.indexOf("/" + "WEB-INF")) + "/";
        
        return path;
    }

    public static String getServerUrl(HttpServletRequest request) {
        String path = request.getContextPath() + "/";
        int port = request.getServerPort();
        String basePath = null;
        if (80 == port) {
            basePath = request.getScheme() + "://" + request.getServerName() + path;
        } else {
            basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        }

        return basePath;
    }

}
