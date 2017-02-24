package meng.asymmetric;

import org.junit.Test;

public class DHDemoTest {
	private String str = "勇敢坚持乐观责任";

	@Test
	public void jsdDHTest() {
		DHDemo.jsdDH(str);
	}
}
