package com.veltro.blazingbarrels.game.location;

import java.util.Arrays;

/**
 * A two-dimensional location - much simpler than its 3-D counterpart, the 2-D location consists solely of an
 * {@link #x x-coordinate} and a {@link #y y-coordinate} (no rotation).
 * TODO: add conversion from in-game meters (Location unit) to pixels
 * 
 * @author LinearLogic
 * @since 0.1.3
 */
public class Location2D extends Location {

	/**
	 * Constructs the two-dimensional location and the {@link Location} superclass with the provided coordinates
	 * 
	 * @param x The {@link #x x-coordinate} of the location
	 * @param y The {@link #y y-coordinate} of the location
	 */
	public Location2D(float x, float y) {
		super(x, y);
	}

	/**
	 * Shifts the location by the specified integer amounts in each direction (casts the provided ints to floats, which
	 * are in turn passed into the {@link #translate(float x, float y)} method)
	 * 
	 * @param dx x-displacement
	 * @param dy y-displacement
	 */
	public void translate(int dx, int dy) {
		translate((float) dx, (float) dy);
	}

	/**
	 * Shifts the location by the specified floating point amounts in each direction by passing the values to the
	 * {@link Location#translate(float...)} method in the Location superclass
	 * 
	 * @param dx x-displacement
	 * @param dy y-displacement
	 */
	public void translate(float dx, float dy) {
		super.translate(dx, dy);
	}

	/**
	 * Overloading of the {@link Location#distanceTo(Location)} method which provides a guaranteed type (and
	 * thus dimensionality) match.
	 * 
	 * @param anotherLocation The second location in the distance equation (along with the location running the method)
	 * @return The distance, a float value, between the two locations
	 */
	public float distanceTo(Location2D anotherLocation) {
		return super.distanceTo(anotherLocation);
	}

	@Override
	public String toString() {
		return "Position: " + Arrays.toString(coordinates).replace("[", "(").replace("]", ")");
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
}
