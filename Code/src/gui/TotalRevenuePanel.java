package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		
		JLabel lblNoEventsTitle = new JLabel("No. of Events:");
		lblNoEventsTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblNoEvents = new JLabel("noEvents");
		lblNoEvents.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTitle)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblTotalRevenue))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNoEventsTitle, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(lblNoEvents, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNoEventsTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNoEvents, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(219, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
