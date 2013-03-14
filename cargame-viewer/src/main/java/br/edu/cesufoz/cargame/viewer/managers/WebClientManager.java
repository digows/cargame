package br.edu.cesufoz.cargame.viewer.managers;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONDateConvertor;
import org.eclipse.jetty.util.ajax.JSONPojoConvertor;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import br.edu.cesufoz.cargame.entity.Car;

/**
 * 
 * @author rodrigo
 */
@WebSocket
public class WebClientManager 
{
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(WebClientManager.class.getName());
	
	static
	{
		JSON.registerConvertor(Calendar.class, new JSONDateConvertor(false));
		JSON.registerConvertor(Car.class, new JSONPojoConvertor(Car.class, false));
	}
	
	/**
	 * 
	 */
	private static final Map<String, Car> cars = new HashMap<>();
	
	/**
	 * 
	 */
	private static Session session;
	
	
	/**
	 * 
	 * @param car
	 * @throws IOException 
	 */
	public static void pushNewMessage( Car car ) throws IOException
	{
		if ( car == null ) return;
		
		cars.put(car.getId(), car);
		
		if ( session != null )
		{
			LOG.info("Pushing new message...");
			session.getRemote().sendString( JSON.toString(car) );
		}
	}
	
	/////------------------------
	
	/**
	 * 
	 * @param session
	 * @throws IOException 
	 */
	@OnWebSocketConnect
	public void onConnect(Session session) throws IOException 
	{
		LOG.info("HTML client conected.");
		WebClientManager.session = session;
		
		//Send the cars added.
		session.getRemote().sendString( JSON.toString(cars.values().toArray()) );
	}
	
	/**
	 * 
	 * @param session
	 */
	@OnWebSocketClose
	public void onClose(Session session, int statusCode, String reason) 
	{
		LOG.info("HTML client disconected.");
		WebClientManager.session = null;
	}
}
