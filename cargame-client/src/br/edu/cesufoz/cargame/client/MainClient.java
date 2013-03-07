package br.edu.cesufoz.cargame.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String server = args.length > 0 && args[0] != null ? args[0] : "localhost";
		final int port = args.length > 0 && args[1] != null ? Integer.valueOf(args[1]) : 6969;
		
		try {
			
			final Socket socket = new Socket(server, port);
			System.out.println( socket.getInputStream().read() );
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
