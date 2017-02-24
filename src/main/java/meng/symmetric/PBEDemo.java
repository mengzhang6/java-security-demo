package meng.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class PBEDemo {

	public static void jdkPBE(String mingwen) throws NoSuchAlgorithmException,
			InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException {
		// 初始化sale
		SecureRandom random = new SecureRandom();
		byte[] salt = random.generateSeed(8);

		// 口令与密钥
		String password = "mengzhang6";// 口令
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBEWITHMD5andDES");
		Key key = factory.generateSecret(pbeKeySpec);// 密钥

		// 加密
		PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
		Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
		cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
		byte[] result = cipher.doFinal(mingwen.getBytes());
		System.out.println("pbe密钥：" + Hex.encodeHexString(result));
		System.out.println("pbe密文：" + Base64.encodeBase64String(result));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
		byte[] jiemi = cipher.doFinal(result);
		System.out.println("pbe解密：" + new String(jiemi));
	}

	public static void bcPBE() {
	}
}
