package meng.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class MdDemo {

	public static void jdkmd2(String src) throws NoSuchAlgorithmException {
		MessageDigest md2 = MessageDigest.getInstance("MD2");
		byte[] result = md2.digest(src.getBytes());
		System.out.println("jdk md2:\n" + Hex.encodeHexString(result));
	}

	public static void jdkmd5(String src) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] result = md5.digest(src.getBytes());
		System.out.println("jdk md5:\n" + Hex.encodeHexString(result));
	}

	public static void bcmd4_two(String src) throws NoSuchAlgorithmException {
		// 添加提供方
		Security.addProvider(new BouncyCastleProvider());
		// 和jdk一致
		MessageDigest md4 = MessageDigest.getInstance("MD4");
		byte[] result = md4.digest(src.getBytes());
		System.out.println("bc  md4:\n" + Hex.encodeHexString(result));
	}

	public static void bcmd4(String src) throws NoSuchAlgorithmException {
		Digest md4 = new MD4Digest();
		md4.update(src.getBytes(), 0, src.getBytes().length);
		byte[] md4Bytes = new byte[md4.getDigestSize()];
		md4.doFinal(md4Bytes, 0);
		System.out.println("bc  md4:\n" + Hex.encodeHexString(md4Bytes));
	}

	public static void bcmd5(String src) throws NoSuchAlgorithmException {
		Digest md5 = new MD5Digest();
		md5.update(src.getBytes(), 0, src.getBytes().length);
		byte[] md5Bytes = new byte[md5.getDigestSize()];
		md5.doFinal(md5Bytes, 0);
		System.out.println("bc  md5:\n" + Hex.encodeHexString(md5Bytes));
	}

	public static void ccmd2(String src) {
		String result = DigestUtils.md2Hex(src.getBytes());
		System.out.println("cc  md2:\n" + result);
	}

	public static void ccmd5(String src) {
		String result = DigestUtils.md5Hex(src.getBytes());
		System.out.println("cc  md5:\n" + result);
	}
}
