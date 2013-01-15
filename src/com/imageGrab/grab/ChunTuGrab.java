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
public class ChunTuGrab {

    public static void grabMeiziPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.chunTuMeiziPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);

        Pattern pattern = Pattern.compile(RegexUtil.chunTuMeiziRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            String imgUrl = matcher.group(1);
            System.out.println("第" + counter + "张：");
            System.out.println(imgUrl);
            counter++;
        }
    }

    public static void grabMeinvPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.chunTuMeinvPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);

        Pattern pattern = Pattern.compile(RegexUtil.chunTuMeinvRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            String imgUrl = matcher.group(1);
            System.out.println("第" + counter + "张：");
            System.out.println(imgUrl);
            counter++;
        }
    }

    public static void grabTaoTuPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.chunTuTaotuPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);
        Pattern pattern = Pattern.compile(RegexUtil.chunTuTaotuRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            String taoTuUrl = matcher.group(1);
            System.out.println("第" + counter + "套：");
            System.out.println(taoTuUrl);

            String photoContent = NetUtil.getPageSource(taoTuUrl);

            Pattern photoPattern = Pattern.compile(RegexUtil.chunTuMeiziRegex);
            Matcher photoMatcher = photoPattern.matcher(photoContent);
            int counter2 = 1;
            while (photoMatcher.find()) {
                String imgUrl = photoMatcher.group(1);
                System.out.println("第" + counter2 + "张：");
                System.out.println(imgUrl);
                counter2++;
            }
            counter++;
        }
    }

    public static void grabTuwenPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.chunTuTuwenPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);
        Pattern photoPattern = Pattern.compile(RegexUtil.chunTuMeinvRegex);
        Pattern contentPattern = Pattern.compile(RegexUtil.chunTuContentRegex);
        Matcher photoMatcher = photoPattern.matcher(pageContent);
        Matcher contentMatcher = contentPattern.matcher(pageContent);
        int counter = 1;
        while (photoMatcher.find()) {
            String imgUrl = photoMatcher.group(1);
            String msg = "";
            if (contentMatcher.find()) {
                msg = contentMatcher.group(1);
            }
            System.out.println("第" + counter + "张：" + msg);
            System.out.println(imgUrl);
            counter++;
        }
    }
}
