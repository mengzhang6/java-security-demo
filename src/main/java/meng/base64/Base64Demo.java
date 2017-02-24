package meng.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加密
 * 
 * @author mengzhang6
 *
 */
@SuppressWarnings("restriction")
public class Base64Demo {

	public static void jdkBase64(String str) throws IOException {
		BASE64Encoder encoder = new BASE64Encoder();
		String result = encoder.encode(str.getBytes());
		System.out.println("jdk 加密后:" + result);

		BASE64Decoder decoder = new BASE64Decoder();
		String str2 = new String(decoder.decodeBuffer(result));
		System.out.println("jdk 解密后:" + str2);
	}

	public static void bcBase64(String str) {
		byte[] encoserBytes = org.bouncycastle.util.encoders.Base64.encode(str
				.getBytes());
		String result = new String(encoserBytes);
		System.out.println("bc 加密后:" + result);

		byte[] decoserBytes = org.bouncycastle.util.encoders.Base64
				.decode(result);
		String str2 = new String(decoserBytes);
		System.out.println("bc 解密后:" + str2);

	}

	public static void ccBase64(String str) {
		byte[] encoserBytes = Base64.encodeBase64(str.getBytes());
		String result = new String(encoserBytes);
		System.out.println("cc 加密后:" + result);

		byte[] decoserBytes = Base64.decodeBase64(result.getBytes());
		String str2 = new String(decoserBytes);
		System.out.println("cc 解密后:" + str2);

	}

}
