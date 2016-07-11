package com.ajou.cmu.common;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WebSocketDemo {
	 
	    @OnMessage
	    public void echoTextMessage(Session session, String msg, boolean last) {
	        try {
	            if (session.isOpen()) {
	                session.getBasicRemote().sendText(msg, last);
	                Thread.sleep(5000);
	                session.getBasicRemote().sendText(msg+"zzzzz", last);
	                
	                
	            }
	        } catch (IOException e) {
	            try {
	                session.close();
	            } catch (IOException e1) {
	                // Ignore
	            }
	        } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        new ConnArduino().test();
	        
	    }
	 
	 
	}

