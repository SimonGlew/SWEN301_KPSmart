package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TotalRevenuePanel extends JPanel {
	public TotalRevenuePanel() {
		initPanel();
	}
	
	private void initPanel() {
		JLabel lblTotalRevenue = new JLabel("Total Revenue:");
		lblTotalRevenue.setFont(new Font("Tahoma", Font.BOLD, 26));
		
		JLabel lblTotalrevenue = new JLabel("totalRevenue");
		lblTotalrevenue.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTotalRevenue)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotalrevenue)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalRevenue)
						.addComponent(lblTotalrevenue))
					.addContainerGap(257, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
