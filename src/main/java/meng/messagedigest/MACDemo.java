package meng.messagedigest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class MACDemo {

	public static void jdkHmacMD5(String src) throws NoSuchAlgorithmException,
			InvalidKeyException, DecoderException {
		// 获取密钥
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] key = secretKey.getEncoded();
		System.out.println("密钥：" + Hex.encodeHexString(key));
//		key = Hex.decodeHex(new char[] { 'a', 'a', 'a', 'a', 'a', 'a', 'a',
//				'a', 'a', 'a' });
//		System.out.println("密钥：" + Hex.encodeHexString(key));

		// 还原密钥
		SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
		Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
		mac.init(restoreSecretKey);
		byte[] bytes = mac.doFinal(src.getBytes());
		System.out.println("jdk hmacMD5:\n"
				+ org.bouncycastle.util.encoders.Hex.toHexString(bytes));
	}

	public static void bcHmacMD5(String src) {
		HMac hmac = new HMac(new MD5Digest());
		hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex
				.decode("aaaaaaaaaa")));
		hmac.update(src.getBytes(), 0, src.getBytes().length);

		byte[] bytes = new byte[hmac.getMacSize()];
		hmac.doFinal(bytes, 0);
		System.out.println("bc  hmacMD5:\n"
				+ org.bouncycastle.util.encoders.Hex.toHexString(bytes));
	}

}
