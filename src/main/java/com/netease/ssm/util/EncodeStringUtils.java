package com.netease.ssm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bjzhangxicheng on 2018/5/28.
 */
public class EncodeStringUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncodeStringUtils.class);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密⽂文转换成⼗十六进制的字符串串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    public static String sha1(String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(source.getBytes());
            return getFormattedText(digest.digest());
        } catch (NoSuchAlgorithmException ex) {
            logger.error("", ex);
        }
        return "";
    }

    public static void main(String[] args) {
        String appKey = "043adf601e7a48ca9816deaa345aa4e2";
        String appSecret = "56b340e0a048d543281fa3a41cbe3d7b7dc6b9e3";
        System.out.println(sha1(appSecret+Card.genRandomNum(128)+System.currentTimeMillis()));
    }
}
