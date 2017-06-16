package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.UIManager;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

@SuppressWarnings("serial")
public class EventNavigationPanel extends JPanel {
	
	private JPanel eventDetailsPanel;
	private CardLayout eventDetailsLayout;

	public EventNavigationPanel() {
		initPanel();
	}

	private void initPanel() {
		setPreferredSize(new Dimension(450, 476));
		setBackground(UIManager.getColor("Panel.background"));

		JLabel titleLabel = new JLabel("Event Navigation");
		titleLabel.setBounds(10, 11, 430, 40);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		
		JButton btnNext = new JButton("Next");
		btnNext.setBounds(330, 425, 110, 40);
		
		JButton button = new JButton("Previous");
		button.setBounds(10, 425, 110, 40);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel eventDetailsLabel = new JLabel("Event Details");
		eventDetailsLabel.setBounds(20, 70, 134, 14);
		eventDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		eventDetailsPanel = new JPanel();
		eventDetailsPanel.setBounds(20, 90, 408, 188);
		
		JLabel businessFiguresLabel = new JLabel("Business Figures");
		businessFiguresLabel.setBounds(20, 289, 134, 20);
		businessFiguresLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel busFigsPanel = new JPanel();
		busFigsPanel.setBounds(20, 315, 408, 99);
		
		JLabel eventNumberLabel = new JLabel("0/0");
		eventNumberLabel.setBounds(207, 438, 40, 14);
		eventNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("Total Revenue");
		label.setBounds(111, 11, 108, 26);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel label_1 = new JLabel("Total Expenditure");
		label_1.setBounds(111, 58, 121, 26);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel label_2 = new JLabel("$0.00");
		label_2.setBounds(243, 58, 45, 26);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		JLabel label_3 = new JLabel("$0.00");
		label_3.setBounds(243, 11, 45, 26);
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		busFigsPanel.setLayout(null);
		busFigsPanel.add(label);
		busFigsPanel.add(label_1);
		busFigsPanel.add(label_2);
		busFigsPanel.add(label_3);
		eventDetailsPanel.setLayout(new CardLayout(0, 0));
		setLayout(null);
		add(eventNumberLabel);
		add(titleLabel);
		add(button);
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

		/*eventDetailsPanel.add(new JPanel(), "PLACE_HOLDER");
		eventDetailsPanel.add(new NavMailCreationPanel(), "MAIL_CREATION");
		eventDetailsPanel.add(new NavCustomerPricePanel(), "CUSTOMER_PRICE");
		eventDetailsPanel.add(new NavTransportCostPanel(), "TRANSPORT_COST");
		eventDetailsPanel.add(new NavTransportDiscontinuePanel(), "TRANSPORT_DISCONTINUE");*/
	}
	
	/**
	 * Switch the body panel
	 * @param name
	 */
	private void showPanel(String name) {
		eventDetailsLayout.show(eventDetailsPanel, name);
	}
}
