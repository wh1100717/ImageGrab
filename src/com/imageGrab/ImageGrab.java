/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab;

import com.imageGrab.common.Const;
import com.imageGrab.grab.ChunTuGrab;
import com.imageGrab.grab.DuiTangGrab;
import com.imageGrab.grab.HuabanGrab;
import com.imageGrab.grab.S91MeiTuGrab;
import com.imageGrab.grab.WoXiHuanGrab;
import com.imageGrab.utils.FileUtil;
import com.imageGrab.utils.JsonUtil;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class ImageGrab {

    static Map<String, Integer> checkSumMap = new HashMap<String, Integer>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // TODO code application logic here
//        getFileCheckSum("F:/360云盘/图片/分类");
//        getFileCheckSum("F:/360云盘/图片/待处理");
        HuabanGrab.grabPetOldPhoto(Const.huaBanPetLastPinId, 5);
    }

    public static void getFileCheckSum(String path) throws NoSuchAlgorithmException, IOException {
        String desPath = path + "_temp";
        File desDirectory = new File(desPath);
        if (!desDirectory.exists()) {
            desDirectory.mkdir();
        }
        File directory = new File(path);
        File[] subDirectories = directory.listFiles();
        int count = 0;
        for (int index = 0; index < subDirectories.length; index++) {
            File subDirectory = subDirectories[index];
            File[] files = subDirectory.listFiles();
            for (int indexF = 0; indexF < files.length; indexF++) {
                File file = files[indexF];
                String md5Hash = String.valueOf(FileUtil.calculateCheckSum(file));
                if (checkSumMap.get(md5Hash) == null) {
                    count++;
                    System.out.println(md5Hash + "count : " + count);
                    checkSumMap.put(md5Hash, 1);
                } else {
                    System.out.println("重复图片，移至" + path + "_temp");
                    FileUtil.move(file, desPath);
                }
            }
        }
    }
}
