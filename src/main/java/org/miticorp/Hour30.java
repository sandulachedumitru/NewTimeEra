package org.miticorp;

//TODO java doc and test classes
public class Hour30 extends Hour {
	
	{
		numberOfHoursPerDay = 30;
		numberOfMinutesPerHour = 100;
		numberOfSecondsPerMinute = 100;
		numberOfMilisecondsPerSecond = 1000;

		numberOfSecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute;
		
		numberOfMilisecondsPerDay =
				numberOfSecondsPerDay *
				numberOfMilisecondsPerSecond;
	}

	public Hour30() {
		Hour hour = HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
		this.hour = hour.hour;
		this.minute = hour.minute;
		this.second = hour.second;
		this.milisecond = hour.milisecond;
	}

	public Hour30(int hour, int minute, int second, int milisecond) {
		validatesAndFormatsHour(hour, minute, second, milisecond);
	}

}
