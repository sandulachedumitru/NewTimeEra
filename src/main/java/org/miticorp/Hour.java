package org.miticorp;

// TODO java doc and test classes
public abstract class Hour {
	protected long hour;
	protected long minute;
	protected long second;
	protected long millisecond;

	protected long numberOfHoursPerDay;
	protected long numberOfMinutesPerHour;
	protected long numberOfSecondsPerMinute;
	protected long numberOfMillisecondsPerSecond;
	protected long numberOfMillisecondsPerDay;
	
	protected long getHourInMilliseconds() {
		return
				hour * numberOfMinutesPerHour * numberOfSecondsPerMinute * numberOfMillisecondsPerSecond + 
				minute * numberOfSecondsPerMinute * numberOfMillisecondsPerSecond +
				second * numberOfMillisecondsPerSecond +
				millisecond;
	}

	protected String getHourPretty() {
		return hour + ":" + minute + ":" + second + ":" + millisecond;
	}
	
	// negative values need to be analyzed
	protected Hour validatesAndFormatsHour(long hour, long minute, long second, long milisecond) {
		long mi, fmi;
		mi = milisecond % numberOfMillisecondsPerSecond;
		fmi = milisecond / numberOfMillisecondsPerSecond;
		
		long s, fs;
		s = (second + fmi) % numberOfSecondsPerMinute;
		fs = (second + fmi) / numberOfSecondsPerMinute;
		
		long m, fm;
		m = (minute + fs) % numberOfMinutesPerHour;
		fm = (minute + fs) / numberOfMinutesPerHour;
		
		
		long h, fh;
		h = (hour + fm) % numberOfHoursPerDay; // hour of the day
		fh = (hour + fm) / numberOfHoursPerDay; // number of days
		
		this.hour = h;
		this.minute = m;
		this.second = s;
		this.millisecond = mi;
		
		return this;
	}
}