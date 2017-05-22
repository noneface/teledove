package com.teledove.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.teledove.model.UserState;
import com.teledove.windowComponet.Home;
import com.teledove.windowComponet.chatWindow;

public class receiveServerDataThread extends Thread {
	private Socket socket;
	private Client client;
	
	
	public receiveServerDataThread(Client client,Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.client = client;
	}
	

	public void dispatchDatagram(String From, String datagram, String type){
		if(From.equals("Server")){
			if(type.equals("Login")){
				processLogin(datagram);
			}else if (type.equals("Register")) {
				
			}else if(type.equals("AllState")){
				processAllState(datagram);
			}else if(type.equals("Notify")){
				processAlterState(datagram);
			}
		}else{
			if(type.equals("Message")){
				ProcessMessage(datagram);
			}
		}
	}
	
	public void processLogin(String datagram){
		String Confirm="";
		String username=null;
		String[] data = datagram.split("\n");
		for(String s:data){
			if(s.split(":")[0].equals("Data"))
				Confirm = s.split(":")[1];
			if(s.split(":")[0].equals("To"))
				username = s.split(":")[1];
		}
		
		if(Confirm.equals("Success")){
			this.client.getLoginFrame().dispose();
			this.client.setUserState(new UserState(username, "online"));
			Home home = new Home(this.client);
			home.homePanel.user.setModel(this.client.userModel);
			this.client.setHome(home);
		}else{
			JOptionPane.showMessageDialog(null, "登录失败", "噢哦~", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	public void processRegister(String datagram){
		String Confirm="";
		String username=null;
		String[] data = datagram.split("\n");
		for(String s:data){
			if(s.split(":")[0].equals("Data"))
				Confirm = s.split(":")[1];
			if(s.split(":")[0].equals("To"))
				username = s.split(":")[1];
		}
		
		if(Confirm.equals("Success")){
			this.client.getLoginFrame().dispose();
			this.client.setUserState(new UserState(username, "online"));
			this.client.setHome(new Home(this.client));
		}else{
			System.out.println("Login error");
		}
		
	}
	
	public void processAllState(String datagram){
		String[] data = datagram.split("\n");
		for(String s:data){
			String[] message = s.split(":");
			if(message[0].equals("State")){
				String username = message[1].split("/")[0];
				String state = message[1].split("/")[1];
				this.client.userModel.addElement(username+"/"+state);
			}	
		}
	}
	
	public void processAlterState(String datagram){
		String[] data = datagram.split("\n");
		for(String s:data){
			String[] message = s.split(":");
			if(message[0].equals("State")){
				String username = message[1].split("/")[0];
				String state = message[1].split("/")[1];
				for(int i=0;i<this.client.userModel.size();i++){
					String current = (String) this.client.userModel.get(i);
					String[] temp = current.split("/");
					if(temp[0].equals(username)){
						this.client.userModel.remove(i);
						break;
					}
				}
				this.client.userModel.addElement(username+"/"+state);
			}	
		}
		
	}
	
	public void ProcessMessage(String datagram){
		String[] data = datagram.split("\n");
		String from=null;
		String message = "";
		String date = "";
		for(String s:data){
			if(s.split(":")[0].equals("From"))
				from = s.split(":")[1];
			if(s.split(":")[0].equals("Data"))
				message = s.split(":")[1];
			if(s.split(":")[0].equals("Date"))
				date = s.split(":")[1];
		}
		
		if(this.client.chatHash.get(from) != null){
			chatWindow chatwindow = this.client.chatHash.get(from);
			chatwindow.chatPanel.addMessage(from, message, date);
		}else{
			chatWindow chatwindow = new chatWindow(from, this.client);
			this.client.chatHash.put(from, chatwindow);
			chatwindow.chatPanel.addMessage(from, message, date);
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			InputStream is = this.socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			String message;
			while(true){
				String from = null;
				String datagram = "";
				String type = null;
				
				while(!((message=br.readLine()).equals("Done"))){
					datagram += message+"\n";
					if(message.split(":")[0].equals("From"))
						from = message.split(":")[1];
					if(message.split(":")[0].equals("Type"))
						type = message.split(":")[1];
				}

				System.out.print(datagram);
				System.out.print("\n");
				this.dispatchDatagram(from, datagram, type);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
