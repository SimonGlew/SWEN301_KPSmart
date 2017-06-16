package gui;

import javax.swing.JFrame;

public class test {

	public test(){
		JFrame frame = new JFrame();
		frame.setSize(525, 268);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new NavMailCreationPanel());
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new test();
	}
}
