package test;

import es.netclasses.Evenement;
import es.netclasses.NetQueueEvenement;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NetQueueTest {

	@Test
	public void testNQI() {

		NetQueueEvenement.addEvenement(new Evenement((byte) 2));
		NetQueueEvenement.addEvenement(new Evenement((byte) 2));
		NetQueueEvenement.addEvenement(new Evenement((byte) 47));

		assertEquals(null, NetQueueEvenement.getEvenement(0));
		assertEquals(null, NetQueueEvenement.getEvenement(1));
		Evenement[] listEv = NetQueueEvenement.getEvents(1);
		Assert.assertArrayEquals(null, listEv);

		Assert.assertNotNull(NetQueueEvenement.getEvenement(2));
		Assert.assertEquals(1, NetQueueEvenement.getEvents(2).length);
	}

}
