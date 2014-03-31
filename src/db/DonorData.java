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
			
			rs = s.executeQuery("SELECT	* FROM donation " +
	         						"WHERE donation.donor_id = " + donor_id);

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
}
