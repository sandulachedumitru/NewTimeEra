package org.miticorp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HourOpsTest {
	private static final Logger LOG = LoggerFactory.getLogger(HourTest.class);

	@Test
	public void testGetTransformationFactor() {
		int noOfDecimals = 100000;
		
		float fromMilliseconds = 24 * 60 * 60 * 1000;
		float toMilliseconds = 30 * 100 * 100 * 1000;
		long expectedFactor = (long) (toMilliseconds / fromMilliseconds * noOfDecimals);
		float factor = (float) expectedFactor / (float) noOfDecimals;
		
		Hour fromHour = new Hour24();
		Hour toHour = new Hour30();
		
		long transfFactor = (long) (HourOps.getTransformationFactor(fromHour, toHour) * noOfDecimals);
		
		LOG.info("The hour system which must be transformed is of type {}h:{}m:{}s:{}mil and the hour system which is transformed is of type {}h:{}m:{}s:{}mil", 
				fromHour.numberOfHoursPerDay, 
				fromHour.numberOfMinutesPerHour, 
				fromHour.numberOfSecondsPerMinute, 
				fromHour.numberOfMillisecondsPerSecond,
				toHour.numberOfHoursPerDay, 
				toHour.numberOfMinutesPerHour, 
				toHour.numberOfSecondsPerMinute, 
				toHour.numberOfMillisecondsPerSecond);
		LOG.info("The selected hours are: {}h:{}m:{}s:{}mi and {}h:{}m:{}s:{}mi.", fromHour.hour, fromHour.minute, fromHour.second, fromHour.millisecond, toHour.hour, toHour.minute, toHour.second, toHour.millisecond);
		LOG.info("The transformation factor is: {}", factor);
		
		assertEquals(expectedFactor, transfFactor);
	}

	@Test
	public void testGetTimeFromhoursysTohoursys() {
		
	}

}
