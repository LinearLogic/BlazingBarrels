package com.veltro.blazingbarrels.engine.graphics_3d.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
}
