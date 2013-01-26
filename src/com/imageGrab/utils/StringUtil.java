/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class StringUtil {

    public static String decodeUnicode(String dataStr) {
        int start = 0;
        int end = 0;
        StringBuffer buffer = new StringBuffer();
        
        while(start > -1){
            end = dataStr.indexOf("\\u", start);
            if(end == -1){
                buffer.append(dataStr.substring(start,dataStr.length()));
                start = end;
            }else{
                buffer.append(dataStr.substring(start, end));
                String charStr = dataStr.substring(end + 2, end + 6);
                char letter = (char)Integer.parseInt(charStr,16);
                buffer.append(new Character(letter).toString());
                start = end + 6;
            }
        }
        return buffer.toString();
    }
        /**
     * 生成随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";   //生成字符串从此序列中取
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
