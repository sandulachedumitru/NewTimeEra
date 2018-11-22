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
	
	protected Hour validate(int hour, int minute, int second, int milisecond) {
		int mi, fmi;
		mi = milisecond % numberOfMilisecondsPerSecond;
		fmi = milisecond / numberOfMilisecondsPerSecond;
		
		int s, fs;
		s = second % numberOfSecondsPerMinute; s += fmi;
		fs = second / numberOfSecondsPerMinute;
		
		int m, fm;
		m = minute % numberOfMinutesPerHour; m += fs;
		fm = minute / numberOfMinutesPerHour;
		
		// int h, fh;
		// h = (hour + fm) % numberOfHoursPerDay; // hour of the day
		// fh = (hour + fm) / numberOfHoursPerDay; // number of days
		
		int h, fh;
		h = hour % numberOfHoursPerDay; h += fm;
 		fh = hour / numberOfHoursPerDay;
		
		this.hour = h;
		this.minute = m;
		this.second = s;
		this.milisecond = mi;
		
		System.out.println("method: " + h + ":" + m + ":" + s + ":" + mi);
		
		return this;
	}
}