package org.miticorp;

public class Main {

	public static void main(String[] args) {
		Hour24 hour24 = new Hour24(16, 44, 49, 0);
		Hour30 hour30 = (Hour30) HourOps.getTimeFromhoursysTohoursys(hour24, new Hour30());
		System.out.println("getHourInSeconds24: " + hour24.getHourInSeconds());
		System.out.println("getHourInSeconds30: " + hour30.getHourInSeconds());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
		
		System.out.println();System.out.println();
		
		hour30 = new Hour30(20,93,36,0);
		hour24 = (Hour24) HourOps.getTimeFromhoursysTohoursys(hour30, new Hour24());
		System.out.println("getHourInSeconds30: " + hour30.getHourInSeconds());
		System.out.println("getHourInSeconds24: " + hour24.getHourInSeconds());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		
		System.out.println();System.out.println();
		
		hour24 = new Hour24();
		hour30 = new Hour30();
		System.out.println("getHourInSeconds24: " + hour24.getHourInSeconds());
		System.out.println("getHourInSeconds30: " + hour30.getHourInSeconds());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
	}

}
