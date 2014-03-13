package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class DonorTest {

	@Test
	public void testDonor() {
		Donor m = new Donor(1,"Chuck Norris");
		assertEquals(m.donor_name,"Chuck Norris");
		assertEquals(m.donor_id,1);
	}

	@Test
	public void testGetDonorName() {
		Donor m = new Donor(1,"Chuck Norris");
		assertEquals(m.getDonorName(),"Chuck Norris");
	}

	@Test
	public void testGetDonorID() {
		Donor m = new Donor(1,"Chuck Norris");
		assertEquals(m.donor_id,1);
	}

	@Test
	public void testSetDonorName() {
		Donor m = new Donor(1,"Chuck Norris");
		m.setDonorName("Bruce Lee");
		assertEquals(m.getDonorName(),"Bruce Lee");
	}

	@Test
	public void testSetDonorID() {
		Donor m = new Donor(1,"Chuck Norris");
		m.setDonorID(2);
		assertEquals(m.getDonorID(),2);
	}
}
