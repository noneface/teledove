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
	private JTextField jField;
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
    	  
    	  jField = new JTextField();
    	  jField.setBounds(220, 160, 200, 30);
    	  this.add(jField);
    	  
    	  jField = new JTextField();
    	  jField.setBounds(220, 215, 200, 30);
    	  this.add(jField);
    	  
    	  jButton = new JButton("登录");
    	  jButton.setBounds(100, 293, 120, 30);
    	  this.add(jButton);
    	  
    	  jButton_1 = new JButton("注册");
    	  jButton.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent arg0) {
    	  		mainpanel.card.previous(mainpanel);
    	  	}
    	  });
    	  jButton_1.setBounds(320, 293, 120, 30);
    	  this.add(jButton_1);
      }
      
}
