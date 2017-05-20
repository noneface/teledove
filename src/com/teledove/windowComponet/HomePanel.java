package com.teledove.windowComponet;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;


import javax.swing.JSpinner;

import com.teledove.dao.Dao;
import com.teledove.model.User;
import com.teledove.model.UserState;

import javax.swing.JComboBox;

public class HomePanel extends JPanel{
	private JList user;
	private JScrollPane scrollPane;
	public JLabel lblNewLabel;
	private JLabel label;
	private Dao dao;
	private UserState userState;
	
	public HomePanel(){
		this.setLayout(null);
    	this.setBounds(100, 100, 292, 570);
    	  
    	this.lblNewLabel = new JLabel("账号名");
    	lblNewLabel.setBounds(89, 30, 72, 18);
    	add(lblNewLabel);
    	  dao = new Dao();
    	  List<User> list = dao.load();
    	  
    	  DefaultListModel userModel = new DefaultListModel();
    	  for(int i=0;i<list.size();i++){
    		  userState = new UserState(list.get(i).getUsername(),"离线");
    		  userModel.addElement(userState.getUsername()+"("+userState.getUserState()+")");
    	  }
    	  
    	  user = new JList(userModel);
    	  
    	  scrollPane = new JScrollPane(user);
    	  scrollPane.setBorder(BorderFactory.createTitledBorder("用户列表"));
    	  scrollPane.setBounds(14, 82, 264, 432);
    	  add(scrollPane);
    	  
    	  JComboBox<String> comboBox = new JComboBox<String>();
    	  comboBox.addItem("在线");
    	  comboBox.addItem("隐身");
    	  comboBox.setBounds(203, 27, 58, 24);
    	  add(comboBox);
    	  
    	  label = new JLabel("\u7528\u6237\u540D");
    	  label.setBounds(21, 30, 58, 18);
    	  add(label);
    	  
    	  this.validate();
      } 
}
