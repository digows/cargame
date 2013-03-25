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
	public Car() 
	{
		this.created = Calendar.getInstance();
	}
	
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(angle);
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (Float.floatToIntBits(angle) != Float.floatToIntBits(other.angle))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Car [id=" + id + ", x=" + x + ", y=" + y + ", angle=" + angle
				+ ", playerName=" + playerName +"]";
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
