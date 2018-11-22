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
	protected int numberOfMilisecondsPerDay;

	protected int getHourInSeconds() {
		return 
				hour * numberOfMinutesPerHour * numberOfSecondsPerMinute + 
				minute * numberOfSecondsPerMinute +
				second +
				milisecond / numberOfMilisecondsPerSecond;
	}
	
	protected int getHourInMiliseconds() {
		return
				hour * numberOfMinutesPerHour * numberOfSecondsPerMinute * numberOfMilisecondsPerSecond + 
				minute * numberOfSecondsPerMinute * numberOfMilisecondsPerSecond +
				second * numberOfMilisecondsPerSecond +
				milisecond;
	}

	protected String getHourPretty() {
		return hour + ":" + minute + ":" + second + ":" + milisecond;
	}
	
	protected Hour validatesAndFormatsHour(int hour, int minute, int second, int milisecond) {
		int mi, fmi;
		mi = milisecond % numberOfMilisecondsPerSecond;
		fmi = milisecond / numberOfMilisecondsPerSecond;
		
		int s, fs;
		s = second % numberOfSecondsPerMinute; s += fmi;
		fs = second / numberOfSecondsPerMinute;
		
		int m, fm;
		m = minute % numberOfMinutesPerHour; m += fs;
		fm = minute / numberOfMinutesPerHour;
		
		/* useful when working with days and will replace the below equivalent code block
		int h, fh;
		h = (hour + fm) % numberOfHoursPerDay; // hour of the day
		fh = (hour + fm) / numberOfHoursPerDay; // number of days
		*/
		
		int h, fh;
		h = hour % numberOfHoursPerDay; h += fm;
 		fh = hour / numberOfHoursPerDay;
		
		this.hour = h;
		this.minute = m;
		this.second = s;
		this.milisecond = mi;
		
		return this;
	}
}