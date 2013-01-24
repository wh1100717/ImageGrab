/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.controller;

import com.imageGrab.utils.FileUtil;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Eric
 */
public class ImageFilter {

    public static void widthFilter(String folderPath, int minWidth) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            return;
        }
        String destFolderPath = folder + "lessWidth";
        File[] files = folder.listFiles();
        for (int index = 0; index < files.length; index++) {
            File file = files[index];
            Boolean result = false;
            try {
                result = FileUtil.checkWidthLimit(file.getAbsolutePath(), minWidth);
            } catch (Exception e) {
                result = false;
            }
            if (!result) {
                FileUtil.move(file, destFolderPath);
                System.out.println("Moving " + index + "File");
            }
        }
        System.out.println("Width Filter Finish!");
    }
}
