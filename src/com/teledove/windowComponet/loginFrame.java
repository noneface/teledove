package com.teledove.windowComponet;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JButton;

public class loginFrame extends JFrame{
	public loginFrame(){
	   mainPanel mainpanel = new mainPanel();
	   this.setContentPane(mainpanel);
	   this.setBounds(700,290,597,467);

	   this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	}
      public static void main(String[] args) {
		new loginFrame();
	}
} 
