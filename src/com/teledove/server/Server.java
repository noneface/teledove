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
	
	private HashMap<userState, Socket> socketPool;
	private ServerSocket serverSocket;
	
	public Server() {
		 
		this.socketPool = new HashMap<>();
		try {
			serverSocket = new ServerSocket(9999);  //  listen the port ,and accept the new socket connect
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// To run the server
	}
}
