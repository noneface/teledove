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
import javax.swing.Box;


public class ChatPanel extends JPanel{
	private JScrollPane scrollPane;
	public Client client;
	public String username;
	private JTextPane textPane;
	private JEditorPane editorPane;
	
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
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh：mm：ss");
			String d = simpleDateFormat.format(date);
			
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
	  
	  this.editorPane = new JEditorPane();
	  editorPane.setBounds(14, 368, 523, 78);
	  add(editorPane);
	  
	  JLabel lblTeledove = new JLabel("\u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014> \u2014\u2014>     Teledove");
	  lblTeledove.setBounds(14, 337, 523, 18);
	  add(lblTeledove);
  	  
  	  this.validate();
     }
	
	public void addMessage(String fromUser,String message, String d){
		try {
			message = URLDecoder.decode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.textPane.setText(textPane.getText()+"  "+fromUser+"("+ d +")\n	"+message+"\n");
	}
}
