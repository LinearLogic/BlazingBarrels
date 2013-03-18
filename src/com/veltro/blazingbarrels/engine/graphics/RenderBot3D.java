package com.veltro.blazingbarrels.engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

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
	 * Renders a fully opaque cylinder with the specified attributes at the provided {@link Location3D location}. If
	 * the bottom and top radii are the same, a normal cylinder will be drawn. If the two radii differ, a frustum (if
	 * neither radius is zero) or cone (if one of the radii is zero) will be drawn.
	 * 
	 * @param bottomRadius The radius, in pixels, of the bottom of the cylinder
	 * @param topRadius The radius, in pixels, of the top of the cylinder
	 * @param height The height, in pixels, of the cylinder (the distance between its bases)
	 * @param slices The number of horizontal and vertical slices to use to divide the sphere for rendering (the higher
	 * this number, the smoother the surface of the sphere)
	 * @param location The location (position and rotation) of the center of the base of the cylinder
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderColoredCylinder(float bottomRadius, float topRadius, float height, int slices,
			Location3D location, float r, float g, float b) {
		renderTransparentColoredCylinder(bottomRadius, topRadius, height, slices, location, topRadius, g, b, 1);
	}

	/**
	 * Renders a (partially) transparent cylinder with the specified attributes at the provided
	 * {@link Location3D location}. If the bottom and top radii are the same, a normal cylinder will be drawn. If the
	 * two radii differ, a frustum (if neither radius is zero) or cone (if one of the radii is zero) will be drawn.
	 * 
	 * @param bottomRadius The radius, in pixels, of the bottom of the cylinder
	 * @param topRadius The radius, in pixels, of the top of the cylinder
	 * @param height The height, in pixels, of the cylinder (the distance between its bases)
	 * @param slices The number of horizontal and vertical slices to use to divide the sphere for rendering (the higher
	 * this number, the smoother the surface of the sphere)
	 * @param location The location (position and rotation) of the center of the base of the cylinder
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentColoredCylinder(float bottomRadius, float topRadius, float height, int slices,
			Location3D location, float r, float g, float b, float transparency) {
		glPushMatrix();
		glTranslatef(-location.getX(), -location.getY(), -location.getZ());
		glRotatef(location.getPitch(), -1, 0, 0);
        glRotatef(location.getYaw() - 90, 0, 1, 0);
        glRotatef(location.getRoll(), 0, 0, 1);
        glColor4f(r, g, b, transparency);
        (new Cylinder()).draw(bottomRadius, topRadius, height, slices, slices);
        glPopMatrix();
	}

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
		glPushMatrix();
        glBindBuffer(GL_ARRAY_BUFFER, model.getVBOVertexHandle());
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        glBindBuffer(GL_ARRAY_BUFFER, model.getVBONormalHandle());
        glNormalPointer(GL_FLOAT, 0, 0L);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_NORMAL_ARRAY);

        // Apply the location, color, and transparency:
        glRotatef(location.getPitch(), -1, 0, 0);
        glRotatef(location.getYaw() - 90, 0, 1, 0);
        glRotatef(location.getRoll(), 0, 0, 1);
        glTranslatef(-location.getX(), -location.getY(), -location.getZ());
        glColor4f(r, g, b, transparency);

        glMaterialf(GL_FRONT, GL_SHININESS, 10f);
        glDrawArrays(GL_TRIANGLES, 0, model.getFaces().size() * 3);
        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_NORMAL_ARRAY);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glPopMatrix();
	}

	/**
	 * Renders a fully opaque sphere with the specified attributes at the provided location
	 * 
	 * @param radius The radius, in pixels, of the sphere
	 * @param slices The number of horizontal and vertical slices to use to divide the sphere for rendering (the higher
	 * this number, the smoother the surface of the sphere)
	 * @param x The x-coordinate, in pixels, of the sphere's center
	 * @param y The y-coordinate, in pixels, of the sphere's center
	 * @param z The z-coordinate, in pixels, of the sphere's center
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderSphere(float radius, int slices, float x, float y, float z, float r, float g, float b) {
		renderTransparentColoredSphere(radius, slices, x, y, z, r, g, b, 1);
	}
	/**
	 * Renders a (partially) transparent sphere with the specified attributes at the provided location
	 * 
	 * @param radius The radius, in pixels, of the sphere
	 * @param slices The number of horizontal and vertical slices to use to divide the sphere for rendering (the higher
	 * this number, the smoother the surface of the sphere)
	 * @param x The x-coordinate, in pixels, of the sphere's center
	 * @param y The y-coordinate, in pixels, of the sphere's center
	 * @param z The z-coordinate, in pixels, of the sphere's center
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentColoredSphere(float radius, int slices, float x, float y, float z, float r, float g,
			float b, float transparency) {
		glPushMatrix();
		glTranslatef(-x, -y, -z);
		glColor4f(r, g, b, transparency);
		(new Sphere()).draw(radius, slices, slices);
		glPopMatrix();
	}
}
