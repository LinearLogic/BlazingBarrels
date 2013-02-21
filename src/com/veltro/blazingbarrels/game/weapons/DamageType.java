package com.veltro.blazingbarrels.game.weapons;

/**
 * Does all damage calculation and property management
 * @author deager4
 * @since 0.1.15
 *
 */
public enum DamageType 
{
	ROCKET_EXPLOSION(15, 50, true, false, true, false, true),
	GRENADE_EXPLOSION(9, 20, true, false, false, true, true),
	MINIGUN_HIT(0, 1, false, false, true, false, false),
	LASER_HIT(0, 5, false, true, false, true, false),
	NUKE_EXPLOSION(1000, 9001, true, true, false, false, false),
	AIRSTRIKE_EXPLOSION(30, 100, true, false, true, true, true),
	EXPLOSIVE_BARREL(9, 15, true, false, false, false, false);
	
	
	/**
	 * The radius of the blast (if applicable) in meters, set to -1 if no explosive effects
	 */
	private int blastRadius;
	
	/**
	 * The amount of damage that a player will accrue if they are standing at the epicenter of the damage cloud in health ticks, ie. 0 < x < 100
	 */
	private int damageAmmount;
	
	/**
	 * Is the damage type explosive
	 */
	private boolean isExplosive;
	
	/**
	 * Does it go through destructable cover? if it does not go through cover, it will do extra damage
	 */
	private boolean isArmorPircing;
	
	/**
	 * Does it spew out hot, deadly, pointy pieces of metal?
	 */
	private boolean hasShraphnel;
	
	/**
	 * Does it turn things into plasma?
	 */
	private boolean isEnergyWeapon;
	
	/**
	 * Does it actually fire a solid object, ex. rocket, bullets
	 */
	private boolean isProjectileWeapon;
	/**
	 * 
	 * @param blastRadius the radius of the blast in meters
	 * @param damageAmmount the amount of damage that a player will take at the epicenter of the radius, in health ticks
	 * @param isExplosive do I even need to explain this?
	 * @param isEnergyWeapon Does it go boom?
	 * @param isProjectileWeapon does face melt?
	 * @param isArmorPircing ""
	 * @param hasShraphnel ""
	 */
	DamageType(int blastRadius, int damageAmmount, boolean isExplosive, boolean isEnergyWeapon, boolean isProjectileWeapon, boolean isArmorPircing, boolean hasShraphnel)
	{
		this.blastRadius = blastRadius; 
		this.damageAmmount = damageAmmount;
		this.isExplosive = isExplosive;
		this.isArmorPircing = isArmorPircing;
		this.hasShraphnel = hasShraphnel;
		this.isEnergyWeapon = isEnergyWeapon;
		this.isProjectileWeapon = isProjectileWeapon;
	}
	
	/**
	 * Calculates the ammount of damage a player would take taking into consideration both cover, and distance from shot
	 * @param distanceFromCenter The distance from the epicenter of the blast, in meters
	 * @param isBehindCover are you behind something solid?
	 * @return
	 */
	public int getDamageAmmount(double distanceFromCenter, boolean isBehindCover)
	{
		if(distanceFromCenter > this.blastRadius)
		{
			return 0;
		}
		if(this.isExplosive)
		{
			if(this.isEnergyWeapon)
			{
				if(this.hasShraphnel)
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter)+1 == 0)
							{
								return this.damageAmmount;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1)); //no shraphnel damage because they are behind cover;
						}
						else
						{
							if(Math.log(distanceFromCenter)+1 == 0)
							{
								return this.damageAmmount + 7; 
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1)) + 7; //the damage is +7 because of the +5 from non sheilded armor-pircing, and the +2 from shraphnel damage
						}
					}
					else
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter)+1 == 0)
							{
								return this.damageAmmount; //no shraphnel damage because behind cover
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
						}
						else
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount + 2; // +2 because of shraphnel damage
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1)) + 2;
						}
					}
				}
				else
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount;
							}
							return (int)(this.damageAmmount/ ((Math.log(distanceFromCenter)) +1 ));
						}
						else
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount + 5;
							}
							return (int)(this.damageAmmount/ ((Math.log(distanceFromCenter)) +1)) + 5;
						}
					}
					else
					{
						if(Math.log(distanceFromCenter) +1 == 0)
						{
							return this.damageAmmount;
						}
						return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
					}
				}
			}
			else if(this.isProjectileWeapon)
			{
				if(this.hasShraphnel)
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter) + 1 == 0)
							{
								return this.damageAmmount;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
						}
						else
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount + 7;
							}
							return (int)(this.damageAmmount/ ((Math.log(distanceFromCenter)) + 1)) + 7;
						}
					}
					else
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter) + 1 == 0)
							{
								return this.damageAmmount;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
						}
						else
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount + 2;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1)) +2;
						}
					}
				}
				else
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
						}
						else
						{
							if(Math.log(distanceFromCenter) +1 == 0)
							{
								return this.damageAmmount + 5;
							}
							return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1)) + 5;
						}
					}
					else
					{
						if(Math.log(this.damageAmmount) +1 == 0)
						{
							return this.damageAmmount;
						}
						return (int)(this.damageAmmount/((Math.log(distanceFromCenter)) +1));
					}
				}
			}
			else
			{
				if(Math.log(this.damageAmmount) +1 ==0)
				{
					return this.damageAmmount;
				}
				return (int)(this.damageAmmount/((Math.log(distanceFromCenter))+1));
			}
		}
		else
		{
			if(this.isEnergyWeapon)
			{
				if(this.hasShraphnel)
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							return this.damageAmmount;
						}
						else
						{
							return this.damageAmmount + 7;
						}
					}
					else
					{
						return this.damageAmmount +2;
					}
				}
				else
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							return this.damageAmmount;
						}
						else
						{
							return this.damageAmmount + 5;
						}
					}
					else
					{
						return this.damageAmmount;
					}
				}
			}
			else if(this.isProjectileWeapon)
			{
				if(this.hasShraphnel)
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							return this.damageAmmount;
						}
						else
						{
							return this.damageAmmount + 7;
						}
					}
					else
					{
						return this.damageAmmount + 2;
					}
				}
				else
				{
					if(this.isArmorPircing)
					{
						if(isBehindCover)
						{
							return this.damageAmmount;
						}
						else
						{
							return this.damageAmmount + 5;
						}
					}
					else
					{
						return this.damageAmmount + 2;
					}
				}
			}
			else
			{
				return this.damageAmmount;
			}
		}
		
	}
}
