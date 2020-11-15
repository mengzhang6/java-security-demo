package meng.asymmetric;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/11/16 01:51
 */
public class RsaMain {

    public static void getKey() throws NoSuchAlgorithmException {
        // 初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator
                .getInstance("RSA");
        keyPairGenerator.initialize(512); // 512 1024 2048

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

        String publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        System.out.println("公钥：\n" + publicKey);
        System.out.println(Base64.decodeBase64(publicKey).length == rsaPublicKey.getEncoded().length);
        System.out.println("私钥：\n" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
        System.out.println("---------------------");
    }

    public static void main(String[] args) throws Exception {
        getKey();
        /**
         * 512
         * 公钥：
         * MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIyoRDKP8aFts62Oiq7RAxh6XyqM94QIPgwLPJ4x1WyXgoFHIqjjWyF18ktvsgcHPzv5Sk5Y8X+kc1Lpsovavf8CAwEAAQ==
         * true
         * 私钥：
         * MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAjKhEMo/xoW2zrY6KrtEDGHpfKoz3hAg+DAs8njHVbJeCgUciqONbIXXyS2+yBwc/O/lKTljxf6RzUumyi9q9/wIDAQABAkAHwI5hqwVKPF2WxzBliid9heEQ20wY/stMpqHkpiJ5JZrw2orAMepqx+ZVEAN008M9CzL6VkzsEVVdHUh3ul+hAiEA9cZ8bvAI3HUeKow1l9s7yxY6jfhrTh/4LuYl6bTjzOsCIQCSgkUzq3tWXe7Oe5Z8fF0q8oraj6vrNvj+grCtjVY+PQIgKJHFBIL20305p4hRaGm16wYWUE0LWXAJhaRT6coEOFUCIBoqGaG4pjYuYE+P0rg4nYrl9dZCgjlnf+A0Bsl3zEchAiBtsvYxmNWUWspwxED6puFypwAsBh1YYcV/oIONYM0dBQ==
         */
        String ciphertext = encrypt("MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIyoRDKP8aFts62Oiq7RAxh6XyqM94QIPgwLPJ4x1WyXgoFHIqjjWyF18ktvsgcHPzv5Sk5Y8X+kc1Lpsovavf8CAwEAAQ==", "TYPE#20201116021010");
        decrypt("MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAjKhEMo/xoW2zrY6KrtEDGHpfKoz3hAg+DAs8njHVbJeCgUciqONbIXXyS2+yBwc/O/lKTljxf6RzUumyi9q9/wIDAQABAkAHwI5hqwVKPF2WxzBliid9heEQ20wY/stMpqHkpiJ5JZrw2orAMepqx+ZVEAN008M9CzL6VkzsEVVdHUh3ul+hAiEA9cZ8bvAI3HUeKow1l9s7yxY6jfhrTh/4LuYl6bTjzOsCIQCSgkUzq3tWXe7Oe5Z8fF0q8oraj6vrNvj+grCtjVY+PQIgKJHFBIL20305p4hRaGm16wYWUE0LWXAJhaRT6coEOFUCIBoqGaG4pjYuYE+P0rg4nYrl9dZCgjlnf+A0Bsl3zEchAiBtsvYxmNWUWspwxED6puFypwAsBh1YYcV/oIONYM0dBQ==", ciphertext);

        /**
         * 1024
         * 公钥：
         * MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCww5wMa7KzM9O12dntxgSxOY+pQugw9lVz2KsgN3qKp+biyc2sarc6A03A47fZhfx8oujIJy/8MMxZkwicl9IrlA45uAVesP6ik0Xh8o9/Vne/k4n9IObfZXKBS1xkeQEgvjefoCGin7KqDsuwWoCOD2tT05YsbVRtAm6Ni8VwgQIDAQAB
         * true
         * 私钥：
         * MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALDDnAxrsrMz07XZ2e3GBLE5j6lC6DD2VXPYqyA3eoqn5uLJzaxqtzoDTcDjt9mF/Hyi6MgnL/wwzFmTCJyX0iuUDjm4BV6w/qKTReHyj39Wd7+Tif0g5t9lcoFLXGR5ASC+N5+gIaKfsqoOy7BagI4Pa1PTlixtVG0Cbo2LxXCBAgMBAAECgYB8OHspn2Xt2bYmTfqAxm/1u+XhpUtXXUWgK9vWwCY12ti+9+K6ECTDeB7kNyO0khxl2naMcj2elQyGxnV+1pKzMcmkQvS/YIX4105bvrXUFP4rVRl3Mv9WaZG+7T8Qw2jEy3A4B3qEtETpS/PPuU33OmN59dKYsF+6QNcLuiSwIQJBAOHot0J3NvxRZXsceLggak3r8GUTiNAg1um0hPuPJIO45Itq5fxL9UnPsHnxxGxqKA/5aW1DAth0q68i05QDGGMCQQDITxkAPoAqwMCZp+eIVRj5rnKgUUzNsPKZqv7DVvohV078HwPycHIRIXcnNdDSIVWhvJjWwhKqCc6S1THj7J7LAkEApwz9Jusc/+v4zNa69f0SXtiPhU6ypqUCBwN0KskoyTbZ2baMpspMce5go2wCQeEmCtn1+dPJsOJlajaomhTH3QJATBuprysQPa5RP9DJK0bSH4RF714UpY7XDsYm2IfCsEphK3CJamOJI7Mod9B90Gl0BOkI1YiV2N+zCp1QwH5WcwJBALraxCNPBjymVqkJb/tSgJRQ4XD7qw5aiPyeJz2M1LzcnZbXhVmADfPVSRSEk8jOaw2grIfCxGtTKgWS3c+HtJk=
         */
        String ciphertext2 = encrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCww5wMa7KzM9O12dntxgSxOY+pQugw9lVz2KsgN3qKp+biyc2sarc6A03A47fZhfx8oujIJy/8MMxZkwicl9IrlA45uAVesP6ik0Xh8o9/Vne/k4n9IObfZXKBS1xkeQEgvjefoCGin7KqDsuwWoCOD2tT05YsbVRtAm6Ni8VwgQIDAQAB", "TYPE#20201116021010");
        decrypt("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALDDnAxrsrMz07XZ2e3GBLE5j6lC6DD2VXPYqyA3eoqn5uLJzaxqtzoDTcDjt9mF/Hyi6MgnL/wwzFmTCJyX0iuUDjm4BV6w/qKTReHyj39Wd7+Tif0g5t9lcoFLXGR5ASC+N5+gIaKfsqoOy7BagI4Pa1PTlixtVG0Cbo2LxXCBAgMBAAECgYB8OHspn2Xt2bYmTfqAxm/1u+XhpUtXXUWgK9vWwCY12ti+9+K6ECTDeB7kNyO0khxl2naMcj2elQyGxnV+1pKzMcmkQvS/YIX4105bvrXUFP4rVRl3Mv9WaZG+7T8Qw2jEy3A4B3qEtETpS/PPuU33OmN59dKYsF+6QNcLuiSwIQJBAOHot0J3NvxRZXsceLggak3r8GUTiNAg1um0hPuPJIO45Itq5fxL9UnPsHnxxGxqKA/5aW1DAth0q68i05QDGGMCQQDITxkAPoAqwMCZp+eIVRj5rnKgUUzNsPKZqv7DVvohV078HwPycHIRIXcnNdDSIVWhvJjWwhKqCc6S1THj7J7LAkEApwz9Jusc/+v4zNa69f0SXtiPhU6ypqUCBwN0KskoyTbZ2baMpspMce5go2wCQeEmCtn1+dPJsOJlajaomhTH3QJATBuprysQPa5RP9DJK0bSH4RF714UpY7XDsYm2IfCsEphK3CJamOJI7Mod9B90Gl0BOkI1YiV2N+zCp1QwH5WcwJBALraxCNPBjymVqkJb/tSgJRQ4XD7qw5aiPyeJz2M1LzcnZbXhVmADfPVSRSEk8jOaw2grIfCxGtTKgWS3c+HtJk=", ciphertext2);


    }

    public static String encrypt(String publicKeyStr, String str) throws Exception {
        System.out.println("公钥加密 私钥解密---加密:");

        // 公钥加密 私钥解密---加密
        byte[] publicKeyBytes = Base64.decodeBase64(publicKeyStr);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(str.getBytes());

        String result = Base64.encodeBase64String(bytes);
        System.out.println(result);
        return result;
    }

    public static void decrypt(String privateKeyStr, String str) throws Exception {
        System.out.println("公钥加密 私钥解密---解密:");

        // 公钥加密 私钥解密---解密
        byte[] privateKeyBytes = Base64.decodeBase64(privateKeyStr);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(str));
        System.out.println(new String(bytes));
    }


}
