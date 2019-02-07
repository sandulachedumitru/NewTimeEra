package org.miticorp;

/**
 * 
 * @author Dumitru Sandulache - sandulachedumitru@hotmail.com
 * This class abstracts any hour systems
 * 
 */
public abstract class Hour {
	protected long day; // just for special cases
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
	
	protected String getDayAndHourPretty() {
		return day + ":" + hour + ":" + minute + ":" + second + ":" + millisecond;
	}

	/**
	 * This method corrects the values of the hour system.
	 * If the hour system is for example 30h:100min:100sec:1000mili and the hour is 32:12345:12345:12345 then the corrected hour is 5 days 6 hours 68 minutes 57 seconds 345 milliseconds (5:6:68:57:345)
	 * @param hour in the system type
	 * @param minute in the system type
	 * @param second in the system type
	 * @param millisecond in the system type
	 * @return corrected hour
	 */
	protected Hour validatesAndFormatsHour(long hour, long minute, long second, long millisecond) {
		long mi, fmi, s, fs, m, fm, h, fh, day;
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
		day = fh;
		
		this.day = day;
		this.hour = h;
		this.minute = m;
		this.second = s;
		this.millisecond = mi;

		return this;
	}
	
	private Values getValues(long unit, long numberPerUnit, long addition) {
		Values values = new Values();
		long value, frequency;
		
		value = (unit + addition) % numberPerUnit;
		frequency = (unit + addition) / numberPerUnit;
		
		if (value < 0) {
			value = numberPerUnit + value;
			frequency += -1;
		}

		values.value = value;
		values.frequency = frequency;
		return values;
	}
	
	private class Values {
		long value;
		long frequency;
	}
}