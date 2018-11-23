package org.miticorp;

import java.time.LocalTime;

//TODO java doc and test classes
public class Hour24 extends Hour {

	private LocalTime now24 = LocalTime.now();
	
	{
		numberOfHoursPerDay = 24;
		numberOfMinutesPerHour = 60;
		numberOfSecondsPerMinute = 60;
		numberOfMilisecondsPerSecond = 1000;

		numberOfSecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute;
		
		numberOfMilisecondsPerDay =
				numberOfSecondsPerDay *
				numberOfMilisecondsPerSecond;
	}

	public Hour24() {
		this.hour = now24.getHour();
		this.minute = now24.getMinute();
		this.second = now24.getSecond();
		this.milisecond = now24.getNano() / 1000 / 1000;
	}

	public Hour24(int hour, int minute, int second, int milisecond) {
		validatesAndFormatsHour(hour, minute, second, milisecond);
	}

}
