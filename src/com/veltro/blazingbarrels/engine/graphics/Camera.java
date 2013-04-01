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
	 * Default version of this method - calls the {@link #handleMouseInput(float)} method, passing the default
	 * rotational speed multiplier for the camera
	 */
	void handleMouseInput();

	/**
	 * Detects input from the mouse and updates the Camera (its position, rotation, etc.) accordingly
	 * 
	 * @param speed The factor by which to scale the mouse speed, which is used to determine rotational speed
	 */
	void handleMouseInput(float speedMultiplier);

	/**
	 * Default version of this method - calls the {@link #handleKeyboardInput(float)} method, passing the default
	 * translational speed for the camera
	 */
	void handleKeyboardInput();

	/**
	 * Detects input from the keyboard and updates the Camera (its position, rotation, etc.) accordingly
	 * 
	 * @param speed The speed, in pixels per second, of the camera's movement
	 */
	void handleKeyboardInput(float speed);

	/**
	 * Renders the game world from the camera's perspective, translating/rotating the gluPerspective based on the
	 * the camera's location (updated by the {@link #updatePosition()} method)
	 */
	void draw();

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
