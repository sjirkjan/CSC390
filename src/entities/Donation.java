package entities;

public class Donation {
	int donation_id;
	int relation_id;
	double amount;
	Date date;
	
	public Donation(int d_id, int r_id, double donationAmount, Date d)
	{
		donation_id = d_id;
		relation_id = r_id;
		amount = donationAmount;
		date = d;
	}

	public int getDonationID()
	{
		return donation_id;
	}
	public int getRelationID()
	{
		return relation_id;
	}
	public double getAmount()
	{
		return amount;
	}
	public Date getDate()
	{
		return date;
	}
	
	public void setDonationID(int d_id)
	{
		donation_id = d_id;
	}
	public void setRelationID(int r_id)
	{
		relation_id = r_id;
	}
	public void setAmount(double donation_amount)
	{
		amount = donation_amount;
	}
	public void setDate(Date d)
	{
		date = d;
	}
	
	public String toString()
	{
		return "" + donation_id + " | " + relation_id + " | " + amount + " | " + date.toString(); 
	}
}
