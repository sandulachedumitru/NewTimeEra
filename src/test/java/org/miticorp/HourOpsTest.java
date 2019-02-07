package org.miticorp;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HourOpsTest {
	private static final Logger LOG = LoggerFactory.getLogger(HourTest.class);
	private static final String exceptionMessage = "One of the arguments is null.";
	private static final String testSeparator = "-------------------------------------------------------------------------------------------------";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testGetTransformationFactor() {
		int noOfDecimals = 100000;
		
		float fromMilliseconds = 24 * 60 * 60 * 1000;
		float toMilliseconds = 30 * 100 * 100 * 1000;
		long expectedFactor = (long) (toMilliseconds / fromMilliseconds * noOfDecimals);
		float factor = (float) expectedFactor / (float) noOfDecimals;
		
		Hour fromHour = new Hour24();
		Hour toHour = new Hour30();
		
		long transfFactor = (long) (HourOps.getTransformationFactor(fromHour, toHour) * noOfDecimals);
		
		printLog(fromHour, toHour);
		LOG.info("The transformation factor is: {}", factor);
		LOG.info(testSeparator);
		
		assertEquals(expectedFactor, transfFactor);
	}
	
	@Test
	public void testGetTransformationFactorWithException() {
		Hour fromHour = null;
		Hour toHour = null;
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(exceptionMessage);
		
		HourOps.getTransformationFactor(fromHour, toHour);
	}

	@Test
	public void testGetTimeFromhoursysTohoursys() {
		// tests 3 cycles of a day: i=1 --> beginning of the day, i=2 --> day/2, i=3 --> dau/3 (ex. for hour system type 12:60:60:1000 the 3 cycles are: 0:0:0:0, 12:0:0:0, 8:0:0:0
		for (long i = 1; i <= 3; i++)
			testCyclesOfDay(i);
		
		long sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1;
		long sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2;
		long day1, day2;
		Hour24 fromHour24, toHour24, toCompareHour24;
		Hour30 fromHour30, toHour30, toCompareHour30;
		HourSystemGeneralization fromHourSysGen, toHourSysGen, toComapareHourSysGen;
		HourDTO unformattedHourDTO, expectedFormatHourDTO;
		
		// Hour24(16, 44, 49, 0) --> Hour30(20, 93, 36, 816)
		h1 = 16; m1 = 44; s1 = 49; mi1 = 0;
		h2 = 20; m2 = 93; s2 = 36; mi2 = 816;
		fromHour24 = new Hour24(h1, m1, s1, mi1);
		toHour30 = new Hour30();
		toCompareHour30 = new Hour30(h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHour24, toHour30, toCompareHour30);
		
		// Hour30(20, 93, 36, 816) --> Hour24(16, 44, 49, 0)
		h1 = 20; m1 = 93; s1 = 36; mi1 = 816;
		h2 = 16; m2 = 44; s2 = 49; mi2 = 0;
		fromHour30 = new Hour30(h1, m1, s1, mi1);
		toHour24 = new Hour24();
		toCompareHour24 = new Hour24(h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHour30, toHour24, toCompareHour24);
		
		// HourSystemGeneralization24(24, 60, 60, 1000, 16, 44, 49, 0) --> HourSystemGeneralization30(30, 100, 100, 1000, 20, 93, 36, 816)
		sysH1 = 24;	sysM1 = 60;		sysS1 = 60;		sysMi1 = 1000;	h1 = 16; m1 = 44; s1 = 49; mi1 = 0;
		sysH2 = 30;	sysM2 = 100;	sysS2 = 100;	sysMi2 = 1000;	h2 = 20; m2 = 93; s2 = 36; mi2 = 816;
		fromHourSysGen = new HourSystemGeneralization(sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1);
		toHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2);
		toComapareHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHourSysGen, toHourSysGen, toComapareHourSysGen);
		
		// HourSystemGeneralization30(30, 100, 100, 1000, 20, 93, 36, 816) --> HourSystemGeneralization3(3, 78, 32, 4321, 2, 7, 9, 203)
		sysH1 = 30;	sysM1 = 100;	sysS1 = 100;	sysMi1 = 1000;	h1 = 20;	m1 = 93;	s1 = 36;	mi1 = 816;
		sysH2 = 3;	sysM2 = 78;		sysS2 = 32;		sysMi2 = 4321;	h2 = 2;		m2 = 7;		s2 = 9;		mi2 = 203;
		fromHourSysGen = new HourSystemGeneralization(sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1);
		toHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2);
		toComapareHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHourSysGen, toHourSysGen, toComapareHourSysGen);
		
		// HourSystemGeneralization3(3, 78, 32, 4321, 2, 7, 9, 203) --> HourSystemGeneralization321(321, 102033, 999, 9999, 223, 101053, 117, 2233)
		sysH1 = 3;		sysM1 = 78;		sysS1 = 32;		sysMi1 = 4321;	h1 = 2;		m1 = 7;			s1 = 9;		mi1 = 203;
		sysH2 = 321;	sysM2 = 102033;	sysS2 = 999;	sysMi2 = 9999;	h2 = 223;	m2 = 101053;	s2 = 117;	mi2 = 2233;
		fromHourSysGen = new HourSystemGeneralization(sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1);
		toHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2);
		toComapareHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHourSysGen, toHourSysGen, toComapareHourSysGen);
		
		// HourSystemGeneralization321(321, 102033, 999, 9999, 223, 101053, 117, 2233) --> HourSystemGeneralization24(24, 60, 60, 1000, 16, 44, 49, 0)
		sysH1 = 321;	sysM1 = 102033;	sysS1 = 999;	sysMi1 = 9999;	h1 = 223;	m1 = 101053;	s1 = 117;	mi1 = 2233;
		sysH2 = 24;		sysM2 = 60;		sysS2 = 60;		sysMi2 = 1000;	h2 = 16;	m2 = 44;		s2 = 49;	mi2 = 0;
		fromHourSysGen = new HourSystemGeneralization(sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1);
		toHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2);
		toComapareHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2); 
		testTransformationBetweenHourSystems(fromHourSysGen, toHourSysGen, toComapareHourSysGen);
		
		// Hour24.now() --> Hour30.now()
		fromHour24 = new Hour24();
		toHour30 = new Hour30();
		toCompareHour30 = new Hour30(); 
		testTransformationBetweenHourSystems(fromHour24, toHour30, toCompareHour30);
		
		// Hour30.now() --> Hour24.now()
		fromHour30 = new Hour30();
		toHour24 = new Hour24();
		toCompareHour24 = new Hour24(); 
		testTransformationBetweenHourSystems(fromHour30, toHour24, toCompareHour24);
		
		// HourSystemGeneralization24.now() --> HourSystemGeneralization30.now()
		fromHourSysGen = new HourSystemGeneralization(sysH1, sysM1, sysS1, sysMi1);
		toHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2);
		toComapareHourSysGen = new HourSystemGeneralization(sysH2, sysM2, sysS2, sysMi2); 
		testTransformationBetweenHourSystems(fromHourSysGen, toHourSysGen, toComapareHourSysGen);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 12, 12, 12, 123)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 12; m1 = 12; s1 = 12; mi1 = 123;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 0; h2 = 12; m2 = 12; s2 = 12; mi2 = 123;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -12, -12, -12, -123)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -12; m1 = -12; s1 = -12; mi1 = -123;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -1; h2 = 11; m2 = 87; s2 = 87; mi2 = 877;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -12, 12, -12, 123)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -12; m1 = 12; s1 = -12; mi1 = 123;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -1; h2 = 12; m2 = 11; s2 = 88; mi2 = 123;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 12, -12, 12, -123)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 12; m1 = -12; s1 = 12; mi1 = -123;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 0; h2 = 11; m2 = 88; s2 = 11; mi2 = 877;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 24, 100, 100, 1000)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 24; m1 = 100; s1 = 100; mi1 = 1000;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 1; h2 = 1; m2 = 1; s2 = 1; mi2 = 0;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -24, -100, -100, -1000)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -24; m1 = -100; s1 = -100; mi1 = -1000;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -2; h2 = 22; m2 = 98; s2 = 99; mi2 = 0;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 24, -100, 100, -1000)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 24; m1 = -100; s1 = 100; mi1 = -1000;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 0; h2 = 23; m2 = 0; s2 = 99; mi2 = 0;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -24, 100, -100, 1000)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -24; m1 = 100; s1 = -100; mi1 = 1000;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -1; h2 = 0; m2 = 99; s2 = 1; mi2 = 0;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -1234, -1234, -1234, -123456)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -1234; m1 = -1234; s1 = -1234; mi1 = -12345;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -52; h2 = 1; m2 = 53; s2 = 53; mi2 = 655;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 1234, 1234, 1234, 123456)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 1234; m1 = 1234; s1 = 1234; mi1 = 12345;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 51; h2 = 22; m2 = 46; s2 = 46; mi2 = 345;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, -1234, 1234, -1234, 123456)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = -1234; m1 = 1234; s1 = -1234; mi1 = 12345;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = -51; h2 = 2; m2 = 21; s2 = 78; mi2 = 345;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 100, 100, 1000, 1234, -1234, 1234, -123456)
		sysH1 = 24;	sysM1 = 100; sysS1 = 100; sysMi1 = 1000; day1 = 0; h1 = 1234; m1 = -1234; s1 = 1234; mi1 = -12345;
		sysH2 = 24;	sysM2 = 100; sysS2 = 100; sysMi2 = 1000; day2 = 50; h2 = 21; m2 = 78; s2 = 21; mi2 = 655;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
		
		// format HourSystemGeneralization(24, 60, 60, 1000, 1234, -1234, 1234, -123456)
		sysH1 = 24;	sysM1 = 60; sysS1 = 60; sysMi1 = 1000; day1 = 0; h1 = 1234; m1 = -1234; s1 = 1234; mi1 = -12345;
		sysH2 = 24;	sysM2 = 60; sysS2 = 60; sysMi2 = 1000; day2 = 50; h2 = 13; m2 = 46; s2 = 21; mi2 = 655;
		unformattedHourDTO = new HourDTO(sysH1, sysM1, sysS1, sysMi1, day1, h1, m1, s1, mi1);
		expectedFormatHourDTO = new HourDTO(sysH2, sysM2, sysS2, sysMi2, day2, h2, m2, s2, mi2);
		hourFormatting(unformattedHourDTO, expectedFormatHourDTO);
	}
	
	@Test
	public void testGetTimeFromhoursysTohoursysWhithException() {
		Hour fromHour = null;
		Hour toHour = null;
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(exceptionMessage);
		
		HourOps.getTimeFromhoursysTohoursys(fromHour, toHour);
	}

	private static void printLog(Hour fromHour,Hour toHour) {
		LOG.info("The hour system which must be transformed is of type {}h:{}m:{}s:{}mil and the hour system which is transformed is of type {}h:{}m:{}s:{}mil", 
				fromHour.numberOfHoursPerDay, 
				fromHour.numberOfMinutesPerHour, 
				fromHour.numberOfSecondsPerMinute, 
				fromHour.numberOfMillisecondsPerSecond,
				toHour.numberOfHoursPerDay, 
				toHour.numberOfMinutesPerHour, 
				toHour.numberOfSecondsPerMinute, 
				toHour.numberOfMillisecondsPerSecond);
		LOG.info("The selected hours are: {}h:{}m:{}s:{}mi and {}h:{}m:{}s:{}mi.", fromHour.hour, fromHour.minute, fromHour.second, fromHour.millisecond, toHour.hour, toHour.minute, toHour.second, toHour.millisecond);
	}
	
	private static void testCyclesOfDay(long n) {
		long h24 = 24, m24 = 0, s24 = 0, mi24 = 0;
		long h30 = 30, m30 = 0, s30 = 0, mi30 = 0;
		if (n == 1) {h24 = 0; h30 = 0;}
		Hour24 hour24 = new Hour24(h24 / n, m24, s24, mi24);
		Hour30 hour30 = new Hour30();
		
		HourOps.getTimeFromhoursysTohoursys(hour24, hour30);
		
		printLog(hour24, hour30);
		LOG.info("Hour24: {}", hour24.getHourPretty());
		LOG.info("Hour30: {}", hour30.getHourPretty());
		LOG.info(testSeparator);
		
		assertEquals(hour30.hour,			h30 / n);
		assertEquals(hour30.minute,			m30);
		assertEquals(hour30.second,			s30);
		assertEquals(hour30.millisecond,	mi30);
	}
	
	private static void testTransformationBetweenHourSystems(Hour fromHour, Hour toHour, Hour toCompareHour) {
		long sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1;
		long sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2;
		
		h1 = fromHour.hour; 		m1 = fromHour.minute; 		s1 = fromHour.second; 		mi1 = fromHour.millisecond;
		h2 = toCompareHour.hour; 	m2 = toCompareHour.minute; 	s2 = toCompareHour.second; 	mi2 = toCompareHour.millisecond;
		sysH1 = fromHour.numberOfHoursPerDay;		sysM1 = fromHour.numberOfMinutesPerHour;		sysS1 = fromHour.numberOfSecondsPerMinute;		sysMi1 = fromHour.numberOfMillisecondsPerSecond;
		sysH2 = toCompareHour.numberOfHoursPerDay;	sysM2 = toCompareHour.numberOfMinutesPerHour;	sysS2 = toCompareHour.numberOfSecondsPerMinute;	sysMi2 = toCompareHour.numberOfMillisecondsPerSecond;
		
		toHour = HourOps.getTimeFromhoursysTohoursys(fromHour, toHour);
		
		LOG.info("{}({}:{}:{}:{}, {}:{}:{}:{}) --> {}({}:{}:{}:{}, {}:{}:{}:{})", 
				fromHour.getClass().getSimpleName(), sysH1, sysM1, sysS1, sysMi1, h1, m1, s1, mi1, 
				toCompareHour.getClass().getSimpleName(),sysH2, sysM2, sysS2, sysMi2, h2, m2, s2, mi2);
		printLog(fromHour, toHour);
		LOG.info("{}: {}", fromHour.getClass().getSimpleName(), fromHour.getDayAndHourPretty());
		LOG.info("{}: {}", toHour.getClass().getSimpleName(), toHour.getDayAndHourPretty());
		LOG.info(testSeparator);
		
		assertEquals(toHour.hour,			h2);
		assertEquals(toHour.minute,			m2);
		assertEquals(toHour.second,			s2);
		assertTrue(Math.abs(toHour.millisecond/10 - mi2/10) <= 10); // divided by 10 to increase precision. The last digit will not be considered because test could fail due to difference in millisecond last digit.
	}
	
	private static void hourFormatting(HourDTO unformattedHourDTO, HourDTO expectedFormatHourDTO) {
		LOG.info("The hour system which must be formated is of type {}h:{}m:{}s:{}mil.", unformattedHourDTO.sysH, unformattedHourDTO.sysM, unformattedHourDTO.sysS, unformattedHourDTO.sysMi); 
		LOG.info("Time before formatting: {}day:{}h:{}m:{}s:{}mi.", 0, unformattedHourDTO.h, unformattedHourDTO.m, unformattedHourDTO.s, unformattedHourDTO.mi);
		HourSystemGeneralization hourSysGen = new HourSystemGeneralization(
				unformattedHourDTO.sysH, unformattedHourDTO.sysM, unformattedHourDTO.sysS, unformattedHourDTO.sysMi, 
				unformattedHourDTO.h, unformattedHourDTO.m, unformattedHourDTO.s, unformattedHourDTO.mi); 
		LOG.info("Time after formatting is: {}day:{}h:{}m:{}s:{}mi.", hourSysGen.day, hourSysGen.hour, hourSysGen.minute, hourSysGen.second, hourSysGen.millisecond);
		LOG.info(testSeparator);
		
		assertEquals(hourSysGen.day, expectedFormatHourDTO.day);
		assertEquals(hourSysGen.hour, expectedFormatHourDTO.h);
		assertEquals(hourSysGen.minute, expectedFormatHourDTO.m);
		assertEquals(hourSysGen.second, expectedFormatHourDTO.s);
		assertEquals(hourSysGen.millisecond, expectedFormatHourDTO.mi);
	}
	
	private class HourDTO {
		long sysH, sysM, sysS, sysMi, day, h, m, s, mi;
		HourDTO(long sysH,long sysM,long sysS,long sysMi,long day, long h,long m,long s,long mi) {
			this.day = day;
			this.sysH = sysH; this.sysM = sysM; this.sysS = sysS; this.sysMi = sysMi;
			this.h = h; this.m = m; this.s = s; this.mi = mi;
		}
	}
}
