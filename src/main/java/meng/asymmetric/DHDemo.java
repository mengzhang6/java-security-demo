package meng.asymmetric;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * DH(Diffie-Hellman)密钥交换算法
 * 
 * @author mengzhang6
 *
 */
public class DHDemo {

	public static void jsdDH(String str) {
		try {
			// 1.初始化发送方密钥
			KeyPairGenerator keyPairGenerator = KeyPairGenerator
					.getInstance("DH");
			keyPairGenerator.initialize(512);

			KeyPair senderKeyPair = keyPairGenerator.generateKeyPair();
			byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();

			// 2.初始化接收方密钥
			KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
					senderPublicKeyEnc);
			PublicKey receiverPublicKey = receiverKeyFactory
					.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey)
					.getParams();
			keyPairGenerator.initialize(dhParameterSpec);
			KeyPair receiverKeyPair = keyPairGenerator.generateKeyPair();
			PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
			byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic()
					.getEncoded();

			// 3.密钥构建
			KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
			receiverKeyAgreement.init(receiverPrivateKey);
			receiverKeyAgreement.doPhase(receiverPublicKey, true);
			SecretKey receiverSecreKey = receiverKeyAgreement
					.generateSecret("DES");

			//
			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
			PublicKey senderPublicKey = senderKeyFactory
					.generatePublic(x509EncodedKeySpec);
			KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
			senderKeyAgreement.init(senderKeyPair.getPrivate());
			senderKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderSecreKey = senderKeyAgreement.generateSecret("DES");

			if (Objects.equals(senderSecreKey, receiverSecreKey)) {
				System.out.println("密钥生成成功");
			}
			// 4.加密
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderSecreKey);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println(Base64.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, receiverSecreKey);
			result = cipher.doFinal(result);
			System.out.println(new String(result));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
