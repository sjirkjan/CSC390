package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class DonationTest {

	@Test
	public void testDonation() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(""+d.amount,""+100.0);
		assertEquals(d.donation_id,1);
		assertEquals(d.donor_id,1);
		assertEquals(d.missionary_id,1);
		assertEquals(d.date.toString(),(new Date(2001,1,1)).toString());
	}

	@Test
	public void testGetDonationID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(d.getDonationID(),1);
	}
	
	@Test
	public void testGetDonorID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(d.getDonorID(),1);
	}
	
	@Test
	public void testGetMissionaryID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(d.getMissionaryID(),1);
	}

	@Test
	public void testGetAmount() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(""+d.getAmount(),""+100.0);
	}

	@Test
	public void testGetDate() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		assertEquals(d.getDate().toString(),(new Date(2001,1,1)).toString());
	}

	@Test
	public void testSetDonationID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		d.setDonationID(2);
		assertEquals(d.getDonationID(),2);
	}

	@Test
	public void testSetDonorID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		d.setDonorID(2);
		assertEquals(d.getDonorID(),2);
	}

	@Test
	public void testSetMissionaryID() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		d.setMissionaryID(2);
		assertEquals(d.getMissionaryID(),2);
	}
	
	@Test
	public void testSetAmount() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		d.setAmount(200);
		assertEquals(""+d.getAmount(),""+200.0);
	}

	@Test
	public void testSetDate() {
		Donation d = new Donation(1,1,1,100,new Date(2001,1,1));
		d.setDate(new Date(2001,2,2));
		
		assertEquals(d.getDate().toString(),(new Date(2001,2,2)).toString());
	}

}
