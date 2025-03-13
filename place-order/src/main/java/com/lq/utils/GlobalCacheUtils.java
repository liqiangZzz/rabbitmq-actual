package com.lq.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.utils
 * @className GlobalCacheUtils
 * @description: 全局缓存 不考虑安全
 * @author: liqiang
 * @create: 2023-08-18 15:03
 **/
public class GlobalCacheUtils {

    private static Map<String, Object> globalCacheMap = new HashMap<>();


    public static void put(String key, Object value) {
        globalCacheMap.put(key, value);
    }

    public static Object get(String key) {
        return globalCacheMap.get(key);
    }

    public static void remove(String key) {
        globalCacheMap.remove(key);
    }
}
