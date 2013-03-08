package br.edu.cesufoz.cargame.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class MainServer {
	
	private static final Logger LOG = Logger.getLogger(MainServer.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		final int port = args.length > 0 && args[0] != null ? Integer.valueOf(args[0]) : 6969;
		
		try 
		{
			final ServerSocket serverSocket = new ServerSocket(port);
			LOG.info("Server started");

			while (true)
			{
				final Socket socket = serverSocket.accept();
				LOG.info("Client connected...");

		        final BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		        String line;
		        while ( (line = in.readLine()) != null) 
		        {
		            System.out.println(line);
		        }
		        
				LOG.info("Data sent to the client.");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
