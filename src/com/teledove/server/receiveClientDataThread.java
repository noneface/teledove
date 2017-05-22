package com.teledove.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.teledove.model.User;

public class receiveClientDataThread extends Thread {
	
	private Server server;
	private Socket socket;
	private String username;
	
	public receiveClientDataThread(Server server, Socket socket) {
		// TODO Auto-generated constructor stub
		this.server = server;
		this.socket = socket;
		this.username = "Anonymous";
	}
	
	public void validate(String username, String password){
		if(this.server.userDao.login(username, password) != null){
			if(this.server.socketPool.get(username) == null){
				this.server.showOnlineUser(socket, username);
				this.server.alterOnlineUser(username);
				this.server.socketPool.put(username, this.socket);
				this.username = username;
				String senddatagram = "From:Server\n";
				senddatagram += "To:"+username+"\n";
				senddatagram += "Type:Login\n";
				senddatagram += "Data:Success\n";
				this.server.sendData(this.socket, senddatagram);	
				
			}else{
				String senddatagram = "From:Server\n";
				senddatagram += "To:"+username+"\n";
				senddatagram += "Type:Login\n";
				senddatagram += "Data:Already Login\n";
				this.server.sendData(this.socket, senddatagram);
			}
		}else{
			String senddatagram = "From:Server\n";
			senddatagram += "To:"+username+"\n";
			senddatagram += "Type:Login\n";
			senddatagram += "Data:Failed\n";
			this.server.sendData(this.socket, senddatagram);
		}
	}
	
	public void loginService(String datagram){
		String username="";
		String password="";
		String[] data = datagram.split("\n");
		for(String s:data){
			if(s.split(":")[0].equals("username"))
				username = s.split(":")[1];
			if(s.split(":")[0].equals("password"))
				password = s.split(":")[1];
		}
		this.validate(username, password);
	}
	
	public void registerService(String datagram){
		String username="";
		String password="";
		String[] data = datagram.split("\n");
		for(String s:data){
			if(s.split(":")[0].equals("username"))
				username = s.split(":")[1];
			if(s.split(":")[0].equals("password"))
				password = s.split(":")[1];
		}
		if(this.registerValidate(username)){
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			this.server.userDao.add(user);
		}
	}
	public boolean registerValidate(String username){
		if(this.server.userDao.queryUserByUsername(username)!=null){
			String senddatagram = "From:Server\n";
			senddatagram += "To:"+username+"\n";
			senddatagram += "Type:Register\n";
			senddatagram += "Data:Failed\n";
			this.server.sendData(this.socket, senddatagram);
			return false;
		}else{
			this.server.showOnlineUser(socket, username);
			this.server.alterOnlineUser(username);
			this.server.socketPool.put(username, this.socket);
			this.username=username;
			String senddatagram = "From:Server\n";
			senddatagram += "To:"+username+"\n";
			senddatagram += "Type:Register\n";
			senddatagram += "Data:Success\n";
			this.server.sendData(this.socket, senddatagram);
			return true;
		}
	}
	public void messageService(String datagram){
		String[] data = datagram.split("\n");
		String to = null;
		String message = "";
		for(String s:data){
			if(s.split(":")[0].equals("To"))
				to = s.split(":")[1];
			if(s.split(":")[0].equals("Data"))
				message = s.split(":")[1];
		}
		
		Socket socket = this.server.socketPool.get(to);
		System.out.println("send message");
		
		
		this.server.sendData(socket, datagram);
	}
	
	public void dispatchDatagram(String To, String datagram, String type){
		if(To.equals("Server")){
			if(type.equals("Login")){
				loginService(datagram);				
			}else if (type.equals("Register")) {
				registerService(datagram);
			}
		}else{
			if(type.equals("Message")){
				messageService(datagram);
			}
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
				String to = null;
				String datagram = "";
				String type = null;
				
				while(!((message=br.readLine()).equals("Done"))){
					datagram += message+"\n";
					if(message.split(":")[0].equals("To"))
						to = message.split(":")[1];
					if(message.split(":")[0].equals("Type"))
						type = message.split(":")[1];
				}

				System.out.print(datagram);
				this.dispatchDatagram(to, datagram, type);
			} 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(this.username+" is offline!");
		}
	}
}
