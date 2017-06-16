package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.UIManager;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AccountingPanel extends JPanel {

	public JLabel noEventsLabel;
	public JLabel expenditureLabel;
	public JLabel revenueLabel;
	public JLabel mailDelivery;
	public JLabel customerCost;
	public JLabel transportCost;
	public JLabel transportDisc;

	public AccountingPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);

		JLabel titleLabel = new JLabel("Accounting Figures");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(10, 11, 430, 47);
		add(titleLabel);

		JLabel revenueTitleLabel = new JLabel("Total Revenue");
		revenueTitleLabel.setForeground(Color.DARK_GRAY);
		revenueTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		revenueTitleLabel.setBounds(100, 69, 300, 26);
		add(revenueTitleLabel);

		revenueLabel = new JLabel("$0.00");
		revenueLabel.setForeground(Color.BLACK);
		revenueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		revenueLabel.setBounds(300, 69, 300, 26);
		add(revenueLabel);

		JLabel expenditureTitleLabel = new JLabel("Total Expenditure");
		expenditureTitleLabel.setForeground(Color.DARK_GRAY);
		expenditureTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		expenditureTitleLabel.setBounds(100, 116, 121, 26);
		add(expenditureTitleLabel);

		expenditureLabel = new JLabel("$0.00");
		expenditureLabel.setForeground(Color.BLACK);
		expenditureLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		expenditureLabel.setBounds(300, 116, 300, 26);
		add(expenditureLabel);

		JLabel noEventsTitleLabel = new JLabel("Number of Events");
		noEventsTitleLabel.setForeground(Color.DARK_GRAY);
		noEventsTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		noEventsTitleLabel.setBounds(100, 164, 200, 26);
		add(noEventsTitleLabel);

		JLabel mailDeliveryLabel = new JLabel("Mail Delivery:");
		mailDeliveryLabel.setForeground(Color.DARK_GRAY);
		mailDeliveryLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mailDeliveryLabel.setBounds(100, 214, 250, 26);
		add(mailDeliveryLabel);

		mailDelivery = new JLabel("0");
		mailDelivery.setForeground(Color.BLACK);
		mailDelivery.setFont(new Font("SansSerif", Font.PLAIN, 16));
		mailDelivery.setBounds(300, 214, 45, 26);
		add(mailDelivery);

		JLabel customerLabel = new JLabel("Customer Price Update:");
		customerLabel.setForeground(Color.DARK_GRAY);
		customerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		customerLabel.setBounds(100, 264, 250, 26);
		add(customerLabel);

		customerCost = new JLabel("0");
		customerCost.setForeground(Color.BLACK);
		customerCost.setFont(new Font("SansSerif", Font.PLAIN, 16));
		customerCost.setBounds(300, 264, 45, 26);
		add(customerCost);

		JLabel transportCostLabel = new JLabel("Transport Cost Update:");
		transportCostLabel.setForeground(Color.DARK_GRAY);
		transportCostLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		transportCostLabel.setBounds(100, 314, 250, 26);
		add(transportCostLabel);

		transportCost = new JLabel("0");
		transportCost.setForeground(Color.BLACK);
		transportCost.setFont(new Font("SansSerif", Font.PLAIN, 16));
		transportCost.setBounds(300, 314, 45, 26);
		add(transportCost);

		JLabel transportDiscLabel = new JLabel("Transport Discontinued: ");
		transportDiscLabel.setForeground(Color.DARK_GRAY);
		transportDiscLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		transportDiscLabel.setBounds(100, 364, 250, 26);
		add(transportDiscLabel);

		transportDisc = new JLabel("0");
		transportDisc.setForeground(Color.BLACK);
		transportDisc.setFont(new Font("SansSerif", Font.PLAIN, 16));
		transportDisc.setBounds(300, 364, 45, 26);
		add(transportDisc);

		JLabel totalLabel = new JLabel("Total:");
		totalLabel.setForeground(Color.DARK_GRAY);
		totalLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		totalLabel.setBounds(100, 414, 250, 26);
		add(totalLabel);

		noEventsLabel = new JLabel("0");
		noEventsLabel.setForeground(Color.BLACK);
		noEventsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noEventsLabel.setBounds(300, 414, 45, 26);
		add(noEventsLabel);
	}

	public void updateBusiness(double totalRevenue, double totalExpenditure, int totalNumberOfMailDeliveryEvents,
			int totalNumberOfCustomerPriceUpdateEvents, int totalNumberOfTransportCostUpdateEvents,
			int totalNumberOfTransportDiscontinuedEvents) {
		this.revenueLabel.setText(totalRevenue + "");
		this.expenditureLabel.setText(totalExpenditure + "");
		this.mailDelivery.setText(totalNumberOfMailDeliveryEvents + "");
		this.customerCost.setText(totalNumberOfCustomerPriceUpdateEvents + "");
		this.transportCost.setText(totalNumberOfTransportCostUpdateEvents + "");
		this.transportDisc.setText(totalNumberOfTransportDiscontinuedEvents + "");
		this.noEventsLabel.setText((totalNumberOfCustomerPriceUpdateEvents + totalNumberOfMailDeliveryEvents + totalNumberOfTransportCostUpdateEvents + totalNumberOfTransportDiscontinuedEvents)+ "");
	}
}
