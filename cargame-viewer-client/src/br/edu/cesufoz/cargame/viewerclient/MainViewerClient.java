package br.edu.cesufoz.cargame.viewerclient;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import br.edu.cesufoz.cargame.viewerclient.managers.LocalServerSocketManager;
import br.edu.cesufoz.cargame.viewerclient.managers.ViewerSocketManager;


/**
 * 
 * @author rodrigofraga
 */
public class MainViewerClient 
{
	/**
	 * 
	 */
	private ViewerSocketManager viewerSocketManager;
	/**
	 * 
	 */
	private Set<LocalServerSocketManager> localServerSocketManagers = new HashSet<>();

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException 
	{
		final String viewerServerName = args.length > 0 && args[0] != null ? args[0] : "127.0.0.1";
		final int viewerServerPort = args.length > 0 && args[1] != null ? Integer.valueOf(args[1]) : 6969;
		
		final MainViewerClient mainClient = new MainViewerClient();
		mainClient.connectToViewer(viewerServerName, viewerServerPort);

		final Scanner scanner = new Scanner( System.in );
		String localServer;
		int localServerPort;
		
		while ( true ) 
		{
			System.out.println("Please, set the local server ADDRESS:");
			localServer = scanner.nextLine();
			
			System.out.println("Please, set the local server PORT:");
			localServerPort = Integer.valueOf(scanner.nextLine());
			
			if ( localServer != null && localServerPort != 0 )
			{
				mainClient.connectToLocalServer(localServer, localServerPort);
			}
			
			System.out.println("=====================================");
			Thread.sleep(4000);
		}
	}
	
	/**
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * 
	 */
	public void connectToViewer( String serverName, int port ) throws UnknownHostException, IOException
	{
		ViewerSocketManager.connect(serverName, port);
	}
	
	/**
	 * 
	 */
	public void connectToLocalServer( String serverName, int port )
	{
		final LocalServerSocketManager localServerSocketManager = new LocalServerSocketManager(serverName, port);
		localServerSocketManager.start();
		this.localServerSocketManagers.add(localServerSocketManager);
	}
}
