package com.lvqiang.springcloud.practice.server.utils;

import java.util.Random;

public class KeyUtil {
    public static synchronized String genUniqueKey() {
        //UUID uuid = UUID.randomUUID();
        //String strUUID = uuid.toString();
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
