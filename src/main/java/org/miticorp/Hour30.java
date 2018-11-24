package org.miticorp;

//TODO java doc and test classes
public class Hour30 extends Hour {
	
	{
		numberOfHoursPerDay = 30;
		numberOfMinutesPerHour = 100;
		numberOfSecondsPerMinute = 100;
		numberOfMillisecondsPerSecond = 1000;
		numberOfMillisecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute *
				numberOfMillisecondsPerSecond;
	}

	public Hour30() {
		// this method initializes this.{hour, minute, second, millisecond}
		HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
	}

	public Hour30(long hour, long minute, long second, long milisecond) {
		// this method initializes this.{hour, minute, second, millisecond}
		validatesAndFormatsHour(hour, minute, second, milisecond);
	}

}
