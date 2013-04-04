package com.veltro.blazingbarrels.engine.graphics.construct;

import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * A tube (rendered upright by default) is a hollow cylinder that may or may not have endcaps, can have a different
 * radius at each end, and whose lateral surface is comprised of a configurable number of planar faces. As a result of
 * this variety of dimensions, a tube can be used to render a wide spectrum of shapes, such as a cone (if one radius is
 * zero), a pyramid (same as a cone but with fewer lateral faces), an hourglass (if one radius is the negative of the
 * other), and a rectangle (if the radii are the same, the lateral surface consists of four faces, and end caps are
 * included).
 * 
 * @author LinearLogic
 * @since 0.5.1
 */
public class Tube extends Shape3D {

	/**
	 * The radius, in pixels, of the (roughly) circular bottom end of the tube
	 */
	private float bottomRadius;

	/**
	 * The radius, in pixels, of the (roughly) circular top end of the tube
	 */
	private float topRadius;

	/**
	 * The distance, in pixels, between the two ends of the tube
	 */
	private float height;

	/**
	 * The number of planar faces comprising the lateral surface of the tube. (Read: the number of sides the cross-
	 * section of the tube has)
	 */
	private int edges;

	/**
	 * Whether to render the tube with endcaps on its ends (the endcaps will be the shape of the tube's cross section
	 * at either end radius)
	 */
	private boolean endcaps;

	/**
	 * Shortcut constructor for regular cylinders (uniform top and bottom radii, lateral face count of 48)
	 * 
	 * @param radius The tube's radius, in pixels
	 * @param height The tube's {@link #height}
	 * @param endcaps Whether the tube has {@link #endcaps}
	 * @param centerLocation The location of the {@link Construct3D} to which this tube belongs
	 * @param relativeLocation The tube's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the tube's color (a float between 0 and 1)
	 * @param g The green-intensity of the tube's color (a float between 0 and 1)
	 * @param b The blue-intensity of the tube's color (a float between 0 and 1)
	 * @param transparency The transparency level of the tube's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Tube(float radius, float height, boolean endcaps, Location3D centerLocation, Location3D relativeLocation,
			float r, float g, float b, float transparency) {
		this(radius, radius, height, 32, endcaps, centerLocation, relativeLocation, r, g, b, transparency);
	}

	/**
	 * Complete constructor - initializes each of the tube's dimension specifications and constructs the
	 * {@link Shape3D shape superclass}
	 * 
	 * @param bottomRadius The tube's {@link #bottomRadius}
	 * @param topRadius The tube's {@link #topRadius}
	 * @param height The tube's {@link #height}
	 * @param lateralFaceCount See {@link #edges}
	 * @param endcaps Whether the tube has {@link #endcaps}
	 * @param centerLocation The location of the {@link Construct3D} to which this tube belongs
	 * @param relativeLocation The tube's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the tube's color (a float between 0 and 1)
	 * @param g The green-intensity of the tube's color (a float between 0 and 1)
	 * @param b The blue-intensity of the tube's color (a float between 0 and 1)
	 * @param transparency The transparency level of the tube's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Tube(float bottomRadius, float topRadius, float height, int lateralFaceCount, boolean endcaps,
			Location3D centerLocation, Location3D relativeLocation, float r, float g, float b, float transparency) {
		super(centerLocation, relativeLocation, r, g, b, transparency);
		this.bottomRadius = bottomRadius;
		this.topRadius = topRadius;
		this.height = height;
		edges = lateralFaceCount;
		this.endcaps = endcaps;
	}

	/**
	 * Renders the tube by calling the renderColoredCylinder(...) method in {@link RenderBot3D}
	 */
	public void draw() {
		Location3D l = new Location3D(location.getX() + centerLocation.getX(), location.getY() + centerLocation.getY(),
				location.getZ() + centerLocation.getZ(), location.getYaw(), location.getPitch(), location.getRoll());
		RenderBot3D.renderColoredCylinder(bottomRadius, topRadius, height, edges, 5, l, r, g, b, transparency);
		if (endcaps) {
			RenderBot3D.renderColoredDisk(bottomRadius, 0, edges, l, r, g, b, transparency);
			l.translate((float) (height * Math.sin(l.getYaw() * Math.PI / 180.0) * Math.sin(l.getPitch() * Math.PI /
				180.0)), (float) (height * Math.cos(l.getPitch() * Math.PI / 180.0)), (float) (height *
				Math.cos(l.getYaw() * Math.PI / 180.0) * Math.sin(l.getPitch() * Math.PI / 180.0)));
			RenderBot3D.renderColoredDisk(topRadius, 0, edges, l, r, g, b, transparency);
		}
	}

	/**
	 * @return The {@link #bottomRadius} of the tube
	 */
	public float getBottomRadius() {
		return bottomRadius;
	}

	/**
	 * Sets the {@link #bottomRadius} of the tube to the provided float value
	 * 
	 * @param bottomRadius
	 */
	public void setBottomRadius(float bottomRadius) {
		this.bottomRadius = bottomRadius;
	}

	/**
	 * @return The {@link #topRadius} of the tube
	 */
	public float getTopRadius() {
		return topRadius;
	}

	/**
	 * Sets the {@link #topRadius} of the tube to the provided float value
	 * 
	 * @param topRadius
	 */
	public void setTopRadius(float topRadius) {
		this.topRadius = topRadius;
	}

	/**
	 * @return The {@link #height} of the tube
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Sets the {@link #height} of the tube
	 * 
	 * @param height
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return The tube's {@link #edges lateral face count}
	 */
	public int getEdges() {
		return edges;
	}

	/**
	 * Sets the tube's {@link #edges lateral face count}
	 * 
	 * @param edges Setting this number above 64 is not recommended, as it taxes the graphics processor unnecessarily
	 */
	public void setEdges(int edges) {
		this.edges = edges;
	}

	/**
	 * @return Whether the tube is rendered with endcaps
	 */
	public boolean hasEndcaps() {
		return endcaps;
	}

	/**
	 * Sets whether to render the tube with endcaps
	 * 
	 * @param endcaps
	 */
	public void useEndcaps(boolean endcaps) {
		this.endcaps = endcaps;
	}
	
}
