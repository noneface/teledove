package com.teledove.windowComponet;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registerPanel extends JPanel{
	
	private JLabel jlabel;
	private JTextField usernameFiled;
	private JTextField passwordFiled;
	private JButton jButton;
	private JButton jButton_1;
	private mainPanel mainpanel;
	
    public registerPanel(mainPanel mainpanel){
    	this.mainpanel = mainpanel;
    	  this.setLayout(null);
    	  this.setBounds(600, 300, 600, 500);
    	  this.validate();
    	 
    	  jlabel = new JLabel("注册");
    	  jlabel.setBounds(240, 50, 150, 70);
    	  jlabel.setFont(new Font ("隶书", Font.BOLD, 50));
    	  this.add(jlabel);
    	  
    	  jlabel = new JLabel("用户名");
    	  jlabel.setBounds(100, 130, 80, 80);
    	  jlabel.setFont(new Font (Font.DIALOG, Font.BOLD, 22));
    	  this.add(jlabel);
    	  
    	  jlabel = new JLabel("密 码");
    	  jlabel.setBounds(100, 190, 80, 80);
    	  jlabel.setFont(new Font (Font.DIALOG, Font.BOLD, 22));
    	  this.add(jlabel);	  
    	  
    	  usernameFiled = new JTextField();
    	  usernameFiled.setBounds(220, 160, 200, 30);
    	  this.add(usernameFiled);
    	  
    	  passwordFiled = new JTextField();
    	  passwordFiled.setBounds(220, 215, 200, 30);
    	  this.add(passwordFiled);
    	  
    	  jButton = new JButton("登录");
    	  jButton.addActionListener(new ActionListener() {
      	  	public void actionPerformed(ActionEvent arg0) {
      	  		mainpanel.card.previous(mainpanel);
      	  	}
      	  });
    	  jButton.setBounds(100, 293, 120, 30);
    	  this.add(jButton);
    	  
    	  
    	  jButton_1 = new JButton("注册");
    	  jButton_1.addActionListener(new ActionListener() {
      	  	public void actionPerformed(ActionEvent arg0) {
	      	  	String username = usernameFiled.getText();
		  		String password = passwordFiled.getText();
		  		mainpanel.client.register(username, password);
      	  	}
      	  });
    	  jButton_1.setBounds(320, 293, 120, 30);
    	  this.add(jButton_1);
      }
      
}
