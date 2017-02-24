package meng.messagedigest;

import org.junit.Test;

public class MACDemoTest {
	String str = "Hello World By mengzhang6";

	@Test
	public void jdkHmacMD5Test() {
		try {
			MACDemo.jdkHmacMD5(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcHmacMD5Test() {
		MACDemo.bcHmacMD5(str);
	}

}
