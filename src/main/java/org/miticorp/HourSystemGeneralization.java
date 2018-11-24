package org.miticorp;

public class HourSystemGeneralization extends Hour {

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

	public HourSystemGeneralization() {
		HourOps.getTimeFromhoursysTohoursys2(new Hour24(), this);

	}

	public HourSystemGeneralization(int hour, int minute, int second, int milisecond) {
		HourOps.getTimeFromhoursysTohoursys2(new Hour24(hour, minute, second, milisecond), this);
	}

	public HourSystemGeneralization(
			int numberOfHoursPerDay, 	int numberOfMinutesPerHour, 	int numberOfSecondsPerMinute, 	int numberOfMilisecondsPerSecond,
			int hour, 					int minute, 					int second, 					int milisecond) {
		this.numberOfHoursPerDay = numberOfHoursPerDay;
		this.numberOfMinutesPerHour = numberOfMinutesPerHour;
		this.numberOfSecondsPerMinute = numberOfSecondsPerMinute;
		this.numberOfMilisecondsPerSecond = numberOfMilisecondsPerSecond;

		this.numberOfSecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute;
		
		this.numberOfMilisecondsPerDay =
				this.numberOfSecondsPerDay *
				numberOfMilisecondsPerSecond;

		HourOps.getTimeFromhoursysTohoursys2(new Hour24(hour, minute, second, milisecond), this);
	}
	
	public HourSystemGeneralization(
			int numberOfHoursPerDay, 	int numberOfMinutesPerHour, 	int numberOfSecondsPerMinute, 	int numberOfMilisecondsPerSecond,
			Hour fromSystemHour) {
		this.numberOfHoursPerDay = numberOfHoursPerDay;
		this.numberOfMinutesPerHour = numberOfMinutesPerHour;
		this.numberOfSecondsPerMinute = numberOfSecondsPerMinute;
		this.numberOfMilisecondsPerSecond = numberOfMilisecondsPerSecond;

		this.numberOfSecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute;
		
		this.numberOfMilisecondsPerDay =
				this.numberOfSecondsPerDay *
				numberOfMilisecondsPerSecond;

		HourOps.getTimeFromhoursysTohoursys2(fromSystemHour, this);
	}
}
