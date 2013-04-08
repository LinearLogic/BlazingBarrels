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
	private float bottomOuterRadius;

	/**
	 * The radius, in pixels, of the hole in the (roughly) circular hole in the bottom of the tube
	 */
	private float bottomInnerRadius;

	/**
	 * The outer radius, in pixels, of the (roughly) circular top end of the tube
	 */
	private float topOuterRadius;

	/**
	 * The radius, in pixels, of the hole in the (roughly) circular top end of the tube
	 */
	private float topInnerRadius;

	/**
	 * The distance, in pixels, between the two ends of the tube
	 */
	private float length;

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
	 * Shortcut constructor for regular cylinders (uniform top and bottom outer radii, inner radii of zero, lateral
	 * face count of 32)
	 * 
	 * @param radius The tube's radius, in pixels
	 * @param length The tube's {@link #length}
	 * @param endcaps Whether the tube has {@link #endcaps}
	 * @param centerLocation The location of the {@link Construct3D} object to which this sphere belongs
	 * @param relativeLocation The tube's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the tube's color (a float between 0 and 1)
	 * @param g The green-intensity of the tube's color (a float between 0 and 1)
	 * @param b The blue-intensity of the tube's color (a float between 0 and 1)
	 * @param transparency The transparency level of the tube's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Tube(float radius, float length, boolean endcaps, Location3D centerLocation, Location3D relativeLocation,
			float r, float g, float b, float transparency) {
		this(radius, 0, radius, 0, length, 32, endcaps, centerLocation, relativeLocation, r, g, b, transparency);
	}

	/**
	 * Complete constructor - initializes each of the tube's dimension specifications and constructs the
	 * {@link Shape3D shape superclass}
	 * 
	 * @param bottomOuterRadius The tube's {@link #bottomOuterRadius}
	 * @param bottomInnerRadius The tube's {@link #bottomInnerRadius}
	 * @param topOuterRadius The tube's {@link #topOuterRadius}
	 * @param topInnerRadius The tube's {@link #topInnerRadius}
	 * @param length The tube's {@link #length}
	 * @param lateralFaceCount See {@link #edges}
	 * @param endcaps Whether the tube has {@link #endcaps}
	 * @param centerLocation The location of the {@link Construct3D} object to which this sphere belongs
	 * @param relativeLocation The tube's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the tube's color (a float between 0 and 1)
	 * @param g The green-intensity of the tube's color (a float between 0 and 1)
	 * @param b The blue-intensity of the tube's color (a float between 0 and 1)
	 * @param transparency The transparency level of the tube's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Tube(float bottomOuterRadius, float bottomInnerRadius, float topOuterRadius, float topInnerRadius,
			float length, int lateralFaceCount, boolean endcaps, Location3D centerLocation,
			Location3D relativeLocation, float r, float g, float b, float transparency) {
		super(centerLocation, relativeLocation, r, g, b, transparency);
		this.bottomOuterRadius = bottomOuterRadius;
		this.bottomInnerRadius= bottomInnerRadius;
		this.topOuterRadius = topOuterRadius;
		this.topInnerRadius = topInnerRadius;
		this.length = length;
		edges = lateralFaceCount;
		this.endcaps = endcaps;
	}

	/**
	 * Renders the tube by calling the renderColoredCylinder(...) method in {@link RenderBot3D}
	 * 
	 * @param x The x-coordinate of the location of the center of the construct to which this shape belong
	 * @param y The y-coordinate of the above location
	 * @param z The z-coordinate of the above location
	 */
	public void draw() {
		Location3D l = new Location3D(centerLocation.getX() + renderingPosition.getX(), centerLocation.getY() +
				renderingPosition.getY(), centerLocation.getZ() + renderingPosition.getZ(), centerLocation.getYaw() +
				location.getYaw(), centerLocation.getPitch() + location.getPitch(), centerLocation.getRoll() +
				location.getRoll());
		RenderBot3D.renderColoredCylinder(bottomOuterRadius, topOuterRadius, length, edges, 1, l, r, g, b, transparency);
		if (bottomInnerRadius != 0 && topInnerRadius != 0)
			RenderBot3D.renderColoredCylinder(bottomInnerRadius, topInnerRadius, length, edges, 5, l, r, g, b, transparency);
		if (endcaps) {
			RenderBot3D.renderColoredDisk(bottomOuterRadius, bottomInnerRadius, edges, l, r, g, b, transparency);
			l.translate(-(float) (length * Math.sin(l.getYaw() * Math.PI / 180.0) * Math.cos(l.getPitch() * Math.PI /
				180.0)), (float) (length * Math.sin(l.getPitch() * Math.PI / 180.0)), -(float) (length *
				Math.cos(l.getYaw() * Math.PI / 180.0) * Math.cos(l.getPitch() * Math.PI / 180.0)));
			RenderBot3D.renderColoredDisk(topOuterRadius, topInnerRadius, edges, l, r, g, b, transparency);
		}
	}

	/**
	 * @return The {@link #bottomOuterRadius} value
	 */
	public float getBottomOuterRadius() {
		return bottomOuterRadius;
	}


	/**
	 * Sets the value of {@link #bottomOuterRadius}
	 *
	 * @param bottomOuterRadius
	 */
	public void setBottomOuterRadius(float bottomOuterRadius) {
		this.bottomOuterRadius = bottomOuterRadius;
	}


	/**
	 * @return The {@link #bottomInnerRadius} value
	 */
	public float getBottomInnerRadius() {
		return bottomInnerRadius;
	}


	/**
	 * Sets the value of {@link #bottomInnerRadius}
	 *
	 * @param bottomInnerRadius
	 */
	public void setBottomInnerRadius(float bottomInnerRadius) {
		this.bottomInnerRadius = bottomInnerRadius;
	}


	/**
	 * @return The {@link #topOuterRadius} value
	 */
	public float getTopOuterRadius() {
		return topOuterRadius;
	}


	/**
	 * Sets the value of {@link #topOuterRadius}
	 *
	 * @param topOuterRadius
	 */
	public void setTopOuterRadius(float topOuterRadius) {
		this.topOuterRadius = topOuterRadius;
	}


	/**
	 * @return The {@link #topInnerRadius} value
	 */
	public float getTopInnerRadius() {
		return topInnerRadius;
	}


	/**
	 * Sets the value of {@link #topInnerRadius}
	 *
	 * @param topInnerRadius
	 */
	public void setTopInnerRadius(float topInnerRadius) {
		this.topInnerRadius = topInnerRadius;
	}


	/**
	 * @return The {@link #endcaps} value
	 */
	public boolean isEndcaps() {
		return endcaps;
	}


	/**
	 * Sets the value of {@link #endcaps}
	 *
	 * @param endcaps
	 */
	public void setEndcaps(boolean endcaps) {
		this.endcaps = endcaps;
	}


	/**
	 * @return The {@link #length} of the tube
	 */
	public float getLength() {
		return length;
	}

	/**
	 * Sets the {@link #length} of the tube
	 * 
	 * @param height
	 */
	public void setLength(float height) {
		this.length = height;
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
