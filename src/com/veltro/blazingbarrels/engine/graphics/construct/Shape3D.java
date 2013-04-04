package com.veltro.blazingbarrels.engine.graphics.construct;

import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * The Shape3D class is the superclass for all three-dimensional shapes, such as spheres and cylinders. Each shape has
 * a {@link Location3D} relative to the center of the {@link Construct3D} to which it belongs, which is used when
 * rendering the shape.
 * 
 * @author LinearLogic
 * @since 0.5.0
 */
public abstract class Shape3D {

	/**
	 * The shape's position (relative to the center of the {@link Construct3D} to which this shape belongs) and
	 * rotation (absolute)
	 */
	private Location3D location;

	/**
	 * The absolute location of the {@link Construct} to which this shape belongs
	 */
	protected Location3D centerLocation;

	/**
	 * The red-intensity of the color of the shape's surface (a floating point value between 0 and 1)
	 */
	protected float r;

	/**
	 * The green-intensity of the color of the shape's surface (a floating point value between 0 and 1)
	 */
	protected float g;

	/**
	 * The blue-intensity of the color of the shape's surface (a floating point value between 0 and 1)
	 */
	protected float b;

	/**
	 * The transparency level of the shape's surface (0.0 => completely transparent, 1.0 => completely opaque)
	 */
	protected float transparency;

	/**
	 * Shape superclass constructor - initializes the fields of this class to the provided values
	 * 
	 * @param centerLocation See {@link #centerLocation}
	 * @param relativeLocation See {@link #location}
	 * @param r The red-intensity of the shape's color (a float between 0 and 1)
	 * @param g The green-intensity of the shape's color (a float between 0 and 1)
	 * @param b The blue-intensity of the shape's color (a float between 0 and 1)
	 * @param transparency The transparency of the shape's surface (a float between 0 and 1, 0 being transparent)
	 */
	public Shape3D(Location3D centerLocation, Location3D relativeLocation, float r, float g, float b, float transparency) {
		this.centerLocation = centerLocation;
		location = relativeLocation;
		this.r = r;
		this.g = g;
		this.b = b;
		this.transparency = transparency;
	}

	/**
	 * This method, as implemented in Shape3D subclasses, renders the shape
	 */
	public abstract void draw();

	/**
	 * Updates the translational coordinates of the shape's {@link #location}
	 * 
	 * @param dx The amount, in pixels, by which to increment the shape's x-coordinate
	 * @param dy The amount, in pixels, by which to increment the shape's y-coordinate
	 * @param dz The amount, in pixels, by which to increment the shape's z-coordinate
	 */
	public void translate(float dx, float dy, float dz) {
		location.translate(dx, dy, dz);
	}

	/**
	 * Sets the translational coordinates of the shape's {@link #location}
	 * 
	 * @param x The new x-coordinate, in pixels
	 * @param y The new y-coordinate, in pixels
	 * @param z The new z-coordinate, in pixels
	 */
	public void setPosition(float x, float y, float z) {
		location.setCoordinates(x, y, z);
	}

	/**
	 * Updates the rotational coordinates of the shape's {@link #location}
	 * 
	 * @param dYaw The change in yaw, in degrees
	 * @param dPitch The change in pitch, in degrees
	 * @param dRoll The change in roll, in degrees
	 */
	public void rotate(float dYaw, float dPitch, float dRoll) {
		location.rotate(dYaw, dPitch, dRoll);
	}

	/**
	 * @return The shape's {@link #location}
	 */
	public Location3D getRelativeLocation() {
		return location;
	}

	/**
	 * Sets the shape's {@link #location}
	 * 
	 * @param location A {@link Location3D} object, as this is a 3D shape
	 */
	public void setRelativeLocation(Location3D location) {
		this.location = location;
	}

	/**
	 * @return The distance, in pixels, from the center of the shape to the center of the {@link Construct3D} to which
	 * the shape belongs
	 */
	public float getDistance() {
		return (float) Math.sqrt(Math.pow(location.getX(), 2) +  Math.pow(location.getY(), 2) + Math.pow(location.getZ(), 2));
	}

	public void setColor(float r, float g, float b, float transparency) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.transparency = transparency;
	}
}
