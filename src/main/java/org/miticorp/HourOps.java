package org.miticorp;

import java.util.ArrayList;

//TODO java doc and test classes
public class HourOps {
	
	public static float getTransformationFactor(Hour fromHour, Hour toHour) {
		return (float) toHour.numberOfMillisecondsPerDay / (float) fromHour.numberOfMillisecondsPerDay;
	}
	
	public static Hour getTimeFromhoursysTohoursys(Hour fromHour, Hour toHour) {
		if (fromHour == null || toHour == null) throw new IllegalArgumentException("One of the arguments is null.");
		boolean flag = false;

		long fromHourInMilliseconds = fromHour.getHourInMilliseconds();
		long toHourInMilliseconds = (long) (fromHourInMilliseconds * getTransformationFactor(fromHour, toHour));

		ArrayList<Long> array = new ArrayList<>();
		long dividend, divisor, result, rest;
		
		long [] divisors = {toHour.numberOfMillisecondsPerSecond, toHour.numberOfSecondsPerMinute, toHour.numberOfMinutesPerHour, toHour.numberOfHoursPerDay};
		int index = 0;

		dividend = toHourInMilliseconds;
		
		// dividend  / divisor = result + rest
		// dividend, divisor, result, rest are long type
		while (!flag) {
			divisor = divisors[index++];
			result = dividend / divisor;
			rest = dividend % divisor;

			if (result == 0) flag = !flag;
			dividend = result;
			array.add(rest);
		}
		
		switch (array.size()) {
			case 4:		toHour.millisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = array.get(2);
						toHour.hour = array.get(3);
						break;
			case 3:		toHour.millisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = array.get(2);
						toHour.hour = 0;
						break;
			case 2:		toHour.millisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = 0;
						toHour.hour = 0;
						break;
			case 1:		toHour.millisecond = array.get(0);
						toHour.second = 0;
						toHour.minute = 0;
						toHour.hour = 0;
						break;
			default: 	toHour.millisecond = 0;
						toHour.second = 0;
						toHour.minute = 0;
						toHour.hour = 0;
						break;

		}
		return toHour;
	}

}
