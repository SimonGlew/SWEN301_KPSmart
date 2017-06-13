package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import serverclient.ClientController;

public class HomePanel extends JPanel {
	private ClientController controller;
	private JPanel panelBody;
	private CardLayout layoutPanelBody;

	public HomePanel(ClientController controller) {
		this.controller = controller;
		initPanel();
	}

	private void initPanel() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1000, 800));

		JPanel panelHeader = new JPanel();

		JPanel panelSide = new JPanel();

		initPanelBody();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelHeader, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelSide, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelBody, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelHeader, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(panelSide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
						.addComponent(panelBody, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 1, 0, 0));

		JScrollPane scrollPaneCreateEvents = new JScrollPane();
		scrollPaneCreateEvents
				.setBorder(new TitledBorder(null, "Create Event", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelCreateEvent = new JPanel();
		scrollPaneCreateEvents.setViewportView(panelCreateEvent);
		panelCreateEvent.setLayout(new GridLayout(4, 1, 0, 0));

		JScrollPane scrollPaneKeyFigures = new JScrollPane();
		scrollPaneKeyFigures.setBorder(new TitledBorder(null, "Key Events", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(59, 59, 59)));

		JPanel panelKeyFigures = new JPanel();
		scrollPaneCreateEvents.setViewportView(panelCreateEvent);

		JButton lblMailDelivery = new JButton("Mail Delivery");
		lblMailDelivery.addActionListener(e -> showPanel("MAIL_CREATION"));
		panelCreateEvent.add(lblMailDelivery);

		JButton lblCustomerCostUpdate = new JButton("Customer Price Update");
		panelCreateEvent.add(lblCustomerCostUpdate);

		JButton lblTransportCostUpdate = new JButton("Transport Cost Update");
		panelCreateEvent.add(lblTransportCostUpdate);

		JButton lblTransportDiscontinued = new JButton("Transport Discontinued");
		panelCreateEvent.add(lblTransportDiscontinued);
		scrollPaneKeyFigures.setViewportView(panelKeyFigures);
		panelKeyFigures.setLayout(new GridLayout(5, 1, 0, 0));

		JButton lblTotalRevenue = new JButton("Total Revenue");
		lblTotalRevenue.addActionListener(e -> showPanel("TOTAL_REVENUE"));
		panelKeyFigures.add(lblTotalRevenue);

		JButton lblTotalExpenditure = new JButton("Total Expenditure");
		panelKeyFigures.add(lblTotalExpenditure);

		JButton lblNewLabel = new JButton("Amount of Mail");
		panelKeyFigures.add(lblNewLabel);

		JButton lblAverageDeliveryTimes = new JButton("Average Delivery Times");
		panelKeyFigures.add(lblAverageDeliveryTimes);

		JButton lblCriticalRoutes = new JButton("Critical Routes");
		panelKeyFigures.add(lblCriticalRoutes);

		GroupLayout gl_panelSide = new GroupLayout(panelSide);
		gl_panelSide.setHorizontalGroup(
			gl_panelSide.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSide.createSequentialGroup()
					.addGroup(gl_panelSide.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneCreateEvents, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addComponent(scrollPaneKeyFigures, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_panelSide.setVerticalGroup(
			gl_panelSide.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSide.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneCreateEvents, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneKeyFigures, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelSide.setLayout(gl_panelSide);

		JButton lblLogIn = new JButton("Log out");
		lblLogIn.setBorder(null);
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblKpsmart = new JLabel("KPSmart");
		lblKpsmart.setForeground(Color.DARK_GRAY);
		lblKpsmart.setHorizontalAlignment(SwingConstants.CENTER);
		lblKpsmart.setFont(new Font("Bitstream Vera Sans", Font.ITALIC, 31));

		JLabel lblLoggedInDallan = new JLabel("Logged in: Dallan Freemantle (Manager)");
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap(666, Short.MAX_VALUE)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addComponent(lblLoggedInDallan)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblKpsmart, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panelHeader.setVerticalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblKpsmart, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLoggedInDallan)
						.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		panelHeader.setLayout(gl_panelHeader);
		setLayout(groupLayout);
	}

	/**
	 * Initialise the body panel
	 */
	private void initPanelBody() {
		// Create the body using a card layout
		layoutPanelBody = new CardLayout();
		panelBody = new JPanel(layoutPanelBody);
		
		panelBody.add(new JPanel(), "EMPTY");
		panelBody.add(new MailCreationPanel(this.controller), "MAIL_CREATION");
		panelBody.add(new TotalRevenuePanel(), "TOTAL_REVENUE");
	}
	
	/**
	 * Switch the body panel
	 * @param name
	 */
	private void showPanel(String name) {
		layoutPanelBody.show(panelBody, name);
	}
}
