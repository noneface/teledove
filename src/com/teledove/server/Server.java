package com.teledove.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.teledove.model.User;
import com.teledove.model.userState;

public class Server {
	
	private HashMap<String, Socket> socketPool;
	private ServerSocket serverSocket;
	
	public Server() {
		 
		this.socketPool = new HashMap<>();
		try {
			serverSocket = new ServerSocket(9999);  //  listen the port ,and accept the new socket connect
			while(true){
				Socket socket = this.serverSocket.accept();
				this.addSocket(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSocket(Socket socket){
		try {
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message;
			String username = null;
			while(!(message=br.readLine()).equals("Done")){
				username = message.split(":")[1];
			}
			if(this.socketPool.get(username) != null){
				this.socketPool.put(username, socket);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// To run the server
	}
}
