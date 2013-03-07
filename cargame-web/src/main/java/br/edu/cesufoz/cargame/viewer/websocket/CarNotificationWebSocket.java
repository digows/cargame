package br.edu.cesufoz.cargame.viewer.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="carstreaming")
public class CarNotificationWebSocket
{
	@OnOpen
    public void onOpen(Session session) throws IOException 
    {
		System.out.println("onOpen");
		
        session.getBasicRemote().sendText("onOpen: "+session);
        //session.getBasicRemote().sendBinary(byteBuffer);
        //final Future<Void> future = session.getAsyncRemote().sendText("myText");
    }
	
	@OnMessage
    public String onMessage(String s) 
	{
		System.out.println("onMessage: "+s);
		
        return s;
    }
}
