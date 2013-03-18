package com.veltro.blazingbarrels.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import com.veltro.blazingbarrels.engine.graphics.model.Model;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * The RenderBot3D is a utility class that provides various methods for three-dimensional rendering, such as drawing
 * VBOs or colored spheres.
 * 
 * @author LinearLogic
 * @since 0.4.2
 */
public class RenderBot3D {

	/**
	 * Renders a fully opaque and black {@link Model} at the specified {@link Location3D location}
	 * 
	 * @param model The model whose VBO to retrieve and draw
	 * @param location The location (position and rotation) in the game world at which to render the model
	 */
	public void renderModel(Model model, Location3D location) {
		renderTransparentColoredModel(model, location, 0, 0, 0, 1);
	}

	/**
	 * Renders a fully opaque {@link Model} at the specified {@link Location3D location} with the specified color
	 * 
	 * @param model The model whose VBO to retrieve and draw
	 * @param location The location (position and rotation) in the game world at which to render the model
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderColoredModel(Model model, Location3D location, float r, float g, float b) {
		renderTransparentColoredModel(model, location, r, g, b, 1);
	}

	/**
	 * Renders a (partially) transparent {@link Model} at the specified {@link Location3D location} with the specified
	 * color
	 * 
	 * @param model The model whose VBO to retrieve and draw
	 * @param location The location (position and rotation) in the game world at which to render the model
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentColoredModel(Model model, Location3D location, float r, float g, float b,
			float transparency) {
//		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glLoadIdentity();
        glBindBuffer(GL_ARRAY_BUFFER, model.getVBOVertexHandle());
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, model.getVBONormalHandle());
        glNormalPointer(GL_FLOAT, 0, 0L);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);
        glColor4f(r, g, b, transparency);
        glMaterialf(GL_FRONT, GL_SHININESS, 10f);
        glDrawArrays(GL_TRIANGLES, 0, model.getFaces().size() * 3);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
}
