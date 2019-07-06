package com.netease.ssm.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil {

    private DesUtil(){}

    /**
     * 获取des加密结果  结果为16进制格式
     * @param data
     * @param key
     * @return
     */
    public static String getDesEncrypt(String data, String key) throws Exception{
        byte[] keyBytes = key.getBytes();
        DESKeySpec keySpec=new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
        SecretKey secretKey=keyFactory.generateSecret(keySpec);

        //des加密  加密模式: CBC  填充方式: PKCS5Padding
        Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(keyBytes));
        byte[] result=cipher.doFinal(data.getBytes());

        return byteToHexString(result);
    }

    /**
     * 获取des解密结果
     * @param data
     * @param key
     * @return
     */
    public static String getDesDecrypt(String data, String key) throws Exception{
        byte[] dataBytes = parseHexStr2Byte(data);
        byte[] keyBytes = key.getBytes();

        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
        SecretKey secretKey=keyFactory.generateSecret(keySpec);

        Cipher cipher=Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(keyBytes));
        byte[] result=cipher.doFinal(dataBytes);

        return new String(result);
    }

    /**
     * 将byte数组转为16进制字符串
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2){
                sb.append(0);
            }
            sb.append(sTemp.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转byte数组
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    public static void main(String[] args){
        //加密内容
        String content = "test";
        //加密密钥
        String key = "12345678";
        try {
            //加密
            String encResult = getDesEncrypt(content, key);
            //解密
            String decResult = getDesDecrypt(encResult, key);
            System.out.println(decResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}