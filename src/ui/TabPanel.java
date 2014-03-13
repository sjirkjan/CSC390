package ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTabbedPane;

import db.DBBuilder;

import entities.Donation;
import entities.Donor;
import entities.Missionary;
import entities.Relation;

public class TabPanel extends JTabbedPane {

	JTabbedPane tabPane;
	DBBuilder database = new DBBuilder();
	JComponent donorTab;
	JComponent missionaryTab;
	JComponent relationTab;
	JComponent donationTab;
	DefaultListModel<Donor> donorListModel;
	DefaultListModel<Missionary> missionaryListModel;
	DefaultListModel<Relation> relationListModel;
	DefaultListModel<Donation> donationListModel;
	JList<Donor> donorList;
	JList<Missionary> missionaryList;
	JList<Relation> relationList;
	JList<Donation> donationList;
	public TabPanel()
	{
		tabPane = new JTabbedPane();
		
		
		donorTab = createDonorList();
		tabPane.addTab("Donor", donorTab);
		
		missionaryTab = createMissionaryList();
		tabPane.addTab("Missionary", missionaryTab);
		
		relationTab = createRelationList();
		tabPane.addTab("Relation", relationTab);
		
		donationTab = createDonationList();
		tabPane.addTab("Donation", donationTab);
		
		tabPane.setVisible(true);
		add(tabPane, BorderLayout.CENTER);
	}
	
	private JList<Donor> createDonorList()
	{
		ArrayList<Donor> donorTable =database.makeDonorTable();
		donorListModel = new DefaultListModel<Donor>(); 
		for(Donor i : donorTable)
			donorListModel.addElement(i);
		
		donorList=new JList<Donor>(donorListModel);
		donorList.setVisible(true);
		donorList.setSelectedIndex(0);
		
		add(donorList, BorderLayout.CENTER);
		return donorList;
		
	}
	private JList<Missionary> createMissionaryList()
	{
		ArrayList<Missionary> missionaryTable =database.makeMissionaryTable();
		missionaryListModel = new DefaultListModel<Missionary>(); 
		for(Missionary i : missionaryTable)
			missionaryListModel.addElement(i);
		
		missionaryList=new JList<Missionary>(missionaryListModel);
		missionaryList.setVisible(true);
		missionaryList.setSelectedIndex(0);

		add(missionaryList, BorderLayout.CENTER);
		return missionaryList;
	}
	private JList<Relation> createRelationList()
	{
		ArrayList<Relation> relationTable =database.makeRelationTable();
		relationListModel = new DefaultListModel<Relation>(); 
		for(Relation i : relationTable)
			relationListModel.addElement(i);
		
		relationList=new JList<Relation>(relationListModel);

		relationList.setVisible(true);
		relationList.setSelectedIndex(0);

		add(relationList, BorderLayout.CENTER);
		return relationList;
	}
	private JList<Donation> createDonationList()
	{
		ArrayList<Donation> donationTable =database.makeDonationTable();
		donationListModel = new DefaultListModel<Donation>(); 
		for(Donation i : donationTable)
			donationListModel.addElement(i);
		
		donationList=new JList<Donation>(donationListModel);
		donationList.setVisible(true);
		donationList.setSelectedIndex(0);
		
		add(donationList, BorderLayout.CENTER);
		return donationList;
	}
}
