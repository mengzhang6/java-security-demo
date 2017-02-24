package meng.symmetric;

import org.junit.Test;

public class AesDemoTest {
	
	String str = "Hello World By mengzhang6";

	@Test
	public void jdkAESTest() {
		try {
			AesDemo.jdkAES(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bcAESTest() {
		try {
			AesDemo.bcAES(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
