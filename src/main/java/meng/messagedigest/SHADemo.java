package meng.messagedigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SHADemo {

	public static void jdkSHA1(String src) throws NoSuchAlgorithmException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA");
		sha1.update(src.getBytes());
		String result = Hex.encodeHexString(sha1.digest());
		System.out.println("jdk SHA1:\n" + result);
	}

	public static void bcSHA1(String src) {
		Digest sha1 = new SHA1Digest();
		// Digest sha224 = new SHA224Digest();
		// Digest sha256 = new SHA256Digest();
		// Digest sha512 = new SHA512Digest();
		sha1.update(src.getBytes(), 0, src.getBytes().length);
		byte[] sha1Bytes = new byte[sha1.getDigestSize()];
		sha1.doFinal(sha1Bytes, 0);
		System.out.println("bc  SHA1:\n" + Hex.encodeHexString(sha1Bytes));
	}

	public static void ccSHA(String src) {
		String ccSHA1 = DigestUtils.sha1Hex(src.getBytes());
		System.out.println("cc  SHA1:\n" + ccSHA1);
		String ccSHA256 = DigestUtils.sha256Hex(src.getBytes());
		System.out.println("cc  SHA256:\n" + ccSHA256);
		String ccSHA384 = DigestUtils.sha384Hex(src.getBytes());
		System.out.println("cc  SHA384:\n" + ccSHA384);
		String ccSHA512 = DigestUtils.sha512Hex(src.getBytes());
		System.out.println("cc  SHA512:\n" + ccSHA512);
	}

	public static void bcSHA224(String src) throws NoSuchAlgorithmException {
		// SHA-224 提供方，bc
		Security.addProvider(new BouncyCastleProvider());

		MessageDigest sha224 = MessageDigest.getInstance("SHA-224");
		sha224.update(src.getBytes());
		String result = Hex.encodeHexString(sha224.digest());
		System.out.println("bc  SHA224:\n" + result);
	}

	public static void bcSHA224_two(String src) {
		Digest sha224 = new SHA224Digest();
		sha224.update(src.getBytes(), 0, src.getBytes().length);
		byte[] shaBytes = new byte[sha224.getDigestSize()];
		sha224.doFinal(shaBytes, 0);
		System.out.println("bc  SHA224:\n" + Hex.encodeHexString(shaBytes));
	}

	public static void jdkSHA256(String src) throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		sha256.update(src.getBytes());
		String result = Hex.encodeHexString(sha256.digest());
		System.out.println("jdk SHA256:\n" + result);
	}

	public static void bcSHA256(String src) {
		Digest sha256 = new SHA256Digest();
		sha256.update(src.getBytes(), 0, src.getBytes().length);
		byte[] shaBytes = new byte[sha256.getDigestSize()];
		sha256.doFinal(shaBytes, 0);
		System.out.println("bc  SHA256:\n" + Hex.encodeHexString(shaBytes));
	}

	public static void jdkSHA384(String src) throws NoSuchAlgorithmException {
		MessageDigest sha384 = MessageDigest.getInstance("SHA-384");
		sha384.update(src.getBytes());
		String result = Hex.encodeHexString(sha384.digest());
		System.out.println("jdk SHA384:\n" + result);
	}

	public static void bcSHA384(String src) {
		Digest sha384 = new SHA384Digest();
		sha384.update(src.getBytes(), 0, src.getBytes().length);
		byte[] shaBytes = new byte[sha384.getDigestSize()];
		sha384.doFinal(shaBytes, 0);
		System.out.println("bc  SHA384:\n" + Hex.encodeHexString(shaBytes));
	}

}
