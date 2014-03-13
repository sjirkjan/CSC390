package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTest {

	@Test
	public void testDate() {
		Date date = new Date(2001,1,3);
		assertEquals(date.toString(),"2001-1-3");
	}

	@Test
	public void testHasThirtyOneDays() {
		Date day = new Date(2001,1,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,2,1);
		assertEquals(false,day.hasThirtyOneDays());
		day = new Date(2001,3,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,4,1);
		assertEquals(false,day.hasThirtyOneDays());
		day = new Date(2001,5,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,6,1);
		assertEquals(false,day.hasThirtyOneDays());
		day = new Date(2001,7,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,8,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,9,1);
		assertEquals(false,day.hasThirtyOneDays());
		day = new Date(2001,10,1);
		assertEquals(true,day.hasThirtyOneDays());
		day = new Date(2001,11,1);
		assertEquals(false,day.hasThirtyOneDays());
		day = new Date(2001,12,1);
		assertEquals(true,day.hasThirtyOneDays());
	}

	@Test
	public void testHasThirtyDays() {
		Date day = new Date(2001,1,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,2,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,3,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,4,1);
		assertEquals(true,day.hasThirtyDays());
		day = new Date(2001,5,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,6,1);
		assertEquals(true,day.hasThirtyDays());
		day = new Date(2001,7,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,8,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,9,1);
		assertEquals(true,day.hasThirtyDays());
		day = new Date(2001,10,1);
		assertEquals(false,day.hasThirtyDays());
		day = new Date(2001,11,1);
		assertEquals(true,day.hasThirtyDays());
		day = new Date(2001,12,1);
		assertEquals(false,day.hasThirtyDays());
	}

	@Test
	public void testDateToString() {
		Date day = new Date(2001,1,1);
		assertEquals("2001-1-1",day.toString());
	}
}
