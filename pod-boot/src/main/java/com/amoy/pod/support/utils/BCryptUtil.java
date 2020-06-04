package com.amoy.pod.support.utils;

import com.amoy.pod.support.utils.bcrypt.BCrypt;

/**
 * @description:
 * @author hongjiacan
 * @date 2020/6/2
 */
public class BCryptUtil {

    public static String encrypt(String origin) {

        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    public static Boolean check(String origin, String encrypt){

        return BCrypt.checkpw(origin, encrypt);
    }
}
