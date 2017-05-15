package com.teledove.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.teledove.model.User;

public class connectListenerThread extends Thread{
	
	private ServerSocket ss;
	private Server server;
	
	public void setServerSocket(ServerSocket ss){
		this.ss = ss;
	}
	
	public void setServer(Server server){
		this.server = server;
	}
	
	public Boolean validateUser(User user){
		
		if(true){   //  Dao  validate user information  user.validateInformation()
			return true;
		}
		
		return false;
	}
	
	public void processConnect(Socket socket){

		try {
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String message;
			User user = new User();
			
			while(!(message=br.readLine()).equals("Done")){
				
				String[] data = message.split(":");
				
				if(data[0].equals("username")){
					user.setUsername(data[1]);
				}
				
				if(data[0].equals("password")){
					user.setPassword(data[1]);
				}
			}
			if(this.validateUser(user)){

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	
	}
}
