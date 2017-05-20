package com.teledove.windowComponet;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class loginPanel extends JPanel{
	
	private JLabel jlabel;
	private JTextField usernameField;
	private JButton jButton;
	private JButton jButton_1;
	private mainPanel mainpanel;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	
    public loginPanel(mainPanel mainpanel){

    	this.mainpanel = mainpanel;
    	this.setLayout(null);
    	this.setBounds(600, 300, 600, 500);
    	this.validate();
    	 
    	  jlabel = new JLabel("登录");
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
    	  
    	  usernameField = new JTextField();
    	  usernameField.setBounds(220, 160, 200, 30);
    	  this.add(usernameField);
    	  
    	  jButton = new JButton("登录");
    	  jButton.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent arg0) {
    	  		String username = usernameField.getText();
    	  		String password = passwordField.getText();
    	  		mainpanel.client.connectServer(username, password);
    	  	}
    	  });
    	  jButton.setBounds(100, 323, 120, 30);
    	  this.add(jButton);
    	  
    	  jButton_1 = new JButton("注册");
    	  jButton_1.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent arg0) {
    	  		mainpanel.card.next(mainpanel);
    	  	}
    	  });
    	  jButton_1.setBounds(317, 323, 120, 30);
    	  this.add(jButton_1);
    	  
    	  passwordField = new JPasswordField();
    	  passwordField.setBounds(220, 221, 200, 30);
    	  add(passwordField);
    	  
    	  jlabel = new JLabel("状态");
    	  jlabel.setFont(new Font ("隶书", Font.BOLD, 22));
    	  jlabel.setBounds(100, 274, 72, 18);
    	  add(jlabel);
    	  
    	  comboBox = new JComboBox();
    	  comboBox.addItem("在线");
    	  comboBox.addItem("隐身");
    	  comboBox.setBounds(220, 271, 97, 24);
    	  add(comboBox);
      }
}
