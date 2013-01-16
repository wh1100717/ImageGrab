/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.grab;

import com.imageGrab.common.Const;
import com.imageGrab.utils.JsonUtil;
import com.imageGrab.utils.NetUtil;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eric
 */
public class HuabanGrab {

    public static void grabPetOldPhoto(Object lastPinId, int totalCount) throws IOException {
        String pageUrl = Const.huaBanPetOldPageUrl;
        for (int index = 0; index < totalCount; index++) {
            pageUrl = pageUrl + lastPinId;
            String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.10 (KHTML, like Gecko) Chrome/23.0.1262.0 Safari/537.10";
            String pageContent = NetUtil.getPageSourceByHTTPClient(pageUrl, "huaban.com", "http://huaban.com/", userAgent);
            List pins = (List) ((Map) JsonUtil.getMap4Json(pageContent)).get("pins");
            int counter = 1;
            for (int i = 0; i < pins.size(); i++) {
                Map pin = (Map) pins.get(i);
                String key = (String) ((Map) pin.get("file")).get("key");
                key = "http://img.hb.aicdn.com/" + key;
                Object pinId = pin.get("pin_id");
                lastPinId = pinId;
                System.out.println(pinId + " | " + counter + " : " + key);
                counter++;
            }
        }
    }

    public static void grabPetNewPhoto(Object lastPinId) throws IOException {
        String pageUrl = "";
        int counter = 1;
        while (true) {
            if (counter > 10000) {
                break;
            }
            pageUrl = Const.huaBanPetNewPageUrl + lastPinId;
            String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.10 (KHTML, like Gecko) Chrome/23.0.1262.0 Safari/537.10";
            String pageContent = NetUtil.getPageSourceByHTTPClient(pageUrl, "huaban.com", "http://huaban.com/", userAgent);
            List pins = (List) ((Map) JsonUtil.getMap4Json(pageContent)).get("pins");
            if (pins.isEmpty()) {
                break;
            }
            for (int i = 0; i < pins.size(); i++) {
                Map pin = (Map) pins.get(i);
                String key = (String) ((Map) pin.get("file")).get("key");
                key = "http://img.hb.aicdn.com/" + key;
                Object pinId = pin.get("pin_id");
                lastPinId = pinId;
                System.out.println(pinId + " | " + counter + " : " + key);
                counter++;
            }
        }
    }

    public static void grabBeautyOldPhoto(Object lastPinId, int totalCount) throws IOException {
        String pageUrl = Const.huaBanBeautyOldPageUrl;
        for (int index = 0; index < totalCount; index++) {
            pageUrl = pageUrl + lastPinId;
            String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.10 (KHTML, like Gecko) Chrome/23.0.1262.0 Safari/537.10";
            String pageContent = NetUtil.getPageSourceByHTTPClient(pageUrl, "huaban.com", "http://huaban.com/", userAgent);
            List pins = (List) ((Map) JsonUtil.getMap4Json(pageContent)).get("pins");
            int counter = 1;
            for (int i = 0; i < pins.size(); i++) {
                Map pin = (Map) pins.get(i);
                String key = (String) ((Map) pin.get("file")).get("key");
                key = "http://img.hb.aicdn.com/" + key;
                Object pinId = pin.get("pin_id");
                lastPinId = pinId;
                System.out.println(pinId + " | " + counter + " : " + key);
                counter++;
            }
        }
    }

    public static void grabBeautyNewPhoto(Object lastPinId) throws IOException {
        String pageUrl = "";
        int counter = 1;
        while (true) {
            if (counter > 10000) {
                break;
            }
            pageUrl = Const.huaBanBeautyNewPageUrl + lastPinId;
            String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.10 (KHTML, like Gecko) Chrome/23.0.1262.0 Safari/537.10";
            String pageContent = NetUtil.getPageSourceByHTTPClient(pageUrl, "huaban.com", "http://huaban.com/", userAgent);
            List pins = (List) ((Map) JsonUtil.getMap4Json(pageContent)).get("pins");
            if (pins.isEmpty()) {
                break;
            }
            for (int i = 0; i < pins.size(); i++) {
                Map pin = (Map) pins.get(i);
                String key = (String) ((Map) pin.get("file")).get("key");
                key = "http://img.hb.aicdn.com/" + key;
                Object pinId = pin.get("pin_id");
                lastPinId = pinId;
                System.out.println(pinId + " | " + counter + " : " + key);
                counter++;
            }
        }
    }
}
