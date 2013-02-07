package com.veltro.blazingbarrels.game.location;

/**
 * A more complex {@link Location} subclass, Location3D objects represent three-dimensional locations that facotr in
 * rotation (pitch and yaw).
 * 
 * @author LinearLogic
 * @since 0.1.4
 */

public class Location3D extends Location {

	/**
	 * The x-coordinate (in in-game meters) of the location
	 */
	private float x;

	/**
	 * The y-coordinate (in in-game meters) of the location
	 */
	private float y;

	/**
	 * The z-coordinate (in in-game meters) of the location
	 */
	private float z;

	/**
	 * The pitch (rotation about the y-axis), in degrees, of the location. This value is typically between -180 and 180
	 */
	private float pitch;

	/**
	 * The yaw (rotation about the z-axis), in degrees, of the location
	 */
	private float yaw;

	/**
	 * Simple constructor - takes the x, y, and z coordinates of the location as paramaters
	 * 
	 * @param x The {@link #x x-coordinate} of the location
	 * @param y The {@link #y y-coordinate} of the location
	 * @param z The {@link #z z-coordinate} of the location
	 */
	public Location3D(float x, float y, float z) {
		this(x, y, z, 0, 0);
	}

	/**
	 * Complete constructor - takes the x, y, and z coordinates of the location, as well as its pitch and yaw, as
	 * parameters.
	 * 
	 * @param x The {@link #x x-coordinate} of the location
	 * @param y The {@link #y y-coordinate} of the location
	 * @param z The {@link #z z-coordinate} of the location
	 * @param pitch The {@link #pitch} of the location
	 * @param yaw The {@link #yaw} of the location
	 */
	public Location3D(float x, float y, float z, float pitch, float yaw) {
		super(x, y, z); 
		this.x = x;
		this.y = y;
		this.z = z;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	/**
	 * Overloading of the {@link Location#distanceTo(Location)} method which provides a guaranteed type (and
	 * thus dimensionality) match.
	 * 
	 * @param anotherLocation The second location in the distance equation (along with the location running the method)
	 * @return The distance, a float value, between the two locations
	 */
	public float distanceTo(Location3D anotherLocation) {
		return super.distanceTo(anotherLocation);
	}

	/**
	 * @return The {@link #x x-coordinate} of the location
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets the {@link #x x-coordinate} of the location to the specified value
	 * 
	 * @param x A floating point value
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return The {@link #y y-coordinate} of the location
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets the {@link #y y-coordinate} of the location to the specified value
	 * 
	 * @param y A floating point value
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return The {@link #z z-coordinate} of the location
	 */
	public float getZ() {
		return z;
	}

	/**
	 * Sets the {@link #z z-coordinate} of the location to the specified value
	 * 
	 * @param z A floating point value
	 */
	public void setZ(float z) {
		this.z = z;
	}

	/**
	 * @return The {@link #pitch} of the location
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * Sets the {@link #pitch} of the location to the provided value
	 * 
	 * @param pitch A floating point value
	 */
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	/**
	 * @return The {@link #yaw} of the location
	 */
	public float getYaw() {
		return yaw;
	}

	/**
	 * Sets the {@link #yaw} of the location to the specified value
	 * 
	 * @param yaw A floating point value
	 */
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
}
