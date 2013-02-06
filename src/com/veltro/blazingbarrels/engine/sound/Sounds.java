package com.veltro.blazingbarrels.engine.sound;

import java.io.File;
import java.io.IOException;

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

	// Music {LL}(these clips tend to be the long ones){D}(indeed friend)
	INTRO_MUSIC("Music\\Intro Music.wav"),
	MAIN_MENU_MUSIC("Music\\Main Menu Music.wav"),
	TROLOLOL_SONG_FULL("Music\\Trololol.wav"),

	// Sounds
	DEATH_SCREAM("Sounds\\Death Scream.wav"),   //a really really bad death scream
	LAUGH("Sounds\\Laugh.wav"),
	OVER_NINE_THOUSAND("Sounds\\Over Nine Thousand.wav"),
	KILLING_SPREE("Sounds\\Killing Spree.wav"), // Killing Spree!!!!!!!
	COMBO_BREAKER("Sounds\\Combo Breaker.wav"),
	SICK_DUDE("Sounds\\Sick Dude.wav"),
	YO_DAWG_I_HEARD_YOU_LIKE_BULLETS("Sounds\\Yo Dawg I Heard You Like Bullets.wav"),
	WAT("Sounds\\Wat.wav"),
	OLD_SPICE("Sounds\\Old Spice.wav"),
	THAT_ESCALATED_QUICKLY("Sounds\\That Escalated Quickly.wav"),
	NUKE("Sounds\\Nuke.wav"),
	AIRSTRIKE("Sounds\\Airstrike.wav"),
	EXPLOSIVE_BARREL("Sounds\\Explosive Barrel.wav"),
	LASER_CANNON("Sounds\\Laser Cannon.wav"),     //Laser Cannon firing
	RAIL_GUN("Sounds\\Rail Gun.wav"),
	MINI_GUN("Sounds\\Mini Gun.wav"),
	IMA_FIRIN_MY_LASER("Sounds\\Ima Firin My Laser.wav"),
	MINI_GUN_DRY_FIRE("Sounds\\Mini Gun Dry Fire.wav"),
	MINI_GUN_RELOAD("Sounds\\Mini Gun Reload.wav"),
	WEAPON_OVERHEAT("Sounds\\Weapon Overheat.wav"),
	ROCKET_LAUNCHER("Sounds\\Rocket Launcher.wav"),
	LASER_HIT("Sounds\\Laser Hit.wav"),
	ROCKET_HIT("Sounds\\Rocket Hit.wav"),
	BULLET_HIT("Sounds\\Bullet Hit.wav"),
	TROLOLOL("Sounds\\Trololol.wav"),
	BUTTON_ON("Sounds\\BUTTON ON.wav"),
	BUTTON_OFF("Sounds\\BUTTON OFF.wav");

	/**
	 * The Sounds value's unique audio clip, played using the {@link #play(float)} method.
	 */
	private Clip clip;

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
	}
}

