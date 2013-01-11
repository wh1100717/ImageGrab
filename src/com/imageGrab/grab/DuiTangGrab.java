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
public class DuiTangGrab {

    public static void grabPhoto(int pageNumber) throws IOException {
        String pageUrl = Const.duiTangPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);
        List blogs = (List) ((Map) ((Map) JsonUtil.getMap4Json(pageContent)).get("data")).get("blogs");
        for (int j = 0; j < blogs.size(); j++) {
            Map picInfo = (Map) blogs.get(j);
            String picUrl = (String) picInfo.get("isrc");
            String msg = (String) picInfo.get("msg");
            picUrl = picUrl.replace(".thumb.200_0.", ".");
            System.out.println("第" + j + "张：" + msg);
            System.out.println(picUrl);
        }
    }
}
