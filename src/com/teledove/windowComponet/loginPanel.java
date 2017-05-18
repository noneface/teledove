package com.teledove.windowComponet;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class loginPanel extends JPanel{
	
	private JLabel jlabel;
	private JTextField jField;
      public loginPanel(){
    	  this.setLayout(null);
    	  this.setBounds(600, 300, 600, 500);
    	  this.validate();
    	 
    	  jlabel = new JLabel("登录");
    	  jlabel.setBounds(230, 40, 140, 70);
    	  jlabel.setFont(new Font (Font.DIALOG, Font.BOLD, 30));
    	  this.add(jlabel);
    	  
    	  jlabel = new JLabel("用户名");
    	  jlabel.setBounds(100, 120, 80, 80);
    	  jlabel.setFont(new Font (Font.DIALOG, Font.BOLD, 22));
    	  this.add(jlabel);
    	  
    	  
      }
      
}
