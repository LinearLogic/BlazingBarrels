package com.veltro.blazingbarrels.game.player;

public enum PlayerState
{
	
	DEAD(true, true, false, false, false, true),
	ALIVE(false, true, false, true, true, false),
	SPECTATOR(false, false, true, false, false, true),
	WAITING(false, false, false, false, false, true);

	/**
	 * is the Player dead (honestly this should be obvious
	 */
	private boolean isDead;
	
	/**
	 * Is the Player currently playing the game
	 */
	private boolean isPlaying;
	
	/**
	 * is the player a spectator
	 */
	private boolean isSpectator;
	
	/**
	 * is the player vulnerable
	 */
	private boolean isVulnerable;
	
	/**
	 * can other players see the player
	 */
	private boolean isVisible;
	
	/**
	 * Can the player speak in chat?
	 */
	private boolean isSilenced;
	
	
	/**
	 * Determines what a player is able to do...
	 * @param isDead
	 * @param isPlaying
	 * @param isSpectator
	 * @param isVulnerable
	 * @param isVisible
	 * @param isSilenced
	 */
	PlayerState(boolean isDead, boolean isPlaying, boolean isSpectator, boolean isVulnerable, boolean isVisible, boolean isSilenced)
	{
		this.isDead = isDead;
		this.isPlaying = isPlaying;
		this.isSpectator = isSpectator; 
		this.isVulnerable = isVulnerable;
		this.isVisible = isVisible;
		this.isSilenced = isSilenced;
	}
	
	
}
