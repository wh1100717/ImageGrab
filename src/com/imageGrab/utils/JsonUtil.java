/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

import org.stringtree.json.JSONReader;

/**
 *
 * @author Eric
 */
public class JsonUtil {

    public static Object getMap4Json(String jsonString) {
        JSONReader reader = new JSONReader();
        Object obj = reader.read(jsonString);
        return obj;
    }
}
