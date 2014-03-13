package entities;

public class Donor {
	String donor_name;
	int donor_id;
	
	public Donor(int d_id, String name)
	{
		donor_name = name;
		donor_id = d_id;
	}

	public String getDonorName()
	{
		return donor_name;
	}
	public int getDonorID()
	{
		return donor_id;
	}
	
	public void setDonorName(String name)
	{
		donor_name = name;
	}
	public void setDonorID(int d_id)
	{
		donor_id=d_id;
	}
	public String toString()
	{
		return "" + donor_id + " | " + donor_name;
	}
}
