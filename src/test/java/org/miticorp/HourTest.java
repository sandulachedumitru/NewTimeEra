package org.miticorp;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Before;
import org.junit.Test;

public class HourTest {
	private Hour hour =  new Hour() {};
	private static final Logger LOG = LoggerFactory.getLogger(HourTest.class);

	@Before
	public void setup() {
		hour.day = 0;
		hour.hour = 23;
		hour.minute = 59;
		hour.second = 59;
		hour.millisecond = 1000;

		hour.numberOfHoursPerDay = 24;
		hour.numberOfMinutesPerHour = 60;
		hour.numberOfSecondsPerMinute = 60;
		hour.numberOfMillisecondsPerSecond = 1000;

		hour.numberOfMillisecondsPerDay = 
				hour.numberOfHoursPerDay *
				hour.numberOfMinutesPerHour *
				hour.numberOfSecondsPerMinute *
				hour.numberOfMillisecondsPerSecond;
	}

	@Test
	public void testGetHourInMilliseconds() {
		long hourInMiliseconds = hour.getHourInMilliseconds();

		LOG.info("The hour system is of type {}h:{}m:{}s:{}mil", 
				hour.numberOfHoursPerDay, 
				hour.numberOfMinutesPerHour, 
				hour.numberOfSecondsPerMinute, 
				hour.numberOfMillisecondsPerSecond);
		LOG.info("The testing hour is {}h:{}m:{}s:{}mil",
				hour.hour,
				hour.minute,
				hour.second,
				hour.millisecond);
		LOG.info("The testing hour in miliseconds is: {}", hourInMiliseconds);

		assertEquals(hourInMiliseconds, hour.numberOfMillisecondsPerDay);
	}

	@Test
	public void testGetHourPretty() {
		String pretty = hour.hour + ":" + hour.minute + ":" + hour.second + ":" + hour.millisecond;
		
		LOG.info("The hour in pretty format is {}", pretty);
		
		assertEquals(pretty, hour.getHourPretty());
	}

	@Test
	public void testGetDayAndHourPretty() {
		String pretty = hour.day + ":" + hour.hour + ":" + hour.minute + ":" + hour.second + ":" + hour.millisecond;
		
		LOG.info("The hour in pretty format with day is {}", pretty);
		
		assertEquals(pretty, hour.getDayAndHourPretty());
	}

	@Test
	public void testValidatesAndFormatsHour() {
		String pretty = hour.hour + ":" + hour.minute + ":" + hour.second + ":" + hour.millisecond;
		String prettyWithValidation = "1:0:0:0:0";
		
		Hour validatesHour = hour.validatesAndFormatsHour(hour.hour, hour.minute, hour.second, hour.millisecond);
		
		LOG.info("The hour values before validation is {}", pretty);
		LOG.info("The hour values after validation is {}", validatesHour.getDayAndHourPretty());
		
		assertEquals(validatesHour.getDayAndHourPretty(), prettyWithValidation);
	}

}
