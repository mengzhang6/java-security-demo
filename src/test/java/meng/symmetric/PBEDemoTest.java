package meng.symmetric;

import org.junit.Test;

public class PBEDemoTest {
	String str = "Hello World By mengzhang6";

	@Test
	public void jdkPBETest() {
		try {
			PBEDemo.jdkPBE(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
