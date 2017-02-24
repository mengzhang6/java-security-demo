package meng.symmetric;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * AES加密
 * 
 * @author mengzhang6
 *
 */
public class AesDemo {

	public static void jdkAES(String mingwen) throws Exception {
		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);// 密钥长度
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		Key key = new SecretKeySpec(bytesKey, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("明文：" + new String(result));
	}

	public static void bcAES(String mingwen) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
		// Provider provider = keyGenerator.getProvider();
		keyGenerator.init(128);// 密钥长度
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("bc密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		Key key = new SecretKeySpec(bytesKey, "AES");

		// 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("bc密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("bc明文：" + new String(result));
	}

}
