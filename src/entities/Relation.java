package entities;

public class Relation {

	int relation_id;
	int donor_id;
	int missionary_id;
	
	public Relation(int r_id, int d_id, int m_id)
	{
		relation_id = r_id;
		donor_id = d_id;
		missionary_id = m_id;
	}

	public int getRelationID()
	{
		return relation_id;
	}
	public int getDonorID()
	{
		return donor_id;
	}
	public int getMissionaryID()
	{
		return missionary_id;
	}
	
	public void setRelationID(int r_id)
	{
		relation_id=r_id;
	}
	public void setDonorID(int d_id)
	{
		donor_id=d_id;
	}
	public void setMissionaryID(int m_id)
	{
		missionary_id=m_id;
	}
	
	public String toString()
	{
		return "" + relation_id + " | " + donor_id + " | " + missionary_id;
	}
}
