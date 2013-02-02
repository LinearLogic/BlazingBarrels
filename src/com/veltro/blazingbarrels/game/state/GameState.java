package com.veltro.blazingbarrels.game.state;

/**
 * The GameState contains all of the input handling and logic for the game itself,
 * a 3-D, multiplayer first person shooter.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class GameState extends State {

	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#GAME} type.
	 */
	public GameState() {
		super(StateType.GAME);
		keyDown = true;
	}

	@Override
	public void initialize() {
		keyDown = true;
	}

	@Override
	public void handleInput() {
		checkKeyStates();
//		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !keyDown) {
//			if (optionMenuActive) {
//				close option menu
//				Mouse.setGrabbed(true);
//				return;
//			} else {
//				open option menu
//				Mouse.setGrabbed(false);
//				return;
//			}
//		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

}
