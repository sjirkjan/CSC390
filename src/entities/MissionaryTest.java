package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class MissionaryTest {

	@Test
	public void testMissionary() {
		Missionary m = new Missionary(1,"Chuck Norris");
		assertEquals(m.missionary_name,"Chuck Norris");
		assertEquals(m.missionary_id,1);
	}

	@Test
	public void testGetMissionaryName() {
		Missionary m = new Missionary(1,"Chuck Norris");
		assertEquals(m.getMissionaryName(),"Chuck Norris");
	}

	@Test
	public void testGetMissionaryID() {
		Missionary m = new Missionary(1,"Chuck Norris");
		assertEquals(m.missionary_id,1);
	}

	@Test
	public void testSetMissionaryName() {
		Missionary m = new Missionary(1,"Chuck Norris");
		m.setMissionaryName("Bruce Lee");
		assertEquals(m.getMissionaryName(),"Bruce Lee");
	}

	@Test
	public void testSetMissionaryID() {
		Missionary m = new Missionary(1,"Chuck Norris");
		m.setMissionaryID(2);
		assertEquals(m.getMissionaryID(),2);
	}
}
