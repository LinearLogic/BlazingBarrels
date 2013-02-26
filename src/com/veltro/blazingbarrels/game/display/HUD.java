package com.veltro.blazingbarrels.game.display;

import com.veltro.blazingbarrels.game.player.Player;
import com.veltro.blazingbarrels.game.weapons.Weapon;

public class HUD 
{
	private Player player;
	private Weapon currentWeapon; 
	private String playerName;
	private int currentWeaponAmmo;
	private int playerHealth;
	private double currentWeaponOverheatLevel;
	
	public HUD(Player player)
	{
		this.player = player;
		this.currentWeapon = player.getCurrentWeapon();
		this.playerName = player.getPlayerName();
		this.currentWeaponAmmo = currentWeapon.getCurrentAmmo();
		this.playerHealth = player.getPlayerHealth();
		this.currentWeaponOverheatLevel = currentWeapon.getWeaponOverheatLevel();
	}
	
	public void refresh()
	{
		
	}
}
