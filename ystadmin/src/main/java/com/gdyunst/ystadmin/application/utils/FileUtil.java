package com.gdyunst.ystadmin.application.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
/**
 * 文件工具
 * @author: dengshuai
 * @date: Created in 11:29 2018/1/22
 * @modified: by autor in 11:29 2018/1/22
 */
public class FileUtil {

    /**
     * 文件写入
     * @author: dengshuai
     * @date: Created in 11:28 2018/1/22
     * @modified: by autor in 11:28 2018/1/22
     * @param inputStream 输入流
     * @param rootPath    根路径
     * @param fileName    文件名
     * @return
     */
    public static String fileWrite(InputStream inputStream, String rootPath, String fileName) {

        String path = null;
        OutputStream output = null;
        if (inputStream == null){
            return null;
        }
        // 文件写入;
        try {
            // 创建上传之后的文件路径;
            File temp = new File(rootPath, fileName);
            //文件或目录是否存在
            if (!temp.exists()) {
                temp.getParentFile().mkdirs();
            }
            temp.createNewFile();
            output = new FileOutputStream(temp);
            int byteRead = 0;
            // 1M;
            byte[] buffer = new byte[1024];
            while ((byteRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, byteRead);
            }
            output.flush();
            path = temp.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (StringUtils.isNotBlank(path)){
            path = path.replaceAll("\\\\","/");
        }
        return path;
    }

    /**
     * base64字符串转输入流
     *
     * @param base64Str
     * @return
     */
    public static InputStream base64ToInputString(String base64Str) {
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes = decoder.decodeBuffer(base64Str);
            stream = new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return stream;
        }
    }

    /**
     * 输入流转base64字符串
     * @param inputStream
     * @return
     */
    public static String inputToBase64(InputStream inputStream) {
        String str = null;
        try {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
            BASE64Encoder encoder = new BASE64Encoder();
            str = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return str;
        }

    }
}
