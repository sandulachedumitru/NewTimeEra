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
	}

	public HourSystemGeneralization() {
		Hour hour = HourOps.getTimeFromhoursysTohoursys(new Hour24(), this);
		this.hour = hour.hour;
		this.minute = hour.minute;
		this.second = hour.second;
		this.milisecond = hour.milisecond;
	}

	public HourSystemGeneralization(int hour, int minute, int second, int milisecond) {
		Hour hourValidation = validate(hour, minute, second, milisecond);
		System.out.println("hourValidation:" + hourValidation.getHourPretty());
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

		Hour hourValidation = validate(hour, minute, second, milisecond);
		System.out.println("hourValidation:" + hourValidation.getHourPretty());
		Hour newHour = HourOps.getTimeFromhoursysTohoursys(new Hour24(hour, minute, second, milisecond), hourValidation);
		this.hour = newHour.hour;
		this.minute = newHour.minute;
		this.second = newHour.second;
		this.milisecond = newHour.milisecond;
	}
	
	// TODO implements the method; analyze whether the method could be moved to the abstract class Hour
//	private Hour validate(int hour, int minute, int second, int milisecond) {
//		int mi, fmi;
//		mi = milisecond % numberOfMilisecondsPerSecond;
//		fmi = milisecond / numberOfMilisecondsPerSecond;
//		
//		int s, fs;
//		s = second % numberOfSecondsPerMinute; s += fmi;
//		fs = second / numberOfSecondsPerMinute;
//		
//		int m, fm;
//		m = minute % numberOfMinutesPerHour; m += fs;
//		fm = minute / numberOfMinutesPerHour;
//		
////		int h, fh;
////		h = (hour + fm) % numberOfHoursPerDay;
////		fh = (hour + fm) / numberOfHoursPerDay;
//		
//		int h, fh;
//		h = hour % numberOfHoursPerDay; h += fm;
// 		fh = hour / numberOfHoursPerDay;
//		
//		this.hour = h;
//		this.minute = m;
//		this.second = s;
//		this.milisecond = mi;
//		
//		System.out.println("method: " + h + ":" + m + ":" + s + ":" + mi);
//		
//		return this;
//	}

}
