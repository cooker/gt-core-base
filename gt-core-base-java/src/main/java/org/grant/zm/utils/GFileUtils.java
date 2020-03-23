package org.grant.zm.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Paths;
import java.util.Base64;

import static org.grant.zm.base.GConstants.*;

/**
 * ZoomGrant 2020/3/18 17:37
 *
 */
public class GFileUtils {

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if (StringUtils.isNotEmpty(filename)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取文件扩展名，不带 .
     */
    public static String getExtensionName(String filename) {
        if (StringUtils.isNoneEmpty(filename)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     */
    public static String getSize(long size){
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (float) GB) + "GB   ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (float) MB) + "MB   ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (float) KB) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }

    /**
     * inputStream 转 File
     */
    public static File inputStreamToFile(InputStream ins, String fileName) throws IOException{
        File file = Paths.get(fileName).toFile();
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        int len = 8192;
        byte[] buffer = new byte[len];
        while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return file;
    }

    public static String toBase64(File file) throws IOException {
        if (!file.exists()) {
            return "";
        }

        FileInputStream fin = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        fin.read(buffer);
        fin.close();
        return Base64.getEncoder().encodeToString(buffer);
    }



}
