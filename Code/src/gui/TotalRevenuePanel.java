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
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTotalRevenue)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTitle)
						.addComponent(lblTotalRevenue))
					.addContainerGap(257, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
