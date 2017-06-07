# teledove
This is an IM application develop in Java.

C/S model are used to design this teledove chat application.

For more details, we use Java Swing to implements our UI.

In the server, we choice Blocking I/O model, for each connect or socket to create a new Thread(receiveClientDataThread.java).

# Useage

You should create a Mysql Database and change src.hibernate.cfg.xml which file is settings file for Hibernate.

src.mysql is Database structure, and you can import and run this file to Mysql.	

In src.com.teledove.server.Server, run this file and then the server is starting.

In src.test.startUp.java , run this file to start client, you can start several clients in the same machine.

# Contributor
	- @noneface
	- @liubanxian
