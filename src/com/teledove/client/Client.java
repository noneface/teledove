package com.teledove.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket socket;
	private DataOutputStream dataOutputStream;
	public Client() {
		// this is user client
		
		try {
			this.socket = new Socket("127.0.0.1", 9999);
			this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
			new receiveServerDataThread(socket).start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void connectServer(String username, String password){
			
		String datagram = "From:Anonymous\n";
		datagram += "To:Server\n";
		datagram += "type:Login\n";
		datagram += "username:"+username+"\n";
		datagram += "password:"+password+"\n";
		this.sendData(datagram);
	}
	
	public void sendMessage(String message){
		String datagram = "From:";
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
