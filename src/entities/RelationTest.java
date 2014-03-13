package entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class RelationTest {

	@Test
	public void testRelation() {
		Relation r = new Relation(1,1,1);
		assertEquals(r.relation_id,1);
		assertEquals(r.donor_id,1);
		assertEquals(r.missionary_id,1);
	}

	@Test
	public void testGetRelationID() {
		Relation r = new Relation(1,1,1);
		assertEquals(r.getRelationID(), 1);
	}

	@Test
	public void testGetDonorID() {
		Relation r = new Relation(1,1,1);
		assertEquals(r.getDonorID(), 1);
	}

	@Test
	public void testGetMissionaryID() {
		Relation r = new Relation(1,1,1);
		assertEquals(r.getMissionaryID(), 1);
	}

	@Test
	public void testSetRelationID() {
		Relation r = new Relation(1,1,1);
		r.setRelationID(2);
		assertEquals(r.getRelationID(), 2);
	}

	@Test
	public void testSetDonorID() {
		Relation r = new Relation(1,1,1);
		r.setDonorID(2);
		assertEquals(r.getDonorID(), 2);
	}

	@Test
	public void testSetMissionaryID() {
		Relation r = new Relation(1,1,1);
		r.setMissionaryID(2);
		assertEquals(r.getMissionaryID(), 2);
	}

}
