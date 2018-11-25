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

	// TODO negative values need to be analyzed
	// not working, wrong values
	protected Hour validatesAndFormatsHour(long hour, long minute, long second, long millisecond) {
		long mi, fmi, s, fs, m, fm, h, fh;

		mi = millisecond % numberOfMillisecondsPerSecond;
		fmi = millisecond / numberOfMillisecondsPerSecond;

		if (second < 0) {
			s = (numberOfSecondsPerMinute + second + fmi) % numberOfSecondsPerMinute;
			fs = (numberOfSecondsPerMinute + second + fmi) / numberOfSecondsPerMinute;
		} else {
			if (second + fmi >= 0) {
				s = (second + fmi) % numberOfSecondsPerMinute;
				fs = (second + fmi) / numberOfSecondsPerMinute;
			} else {
				s = (numberOfSecondsPerMinute - (second + fmi)) % numberOfSecondsPerMinute;
				fs = -(second + fmi) / numberOfSecondsPerMinute; // ????
			}
		}

		if (minute < 0) {
			m = (numberOfMinutesPerHour + minute + fs) % numberOfMinutesPerHour;
			fm = (numberOfMinutesPerHour + minute + fs) / numberOfMinutesPerHour;
		} else {
			if (minute + fs >= 0) {
				m = (minute + fs) % numberOfMinutesPerHour;
				fm = (minute + fs) / numberOfMinutesPerHour;
			} else {
				m = (numberOfMinutesPerHour - minute + fs) % numberOfMinutesPerHour;
				fm = -(minute + fs) / numberOfMinutesPerHour; // ????
			}
		}

		if (hour < 0) {
			h = (numberOfHoursPerDay + hour + fm) % numberOfHoursPerDay; // hour of the day
			fh = (numberOfHoursPerDay + hour + fm) / numberOfHoursPerDay; // number of days
		} else {
			if (hour + fm >= 0) {
				h = (hour + fm) % numberOfHoursPerDay; // hour of the day
				fh = (hour + fm) / numberOfHoursPerDay; // number of days
			} else {
				h = (numberOfHoursPerDay - hour + fm) % numberOfHoursPerDay; // hour of the day
				fh = -(hour + fm) / numberOfHoursPerDay; // number of days // ????
			}
		}

		this.hour = h;
		this.minute = m;
		this.second = s;
		this.millisecond = mi;

		return this;
	}
}