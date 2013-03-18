package com.veltro.blazingbarrels.game.state;

import com.veltro.blazingbarrels.engine.graphics.Camera3D;

/**
 * The GameState contains all of the input handling and logic for the game itself,
 * a 3-D, multiplayer first person shooter.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class GameState extends State {

	/**
	 * The {@link Camera3D camera} used to view the game world
	 */
	private Camera3D camera;
	
	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#GAME} type.
	 */
	public GameState() {
		super(StateType.GAME);
		keyDown = true;
	}

	@Override
	public void initialize() 
	{
		camera = new Camera3D();
		camera.useView();
		keyDown = true;
	}

	@Override
	public void handleInput() {
		checkKeyStates();
		camera.handleMouseInput();
		camera.handleKeyboardInput();
	}

	@Override
	public void draw() {
		camera.updatePosition();
		camera.draw();
	}
}
