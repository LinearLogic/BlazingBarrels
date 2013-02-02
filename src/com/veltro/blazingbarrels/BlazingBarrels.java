package com.veltro.blazingbarrels;

import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Main class, containing the {@link #BlazingBarrels(int, int) game object constructor}
 * and {@link #main(String[]) program entry point}.
 * 
 * @author LinearLogic
 * @version 0.0.2
 * @since 0.0.1
 */
public class BlazingBarrels {

	public static final String VERSION = "0.0.2";
	/**
	 * If this flag is 'true', the program will log its activity to Console
	 */
	private static boolean debugModeEnabled;

	/**
	 * Whether to load a new game instance after destroying the current one
	 */
	private boolean reload;

	/**
	 * Initialized as 'true' in the {@link #BlazingBarrels(int, int) constructor},
	 * this boolean variable will cause the game loop to exit if set to 'false'
	 */
	private boolean running;

	/**
	 * The time, in milliseconds, between the previous frame and the current one
	 */
	private int delta;

	/**
	 * The adjusted system time at which the last frame was rendered
	 */
	private long lastFrameTime;

	/**
	 * The width, in pixels, of the game window
	 */
	private int windowWidth;

	/**
	 * The height, in pixels, of the game window
	 */
	private int windowHeight;

	/**
	 * Constructor - creates a game window with the specified dimensions,
	 * sets up the game, and runs the main logic/rendering loop.
	 * 
	 * @param windowWidth Width, in pixels, of the game window
	 * @param windowHeight Height, in pixels, of the game window
	 */
	public BlazingBarrels(int windowWidth, int windowHeight) {
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		if (debugModeEnabled)
		System.out.println("Constructing the game object. Window dimensions: " + windowWidth + "x" + windowHeight + " pixels.");
		initDisplay(windowWidth, windowHeight);
		lastFrameTime = getTime();

		running = true;
		reload = false;
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Reset 2D and 3D
			long currentTime = getTime();
			delta = (int) (currentTime - lastFrameTime);
			lastFrameTime = currentTime;

			Display.update();
			Display.sync(60); // Framerate = 60 FPS
			if (Display.isCloseRequested()) { // Exit without reloading
				running = false;
				reload = false;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_F5)) { // Exit, but load a new game instance
				running = false;
				reload = true;
			}
		}

		if (debugModeEnabled)
			System.out.println("Destroying the openGL context and closing the game window.");
		Display.destroy();
		if (reload)
			new BlazingBarrels(windowWidth, windowHeight);
		else
			System.exit(0);
	}

	/**
	* Initializes the game display window with the given pixel dimensions
	*
	* @param width The pixel width of the window
	* @param height The pixel height of the window
	*/
	private void initDisplay(int width, int height) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("Blazing Barrels");
			Display.setVSyncEnabled(true); // Framerate cannot exceed the computer monitor's refresh rate (prevents image tearing)
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return 'true' iff the program is running in debug mode.
	 */
	public boolean isDebugModeEnabled() {
		return debugModeEnabled;
	}

	public int getCurrentFPS() {
		return 1000/delta;
	}

	/**
	 * @return The time {@link #delta} between the current and previous frames
	 */
	public int getDelta() {
		return delta;
	}

	/**
	 * @return The value of the {@link #windowWidth} variable
	 */
	public int getWindowWidth() {
		return windowWidth;
	}

	/**
	 * @param windowWidth The value to which to set the {@link #windowWidth} variable
	 */
	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	/**
	 * @return The value of the {@link #windowHeight} variable
	 */
	public int getWindowHeight() {
		return windowHeight;
	}

	/**
	 * @param windowHeight The value to which to set the {@link #windowHeight} variable
	 */
	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	/**
	 * @return The adjusted system time
	 */
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * This is where it all starts... determines whether to run the program in
	 * DEBUG mode, and constructs the game object
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to BlazingBarrels! Run in DEBUG mode? (Y/N)");
		Scanner sc = new Scanner(System.in);
		while(true) {
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
				debugModeEnabled = true;
				break;
			}
			else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
				debugModeEnabled = false;
				break;
			}
			else
				System.out.println("Invalid answer, please type 'Y' or 'N'");
		}
		sc.close();
		new BlazingBarrels(960, 540);
	}
}