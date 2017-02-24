package meng.symmetric;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

public class DesDemoTest {
	String str = "Hello World By mengzhang6";

	@Test
	public void jdkDesTest() {
		try {
			DesDemo.jdkDes(str);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcDesTest() {
		try {
			DesDemo.bcDes(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
