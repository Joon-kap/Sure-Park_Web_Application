
package com.ajou.cmu.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.ajou.cmu.reservation.ReservationController;
import com.ajou.cmu.sensor.SensorController;
import com.ajou.cmu.sensor.SensorStatus;

public class ConnArduino implements Runnable {
	String OpenEntryMessage = "SERVERREQ_OPEN_ENTRY";
	String OpenExitMessage = "SERVERREQ_OPEN_EXIT";
	String changeStatus = "SERVERREQ_CHANGESTATUS";
	String releaseStatus = "SERVERREQ_RELEASESTATUS";
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
        		break;
        		//System.exit(1);
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
        		break;
        		//System.exit(1);
        	}

			/*****************************************************************************
    	 	* At this point we are all connected and we need to create the streams so
    	 	* we can read and write.
		 	*****************************************************************************/

    		System.out.println ("Connection successful");
    		System.out.println ("Waiting for input.....");
//    		String scanmsg;
    		BufferedWriter out = null;
    		BufferedReader in = null;
//    		Scanner scan = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
//				scan = new Scanner(System.in);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
System.out.println("-------while(true) ENTER------------");

    		while(true) {
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

	 	    	try
	 	    	{	
	 	    		//Check valid period of Identification
	 	    		//Check valid period of gate open message
	    			//scanmsg = scan.nextLine();
	 	    		
	 	    		//System.out.println(SensorStatus.getEntryGate());
	 	    		//System.out.println(ReservationController.identification);
					if(SensorStatus.getEntryGate(1)==0 && ReservationController.identification == 1) {
						SensorStatus.setEntryGate(1,0);
						ReservationController.identification=0;
						
		 				System.out.println( "Sending OpenEntryMessage message to client...." );
		   				out.write( OpenEntryMessage, 0, OpenEntryMessage.length() );
						out.flush();
						
					} else if(SensorStatus.getExitGate(1)==0 && ReservationController.identification == 1) {
				    	//System.out.println("MONITORING = " + SensorStatus.getEntryGate());
						SensorStatus.setExitGate(1,0);
						ReservationController.identification=0;
						
		 				System.out.println( "Sending OpenExitMessage message to client...." );
		   				out.write( OpenExitMessage, 0, OpenExitMessage.length() );
						out.flush();
					} else if(ReservationController.spot != 0){
						System.out.println( "Sending OpenExitMessage message to client...." );
		   				out.write( changeStatus + "00" + ReservationController.spot, 0, changeStatus.length() + 3 );
		   				ReservationController.spot = 0;
						out.flush();
					} else if(ReservationController.releaseStatus == 1){
						System.out.println( "Sending OpenExitMessage message to client...." );
		   				out.write( releaseStatus, 0, releaseStatus.length() );
		   				ReservationController.releaseStatus = 0;
						out.flush();
					}
						
					out.write( "echo", 0, 4 );
	   				ReservationController.releaseStatus = 0;
					out.flush();
					
					
					System.out.println(serverSocket.isBound());
					
					
				} catch (Exception e) {
					System.err.println("write failed::");
					e.printStackTrace();
					break;
				}
    		}
			/*****************************************************************************
			 * Print out the fact that we are sending a message to the client, then we
			 * send the message to the client.
			 *****************************************************************************/

			/*****************************************************************************
    		 * Close up the I/O ports then close up the sockets
		 	 *****************************************************************************/
			System.out.println ( "\n.........................\n" );
			try {
				System.out.println("-------Socket CLOSE TRY------------");
				clientSocket.close();
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.toString());
				e.printStackTrace();
				//break;
			}
    		try {
				System.out.println("-------CLOSE TRY------------");
				out.close();
				in.close();
				//scan.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.err.println(e.toString());
				e.printStackTrace();
				break;
			}	
			

    	} // while loop
		


    	System.out.println("exit server");
    	
   	} // main

}
