package com.veltro.blazingbarrels.game.weapons;

/**
 * 
 * @author deager4
 *
 */
public class Weapon 
{

	/**
	 * The {@link Weapons} type of the {@link Weapon}
	 */
	private Weapons weaponType;
	
	/**
	 * the ammound of ammo currently in the weapon
	 */
	private int currentAmmo;
	
	/**
	 * the current heat level of the weapon
	 */
	private double heatLevel;
	
	/**
	 * is the Weapon out of juice?
	 */
	private boolean isEmpty;
	
	/**
	 * Is the weapon overheated
	 */
	private boolean isOverheated;
	
	/**
	 * The Super Constructor of all Weapons
	 * @param weaponType The {@link Weapons} of the {@link Weapon}
	 */
	public Weapon(Weapons weaponType)
	{
		this.weaponType = weaponType;
		isEmpty = false;
		currentAmmo = weaponType.getMagazineCapacity();
		isOverheated = false;
		heatLevel = 0.0;
	}
	
	/**
	 * makes the weapon go boom!!!
	 */
	public void fire()
	{
		if(isEmpty)
		{
			weaponType.getDryFireSound().play(6);
		}
		if(isOverheated)
		{}
		else
		{
			currentAmmo -=1;
			heatLevel += 0.5;
			weaponType.getFireSound().play(6);
			if(currentAmmo == 0)
			{
				isEmpty = true;
			}
			if(heatLevel >= 10.0)
			{
				isOverheated = true;
			}
		}
	}
	
	/**
	 * 
	 * @return the weapon type of the weapon
	 */
	public Weapons getWeaponType()
	{
		return this.weaponType;
	}
	
	/**
	 * reloads the weapon to its maximum ammo capacity
	 */
	public void reload()
	{
		this.currentAmmo = this.weaponType.getMagazineCapacity();
		isEmpty = false;
		this.weaponType.getReloadSound().play(6);
	}
	
	/**
	 * cools the weapon down, .5 a tick
	 */
	public void coolDown()
	{
		heatLevel -= 0.5;
		if(heatLevel == 0.0)
		{
			isOverheated = false;
		}
	}
	
	/**
	 * 
	 * @return the ammount of ammo currently in the weapon
	 */
	public int getCurrentAmmo()
	{
		return this.currentAmmo;
	}
	
	/**
	 * Calculates damage from a weapon at a certain distance from the center of the blast, takes into account coer
	 * @param distanceFromBlast the distance from the blast, in meters, that the player is at
	 * @param behindCover Is the player behind cover?
	 * @return The damage as an INT
	 */
	public int calculateDamage(double distanceFromBlast, boolean behindCover)
	{
		return weaponType.getDamageType().getDamageAmmount(distanceFromBlast, behindCover);
	}
	
}
