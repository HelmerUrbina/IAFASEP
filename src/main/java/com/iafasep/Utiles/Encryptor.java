package com.iafasep.Utiles;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class Encryptor {

    public static String encode(String text) {
        if (text.length() > 0) {
            Random ran = new Random();
            byte[] encoded = Base64.encodeBase64(text.getBytes());
            int limit = encoded.length - 1 < 9 ? encoded.length - 1 : 9;
            int k = ran.nextInt(limit);
            text = "";
            String b64 = new String(encoded);
            char array[] = b64.toCharArray();
            for (int i = 0; i < array.length; i++) {
                if (k == i) {
                    text = k + text + array[i] + ran.nextInt(limit);
                } else {
                    text = text + array[i];
                }
            }
        }
        return text;
    }

    public static String decode(String encoded) {
        char array[] = encoded.toCharArray();
        encoded = "";
        for (int i = 0; i < array.length; i++) {
            if (i != 0 && i != Integer.parseInt("" + array[0]) + 2) {
                encoded = encoded + array[i];
            }
        }
        return new String(Base64.decodeBase64(encoded));
    }

    public static String encodeHsh(String text) {
        if (text.length() > 0) {
            Random ran = new Random();
            try {
                byte[] encoded = Base64.encodeBase64(text.getBytes("UTF-8"));
                int limit = encoded.length - 1 < 9 ? encoded.length - 1 : 9;
                int rand = ran.nextInt(limit);
                text = "" + rand;

                String b64 = new String(encoded);
                char array[] = b64.toCharArray();
                int posOfRand = 0;
                for (int i = 0; i < array.length; i++) {
                    if (posOfRand == rand) {
                        rand = ran.nextInt(limit);
                        rand = rand == 0 ? 1 : rand;
                        text = text + rand + array[i];
                        posOfRand = 0;
                    } else {
                        text = text + array[i];
                    }
                    posOfRand++;
                }
                encoded = Base64.encodeBase64(text.getBytes("UTF-8"));

                text = new String(encoded);
            } catch (UnsupportedEncodingException ex) {
            }
        }
        return text;
    }

    public static String decodeHsh(String encoded) {
        String result = "";
        try {
            encoded = new String(Base64.decodeBase64(encoded));
            char array[] = encoded.toCharArray();
            int rand = Integer.parseInt("" + array[0]);
            int posRand = 0;
            encoded = "";
            for (int i = 1; i < array.length; i++) {
                if (posRand != rand) {
                    encoded = encoded + array[i];
                    posRand++;
                } else {
                    rand = Integer.parseInt("" + array[i]);
                    posRand = 0;
                }
            }

            result = new String(Base64.decodeBase64(encoded), "UTF-8");
        } catch (UnsupportedEncodingException | NumberFormatException ex) {
            result = new String(Base64.decodeBase64(encoded));
        }
        return result;
    }

    public static Properties getPropertiesFromURLHsh(String params) {
        Properties ps = new Properties();
        try {
            params = decodeHsh(params);
            params = params.replaceAll("==&", "== &");
            String[] result = params.split("&");
            for (String result1 : result) {
                String[] parm = result1.split("==");
                ps.setProperty(parm[0], parm[1].trim());
            }
        } catch (Exception err) {
        }
        return ps;
    }

    public static String md5(String str) {
        String md = DigestUtils.md5Hex(str);
        return md;
    }

    /**
     * @param md5
     * @return Retorna una cadena de 32 careacteres
     */
    public static String md52(String md5) {
        md5 = md5 == null ? "" : md5;
        if (!md5.equals("")) {
            String text = md5.substring(16, 32) + md5 + md5.substring(0, 16);
            md5 = DigestUtils.md5Hex(text);
        }
        return md5;
    }

    /**
     * @param params
     * @return Retorna tipo Properties
     * @see java.util.Properties
     */
    public static Properties getPropertiesFromURL(String params) {
        Properties ps = new Properties();
        try {
            params = decode(params);
            params = params.replaceAll("==&", "== &");
            String[] result = params.split("&");
            for (String result1 : result) {
                String[] parm = result1.split("==");
                ps.setProperty(parm[0], parm[1].trim());
            }
        } catch (Exception err) {
        }
        return ps;
    }

    //-------------------Encryptacion con llaves----------
    public static String encriptarConLlave(String texto, String secretKey) {
        //String secretKey = "qualityinfosolutions"; // llave para encriptar datos
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
        }
        return base64EncryptedString;
    }

    public static String desencriptarConLlave(String texto, String secretKey) {
        String base64EncryptedString = "";
        try {
            byte[] message = Base64.decodeBase64(texto.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            base64EncryptedString = new String(plainText, "UTF-8");
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
        }
        return base64EncryptedString;
    }
}
