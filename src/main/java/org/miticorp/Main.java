package org.miticorp;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hour24(16, 44, 49, 0) --> Hour30");
		Hour24 hour24 = new Hour24(16, 44, 49, 0);
		Hour30 hour30 = (Hour30) HourOps.getTimeFromhoursysTohoursys(hour24, new Hour30());
		System.out.println("getHourInSeconds24: " + hour24.getHourInMilliseconds());
		System.out.println("getHourInSeconds30: " + hour30.getHourInMilliseconds());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("Hour30(20,93,36,816) --> Hour24");
		hour30 = new Hour30(20,93,36,816);
		hour24 = (Hour24) HourOps.getTimeFromhoursysTohoursys(hour30, new Hour24());
		System.out.println("getHourInSeconds30: " + hour30.getHourInMilliseconds());
		System.out.println("getHourInSeconds24: " + hour24.getHourInMilliseconds());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("Hour24.now() AND Hour30.now()");
		hour24 = new Hour24();
		hour30 = new Hour30();
		System.out.println("getHourInSeconds24: " + hour24.getHourInMilliseconds());
		System.out.println("getHourInSeconds30: " + hour30.getHourInMilliseconds());
		System.out.println("getHourPretty24: " + hour24.getHourPretty());
		System.out.println("getHourPretty30: " + hour30.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSysGen(24, 100, 100, 1000).now()");
		HourSystemGeneralization hourSysGen = new HourSystemGeneralization(24, 100, 100, 1000); 
		System.out.println("getHourSysGenInMiliseconds: " + hourSysGen.getHourInMilliseconds());
		System.out.println("getHourSysGenPretty: " + hourSysGen.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSysGen(24, 100, 100, 1000, 23, 99, 99, 1000)");
		hourSysGen = new HourSystemGeneralization(24, 100, 100, 1000, 23, 99, 99, 1000); 
		System.out.println("getHourSysGenInMiliseconds: " + hourSysGen.getHourInMilliseconds());
		System.out.println("getHourSysGenPretty: " + hourSysGen.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization24(24, 60, 60, 1000, 16, 44, 49, 0) --> HourSystemGeneralization30(30, 100, 100, 1000)");
		HourSystemGeneralization hourSysGen24 = new HourSystemGeneralization(24, 60, 60, 1000, 16, 44, 49, 0);
		HourSystemGeneralization hourSysGen30 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen24, new HourSystemGeneralization(30, 100, 100, 1000));
		System.out.println("getHourInMiliseconds24: " + hourSysGen24.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds30: " + hourSysGen30.getHourInMilliseconds());
		System.out.println("getHourPretty24: " + hourSysGen24.getHourPretty());
		System.out.println("getHourPretty30: " + hourSysGen30.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization30(30, 100, 100, 1000, 20, 93, 36, 816) --> HourSystemGeneralization24(24, 60, 60, 1000)");
		hourSysGen30 = new HourSystemGeneralization(30, 100, 100, 1000, 20, 93, 36, 816);
		hourSysGen24 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen30, new HourSystemGeneralization(24, 60, 60, 1000));
		System.out.println("getHourInMiliseconds30: " + hourSysGen30.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds24: " + hourSysGen24.getHourInMilliseconds());
		System.out.println("getHourPretty30: " + hourSysGen30.getHourPretty());
		System.out.println("getHourPretty24: " + hourSysGen24.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization24(24, 60, 60, 1000, 16, 44, 49, 0) --> HourSystemGeneralization3(3, 78, 32, 4321)");
		hourSysGen24 = new HourSystemGeneralization(24, 60, 60, 1000, 16, 44, 49, 0);
		HourSystemGeneralization hourSysGen3 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen24, new HourSystemGeneralization(3, 78, 32, 4321));
		System.out.println("getHourInMiliseconds24: " + hourSysGen24.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds3: " + hourSysGen3.getHourInMilliseconds());
		System.out.println("getHourPretty24: " + hourSysGen24.getHourPretty());
		System.out.println("getHourPretty3: " + hourSysGen3.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization3(3, 78, 32, 4321, 2, 7, 9, 201) --> HourSystemGeneralization7(321, 102033, 999, 9999");
		hourSysGen3 = new HourSystemGeneralization(3, 78, 32, 4321, 2, 7, 9, 201);
		HourSystemGeneralization hourSysGen7 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen3, new HourSystemGeneralization(321, 102033, 999, 9999));
		System.out.println("getHourInMiliseconds3: " + hourSysGen3.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds7: " + hourSysGen7.getHourInMilliseconds());
		System.out.println("getHourPretty3: " + hourSysGen3.getHourPretty());
		System.out.println("getHourPretty7: " + hourSysGen7.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization7(321, 102033, 999, 9999, 223, 101051, 437, 3339) --> HourSystemGeneralization24(24, 60, 60, 1000)");
		hourSysGen7 = new HourSystemGeneralization(321, 102033, 999, 9999, 223, 101051, 437, 3339);
		hourSysGen24 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen7, new HourSystemGeneralization(24, 60, 60, 1000));
		System.out.println("getHourInMiliseconds7: " + hourSysGen7.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds24: " + hourSysGen24.getHourInMilliseconds());
		System.out.println("getHourPretty7: " + hourSysGen7.getHourPretty());
		System.out.println("getHourPretty24: " + hourSysGen24.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSystemGeneralization24(24, 60, 60, 1000, 23, 59, 59, 999) --> HourSystemGeneralization7(400, 1000, 1000, 1000)");
		hourSysGen24 = new HourSystemGeneralization(24, 60, 60, 1000, 23, 59, 59, 999);
		hourSysGen7 = (HourSystemGeneralization) HourOps.getTimeFromhoursysTohoursys(hourSysGen24, new HourSystemGeneralization(400, 1000, 1000, 1000));
		System.out.println("getHourInMiliseconds24: " + hourSysGen24.getHourInMilliseconds());
		System.out.println("getHourInMiliseconds7: " + hourSysGen7.getHourInMilliseconds());
		System.out.println("getHourPretty24: " + hourSysGen24.getHourPretty());
		System.out.println("getHourPretty7: " + hourSysGen7.getHourPretty());
		
		System.out.println();System.out.println();
		
		System.out.println("HourSysGen(24, 100, 100, 1000, -1, -1, 101, 0)");
		hourSysGen = new HourSystemGeneralization(24, 100, 100, 1000, -1, -1, 101, 0); 
		System.out.println("getHourSysGenInMiliseconds: " + hourSysGen.getHourInMilliseconds());
		System.out.println("getHourSysGenPretty: " + hourSysGen.getHourPretty());
	}

}
