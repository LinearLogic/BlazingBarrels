package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;

import com.veltro.blazingbarrels.BlazingBarrels;

/**
 * The MainMenuState runs a background animation while providing the user with two
 * navigation buttons: one to connect to a server and one to exit the program.
 * 
 * @author LinearLogic
 * @since 0.0.3
 *
 */
public class MainMenuState extends State {

	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#MAIN_MENU} type.
	 */
	public MainMenuState() {
		super(StateType.MAIN_MENU);
		keyDown = true;
	}

	@Override
	public void initialize() {
		keyDown = true;
	}

	@Override
	public void handleInput() {
		checkKeyStates();
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !keyDown)
			BlazingBarrels.exitGameLoop(false);
		// TODO: add mouse handling for the selection of the 'connect' and 'quit' buttons
	}

	@Override
	public void draw() {
		
	}
}
