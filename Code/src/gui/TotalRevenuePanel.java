package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.SwingConstants;

public class TotalRevenuePanel extends JPanel {
	private JLabel lblTotalRevenue;
	
	public TotalRevenuePanel() {
		initPanel();
	}
	
	private void initPanel() {
		JLabel lblTitle = new JLabel("Total Revenue:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		lblTotalRevenue = new JLabel("totalRevenue");
		lblTotalRevenue.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		JLabel lblNoEventsTitle = new JLabel("Number of events for report:");
		lblNoEventsTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoEventsTitle.setForeground(Color.GRAY);
		lblNoEventsTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JLabel lblNoEvents = new JLabel("noEvents");
		lblNoEvents.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoEvents.setForeground(Color.GRAY);
		lblNoEvents.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTotalRevenue))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblNoEventsTitle, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNoEvents, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(lblTotalRevenue))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNoEvents, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNoEventsTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
