package com.veltro.blazingbarrels.game.location;

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
	 * The x-coordinate (in in-game meters) of the location
	 */
	private float x;

	/**
	 * The y-coordinate (in in-game meters) of the location
	 */
	private float y;

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
	 * Overloading of the {@link Location#distanceTo(Location)} method which provides a guaranteed type (and
	 * thus dimensionality) match.
	 * 
	 * @param anotherLocation The second location in the distance equation (along with the location running the method)
	 * @return The distance, a float value, between the two locations
	 */
	public float distanceTo(Location2D anotherLocation) {
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
}
