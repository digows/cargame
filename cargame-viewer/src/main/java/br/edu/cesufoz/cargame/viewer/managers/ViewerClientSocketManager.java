package br.edu.cesufoz.cargame.viewer.managers;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import br.edu.cesufoz.cargame.entity.Car;

public class ViewerClientSocketManager extends Thread 
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(ViewerClientSocketManager.class.getName());
	
	/**
	 * 
	 */
	private int port;

	/**
	 * 
	 */
	private ServerSocket serverSocket;
	
	/**
	 * 
	 */
	public ViewerClientSocketManager( int port ) 
	{
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
			this.serverSocket = new ServerSocket(port);
			LOG.info("Socket Server started");
			
			while (true)
			{
				final Socket socket = serverSocket.accept();
				LOG.info("The viewer client conected.");
				
				//FIXME Remove this loop and put the NIO.
				while ( socket.isConnected() && !socket.isClosed() ) 
				{
					if ( socket.getInputStream().available() > 0 )
					{
						final ObjectInputStream objectOutputStream = new ObjectInputStream( socket.getInputStream() );
						final Car car = (Car) objectOutputStream.readObject();
						
						LOG.info("Received a car: "+car);
						WebClientManager.pushNewMessage(car);
					}
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
