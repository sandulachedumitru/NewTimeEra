package org.miticorp;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hour24Test {
	private Hour hour;
	private static final Logger LOG = LoggerFactory.getLogger(HourTest.class);

	@Test
	public void testHour24Now() {
		hour = new Hour24();
		LocalTime time = LocalTime.now();
		
		LOG.info("The hour system is of type {}h:{}m:{}s:{}mil", 
				hour.numberOfHoursPerDay, 
				hour.numberOfMinutesPerHour, 
				hour.numberOfSecondsPerMinute, 
				hour.numberOfMillisecondsPerSecond);
		LOG.info("The time is: {}", time.toString());
		
		assertEquals(hour.hour, time.getHour());
		assertEquals(hour.minute, time.getMinute());
		assertEquals(hour.second, time.getSecond());
	}

	@Test
	public void testHour24Custom() {
		long h = 23, m = 59, s = 59, mi = 1000; 
		hour =  new Hour24(h, m, s, mi);
		
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
