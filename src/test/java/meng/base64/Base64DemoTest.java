package meng.base64;

import java.io.IOException;

import org.junit.Test;

public class Base64DemoTest {

	String str = "Hello World By mengzhang6";

	@Test
	public void jdkBase64Test() {
		try {
			Base64Demo.jdkBase64(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcBase64Test() {
		Base64Demo.bcBase64(str);
	}

	@Test
	public void ccBase64Test() {
		Base64Demo.ccBase64(str);
	}

}
