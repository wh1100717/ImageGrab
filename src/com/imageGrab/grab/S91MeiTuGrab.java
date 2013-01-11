/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.grab;

import com.imageGrab.common.Const;
import com.imageGrab.utils.NetUtil;
import com.imageGrab.utils.RegexUtil;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class S91MeiTuGrab {

    public static void grabPhoto(int pageNumber) throws IOException {

        String pageUrl = Const.s91MeiTuPageUrl + pageNumber + "0";
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.10 (KHTML, like Gecko) Chrome/23.0.1262.0 Safari/537.10";
        String pageContent = NetUtil.getPageSourceByHTTPClient(pageUrl, "www.91meitu.net", "http://www.91meitu.net/", userAgent);

        Pattern pattern = Pattern.compile(RegexUtil.s91MeiTuRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            String imgUrl = matcher.group(1);
            imgUrl = imgUrl.replace("filename\":\"","http://meitu91.b0.upaiyun.com/");
            System.out.println("第" + counter + "张：");
            System.out.println(imgUrl);
            counter++;
        }
    }
}
