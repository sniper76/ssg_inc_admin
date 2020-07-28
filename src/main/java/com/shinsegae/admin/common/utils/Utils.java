package com.shinsegae.admin.common.utils;

import java.security.MessageDigest;
import java.util.UUID;

public class Utils {

   
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

  
    public static int parseInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defValue;
        }
    }

  
    public static double parseDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defValue;
        }
    }

   
    public static Long parseLong(String str, Long defValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defValue;
        }
    }

  
    public static boolean strIsNull(String str) {
        return ((str == null) || "".equals(str));
    }


    public static String trim(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    public static String trim(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return trim(obj.toString());
        }
    }


    public static String md5(String str) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }

}
