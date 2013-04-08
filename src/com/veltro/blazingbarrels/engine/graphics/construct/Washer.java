package com.veltro.blazingbarrels.engine.graphics.construct;

import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * A washer (rendered horizontally by default) is a circle (or polygon, depending on the number of {@link #edges}) with
 * a hole in its center of radius innerRadius (if the inner radius is zero and the edge count is high, the result is a
 * typical circle).
 * 
 * @author LinearLogic
 * @since 0.5.3
 */
public class Washer extends Shape3D {

	/**
	 * The radius, in pixels, of the washer as a whole (the distance from its center to its outer rim)
	 */
	private float outerRadius;

	/**
	 * The radius, in pixels, of the hole in the washer (must be greater than or equal to zero and less than or equal
	 * to the {@link #outerRadius} value)
	 */
	private float innerRadius;

	/**
	 * The number of edges that comprise the washer's inner and outer rims (if this value is 4, for instance, the washer
	 */
	private int edges;

	/**
	 * Constructor - initializes each of the washer's dimension specifications and constructs the
	 * {@link Shape3D shape superclass}
	 * 
	 * @param outerRadius The washer's {@link #outerRadius}
	 * @param innerRadius The washer's {@link #innerRadius}
	 * @param edgesCount The washer's {@link #edges edge count}
	 * @param centerLocation The location of the {@link Construct3D} object to which this sphere belongs
	 * @param relativeLocation The washer's position (relative to the above centerLocation) and rotation (absolute)
	 * @param r The red-intensity of the washer's color (a float between 0 and 1)
	 * @param g The green-intensity of the washer's color (a float between 0 and 1)
	 * @param b The blue-intensity of the washer's color (a float between 0 and 1)
	 * @param transparency The transparency level of the washer's surface (a float between 0 and 1; 0 => transparent)
	 */
	public Washer(float outerRadius, float innerRadius, int edgeCount, Location3D centerLocation,
			Location3D relativeLocation, float r, float g, float b, float transparency) {
		super(centerLocation, relativeLocation, r, g, b, transparency);
		this.outerRadius = outerRadius;
		this.innerRadius = innerRadius;
		edges = edgeCount;
	}

	/**
	 * Determines the sphere's absolute location based on its {@link Shape3D#location}, {@link Shape3D#centerLocation},
	 * {@link Shape3D#renderingPosition} and renders it by calling renderColoredDisk(...) method in {@link RenderBot3D}
	 */
	public void draw() {
		RenderBot3D.renderColoredDisk(outerRadius, innerRadius, edges, new Location3D(centerLocation.getX() +
				renderingPosition.getX(), centerLocation.getY() + renderingPosition.getY(), centerLocation.getZ() +
				renderingPosition.getZ(),  centerLocation.getYaw() + location.getYaw(), centerLocation.getPitch() +
				location.getPitch(), centerLocation.getRoll() + location.getRoll()), r, g, b, transparency);
	}

	/**
	 * @return The {@link #outerRadius} value
	 */
	public float getOuterRadius() {
		return outerRadius;
	}

	/**
	 * Sets the value of {@link #outerRadius}
	 *
	 * @param outerRadius
	 */
	public void setOuterRadius(float outerRadius) {
		this.outerRadius = outerRadius;
	}

	/**
	 * @return The {@link #innerRadius} value
	 */
	public float getInnerRadius() {
		return innerRadius;
	}

	/**
	 * Sets the value of {@link #innerRadius}
	 *
	 * @param innerRadius
	 */
	public void setInnerRadius(float innerRadius) {
		this.innerRadius = innerRadius;
	}

	/**
	 * @return The {@link #edges} value
	 */
	public int getEdgeCount() {
		return edges;
	}

	/**
	 * Sets the value of {@link #edges}
	 *
	 * @param edges
	 */
	public void setEdgeCount(int edges) {
		this.edges = edges;
	}
}
