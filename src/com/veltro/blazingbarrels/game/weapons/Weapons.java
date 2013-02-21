package com.veltro.blazingbarrels.game.weapons;

import java.io.File;

import com.veltro.blazingbarrels.engine.sound.Sounds;

public enum Weapons
{
	MINIGUN(Sounds.MINI_GUN, Sounds.MINI_GUN_DRY_FIRE, Sounds.MINI_GUN_RELOAD, Sounds.WEAPON_OVERHEAT, Sounds.BULLET_HIT, (Some texture), (Some filelocation), 200, 5, 5, 9, DamageType.MINIGUN_HIT),
	LASER_CANNON(Sounds.LASER_CANNON, Sounds.MINI_GUN_DRY_FIRE, (Need a reloadSound), Sounds.WEAPON_OVERHEAT, Sounds.BULET_HIT, (Some texture), (Some filelocation), 100, 2, 3, 11, DamageType.LASER_HIT),
	//TODO: GRENADE()
	NUKE(Sounds.NUKE, (Some texture), (Some fileLocation), 1, DamageType.NUKE_EXPLOSION),
	AIRSTRIKE(Sounds.AIRSTRIKE, (Some texture), (Some filelocation), 1, DamageType.AIRSTRIKE_EXPLOSION),
	ROCKET(Sounds.ROCKET_LAUNCHER, Sounds.MINI_GUN_DRY_FIRE, Sounds.MINI_GUN_RELOAD, Sounds.WEAPON_OVERHEAT, Sounds.ROCKET_HIT, (Some texture), (Some filelocation), 2, 1, 8, 10, DamageType.ROCKET_EXPLOSION);
	/**
	 * the Sound that plays while the weapon fires
	 */
	private Sounds firingSound;
	
	/**
	 * the sound that plays while the weapon is reloading
	 */
	private Sounds reloadingSound;
	
	/**
	 * the sound that plays when the weapon is fired dry
	 */
	private Sounds dryFire;
	
	/**
	 * the sound that plays if the weapon overheats
	 */
	private Sounds overheatSound;
	
	/**
	 * the sound that plays when something is shot with the weapon
	 */
	private Sounds impactSound;
	
	/**
	 * the texture that the weapon has
	 */
	private Texture texture;
	
	/**
	 * the location of the model file
	 */
	private File fileLocation;
	
	/**
	 * the number of shots in a magazine for this weapon
	 */
	private int magazineCapacity;
	
	/**
	 * the amount of time, in seconds, that it takes to reload the weapon
	 */
	private int reloadTime;
	
	/**
	 * the amount of time, in seconds, it takes to cool the weapon down after a overheat
	 */
	private int cooldownTime;
	
	/**
	 * the type of damage that the weapon does, this will also let you calculate damage
	 */
	private DamageType damageType;
	
	/**
	 * the rate of fire of the weapon, in rounds/second
	 */
	private int rateOfFire;
	
	
	// TODO: download SLICK for texture
	
	/**
	 * 
	 * @param firingSound
	 * @param dryFire
	 * @param reloadingSound
	 * @param overheatSound
	 * @param impactSound
	 * @param texture
	 * @param fileLocation
	 * @param magazineCapacity
	 * @param rateOfFire
	 * @param reloadTime
	 * @param cooldownTime
	 * @param damageType
	 */
	Weapons(Sounds firingSound, Sounds dryFire, Sounds reloadingSound, Sounds overheatSound, Sounds impactSound, Texture texture, File fileLocation, int magazineCapacity, int rateOfFire, int reloadTime, int cooldownTime, DamageType damageType)
	{
		this.firingSound = firingSound;
		this.dryFire = dryFire;
		this.reloadingSound = reloadingSound;
		this.overheatSound = overheatSound;
		this.impactSound = impactSound;
		this.texture = texture;
		this.fileLocation = fileLocation;
		this.magazineCapacity = magazineCapacity;
		this.reloadTime = reloadTime;
		this.cooldownTime = cooldownTime;
		this.damageType = damageType;
		this.rateOfFire = rateOfFire;
	}
	
	/**
	 * Simplified Weapon constructor
	 * @param sound
	 * @param texture
	 * @param fileLocation
	 * @param magazineCapacity
	 * @param damageType
	 */
	Weapons(Sounds sound, Texture texture, File fileLocation, int magazineCapacity, DamageType damageType)
	{
		this.firingSound = sound;
		this.texture = texture;
		this.fileLocation = fileLocation;
		this.damageType = damageType; 
	}
	
	public int getMagazineCapacity()
	{
		return this.magazineCapacity;
	}
	
	public Sounds getDryFireSound()
	{
		return this.dryFire;
	}
	
	public Sounds getFireSound()
	{
		return this.firingSound;
	}
	
	public Sounds getReloadSound()
	{
		return this.reloadingSound;
	}
	
	public Sounds getOverheatSound()
	{
		return this.overheatSound;
	}
	
	public int getRateOfFire()
	{
		return this.rateOfFire;
	}
	
	public int getReloadTime()
	{
		return this.reloadTime;
	}
	
	public int getCooldownTime()
	{
		return this.cooldownTime;
	}
	
	public DamageType getDamageType()
	{
		return this.damageType;
	}
	
}
