 	package com.veltro.blazingbarrels.engine.graphics.model;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * A model is a three-dimensional object comprised of {@link #faces}, which are used to draw the surface of the model.
 * TODO: add textured models
 * 
 * @author LinearLogic
 * @since 0.0.5
 */
public class Model {

	/**
	 * The integer ID for the model's openGL vertex handle, used to render the model as a Virtual Buffer Object
	 */
	private int vboVertexHandle;

	/**
	 * The integer ID for the model's openGL normal handle, used to render the model as a Virtual Buffer Object
	 */
	private int vboNormalHandle;

	/**
	 * The 3-D coordinates of the vertices corresponding to each face of the model
	 */
	private List<Vector3f> vertices;

	/**
	 * The 3-D face normals (vectors) corresponding to each face of the model
	 */
	private List<Vector3f> normals;

	/**
	 * The vertices of a texture to be drawn onto the model
	 */
	private List<Vector2f> textureVertices;

	/**
	 * The surface faces ({@link Face} objects) of the model (each face has a corresponding vertex and normal vector)
	 */
	private List<Face> faces;

	/**
	 * Constructor - initializes the {@link #vertices}, {@link #normals}, and {@link #faces} ArrayLists
	 */
	public Model() {
		vertices = new ArrayList<Vector3f>();
		normals = new ArrayList<Vector3f>();
		faces = new ArrayList<Face>();
	}

	public void deleteBuffers() {
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboNormalHandle);
	}

	/**
	 * @return The model's {@link #vertices}
	 */
	public Vector3f[] getVertices() {
		return (Vector3f[]) vertices.toArray();
	}

	/**
	 * Adds a vertex coordinate to the list of the model's {@link #vertices}
	 * 
	 * @param vertex
	 */
	public void addVertex(Vector3f vertex) {
		vertices.add(vertex);
	}

	/**
	 * @return The model's {@link normals}
	 */
	public Vector3f[] getNormals() {
		return (Vector3f[]) normals.toArray();
	}

	/**
	 * Adds a face normal to the list of the model's {@link #normals}
	 * 
	 * @param normal
	 */
	public void addNormal(Vector3f normal) {
		normals.add(normal);
	}

	/**
	 * @return The model's {@link #textureVertices}
	 */
	public Vector2f[] getTextureVertices() {
		return (Vector2f[]) textureVertices.toArray();
	}

	/**
	 * Adds a texture vertex to the list of the model's {@link #textureVertices}
	 * 
	 * @param texVertex
	 */
	public void addTextureVertex(Vector2f texVertex) {
		textureVertices.add(texVertex);
	}

	/**
	 * @return The model's {@link #faces}
	 */
	public Face[] getFaces() {
		return (Face[]) faces.toArray();
	}

	/**
	 * Adds a {@link Face} object to the list of the model's {@link #faces}
	 * 
	 * @param face
	 */
	public void addFace(Face face) {
		faces.add(face);
	}

	/**
	 * @return The model's {@link #vboVertexHandle} value
	 */
	public int getVBOVertexHandle() {
		return vboVertexHandle;
	}

	/**
	 * Sets the model's {@link #vboVertexHandle} to the specified integer value
	 * 
	 * @param handle
	 */
	public void setVBOVertexHandle(int handle) {
		this.vboVertexHandle = handle;
	}

	/**
	 * @return The model's {@link #vboNormalHandle} value
	 */
	public int getVBONormalHandle() {
		return vboNormalHandle;
	}

	/**
	 * Sets the model's {@link #vboNormalHandle} to the specified integer value
	 * 
	 * @param handle
	 */
	public void setVBONormalHandle(int handle) {
		this.vboNormalHandle = handle;
	}
}
