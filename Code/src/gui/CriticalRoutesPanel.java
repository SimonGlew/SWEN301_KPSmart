package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.UIManager;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CriticalRoutesPanel extends JPanel {
	private JTable table;

	public CriticalRoutesPanel() {
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);
		
				JLabel titleLabel = new JLabel("Critical Routes");
				titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
				titleLabel.setVerticalAlignment(SwingConstants.TOP);
				titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
				titleLabel.setBounds(10, 11, 629, 47);
				add(titleLabel);
				
				table = new JTable();
				table.setFont(new Font("Tahoma", Font.PLAIN, 13));
				table.setEnabled(false);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"Origin", "Destination", "Company", "Priority", "Average Difference (Per Item)"},
						{null, null, null, null, null},
					},
					new String[] {
						"Origin", "Destination", "Company", "New column", "Average Difference (Per Item)"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, Object.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(4).setPreferredWidth(154);
				table.setBounds(10, 73, 629, 149);
				add(table);
	}
}
