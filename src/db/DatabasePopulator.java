package db;

import entities.Date;

public class DatabasePopulator {

	public static DBBuilder db;
	
	public static void main(String[] args)
	{
		
		db = new DBBuilder();
		
		String[] donors={"Elwood Eyman","Earlie Ephraim","Tianna Toribio"," Dorinda Dave","Natalya Norden",
				"Lillian Leland","Melania Madrid","Bryant Birchard","Merrilee Manriquez","Keli Knighton",
				"Buford Bengtson","Shiloh Stlawrence","Kendrick Karas","Peggie Pinedo","Mendy Matney",
				"Mirna Mathers","Rhett Rana","Thora Touchette","Danille Dittrich","Glory Goodell"};
		
		//adds 20 names to the donor table
		for(int i=0;i<20;i++)
		{
			db.addDonor(donors[i]);
		}
		
		String[] missionaries={"Samira Strong","Hana Hullinger","Angelo Albarado","Isreal Inabinet","Jolie Jin"};
		
		//adds 5 names to the missionary table
		for(int i=0;i<5;i++)
		{
			db.addMissionary(missionaries[i]);
		}
		
		//assigns each donor to a missionary
		for(int i=0;i<20;i++)
		{
			db.addRelation(missionaries[(int)(Math.random()*5)], donors[i]);
		}
		
		//a few more random relationships for good measure
		for(int i=0;i<4;i++)
		{
			db.addRelation(missionaries[(int)(Math.random()*5)], donors[(int)(Math.random()*20)]);
		}
		
		//give each relationship up to 40 transactions to a missionary
		for(int r=1;r<25;r++){
			for(int i=0;i < (int)(Math.random()*40) ;i++)
			{
				Date date = new Date(
						1999+(int)(Math.random()*3),
						1+	 (int)(Math.random()*12),
						1+	 (int)(Math.random()*30));
				db.addDonation((int)(Math.random()*10000), r, date);
			}
		}
	}
}
