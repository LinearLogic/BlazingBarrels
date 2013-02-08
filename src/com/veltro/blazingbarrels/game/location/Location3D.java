package com.veltro.blazingbarrels.game.location;

import java.util.Arrays;

import org.lwjgl.util.vector.Vector3f;

/**
 * A more complex {@link Location} subclass, Location3D objects represent three-dimensional locations that factor in
 * rotation (yaw, pitch, and roll). NOTE: the 'up and down' dimension is the y dimension, as is the convention for
 * graphics design. The z axis runs into the screen, and the x axis runs across it, from left to right.
 * 
 * <p>Coordinate values are in pixels.
 * 
 * @author LinearLogic
 * @since 0.1.4
 */
public class Location3D extends Location {

	/**
	 * The yaw (rotation about the y-axis), in degrees, of the location. This value is on the domain [0, 360)
	 */
	private float yaw;

	/**
	 * The pitch (rotation about the y-axis), in degrees, of the location. This value is on the domain [0, 360)
	 */
	private float pitch;

	/**
	 * The roll (rotation about the x-axis), in degrees, of the location. This value is on the domain [0, 360)
	 */
	private float roll;

	/**
	 * Simplest constructor - calls the {@link #Location3D(float, float, float, float, float, float) complete
	 * constructor} passing 0, 0, 0, 0, 0, 0 for x, y, z, yaw, pitch, roll
	 */
	public Location3D() {
		this(0, 0, 0, 0, 0, 0);
	}

	/**
	 * Simple constructor - takes the x, y, and z coordinates of the location as parameters, and initializes the
	 * rotation variables to zero, the default value
	 * 
	 * @param x The x-coordinate, in pixels, of the location
	 * @param y The y-coordinate, in pixels, of the location
	 * @param z The z-coordinate, in pixels, of the location
	 */
	public Location3D(float x, float y, float z) {
		this(x, y, z, 0, 0, 0);
	}

	/**
	 * Complete constructor - takes the x, y, and z coordinates of the location, as well as its pitch and yaw, as
	 * parameters.
	 * 
	 * @param x The x-coordinate, in pixels, of the location
	 * @param y The y-coordinate, in pixels, of the location
	 * @param z The z-coordinate, in pixels, of the location
	 * @param yaw The {@link #yaw} of the location
	 * @param pitch The {@link #pitch} of the location
	 * @param roll The {@link #roll} of the location
	 */
	public Location3D(float x, float y, float z, float yaw, float pitch, float roll) {
		super(x, y, z); 
		this.roll = roll;
		this.pitch = pitch;
		this.yaw = yaw;
	}

	/**
	 * Shifts the location by the specified integer amounts in each direction (casts the provided ints to floats, which
	 * are in turn passed into the {@link #translate(float x, float y)} method)
	 * 
	 * @param dx x-displacement
	 * @param dy y-displacement
	 * @param dz z-displacement
	 */
	public void translate(int dx, int dy, int dz) {
		this.translate((float) dx, (float) dy, (float) dz);
	}

	/**
	 * Shifts the location by the specified floating point amounts in each direction by passing the values to the
	 * {@link Location#translate(float...)} method in the Location superclass
	 * 
	 * @param dx x-displacement
	 * @param dy y-displacement
	 * @param dz z-displacement
	 */
	public void translate(float dx, float dy, float dz) {
		super.translate(dx, dy, dz);
	}

	/**
	 * Rotates the location by the provided integer amount in each rotational direction
	 * 
	 * @param yawAmount
	 * @param pitchAmount
	 * @param rollAmount
	 */
	public void rotate(int yawAmount, int pitchAmount, int rollAmount) {
		this.rotate((float) yawAmount, (float) pitchAmount, (float) rollAmount);
	}

	/**
	 * Rotates the location by the provided floating point amount in each rotational direction
	 * 
	 * @param yawAmount The value by which to increment the location's {@link #yaw}
	 * @param pitchAmount The value by which to increment the location's {@link #pitch}
	 * @param rollAmount The value by which to increment the location's {@link #roll}
	 */
	public void rotate(float yawAmount, float pitchAmount, float rollAmount) {
		yaw += yawAmount;
		pitch += pitchAmount;
		roll += rollAmount;
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

	@Override
	public String toString() {
		return "Position: " + Arrays.toString(coordinates).replace("[", "(").replace("]", ")") + ", yaw: " + yaw +
				", pitch: " + pitch + ", roll: " + roll;
	}

	/**
	 * @return The pixel coordinates of the location, wrapped as a Vector3f object (a 3-D vector)
	 */
	public Vector3f getCoordinatesAsVector() {
		return new Vector3f(coordinates[0], coordinates[1], coordinates[2]);
	}

	/**
	 * Sets the {@link Location#coordinates} to the values specified in the provided Vector3f object (a 3-D vector)
	 * 
	 * @param coordinateVector A Vector3f object
	 */
	public void setCoordinates(Vector3f coordinateVector) {
		setCoordinates(coordinateVector.x, coordinateVector.y, coordinateVector.z);
	}

	/**
	 * @return The x-coordinate of the location (the first value in the {@link Location#coordinates} Array)
	 */
	public float getX() {
		return coordinates[0];
	}

	/**
	 * Sets the x-coordinate of the location to the specified value by editing the {@link Location#coordinates} Array
	 * 
	 * @param x A floating point value
	 */
	public void setX(float x) {
		coordinates[0] = x;
	}

	/**
	 * @return The y-coordinate of the location (the second value in the {@link Location#coordinates} Array)
	 */
	public float getY() {
		return coordinates[1];
	}

	/**
	 * Sets the y-coordinate of the location to the specified value by editing the {@link Location#coordinates} Array
	 * 
	 * @param x A floating point value
	 */
	public void setY(float y) {
		coordinates[1] = y;
	}

	/**
	 * @return The z-coordinate of the location (the third value in the {@link Location#coordinates} Array)
	 */
	public float getZ() {
		return coordinates[2];
	}

	/**
	 * Sets the z-coordinate of the location to the specified value by editing the {@link Location#coordinates} Array
	 * 
	 * @param z A floating point value
	 */
	public void setZ(float z) {
		coordinates[2] = z;
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
	 * @return The {@link #roll} of the location
	 */
	public float getRoll() {
		return roll;
	}
	
	/**
	 * Sets the {@link #roll} of the location to the provided value
	 * 
	 * @param roll A floating point value
	 */
	public void setRoll(float roll) {
		this.roll = roll;
	}
}
