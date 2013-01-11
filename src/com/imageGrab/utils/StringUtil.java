/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

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
}
