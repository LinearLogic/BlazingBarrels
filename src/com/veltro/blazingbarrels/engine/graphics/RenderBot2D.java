package com.veltro.blazingbarrels.engine.graphics;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.veltro.blazingbarrels.BlazingBarrels;

/**
 * RenderBot2D is a utility class that provides various methods for two-dimensional rendering, such as drawing strings
 * or textured rectangles.
 * 
 * @author LinearLogic
 * @since 0.4.1
 */
public class RenderBot2D {

	/**
	 * Renders a colored rectangle with the specified color intensities that fills up the entire game window
	 * 
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderBackground(double r, double g, double b) {
		renderTransparentColoredRectangle(0, 0, BlazingBarrels.getWindowWidth(), BlazingBarrels.getWindowHeight(), r,
				g, b, 1.0);
	}

	/**
	 * Renders a colored rectangle with the specified color intensities and transparency level that fills up the entire
	 * game window
	 * 
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentBackground(double r, double g, double b, double transparency) {
		renderTransparentColoredRectangle(0, 0, BlazingBarrels.getWindowWidth(), BlazingBarrels.getWindowHeight(), r,
				g, b, transparency);
	}

	/**
	 * Renders a fully opaque rectangular outline with the supplied attributes at the supplied location
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderLinedRectangle(double x, double y, double w, double h, double r, double g, double b) {
		renderTransparentLinedRectangle(x, y, w, h, r, g, b, 1.0);
	}

	/**
	 * Renders a (partially) transparent rectangular outline with the supplied attributes at the supplied location
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentLinedRectangle(double x, double y, double w, double h, double r, double g, double b,
			double transparency) {
		glDisable(GL_TEXTURE_2D);
		glColor4d(r, g, b, transparency);
		glBegin(GL_LINE_LOOP);
			glVertex2d(x, y); // Top left
			glVertex2d(x + w, y); // Top right
			glVertex2d(x + w, y + h); // Bottom right
			glVertex2d(x, y + h); // Bottom left
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}

	/**
	 * Renders a fully opaque colored rectangle with the supplied attributes at the supplied location
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Blue intensity (must be between 0.0 and 1.0, inclusive)
	 */
	public void renderColoredRectangle(double x, double y, double w, double h, double r, double g, double b) {
		renderTransparentColoredRectangle(x, y, w, h, r, g, b, 1.0);
	}

	/**
	 * Renders a (partially) transparent colored rectangle with the supplied attributes at the supplied location
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param r Red intensity (must be between 0.0 and 1.0, inclusive)
	 * @param g Blue intensity (must be between 0.0 and 1.0, inclusive)
	 * @param b Green intensity (must be between 0.0 and 1.0, inclusive)
	 * @param transparency The transparency factor of the rectangle (0.0 = entirely transparent, 1.0 = entirely opaque)
	 */
	public void renderTransparentColoredRectangle(double x, double y, double w, double h, double r, double g, double b,
			double transparency) {
		glDisable(GL_TEXTURE_2D);
		glColor4d(r, g, b, transparency);
		glBegin(GL_TRIANGLE_FAN);
			glVertex2d(x, y); // Top left
			glVertex2d(x + w, y); // Top right
			glVertex2d(x + w, y + h); // Bottom right
			glVertex2d(x, y + h); // Bottom left
		glEnd();
		glEnable(GL_TEXTURE_2D);
	}

	/**
	 * Renders a fully opaque rectangle with a texture object (fitted to the rectangle)
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param texture The texture object with which to fill the rectangle
	 */
	public void renderTexturedRectangle(double x, double y, double w, double h, Texture texture) {
		renderTransparentTexturedRectangle(x, y, w, h, texture, 0.0, 0.0, 1.0, 1.0, 1.0);
	}

	/**
	 * Renders a fully opaque rectangle with a texture object and the supplied location and dimensions of the
	 * texture within the rectangle
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param texture The texture object with which to fill the rectangle
	 * @param texX X-coordinate of the top-lefthand corner of the texture (pixel location within the rendering window)
	 * @param texY Y-coordinate of the top-lefthand corner of the texture (pixel location within the rendering window)
	 * @param texW Width of the texture, in pixels
	 * @param texH Height of the texture, in pixels
	 */
	public void renderTexturedRectangle(double x, double y, double w, double h, Texture texture, double texX,
			double texY,  double texW, double texH) {
		renderTransparentTexturedRectangle(x, y, w, h, texture, texX, texY, texW, texH, 1.0);
	}

	/**
	 * Renders a (partially) transparent rectangle with a texture object (fitted to the rectangle)
	 * 
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param texture The texture object with which to fill the rectangle
	 * @param transparency The transparency factor of the rectangle (0 = entirely transparent, 1 = entirely opaque)
	 */
	public void renderTransparentTexturedRectangle(double x, double y, double w, double h, Texture texture,
			double transparency) {
		renderTransparentTexturedRectangle(x, y, w, h, texture, 0.0, 0.0, 1.0, 1.0, transparency);
	}

	/**
	 * Renders a (partially) transparent rectangle with a texture object and the supplied location and dimensions
	 *  of the texture within the rectangle
	 *  
	 * @param x X-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param y Y-coordinate of the top-lefthand corner of the rectangle (pixel location within the rendering window)
	 * @param w Width of the rectangle, in pixels
	 * @param h Height of the rectangle, in pixels
	 * @param texture The texture object with which to fill the rectangle
	 * @param texX X-coordinate of the top-lefthand corner of the texture (pixel location within the rendering window)
	 * @param texY Y-coordinate of the top-lefthand corner of the texture (pixel location within the rendering window)
	 * @param texW Width of the texture, in pixels
	 * @param texH Height of the texture, in pixels
	 * @param transparency The transparency factor of the rectangle (0 = entirely transparent, 1 = entirely opaque)
	 */
	public void renderTransparentTexturedRectangle(double x, double y, double w, double h, Texture texture,
			double texX, double texY, double texW, double texH, double transparency) {
		glEnable(GL_TEXTURE_2D);
		glColor4d(1.0, 1.0, 1.0, transparency);
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());

		glBegin(GL_TRIANGLE_FAN);
			glTexCoord2d(texX, texY + texH);
			glVertex2d(x, y); // Top left

			glTexCoord2d(texX + texW, texY + texH);
			glVertex2d(x + w, y); // Top right

			glTexCoord2d(texX + texW, texY);
			glVertex2d(x + w, y + h); // Bottom right

			glTexCoord2d(texX, texY);
			glVertex2d(x, y + h); // Bottom left
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
}
