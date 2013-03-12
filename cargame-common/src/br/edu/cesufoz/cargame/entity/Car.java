package br.edu.cesufoz.cargame.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * @author rodrigofraga
 */
public class Car implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299589857855226706L;
	
	/**
	 * 
	 */
	private String id;
	/**
	 * 
	 */
	private float x;
	/**
	 * 
	 */
	private float y;
	/**
	 * 
	 */
	private float angle;
	/**
	 * 
	 */
	private String playerName;
	/**
	 * 
	 */
	private Calendar created;
	
	/**
	 * 
	 */
	public Car(String id, float x, float y, float angle, String playerName) 
	{
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.playerName = playerName;
		this.created = Calendar.getInstance();
	}
	
	/**
	 * 
	 */
	public Car(String id, String playerName) 
	{
		super();
		this.id = id;
		this.playerName = playerName;
		this.created = Calendar.getInstance();
	}
	
	/**
	 * 
	 */
	public Car(float x, float y, float angle) 
	{
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.created = Calendar.getInstance();
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Calendar getCreated() {
		return created;
	}
	public void setCreated(Calendar created) {
		this.created = created;
	}
}
