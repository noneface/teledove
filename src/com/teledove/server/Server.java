package com.teledove.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.teledove.model.User;
import com.teledove.model.userState;

public class Server {
	
	public HashMap<String, Socket> socketPool;
	private ServerSocket serverSocket;
	
	public Server() {
		 
		this.socketPool = new HashMap<>();
		try {
			serverSocket = new ServerSocket(9999);  //  listen the port ,and accept the new socket connect
			while(true){
				Socket socket = this.serverSocket.accept();
				String username = this.validateSocket(socket);
				this.showOnlineUser(socket, username);
				new receiveClientDataThread(this, socket).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String validateSocket(Socket socket){
		String username = null;
		try {
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message;
			while(!((message=br.readLine()).equals("Done"))){
				username = message.split(":")[1];
			}
			if(this.socketPool.get(username) == null){
				System.out.println(username+" is login");
				this.alterOnlineUser(username);
				this.socketPool.put(username, socket);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}
	
	public void alterOnlineUser(String username){
		for(Socket s: this.socketPool.values()){
			String datagram = "From:Server\n";
			datagram += "Type:Notify\nDetial:Online\n";
			datagram += "username:"+username+"\n";
			this.sendData(s, datagram);
		}
	}
	
	public void showOnlineUser(Socket socket, String username){
		String datagram ="From:Server\n";
		datagram +="Type:Notify\nDetial:AllOnLine\n";
		for(String s: this.socketPool.keySet()){
			if(!username.equals(s)){
				datagram += "username:"+s+"\n";
			}
		}
		this.sendData(socket, datagram);
	}
	
	public void sendData(Socket socket, String data) {
		try {
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			dataOutputStream.write(data.getBytes());
			dataOutputStream.write("Done\n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// To run the server
		new Server();
	}
}
