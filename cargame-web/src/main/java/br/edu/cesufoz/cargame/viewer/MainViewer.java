package br.edu.cesufoz.cargame.viewer;

import java.io.IOException;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.websockets.WebSocketAddOn;

public class MainViewer 
{
	private static final Logger LOG = Logger.getLogger(MainViewer.class.getName());
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException 
	{
		final HttpServer httpServer = HttpServer.createSimpleServer("src/main/webapp", 8080);
		
		for ( NetworkListener listener : httpServer.getListeners() ) 
		{
		    listener.registerAddOn(new WebSocketAddOn());
		}
		
		httpServer.start();
		LOG.info("Main Viewer Server started");
		
		System.in.read();
		httpServer.stop();
	}
}
