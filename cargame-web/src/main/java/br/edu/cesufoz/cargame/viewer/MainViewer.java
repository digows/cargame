package br.edu.cesufoz.cargame.viewer;

import java.io.IOException;
import java.util.logging.Logger;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.FragmentConfiguration;
import org.eclipse.jetty.webapp.MetaInfConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

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
		final WebAppContext appContext = new WebAppContext();
		appContext.setContextPath("/cargame");
		appContext.setResourceBase("src/main/webapp");
		appContext.setConfigurations(new Configuration[] 
				{
					new AnnotationConfiguration(), new WebXmlConfiguration(), new WebInfConfiguration(),
		            new PlusConfiguration(), new MetaInfConfiguration(),
		            new FragmentConfiguration(), new EnvConfiguration()
				}
		);

		final Server server = new Server(8080);
        server.setHandler(appContext);	
		
		server.start();
		LOG.info("Main Viewer Server started");
		server.join();
	}
}