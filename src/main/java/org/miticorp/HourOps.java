package org.miticorp;

import java.util.ArrayList;

//TODO java doc and test classes
public class HourOps {

	public static float getTransformationFactor(Hour fromHour, Hour toHour) {
		return (float) toHour.numberOfSecondsPerDay / (float) fromHour.numberOfSecondsPerDay;
	}

	public static Hour getTimeFromhoursysTohoursys(Hour fromHour, Hour toHour) {
		if (fromHour == null || toHour == null) return null;
		if (fromHour.getClass().equals(toHour.getClass())) return fromHour;
		
		boolean flag = false;

		int fromHourInSeconds = fromHour.getHourInSeconds();
		int toHourInSeconds = (int) (fromHourInSeconds * getTransformationFactor(fromHour, toHour));

		ArrayList<Integer> array = new ArrayList<>();
		int dividend, divisor, result, rest;

		dividend = toHourInSeconds;
		divisor = toHour.numberOfSecondsPerMinute;


		// dividend  / divisor = result + rest
		// dividend, divisor, result, rest are integers
		while (!flag) {
			result = dividend / divisor;
			rest = dividend % divisor;

			if (result == 0) flag = !flag;
			dividend = result;
			array.add(rest);
		}
		
		switch (array.size()) {
			case 3:		toHour.second = array.get(0);
						toHour.minute = array.get(1);
						toHour.hour = array.get(2);
						break;
			case 2:		toHour.second = array.get(0);
						toHour.minute = array.get(1);
						toHour.hour = 0;
						break;
			case 1:		toHour.second = array.get(0);
						toHour.minute = 0;
						toHour.hour = 0;
						break;
			default: 	toHour.second = 0;
						toHour.minute = 0;
						toHour.hour = 0;
						break;
		}

		return toHour;
	}

	public static void main(String[] args) {
		Hour24 hour24 = new Hour24(16, 44, 49, 0);
		Hour30 hour30 = new Hour30();
		System.out.println(hour24.getHourPretty() + " [24] --> [30] " + HourOps.getTimeFromhoursysTohoursys(hour24, hour30).getHourPretty());
		
		hour24 = new Hour24(0, 0, 1, 0);
		hour30 = new Hour30();
		System.out.println(hour24.getHourPretty() + " [24] --> [30] " + HourOps.getTimeFromhoursysTohoursys(hour24, hour30).getHourPretty());
		
		hour24 = new Hour24();
		hour30 = new Hour30();
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());

	}
}
