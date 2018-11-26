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

	protected Hour validatesAndFormatsHour(long hour, long minute, long second, long millisecond) {
		long mi, fmi, s, fs, m, fm, h, fh;
		Values values;

		values = getValues(millisecond, numberOfMillisecondsPerSecond, 0L);
		mi = values.value;
		fmi = values.frequency;

		values = getValues(second, numberOfSecondsPerMinute, fmi);
		s = values.value;
		fs = values.frequency;
		
		values = getValues(minute, numberOfMinutesPerHour, fs);
		m = values.value;
		fm = values.frequency;

		values = getValues(hour, numberOfHoursPerDay, fm);
		h = values.value; // hour of the day
		fh = values.frequency; // number of days

		this.hour = h;
		this.minute = m;
		this.second = s;
		this.millisecond = mi;

		return this;
	}
	
	/*
	 * Algorithm for millisecond (identical for: second, minute, hour)
	 * millisecond 	= -1000	< 0	--> s == 0 	--> 1000 + (-1000)	= 0		--> mi = 0;		fmi = -1
	 * millisecond 	= -1	< 0 --> s > 0 	--> 1000 + (-1)		= 999	--> mi = 999;	fmi = 0;
	 * millisecond 	= -1001	< 0 --> s < 0 	--> 1000 + (-1001)	= -1	--> mi = 999	fmi = -1
	 * millisecond 	= 999	> 0 --> mi = 999; fmi = 0;
	 * 				= 2222			mi = 222; fmi = 2;
	 * millisecond = 0 			--> mi = 0;	 fmi = 0
	 */
	private Values getValues(long unit, long numberPerUnit, long addition) {
		Values values = new Values();
		long sum = unit + numberPerUnit + addition;
		if (unit < 0) {
			if (sum == 0) {
				values.value = 0;
				values.frequency = -1;
			} else if (sum > 0) {
				values.value = sum;
				values.frequency = 0;
			} else if (sum < 0) {
				values.value = sum % numberPerUnit + numberPerUnit;
				values.frequency = (unit + addition) / numberPerUnit;
			}
		} else {
			values.value = (unit + addition) % numberPerUnit;
			values.frequency = (unit + addition) / numberPerUnit;
		}
		
		return values;
	}
	
	private class Values {
		long value;
		long frequency;
	}
}