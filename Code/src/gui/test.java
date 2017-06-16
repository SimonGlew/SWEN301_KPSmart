package gui;

import javax.swing.JFrame;

public class test {

	public test(){
		JFrame frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new NavTransportDiscontinuePanel());
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new test();
	}
}
