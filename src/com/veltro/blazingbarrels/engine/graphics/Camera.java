package com.veltro.blazingbarrels.engine.graphics;

import com.veltro.blazingbarrels.game.location.Location;

/**
 * Highest-order Camera interface - specifies basic methods for handling the camera that all implementations of this
 * interface should use, regardless of dimensionality.
 * 
 * @author LinearLogic
 * @since 0.1.6
 */
public interface Camera<T extends Location> {

	/**
	 * Sets the screen's viewing window to that of the camera
	 */
	void useView();

	/**
	 * Detects input from the mouse and updates the Camera (its position, rotation, etc.) accordingly
	 */
	void handleMouseInput();

	/**
	 * Detects input from the keyboard and updates the Camera (its position, rotation, etc.) accordingly
	 */
	void handleKeyboardInput();

	/**
	 * Updates the Camera's position (read: the viewing window) based on the input processed in the
	 * {@link #handleMouseInput()} and {@link #handleKeyboardInput()} methods.
	 */
	void updatePosition();

	/**
	 * @return The location of the camera (a {@link Location} subclass)
	 */
	T getLocation();

	/**
	 * Sets the location of the camera to the provided location
	 * 
	 * @param location A {@link Location} subclass
	 */
	void setLocation(T location);
}
