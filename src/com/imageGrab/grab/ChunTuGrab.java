/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.grab;

import com.imageGrab.common.Const;
import com.imageGrab.utils.NetUtil;
import com.imageGrab.utils.RegexUtil;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eric
 */
public class ChunTuGrab {

    public static List<Map> grabMeiziPhoto(int pageNumber) throws IOException, ParseException {
        List<Map> result = new ArrayList<Map>();
        SimpleDateFormat dateConvert = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pageUrl = Const.chunTuMeiziPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);

        Pattern pattern = Pattern.compile(RegexUtil.chunTuMeiziRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            Map photo = new HashMap();
            photo.put("description", matcher.group(1));
            photo.put("id", matcher.group(2));
            photo.put("date", dateConvert.parse(matcher.group(3)));
            photo.put("imgUrl", matcher.group(4));
            result.add(photo);
            counter++;
        }
        return result;
    }

    public static List<Map> grabMeinvPhoto(int pageNumber) throws IOException, ParseException {
        List<Map> result = new ArrayList<Map>(0);
        SimpleDateFormat dateConvert = new SimpleDateFormat("yyyy年MM月dd日");

        String pageUrl = Const.chunTuMeinvPageUrl + pageNumber;
        String pageExtUrl = Const.chunTuMeinvExtPageUrl + pageNumber;
        Map paras = new HashMap();
        paras.put("p", "1");

        String pageContent = NetUtil.getPageSource(pageUrl);
        pageContent += NetUtil.getPageSourceByHTTPClientPost(pageExtUrl, "www.chuntu.cc", pageUrl, null, paras);
        pageContent = pageContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");

        Pattern pattern = Pattern.compile(RegexUtil.chunTuMeinvRegex);
        Matcher matcher = pattern.matcher(pageContent);
        int counter = 1;
        while (matcher.find()) {
            Map photo = new HashMap();
            photo.put("id", matcher.group(1));
            photo.put("imgUrl", matcher.group(2).replace("200", "600"));
            photo.put("date", dateConvert.parse("2013年" + matcher.group(3)));
            result.add(photo);
            counter++;
        }
        return result;
    }

    public static List<Map> grabTuwenPhoto(int pageNumber) throws IOException, ParseException {
        List<Map> result = new ArrayList<Map>(0);
        SimpleDateFormat dateConvert = new SimpleDateFormat("yyyy年MM月dd日");

        String pageUrl = Const.chunTuTuwenPageUrl + pageNumber;
        String pageExtUrl = Const.chunTuTuwenExtPageUrl + pageNumber;
        Map paras = new HashMap();
        paras.put("p", "1");

        String pageContent = NetUtil.getPageSource(pageUrl);
        pageContent += NetUtil.getPageSourceByHTTPClientPost(pageExtUrl, "www.chuntu.cc", pageUrl, null, paras);
        pageContent = pageContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");

        Pattern pattern = Pattern.compile(RegexUtil.chunTuTuwenRegex);
        Matcher matcher = pattern.matcher(pageContent);

        int counter = 1;
        while (matcher.find()) {
            Map photo = new HashMap();
            photo.put("id", matcher.group(1));
            photo.put("imgUrl", matcher.group(2).replace("200", "600"));
            photo.put("description", matcher.group(3));
            photo.put("date", dateConvert.parse("2013年" + matcher.group(4)));
            result.add(photo);
            counter++;
        }
        return result;
    }

    public static List grabTaoTuIds(int pageNumber) throws IOException {
        String pageUrl = Const.chunTuTaotuPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);
        Pattern pattern = Pattern.compile(RegexUtil.chunTuTaotuRegex);
        Matcher matcher = pattern.matcher(pageContent);
        List taotuIds = new ArrayList();
        while (matcher.find()) {
            taotuIds.add(matcher.group(1));
        }
        return taotuIds;
    }

    public static List<String> grabTaoTuPhotosById(int id) throws IOException {
        List result = new ArrayList();
        String pageUrl = Const.chunTuTaotuDetailPageUrl + id;
        String pageContent = NetUtil.getPageSource(pageUrl);
        Pattern pattern = Pattern.compile("data-original=\"(.*?)\"");
        Matcher matcher = pattern.matcher(pageContent);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
