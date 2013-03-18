package com.veltro.blazingbarrels.engine.graphics;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * The TextureBot provides a utility method for loading Texture objects from an image file.
 * 
 * @author LinearLogic
 * @since 0.4.0
 */
public class TextureBot {

	/**
	 * @param format A string representing the format of the texture
	 * @param pathToTexture The system path to the texture file
	 * @return The Texture (image) of the specified format (JPG, PNG, etc.) at the specified disk location. If the Texture
	 * object is unable to be loaded (due to an invalid path, an incompatible format, or otherwise), the method returns null.
	 */
	public static Texture loadTexture(String format, String pathToTexture) {
		Texture tex = null;
		try {
			tex = TextureLoader.getTexture(format, ResourceLoader.getResourceAsStream(pathToTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
}
