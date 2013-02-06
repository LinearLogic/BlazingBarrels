package com.veltro.blazingbarrels.game.location;

import java.util.Arrays;

/**
 * Abstract superclass for in-game locations
 * TODO: add conversion from in-game meters (Location unit) to pixels
 * 
 * @author LinearLogic
 * @since 0.1.1
 */
public abstract class Location {

	/**
	 * The dimensionality of the location (2 or 3 for all applications within BlazingBarrels)
	 */
	private final int dimension;

	/**
	 * The coordinate components (eg. x,y or x,y,z) of the location. These values are in in-game meters, not pixels
	 */
	private float[] coordinates;

	/**
	 * Constructor - determines the {@link #dimension dimensionality} of the location based on the number of coordinate
	 * components provided in the method call.
	 * 
	 * @param coordinates The location's {@link #coordinates}
	 */
	public Location(float... coordinates) {
		this.coordinates = coordinates;
		this.dimension = coordinates.length;
	}

	/**
	 * Calculates the distance between two locations (which must be subclasses of this class) using a Pythagorean-esque
	 * setup. While this method performs a check to ensure that the dimensions of the provided locations match, this
	 * method is overloaded in the Location subclasses to enforce uniform dimensionality.
	 * 
	 * @param anotherLocation The second location in the distance equation (along with the location running the method)
	 * @return The distance, a float value, between the two locations
	 */
	protected <T extends Location> float distanceTo(T anotherLocation) {
		if (dimension != anotherLocation.dimension)
			throw new LocationDimensionalityMismatchException();
		if (dimension < 1) // This should never happen...
			return 0;
		double sum = 0;
		for (int i = 0; i < dimension; i++)
			sum += Math.pow(anotherLocation.coordinates[i] - coordinates[i], 2);
		return (float) Math.sqrt(sum);
	}

	/**
	 * Method for converting the location
	 */
	@Override
	public String toString() {
		return Arrays.toString(coordinates).replace("[", "(").replace("]", ")");
	}

	/**
	 * @return The {@link #coordinates} of the location (in in-game meters, not pixels)
	 */
	public float[] getCoordinates() {
		return coordinates;
	}


	/**
	 * Sets the coordinates of the location to the provided float Array, provided that the two arrays' dimensions match
	 * 
	 * @param coordinates The coordinate components with which to replace the old {@link #coordinates}
	 */
	public void setCoordinates(float... coordinates) {
		if (coordinates.length != dimension)
			throw new IllegalArgumentException("The number of coordinates provided does not match the dimensionality of the Location");
		this.coordinates = coordinates;
	}

	/**
	 * @return The {@link #dimension dimensionality} of the location
	 */
	public int getDimension() {
		return dimension;
	}
}
