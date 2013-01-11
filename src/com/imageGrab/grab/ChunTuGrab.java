/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.grab;

import com.imageGrab.common.Const;
import com.imageGrab.utils.NetUtil;
import java.io.IOException;

/**
 *
 * @author Eric
 */
public class ChunTuGrab {
    public static void grabMeiziPhoto(int pageNumber) throws IOException{
        String pageUrl = Const.chunTuMeiziPageUrl + pageNumber;
        String pageContent = NetUtil.getPageSource(pageUrl);
        System.out.println(pageContent);
    }
}
