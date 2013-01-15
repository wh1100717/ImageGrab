/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

/**
 *
 * @author Eric
 */
public class RegexUtil {
//    public static final String danHuaRegex = "(data-original=\"[^>]*.(gif|jpg))";
    public static final String woXiHuanPhotoRegex = "src='([^']*)'";
    public static final String woXiHuanTitleRegex = "cellTit\">([^/]*)</span>";
    public static final String duiTangRegex = "";
    public static final String s91MeiTuRegex = "(filename\":\"[^,]*.(gif|jpg))";
    public static final String huaBanRegex = "(key\":\"[^\"]*)";
    public static final String renRenRegex = "(key\":\"[^\"]*)";
    public static final String chunTuMeiziRegex = "data-original=\"([^\"]*)\"";
    public static final String chunTuMeinvRegex = "<img src=\"([^\"]*)\"";
    public static final String chunTuTaotuRegex = "<h3 class=\"feed-title\"><a target=\"_blank\" href=\"([^\"]*)\"";
    public static final String chunTuContentRegex = "alt=\"([^\"]*)\"";
}
