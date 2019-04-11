package com.britecloud.marketingcloud.console.util;

/**
 * 字符串工具类
 */
public class HuStringUtils {

    /**
     * 空值替换
     *
     * @param object
     * @return
     */
    public static String nvl(Object object) {
        String str = "";
        if(object == null){
            return "";
        }else {
            str = object.toString();
        }

        if (str != null && (!str.equals("")) && (!str.toUpperCase().equals("NULL"))) {
            return str;
        } else {
            return "";
        }
    }

    /**
     * 空
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object){
        if(object == null){
            return true;
        }
        if("".equals(object.toString()) || "".equals(object.toString().trim())){
            return true;
        }
        return false;
    }

    /**
     * 非空
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object){
        return !isEmpty(object);
    }

}