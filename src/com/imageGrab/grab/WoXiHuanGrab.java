/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.grab;

import com.imageGrab.common.Const;
import com.imageGrab.utils.JsonUtil;
import com.imageGrab.utils.NetUtil;
import com.imageGrab.utils.RegexUtil;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class WoXiHuanGrab {

    public static void grabPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.woXiHuanPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);

        String body = (String) ((Map) JsonUtil.getMap4Json(pageContent)).get("body");

        Pattern photoPattern = Pattern.compile(RegexUtil.woXiHuanPhotoRegex);
        Pattern contentPattern = Pattern.compile(RegexUtil.woXiHuanTitleRegex);
        Matcher photoMatch = photoPattern.matcher(body);
        Matcher contentMatch = contentPattern.matcher(body);
        int counter = 1;
        while (photoMatch.find()) {
            String imgUrl = photoMatch.group(1);
            String msg = "";
            if (contentMatch.find()) {
                msg = contentMatch.group(1);
            }
            imgUrl = imgUrl.replace("196_", "680_");
            System.out.println("第" + counter + "张：" + msg);
            System.out.println(imgUrl);
            counter++;
        }
    }
}
