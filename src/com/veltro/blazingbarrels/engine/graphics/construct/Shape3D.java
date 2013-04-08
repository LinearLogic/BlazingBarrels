package com.veltro.blazingbarrels.engine.graphics.construct;

import org.lwjgl.util.vector.Vector3f;

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
	 * The shape's position and rotation, relative to the center of the {@link Construct3D} to which this shape belongs
	 */
	protected Location3D location;

	/**
	 * The absolute location of the {@link Construct} to which this shape belongs
	 */
	protected Location3D centerLocation;

	/**
	 * A vector containing the position from the shape's {@link #location}, adjusted based on the rotation of the
	 * shape's {@link #centerLocation}
	 */
	protected Vector3f renderingPosition;

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
		if (centerLocation != null && relativeLocation != null)
			updateRenderingPosition();
	}

	/**
	 * This method, as implemented in Shape3D subclasses, renders the shape
	 */
	public abstract void draw();

	/**
	 * Updates the shape's {@link #renderingPosition} based on the rotation component of the {@link #centerLocation}
	 */
	public void updateRenderingPosition() {
		if (renderingPosition == null)
			renderingPosition = new Vector3f(location.getX(), location.getY(), location.getZ());
		Vector3f relativePos = new Vector3f(location.getX(), location.getZ(),
				location.getY()); // Flip z and y coordinates for vector operations
//
		// Convert angles from degrees to radians:
		float yaw = (float) (centerLocation.getYaw() * Math.PI / 180.0);
		float pitch = (float) (centerLocation.getPitch() * Math.PI / 180.0);
		float roll = (float) (centerLocation.getRoll() * Math.PI / 180.0);

		// Apply yaw to relative position:
		relativePos = rotatePointAroundCustomAxis(relativePos, new Vector3f(0, 0, 1), yaw);

//		// Apply pitch to relative position by first determining the axis of rotation (will lie in the xz-plane), and
//		// then rotating about it in the same manner as above
		relativePos = rotatePointAroundCustomAxis(relativePos, new Vector3f((float) Math.cos(yaw),
				(float) Math.sin(yaw), 0), pitch);
//
//		// Apply roll to relative position by first determining the axis of rotation (based on yaw and pitch of
//		// centerLocation) and then rotating about it in the same manner as above
		relativePos = rotatePointAroundCustomAxis(relativePos, new Vector3f((float) (-Math.sin(yaw) * Math.cos(pitch)),
				(float) (Math.cos(yaw) * Math.cos(pitch)), (float) Math.sin(pitch)), roll);
	
		// Flip values
		relativePos.setX(relativePos.getX());
		float y = relativePos.getY();
		relativePos.setY(relativePos.getZ());
		relativePos.setZ(-y);
		renderingPosition = relativePos;
	}

	/**
	 * Rotates a point around a given axis unit vector using the following equation:<p>
	 * <b>new P = (cos(d))P + (1 - cos(d))(P * A)A + sin(d)(A x P)</b>,<br>
	 * where P is the point vector, A is the axis vector, d is the angle of rotation, (A * P) is the dot product, and
	 * (A x P) is the cross product
	 * 
	 * @param point A Vector3f representation of the coordinates of the point
	 * @param axis A unit vector representing the direction of the axis of rotation
	 * @param degrees The angle of rotation to be executed, <b> in radians</b>
	 * @return The updated point as a Vector3f
	 */
	public Vector3f rotatePointAroundCustomAxis(Vector3f point, Vector3f axis, float radians) {
		Vector3f dot = new Vector3f(axis.getX(), axis.getY(), axis.getZ()), scale = new Vector3f (point.getX(),
				point.getY(), point.getZ());

		dot.scale((float) (Vector3f.dot(point, axis) * (1.0 - Math.cos(radians))));

		scale.scale((float) Math.cos(radians));

		return Vector3f.add(Vector3f.add(dot, scale, null), (Vector3f) Vector3f.cross(axis, point, null).scale((float)
				Math.sin(radians)), null);
	}

	/**
	 * Updates the translational coordinates of the shape's {@link #location}
	 * 
	 * @param dx The amount, in pixels, by which to increment the shape's x-coordinate
	 * @param dy The amount, in pixels, by which to increment the shape's y-coordinate
	 * @param dz The amount, in pixels, by which to increment the shape's z-coordinate
	 */
	public void translate(float dx, float dy, float dz) {
		location.translate(dx, dy, dz);
		updateRenderingPosition();
		
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
		updateRenderingPosition();
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
	 * Sets the rotational coordinates of the shape's {@link #location}
	 * 
	 * @param yaw The new yaw, in degrees
	 * @param pitch The new pitch, in degrees
	 * @param roll The new roll, in degrees
	 */
	public void setRotation(float yaw, float pitch, float roll) {
		location.setRotation(yaw, pitch, roll);
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
	 * Updates the translational coordinates of the shape's {@link #centerLocation}
	 * 
	 * @param dx The amount, in pixels, by which to increment the center's x-coordinate
	 * @param dy The amount, in pixels, by which to increment the center's y-coordinate
	 * @param dz The amount, in pixels, by which to increment the center's z-coordinate
	 */
	public void translateCenter(float dx, float dy, float dz) {
		centerLocation.translate(dx, dy, dz);
	}

	/**
	 * Sets the translational coordinates of the shape's {@link #centerLocation}
	 * 
	 * @param x The new x-coordinate, in pixels
	 * @param y The new y-coordinate, in pixels
	 * @param z The new z-coordinate, in pixels
	 */
	public void setCenterPosition(float x, float y, float z) {
		centerLocation.setCoordinates(x, y, z);
	}

	/**
	 * Updates the rotational coordinates of the shape's {@link #centerLocation} and calls the
	 * {@link #updateRenderingPosition()} method
	 * 
	 * @param dYaw The change in yaw, in degrees
	 * @param dPitch The change in pitch, in degrees
	 * @param dRoll The change in roll, in degrees
	 */
	public void rotateCenter(float dYaw, float dPitch, float dRoll) {
		centerLocation.rotate(dYaw, dPitch, dRoll);
		updateRenderingPosition();
	}

	/**
	 * Sets the rotational coordinates of the shape's {@link #centerLocation} and calls the
	 * {@link #updateRenderingPosition()} method
	 * 
	 * @param yaw The new yaw, in degrees
	 * @param pitch The new pitch, in degrees
	 * @param roll The new roll, in degrees
	 */
	public void setCenterRotation(float yaw, float pitch, float roll) {
		centerLocation.setRotation(yaw, pitch, roll);
		updateRenderingPosition();
	}

	/**
	 * @return The shape's {@link #centerLocation}
	 */
	public Location3D getCenterLocation() {
		return centerLocation;
	}

	/**
	 * Sets the shape's {@link #centerLocation}
	 * 
	 * @param location A {@link Location3D} object, as this is a 3D shape
	 */
	public void setCenterLocation(Location3D location) {
		centerLocation = location;
		updateRenderingPosition();
	}

	/**
	 * @return The distance, in pixels, from the center of the shape to the center of the {@link Construct3D} to which
	 * the shape belongs
	 */
	public float getDistance() {
		return (float) Math.sqrt(Math.pow(location.getX(), 2) +  Math.pow(location.getY(), 2) + Math.pow(location.getZ(), 2));
	}

	/**
	 * Sets the color (and transparency) components of the surface of the shape
	 * 
	 * @param r The new {@link #r red intensity}
	 * @param g The new {@link #g green intensity}
	 * @param b The new {@link #b blue intensity}
	 * @param transparency The new {@link #transparency} level
	 */
	public void setColor(float r, float g, float b, float transparency) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.transparency = transparency;
	}
}
