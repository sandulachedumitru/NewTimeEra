package org.miticorp;

// TODO java doc and test classes
public abstract class Hour {
	protected int hour;
	protected int minute;
	protected int second;
	protected int milisecond;

	protected int numberOfHoursPerDay;
	protected int numberOfMinutesPerHour;
	protected int numberOfSecondsPerMinute;
	protected int numberOfMilisecondsPerSecond;
	protected int numberOfSecondsPerDay;

	protected int getHourInSeconds() {
		return 
				hour * numberOfMinutesPerHour * numberOfSecondsPerMinute + 
				minute * numberOfSecondsPerMinute +
				second +
				milisecond / numberOfMilisecondsPerSecond;
	}

	protected String getHourPretty() {
		return hour + ":" + minute + ":" + second + ":" + milisecond;
	}
}