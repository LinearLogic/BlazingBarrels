package com.veltro.blazingbarrels.game.location;

/**
 * This exception is thrown when attempting to operate on two {@link Location} objects or subclass objects of different
 * dimensions (eg. trying to call the distanceTo(...) method on a 2-D and 3-D location).
 * 
 * @author LinearLogic
 * @since 0.1.2
 */
public class LocationDimensionalityMismatchException extends RuntimeException {

	/**
	 * Ensures serializability
	 */
	private static final long serialVersionUID = -7971187515183244852L;

	/**
	 * Constructs the RuntimeException superclass with the default reason
	 */
	public LocationDimensionalityMismatchException() {
		super("Attempted to compare two Locations of different dimensionality.");
	}

	/**
	 * Constructs the RuntimeException superclass with a custom reason
	 * 
	 * @param reason The custom reason for the exception being thrown
	 */
	public LocationDimensionalityMismatchException(String reason) {
		super(reason);
	}
}
