package com.veltro.blazingbarrels.engine.graphics.construct;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * A Construct3D is a complex three-dimensional object composed of various {@link Shape3D shapes} and other constructs
 * When a construct is translated or rotated, its subparts are moved accordingly so that the construct retains its
 * structural orientation. Certain constructs are used quite often, such as the minigun or ship, so they are stored
 * statically within this class for quick retrieval.
 * 
 * @author LinearLogic
 * @since 0.5.4
 */
public class Construct3D {

	/**
	 * The position and rotation of the center of the construct, passed to each of the construct's subparts.
	 */
	private Location3D location;

	/**
	 * The position and rotation of the center of the construct to which this construct belongs. If this construct does
	 * not have any parents, this location is null.
	 */
	private Location3D centerLocation;

	/**
	 * A vector containing the position from the construct's {@link #location}, adjusted based on the rotation of the
	 * construct's {@link #centerLocation}
	 */
	private Vector3f renderingPosition;

	/**
	 * A list of the {@link Shape3D} subclass objects that belong to this construct
	 */
	private ArrayList<Shape3D> shapes;

	/**
	 * A list of the other constructs that belong to this construct. The chain of constructs can be endless: one
	 * construct may belong to another, which in turn may belong to another. A good example of a nested construct is
	 * the minigun construct within the player ship construct.
	 */
	private ArrayList<Construct3D> constructs;

	/**
	 * Constructor for absolute constructs (those that do not belong to other constructs)
	 * 
	 * @param location The construct's {@link #location}
	 */
	public Construct3D(Location3D location) {
		this(location, null);
	}

	/**
	 * Constructor for subconstructs (those that belong to other constructs)
	 * 
	 * @param relativeLocation The construct's {@link #location}
	 * @param centerLocation The construct's {@link #centerLocation}
	 */
	public Construct3D(Location3D relativeLocation, Location3D centerLocation) {
		location = relativeLocation;
		this.centerLocation = centerLocation;
		shapes = new ArrayList<Shape3D>();
		constructs = new ArrayList<Construct3D>();
		if (centerLocation != null && relativeLocation != null)
			updateRenderingPosition();
	}

	/**
	 * Renders the construct and all its shapes and subconstructs, assuming that this construct is not a part of any
	 * other and that its location is thus absolute
	 */
	public void draw() {
		for (Shape3D s : shapes)
			s.draw();
		for (Construct3D c : constructs)
			c.draw();
	}

	/**
	 * Updates the construct's {@link #renderingPosition} based on the rotation component of the {@link #centerLocation}
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
	 * Updates the centerLocation objects
	 */
	private void updateChildren() {
		if (centerLocation == null) {
			for (Shape3D s : shapes) {
				s.setCenterPosition(location.getX(), location.getY(), location.getZ());
				s.setCenterRotation(centerLocation.getYaw() + location.getYaw(), centerLocation.getPitch() + location.getPitch(), centerLocation.getRoll() + location.getRoll());
			}
			for (Construct3D c : constructs) {
				c.setCenterPosition(location.getX(), location.getY(), location.getZ());
				c.setCenterRotation(centerLocation.getYaw() + location.getYaw(), centerLocation.getPitch() + location.getPitch(), centerLocation.getRoll() + location.getRoll());
			}
			return;
		}
		updateRenderingPosition();
		for (Shape3D s : shapes) {
			s.setCenterPosition(centerLocation.getX() + renderingPosition.getX(), centerLocation.getY() +
					renderingPosition.getY(), centerLocation.getZ() + renderingPosition.getZ());
			s.setCenterRotation(centerLocation.getYaw() + location.getYaw(), centerLocation.getPitch() + location.getPitch(), centerLocation.getRoll() + location.getRoll());
		}
		for (Construct3D c : constructs) {
			c.setCenterPosition(centerLocation.getX() + renderingPosition.getX(), centerLocation.getY() +
					renderingPosition.getY(), centerLocation.getZ() + renderingPosition.getZ());
			c.setCenterRotation(centerLocation.getYaw() + location.getYaw(), centerLocation.getPitch() + location.getPitch(), centerLocation.getRoll() + location.getRoll());
		}
	}

	/**
	 * Updates the translational coordinates of the construct's {@link #location}
	 * 
	 * @param dx The amount, in pixels, by which to increment the construct's x-coordinate
	 * @param dy The amount, in pixels, by which to increment the construct's y-coordinate
	 * @param dz The amount, in pixels, by which to increment the construct's z-coordinate
	 */
	public void translate(float dx, float dy, float dz) {
		location.translate(dx, dy, dz);
		if (centerLocation == null) {
			for (Shape3D s : shapes)
				s.translateCenter(dx, dy, dz);
			for (Construct3D c : constructs)
				c.translateCenter(dx, dy, dz);
			return;
		}
		if (centerLocation != null) { // Construct is subordinate
			updateRenderingPosition();
			for (Shape3D s : shapes)
				s.setCenterPosition(centerLocation.getX() + renderingPosition.getX(), centerLocation.getY() +
						renderingPosition.getY(), centerLocation.getZ() + renderingPosition.getZ());
			for (Construct3D c : constructs)
				c.setCenterPosition(centerLocation.getX() + renderingPosition.getX(), centerLocation.getY() +
						renderingPosition.getY(), centerLocation.getZ() + renderingPosition.getZ());
		}
	}

	/**
	 * Sets the translational coordinates of the construct's {@link #location}
	 * 
	 * @param x The new x-coordinate, in pixels
	 * @param y The new y-coordinate, in pixels
	 * @param z The new z-coordinate, in pixels
	 */
	public void setPosition(float x, float y, float z) {
		location.setCoordinates(x, y, z);
		updateChildren();
	}

	/**
	 * Updates the rotational coordinates of the construct's {@link #location} and updates the rotation and relative
	 * position of each shape and subconstruct the construct contains
	 * 
	 * @param dYaw The change in yaw, in degrees
	 * @param dPitch The change in pitch, in degrees
	 * @param dRoll The change in roll, in degrees
	 */
	public void rotate(float dYaw, float dPitch, float dRoll) {
		location.rotate(dYaw, dPitch, dRoll);
		for (Shape3D s : shapes)
			s.rotateCenter(dYaw, dPitch, dRoll);
		for (Construct3D c : constructs)
			c.rotateCenter(dYaw, dPitch, dRoll);
	}

	/**
	 * Sets the rotational coordinates of the construct's {@link #location}
	 * 
	 * @param yaw The new yaw, in degrees
	 * @param pitch The new pitch, in degrees
	 * @param roll The new roll, in degrees
	 */
	public void setRotation(float yaw, float pitch, float roll) {
		location.setRotation(yaw, pitch, roll);
		for (Shape3D s : shapes)
			s.setCenterRotation(yaw, pitch, roll);
	}

	/**
	 * @return The construct's {@link #location}
	 */
	public Location3D getLocation() {
		return location;
	}

	/**
	 * Sets the construct's {@link #location}
	 * 
	 * @param centerLocation A {@link Location3D} object, as this is a 3D construct
	 */
	public void setLocation(Location3D location) {
		this.location = location;
	}

	/**
	 * Updates the translational coordinates of the construct's {@link #centerLocation}
	 * 
	 * @param dx The amount, in pixels, by which to increment the construct's x-coordinate
	 * @param dy The amount, in pixels, by which to increment the construct's y-coordinate
	 * @param dz The amount, in pixels, by which to increment the construct's z-coordinate
	 */
	public void translateCenter(float dx, float dy, float dz) {
		centerLocation.translate(dx, dy, dz);
		for (Shape3D s : shapes)
			s.translateCenter(dx, dy, dz);
		for (Construct3D c : constructs)
			c.translateCenter(dx, dy, dz);
	}

	/**
	 * Sets the translational coordinates of the construct's {@link #centerLocation}
	 * 
	 * @param x The new x-coordinate, in pixels
	 * @param y The new y-coordinate, in pixels
	 * @param z The new z-coordinate, in pixels
	 */
	public void setCenterPosition(float x, float y, float z) {
		centerLocation.setCoordinates(x, y, z);
		updateChildren();
	}

	/**
	 * Updates the rotational coordinates of the construct's {@link #centerLocation}
	 * 
	 * @param dYaw The change in yaw, in degrees
	 * @param dPitch The change in pitch, in degrees
	 * @param dRoll The change in roll, in degrees
	 */
	public void rotateCenter(float dYaw, float dPitch, float dRoll) {
		centerLocation.rotate(dYaw, dPitch, dRoll);
		updateChildren();
	}

	/**
	 * Sets the rotational coordinates of the construct's {@link #centerLocation}
	 * 
	 * @param yaw The new yaw, in degrees
	 * @param pitch The new pitch, in degrees
	 * @param roll The new roll, in degrees
	 */
	public void setCenterRotation(float yaw, float pitch, float roll) {
		centerLocation.setRotation(yaw, pitch, roll);
		updateChildren();
	}

	/**
	 * @return The construct's {@link #centerLocation}
	 */
	public Location3D getCenterLocation() {
		return centerLocation;
	}

	/**
	 * Sets the construct's {@link #centerLocation}
	 * 
	 * @param centerLocation A {@link Location3D} object, as this is a 3D construct
	 */
	public void setCenterLocation(Location3D location) {
		centerLocation = location;
		updateChildren();
	}

	/**
	 * @return An Array of the shapes contained within this construct
	 */
	public Shape3D[] getShapes() {
		return (Shape3D[]) shapes.toArray();
	}

	/**
	 * @param index
	 * @return The shape at the provided index in the {@link #shapes list of shapes}
	 */
	public Shape3D getShape(int index) {
		return shapes.get(index);
	}

	/**
	 * Adds the specified shape to the end of the {@link #shapes list of shapes}<p>
	 * Note: the shape's centerLocation value can be null, as it will be set to the construct's {@link #location}
	 * before being added to the {@link #shapes list of shapes}
	 * 
	 * @param shape
	 */
	public void addShape(Shape3D shape) {
		shape.setCenterLocation(location.clone());
		shapes.add(shape);
	}

	/**
	 * Puts the specified shape in the {@link #shapes list of shapes} at the provided index
	 * 
	 * @param index
	 * @param shape
	 */
	public void addShape(int index, Shape3D shape) {
		shape.setCenterLocation(location.clone());
		shapes.add(index, shape);
	}

	/**
	 * Removes the shape at the provided index in the {@link #shapes list of shapes}
	 * 
	 * @param index
	 */
	public void removeShape(int index) {
		shapes.remove(index);
	}

	/**
	 * Empties the {@link #shapes list of shapes}
	 */
	public void clearShapes() {
		shapes.clear();
	}

	/**
	 * @return An Array of the constructs contained within this construct
	 */
	public Construct3D[] getConstructs() {
		return (Construct3D[]) constructs.toArray();
	}

	/**
	 * @param index
	 * @return The construct at the provided index in the {@link #constructs list of constructs}
	 */
	public Construct3D getConstruct(int index) {
		return constructs.get(index);
	}

	/**
	 * Adds the specified construct to the end of the {@link #constructs list of constructs}
	 * 
	 * @param construct
	 */
	public void addConstruct(Construct3D construct) {
		construct.setCenterLocation(location.clone());
		constructs.add(construct);
	}

	/**
	 * Puts the specified construct in the {@link #constructs list of constructs} at the provided index
	 * 
	 * @param index
	 * @param construct
	 */
	public void addConstruct(int index, Construct3D construct) {
		construct.setCenterLocation(location.clone());
		constructs.add(index, construct);
	}

	/**
	 * Removes the construct at the provided index in the {@link #constructs list of constructs}
	 * 
	 * @param index
	 */
	public void removeConstruct(int index) {
		constructs.remove(index);
	}

	/**
	 * Empties the {@link #constructs list of constructs}
	 */
	public void clearConstructs() {
		constructs.clear();
	}

	public static Construct3D MINIGUN() {
		// MAKE ZE MINIGUN!!
		Construct3D minigun = new Construct3D(new Location3D());

		// Barrels:
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(1.5f, 0, 0), 0.1f, 0.1f, 0.1f, 1));
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(0.75f, 1.29903f, 0), 0.1f, 0.1f, 0.1f, 1));
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(-0.75f, 1.29903f, 0), 0.1f, 0.1f, 0.1f, 1));
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(-1.5f, 0, 0), 0.1f, 0.1f, 0.1f, 1));
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(-0.75f, -1.29903f, 0), 0.1f, 0.1f, 0.1f, 1));
		minigun.addShape(new Tube(0.45f, 0.3f, 0.45f, 0.3f, 30, 32, true, null, new Location3D(0.75f, -1.29903f, 0), 0.1f, 0.1f, 0.1f, 1));

		// Barrel bindings:
		minigun.addShape(new Tube(2.4f, 6, true, null, new Location3D(0, 0, -0.1f), 0.2f, 0.2f, 0.2f, 1));
		minigun.addShape(new Tube(2.4f, 1.2f, true, null, new Location3D(0, 0, 6.9f), 0.2f, 0.2f, 0.2f, 1));
		minigun.addShape(new Tube(2.4f, 1.2f, true, null, new Location3D(0, 0, 27.3f), 0.2f, 0.2f, 0.2f, 1));

		return minigun;
	}

	public static Construct3D PLAYER_SHIP() {
		return PLAYER_SHIP(1, 1, 1);
	}

	public static Construct3D PLAYER_SHIP(float r, float g, float b) {
		Construct3D ship = new Construct3D(new Location3D());
		ship.addShape(new Sphere(5, 5, 5, null, new Location3D(0, 0, 67.9f), r, g, b, 1));
		ship.addShape(new Sphere(8, 5, 5, null, new Location3D(0, 0, 63f), 0, 0, 0, 1));
		ship.addShape(new Sphere(13, 5, 5, null, new Location3D(0, 0, 55f), r, g, b, 1));
		ship.addShape(new Sphere(12, 32, 7, null, new Location3D(0, 0, 31.5f), r, g, b, 1));
		ship.addShape(new Tube(10, 33.5f, true, null, new Location3D(0, 0, 19.5f), 1, 1, 1, 1));

		// Turret: Minigun
		ship.addShape(new Tube(5, 4, 5, 4, 1, 32, true, null, new Location3D(11, 0, 34.5f), 0, 0, 0, 1));
		ship.addShape(new Tube(5, 4, 5, 4, 1, 32, true, null, new Location3D(11, 0, 27.5f), 0, 0, 0, 1));
		Construct3D minigun = Construct3D.MINIGUN();
		minigun.setLocation(new Location3D(16, 0, 27.5f));
		ship.addConstruct(minigun);

		// Engine
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(6, 0, 17), 0, 1, 1, 1));
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(3, 5.1961524f, 17), 0, 1, 1, 1));
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(3, -5.1961524f, 17), 0, 1, 1, 1));
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(-6, 0, 17), 0, 1, 1, 1));
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(-3, -5.1961524f, 17), 0, 1, 1, 1));
		ship.addShape(new Sphere(3, 32, 3, null, new Location3D(-3, 5.1961524f, 17), 0, 1, 1, 1));
		ship.addShape(new Tube(10, 4.5f, true, null, new Location3D(0, 0, 10), 1, 1, 1, 1));

		// Tail		
		ship.addShape(new Tube(15, 13.5f, 10, 9, 10, 48, true, null, new Location3D(), r, g, b, 1));
		ship.addShape(new Washer(10.8f, 0, 32, null, new Location3D(0, 0, 5.95f), 0.8f, 0.8f, 0.8f, 1));

		// Jet housings
		ship.addShape(new Tube(4, 3, 4, 3, 2, 32, true, null, new Location3D(4.5f, 4.5f, 4), 0.7f, 0.7f, 0.7f, 1));
		ship.addShape(new Tube(4, 3, 4, 3, 2, 32, true, null, new Location3D(-4.5f, 4.5f, 4), 0.7f, 0.7f, 0.7f, 1));
		ship.addShape(new Tube(4, 3, 4, 3, 2, 32, true, null, new Location3D(-4.5f, -4.5f, 4), 0.7f, 0.7f, 0.7f, 1));
		ship.addShape(new Tube(4, 3, 4, 3, 2, 32, true, null, new Location3D(4.5f, -4.5f, 4), 0.7f, 0.7f, 0.7f, 1));

		// Jet flames
		ship.addShape(new Tube(0, 0, 3, 0, 1, 32, true, null, new Location3D(4.5f, 4.5f, 5), 1, 0, 0, 1));
		ship.addShape(new Tube(0, 0, 3, 0, 1, 32, true, null, new Location3D(-4.5f, 4.5f, 5), 1, 0, 0, 1));
		ship.addShape(new Tube(0, 0, 3, 0, 1, 32, true, null, new Location3D(-4.5f, -4.5f, 5), 1, 0, 0, 1));
		ship.addShape(new Tube(0, 0, 3, 0, 1, 32, true, null, new Location3D(4.5f, -4.5f, 5), 1, 0, 0, 1));

		// Quarantined zone for all the transparent entities
		ship.addShape(new Tube(10, 5, true, null, new Location3D(0, 0, 14.5f), 0.3f, 0.3f, 0.6f, 0.5f));

		return ship;
	}
}