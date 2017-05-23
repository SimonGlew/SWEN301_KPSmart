package gui;

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

public class HomePanel extends JPanel {

	public HomePanel() {
		initPanel();

	}

	private void initPanel() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(1000, 800));

		JPanel panelHeader = new JPanel();

		JPanel panelSide = new JPanel();

		JPanel panelBody = new JPanel();

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
								.addComponent(panelSide, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelBody, GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE))
						.addComponent(panelHeader, GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelHeader, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelBody, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelSide, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
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
		gl_panelHeader.setHorizontalGroup(gl_panelHeader.createParallelGroup(Alignment.TRAILING).addGroup(gl_panelHeader
				.createSequentialGroup().addContainerGap(636, Short.MAX_VALUE)
				.addGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelHeader.createSequentialGroup().addComponent(lblLoggedInDallan)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblKpsmart, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 150,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_panelHeader.setVerticalGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelHeader.createSequentialGroup().addContainerGap()
						.addComponent(lblKpsmart, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE).addGap(65)
						.addGroup(gl_panelHeader.createParallelGroup(Alignment.TRAILING).addComponent(lblLoggedInDallan)
								.addComponent(lblLogIn, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panelHeader.setLayout(gl_panelHeader);
		setLayout(groupLayout);
	}
}
