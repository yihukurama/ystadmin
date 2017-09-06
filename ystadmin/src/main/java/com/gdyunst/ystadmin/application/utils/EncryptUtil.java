package com.gdyunst.ystadmin.application.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 功能描述:加密工具类 
 * @Author:denghsuai
 * @Date:2017年9月1日 上午10:31:46
 */
public class EncryptUtil {

    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

        // 返回形式为数字跟字符串
        private static String byteToArrayString(byte bByte) {
            int iRet = bByte;
            if (iRet < 0) {
                iRet += 256;
            }
            int iD1 = iRet / 16;
            int iD2 = iRet % 16;
            return strDigits[iD1] + strDigits[iD2];
        }

            // 返回形式只为数字
            private static String byteToNum(byte bByte) {
                int iRet = bByte;
                System.out.println("iRet1=" + iRet);
                if (iRet < 0) {
                    iRet += 256;
                }
                return String.valueOf(iRet);
            }

            // 转换字节数组为16进制字串
            private static String byteToString(byte[] bByte) {
                StringBuffer sBuffer = new StringBuffer();
                for (int i = 0; i < bByte.length; i++) {
                    sBuffer.append(byteToArrayString(bByte[i]));
                }
                return sBuffer.toString();
            }

            public static String GetMD5Code(String strObj) {
                String resultString = null;
                try {
                    resultString = new String(strObj);
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    // md.digest() 该函数返回值为存放哈希值结果的byte数组
                    resultString = byteToString(md.digest(strObj.getBytes()));
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                }
                return resultString;
            }
            
        /**
         * 功能描述:MD5生成长度为16位字符的随机串;
         * @return 16位长度的随机串
         * @Author:liujun
         * @Date:2017年2月28日 下午4:15:46
         */
        public static String gen16BitMD5String()
        {
            String sourceString = UUID.randomUUID().toString().replaceAll("-", "");   // 原字符串;
            
            try 
            {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(sourceString.getBytes());
                byte b[] = messageDigest.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++)
                {
                    i = b[offset];
                    if (i < 0)
                    {
                        i += 256;
                    }
                    if (i < 16)
                    {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                
                return buf.toString().substring(8, 24);
            } 
            catch (NoSuchAlgorithmException e) 
            {
                return "";
            }
        }
}
