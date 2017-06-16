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
						.addComponent(busFigsPanel, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addComponent(businessFiguresLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(eventDetailsPanel, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(eventDetailsLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(titleLabel)
					.addGap(19)
					.addComponent(eventDetailsLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eventDetailsPanel, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(businessFiguresLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(busFigsPanel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNext, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
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
