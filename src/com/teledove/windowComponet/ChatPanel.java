package com.teledove.windowComponet;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.teledove.client.Client;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;


public class ChatPanel extends JPanel{
	private JScrollPane scrollPane;
	public Client client;
	public String username;
	private JTextPane textPane;
	private JEditorPane editorPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox_1;
	
	public ChatPanel(String username, Client client){
		this.client = client;
		this.username = username;
  	  this.setLayout(null);
  	  this.setBounds(100, 100, 551, 495);
  	  
	  scrollPane = new JScrollPane();
	  scrollPane.setBorder(BorderFactory.createTitledBorder("消息记录"));
	  scrollPane.setBounds(14, 13, 523, 311);
	  add(scrollPane);
	  

	  this.textPane = new JTextPane();

	  this.textPane.setFocusable(false);
	  this.textPane.setEditable(false);

	  scrollPane.setViewportView(textPane);
	  
	  
	  JButton button_1 = new JButton("发送");
	  button_1.addActionListener(new ActionListener() {
	  	public void actionPerformed(ActionEvent arg0) {
	  		
	  		Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String d = "";
			try {
				d = URLEncoder.encode(simpleDateFormat.format(date), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	  		String message = "";
			try {
				message = URLEncoder.encode(editorPane.getText(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		editorPane.setText("");
	  		addMessage(client.getUserState().getUsername(), message, d);
	  		client.sendMessage(username, message, d);
	  	}
	  });
	  button_1.setBounds(424, 455, 113, 27);
	  add(button_1);
	  
	  
	  lblNewLabel = new JLabel("\u5B57\u4F53\u5927\u5C0F");
	  lblNewLabel.setBounds(177, 334, 72, 18);
	  add(lblNewLabel);
	  
	  JComboBox comboBox = new JComboBox();
	  comboBox.addItem("15");
	  comboBox.addItem("18");
	  comboBox.addItem("22");
	  comboBox.addItem("25");
	  comboBox.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  	  int num =Integer.parseInt(comboBox.getSelectedItem().toString());
			  editorPane.setFont(new Font(comboBox_1.getSelectedItem().toString(),Font.PLAIN,num));
		  	}
		  });
	  comboBox.setFocusable(false);
	  comboBox.setBounds(263, 331, 72, 24);
	  add(comboBox);
	  
	  lblNewLabel_1 = new JLabel("\u5B57\u4F53");
	  lblNewLabel_1.setBounds(24, 334, 72, 18);
	  add(lblNewLabel_1);
	  
	  comboBox_1 = new JComboBox();
	  comboBox_1.addItem("宋体");
	  comboBox_1.addItem("隶书");
	  comboBox_1.addItem("黑体");
	  comboBox_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
			  	  int num =Integer.parseInt(comboBox.getSelectedItem().toString());
				  editorPane.setFont(new Font(comboBox_1.getSelectedItem().toString(),Font.PLAIN,num));
		  	}
		  });
	  comboBox_1.setFocusable(false);
	  comboBox_1.setBounds(69, 331, 67, 24);
	  add(comboBox_1);
	  
	  
	  this.editorPane = new JEditorPane();
	  editorPane.setBounds(14, 368, 523, 78);
	  add(editorPane);

	  /*JLabel lblTeledove = new JLabel("\u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014>     Teledove");
	  lblTeledove.setBounds(14, 337, 523, 18);
	  add(lblTeledove);*/
	  
  	  
  	  this.validate();
     }
	
	public void addMessage(String fromUser,String message, String d){
		try {
			message = URLDecoder.decode(message, "UTF-8");
			d = URLDecoder.decode(d, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.textPane.setText(textPane.getText()+"  "+fromUser+"("+ d +")说:\n    "+message+"\n");
	}
}
