package com.teledove.windowComponet;

import javax.swing.JPanel;


import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;


import javax.swing.ListSelectionModel;

import com.teledove.client.Client;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePanel extends JPanel{
	public JList user;
	private JScrollPane scrollPane;
	public JLabel lblNewLabel;
	private JLabel label;
	private Client client;
	
	public HomePanel(Client client){
		this.client = client;
		
		this.setLayout(null);
    	this.setBounds(100, 100, 254, 570);
    	  
    	this.lblNewLabel = new JLabel("账号名");
    	lblNewLabel.setBounds(89, 30, 72, 18);
    	add(lblNewLabel);
    	
    	user = new JList();
    	
    	user.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    	user.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			if(e.getClickCount()==2){
    				Object u = user.getSelectedValue();
    				if(u!=null){
    					int index = u.toString().indexOf("/");
    					if(index>-1){
    						String username = u.toString().substring(0, index);
    						if(client.chatHash.get(username)==null){
	    						chatWindow chatwindow = new chatWindow(username, client);
	    						client.chatHash.put(username, chatwindow);
    						}else{
    							chatWindow chatWindow = client.chatHash.get(username);
    							chatWindow.setVisible(true);
    						}
    						
    				   }
    				}
    			}
    		}
    	});
    	user.setFont(new Font("宋体", Font.BOLD, 22));
 
    	  
    	scrollPane = new JScrollPane(user);
    	scrollPane.setBorder(BorderFactory.createTitledBorder("用户列表"));
    	scrollPane.setBounds(14, 82, 223, 461);
    	add(scrollPane);
    	  
    	JComboBox<String> comboBox = new JComboBox<String>();
    	comboBox.addItem("在线");
    	comboBox.addItem("隐身");
    	comboBox.setBounds(191, 27, 58, 24);
    	add(comboBox);
    	  
    	label = new JLabel("\u7528\u6237\u540D");
    	label.setBounds(21, 30, 58, 18);
    	add(label);

    	this.validate();
	}
	 
}
