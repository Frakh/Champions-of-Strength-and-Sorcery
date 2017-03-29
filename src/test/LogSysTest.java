package test;

import es.eventlogger.LogSys;
import org.junit.Test;

public class LogSysTest {

	@Test
	public void testLOGGING() {

		System.out.println(LogSys.log("Hello World"));
		LogSys.exit();

		char a = '4';
		char b = '1';
		int v = Integer.parseInt(String.valueOf(a)+b);

		System.out.println(Character.getNumericValue('7'));
		System.out.println(v);
	}
}
