package meng.symmetric;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 三重DES加密
 * 
 * @author mengzhang6
 *
 */
public class DesTripleDemo {

	public static void jdk3Des(String mingwen) throws Exception {
		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
		// keyGenerator.init(168);// 密钥长度
		keyGenerator.init(new SecureRandom());// 生成一个默认长度的密钥
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("3des密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		SecretKey secretKey_conbert = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey_conbert);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("3des密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, secretKey_conbert);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("3des明文：" + new String(result));
	}

	public static void bc3Des(String mingwen) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
		// Provider provider = keyGenerator.getProvider();
		// keyGenerator.init(56);// 密钥长度
		keyGenerator.init(new SecureRandom());// 生成一个默认长度的密钥
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("3des bc密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
		SecretKey secretKey_conbert = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey_conbert);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("3des bc密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, secretKey_conbert);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("3des bc明文：" + new String(result));
	}

}
