package br.edu.cesufoz.cargame.viewerclient.managers;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

import br.edu.cesufoz.cargame.entity.Car;

/**
 * 
 * @author rodrigofraga
 */
public class LocalServerSocketManager extends Thread 
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(LocalServerSocketManager.class.getName());
	
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
	public LocalServerSocketManager( String serverName, int port ) 
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
			LOG.info("Local server connected: "+serverName);

			//FIXME Remove this loop and use NIO.
			while ( socket.isConnected() && !socket.isClosed() ) 
			{
				if ( socket.getInputStream().available() > 0 )
				{
					final ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
					ViewerSocketManager.sendCarUpdate( (Car) objectInputStream.readObject() );
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
