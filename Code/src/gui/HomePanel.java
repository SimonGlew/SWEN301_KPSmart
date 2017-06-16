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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import serverclient.ClientController;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {
	private ClientController controller;
	private JPanel panelBody;
	private CardLayout layoutPanelBody;
	public JLabel username;
	private Gui gui;
	private ArrayList<JPanel> panels;

	public MailCreationPanel mail;
	public TransportCostPanel transportCost;
	public TransportDiscontinuePanel transportDisc;
	public CustomerPricePanel customerPrice;
	
	public JButton criticalRoutesButton;
	public JButton eventNavButton;

	public HomePanel(Gui g,ClientController controller) {
		this.gui = g;
		this.controller = controller;
		initPanel();
	}

	public void update(){
		mail.update();
		transportCost.update();
		transportDisc.update();
		customerPrice.update();
	}

	public void initPanel() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1000, 800));

		JPanel panelHeader = new JPanel();

		JPanel panelSide = new JPanel();

		initPanelBody();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelHeader, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
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
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelSide, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
						.addComponent(panelBody, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		scrollPaneKeyFigures.setBorder(new TitledBorder(null, "Business Figures", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelKeyFigures = new JPanel();
		scrollPaneCreateEvents.setViewportView(panelCreateEvent);

		JButton mailDeliveryButton = new JButton("Mail Delivery");
		mailDeliveryButton.addActionListener(e -> showPanel("MAIL_CREATION"));
		panelCreateEvent.add(mailDeliveryButton);

		JButton customerCostUpdateButton = new JButton("Customer Price Update");
		customerCostUpdateButton.addActionListener(e -> showPanel("CUSTOMER_PRICE"));
		panelCreateEvent.add(customerCostUpdateButton);

		JButton transportCostUpdateButton = new JButton("Transport Cost Update");
		transportCostUpdateButton.addActionListener(e -> showPanel("TRANSPORT_COST"));
		panelCreateEvent.add(transportCostUpdateButton);

		JButton transportDiscontinuedButton = new JButton("Transport Discontinued");
		transportDiscontinuedButton.addActionListener(e -> showPanel("TRANSPORT_DISC"));
		panelCreateEvent.add(transportDiscontinuedButton);

		scrollPaneKeyFigures.setViewportView(panelKeyFigures);
		panelKeyFigures.setLayout(new GridLayout(2, 1, 0, 0));

		JButton accountingButton = new JButton("Accounting Figures");
		accountingButton.addActionListener(e -> showPanel("ACCOUNTING"));
		panelKeyFigures.add(accountingButton);

		JButton mailStatsButton = new JButton("Mail Statistics");
		mailStatsButton.addActionListener(e -> showPanel("MAIL_STATISTICS"));
		panelKeyFigures.add(mailStatsButton);

		JScrollPane scrollPaneManager = new JScrollPane();
		scrollPaneManager.setBorder(new TitledBorder(null, "Managers Panel", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panelManager = new JPanel();
		scrollPaneManager.setViewportView(panelManager);
		panelManager.setLayout(new GridLayout(2, 1, 0, 0));
		
		criticalRoutesButton = new JButton("Critical Routes");
		criticalRoutesButton.addActionListener(e -> showPanel("CRITICAL_ROUTES"));
		panelManager.add(criticalRoutesButton);
		
		eventNavButton = new JButton("Event Navigation");
		eventNavButton.addActionListener(e -> showPanel("EVENT_NAV"));
		panelManager.add(eventNavButton);
		
		GroupLayout gl_panelSide = new GroupLayout(panelSide);
		gl_panelSide.setHorizontalGroup(
			gl_panelSide.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPaneCreateEvents, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
				.addComponent(scrollPaneKeyFigures, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
				.addComponent(scrollPaneManager, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
		);
		gl_panelSide.setVerticalGroup(
			gl_panelSide.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSide.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneCreateEvents, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneKeyFigures, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneManager, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panelSide.setLayout(gl_panelSide);

		JButton lblLogIn = new JButton("Log out");

		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("Not Logged");
				gui.loginFail();
			}
		});

		JLabel lblKpsmart = new JLabel("KPSmart");
		lblKpsmart.setForeground(Color.DARK_GRAY);
		lblKpsmart.setHorizontalAlignment(SwingConstants.CENTER);
		lblKpsmart.setFont(new Font("Bitstream Vera Sans", Font.ITALIC, 31));

		username = new JLabel("Not Logged");
		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(
			gl_panelHeader.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelHeader.createSequentialGroup()
					.addContainerGap(666, Short.MAX_VALUE)
					.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup()
							.addComponent(username)
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
						.addComponent(username)
						.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		panelHeader.setLayout(gl_panelHeader);
		setLayout(groupLayout);
	}

	/**
	 * Initialise the body panel
	 */
	public void initPanelBody() {
		// Create the body using a card layout
		layoutPanelBody = new CardLayout();
		panelBody = new JPanel(layoutPanelBody);

		mail = new MailCreationPanel(this.controller, gui);
		transportCost = new TransportCostPanel(this.controller);
		transportDisc = new TransportDiscontinuePanel(this.controller);
		customerPrice = new CustomerPricePanel(this.controller);


		panelBody.add(new JPanel(), "PLACE_HOLDER");
		panelBody.add(mail, "MAIL_CREATION");
		panelBody.add(customerPrice, "CUSTOMER_PRICE");
		panelBody.add(transportCost, "TRANSPORT_COST");
		panelBody.add(transportDisc, "TRANSPORT_DISC");
		panelBody.add(new AccountingPanel(), "ACCOUNTING");
		panelBody.add(new MailStatisticsPanel(), "MAIL_STATISTICS");
		panelBody.add(new CriticalRoutesPanel(), "CRITICAL_ROUTES");
		panelBody.add(new EventNavigationPanel(), "EVENT_NAV");
	}

	/**
	 * Switch the body panel
	 * @param name
	 */
	private void showPanel(String name) {
		layoutPanelBody.show(panelBody, name);
	}
}
