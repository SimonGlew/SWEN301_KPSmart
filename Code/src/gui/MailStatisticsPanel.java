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

import io.Codes;
import serverclient.ClientController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MailStatisticsPanel extends JPanel {
	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private Gui gui;
	
	private JLabel noItemsLabel;
	private JLabel volumeLabel;
	private JLabel weightLabel;
	private JLabel avgTimeLabel;

	public MailStatisticsPanel(Gui g) {
		this.gui = g;
		initPanel();
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);

		JLabel titleLabel = new JLabel("Mail Statistics");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(10, 11, 430, 40);
		add(titleLabel);

		JLabel originLabel = new JLabel("Origin");
		originLabel.setForeground(Color.DARK_GRAY);
		originLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originLabel.setBounds(71, 66, 82, 26);
		add(originLabel);

		originComboBox = new JComboBox<String>();
		originComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "All", "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
		originComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originComboBox.setBounds(191, 66, 170, 26);
		add(originComboBox);

		JLabel destLabel = new JLabel("Destination");
		destLabel.setForeground(Color.DARK_GRAY);
		destLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destLabel.setBounds(71, 113, 82, 26);
		add(destLabel);

		destComboBox = new JComboBox<String>();
		destComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "All", "Wellington", "Auckland", "Christchurch", "Dunedin", "Palmerston North", "Hamilton" }));
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setBounds(191, 113, 170, 26);
		add(destComboBox);

		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(71, 160, 82, 26);
		add(priorityLabel);

		JComboBox<String> priorityComboBox = new JComboBox<String>();
		priorityComboBox.setModel(new DefaultComboBoxModel(new String[] {"All", Codes.Priorities.InternationalAir , Codes.Priorities.InternationalStandard, Codes.Priorities.DomesticAir, Codes.Priorities.DomesticStandard}));
		priorityComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityComboBox.setBounds(191, 160, 170, 26);
		add(priorityComboBox);
		
		JButton query = new JButton("Query");
		query.setBounds(150, 210, 100, 30);
		query.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.controller.requestMailDeliveryStats((String)originComboBox.getSelectedItem(), (String)destComboBox.getSelectedItem(), (String)priorityComboBox.getSelectedItem());
			}
			
		});
		add(query);

		JLabel noItemsTitleLabel = new JLabel("Number of Items");
		noItemsTitleLabel.setForeground(Color.DARK_GRAY);
		noItemsTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noItemsTitleLabel.setBounds(71, 255, 121, 26);
		add(noItemsTitleLabel);

		noItemsLabel = new JLabel("0");
		noItemsLabel.setForeground(Color.DARK_GRAY);
		noItemsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		noItemsLabel.setBounds(204, 255, 82, 26);
		add(noItemsLabel);

		JLabel volumeTitleLabel = new JLabel("Total Volume");
		volumeTitleLabel.setForeground(Color.DARK_GRAY);
		volumeTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTitleLabel.setBounds(71, 299, 93, 26);
		add(volumeTitleLabel);

		volumeLabel = new JLabel("0.00");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(204, 299, 82, 26);
		add(volumeLabel);

		JLabel weightTitleLabel = new JLabel("Total Weight");
		weightTitleLabel.setForeground(Color.DARK_GRAY);
		weightTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightTitleLabel.setBounds(71, 346, 121, 26);
		add(weightTitleLabel);

		weightLabel = new JLabel("0.00");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(204, 346, 82, 26);
		add(weightLabel);

		JLabel avgTimeTitleLabel = new JLabel("Average Delivery Time");
		avgTimeTitleLabel.setForeground(Color.DARK_GRAY);
		avgTimeTitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		avgTimeTitleLabel.setBounds(71, 393, 170, 26);
		add(avgTimeTitleLabel);

		avgTimeLabel = new JLabel("0 hours");
		avgTimeLabel.setForeground(Color.DARK_GRAY);
		avgTimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		avgTimeLabel.setBounds(250, 393, 82, 26);
		add(avgTimeLabel);
	}

	public void update(int numOfItems, double totalVolume, double totalWeight, double avDeliveryTime) {
		noItemsLabel.setText(numOfItems + "");
		volumeLabel.setText(totalVolume + "");
		weightLabel.setText(totalWeight + "");
		avgTimeLabel.setText(avDeliveryTime + "");
	}
}
