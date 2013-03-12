package br.edu.cesufoz.cargame.client;

import br.edu.cesufoz.cargame.client.managers.WebSocketManager;


/**
 * 
 * @author rodrigofraga
 */
public class MainClient 
{
	private WebSocketManager webSocketManager;

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		final String webSocketServerName = args.length > 0 && args[0] != null ? args[0] : "127.0.0.1";
		final int webSocketServerPort = args.length > 0 && args[1] != null ? Integer.valueOf(args[1]) : 6969;
		
		final MainClient mainClient = new MainClient();
		mainClient.connectToWebSocket(webSocketServerName, webSocketServerPort);
	}
	
	/**
	 * 
	 */
	public void connectToWebSocket( String webSocketServerName, int webSocketServerPort )
	{
		this.webSocketManager = new WebSocketManager(webSocketServerName, webSocketServerPort);
		this.webSocketManager.start();
	}

}
