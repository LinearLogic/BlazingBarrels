package com.veltro.blazingbarrels.engine.graphics;

import org.lwjgl.util.vector.Vector;

import com.veltro.blazingbarrels.game.location.Location;

/**
 * Highest-order Camera interface - specifies three basic methods for updating the camera
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
	 * @return The position of the camera in terms of pixels (in the game world, not the window)
	 */
	<S extends Vector> S getPixelPosition();

	/**
	 * Sets the camera's position to the provided pixel coordinates (within the game world, not the game window)
	 * 
	 * @param coordinates An Array of floating point values (the number of values depends on the dimensionality of the
	 * camera, which is determined by the dimensionality of the {@link Location} subclass used for the camera
	 */
	void setPixelPosition(float... coordinates);
	
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
