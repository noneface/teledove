package com.teledove.windowComponet;

import javax.swing.JFrame;


public class loginFrame extends JFrame{
	public loginFrame(){
	   mainPanel mainpanel = new mainPanel();
	   this.setContentPane(mainpanel);
	   this.setBounds(700,290,597,467);
	   this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	   this.validate();
	   this.setVisible(true);
	}
} 
