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

	public AccountingPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);
		
				JLabel titleLabel = new JLabel("Accounting Figures");
				titleLabel.setVerticalAlignment(SwingConstants.TOP);
				titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
				titleLabel.setBounds(69, 11, 327, 47);
				add(titleLabel);

		JLabel revenueTitleLabel = new JLabel("Total Revenue");
		revenueTitleLabel.setForeground(Color.DARK_GRAY);
		revenueTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		revenueTitleLabel.setBounds(30, 69, 108, 26);
		add(revenueTitleLabel);
		
		JLabel revenueLabel = new JLabel("$0.00");
		revenueLabel.setForeground(Color.BLACK);
		revenueLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		revenueLabel.setBounds(162, 69, 108, 26);
		add(revenueLabel);

		JLabel expenditureTitleLabel = new JLabel("Total Expenditure");
		expenditureTitleLabel.setForeground(Color.DARK_GRAY);
		expenditureTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		expenditureTitleLabel.setBounds(30, 116, 121, 26);
		add(expenditureTitleLabel);
		
		JLabel expenditureLabel = new JLabel("$0.00");
		expenditureLabel.setForeground(Color.BLACK);
		expenditureLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		expenditureLabel.setBounds(162, 116, 82, 26);
		add(expenditureLabel);

		JLabel noEventsTitleLabel = new JLabel("Number of Events");
		noEventsTitleLabel.setForeground(Color.DARK_GRAY);
		noEventsTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noEventsTitleLabel.setBounds(30, 164, 123, 26);
		add(noEventsTitleLabel);
		
		JLabel noEventsLabel = new JLabel("0");
		noEventsLabel.setForeground(Color.BLACK);
		noEventsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noEventsLabel.setBounds(162, 164, 82, 26);
		add(noEventsLabel);
	}
}
