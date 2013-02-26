package com.veltro.blazingbarrels.game.player;

import java.io.File;

import com.veltro.blazingbarrels.game.weapons.Weapon;

public class Player 
{
	/**
	 * The weapon that the player has when they next respawn
	 */
	private Weapon futureWeapon;
	
	/**
	 * the current state of the player 
	 */
	private PlayerState playerState;
	
	/**
	 * the Name of the player
	 */
	private String playerName;
	
	/**
	 * the location of the player
	 */
	private Location3D playerLocation;
	
	/**
	 * the weapon the player is currently using
	 */
	private Weapon currentWeapon;
	
	/**
	 * the health of the player, an int from 0 - 100
	 */
	private int playerHealth;
	
	/**
	 * the location of the skin of the player
	 */
	private File playerSkin;
	
	/**
	 * This creates a player
	 * @param playerName the name of the player, as seen by others, server, etc
	 * @param playerInitialLocation the location that the player will start at
	 * @param playerSkinLocation the location of the skin image for the player's vehicle
	 */
	public Player(String playerName, float[] playerInitialLocation, File playerSkinLocation)
	{
		this.playerName = playerName;
		this.playerLocation = playerInitialLocation;
		this.playerSkin = playerSkinLocation;
		playerHealth = 100;
	}
	
	/**
	 * spawns the player, with 100 health, and ALIVE
	 */
	public void spawn()
	{
		playerState = PlayerState.ALIVE;
		playerHealth = 100;
		//TODO: do some shit here to make it so that the player will be spawned at the initial location
	}
	
	/**
	 * returns the 3d location of the player 
	 * @return the {@link playerLocation} of the player
	 */
	public float[] getPlayerLocation()
	{
		return this.playerLocation;
	}
	
	/**
	 * updates the location of the player
	 * @param newLocation the new float[] location of the player
	 */
	public void updateLocation(float[] newLocation)
	{
		this.playerLocation = newLocation;
	}
	
	/**
	 * smites the player, removing all of their life, and setting them to DEAD
	 * Also displays a message when they die
	 */
	public void kill(String deadMessage)
	{
		this.playerHealth = 0;
		playerState = PlayerState.DEAD;
		this.setCurrentWeapon(futureWeapon);
	}
	
	/**
	 * kills a player, removing all life, and sets them to dead
	 */
	public void kill()
	{
		this.playerHealth = 0;
		playerState = PlayerState.DEAD;
		this.setCurrentWeapon(futureWeapon);
	}
	
	/**
	 * returns the current weapon of the player
	 * @return {@link currentWeapon}
	 */
	public Weapon getCurrentWeapon()
	{
		return this.currentWeapon;
	}
	
	/**
	 * 
	 * @param weapon the new weapon type of the player
	 */
	public void setCurrentWeapon(Weapon weapon)
	{
		this.currentWeapon = weapon;
	}
	
	public void setFutureWeapon(Weapon weapon)
	{
		this.futureWeapon = weapon;
	}
	
	public int getPlayerHealth()
	{
		return this.playerHealth;
	}
	
	public void healPlayer(int healthAmmount)
	{
		if(this.playerHealth + healthAmmount >= 100)
		{
			this.playerHealth = 100;
		}
		else
		{
			this.playerHealth += healthAmmount;
		}
	}
	
	/**
	 * 
	 * @param damageAmmount the ammount of damage you wish to deal to a player
	 */
	public void damagePlayer(int damageAmmount)
	{
		if(this.playerHealth - damageAmmount <= 0)
		{
			this.playerHealth = 0;
			this.kill();
		}
		else
		{
			this.playerHealth -= damageAmmount;
		}
	}
	
	public void reloadCurrentWeapon()
	{
		this.currentWeapon.reload();
	}
	
	public void fireCurrentWeapon()
	{
		this.currentWeapon.fire();
	}
	
	public File getSkin()
	{
		return this.playerSkin;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public PlayerState getPlayerState()
	{
		return this.playerState;
	}
}
