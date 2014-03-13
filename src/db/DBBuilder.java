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
import entities.Relation;

public class DBBuilder{
	 /* the default framework is embedded*/
	 private String framework = "embedded";
	 private String protocol = "jdbc:derby:";
	

	 ArrayList<Donor> donors;
	 ArrayList<Missionary> missionaries;
	 ArrayList<Relation> relations;
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
	 
	 public ArrayList<Relation> makeRelationTable()
	 {
		 relations = new ArrayList<Relation>();
		 try
		 {
	         s = conn.createStatement(); // Creating a statement object that we can use for running various SQL statements commands against the database.
	         rs = s.executeQuery("	SELECT relation_id, donor_id, missionary_id " +
	         					   	"FROM relation ORDER BY relation_id");

			 while(rs.next())
	         {
				 int r_id = rs.getInt("relation_id");
	        	 int d_id =rs.getInt("donor_id");
	        	 int m_id = rs.getInt("missionary_id");
	        	 relations.add(new Relation(r_id,d_id,m_id));
	         }
		 }catch(SQLException sqle)
	     {
	         printSQLException(sqle);
	     }
		 return relations;
	 }
	 public void printRelationTable()
	 {
		 System.out.println("Relation Table");
		 makeRelationTable();
		 for(Relation i : relations)
			 System.out.println(i.toString());
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
	        	 int d_id =rs.getInt("donation_id");
	        	 int r_id = rs.getInt("relationship_id");
	        	 double amount = rs.getDouble("amount");
	        	 String t_date = rs.getString("donation_date");
	        	 donations.add(new Donation(d_id, r_id, amount, Date.stringToDate(t_date)));
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
	 public void addDonor(String name)
	 {
		 System.out.println("Adding Donor: " + name);
		 try
	     {
	         s = conn.createStatement();  
	         psInsert = conn.prepareStatement("insert into donor (name) values (?)");
	         psInsert.setString(1, name);
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
	 public void addRelation(String donor_name, String missionary_name)
	 {
		 System.out.println("Adding Relationship : D " + donor_name + " Missionary: " + missionary_name);
		 try
	     {
			 System.out.println("FLAG 1");
	         s = conn.createStatement();  
	         rs = s.executeQuery("SELECT donor_id FROM donor where name=\'" + donor_name + "\'");
	         rs.next();
	         int d_id = rs.getInt("donor_id");
	         System.out.println("FLAG 2");
	         rs = s.executeQuery("SELECT missionary_id FROM missionary WHERE name=\'" + missionary_name + "\'");
	         rs.next();
	         int m_id = rs.getInt("missionary_id");
	         
	         System.out.println("FLAG 3");
	         psInsert = conn.prepareStatement("	INSERT INTO relation(donor_id,missionary_id) " +
	         									"values (" + d_id+ "," + m_id + ")");
	         psInsert.executeUpdate();
	         conn.commit();
	         
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public void removeRelation(String donor, String missionary)
	 {
		 System.out.println("Removing relation -- donor :" + donor +" missionary :" + missionary);
		 try
	     {
			 System.out.println("FLAG 4");
	         s = conn.createStatement(); 
	         psQuery = conn.prepareStatement("SELECT * FROM donor WHERE name = \'" + donor +"\'");
	         rs.next(); // This assumes the donor exists
	         System.out.println("FLAG 3");
	         int d_id = rs.getInt("donor_id");
	         psQuery = conn.prepareStatement("SELECT * FROM missionary WHERE name = \'" + missionary +"\'");
	         rs.next();
	         System.out.println("FLAG 2");
	         int m_id = rs.getInt("missionary_id");
	         psRemove = conn.prepareStatement("DELETE FROM relation WHERE donor_id = "+ d_id +"AND missionary_id = " +m_id);
	         

	         psRemove.executeUpdate();
	         conn.commit();
	         System.out.println("FLAG 1");
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public void addDonation(double amount, String m_name, String d_name, Date date)
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
	         
	         rs = s.executeQuery("SELECT relation_id FROM relation WHERE donor_id =" + d_id+ " AND missionary_id="+m_id);
	         rs.next();
	         int r_id = rs.getInt("relation_id");
	         String date_ = date.toString();
	         
	         psInsert = conn.prepareStatement("	INSERT INTO donation(amount, relationship_id, donation_date) " +
	         									"values ("+amount+"," + r_id+ ",'" + date_+ "')");
	         psInsert.executeUpdate();
	         conn.commit();
	         System.out.println("Committed the donation");
	     }catch(SQLException sqle) {printSQLException(sqle);}
	 }
	 public void addDonation(double amount, int r, Date date)
	 {
		 System.out.println("Adding Donation : Relation ID " + r + " amount:" + amount +" on date:" + date.toString());
		 try
	     {
	         s = conn.createStatement();  
	         int r_id = r;
	         String date_ = date.toString();

	         psInsert = conn.prepareStatement("	INSERT INTO donation(amount, relationship_id, donation_date) " +
	         									"values ("+amount+","+r_id+ ",'" + date_+ "')");
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
			 s.execute("create table RELATION(relation_id int primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), donor_id int, missionary_id int)");
			 System.out.println("Relation Table setup");
		 }catch(SQLException sqle) {printSQLException(sqle);}
		 try{
			 s.execute("create table donation(donation_id int primary key NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), relationship_id int, amount double, donation_date varchar(20))");
			 System.out.println("Donation setup");
		 }catch(SQLException sqle) {printSQLException(sqle);}
		 System.out.println("FLAG TABLE SETUP END");
		 disconnect();
		 
	 }
}
