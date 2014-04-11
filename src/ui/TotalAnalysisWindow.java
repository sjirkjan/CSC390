package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import db.DBBuilder;
import db.DonorData;
import entities.Donation;
import entities.Donor;

public class TotalAnalysisWindow {

	ArrayList<Donation> donationList = new ArrayList<Donation>();
	ArrayList<Point2D> pointList;
	ArrayList<Point2D> bigPointList;
	DefaultListModel<Donor> donorListModel;
	DBBuilder db;
	JFrame frame;
	private JList<Donor> donorList;
	private final int FRAME_WIDTH = 1400;
	private final int FRAME_HEIGHT = 500;
	private final double LARGE_DONATION = 1000;

	public static void main(String[] args)
	{
		ArrayList<Donor> donors = new ArrayList<Donor>();
		
		DBBuilder db = new DBBuilder();
		donors = db.makeDonorTable(); 
		
		TotalAnalysisWindow daw = new TotalAnalysisWindow(donors, db);
		
		db.disconnect();
	}
	public TotalAnalysisWindow(ArrayList<Donor> donors, DBBuilder database)
	{
		db = database;
		frame = new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JScrollPane list = new JScrollPane(makeList());
		Donor d = donorListModel.elementAt(donorList.getSelectedIndex());//donorList.getSelectedIndex());
		//for(Donor d : donors)
		donationList = DonorData.donorAllContributionsTable(d, db);
		
		pointList = sumList(bubbleSort(donationList));
		bigPointList = bigList(bubbleSort(donationList));

//		Graph2 graph = new Graph2(pointList, bigPointList);
		
//		graph.setVisible(true);
		list.setVisible(true);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(graphButton(), c);
		c.gridy = 1;
		c.weighty = 1;
		c.ipady = 300;
		panel.add(list,c);
		
//		frame.add(graph);
		frame.add(panel,BorderLayout.EAST);
		frame.setVisible(true);
	}
		
	private JButton graphButton()
	{
		JButton graphButton = new JButton("GRAPH");
		class AddGraphListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				int index = donorList.getSelectedIndex();
				System.out.print(index);
				Donor d = donorListModel.get(index);
				pointList = sumList(bubbleSort(donationList));
				bigPointList = bigList(bubbleSort(donationList));
				Graph2 graph = new Graph2(pointList, bigPointList);
				graph.setVisible(true);
				frame.add(graph);
				frame.setVisible(true);
			}
		}
		graphButton.setSize(200, 100);
		ActionListener listener = new AddGraphListener();
		graphButton.addActionListener(listener);
		return graphButton;
	}
	public JList<Donor> makeList()
	{
		ArrayList<Donor> donorTable = db.makeDonorTable();
		
		donorListModel = new DefaultListModel<Donor>(); 
		for(Donor i : donorTable)
		{
			donorListModel.addElement(i);
		}
		donorList=new JList<Donor>(donorListModel);
		donorList.setVisible(true);
		
		return(donorList);
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
