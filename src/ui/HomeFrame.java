package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

@SuppressWarnings("serial")
public class HomeFrame extends JFrame 
{
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 700;
	
	DBBuilder database;
	
	JComponent mainMenu;
	
	JTextField donorNameField;
	JButton addDonorButton;
	JTextField missionaryNameField;
	JButton addMissionaryButton;
	
	JTextField donationAmountField;
	JTextField donationDateField;
	JButton addDonationButton;

	JButton deleteDonorButton;
	JButton deleteMissionaryButton;
	JButton deleteDonationButton;
	
	JButton analyzeDonorButton;
	JButton analyzeMissionaryButton;

	JPanel controlPanel;
	JPanel topPanel;
	JPanel listPanel;
	
	JTabbedPane tabPane;
	JComponent donorTab;
	JComponent missionaryTab;
	JComponent donationTab;
	DefaultListModel<Donor> donorListModel;
	JList<Donor> donorList;
	DefaultListModel<Missionary> missionaryListModel;
	JList<Missionary> missionaryList;
	DefaultListModel<Donation> donationListModel;
	JList<Donation> donationList;
	
	JTextField nameField;
	String name;
	
	public HomeFrame()
	{
		database = new DBBuilder();
		
		createControlPanel();
		
		createListPanel();

		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
		
	}

	private void createListPanel()
	{
		JPanel panel = new JPanel();
		panel.setSize(100,600);
		donorTab = createDonorList();
		missionaryTab = createMissionaryList();
		donationTab = createDonationList();
		
		JScrollPane donorPane = new JScrollPane(donorTab); 
		JScrollPane missionaryPane = new JScrollPane(missionaryTab);
		JScrollPane donationPane = new JScrollPane(donationTab);
		
		createAnalyzeDonorButton();
		createAnalyzeMissionaryButton();
		
		panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Donor"),c);
		
		c.gridx = 1;
		panel.add(new JLabel("Missionary"),c);
		
		c.gridx = 2;
		panel.add(new JLabel("Donations"),c);
		
		c.ipady = 450;
		c.gridy = 1;

		
		c.gridx = 0;
		panel.add(donorPane, c);
		
		c.gridx = 1;
		panel.add(missionaryPane, c);
		
		c.gridx = 2;
		panel.add(donationPane, c);
		
		c.ipady = 15;
		c.gridy = 2;
		c.gridx = 0;
		panel.add(analyzeDonorButton,c);
		c.gridx = 1;
		panel.add(analyzeMissionaryButton,c);
		add(panel,BorderLayout.CENTER);
		
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
		controlPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		
		
		createAddDonorButton();
		createAddMissionaryButton();
		createAddDonationButton();
		
		createDeleteDonorButton();
		createDeleteMissionaryButton();
		createDeleteDonationButton();

		donorNameField = new JTextField(30);
		missionaryNameField = new JTextField(30);
		donationAmountField = new JTextField(30);
		donationDateField = new JTextField(10);
		
		c.gridx = 0;
		c.gridy = 0;
		controlPanel.add(new JLabel("New Donor Name:"),c);
		c.gridy = 1;
		controlPanel.add(donorNameField, c);
		c.gridy = 2;
		controlPanel.add(addDonorButton, c);
		
		c.gridy = 3;
		controlPanel.add(new JLabel("New Missionary Name:"),c);
		c.gridy = 4;
		controlPanel.add(missionaryNameField, c);
		c.gridy = 5;
		controlPanel.add(addMissionaryButton, c);
		
		c.gridy = 6;
		controlPanel.add(new JLabel("Donation Amount"),c);
		c.gridy = 7;
		controlPanel.add(donationAmountField, c);
		c.gridy = 8;
		controlPanel.add(new JLabel("Date (yyyy-mm-dd)"),c);
		c.gridy = 9;
		controlPanel.add(donationDateField, c);
		c.gridy = 10;
		controlPanel.add(addDonationButton, c);

		c.gridy = 11;
		controlPanel.add(deleteDonorButton, c);
		c.gridy = 12;
		controlPanel.add(deleteMissionaryButton, c);
		c.gridy = 13;
		controlPanel.add(deleteDonationButton, c);
		add(controlPanel, BorderLayout.WEST);
	}

	private void createAddDonorButton() 
	{
		addDonorButton = new JButton("Add new donor");
		class AddDonorListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				String donorName = donorNameField.getText();
				if(donorName.length() > 0)
				{
					database.addDonor(donorName);
					Donor donor = database.getDonor(donorName);
					donorListModel.addElement(donor);
					donorNameField.setText("");
				}
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
				String missionaryName = missionaryNameField.getText();
				if(missionaryName.length() > 0)
				{
					database.addMissionary(missionaryName);
					Missionary missionary = database.getMissionary(missionaryName);
					missionaryListModel.addElement(missionary);
					missionaryNameField.setText("");
				}
			}
		}
		ActionListener listener = new AddMissionaryListener();
		addMissionaryButton.addActionListener(listener);
	}
	private void createAddDonationButton() 
	{
		addDonationButton = new JButton("Add new donation");
		class AddDonationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				int donor_index = donorList.getSelectedIndex();
				int missionary_index = missionaryList.getSelectedIndex();
				
				int donor_id = donorListModel.get(donor_index).getDonorID();
				int missionary_id = missionaryListModel.get(missionary_index).getMissionaryID();
				
				double amount = Double.parseDouble(donationAmountField.getText());
				Date date = Date.stringToDate(donationDateField.getText());
				
				if(amount > 0)
				{
					database.addDonation(amount, donor_id, missionary_id, date);
					Donation donation = database.getDonation(amount, donor_id, missionary_id, date);
					donationListModel.addElement(donation);
				}
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
			{	
				int index = donorList.getSelectedIndex();
				database.removeDonor(donorListModel.get(index).getDonorName());
				donorListModel.remove(index);
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
				int index = missionaryList.getSelectedIndex();
				database.removeMissionary(missionaryListModel.get(index).getMissionaryName());
				missionaryListModel.remove(index);			
			}
		}
		ActionListener listener = new DeleteMissionaryListener();
		deleteMissionaryButton.addActionListener(listener);
	}
	private void createDeleteDonationButton() {
		deleteDonationButton = new JButton("Delete donation");
		class DeleteDonationListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{	
				int index = donationList.getSelectedIndex();
				database.removeDonation(donationListModel.get(index).getDonationID());
				donationListModel.remove(index);			
			}
		}
		ActionListener listener = new DeleteDonationListener();
		deleteDonationButton.addActionListener(listener);
	}
	
	private void createAnalyzeDonorButton(){
		analyzeDonorButton = new JButton("Analyze");
		class AnalyzeDonorListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int index = donorList.getSelectedIndex();
				Donor donor = donorListModel.get(index);
				DonorAnalysisWindow daw = new DonorAnalysisWindow(donor, database);
			}
		}
		ActionListener listener = new AnalyzeDonorListener();
		analyzeDonorButton.addActionListener(listener);
	}
	private void createAnalyzeMissionaryButton(){
		analyzeMissionaryButton = new JButton("Analyze");
		class AnalyzeMissionaryListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				int index = missionaryList.getSelectedIndex();
				Missionary missionary = missionaryListModel.get(index);
				//MissionaryAnalysisWindow maw = new MissionaryAnalysisWindow(missionary);
			}
		}
		ActionListener listener = new AnalyzeMissionaryListener();
		analyzeMissionaryButton.addActionListener(listener);
		
	}
}
