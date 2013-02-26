package com.veltro.blazingbarrels.game.state;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.veltro.blazingbarrels.engine.graphics.Camera3D;
import com.veltro.blazingbarrels.game.player.Player;

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
	 * if true, option menu is active
	 */
	private boolean optionMenuActive;
	
	/**
	 * The player...
	 */
	private Player player;

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
		if(Keyboard.isKeyDown(Keyboard.KEY_R) && !keyDown)
		{
			player.reloadCurrentWeapon();
		}
		if(K
		if(Mouse.isButtonDown(0))
		{
			player.fireCurrentWeapon();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) && !keyDown)
		{
			Location3D 
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && !keyDown)
		{
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && !keyDown)
		{
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D) && !keyDown)
		{
			
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && !keyDown)
		{
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !keyDown) 
		{
			if (optionMenuActive)
			{
				optionMenu.close();
				Mouse.setGrabbed(true);
				return;
			} 
			else 
			{
				openOptionMenu();
				Mouse.setGrabbed(false);
				return;
			}
		}
		camera.handleMouseInput();
		camera.handleKeyboardInput();
	}

	@Override
	public void draw() {
		camera.updatePosition();
		camera.draw();
	}
	
	/**
	 *  set player
	 * @param player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/**
	 * gets that thar player
	 * @return
	 */
	public Player getPlayer()
	{
		return this.player;
	}
	
	private void openOptionMenu()
	{
		
	}
}
