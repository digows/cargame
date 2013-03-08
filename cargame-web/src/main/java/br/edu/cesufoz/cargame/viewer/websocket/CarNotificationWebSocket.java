package br.edu.cesufoz.cargame.viewer.websocket;

import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * 
 * @author rodrigo
 */
@WebSocket
public class CarNotificationWebSocket 
{
	/**
	 * 
	 * @param message
	 */
	@OnWebSocketMessage
	public void onText(String message) 
	{
		System.out.println("onText -> "+ message);
	}
}
