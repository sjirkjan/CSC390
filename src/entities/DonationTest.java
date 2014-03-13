package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class DonationTest {

	@Test
	public void testDonation() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		assertEquals(""+d.amount,""+100.0);
		assertEquals(d.donation_id,1);
		assertEquals(d.relation_id,1);
		assertEquals(d.date.toString(),(new Date(2001,1,1)).toString());
	}

	@Test
	public void testGetDonationID() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		assertEquals(d.getDonationID(),1);
	}

	@Test
	public void testGetRelationID() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		assertEquals(d.getRelationID(),1);
	}

	@Test
	public void testGetAmount() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		assertEquals(""+d.getAmount(),""+100.0);
	}

	@Test
	public void testGetDate() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		assertEquals(d.getDate().toString(),(new Date(2001,1,1)).toString());
	}

	@Test
	public void testSetDonationID() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		d.setDonationID(2);
		assertEquals(d.getDonationID(),2);
	}

	@Test
	public void testSetRelationID() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		d.setRelationID(2);
		assertEquals(d.getRelationID(),2);
	}

	@Test
	public void testSetAmount() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		d.setAmount(200);
		assertEquals(""+d.getAmount(),""+200.0);
	}

	@Test
	public void testSetDate() {
		Donation d = new Donation(1,1,100,new Date(2001,1,1));
		d.setDate(new Date(2001,2,2));
		
		assertEquals(d.getDate().toString(),(new Date(2001,2,2)).toString());
	}

}
