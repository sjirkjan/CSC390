package ui;


import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;

import db.DBBuilder;
import db.DonorData;

import entities.Donation;
import entities.Donor;

public class DonorAnalysisWindow {

	ArrayList<Donation> donationList = new ArrayList<Donation>();
	ArrayList<Point2D> pointList ;//= new ArrayList<Point>();

	public static void main(String[] args)
	{
		ArrayList<Donation> donationList = new ArrayList<Donation>();
		ArrayList<Point2D> pointList = new ArrayList<Point2D>();
		ArrayList<Point2D> donationSumList = new ArrayList<Point2D>();
		
		DBBuilder db = new DBBuilder();
		Donor donor = new Donor(105,"BBB");
		donationList = DonorData.donorAllContributionsTable(donor, db);
		
		DonorAnalysisWindow daw = new DonorAnalysisWindow(db.getDonor("76590"), db);
		db.disconnect();
		pointList = bubbleSort(donationList);
	}
	public DonorAnalysisWindow(Donor donor, DBBuilder db)
	{
		JFrame frame = new JFrame();
		frame.setSize(1200,500);
		
		frame.setVisible(true);
		donationList = DonorData.donorAllContributionsTable(donor, db);
		pointList = bubbleSort(donationList);
		//System.out.println(pointList.toString());
		Graph2 graph = new Graph2(pointList);
		graph.setVisible(true);
		frame.add(graph);
	}
		
	public static ArrayList<Point2D> bubbleSort(ArrayList<Donation> donationList)
	{
		ArrayList<Point2D> pointList = new ArrayList<Point2D>();
		ArrayList<Point2D> list = new ArrayList<Point2D>();
		ArrayList<Point2D> donationSumList = new ArrayList<Point2D>();
		int index;
		
		for(Donation d : donationList)
		{
			pointList.add(new Point2D.Double(d.getDate().days_since_2010(),d.getAmount()));
		}
		
		while(pointList.size() > 0)
		{
			index = 0;
			for(int i=0; i<pointList.size(); i++)
				if(pointList.get(index).getX() > pointList.get(i).getX())
					index = i;

			list.add(pointList.get(index));
			pointList.remove(index);
		}
		
		double ySum = 0;
		for(Point2D p : list)
		{
			if(p.getY()<=1000.0)
			{
				ySum += p.getY();
				donationSumList.add(new Point2D.Double(p.getX(),ySum));
			}
		}
		
		return donationSumList;
	}
}
