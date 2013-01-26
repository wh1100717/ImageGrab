/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imageGrab.utils;

import com.sun.media.jai.codec.SeekableStream;
import java.awt.color.CMMException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;

/**
 *
 * @author Eric
 */
public class FileUtil {

    /**
     * 移动文件
     *
     * @param srcFile 源文件
     * @param destPath 目标文件夹路径
     * @return
     */
    public static boolean move(File srcFile, String destPath) {
        //目标位置所在文件夹
        File dir = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //更名
        boolean success = srcFile.renameTo(new File(dir, srcFile.getName()));
        return success;
    }

    /**
     * 删除文件
     *
     * @param srcFile 要删除的文件
     * @return
     */
    public static boolean deleteFile(File srcFile) {
        if (srcFile.isDirectory()) {
            return false;
        }
        if (!srcFile.exists()) {
            return false;
        }
        srcFile.delete();
        return true;
    }

    /**
     * 删除文件
     *
     * @param srcFilePath 要删除的文件路径
     * @return
     */
    public static boolean deleteFile(String srcFilePath) {
        File srcFile = new File(srcFilePath);
        return deleteFile(srcFile);
    }

    /**
     * 删除目录
     *
     * @param directory 要删除的目录文件
     * @return
     */
    public static boolean deleteDirectory(File directory) {
        if (!directory.isDirectory()) {
            return false;
        }
        if (!directory.exists()) {
            return false;
        }
        File[] fileList = directory.listFiles();
        for (int index = 0; index < fileList.length; index++) {
            File file = fileList[index];
            if (!file.exists()) {
                continue;
            }
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                file.delete();
            }
        }
        return true;
    }

    /**
     * 删除目录
     *
     * @param directoryPath 要删除目录的路径
     * @return
     */
    public static boolean deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        return deleteDirectory(directory);
    }

    /**
     * 移动文件
     *
     * @param srcFilePath 源文件路径
     * @param destPath 目标文件夹路径
     * @return
     */
    public static boolean move(String srcFilePath, String destPath) {
        File srcFile = new File(srcFilePath);
        return move(srcFile, destPath);
    }

    /**
     * 计算文件的Checksum 校验值
     *
     * @param file 文件对象
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static long calculateCheckSum(File file) throws NoSuchAlgorithmException, IOException {
        AbstractChecksum jacksum = JacksumAPI.getChecksumInstance("md5", true);
        return jacksum.readFile(file.getAbsolutePath());
    }

    /**
     * 计算文件的Checksum 校验值
     *
     * @param filePath 文件所在路径
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static long calculateCheckSum(String filePath) throws NoSuchAlgorithmException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return 0;
        }
        return calculateCheckSum(file);
    }

    /**
     * 根据URL路径来下载文件到指定位置
     *
     * @param urlString 文件的URL连接地址
     * @param filePath 指定路径
     * @throws Exception
     */
    public static void download(String urlString, String filePath)
            throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File file = new File(filePath);
        System.out.println(file.getAbsoluteFile());
        OutputStream os = new FileOutputStream(file);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * 查看图片的宽度是否小于widthLimit，如果小于则返回false，如果大于则返回true
     *
     * @param filePath 图片所在路径
     * @param widthLimit 比较图片宽度的值
     * @return true表示图片宽度大于阀值，false表示图片宽度小于阀值
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean checkWidthLimit(String filePath, int widthLimit) throws IOException {
        File file = new File(filePath);
        BufferedImage buff = FileUtil.getImageInfo(file);
        System.out.println(buff.getWidth());// 得到图片的宽度
        int width = buff.getWidth();
        if (width < widthLimit) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 查看图片的宽度是否小于heightLimit，如果小于则返回false，如果大于则返回true
     *
     * @param filePath 图片所在路径
     * @param heightLimit 比较图片高度的值
     * @return true表示图片高度大于阀值，false表示图片高度小于阀值
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean checkHeightLimit(String filePath, int heightLimit) throws IOException {
        File file = new File(filePath);
        BufferedImage buff = FileUtil.getImageInfo(file);

        System.out.println(buff.getWidth());// 得到图片的高度
        int height = buff.getHeight();
        if (height < heightLimit) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 检查图片是否为正方形
     *
     * @param filePath 图片所在路径
     * @return true表示该图片是正方形，false表示该图片不是正方形
     * @throws IOException
     */
    public static boolean checkSquareImage(String filePath) throws IOException {
        File file = new File(filePath);
        BufferedImage buff = FileUtil.getImageInfo(file);
        if (buff.getWidth() == buff.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查图片是否是近似正方形
     *
     * @param filePath 图片所在路径
     * @param nearlyValue 近似程度值
     * @return true表示该图片是近似正方形，false表示该图片不是近似正方形
     * @throws IOException
     */
    public static boolean checkNearlySquareImage(String filePath, float nearlyValue) throws IOException {
        File file = new File(filePath);
        BufferedImage buff = FileUtil.getImageInfo(file);
        float width = buff.getWidth();
        float height = buff.getHeight();
        float s = 0;
        if (width == 0 || height == 0) {
            return false;
        }
        if (height / width < 1 + nearlyValue && width / height < 1 + nearlyValue) {
            return true;
        } else {
            return false;
        }
    }

    public static BufferedImage getImageInfo(File image) throws IOException, IOException {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(image);
        } catch (CMMException ex) {
            InputStream is = new FileInputStream(image);
            bufferedImage = JAI.create("stream", SeekableStream.wrapInputStream(is, true)).getAsBufferedImage();
        }
        return bufferedImage;
    }
}
