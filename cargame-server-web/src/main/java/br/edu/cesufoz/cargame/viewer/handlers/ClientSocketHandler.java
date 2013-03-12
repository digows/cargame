package br.edu.cesufoz.cargame.viewer.handlers;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import br.edu.cesufoz.cargame.entity.Car;

public class ClientSocketHandler extends Thread 
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(ClientSocketHandler.class.getName());
	
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
	public ClientSocketHandler( int port ) 
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
				LOG.info("The web client conected.");
				
				while ( socket.isConnected() && !socket.isClosed() ) 
				{
					if ( socket.getInputStream().available() > 0 )
					{
						final ObjectInputStream objectOutputStream = new ObjectInputStream( socket.getInputStream() );
						final Car car = (Car) objectOutputStream.readObject();
						LOG.info("Received a car: "+car);						
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
