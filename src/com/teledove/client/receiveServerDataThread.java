package com.teledove.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.teledove.model.UserState;
import com.teledove.windowComponet.Home;

public class receiveServerDataThread extends Thread {
	private Socket socket;
	private Client client;
	
	
	public receiveServerDataThread(Client client,Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.client = client;
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
			this.client.setHome(new Home());
		}else{
			System.out.println("Login error");
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
			this.client.setHome(new Home());
		}else{
			System.out.println("Login error");
		}
		
	}
	
	
	public void dispatchDatagram(String From, String datagram, String type){
		if(From.equals("Server")){
			if(type.equals("Login")){
				processLogin(datagram);
			}else if (type.equals("Register")) {
				
			}
		}else{
			
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
