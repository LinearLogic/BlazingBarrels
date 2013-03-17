package com.veltro.blazingbarrels;

import static org.lwjgl.opengl.GL11.*;

import java.util.Scanner;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.veltro.blazingbarrels.game.state.ConnectState;
import com.veltro.blazingbarrels.game.state.GameState;
import com.veltro.blazingbarrels.game.state.IntroState;
import com.veltro.blazingbarrels.game.state.MainMenuState;
import com.veltro.blazingbarrels.game.state.State;
import com.veltro.blazingbarrels.game.state.StateType;

/**
 * Main class, containing the {@link #BlazingBarrels(int, int) game object constructor}
 * and {@link #main(String[]) program launch point}.
 * 
 * @author LinearLogic
 * @version 0.3.3
 */
public class BlazingBarrels {

	public static final String VERSION = "0.3.3";
	/**
	 * If this flag is 'true', the program will log its activity to Console
	 */
	private static boolean debugModeEnabled;

	/**
	 * Whether to load a new game instance after destroying the current one
	 */
	private static boolean reload;

	/**
	 * Initialized as 'true' in the {@link #BlazingBarrels(int, int) constructor},
	 * this boolean variable will cause the game loop to exit if set to 'false'
	 */
	private static boolean running;

	/**
	 * The time, in milliseconds, between the previous frame and the current one
	 */
	private static int delta;

	/**
	 * The adjusted system time at which the last frame was rendered
	 */
	private long lastFrameTime;

	/**
	 * The width, in pixels, of the game window
	 */
	private static int windowWidth;

	/**
	 * The height, in pixels, of the game window
	 */
	private static int windowHeight;

	/**
	 * The {@link State game state} that the program is currently in
	 */
	private static State currentState;

	/**
	 * An Array of all the game states ({@link State} subclass objects).
	 * This list is populated in the {@link #BlazingBarrels(int, int) constructor}
	 */
	private static State[] states;

	/**
	 * Constructor - creates a game window with the specified dimensions,
	 * sets up the game, and runs the main logic/rendering loop.
	 * 
	 * @param windowWidth Width, in pixels, of the game window
	 * @param windowHeight Height, in pixels, of the game window
	 */
	public BlazingBarrels(int windowWidth, int windowHeight) {
		BlazingBarrels.windowWidth = windowWidth;
		BlazingBarrels.windowHeight = windowHeight;
		if (debugModeEnabled)
			System.out.println("Constructing the game object. Window dimensions: " + windowWidth + "x" + windowHeight + " pixels.");
		initDisplay(windowWidth, windowHeight);
		states = new State[] {new IntroState(), new MainMenuState(), new ConnectState(), new GameState()};
		lastFrameTime = getTime();

		running = true;
		reload = false;
		
		setCurrentState(StateType.INTRO, true);
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Reset 2D and 3D
			long currentTime = getTime();
			delta = (int) (currentTime - lastFrameTime);
			lastFrameTime = currentTime;

			currentState.handleInput();
			currentState.draw();

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
		if (reload) {
			if (debugModeEnabled)
				System.out.println("Reloading the game...");
			new BlazingBarrels(windowWidth, windowHeight);
		}
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
	 * @return The current game state (a {@link State} subclass object)
	 */
	public static State getCurrentState() {
		return currentState;
	}

	/**
	 * Sets the current game state to the {@link State} subclass object with the provided type
	 * 
	 * @param state A {@link StateType} enum value
	 * @param initialize Whether to call the state's initialize() method after setting it as the current state.
	 */
	public static void setCurrentState(StateType state, boolean initialize) {
		for (State s : states)
			if (s.getType().equals(state)) {
				if (debugModeEnabled)
					System.out.println("Switching to the " + s.getType().toString() + " state.");
				currentState = s;
				if (initialize)
					s.initialize();
			}
	}

	/**
	 * 
	 * @return 'true' iff the program is running in debug mode.
	 */
	public static boolean isDebugModeEnabled() {
		return debugModeEnabled;
	}

	/**
	 * @return The current framerate
	 */
	public static int getCurrentFPS() {
		return 1000/delta;
	}

	/**
	 * @return The time {@link #delta} between the current and previous frames
	 */
	public static int getDelta() {
		return delta;
	}

	/**
	 * @return The value of the {@link #windowWidth} variable
	 */
	public static int getWindowWidth() {
		return windowWidth;
	}

	/**
	 * @param windowWidth The value to which to set the {@link #windowWidth} variable
	 */
	public static  void setWindowWidth(int width) {
		windowWidth = width;
	}

	/**
	 * @return The value of the {@link #windowHeight} variable
	 */
	public static int getWindowHeight() {
		return windowHeight;
	}

	/**
	 * @param windowHeight The value to which to set the {@link #windowHeight} variable
	 */
	public static void setWindowHeight(int height) {
		windowHeight = height;
	}

	/**
	 * @return The adjusted system time
	 */
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	/**
	 * Sets the {@link #running} variable to 'false', causing the main game loop to exit.
	 * The {@link #reload} variable is set to the provided boolean, determining whether the
	 * program is completely terminated or is reloaded after exiting the game loop.
	 * 
	 * @param reloadProgram Whether to reload the game after quitting
	 */
	public static void exitGameLoop(boolean reloadProgram) {
		running = false;
		reload = reloadProgram;
	}

	/**
	 * This is where it all starts... determines whether to run the program in
	 * DEBUG mode, and constructs the game object
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println("Game is not in a stable state. Cancelling launch...");
//		System.out.println("Welcome to BlazingBarrels! Run in DEBUG mode? (Y/N)");
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			String response = sc.nextLine();
//			if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
//				debugModeEnabled = true;
//				break;
//			}
//			else if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
//				debugModeEnabled = false;
//				break;
//			}
//			else
//				System.out.println("Invalid answer, please type 'Y' or 'N'");
//		}
//		sc.close();
//		new BlazingBarrels(960, 540);
	}
}
