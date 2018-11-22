package org.miticorp;

import java.util.ArrayList;

//TODO java doc and test classes
public class HourOps {

	public static float getTransformationFactor(Hour fromHour, Hour toHour) {
		return (float) toHour.numberOfSecondsPerDay / (float) fromHour.numberOfSecondsPerDay;
	}
	
	public static float getTransformationFactor2(Hour fromHour, Hour toHour) {
		return (float) toHour.numberOfMilisecondsPerDay / (float) fromHour.numberOfMilisecondsPerDay;
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
	
	// TODO validatesAndFormatsHour method could be moved here in this class
	public static Hour getTimeFromhoursysTohoursys2(Hour fromHour, Hour toHour) {
		if (fromHour == null || toHour == null) return null;
		if (fromHour.getClass().equals(toHour.getClass())) return fromHour;
		
		boolean flag = false;

		int fromHourInMiliseconds = fromHour.getHourInMiliseconds();
		int toHourInMiliseconds = (int) (fromHourInMiliseconds * getTransformationFactor2(fromHour, toHour));

		ArrayList<Integer> array = new ArrayList<>();
		int dividend, divisor, result, rest;
		
		int [] divisor2 = {toHour.numberOfMilisecondsPerSecond, toHour.numberOfSecondsPerMinute, toHour.numberOfMinutesPerHour, toHour.numberOfHoursPerDay};
		int index = 0;

		dividend = toHourInMiliseconds;
		divisor = toHour.numberOfSecondsPerMinute;

		
		
		// dividend  / divisor = result + rest
		// dividend, divisor, result, rest are integers
		while (!flag) {
			// if (index >= divisor2.length) index = 0;
			divisor = divisor2[index++];
			result = dividend / divisor;
			rest = dividend % divisor;

			if (result == 0) flag = !flag;
			dividend = result;
			array.add(rest);
		}
		
		System.out.println("divisor2: " + divisor2);
		
		switch (array.size()) {
			case 4:		toHour.milisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = array.get(2);
						toHour.hour = array.get(3);
						break;
			case 3:		toHour.milisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = array.get(2);
						toHour.hour = 0;
						break;
			case 2:		toHour.milisecond = array.get(0);
						toHour.second = array.get(1);
						toHour.minute = 0;
						toHour.hour = 0;
						break;
			case 1:		toHour.milisecond = array.get(0);
						toHour.second = 0;
						toHour.minute = 0;
						toHour.hour = 0;
						break;
			default: 	toHour.milisecond = 0;
						toHour.second = 0;
						toHour.minute = 0;
						toHour.hour = 0;
						break;

		}
		

		return toHour;
	}

}
