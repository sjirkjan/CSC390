package entities;

import java.util.Comparator;

public class Donation {
	int donation_id;
	int donor_id;
	int missionary_id;
	double amount;
	Date date;
	
	public Donation(int donation_id, int donor_id, int missionary_id, double donationAmount, Date date)
	{
		this.donation_id = donation_id;
		this.donor_id = donor_id;
		this.missionary_id = missionary_id;
		amount = donationAmount;
		this.date = date;
	}
	
	public int getDonationID()
	{
		return donation_id;
	}
	public int getDonorID()
	{
		return donor_id;
	}
	public int getMissionaryID()
	{
		return missionary_id;
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
	public void setDonorID(int d_id)
	{
		donor_id = d_id;
	}
	public void setMissionaryID(int m_id)
	{
		missionary_id = m_id;
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
		return "" + donation_id + " | " + donor_id + " | " + missionary_id + " | " + amount + " | " + date.toString(); 
	}
}
