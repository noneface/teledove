package com.teledove.windowComponet;

import javax.swing.JFrame;

public class loginFrame extends JFrame{
	public loginFrame(){
	   loginPanel loginPanel = new loginPanel();
	   this.setContentPane(loginPanel);
	   this.setBounds(700,290,600,500);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	}
      public static void main(String[] args) {
		new loginFrame();
	}
} 
