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
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(8,1));
		
		createAddDonorButton();
		createAddMissionaryButton();
		createAddDonationButton();
		
		createDeleteDonorButton();
		createDeleteMissionaryButton();
		createDeleteDonationButton();
		
		donorNameField = new JTextField();
		controlPanel.add(donorNameField);
		controlPanel.add(addDonorButton);
		controlPanel.add(addMissionaryButton);
		controlPanel.add(addDonationButton);
		controlPanel.add(deleteDonorButton);
		controlPanel.add(deleteMissionaryButton);
		controlPanel.add(deleteDonationButton);
		add(controlPanel, BorderLayout.WEST);
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
				NewMissionaryFrame frame = new NewMissionaryFrame();
				frame.setVisible(true);
				
				String name = frame.getNewMissionaryName();
				if(name != null)
				{
					database.addMissionary(name);
					Missionary missionary = database.getMissionary(name);
					missionaryListModel.addElement(missionary);
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
