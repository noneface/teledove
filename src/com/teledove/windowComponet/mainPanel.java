package com.teledove.windowComponet;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.teledove.client.Client;

import javax.swing.JButton;

public class mainPanel extends JPanel{
	public CardLayout card;
	public loginPanel lpanel1;
	public registerPanel rpanel1;
	public Client client;
	
	public mainPanel(Client client){
		this.client = client;
		
		this.lpanel1 = new loginPanel(this);
	   	this.rpanel1 = new registerPanel(this);
	   	this.card = new java.awt.CardLayout();
	   	setLayout(this.card);
	   	this.setBounds(600, 300, 700, 600);
	   	this.add(this.lpanel1,"p1");
	   	this.add(this.rpanel1,"p2");
	   
	   
   }
}
