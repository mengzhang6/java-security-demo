package meng.messagedigest;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class Md5DemoTest {
	String str = "Hello World By mengzhang6";

	@Test
	public void jdkmd2Test() {
		try {
			MdDemo.jdkmd2(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void jdkmd5Test() {
		try {
			MdDemo.jdkmd5(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcmd4Test() {
		try {
			MdDemo.bcmd4(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcmd4_twoTest() {
		try {
			MdDemo.bcmd4_two(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcmd5Test() {
		try {
			MdDemo.bcmd5(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void ccmd2Test() {
		MdDemo.ccmd2(str);
	}

	@Test
	public void ccmd5Test() {
		MdDemo.ccmd5(str);
	}

}
