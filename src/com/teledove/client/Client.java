package com.teledove.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;

import com.teledove.dao.Dao;
import com.teledove.model.User;
import com.teledove.model.UserState;
import com.teledove.windowComponet.Home;
import com.teledove.windowComponet.chatWindow;
import com.teledove.windowComponet.loginFrame;

public class Client {
	private Socket socket;
	private DataOutputStream dataOutputStream;
	private loginFrame loginFrame;
	private Home home;
	private UserState userState;
	public DefaultListModel userModel;
	public HashMap<String, chatWindow> chatHash;
	
	public Client() {
		// this is user client

		this.userModel = new DefaultListModel();
		this.chatHash = new HashMap<>();
		
		this.loginFrame = new loginFrame(this);
		
		try {
			this.socket = new Socket("127.0.0.1", 9999);
			this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
			new receiveServerDataThread(this, socket).start();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Connect server error");
		}
		
	}
	

	public loginFrame getLoginFrame() {
		return loginFrame;
	}


	public void setLoginFrame(loginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}


	public Home getHome() {
		return home;
	}


	public void setHome(Home home) {
		this.home = home;
		this.home.homePanel.lblNewLabel.setText(this.userState.getUsername());
	}


	public UserState getUserState() {
		return userState;
	}


	public void setUserState(UserState userState) {
		this.userState = userState;
	}


	public void connectServer(String username, String password){
			
		String datagram = "From:Anonymous\n";
		datagram += "To:Server\n";
		datagram += "Type:Login\n";
		datagram += "username:"+username+"\n";
		datagram += "password:"+password+"\n";
		this.sendData(datagram);
	}
	
	public void closeConnect(){
		String datagram = "From:"+this.userState.getUsername()+"\n";
		datagram += "To:Server\n";
		datagram += "Type:Logout\n";
		datagram += "username:"+this.userState.getUsername()+"\n";
		this.sendData(datagram);
	}
	
	public void register(String username, String password){
			
		String datagram = "From:Anonymous\n";
		datagram += "To:Server\n";
		datagram += "Type:Register\n";
		datagram += "username:"+username+"\n";
		datagram += "password:"+password+"\n";
		this.sendData(datagram);
	}
	
	
	public void sendMessage(String username, String message, String d){
		String datagram = "From:"+this.userState.getUsername()+"\n";
		datagram += "To:"+username+"\n";
		datagram += "Type:Message\n";
		datagram += "Date:"+d+"\n";
		datagram += "Data:"+message+"\n";
		this.sendData(datagram);
	}
	
	public void sendData(String data){

		try {
			this.dataOutputStream.write(data.getBytes());
			this.dataOutputStream.write("Done\n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
