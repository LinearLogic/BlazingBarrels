package com.veltro.blazingbarrels.engine.sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
   
/**
 * The Sounds enum contains various audio clips that can be played at range of volumes.
 * 
 * @author deager4, LinearLogic
 * @since 0.0.4
 */
public enum Sounds {



	// Sounds
	DEATH_SCREAM("Sounds" + File.separator + "Death Scream.wav"),   //a really really bad death scream
	LAUGH("Sounds" + File.separator + "Laugh.wav"),
	OVER_NINE_THOUSAND("Sounds" + File.separator + "Over Nine Thousand.wav"),
	KILLING_SPREE("Sounds" + File.separator + "Killing Spree.wav"), // Killing Spree!!!!!!!
	COMBO_BREAKER("Sounds" + File.separator + "Combo Breaker.wav"),
	SICK_DUDE("Sounds" + File.separator + "Sick Dude.wav"),
	YO_DAWG_I_HEARD_YOU_LIKE_BULLETS("Sounds" + File.separator + "Yo Dawg I Heard You Like Bullets.wav"),
	WAT("Sounds" + File.separator + "Wat.wav"),
	OLD_SPICE("Sounds" + File.separator + "Old Spice.wav"),
	THAT_ESCALATED_QUICKLY("Sounds" + File.separator + "That Escalated Quickly.wav"),
	NUKE("Sounds" + File.separator + "Nuke.wav"),
	AIRSTRIKE("Sounds" + File.separator + "Airstrike.wav"),
	EXPLOSIVE_BARREL("Sounds" + File.separator + "Explosive Barrel.wav"),
	LASER_CANNON("Sounds" + File.separator + "Laser Cannon.wav"),     //Laser Cannon firing
	RAIL_GUN("Sounds" + File.separator + "Rail Gun.wav"),
	MINI_GUN("Sounds" + File.separator + "Mini Gun.wav"),
	IMA_FIRIN_MY_LASER("Sounds" + File.separator + "Ima Firin My Laser.wav"),
	MINI_GUN_DRY_FIRE("Sounds" + File.separator + "Mini Gun Dry Fire.wav"),
	MINI_GUN_RELOAD("Sounds" + File.separator + "Mini Gun Reload.wav"),
	WEAPON_OVERHEAT("Sounds" + File.separator + "Weapon Overheat.wav"),
	ROCKET_LAUNCHER("Sounds" + File.separator + "Rocket Launcher.wav"),
	LASER_HIT("Sounds" + File.separator + "Laser Hit.wav"),
	ROCKET_HIT("Sounds" + File.separator + "Rocket Hit.wav"),
	BULLET_HIT("Sounds" + File.separator + "Bullet Hit.wav"),
	TROLOLOL("Sounds" + File.separator + "Trololol.wav"),
	BUTTON_ON("Sounds" + File.separator + "BUTTON ON.wav"),
	BUTTON_OFF("Sounds" + File.separator + "BUTTON OFF.wav");

	/**
	 * The Sounds value's unique audio clip, played using the {@link #play(float)} method.
	 */
	private Clip clip;

	/**
	 * A list of all currently playing clips
	 */
	private static ArrayList<Clip> playingClips = new ArrayList<Clip>();

 	/**
	 * Constructor - initializes each enum value, loading its clip from the sound file at the provided location.
	 * 
	 * @param soundFileLocation The system path to the sound file containing the audio clip
	 */
	Sounds(String soundFileLocation) {
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileLocation).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e = new UnsupportedAudioFileException("The provided file is not an audio file: " + soundFileLocation);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the audio clip from the beginning (stopping and rewinding it if necessary) at the specified volume.
	 * 
	 * @param vol The volume level for the clip, a floating point value between -80 and 6
	 */
	public void play(float vol) {
		if (vol < -80 || vol > 6)
			throw new IllegalArgumentException("Volume must be between 1 and 100, inclusive.");
		if (clip.isRunning())
			clip.stop();
		clip.setFramePosition(0); // rewind the clip
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(vol);
		clip.start();
		playingClips.add(clip);
	}
	
	/**
	 * Stops all currently playing sounds
	 */
	public static void stopAll()
	{
		for(int count = 0; count < playingClips.size(); count ++)
		{
			playingClips.get(count).stop();
		}
	}
}
