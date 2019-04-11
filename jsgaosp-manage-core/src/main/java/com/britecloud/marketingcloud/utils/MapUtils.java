package com.britecloud.marketingcloud.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtils {

    public static Map<String, Object> transToLowerCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();

        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }

        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toLowerCase();
//            newKey = newKey.replace("_", "");

            resultMap.put(newKey, orgMap.get(key));
        }

        return resultMap;
    }
}
