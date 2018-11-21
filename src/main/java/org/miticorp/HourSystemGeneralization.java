package org.miticorp;

public class HourSystemGeneralization extends Hour {
	
	{
		numberOfHoursPerDay = 24;
		numberOfMinutesPerHour = 100;
		numberOfSecondsPerMinute = 100;
		numberOfMilisecondsPerSecond = 1000;

		numberOfSecondsPerDay = 
				numberOfHoursPerDay *
				numberOfMinutesPerHour *
				numberOfSecondsPerMinute;
	}
	
	public HourSystemGeneralization() {
		Hour hour = HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
		this.hour = hour.hour;
		this.minute = hour.minute;
		this.second = hour.second;
		this.milisecond = hour.milisecond;
	}
	
	 public HourSystemGeneralization(int hour, int minute, int second, int milisecond) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
			this.milisecond = milisecond;
	 }
	 
	 public void setHourSystemGeneralization(int numberOfHoursPerDay, int numberOfMinutesPerHour, int numberOfSecondsPerMinute, int numberOfMilisecondsPerSecond) {
		 this.numberOfHoursPerDay = numberOfHoursPerDay;
		 this.numberOfMinutesPerHour = numberOfMinutesPerHour;
		 this.numberOfSecondsPerMinute = numberOfSecondsPerMinute;
		 this.numberOfMilisecondsPerSecond = numberOfMilisecondsPerSecond;
	 }

}
