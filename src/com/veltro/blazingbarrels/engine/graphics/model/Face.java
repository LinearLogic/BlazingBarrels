package com.veltro.blazingbarrels.engine.graphics.model;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

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
	 * The index of the texture to be applied to the face
	 */
	public final Vector3f TEXTURE_INDEX;

	public final Texture TEXTURE;

	/**
	 * Constructor - initializes the {@link #VERTEX} and {@link #NORMAL} vectors with the supplied Vector3f objects.
	 * 
	 * @param vertexCoord
	 * @param textureCoord
	 * @param normalCoord
	 */
	public Face(Vector3f vertex, Vector3f textureCoord, Vector3f normal, Texture texture) {
		VERTEX = vertex;
		TEXTURE_INDEX = textureCoord;
		NORMAL = normal;
		TEXTURE = texture;
	}
}
