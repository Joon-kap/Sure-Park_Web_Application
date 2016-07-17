package com.ajou.cmu.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConnArduino implements Runnable {
	public void run() {
		test();
	}
	
	private static boolean available(int port) {
	    System.out.println("--------------Testing port " + port);
	    Socket s = null;
	    try {
	        s = new Socket("localhost", port);

	        // If the code makes it this far without an exception it means
	        // something is using the port and has responded.
	        System.out.println("--------------Port " + port + " is not available");
	        return false;
	    } catch (IOException e) {
	        System.out.println("--------------Port " + port + " is available");
	        return true;
	    } finally {
	        if( s != null){
	            try {
	                s.close();
	            } catch (IOException e) {
	                throw new RuntimeException("You should handle this error." , e);
	            }
	        }
	    }
	}
	
	public void test()
 	{
    	ServerSocket serverSocket = null;							// Server socket object
		Socket clientSocket = null;									// Client socket object
    	int	portNum = 550;											// Port number for server socket
    	String[]serverMsg = {	"Hello there little Arduino...\n", 	// Server messages. You can add
    							"Hello Arduino from the PC...\n"	//  more messages here if you want to.
    						};										//
    	int msgNum = 0;												// Message to display from serverMsg[]
   		String inputLine;											// Data from client

		while(true)
		{
    		/*****************************************************************************
    	 	* First we instantiate the server socket. The ServerSocket class also does
    	 	* the listen()on the specified port.
		 	*****************************************************************************/
    		try
    		{
    			if(!available(portNum)){
    				break;
    			}
        		serverSocket = new ServerSocket(portNum);
        		System.out.println ( "\n\nWaiting for connection on port " + portNum + "." );
        	}
    		catch (IOException e)
        	{
        		System.err.println( "\n\nCould not instantiate socket on port: " + portNum + " " + e);
        		System.exit(1);
        	}

			/*****************************************************************************
    	 	* If we get to this point, a client has connected. Now we need to
    	 	* instantiate a client socket. Once its instantiated, then we accept the
    	 	* connection.
		 	*****************************************************************************/

	    	try
    		{
        		clientSocket = serverSocket.accept();
        	}
    		catch (Exception e)
        	{
        		System.err.println("Accept failed.");
        		System.exit(1);
        	}

			/*****************************************************************************
    	 	* At this point we are all connected and we need to create the streams so
    	 	* we can read and write.
		 	*****************************************************************************/

    		System.out.println ("Connection successful");
    		System.out.println ("Waiting for input.....");
    		String scanmsg;
    		BufferedWriter out = null;
    		BufferedReader in = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scanner scan = new Scanner(System.in);

    		while(true) {

    			scanmsg = scan.nextLine();
	 	    	try
	 	    	{

	 				System.out.println( "Sending message to client...." );
	   				out.write( scanmsg, 0, scanmsg.length() );
					out.flush();

	    			
					
				} catch (Exception e) {

					System.err.println("write failed::");
	        		//System.exit(1);

				}
				
    			if(scanmsg.equals("bye")) {
    				break;
    			}
    			
/*
 	    		if ((inputLine = in.readLine()) != null)
    			{
      				System.out.println ("Last Client Message: " + inputLine);

    			}*/

    			
    		}
			/*****************************************************************************
			 * Print out the fact that we are sending a message to the client, then we
			 * send the message to the client.
			 *****************************************************************************/

			/*****************************************************************************
    		 * Close up the I/O ports then close up the sockets
		 	 *****************************************************************************/

	    	try {
				out.close();
				in.close();
				clientSocket.close();
		    	serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			scan.close();

   		 	

			System.out.println ( "\n.........................\n" );

    	} // while loop
   	} // main

}
