package test;

import es.eventlogger.LogSys;
import org.junit.Test;

public class LogSysTest {

	@Test
	public void testLOGGING() {

		System.out.println(LogSys.log("Hello World"));
		LogSys.exit();

	}
}
