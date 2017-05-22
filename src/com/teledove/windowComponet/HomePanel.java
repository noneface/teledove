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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;

public class HomePanel extends JPanel{
	public JList user;
	private JScrollPane scrollPane;
	public JLabel lblNewLabel;
	private JLabel label;
	private Client client;
	public String state;
	
	public HomePanel(Client client){
		this.client = client;
		this.state = "online";
		this.setLayout(null);
    	this.setBounds(100, 100, 254, 570);
    	  
    	this.lblNewLabel = new JLabel("账号名");
    	lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
    	lblNewLabel.setBounds(89, 10, 155, 39);
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
    	  
    	label = new JLabel("用户名:");
    	label.setFont(new Font("宋体", Font.PLAIN, 18));
    	label.setBounds(14, 20, 76, 18);
    	add(label);
    	
    	JComboBox comboBox = new JComboBox();
    	comboBox.addItemListener(new ItemListener(){
    		   @Override
    		   public void itemStateChanged(ItemEvent e)
    		   {
    		//如果选中了一个 
    		    if (e.getStateChange() == ItemEvent.SELECTED){
    		    	String cstate = (String) comboBox.getSelectedItem();
    		    	if( ! cstate.equals(state)){
    		    		state = cstate;
    		    		client.setState(cstate);
    		    	}
    		    }
    		   }
    	});
    	
    	comboBox.addItem("online");
    	comboBox.addItem("hide");
    	
    	comboBox.setBounds(145, 47, 92, 24);
    	add(comboBox);

    	this.validate();
	}
}
