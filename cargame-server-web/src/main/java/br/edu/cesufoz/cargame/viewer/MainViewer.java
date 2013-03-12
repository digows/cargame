package br.edu.cesufoz.cargame.viewer;

import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;
import org.eclipse.jetty.websocket.server.WebSocketHandler;

import br.edu.cesufoz.cargame.viewer.handlers.ClientSocketHandler;
import br.edu.cesufoz.cargame.viewer.handlers.ClientWebHandler;

public class MainViewer
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(MainViewer.class.getName());

	/**
	 * @param args[0] web server port
	 * @param args[1] socket server port
	 * 
	 * @throws Exception 
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception 
	{
		final int webServerPort = args.length > 0 && args[0] != null ? Integer.valueOf(args[0]) : 8080;
		final int socketServerPort = args.length > 1 && args[1] != null ? Integer.valueOf(args[1]) : 6969;
		final String webServerContext = args.length > 2 && args[2] != null ? args[2] : "cargame";
		
		final MainViewer mainViewer = new MainViewer();
		mainViewer.startSocketServer(socketServerPort);
		mainViewer.startWebServer(webServerPort, webServerContext);
	}
	
	/**
	 * @throws Exception 
	 */
	public void startWebServer(int port, String context) throws Exception
	{
		final WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/"+context);
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setParentLoaderPriority(true); 
		webAppContext.setConfigurations(new Configuration[] 
				{
					new WebInfConfiguration(),
					new WebXmlConfiguration(),
					new MetaInfConfiguration(),
					new FragmentConfiguration(),
					new AnnotationConfiguration(),
		            new PlusConfiguration(), 
		            new EnvConfiguration()
				}
		);
		
		final WebSocketHandler webSocketHandler = new WebSocketHandler.Simple(ClientWebHandler.class);

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{webAppContext, webSocketHandler});

		final Server server = new Server(port);
        server.setHandler(handlers);	
		
		server.start();
		LOG.info("Web Server started");
		server.join();
	}
	
	/**
	 * 
	 */
	public void startSocketServer( int port ) throws Exception
	{
		new ClientSocketHandler(port).start();
	}
}