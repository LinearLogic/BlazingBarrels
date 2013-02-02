package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;

/**
 * An enum containing the various game states and data concerning their (keyboard and mouse) input handling.
 * 
 * @author LinearLogic
 * @since 0.0.2
 */
public enum StateType {

	/**
	 * The loading screen. Pressing the 'escape' key exits to the {@link StateType.MAIN_MENU} state
	 */
	INTRO(new int[] {Keyboard.KEY_ESCAPE}, false),

	/**
	 * The main menu state. Pressing the 'escape' key quits the game.
	 * This state contains two rather self-explanatory buttons - 'exit' and 'connect'.
	 */
	MAIN_MENU(new int[] {Keyboard.KEY_ESCAPE}, true),

	/**
	 * The connection window. Mouse handling is disabled in this state, as the 'escape' key returns the user to the
	 * main menu and the 'enter' key attempts to connect the user to the server at the entered address. All keys are
	 * handled in this state, but only valid address characters are accepted into the text box.	 	
	 */
	CONNECT(new int[] {-1}, false),

	/**
	 * The game state. This is where it all goes down.
	 */
	GAME(new int[] {Keyboard.KEY_ESCAPE, Keyboard.KEY_W, Keyboard.KEY_A,
			Keyboard.KEY_S, Keyboard.KEY_D, Keyboard.KEY_1, Keyboard.KEY_2,
			Keyboard.KEY_3, Keyboard.KEY_4, Keyboard.KEY_5, Keyboard.KEY_SPACE,
			Keyboard.KEY_R, Keyboard.KEY_TAB}, true);

	/**
	 * Rather self explanatory - if 'true', denotes a state that handles input from the mouse
	 * (applies to all states but the intro).
	 */
	public final boolean registerMouseInput;

	/**
	 * The keys from which to register input
	 */
	public final int[] importantKeys;

	/**
	 * Enum constant constructor
	 * @param importantKeys The {@link #importantKeys}
	 * @param registerMouseInput Whether to {@link #registerMouseInput}
	 */
	StateType(int[] importantKeys, boolean registerMouseInput) {
		this.importantKeys = importantKeys;
		this.registerMouseInput = registerMouseInput;
	}
}