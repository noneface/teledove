package com.teledove.windowComponet;

import javax.swing.JFrame;

import com.teledove.client.Client;


public class loginFrame extends JFrame{
	public loginFrame(Client client){
		
		mainPanel mainpanel = new mainPanel(client);
		this.setContentPane(mainpanel);
		this.setBounds(700,290,597,467);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	   
	}
} 
