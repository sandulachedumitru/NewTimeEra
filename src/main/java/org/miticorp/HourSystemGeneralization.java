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
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute *
				numberOfMilisecondsPerSecond;
	}

	public HourSystemGeneralization() {
		Hour hour = HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
		this.hour = hour.hour;
		this.minute = hour.minute;
		this.second = hour.second;
		this.milisecond = hour.milisecond;
	}

	public HourSystemGeneralization(int hour, int minute, int second, int milisecond) {
		Hour hourValidation = validatesAndFormatsHour(hour, minute, second, milisecond);
		Hour newHour = HourOps.getTimeFromhoursysTohoursys(new Hour24(hour, minute, second, milisecond), hourValidation);
		this.hour = newHour.hour;
		this.minute = newHour.minute;
		this.second = newHour.second;
		this.milisecond = newHour.milisecond;
	}

	public HourSystemGeneralization(
			int numberOfHoursPerDay, 	int numberOfMinutesPerHour, 	int numberOfSecondsPerMinute, 	int numberOfMilisecondsPerSecond,
			int hour, 					int minute, 					int second, 					int milisecond) {
		this.numberOfHoursPerDay = numberOfHoursPerDay;
		this.numberOfMinutesPerHour = numberOfMinutesPerHour;
		this.numberOfSecondsPerMinute = numberOfSecondsPerMinute;
		this.numberOfMilisecondsPerSecond = numberOfMilisecondsPerSecond;

		Hour hourValidation = validatesAndFormatsHour(hour, minute, second, milisecond);
		Hour newHour = HourOps.getTimeFromhoursysTohoursys(new Hour24(hour, minute, second, milisecond), hourValidation);
		this.hour = newHour.hour;
		this.minute = newHour.minute;
		this.second = newHour.second;
		this.milisecond = newHour.milisecond;
	}	
}
