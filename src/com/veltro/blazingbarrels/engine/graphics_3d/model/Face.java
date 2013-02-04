package com.veltro.blazingbarrels.engine.graphics_3d.model;

import org.lwjgl.util.vector.Vector3f;

/**
 * A Face is a two-dimensional surface specified by a 3-D vertex and normal vector.
 * Faces are used to render {@link Model models}
 * 
 * @author LinearLogic
 * @since 0.0.5
 */
public class Face {

	/**
	 * The 3-D coordinate of the vertex of the face (stored using a vector)
	 */
	public final Vector3f VERTEX;

	/**
	 * The 3-D normal of the face (a vector)
	 */
	public final Vector3f NORMAL;

	/**
	 * Constructor - initializes the {@link #VERTEX} and {@link #NORMAL} vectors with the supplied Vector3f objects.
	 * 
	 * @param vertex
	 * @param normal
	 */
	public Face(Vector3f vertex, Vector3f normal) {
		this.VERTEX = vertex;
		this.NORMAL = normal;
	}
	
}
