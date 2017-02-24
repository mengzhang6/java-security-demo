package meng.messagedigest;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class SHADemoTest {

	String str = "Hello World By mengzhang6";

	@Test
	public void jdkSHA1Test() {
		try {
			SHADemo.jdkSHA1(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void jdkSHA256Test() {
		try {
			SHADemo.jdkSHA256(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void jdkSHA384Test() {
		try {
			SHADemo.jdkSHA384(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcSHA1Test() {
		SHADemo.bcSHA1(str);
	}

	@Test
	public void bcSHA224Test() {
		try {
			SHADemo.bcSHA224(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void bcSHA224_twoTest() {
		SHADemo.bcSHA224_two(str);
	}

	@Test
	public void bcSHA256Test() {
		SHADemo.bcSHA256(str);
	}

	@Test
	public void bcSHA384Test() {
		SHADemo.bcSHA384(str);
	}

	@Test
	public void ccSHATest() {
		SHADemo.ccSHA(str);
	}

}
