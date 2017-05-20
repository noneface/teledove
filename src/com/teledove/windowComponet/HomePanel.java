package com.teledove.windowComponet;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;


import javax.swing.JSpinner;

import com.teledove.model.User;
import javax.swing.JComboBox;

public class HomePanel extends JPanel{
	private JList<User> user;
	private JScrollPane scrollPane;
      public HomePanel(){
    	  this.setLayout(null);
    	  this.setBounds(100, 100, 292, 570);
    	  
    	  JLabel lblNewLabel = new JLabel("账号名");
    	  lblNewLabel.setBounds(36, 30, 72, 18);
    	  add(lblNewLabel);
    	  
    	  user = new JList<User>();
    	  
    	  scrollPane = new JScrollPane(user);
    	  scrollPane.setBorder(BorderFactory.createTitledBorder("用户列表"));
    	  scrollPane.setBounds(14, 82, 264, 432);
    	  add(scrollPane);
    	  
    	  JComboBox<String> comboBox = new JComboBox<String>();
    	  comboBox.addItem("在线");
    	  comboBox.addItem("隐身");
    	  comboBox.setBounds(122, 27, 58, 24);
    	  add(comboBox);
    	  
    	  this.validate();
      } 
}
