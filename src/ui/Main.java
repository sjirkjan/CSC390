package ui;

import db.DBBuilder;
import entities.Date;

import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		DBBuilder db = new DBBuilder();
		System.out.println("What would you like to do?");
		System.out.println(" 	0) Exit");
		System.out.println("	1) Add new donor");
		System.out.println("	2) Add new missionary");
		System.out.println("	4) Perform new transaction");
		System.out.println("	5) Remove donor");
		System.out.println(" 	6) Remove Missionary");
		System.out.println(" 	8) Remove Transaction");
		System.out.println(" 	9) Print Donor Table");
		System.out.println(" 	10) Print Missionary Table");
		System.out.println(" 	12) Print Transaction Table");
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		while(choice >0 )
		{
			in.nextLine();
			
			switch(choice)
			{
				case 1 : 
				{
					System.out.println("Name of Donor?");
					String name = in.nextLine();
					db.addDonor(name);
					break;
					
				}
				case 2 :
				{
					System.out.println("Name of Missionary?");
					String name = in.nextLine();
					db.addMissionary(name);
					break;
				}

				case 4 :
				{
					System.out.println("From which donor?");
					String donor = in.nextLine();
					System.out.println("To which missionary?");
					String missionary = in.nextLine();
					System.out.println("How much?");
					double amount = in.nextDouble();
					Date date = new Date(2001,1,1);
					db.addDonation(amount, missionary, donor, date);
					break;
				}
				case 5 :
				{
					System.out.println("Name of Donor?");
					String name = in.nextLine();
					db.removeDonor(name);
					break;
				}
				case 6 : 
				{
					System.out.println("Name of Missionary?");
					String name = in.nextLine();
					db.removeMissionary(name);
					break;
				}

				case 8 : 
				{
					System.out.println("ID of donation?");
					int id = in.nextInt();
					in.nextLine();
					db.removeDonation(id);
					break;
				}
				case 9 : 
				{
					System.out.println("DONOR TABLE");
					db.printDonorTable();
					break;
				}
				case 10 : 
				{
					System.out.println("MISSIONARY TABLE");
					db.printMissionaryTable();
					break;
				}

				case 12 : 
				{
					System.out.println("DONATION TABLE");
					db.printDonationTable();
					break;
				}
				default:
					break;
			}
			choice = in.nextInt();
		}
		in.close();
	}
}
