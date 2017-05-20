package com.teledove.windowComponet;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Home extends JFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		HomePanel homePanel = new HomePanel();
		this.setContentPane(homePanel);
		this.setBounds(600, 100, 312, 585);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	}
}
