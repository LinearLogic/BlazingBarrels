package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;

import com.veltro.blazingbarrels.BlazingBarrels;
import com.veltro.blazingbarrels.engine.sound.Sounds;

/**
 * The IntroState is the program's loading screen, the first to be encountered
 * when the game client is started.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class IntroState extends State {

	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#INTRO} type.
	 */
	public IntroState() {
		super(StateType.INTRO);
		keyDown = true;
	}

	@Override
	public void initialize() {
		keyDown = true;
		Sounds.INTRO_MUSIC.play((float)6);
	}

	@Override
	public void handleInput() {
		checkKeyStates();
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !keyDown) {
			BlazingBarrels.setCurrentState(StateType.MAIN_MENU, true);
		}
	}

	@Override
	public void draw() {
		
	}

}
