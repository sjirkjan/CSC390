package db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MissionaryData {
	public static DBBuilder db;
	Connection conn = null;
 	PreparedStatement psInsert = null;
 	PreparedStatement psQuery = null;
 	static Statement s = null;
 	static ResultSet rs = null;
	 
 	public static void main(String[] args)
 	{
 		System.out.println("MissionaryData");
 		db = new DBBuilder();
 		db.printMissionaryTable();
 		db.printDonorTable();
 		
 		missionaryAllContributionsTable("Jolie Jin");
//		donor_missionaryContributionTable("Tianna Toribio","Angelo Albarado");
 		donor_missionaryContributionTable(" Dorinda Dave","Isreal Inabinet");
 		donor_missionaryContributionTable(" Dorinda Dave","Jolie Jin");
 		db.disconnect();
 	}
 	
 	//Selects transactions from donor to all missionaries
	public static void missionaryAllContributionsTable(String missionary)
	{
		System.out.println("All Contributions");
		
		try
		{
			s = db.conn.createStatement(); 
			
			rs = s.executeQuery("	SELECT 	donation.donation_id,donation.donation_date,donation.amount,donation.relationship_id,"+
									"		relation.donor_id,relation.relation_id,relation.missionary_id,"+
									"		donor.donor_id,donor.name, " +
									"		missionary.missionary_id,missionary.name AS mname " +
	         						"FROM donation,relation,donor,missionary " +
	         						"WHERE missionary.name = \'" + missionary + "\'"+
	         							"AND relation.missionary_id =  missionary.missionary_id " +
	         							"AND donation.relationship_id = relation.relation_id " +
	         							"AND donor.donor_id = relation.donor_id ORDER BY donor.donor_id");

			while(rs.next())
			{
				double amount = rs.getDouble("amount");
				String date = rs.getString("donation_date");
				String donor = rs.getString("name");
				System.out.println("from:" + donor + " " +amount+" on " + date + " to " + missionary);
			}
	 	}catch(SQLException sqle)
	 	{
	 		DBBuilder.printSQLException(sqle);
	 	}
	}
	
	//Selects transactions to a missionary from a specific donor
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
				System.out.println("from:" +donor + " " +amount+" on " + date + " to " + mname);
			}
	 	}catch(SQLException sqle)
	 	{
	 		DBBuilder.printSQLException(sqle);
	 	}
	}
}
