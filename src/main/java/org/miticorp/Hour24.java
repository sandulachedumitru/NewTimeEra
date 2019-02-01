package org.miticorp;

import java.time.LocalTime;

/**
 * 
 * This is a specialized class for hour system of type 24h:60m:60s:1000mil.
 * It reduces complexity of HourSystemGeneralization class.
 * @author Dumitru Sandulache - sandulachedumitru@hotmail.com
 *
 */
public class Hour24 extends Hour {

	private LocalTime now24 = LocalTime.now();
	
	{
		numberOfHoursPerDay = 24;
		numberOfMinutesPerHour = 60;
		numberOfSecondsPerMinute = 60;
		numberOfMillisecondsPerSecond = 1000;
		numberOfMillisecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute *
				numberOfMillisecondsPerSecond;;
	}

	public Hour24() {
		this.hour = now24.getHour();
		this.minute = now24.getMinute();
		this.second = now24.getSecond();
		this.millisecond = now24.getNano() / 1000 / 1000;
	}

	public Hour24(long hour, long minute, long second, long milisecond) {
		// this method initializes this.{hour, minute, second, millisecond}
		validatesAndFormatsHour(hour, minute, second, milisecond);
	}

}
