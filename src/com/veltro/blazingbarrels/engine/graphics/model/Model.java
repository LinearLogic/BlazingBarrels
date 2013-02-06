package com.veltro.blazingbarrels.engine.graphics.model;

import java.util.ArrayList;
import java.util.List;

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
	 * The 3-D coordinates of the vertices corresponding to each face of the model
	 */
	private List<Vector3f> vertices;

	/**
	 * The 3-D face normals (vectors) corresponding to each face of the model
	 */
	private List<Vector3f> normals;

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

	/**
	 * @return The model's {@link #vertices}
	 */
	public List<Vector3f> getVertices() {
		return vertices;
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
	public List<Vector3f> getNormals() {
		return normals;
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
	 * @return The model's {@link #faces}
	 */
	public List<Face> getFaces() {
		return faces;
	}

	/**
	 * Adds a {@link Face} object to the list of the model's {@link #faces}
	 * 
	 * @param face
	 */
	public void addFace(Face face) {
		faces.add(face);
	}
}
