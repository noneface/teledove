package com.teledove.windowComponet;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.teledove.client.Client;

public class Home extends JFrame {
	
	public HomePanel homePanel;
	
	public Home(Client client) {
		this.homePanel = new HomePanel(client);
		homePanel.user.setBounds(20, 102, 215, 405);
		this.setContentPane(homePanel);
		this.setBounds(600, 100, 275, 606);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
		this.setResizable(false);
	}
}
