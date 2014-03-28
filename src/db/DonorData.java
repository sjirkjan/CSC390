package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Date;
import entities.Donation;
import entities.Donor;


public class DonorData {
	public static DBBuilder db;
	Connection conn = null;
 	PreparedStatement psInsert = null;
 	PreparedStatement psQuery = null;
 	static Statement s = null;
 	static ResultSet rs = null;
 	static ArrayList<Donation> donationList = new ArrayList<Donation>();
 	
 	//Selects transactions from donor to all missionaries
	public static ArrayList<Donation> donorAllContributionsTable(Donor donor, DBBuilder db)
	{
		int donor_id = donor.getDonorID();
		try
		{
	 		db = new DBBuilder();

			s = db.conn.createStatement(); 
			
			rs = s.executeQuery("SELECT	* FROM donation,donor " +
	         						"WHERE donor.donor_id = " + donor_id);

			while(rs.next())
			{
				int id = rs.getInt("donation_id");
				int missionary_id = rs.getInt("missionary_id");
				double amount = rs.getDouble("amount");
				Date date = Date.stringToDate(rs.getString("donation_date"));

				Donation donation = new Donation(id,donor_id,missionary_id,amount,date);
				donationList.add(donation);
			}
			
	 	}catch(SQLException sqle)
	 	{
	 		DBBuilder.printSQLException(sqle);
	 	}
		return donationList;
	}
	

	//Selects transactions from donor to specific missionary
	public static void donor_missionaryContributionTable(String donor, String missionary)
	{
		
		try
		{
			s = db.conn.createStatement(); 
			System.out.println("FLAG 1");
			rs = s.executeQuery("	SELECT 	donation.donation_id,donation.donation_date,donation.amount,donation.relationship_id,"+
									"		relation.donor_id,relation.relation_id,"+
									"		donor.donor_id,donor.name, " +
									"		missionary.missionary_id,missionary.name AS mname "+
	         						"FROM donation,relation,donor,missionary " +
	         						"WHERE donor.name = \'" + donor + "\' "+
	         							"AND relation.donor_id =  donor.donor_id " +
	         							"AND relation.missionary_id =  missionary.missionary_id " +
	         							"AND donation.relationship_id = relation.relation_id " +
	         							"AND missionary.name = \'" + missionary + "\' ORDER BY donation.donation_date");

			while(rs.next())
			{
				double amount = rs.getDouble("amount");
				String mname = rs.getString("mname");
				String date = rs.getString("donation_date");
				//String name = rs.getString("name");
				System.out.println(amount+" on " + date + " to " + mname);
			}
	 	}catch(SQLException sqle)
	 	{
	 		DBBuilder.printSQLException(sqle);
	 	}
	}
}
