package com.teledove.windowComponet;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class chatWindow extends JFrame {

	private JPanel contentPane;
	static String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chatWindow frame = new chatWindow(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public chatWindow(String username) {
		ChatPanel chatPanel = new ChatPanel();
		this.setTitle("与"+username+"对话中");
		this.setContentPane(chatPanel);
		this.setBounds(100, 100, 561, 535);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.validate();
		this.setVisible(true);
	}
}
