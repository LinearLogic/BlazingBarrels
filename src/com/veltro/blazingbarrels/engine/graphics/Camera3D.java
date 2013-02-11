package com.veltro.blazingbarrels.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.ARBDepthClamp.GL_DEPTH_CLAMP;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;

import com.veltro.blazingbarrels.BlazingBarrels;
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
	 * The change in the camera's x displacement
	 */
	private float dx;

	/**
	 * The change in the camera's y displacement
	 */
	private float dy;

	/**
	 * The change in the camera's z displacement
	 */
	private float dz;

	/**
	 * The change in the camera's angle of yaw
	 */
	private float dYaw;

	/**
	 * The change in the camera's angle of pitch
	 */
	private float dPitch;

	/**
	 * The change in the camera's angle of roll
	 */
	private float dRoll;

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
		if (fov <= 0 || fov >= 120 /* TODO: determine a reasonable fov cap, if this one is not reasonable */)
			throw new IllegalArgumentException("The field of view angle must be between 0 and 120 degrees," +
					"non-inclusive");
		if (aspectRatio <= 0)
			throw new IllegalArgumentException("The aspect ratio value must be greater than zero");
		if (zNear <= 0)
			throw new IllegalArgumentException("The near clipping plane displacement must be greater than zero");
		if (zFar <= zNear)
			throw new IllegalArgumentException("The far clipping plane must be farther from the camera than the near" +
					"clipping plane");
		this.fov = fov;
		this.aspectRatio = aspectRatio;
		this.zNear = zNear;
		this.zFar = zFar;
		this.location = location;
	}

	public void useView() {
		optimize();
		applyPerspectiveMatrix();
	}

	/**
	 * Sets up a perspective projection matrix, renders the game from the camera's view, and reverts the matrix mode
	 */
	private void applyPerspectiveMatrix() {
		glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, aspectRatio, zNear, zFar);
        glPopAttrib();
    }

	/**
     * Toggles GL_DEPTH_CLAMP in order to maximize rendering efficiency
     */
    private void optimize() {
        if (GLContext.getCapabilities().GL_ARB_depth_clamp)
            glEnable(GL_DEPTH_CLAMP);
    }

	public void handleMouseInput() {
		handleMouseInput(1);
	}

	public void handleMouseInput(float speed) {
		// Horizontal rotation:
		dYaw = ((float) Mouse.getDX()) * speed * 0.16f;
		
		// Vertical rotation:
		dPitch = ((float) Mouse.getDY()) * speed * 0.16f;
	}

	public void handleKeyboardInput() {
		this.handleKeyboardInput(200);
	}

	public void handleKeyboardInput(float speed) {
		
		// Side-to-side movement
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			dx += speed * BlazingBarrels.getDelta() / 1000.0;
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			dx -= speed * BlazingBarrels.getDelta() / 1000.0;
		dx *= (float) Math.sin(location.getYaw() * Math.PI / 180.0); // Handle direction in which the camera is looking

		// Forward/backward movement
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			dz += speed * BlazingBarrels.getDelta() / 1000.0;
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			dz -= speed * BlazingBarrels.getDelta() / 1000.0;
		dz *= (float) Math.cos(location.getYaw() * Math.PI / 180.0); // Handle direction in which the camera is looking

		// Vertical movement (not affected by the rotation of the camera's viewing window)
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			dy += speed * BlazingBarrels.getDelta() / 1000.0;
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			dy -= speed * BlazingBarrels.getDelta() / 1000.0;
	}

	public void updatePosition() {
		float pitch = location.getPitch();
		if (pitch + dPitch <= 85) // Maximum upwards angle
			if ((pitch + dPitch) % 360 > 85)
				dPitch = 0;
		else if (pitch + dPitch >= 275) // Maximum downwards angle
			if ((pitch + dPitch) % 360 < 275)
				dPitch = 0;
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glRotatef(dPitch, 1, 0, 0);
        glRotatef(dYaw, 0, 1, 0);
        glRotatef(dRoll, 0, 0, 1);
        glTranslatef(-dx, -dy, -dz);
        glPopAttrib();
    }

	/**
	 * @return The camera's {@link #fov} angle
	 */
	public float getFieldOfVision() {
		return fov;
	}

	/**
	 * Sets the camera's {@link #fov} angle to the specified value
	 * 
	 * @param fov The degrees of the angle, a floating point value
	 */
	public void setFieldOfVision(float fov) {
		if (fov <= 0 || fov >= 120)
			throw new IllegalArgumentException("The field of view angle must be between 0 and 120 degrees," +
					"non-inclusive");
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
		if (aspectRatio <= 0)
			throw new IllegalArgumentException("The aspect ratio value must be greater than zero");
		this.aspectRatio = aspectRatio;
	}
	/**
	 * @return The camera's {@link #zNear} distance
	 */
	public float getNearClippingPlane() {
		return zNear;
	}

	/**
	 * @return The camera's {@link #zFar} distance
	 */
	public float getFarClippingPlane() {
		return zFar;
	}

	public Location3D getLocation() {
		return location;
	}

	public void setLocation(Location3D location) {
		this.location = location;
	}
}
