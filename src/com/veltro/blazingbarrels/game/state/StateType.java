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
	INTRO(new int[] {Keyboard.KEY_RETURN}, false),

	/**
	 * The main menu state. Pressing the 'escape' key quits the game.
	 * This state contains two rather self-explanatory buttons - 'exit' and 'connect'.
	 */
	MAIN_MENU(new int[] {Keyboard.KEY_ESCAPE}, true),

	/**
	 * The connection window. All keys are available in this state, and the mouse is handled, as it is used to click
	 * the 'connect' and 'cancel' navigation buttons at the bottom of the window.
	 */
	CONNECT(new int[] {Keyboard.KEY_ESCAPE, Keyboard.KEY_RETURN, Keyboard.KEY_0,
			Keyboard.KEY_1, Keyboard.KEY_2, Keyboard.KEY_3, Keyboard.KEY_4,
			Keyboard.KEY_5, Keyboard.KEY_6, Keyboard.KEY_7, Keyboard.KEY_8,
			Keyboard.KEY_9, Keyboard.KEY_PERIOD, Keyboard.KEY_SEMICOLON,
			Keyboard.KEY_BACK}, false),

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

	public String toString() {
		switch(this) {
			case INTRO:
				return "Loading Screen";
			case MAIN_MENU:
				return "Main Menu";
			case CONNECT:
				return "Connection Window";
			case GAME:
				return "Game";
			default: // This code should never be reached...
				return "";
		}
	}
}
