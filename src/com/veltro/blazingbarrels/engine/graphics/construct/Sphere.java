package com.veltro.blazingbarrels.engine.graphics.construct;

import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * The sphere is a fairly straight forward {@link Shape3D} subclass. Its appearance is defined by its radius, slices,
 * and stacks. The last two of these variables determine the smoothness of the sphere's surface. The effects of
 * modifying them are difficult to describe, but setting one to a very low value (such as 3) produces very interesting
 * results.
 * 
 * @author LinearLogic
 * @since 0.5.2
 */
public class Sphere extends Shape3D {

	/**
	 * The radius, in pixels, of the sphere
	 */
	private float radius;

	/**
	 * The number of slices used to render the sphere (along with {@link #stacks}, this determines the smoothness of
	 * the sphere's surface)
	 */
	private int slices;

	/**
	 * The number of stacks used to render the sphere (along with {@link #slices}, this determines the smoothness of
	 * the sphere's surface)
	 */
	private int stacks;

	/**
	 * Shortcut constructor for smooth spheres - calls the full constructor passing the slices and stacks values as 32
	 * 
	 * @param radius The radius, in pixels, of the sphere
	 * @param centerLocation The location of the {@link Construct3D} to which this sphere belongs
	 * @param relativeLocation The sphere's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the sphere's color (a float between 0 and 1)
	 * @param g The green-intensity of the sphere's color (a float between 0 and 1)
	 * @param b The blue-intensity of the sphere's color (a float between 0 and 1)
	 * @param transparency The transparency level of the sphere's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Sphere(float radius, Location3D centerLocation, Location3D relativeLocation, float r, float g, float b,
			float transparency) {
		this(radius, 32, 32, centerLocation, relativeLocation, r, g, b, transparency);
	}

	/**
	 * Complete constructor - initializes each of the sphere's dimension specifications and constructs the
	 * {@link Shape3D shape superclass}
	 * 
	 * @param radius The radius, in pixels, of the sphere
	 * @param slices The number of slices to use to render the sphere
	 * @param stacks The number of stacks to use to render the sphere
	 * @param centerLocation The location of the {@link Construct3D} to which this sphere belongs
	 * @param relativeLocation The sphere's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the sphere's color (a float between 0 and 1)
	 * @param g The green-intensity of the sphere's color (a float between 0 and 1)
	 * @param b The blue-intensity of the sphere's color (a float between 0 and 1)
	 * @param transparency The transparency level of the sphere's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Sphere(float radius, int slices, int stacks, Location3D centerLocation, Location3D relativeLocation,
			float r, float g, float b, float transparency) {
		super(centerLocation, relativeLocation, r, g, b, transparency);
		this.radius = radius;
		this.slices = slices;
		this.stacks = stacks;
	}

	/**
	 * Renders the sphere by calling the renderColoredSphere(...) method in {@link RenderBot3D}
	 */
	public void draw() {
		RenderBot3D.renderColoredSphere(radius, slices, stacks, new Location3D(location.getX() + centerLocation.getX(),
				location.getY() + centerLocation.getY(), location.getZ() + centerLocation.getZ(), location.getYaw(),
				location.getPitch(), location.getRoll()), r, g, b, transparency);
	}

	/**
	 * @return The sphere's {@link #radius}
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * Sets the sphere's {@link #radius}
	 * 
	 * @param radius The radius in pixels
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * @return The {@link #slices} value
	 */
	public int getSlices() {
		return slices;
	}

	/**
	 * Sets the {@link #slices} value
	 * 
	 * @param slices
	 */
	public void setSlices(int slices) {
		this.slices = slices;
	}

	/**
	 * @return The {@link #stacks} value
	 */
	public int getStacks() {
		return stacks;
	}

	/**
	 * Sets the {@link #stacks} value
	 * 
	 * @param stacks
	 */
	public void setStacks(int stacks) {
		this.stacks = stacks;
	}
}
