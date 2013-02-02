package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;

import com.veltro.blazingbarrels.BlazingBarrels;

/**
 * The connection state provides the user with a textbox into which an IP address can be entered.
 * Pressing 'enter' will then attempt to connect the client to the server at the specified address,
 * and if the connection is successful, the program will switch to the {@link GameState}. A future
 * update will add an editable list of servers, as well as support for text-based domain names.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class ConnectState extends State {

	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#CONNECT} type.
	 */
	public ConnectState() {
		super(StateType.CONNECT);
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
			BlazingBarrels.setCurrentState(StateType.MAIN_MENU, true);
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !keyDown) {
			// TODO: try to connect to the provided address. If successful, return StateType.GAME
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
