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
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		
		JButton btnNext = new JButton("Next");
		
		JButton button = new JButton("Previous");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JLabel eventDetailsLabel = new JLabel("Event Details");
		eventDetailsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		eventDetailsPanel = new JPanel();
		
		JLabel businessFiguresLabel = new JLabel("Business Figures");
		businessFiguresLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel busFigsPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
					.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(eventDetailsLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addGap(22))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(eventDetailsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(busFigsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(businessFiguresLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(titleLabel)
					.addGap(19)
					.addComponent(eventDetailsLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eventDetailsPanel, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(businessFiguresLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(busFigsPanel, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		busFigsPanel.setLayout(null);
		
		JLabel label = new JLabel("Total Revenue");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(111, 11, 108, 26);
		busFigsPanel.add(label);
		
		JLabel label_1 = new JLabel("Total Expenditure");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_1.setBounds(111, 58, 121, 26);
		busFigsPanel.add(label_1);
		
		JLabel label_2 = new JLabel("$0.00");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_2.setBounds(243, 58, 45, 26);
		busFigsPanel.add(label_2);
		
		JLabel label_3 = new JLabel("$0.00");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label_3.setBounds(243, 11, 45, 26);
		busFigsPanel.add(label_3);
		eventDetailsPanel.setLayout(new CardLayout(0, 0));
		setLayout(groupLayout);
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
