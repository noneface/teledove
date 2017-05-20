package com.teledove.server;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.teledove.dao.Dao;
import com.teledove.dao.HibernateUtil;
import com.teledove.model.User;

public class Server {
	
	public HashMap<String, Socket> socketPool;
	private ServerSocket serverSocket;
	public Dao userDao;
	private List<User> userList;
	
	public Server() { 
		 this.userDao = new Dao();
		 userList = this.userDao.load();
		 
		 this.socketPool = new HashMap<>();
		 try {
			 serverSocket = new ServerSocket(9999);  //  listen the port ,and accept the new socket connect
			 while(true){
				 Socket socket = this.serverSocket.accept();
				 new receiveClientDataThread(this, socket).start();
			 }
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	}
	
	
	public void alterOnlineUser(String username){
		for(Socket s: this.socketPool.values()){
			String datagram = "From:Server\n";
			datagram += "Type:Notify\n";
			datagram += "State:"+username+"/online\n";
			this.sendData(s, datagram);
		}
	}
	
	public void showOnlineUser(Socket socket, String username){
		String datagram ="From:Server\n";
		datagram += "To:"+username+"\n";
		datagram +="Type:AllState\n";
		
		for(User u:userList){
			if(!username.equals(u.getUsername())){
				datagram += "State:"+u.getUsername();
				if(this.socketPool.get(u.getUsername())!=null)
					datagram += "/online\n";
				else{
					datagram += "/offline\n";
				}
					
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
