package com.teledove.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client3 {
	private Socket socket;
	private DataOutputStream dataOutputStream;
	public Client3() {
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
	
public void connectServer(String username){
		
		try {
			String datagram = "From:Anonymous\n";
			datagram += "To:Server\n";
			datagram += "username:"+username+"\n";
			datagram += "password:test for login\n";
			this.dataOutputStream.write(datagram.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static void main(String[] args) {
		Client3 aClient = new Client3();
		aClient.connectServer("test");
		Scanner input = new Scanner(System.in);
		while(true){
			String datagram = "From:test\nTo:noneface\n";
			datagram += "Type:Message\n";
			datagram += "Data:"+input.nextLine()+"\n";
			aClient.sendData(datagram);
		}
	}
	
}
