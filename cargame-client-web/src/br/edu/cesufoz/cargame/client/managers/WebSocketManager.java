package br.edu.cesufoz.cargame.client.managers;

import java.net.Socket;

/**
 * 
 * @author rodrigofraga
 */
public class WebSocketManager extends Thread 
{
	/**
	 * 
	 */
	private int port;
	/**
	 * 
	 */
	private String serverName;
	/**
	 * 
	 */
	public Socket socket;

	/**
	 * 
	 */
	public WebSocketManager( String serverName, int port ) 
	{
		this.serverName = serverName;
		this.port = port;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() 
	{
		super.run();
		
		try 
		{
			this.socket = new Socket(serverName, port);
			
			while ( socket.isConnected() && !socket.isClosed() ) 
			{
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
