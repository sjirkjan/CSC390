package ui;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import db.DBBuilder;
import db.DonorData;
import entities.Donation;
import entities.Donor;

public class DonorAnalysisWindow {

	ArrayList<Donation> donationList = new ArrayList<Donation>();
	ArrayList<Point2D> pointList;
	ArrayList<Point2D> bigPointList;
	private final int FRAME_WIDTH = 1200;
	private final int FRAME_HEIGHT = 500;
	private final double LARGE_DONATION = 1000;

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
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		ArrayList<Donation> donationList = DonorData.donorAllContributionsTable(donor, db);
		
		pointList = sumList(bubbleSort(donationList));
		bigPointList = bigList(bubbleSort(donationList));

		Graph2 graph = new Graph2(pointList, bigPointList);
		graph.setVisible(true);
		frame.add(graph);
		frame.setVisible(true);
	}
		
	public static ArrayList<Point2D> bubbleSort(ArrayList<Donation> donationList)
	{
		ArrayList<Point2D> pointList = new ArrayList<Point2D>();
		ArrayList<Point2D> list = new ArrayList<Point2D>();

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
		return list;
	}
	
	public ArrayList<Point2D> sumList(ArrayList<Point2D> donationList)
	{
		double ySum = 0;
		ArrayList<Point2D> donationSumList = new ArrayList<Point2D>();
		
		for(Point2D p : donationList)
		{
			if(p.getY()<= LARGE_DONATION)
			{
				ySum += p.getY();
				donationSumList.add(new Point2D.Double(p.getX(),ySum));
			}
		}
		return donationSumList;
	}
	
	public ArrayList<Point2D> bigList(ArrayList<Point2D> donationList)
	{
		ArrayList<Point2D> bigList = new ArrayList<Point2D>();
		
		for(Point2D p : donationList)
		{
			if(p.getY() > LARGE_DONATION)
			{
				
				bigList.add(new Point2D.Double(p.getX(),p.getY()));
			}
		}
		return bigList;
	}
}
