package com.veltro.blazingbarrels.engine.graphics_3d.model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

/**
 * The ModelBot provides methods for handling 3-D {@link Model models}
 * 
 * @author LinearLogic
 * @since 0.0.5
 */
public class ModelBot {

	/**
	 * Attempts to load a {@link Model model} from the specified .obj file
	 * 
	 * @param modelFile The {@link File} from which to load the model
	 * @return The model loaded from the file if the operation was successful
	 * @throws FileNotFoundException Thrown if the paramater File cannot be located
	 * @throws IOException Thrown by the BufferedReader if it encounters an error while parsing the data in the file
	 */
	public static Model loadModel(File modelFile) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(modelFile));
		Model model = new Model();
		String dataLine;

		while ((dataLine = reader.readLine()) != null) {
			String[] data = dataLine.split("\\s+");
			if (data.length != 4) // Prevents ArrayIndexOutOfBoundsExceptions
				continue;
			// Parse the .obj file lines:
			if (data[0].equalsIgnoreCase("v"))
				model.addVertex(new Vector3f(Float.valueOf(data[1]), Float.valueOf(data[2]), Float.valueOf(data[3])));
			else if (data[0].equalsIgnoreCase("vn"))
				model.addNormal(new Vector3f(Float.valueOf(data[1]), Float.valueOf(data[2]), Float.valueOf(data[3])));
			else if (data[0].equalsIgnoreCase("f")) {
				String[] xData = data[1].split("/"), yData = data[2].split("/"), zData = data[3].split("/");
				Vector3f vertex = new Vector3f(Float.valueOf(xData[0]), Float.valueOf(yData[0]), Float.valueOf(zData[0])),
						normal = new Vector3f(Float.valueOf(xData[2]), Float.valueOf(yData[2]), Float.valueOf(zData[2]));
				model.addFace(new Face(vertex, normal));
			}
			continue;
		}

		reader.close();
		return model;
	}

	/**
	 * Creates a VBO (virtual buffer object) for the provided {@link Model}, and loads it into the openGL pipe.
	 * 
	 * @param model The model to render as a VBO
	 * @return An integer Array consisting of the vertex and normal VBO handles
	 */
	public static int[] generateVBO(Model model) {
		int vertexHandle = glGenBuffers();
		FloatBuffer vertices = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);
		
		int normalHandle = glGenBuffers();
		FloatBuffer normals = BufferUtils.createFloatBuffer(model.getFaces().size() * 9);
		
		for (Face face : model.getFaces()) {
			try {
			    vertices.put(asFloatArray(model.getVertices().get((int) face.VERTEX.x - 1)));
			    vertices.put(asFloatArray(model.getVertices().get((int) face.VERTEX.y - 1)));
			    vertices.put(asFloatArray(model.getVertices().get((int) face.VERTEX.z - 1)));
			    normals.put(asFloatArray(model.getNormals().get((int) face.VERTEX.x - 1)));
			    normals.put(asFloatArray(model.getNormals().get((int) face.VERTEX.y - 1)));
			    normals.put(asFloatArray(model.getNormals().get((int) face.VERTEX.z - 1)));
			} catch (IndexOutOfBoundsException e) {
				continue;
			}
		}
		vertices.flip();
		normals.flip();

		glBindBuffer(GL_ARRAY_BUFFER, vertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		glVertexPointer(3, GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, normalHandle);
		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		glNormalPointer(GL_FLOAT, 0, 0L);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		return new int[] {vertexHandle, normalHandle};
	}

	/**
	 * Utility method - turns a float vector into a float Array of length 3
	 * 
	 * @param vector
	 * @return A float Array containing the x, y, and z components of the provided Vector3f object
	 */
	private static float[] asFloatArray(Vector3f vector) {
		return new float[] {vector.x, vector.y, vector.z};
	}
}
