package com.britecloud.marketingcloud.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
