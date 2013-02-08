package com.veltro.blazingbarrels.engine.graphics;

import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * A {@link Camera} implementation used for viewing three-dimensional space. The camera's position/rotation is managed
 * using a {@link Location3D} object. 
 * 
 * <p>The majority of its settings (eg. {@link #fov} and {@link #aspectRatio}) can be
 * altered at any point, but the {@link #zNear} and {@link #zFar} values are final, instantiated in the constructor.
 * 
 * @author LinearLogic
 * @since 0.1.9
 */
public class Camera3D implements Camera<Location3D> {

	/**
	 * The angle of the camera's field of vision
	 */
	private float fov;

	/**
	 * The aspect ratio of the camera's display window
	 */
	private float aspectRatio;

	/**
	 * The distance (in pixels) from the camera's location of the near clipping plane
	 */
	private final float zNear;

	/**
	 * The distance (in pixels) from the camera's location of the far clipping plane
	 */
	private final float zFar;

	/**
	 * The camera's three-dimensional location (a {@link Location3D} object containing the camera's position and
	 * rotation)
	 */
	private Location3D location;

	/**
	 * Simplest constructor - passes default values for fov, aspectRatio, zNear, zFar, and location to the
	 * {@link #Camera3D(float, float, float, float, Location3D) complete constructor}
	 */
	public Camera3D() {
		this(90, 1, 0.3f, 200, new Location3D());
	}

	/**
	 * Simplified constructor - passes default fov and aspectRatio values and a default location along with the
	 * provided values to the {@link #Camera3D(float, float, float, float, Location3D) complete constructor}
	 * 
	 * @param zNear The distance of the near clipping plane
	 * @param zFar The distance of the far clipping plane
	 */
	public Camera3D(float zNear, float zFar) {
		this(90, 1, zNear, zFar, new Location3D());
	}

	/**
	 * Simplified constructor - passes a default location along with the provided values to the
	 * {@link #Camera3D(float, float, float, float, Location3D) complete constructor}
	 * 
	 * @param fov The camera's {@link #fov field of vision} angle
	 * @param aspectRatio The camera's {@link #aspectRatio}
	 * @param zNear The distance of the near clipping plane
	 * @param zFar The distance of the far clipping plane
	 */
	public Camera3D(float fov, float aspectRatio, float zNear, float zFar) {
		this(fov, aspectRatio, zNear, zFar, new Location3D());
	}

	/**
	 * Complete constructor
	 * 
	 * @param fov The camera's {@link #fov field of vision} angle
	 * @param aspectRatio The camera's {@link #aspectRatio}
	 * @param zNear The distance of the near clipping plane
	 * @param zFar The distance of the far clipping plane
	 * @param location The 3-D pixel {@link #location} of the camera
	 */
	public Camera3D(float fov, float aspectRatio, float zNear, float zFar, Location3D location) {
		this.fov = fov;
		this.aspectRatio = aspectRatio;
		this.zNear = zNear;
		this.zFar = zFar;
		this.location = location;
	}

	public void useView() {
		// TODO
	}

	public void handleMouseInput() {
		// TODO
	}

	public void handleKeyboardInput() {
		// TODO
	}

	public void updatePosition() {
		// TODO
	}

	/**
	 * @return The camera's {@link #fov} angle
	 */
	public float getFOV() {
		return fov;
	}

	/**
	 * Sets the camera's {@link #fov} angle to the specified value
	 * 
	 * @param fov The degrees of the angle, a floating point value
	 */
	public void setFOV(float fov) {
		this.fov = fov;
	}

	/**
	 * @return The camera's {@link #aspectRatio}
	 */
	public float getAspectRatio() {
		return aspectRatio;
	}

	/**
	 * Sets the camera's {@link #aspectRatio} to the provided value
	 * 
	 * @param aspectRatio The aspect ratio fraction as a floating point value
	 */
	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	/**
	 * @return The camera's {@link #zNear} distance
	 */
	public float getZNear() {
		return zNear;
	}

	/**
	 * @return The camera's {@link #zFar} distance
	 */
	public float getZFar() {
		return zFar;
	}

	public Location3D getLocation() {
		return location;
	}

	public void setLocation(Location3D location) {
		this.location = location;
	}
}
