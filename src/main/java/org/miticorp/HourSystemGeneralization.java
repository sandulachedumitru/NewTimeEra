package org.miticorp;

public class HourSystemGeneralization extends Hour {

	public HourSystemGeneralization(long numberOfHoursPerDay, long numberOfMinutesPerHour, long numberOfSecondsPerMinute, long numberOfMilisecondsPerSecond) {
		this.numberOfHoursPerDay 			= numberOfHoursPerDay;
		this.numberOfMinutesPerHour 		= numberOfMinutesPerHour;
		this.numberOfSecondsPerMinute 		= numberOfSecondsPerMinute;
		this.numberOfMillisecondsPerSecond 	= numberOfMilisecondsPerSecond;
		this.numberOfMillisecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute*
				numberOfMilisecondsPerSecond;
		
		// this method initializes this.{hour, minute, second, millisecond}
		HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
	}

	public HourSystemGeneralization(
			long numberOfHoursPerDay, 	long numberOfMinutesPerHour, 	long numberOfSecondsPerMinute, 	long numberOfMilisecondsPerSecond,
			long hour, 					long minute, 					long second, 					long milisecond) {
		this.numberOfHoursPerDay = numberOfHoursPerDay;
		this.numberOfMinutesPerHour = numberOfMinutesPerHour;
		this.numberOfSecondsPerMinute = numberOfSecondsPerMinute;
		this.numberOfMillisecondsPerSecond = numberOfMilisecondsPerSecond;
		this.numberOfMillisecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute*
				numberOfMilisecondsPerSecond;
		
		// this method initializes this.{hour, minute, second, millisecond}
		validatesAndFormatsHour(hour, minute, second, milisecond);
	}
}
