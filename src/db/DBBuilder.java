package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import entities.Date;
import entities.Donation;
import entities.Donor;
import entities.Missionary;

public class DBBuilder{
	 /* the default framework is embedded*/
	 private String framework = "embedded";
	 private String protocol = "jdbc:derby:";
	

	 ArrayList<Donor> donors;
	 ArrayList<Missionary> missionaries;
	 ArrayList<Donation> donations;
	 Connection conn = null;
	 PreparedStatement psInsert = null;
	 PreparedStatement psQuery = null;
	 PreparedStatement psRemove = null;
	 Statement s = null;
	 ResultSet rs = null;

	 public DBBuilder()
	 {
		 connect();
	 }
	 
	 public boolean connect() 
	 {
		 System.out.println("DBBuilder starting in " + framework + " mode");

	     try
	     {
	         Properties props = new Properties(); // connection properties
	         props.put("user", "user1");
	         props.put("password", "user1");
	
	         String dbName = "DB"; // the name of the database
	         conn = DriverManager.getConnection(protocol+dbName,props); // Database already exists.
	         System.out.println("Connected to database 2 " + dbName);
	     }catch (SQLException sqle)
	     {
	         printSQLException(sqle);
	         return false;
	     }
	     return true;
	
	 }
	 public void disconnect()
	 {
         if (framework.equals("embedded"))
         {
             try
             {
                 DriverManager.getConnection("jdbc:derby:;shutdown=true");
             }
             catch (SQLException se)
             {
                 if (( (se.getErrorCode() == 50000) && ("XJ015".equals(se.getSQLState()) ))) 
                 {
                	 System.out.println("Derby shut down normally");
                 } else {
                     System.err.println("Derby did not shut down normally");
                     printSQLException(se);
                 }
             }finally {
    	         // release all open resources to avoid unnecessary memory usage
    	         try {
    	             if (rs != null) {
    	                 rs.close();
    	                 rs = null;
    	             }
    	         } catch (SQLException sqle) {printSQLException(sqle);}
    	
    	         //Connection
    	         try {
    	             if (conn != null) {
    	                 conn.close();
    	                 conn = null;
    	             }
    	         } catch (SQLException sqle) {printSQLException(sqle);}
    	     }
         }
	 }

	 public ArrayList<Donor> makeDonorTable()
	 {
		 donors = new ArrayList<Donor>();
		 try
		 {
	         s = conn.createStatement(); // Creating a statement object that we can use for running various SQL statements commands against the database.

	         rs = s.executeQuery("	SELECT donor_id, name " +
	         						"FROM donor " +
	         						"ORDER BY donor_id");

			 while(rs.next())
	         {
	        	 int d_id =rs.getInt("donor_id");
	        	 String name = rs.getString("name");
	        	 donors.add(new Donor(d_id,name));
	        	 System.out.println(d_id + " " + name);
	         }
		 }catch(SQLException sqle)
	     {
	         printSQLException(sqle);
	     }
		 
		 return donors;
	 }
	 public Donor getDonor(String name)
	 {
		 Donor donor;
		 int d_id = 0;
		 try
		 {
	         s = conn.createStatement(); // Creating a statement object that we can use for running various SQL statements commands against the database.

	         rs = s.executeQuery("	SELECT donor_id, name " +
	         						"FROM donor " +
	         						"WHERE name = \'" + name + "\'");

			 rs.next();
			 d_id =rs.getInt("donor_id");

		 }catch(SQLException sqle)
	     {
	         printSQLException(sqle);
	     }
		 
		 donor = new Donor(d_id,name);
		 return donor;
	 }
	 public void printDonorTable()
	 {
		 System.out.println("Donor Table");
		 
		 makeDonorTable();
		 for(Donor i: donors)
			 i.toString();
	 }

	 public ArrayList<Missionary> makeMissionaryTable()
	 {
		 missionaries = new ArrayList<Missionary>();
	 
		 try
		 {
			 s = conn.createStatement(); 
			 rs = s.executeQuery("	SELECT missionary_id, name " +
					 				"FROM missionary " +
					 				"ORDER BY missionary_id");
			 
			 while(rs.next())
			 {
				 int m_id =rs.getInt("missionary_id");
				 String name = rs.getString("name");
				 missionaries.add(new Missionary(m_id,name));
			 }
		 }catch(SQLException sqle)
		 {
			 printSQLException(sqle);
		 }
		 return missionaries ;
	 }
	 public void printMissionaryTable()
	 {
		 System.out.println("Missionary Table");
		 makeMissionaryTable();
		 for(Missionary i: missionaries)
			 System.out.println(i.toString());
	 }
	 public Missionary getMissionary(String name)
	 {
		 Missionary missionary;
		 int m_id = 0;
		 try
		 {
	         s = conn.createStatement(); // Creating a statement object that we can use for running various SQL statements commands against the database.

	         rs = s.executeQuery("	SELECT missionary_id, name " +
	         						"FROM missionary " +
	         						"WHERE name = \'" + name + "\'");

			 rs.next();
			 m_id =rs.getInt("missionary_id");

		 }catch(SQLException sqle)
	     {
	         printSQLException(sqle);
	     }
		 
		 missionary = new Missionary(m_id,name);
		 return missionary;
	 }

	 public ArrayList<Donation> makeDonationTable()
	 {
		 donations = new ArrayList<Donation>();
		 try
		 {
	         s = conn.createStatement(); // Creating a statement object that we can use for running various SQL statements commands against the database.
	         rs = s.executeQuery("	SELECT * FROM donation ORDER BY donation_id");

	         while(rs.next())
	         {
	        	 int donation_id =rs.getInt("donation_id");
	        	 int donor_id = rs.getInt("donor_id");
	        	 int missionary_id = rs.getInt("missionary_id");
	        	 double amount = rs.getDouble("amount");
	        	 String t_date = rs.getString("donation_date");
	        	 donations.add(new Donation(donation_id, donor_id, missionary_id, amount, Date.stringToDate(t_date)));
        	 }
		 }catch(SQLException sqle)
	     {
	         printSQLException(sqle);
	     }
		 
		 return donations;
	 }
	 public void printDonationTable()
	 {
		 System.out.println("Donations Table");
		 makeDonationTable();
		 for(Donation i:donations)
			 System.out.println(i.toString());
	 }
	 public Donation getDonation(double amount, int d_id, int m_id, Date date)
	 {
		 Donation donation;
		 int donation_id = 0;
		 try
		 {
			 s = conn.createStatement();
			 rs= s.executeQuery(	"SELECT * FROM donation " +
			 						"WHERE donor_id = " + d_id + 
			 						" AND  missionary_id = " + m_id +
			 						" AND donation_date = \'" + date.toString() +
			 						"\' AND amount = " + amount);
			 rs.next();
			 donation_id = rs.getInt("donation_id");
		 }catch(SQLException sqle)
		 {
			 printSQLException(sqle);
		 }
		 
		 donation = new Donation(donation_id, d_id, m_id, amount, date);
		 return donation;
		 
	 }

	 public void addDonor(String name)
	 {
		 System.out.println("Adding Donor: " + name);
		 try
	     {
	         s = conn.createStatement();  
	         psInsert = conn.prepareStatement("insert into donor (name) values \'" + name + "\'");
	         psInsert.executeUpdate();
	         conn.commit();
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public void removeDonor(String name)
	 {
		 System.out.println("Removing Donor :" + name);
		 try
	     {
	         s = conn.createStatement(); 
	         psRemove = conn.prepareStatement("DELETE FROM donor WHERE name = \'" + name +"\'");
	         

	         psRemove.executeUpdate();
	         conn.commit();
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }

	 public void addMissionary(String name)
	 {
		 System.out.println("Adding Missionary :" + name);
		 try
		 {
			 s = conn.createStatement(); 
			 psInsert = conn.prepareStatement("INSERT INTO missionary (name) VALUES (?)");
			 
			 psInsert.setString(1, name);
			 psInsert.executeUpdate();
			 conn.commit();
		 }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public void removeMissionary(String name)
	 {
		 System.out.println("Removing Missionary :" + name);
		 try
		 {
			 s = conn.createStatement(); 
			 psRemove = conn.prepareStatement("DELETE FROM missionary WHERE name = \'" + name +"\'");
			 
			 
			 psRemove.executeUpdate();
			 conn.commit();
		 }catch(SQLException sqle) {printSQLException(sqle);}
	 }


	 public void addDonation(double amount, String d_name, String m_name, Date date)
	 {
		 System.out.println("Adding Donation : M " + m_name + " D " + d_name + " amount:" + amount +" on date:" + date.toString());
		 try
	     {
	         s = conn.createStatement();  
	         rs = s.executeQuery("SELECT donor_id FROM donor where name=\'" + d_name + "\'");
	         rs.next();
	         int d_id = rs.getInt("donor_id");
	         
	         rs = s.executeQuery("SELECT missionary_id FROM missionary WHERE name=\'" +m_name + "\'");
	         rs.next();
	         int m_id = rs.getInt("missionary_id");
	         
	         psInsert = conn.prepareStatement("	INSERT INTO donation(amount, donor_id, missionary_id, donation_date) " +
	         									"values ("+amount+"," + d_id+ "," + m_id+ ",\'" + date+ "\')");
	         psInsert.executeUpdate();
	         conn.commit();
	         System.out.println("Committed the donation");
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 
	 public void addDonation(double amount, int d_id, int m_id, Date date)
	 {
		 System.out.println("Adding Donation : M " + m_id + " D " + d_id + " amount:" + amount +" on date:" + date.toString());
		 try
	     {
	         psInsert = conn.prepareStatement("	INSERT INTO donation(amount, donor_id, missionary_id, donation_date) " +
	         									"values ("+amount+"," + d_id+ "," + m_id+ ",\'" + date+ "\')");
	         psInsert.executeUpdate();
	         conn.commit();
	         System.out.println("Committed the donation");
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 
	 
	 public void removeDonation(int donation_id)
	 {
		 try
	     {
	         s = conn.createStatement(); 
	         psRemove = conn.prepareStatement("DELETE FROM donation WHERE donation_id = " + donation_id);
	         
	         psRemove.executeUpdate();
	         conn.commit();
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public static void printSQLException(SQLException e)
	 {
	     while (e != null)
	     {
	         System.err.println("\n----- SQLException -----");
	         System.err.println("  SQL State:  " + e.getSQLState());
	         System.err.println("  Error Code: " + e.getErrorCode());
	         System.err.println("  Message:    " + e.getMessage());
	         e = e.getNextException();
	     }
	 }
	 
	 //If the Database is not setup, run this command.
	 public void DBSetup() 
	 {	
		 System.out.println("DBBuilder starting in " + framework + " mode");

	     try
	     {
	         Properties props = new Properties(); // connection properties
	         props.put("user", "user1");
	         props.put("password", "user1");
	
	         String dbName = "DB"; // the name of the database
	         conn = DriverManager.getConnection(protocol+dbName+";create=true;",props); // Database already exists.
	         s = conn.createStatement();
	         System.out.println("Connected to database " + dbName);
	     }catch (SQLException sqle){printSQLException(sqle);}
	     
		 try{
			 s.execute("create table missionary(missionary_id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key, name varchar(40))");
			 System.out.println("Missionary Table setup");
		 }catch(SQLException sqle) {printSQLException(sqle);}
		 try{
			 s.execute("create table donor(donor_id int primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), name varchar(40))");
			 System.out.println("Donor Table setup");
		 }catch(SQLException sqle) {printSQLException(sqle);}
		 
		 try{
			 s.execute("create table donation(donation_id int primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), donor_id int, missionary_id int, amount double, donation_date varchar(20))");
			 System.out.println("Donation setup");
		 }catch(SQLException sqle) {printSQLException(sqle);}
		 System.out.println("FLAG TABLE SETUP END");
		 disconnect();
		 
	 }
}
