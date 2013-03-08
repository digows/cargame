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

import br.edu.cesufoz.cargame.viewer.websocket.CarNotificationWebSocket;

public class MainViewer
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(MainViewer.class.getName());

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception 
	{
		final WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/cargame");
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
		
		final WebSocketHandler webSocketHandler = new WebSocketHandler.Simple(CarNotificationWebSocket.class);

        final HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{webAppContext, webSocketHandler});

		final Server server = new Server(8080);
        server.setHandler(handlers);	
		
		server.start();
		LOG.info("Main Viewer Server started");
		server.join();
	}
}