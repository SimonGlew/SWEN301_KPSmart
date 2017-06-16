package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.UIManager;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

@SuppressWarnings("serial")
public class EventNavigationPanel extends JPanel {
	
	private JPanel eventDetailsPanel;
	private CardLayout eventDetailsLayout;
	
	public NavMailCreationPanel mailCreation;
	public NavTransportCostPanel transportCost;
	public NavTransportDiscontinuePanel transportDisc;
	public NavCustomerPricePanel customerCost;
	
	public JLabel revLabel;
	public JLabel expLabel;
	public JLabel username;
	public JLabel date;
	public JLabel eventNumberLabel;
	public JButton btnPrevious;
	public JButton btnNext;
	public Gui g;
	
	private int currentIndex;

	public EventNavigationPanel(Gui g) {
		this.g = g;
		initPanel();
	}

	private void initPanel() {
		setPreferredSize(new Dimension(586, 546));
		setBackground(UIManager.getColor("Panel.background"));

		
		initEventDetails();

		JLabel titleLabel = new JLabel("Event Navigation");
		titleLabel.setBounds(10, 11, 566, 40);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		
		username = new JLabel("User Logged: ");
		username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username.setBounds(20, 60, 300, 20);
		add(username);
		
		date = new JLabel("Date Logged: 00/00/00");
		date.setFont(new Font("Tahoma", Font.PLAIN, 16));
		date.setBounds(400, 60, 200, 20);
		add(date);

		JLabel eventDetailsLabel = new JLabel("Event Details");
		eventDetailsLabel.setBounds(20, 90, 134, 14);
		eventDetailsLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel businessFiguresLabel = new JLabel("Business Figures");
		businessFiguresLabel.setBounds(20, 380, 134, 20);
		businessFiguresLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JPanel busFigsPanel = new JPanel();
		busFigsPanel.setBounds(20, 405, 545, 65);
		busFigsPanel.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel revenueLabel = new JLabel("Total Revenue:");
		revenueLabel.setBounds(10, 10, 158, 20);
		revenueLabel.setForeground(Color.DARK_GRAY);
		revenueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		revLabel = new JLabel("$0.00");
		revLabel.setBounds(150, 10, 45, 20);
		revLabel.setForeground(Color.BLACK);
		revLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel expenditureLabel = new JLabel("Total Expenditure:");
		expenditureLabel.setBounds(10, 35, 151, 20);
		expenditureLabel.setForeground(Color.DARK_GRAY);
		expenditureLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		expLabel = new JLabel("$0.00");
		expLabel.setBounds(150, 35, 45, 20);
		expLabel.setForeground(Color.BLACK);
		expLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		eventNumberLabel = new JLabel("0/0");
		eventNumberLabel.setBounds(270, 508, 30, 20);
		
		btnNext = new JButton("Next");
		btnNext.setBounds(466, 495, 110, 40);
		btnNext.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				g.controller.requestEventLog(currentIndex+1);
			}
			
		});
		
		btnPrevious = new JButton("Previous");
		btnPrevious.setBounds(10, 495, 110, 40);
		btnPrevious.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				g.controller.requestEventLog(currentIndex-1);
			}
			
		});

		busFigsPanel.setLayout(null);
		busFigsPanel.add(revenueLabel);
		busFigsPanel.add(revLabel);
		busFigsPanel.add(expenditureLabel);
		busFigsPanel.add(expLabel);
		
		
		setLayout(null);
		add(eventNumberLabel);
		add(titleLabel);
		add(btnPrevious);
		add(btnNext);
		add(busFigsPanel);
		add(businessFiguresLabel);
		add(eventDetailsPanel);
		add(eventDetailsLabel);
	}
	
	/**
	 * Initialise the body panel
	 */
	public void initEventDetails() {
		// Create the body using a card layout
		eventDetailsLayout = new CardLayout();
		
		eventDetailsPanel = new JPanel(eventDetailsLayout);
		eventDetailsPanel.setBorder(BorderFactory.createEtchedBorder());
		eventDetailsPanel.setBounds(20, 110, 545, 258);
		
		mailCreation = new NavMailCreationPanel();
		customerCost = new NavCustomerPricePanel();
		transportCost = new NavTransportCostPanel();
		transportDisc = new NavTransportDiscontinuePanel();

		eventDetailsPanel.add(new JPanel(), "PLACE_HOLDER");
		eventDetailsPanel.add(mailCreation, "MAIL_CREATION");
		eventDetailsPanel.add(customerCost, "CUSTOMER_PRICE");
		eventDetailsPanel.add(transportCost, "TRANSPORT_COST");
		eventDetailsPanel.add(transportDisc, "TRANSPORT_DISCONTINUE");
	}
	
	public void updateBusinessFigures(double revenue, double expenditure, int id, String user, int numEvents, boolean next, boolean prev, String d){
		currentIndex = id;
		username.setText("User Logged: " + user);
		eventNumberLabel.setText(id-1 + "/" + numEvents);
		revLabel.setText(revenue + "");
		expLabel.setText(expenditure + "");
		date.setText("Date Logged: " + d);
		
		if(!next) btnNext.setEnabled(false);
		else btnNext.setEnabled(true);
		if(!prev) btnPrevious.setEnabled(false);
		else btnPrevious.setEnabled(true);
	}
	
	public void updateLogMailDelivery(int id, String username, String date, String day, String from, String to, String priority, double volume, double weight, double kpsCost, double routeCost, int hours, double expenditure, double revenue, int numEvents, boolean next, boolean prev){
		updateBusinessFigures(revenue, expenditure, id, username, numEvents, next, prev, date);
		mailCreation.update(day, from, to, priority, volume, weight, kpsCost, routeCost, hours);
		showPanel("MAIL_CREATION");
	}
	
	public void updateLogCustomerUpdate(int id, String username, String date, String from, String to, String priority, double volumeCost, double weightCost, double expenditure, double revenue, int numEvents, boolean next, boolean prev){
		updateBusinessFigures(revenue, expenditure, id, username, numEvents, next, prev, date);
		customerCost.update(from, to, priority, volumeCost, weightCost);
		showPanel("CUSTOMER_PRICE");
	}
	
	public void updateLogTransportUpdate(int id, String username, String date, String company, String to, String from, String priority, double weight, double volume, double maxWeight, double maxVolume, int duration, int frequency, List<String> days, double expenditure, double revenue, int numEvents, boolean next, boolean prev){
		updateBusinessFigures(revenue, expenditure, id, username, numEvents, next, prev, date);
		transportCost.update(company, to, from, priority, weight, volume, maxWeight, maxVolume, duration, frequency, days);
		showPanel("TRANSPORT_COST");
	}
	
	public void updateLogTransport(int id, String username, String date, String company, String to, String from, String priority, double expenditure, double revenue, int numEvents, boolean next, boolean prev){
		updateBusinessFigures(revenue, expenditure, id, username, numEvents, next, prev, date);
		transportDisc.update(company, to, from, priority);
		showPanel("TRANSPORT_DISCONTINUE");
	}
	
	/**
	 * Switch the body panel
	 * @param name
	 */
	private void showPanel(String name) {
		eventDetailsLayout.show(eventDetailsPanel, name);
	}
}
