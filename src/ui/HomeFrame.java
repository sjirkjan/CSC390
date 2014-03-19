package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


import db.DBBuilder;

import entities.Date;
import entities.Donation;
import entities.Donor;
import entities.Missionary;
import entities.Relation;

@SuppressWarnings("serial")
public class HomeFrame extends JFrame 
{
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	
	DBBuilder database;
	
	JComponent mainMenu;
	
	JButton addDonorButton;
	JButton addMissionaryButton;
	JButton addRelationButton;
	JButton addDonationButton;

	JButton deleteDonorButton;
	JButton deleteMissionaryButton;
	JButton deleteRelationButton;
	JButton deleteDonationButton;
	JPanel panel;
	
	JTabbedPane tabPane;
	JComponent missionaryTab;
	DefaultListModel<Donor> donorListModel;
	JList<Donor> donorList;
	DefaultListModel<Missionary> missionaryListModel;
	JList<Missionary> missionaryList;
	DefaultListModel<Relation> relationListModel;
	JList<Relation> relationList;
	DefaultListModel<Donation> donationListModel;
	JList<Donation> donationList;
	
	JTextField nameField;
	String name;
	
	public HomeFrame()
	{
		database = new DBBuilder();
		
		createControlPanel();
		
		createListTabPanel();

		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		
	}

	private void createListTabPanel()
	{
		
		/*TabPanel tab = new TabPanel();
		tab.setVisible(true);
		add(tab,BorderLayout.EAST);*/
		tabPane = new JTabbedPane();
		
		
		JComponent donorTab = createDonorList();
		tabPane.addTab("Donor", donorTab);
		
		missionaryTab = createMissionaryList();
		tabPane.addTab("Missionary", missionaryTab);
		
		JComponent relationTab = createRelationList();
		tabPane.addTab("Relation", relationTab);
		
		JComponent donationTab = createDonationList();
		tabPane.addTab("Donation", donationTab);
		add(tabPane, BorderLayout.CENTER);
	}
	private void updateListPanel()
	{
		tabPane.removeAll();

		tabPane = new JTabbedPane();
		
		JComponent donorTab = createDonorList();
		tabPane.addTab("Donor", donorTab);
		
		missionaryTab = createMissionaryList();
		tabPane.addTab("Missionary", missionaryTab);
		
		JComponent relationTab = createRelationList();
		tabPane.addTab("Relation", relationTab);
		
		JComponent donationTab = createDonationList();
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
		
		add(donorList);
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

	private void createControlPanel() 
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(8,1));
		
		createAddDonorButton();
		createAddMissionaryButton();
		createAddRelationButton();
		createAddDonationButton();
		
		createDeleteDonorButton();
		createDeleteMissionaryButton();
		createDeleteRelationButton();
		createDeleteDonationButton();
		
		panel.add(addDonorButton);
		panel.add(addMissionaryButton);
		panel.add(addRelationButton);
		panel.add(addDonationButton);
		panel.add(deleteDonorButton);
		panel.add(deleteMissionaryButton);
		panel.add(deleteRelationButton);
		panel.add(deleteDonationButton);
		add(panel, BorderLayout.WEST);
	}

	private void createAddDonorButton() 
	{
		addDonorButton = new JButton("Add new donor");
		class AddDonorListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{	
				String donorName = JOptionPane.showInputDialog(null, "", "Donor Name");
				database.addDonor(donorName); 
				Donor donor = database.getDonor(donorName);
				donorListModel.addElement(donor);
			}
		}
		ActionListener listener = new AddDonorListener();
		addDonorButton.addActionListener(listener);
	}

	private void createAddMissionaryButton() 
	{
		addMissionaryButton = new JButton("Add new missionary");
		class AddMissionaryListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				String missionary_name = JOptionPane.showInputDialog(null, "", "Missionary Name");
				database.addMissionary(missionary_name); 
				Missionary missionary = database.getMissionary(missionary_name);
				missionaryListModel.addElement(missionary);
			}
		}
		ActionListener listener = new AddMissionaryListener();
		addMissionaryButton.addActionListener(listener);
	}
	
	private void createAddRelationButton() 
	{
		addRelationButton = new JButton("Add new relation");
		class AddRelationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				JFrame frame = new JFrame("Add Relation");
				
				frame.add(new JTextField());
				frame.add(new JTextField());
				
				String donor_name = null;
				String missionary_name = null;
				database.addRelation(donor_name, missionary_name);
			}
		}
		ActionListener listener = new AddRelationListener();
		addRelationButton.addActionListener(listener);
	}


	private void createAddDonationButton() 
	{
		addDonationButton = new JButton("Add new donation");
		class AddDonationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	// TODO Auto-generated method stub
			}
		}
		ActionListener listener = new AddDonationListener();
		addDonationButton.addActionListener(listener);
	}

	private void createDeleteDonorButton() 
	{
		deleteDonorButton = new JButton("Delete a donor");
		class DeleteDonorListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	// TODO Auto-generated method stub
			}
		}
		ActionListener listener = new DeleteDonorListener();
		deleteDonorButton.addActionListener(listener);
	}
	private void createDeleteMissionaryButton() 
	{
		deleteMissionaryButton = new JButton("Delete a missionary");
		class DeleteMissionaryListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				// TODO Auto-generated method stub
			}
		}
		ActionListener listener = new DeleteMissionaryListener();
		deleteMissionaryButton.addActionListener(listener);
	}

	private void createDeleteRelationButton() 
	{
		deleteRelationButton = new JButton("Delete Relation");
		class DeleteRelationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	// TODO Auto-generated method stub
			}
		}
		ActionListener listener = new DeleteRelationListener();
		deleteRelationButton.addActionListener(listener);
	}

	private void createDeleteDonationButton() {
		deleteDonationButton = new JButton("Delete donation");
		class DeleteDonationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	// TODO Auto-generated method stub
			}
		}
		ActionListener listener = new DeleteDonationListener();
		deleteDonationButton.addActionListener(listener);
	}
	
}
