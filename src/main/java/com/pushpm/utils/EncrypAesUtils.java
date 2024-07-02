package com.pushpm.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class EncrypAesUtils {

    private static String key = "wappush@2021";

    /**
     * 加密字符串解密字符串
     * @param msg
     * @return
     * @throws Exception
     */
    public static String deMsgToStr(String msg) throws Exception {
        return new String(decrypt(Base64.getDecoder().decode(msg), key.getBytes()));
    }

    /**
     * 字符串加密成密文字符
     * @param msg
     * @return
     * @throws Exception
     */
    public static String enMsgToStr(String msg) throws Exception {
        return Base64.getEncoder().encodeToString(encrypt(msg.getBytes(), key.getBytes()));
    }

    /**
     * 生成密钥对象
     */
    private static SecretKey generateKey(byte[] key) throws Exception {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key);
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(128, random);
        // 生成 AES密钥对象, 也可以直接创建密钥对象: return new SecretKeySpec(key, ALGORITHM);
        return gen.generateKey();
    }
    //明文加密成密文
    public static byte[] encrypt(byte[] plainBytes, byte[] key) throws Exception {
        SecretKey secKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        // 初始化密码器（加密模型）
        cipher.init(Cipher.ENCRYPT_MODE, secKey);
        return cipher.doFinal(plainBytes);
    }

    //密文解密成明文
    public static byte[] decrypt(byte[] cipherBytes, byte[] key) throws Exception {
        SecretKey secKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secKey);
        return cipher.doFinal(cipherBytes);
    }
/*
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

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
    }*/

    public static void main(String[] args) throws Exception {
        System.out.println(EncrypAesUtils.deMsgToStr("RaaSUJBzp3Q118bQcmBfLA=="));
        System.out.println("测试");
        System.out.println(EncrypAesUtils.enMsgToStr("13600012889"));
    }
}
