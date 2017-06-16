package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.UIManager;

import io.Codes;
import serverclient.ClientController;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class MailCreationPanel extends EventCreationPanel implements ActionListener{
	private ClientController controller;

	// Sub-sections of MailCreationPanel
	private JComboBox<String> originComboBox;
	private JComboBox<String> destComboBox;
	private JComboBox<String> prioComboBox;
	private JTextField weightTextField;
	private JLabel weightErrorLabel;
	private JLabel volumeErrorLabel;
	private JTextField volumeTextField;
	private Gui gui;
	private JRadioButton cheap;
	private JRadioButton fast;
	private JButton submitOption;
	private JLabel submitErrorLabel;
	private JDialog option;
	private String priority;
	private double fastCost, fastRouteCost, fastTime;
	private double cheapCost, cheapRouteCost, cheapTime;

	public MailCreationPanel(ClientController controller, Gui gui) {
		this.controller = controller;
		this.gui = gui;
		initPanel();
	}

	public void update(){
		originComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
		destComboBox.setModel(new DefaultComboBoxModel<String>(controller.getLocations()));
	}

	private void initPanel() {
		setBackground(UIManager.getColor("Panel.background"));
		setLayout(null);

		JLabel originLabel = new JLabel("Origin");
		originLabel.setForeground(Color.DARK_GRAY);
		originLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originLabel.setBounds(30, 69, 82, 26);
		add(originLabel);

		originComboBox = new JComboBox<String>();
		originComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		originComboBox.setEditable(false);
		originComboBox.setBounds(150, 69, 170, 26);
		add(originComboBox);

		JLabel destLabel = new JLabel("Destination");
		destLabel.setForeground(Color.DARK_GRAY);
		destLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destLabel.setBounds(30, 116, 82, 26);
		add(destLabel);

		destComboBox = new JComboBox<String>();
		destComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		destComboBox.setEditable(false);
		destComboBox.setBounds(150, 116, 170, 26);
		add(destComboBox);

		JLabel priorityLabel = new JLabel("Priority");
		priorityLabel.setForeground(Color.DARK_GRAY);
		priorityLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		priorityLabel.setBounds(30, 170, 82, 26);
		add(priorityLabel);

		prioComboBox = new JComboBox<String>();
		prioComboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { Codes.Priorities.InternationalAir , Codes.Priorities.InternationalStandard, Codes.Priorities.DomesticAir, Codes.Priorities.DomesticStandard }));
		prioComboBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
		prioComboBox.setEditable(false);
		prioComboBox.setBounds(150, 170, 170, 26);
		add(prioComboBox);

		JLabel weightLabel = new JLabel("Weight");
		weightLabel.setForeground(Color.DARK_GRAY);
		weightLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightLabel.setBounds(30, 222, 82, 26);
		add(weightLabel);

		weightTextField = new JTextField();
		weightTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				 checkWeight();
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});;
		weightTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		weightTextField.setBounds(150, 222, 170, 26);
		add(weightTextField);

		JLabel volumeLabel = new JLabel("Volume");
		volumeLabel.setForeground(Color.DARK_GRAY);
		volumeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeLabel.setBounds(30, 273, 82, 26);
		add(volumeLabel);

		volumeTextField = new JTextField();
		volumeTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				 checkVolume();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		volumeTextField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		volumeTextField.setBounds(150, 273, 170, 26);
		add(volumeTextField);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(new Font("SansSerif", Font.PLAIN, 18));
		submitButton.setBounds(150, 322, 108, 31);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!checkWeight() || !checkVolume()) {
					submitErrorLabel.setText("Please enter correct weight and volume");
					return;
				} else {
					submitErrorLabel.setText("");
				}
				priority = (String)prioComboBox.getSelectedItem();
				controller.requestMailCreationRoutes((String)originComboBox.getSelectedItem(),
						(String)destComboBox.getSelectedItem(),
						(String)prioComboBox.getSelectedItem(),
						Double.parseDouble(weightTextField.getText()),
						Double.parseDouble(volumeTextField.getText()));
						

			}
		});
		add(submitButton);

		JLabel titleLabel = new JLabel("Request for Delivery");
		titleLabel.setFont(new Font("SansSerif", Font.BOLD, 31));
		titleLabel.setBounds(100, 11, 405, 31);
		add(titleLabel);
		
		weightErrorLabel = new JLabel("");
		weightErrorLabel.setFont(errorFont);
		weightErrorLabel.setForeground(errorColor);
		weightErrorLabel.setBounds(150, 248, 170, 14);
		add(weightErrorLabel);
		
		volumeErrorLabel = new JLabel("");
		volumeErrorLabel.setForeground(errorColor);
		volumeErrorLabel.setFont(errorFont);
		volumeErrorLabel.setBounds(150, 299, 170, 14);
		add(volumeErrorLabel);
		
		submitErrorLabel = new JLabel("");
		submitErrorLabel.setForeground(errorColor);
		submitErrorLabel.setFont(errorFont);
		submitErrorLabel.setBounds(150, 352, 290, 14);
		add(submitErrorLabel);
	}

	public void showDeliveryOption(double cheapCost, double cheapRouteCost, double cheapTime, double fastestCost, double fastestRouteCost, double fastestTime) {
		this.fastCost = fastestCost;
		this.fastRouteCost = fastestRouteCost;
		this.fastTime = fastestTime;
		this.cheapCost = cheapCost;
		this.cheapRouteCost = cheapRouteCost;
		this.cheapTime = cheapTime;

		option = new JDialog();
		option.setTitle("Delivery Options");
		option.setModal(true);
		option.setResizable(false);
		option.setSize(new Dimension(620,260));
		option.setPreferredSize(new Dimension(420,260));
		option.setLocation((gui.frame.getWidth()/2)-(option.getWidth()/2), (gui.frame.getHeight()/2)-(option.getHeight()/2));
		option.getContentPane().setLayout(null);
		option.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JLabel header = new JLabel("Please select a delivery option");
		header.setForeground(Color.DARK_GRAY);
		header.setFont(new Font("SansSerif", Font.PLAIN, 20));
		header.setBounds(180, 10, 300, 20);
		option.getContentPane().add(header);

		JLabel cheapD = new JLabel("Cost ($)");
		cheapD.setForeground(Color.DARK_GRAY);
		cheapD.setBounds(250, 45, 100, 20);
		option.getContentPane().add(cheapD);

		JLabel time = new JLabel("Time (hrs)");
		time.setForeground(Color.DARK_GRAY);
		time.setBounds(380, 45, 100, 20);
		option.getContentPane().add(time);


		JPanel option1 = new JPanel();
		option1.setBounds(15, 75, 590, 40);
		option1.setBorder(BorderFactory.createEtchedBorder());
		option1.setLayout(null);

		JLabel cheapLabel = new JLabel("Cheapest Option:");
		cheapLabel.setBounds(5, 10, 150, 20);
		option1.add(cheapLabel);

		JLabel cheapCostLabel = new JLabel(cheapCost + "");
		cheapCostLabel.setBounds(240, 10, 150, 20);
		option1.add(cheapCostLabel);

		JLabel cheapTimeLabel = new JLabel(cheapTime + "");
		cheapTimeLabel.setBounds(375, 10, 100, 20);
		option1.add(cheapTimeLabel);

		cheap = new JRadioButton();
		cheap.setBounds(530, 10, 20, 20);
		cheap.addActionListener(this);
		option1.add(cheap);

		option.getContentPane().add(option1);

		JPanel option2 = new JPanel();
		option2.setBounds(15, 120, 590, 40);
		option2.setBorder(BorderFactory.createEtchedBorder());
		option2.setLayout(null);

		JLabel fastLabel = new JLabel("Fastest Option:");
		fastLabel.setBounds(5, 10, 150, 20);
		option2.add(fastLabel);

		JLabel fastCostLabel = new JLabel(fastestCost + "");
		fastCostLabel.setBounds(240, 10, 150, 20);
		option2.add(fastCostLabel);

		JLabel fastTimeLabel = new JLabel(fastestTime + "");
		fastTimeLabel.setBounds(375, 10, 100, 20);
		option2.add(fastTimeLabel);

		fast = new JRadioButton();
		fast.setBounds(530, 10, 20, 20);
		fast.addActionListener(this);
		option2.add(fast);

		option.getContentPane().add(option2);

		submitOption = new JButton("Submit");
		submitOption.setBounds(260, 180, 100, 30);
		submitOption.addActionListener(this);
		option.getContentPane().add(submitOption);

		option.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JRadioButton){
			if(((JRadioButton)e.getSource()).equals(cheap)) fast.setSelected(false);
			else if(((JRadioButton)e.getSource()).equals(fast)) cheap.setSelected(false);
		}else if(e.getSource() instanceof JButton){
			if(((JButton)e.getSource()).equals(submitOption)){
				option.setVisible(false);
				double cost = 0;
				double routeCost = 0;
				double time = 0;
				if(cheap.isSelected()){
					cost = this.cheapCost;
					routeCost = this.cheapRouteCost;
					time = this.cheapTime;
				}
				else if(fast.isSelected()){
					cost = this.fastCost;
					routeCost = this.fastRouteCost;
					time = this.fastTime;
				}
				controller.requestMailCreation((String)originComboBox.getSelectedItem(),
						(String)destComboBox.getSelectedItem(),
						Double.parseDouble(weightTextField.getText()),
						Double.parseDouble(volumeTextField.getText()),
						priority, cost, routeCost, time);
			}
		}
	}
	
	private boolean checkWeight() {
		if (isValidNumber(weightTextField.getText())) {
			weightErrorLabel.setText("");
			return true;
		} else {
			weightErrorLabel.setText("Invalid weight");
			return false;
		}
	}
	
	private boolean checkVolume() {
		if (isValidNumber(volumeTextField.getText())) {
			volumeErrorLabel.setText("");
			return true;
		} else {
			volumeErrorLabel.setText("Invalid volume");
			return false;
		}
	}

	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
