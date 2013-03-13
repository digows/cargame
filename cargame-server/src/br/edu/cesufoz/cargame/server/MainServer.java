package br.edu.cesufoz.cargame.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

import br.edu.cesufoz.cargame.entity.Car;

/**
 * 
 * @author rodrigofraga
 */
public class MainServer 
{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException 
	{
		final ServerSocket serverSocket = new ServerSocket(1230);
		
		final Car car = new Car(UUID.randomUUID().toString(), "My Carr");
		
		final Socket socket = serverSocket.accept();
		
		while ( socket.isConnected() && !socket.isClosed() )
		{
			car.setAngle(0);
			car.setX( new Random().nextFloat() );
			car.setY( new Random().nextFloat() );
			
			final ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(car);
			objectOutputStream.flush();
			System.out.println("Car sent");
			Thread.sleep(2000);
		}
	}
}
