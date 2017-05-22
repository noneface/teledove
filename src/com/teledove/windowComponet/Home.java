package com.teledove.windowComponet;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Home extends JFrame {
	
	public HomePanel homePanel;
	
	public Home() {
		this.homePanel = new HomePanel();
		homePanel.user.setBounds(20, 102, 215, 405);
		this.setContentPane(homePanel);
		this.setBounds(600, 100, 275, 606);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	}
}
