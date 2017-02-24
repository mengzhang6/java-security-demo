package meng.symmetric;

import org.junit.Test;

public class DesTripleDemoTest {
	String str = "Hello World By mengzhang6";

	@Test
	public void jdk3DesTest() {
		try {
			DesTripleDemo.jdk3Des(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bc3DesTest() {
		try {
			DesTripleDemo.bc3Des(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
