package db;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import entities.Date;


public class KardiaDataEntry {

	public static DBBuilder db;
	public static final int COLUMNS = 3;
	public static final int AMOUNT = 0; 
	public static final int DONOR_ID = 1;
	public static final int DATE = 2;
	
	public static void main(String[] args) throws IOException
	{
		boolean eof=false;
		db = new DBBuilder();
		File file = new File("/home/sjirkjan/data.csv");
		FileReader fr = new FileReader(file);
		BufferedReader textReader = new BufferedReader(fr);
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[] line = new String[COLUMNS];
	
		db.addMissionary("Universal Recipient");
		
		while(!eof)
		{
			String stuff = textReader.readLine(); 
			if (stuff == null)
				break;
			line = stuff.split("\t");

			data.add(line);
		}
		
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(String[] s : data)
		{
			String date = s[DATE].substring(0, 10);
			String[] d = date.split(" ");
			Integer id = Integer.parseInt(s[DONOR_ID]);
			Double amount = Double.parseDouble(s[AMOUNT]);
			if(!idList.contains(id))
			{
				idList.add(id);
				db.addDonor(""+id);
			}
			date = d[2] + "-" + d[1] + "-" + d[0];
			Date day = new Date(Integer.parseInt(d[2]), Integer.parseInt(d[1]),Integer.parseInt(d[0]));
			System.out.println(day);
			
			db.addDonation(amount, ""+id, "Universal Recipient", day);
		}
		System.out.println(idList.toString());
		textReader.close();
	}
}
