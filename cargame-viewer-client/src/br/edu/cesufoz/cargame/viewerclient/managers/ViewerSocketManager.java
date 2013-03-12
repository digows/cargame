package br.edu.cesufoz.cargame.viewerclient.managers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import br.edu.cesufoz.cargame.entity.Car;

/**
 * 
 * @author rodrigofraga
 */
public class ViewerSocketManager
{
	/**
	 * 
	 */
	public static Socket socket;

	/**
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * 
	 */
	public static void connect( String serverName, int port ) throws UnknownHostException, IOException 
	{
		socket = new Socket(serverName, port);
	}
	
	/**
	 * @throws IOException 
	 */
	public static void sendCarUpdate( Car car ) throws IOException
	{
		final ObjectOutputStream objectOutputStream = new ObjectOutputStream( socket.getOutputStream() );
		objectOutputStream.writeObject(car);
		objectOutputStream.flush();
	}
}
