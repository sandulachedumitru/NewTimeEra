package org.miticorp;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HourSystemGeneralizationTest {
	private Hour hour;
	private static final Logger LOG = LoggerFactory.getLogger(HourTest.class);


	@Test
	public void testHourSystemGeneralizationNow() {
		long nh = 24, nm = 100, ns = 100, nmi = 1000;
		hour = new HourSystemGeneralization(nh, nm, ns, nmi);
		LocalTime time = LocalTime.now();
		long h = time.getHour(), m = time.getMinute(), s = time.getSecond(), mi = time.getNano() / 1000 / 1000;
		Hour24 hour24 = new Hour24(h, m, s, mi);
		HourSystemGeneralization hsg = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hour24, hour);
		
		LOG.info("The hour system is of type {}h:{}m:{}s:{}mil", 
				hour.numberOfHoursPerDay, 
				hour.numberOfMinutesPerHour, 
				hour.numberOfSecondsPerMinute, 
				hour.numberOfMillisecondsPerSecond);
		LOG.info("The time is: {}", hour.getHourPretty());
		
		assertEquals(hour.hour, hsg.hour);
		assertEquals(hour.minute, hsg.minute);
		assertEquals(hour.second, hsg.second);
	}

	@Test
	public void testHourSystemGeneralizationCustom() {
		long nh = 24, nm = 100, ns = 100, nmi = 1000;
		long h = 23, m = 99, s = 99, mi = 1000;
		hour = new HourSystemGeneralization(nh, nm, ns, nmi, h, m, s, mi);
		
		LOG.info("The hour system is of type {}h:{}m:{}s:{}mil", 
				hour.numberOfHoursPerDay, 
				hour.numberOfMinutesPerHour, 
				hour.numberOfSecondsPerMinute, 
				hour.numberOfMillisecondsPerSecond);
		LOG.info("The selected hour is: {}h:{}m:{}s:{}mi", h, m, s, mi);
		LOG.info("The corrected hour is {}", hour.getHourPretty());
		
		assertEquals(hour.hour, 0);
		assertEquals(hour.minute, 0);
		assertEquals(hour.second, 0);
		assertEquals(hour.millisecond, 0);		
	}

}
