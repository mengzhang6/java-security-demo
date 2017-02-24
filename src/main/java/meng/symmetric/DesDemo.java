package meng.symmetric;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * DES加密
 * 
 * @author mengzhang6
 *
 */
public class DesDemo {

	public static void jdkDes(String mingwen) throws NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException,
			NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);// 密钥长度
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey_conbert = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey_conbert);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, secretKey_conbert);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("明文：" + new String(result));
	}

	public static void bcDes(String mingwen) throws Exception {
		Security.addProvider(new BouncyCastleProvider());

		// 生成KEY
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
		// Provider provider = keyGenerator.getProvider();
		keyGenerator.init(56);// 密钥长度
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] bytesKey = secretKey.getEncoded();
		System.out.println("bc密钥：" + Hex.encodeHexString(bytesKey));

		// KEY转换
		DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey_conbert = factory.generateSecret(desKeySpec);

		// 加密
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey_conbert);
		byte[] bytes = cipher.doFinal(mingwen.getBytes());
		System.out.println("bc密文：" + Hex.encodeHexString(bytes));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, secretKey_conbert);
		byte[] result = cipher.doFinal(bytes);
		System.out.println("bc明文：" + new String(result));
	}

}
