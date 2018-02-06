package com.comm.util;

import com.comm.exception.DESException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public final class DESUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static SecretKeyFactory keyFactory;

    public static String encrypt(String orig, String desKey) throws DESException {
        String encryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec deskey = new DESKeySpec(desKey.getBytes());
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            encryptedData = new sun.misc.BASE64Encoder().encode(cipher.doFinal(orig.getBytes("UTF-8")));
            //加密后移除换行符
            encryptedData = removeLineFeedChar(encryptedData);
        } catch (Exception e) {
            log.error("加密错误，错误信息：", e);
            throw new DESException("加密错误，错误信息：", e);
        }
        return encryptedData;
    }

    public static String decrypt(String orig, String desKey) throws DESException {
        //解密前移除换行符
        orig = removeLineFeedChar(orig);
        String decryptedData = null;
        try {
            // DES算法要求有一个可信任的随机数源
            SecureRandom sr = new SecureRandom();
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            DESKeySpec deskey = new DESKeySpec(desKey.getBytes());
            SecretKey key = keyFactory.generateSecret(deskey);
            // 加密对象
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key, sr);
            // 加密，并把字节数组编码成字符串
            decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(orig)),"UTF-8");
        } catch (Exception e) {
            log.error("解密错误，错误信息：", e);
            throw new DESException("解密错误", e);
        }
        return decryptedData;
    }

    private static String removeLineFeedChar(String orig) {
        return orig.replaceAll("\n|\r|\t","");
    }

    public static void main(String[] args) throws DESException, UnsupportedEncodingException {
        String key = "domestore";

//        System.out.println("--------------------------------------------------------------------------");
//        String decryptStr = decrypt(encryptStr, key);
//        System.out.println(decryptStr);
//        System.out.println("--------------------------------------------------------------------------");
//        String sss = "uKsfwJVOv7Flj2Xxc+PHnBD2wHQuD9xIrFJ4pe1841fNOX9acx9sTq9FpnF2LJvat9bvghG+h1U0aXYzguMo4Q==";
//        String decryptStr2 = decrypt(sss, key);
//        System.out.println(decryptStr2);
        /*String s = ApiConnector.postBody("http://192.168.130.132:8088/posts/detail", encrypt("{\"id\":8}", key));
        JSONObject jsonObject = JSONObject.parseObject(s);
        String decrypt = decrypt(jsonObject.getString("data"), key);
        JSONObject data = JSONObject.parseObject(decrypt);
        byte[] contentBytes = data.getBytes("contentByte");
        String content = new String(contentBytes,"utf-8");
        System.out.println(content);

        ByteArrayInputStream bin = new ByteArrayInputStream(s.getBytes());*/

       /* String s = ApiConnector.postBody("http://localhost/posts/detail", "{\"id\":8}");
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        byte[] contentBytes = data.getBytes("contentByte");
        String content = new String(contentBytes,"utf-8");
        System.out.println(content);*/

//        FtpUtil.deleteTmpDir();
    }

}
