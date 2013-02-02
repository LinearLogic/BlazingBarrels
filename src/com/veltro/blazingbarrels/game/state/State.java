package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;

/**
 * States are different phases of the program (eg. the intro or the main menu). Each state has a unique {@link StateType}.
 * As states differ greatly in content and handling, each is given its own subclass of this abstract class.
 * 
 * @author LinearLogic
 * @since 0.0.2
 */
public abstract class State {

	/**
	 * The state's {@link StateType type}
	 */
	protected final StateType type;

	/**
	 * Represents the collective status of all {@link #importantKeys}. Only if its value is 'true' will new
	 * keyboard input be handled. In the case of the {@link MainMenuState}, the left mouse button also
	 * affects the status of the keyDown flag.
	 */
	protected boolean keyDown;

	/**
	 * Sets the {@link ChristmasCrashers#currentState} to this state and runs the specific initialization code (such
	 * as starting an animation or loading a world) for this state.
	 */
	public abstract void initialize();

	/**
	 * Constructor for the State superclass - sets the state's {@link StateType type} to the provided value.
	 * 
	 * @param type
	 */
	public State(StateType type) {
		this.type = type;
	}

	/**
	 * Registers and responds to keyboard and mouse input, and returns the new state determined based on input.
	 * The majority of the logic for the state is executed in this method.
	 * 
	 * @return The new state that will be switched on in the next iteration of the main loop.
	 */
	public abstract StateType handleInput();

	/**
	 * Renders the frame (and all graphical objects in it) for the game state.
	 */
	public abstract void draw();

	/**
	 * Iterates through the keyIDs in {@link #importantKeys}, checking the state of each key. If one of
	 * the relevant keys is depressed, function returns 'true'. Otherwise, it returns 'false'.
	 */
	protected void checkKeyStates() {
		if (keyDown) {
			for (int keyID : type.importantKeys)
				if (Keyboard.isKeyDown(keyID))
					return;
		}
		keyDown = false;
	}

	/**
	 * @return The value of the {@link #keyDown} variable
	 */
	public boolean isKeyDown() {
		return keyDown;
	}

	/**
	 * Sets the value of {@link #keyDown} to the supplied boolean
	 * @param value The new key state (true if down, false if up)
	 */
	public void setKeyDown(boolean value) {
		keyDown = value;
	}

	/**
	 * @return The state's {@link StateType type}
	 */
	public StateType getType() {
		return type;
	}
}