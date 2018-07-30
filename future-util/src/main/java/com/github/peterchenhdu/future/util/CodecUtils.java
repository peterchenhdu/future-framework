/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.github.peterchenhdu.future.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author chenpi
 * @since 2018/7/30 23:06
 */
public class CodecUtils {
    private static final String AES = "AES";
    private static final String UTF8 = "UTF-8";
    private static final String AES_KEY = "fb43214469ec6fcbaa81daa55a2e27a7";

    /**
     * 使用指定的字符串生成AES秘钥
     */
    public static String getKeyByPass(String password) {
        //生成秘钥

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            // kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
            //SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以生成的秘钥就一样。
            kg.init(128, new SecureRandom(password.getBytes()));
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = byteToHexString(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * byte数组转化为16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * AES加密
     *
     * @param content
     * @return
     * @throws DecoderException
     */
    private static byte[] encrypt(String content) throws DecoderException {
        try {
            byte[] encodeFormat = null;
            try {
                //秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
                encodeFormat = Hex.decodeHex(AES_KEY.toCharArray());
            } catch (DecoderException e) {
                e.printStackTrace();
            }
            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 加密内容进行编码
            byte[] byteContent = content.getBytes(UTF8);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 正式执行加密操作
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     *
     * @param contents
     * @return
     * @throws DecoderException
     */
    private static byte[] decrypt(String contents) throws DecoderException {
        try {
            //密文使用Hex解码
            byte[] content = Hex.decodeHex(contents.toCharArray());
            //秘钥 Hex解码为什么秘钥要进行解码，因为秘钥是某个秘钥明文进行了Hex编码后的值，所以在使用的时候要进行解码
            byte[] encodeFormat = Hex.decodeHex(AES_KEY.toCharArray());
            SecretKeySpec key = new SecretKeySpec(encodeFormat, AES);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(AES);
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 正式执行解密操作
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Aes加密
     *
     * @param context 明文
     * @return
     * @throws DecoderException
     */
    public static String encryption(String context) {
        //加密后的明文也就变成了密文
        byte[] encryptResult = new byte[0];
        try {
            encryptResult = encrypt(context);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        //密码文Hex编码
        String encryptResultStr = Hex.encodeHexString(encryptResult);
        return encryptResultStr;
    }

    /**
     * Aes解密
     *
     * @param context 密文
     * @return
     * @throws DecoderException
     * @throws UnsupportedEncodingException
     */
    public static String decryption(String context) {
        //这里的密文解密前先进行了Hex解码
        byte[] decryptResult = new byte[0];
        try {
            decryptResult = decrypt(context);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = new String(decryptResult, UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws UnsupportedEncodingException, DecoderException {
        //加密内容
        String content = "123456787654321123456787654321123456787654321123456787654321";
        // 加密
        System.out.println("加密前：" + content);
        // 调用加密方法
        String encryptResultStr = encryption(content);
        System.out.println("加密后：" + encryptResultStr);
        // 调用解密方法
        String result = decryption(encryptResultStr);
        // 解密内容进行解码
        System.out.println("解密后：" + result);
    }
}