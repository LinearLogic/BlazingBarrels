package com.veltro.blazingbarrels.engine.sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
   
/**
 * The Music enum contains various audio clips that can be played at range of volumes.
 * 
 * @author deager4, LinearLogic
 * @since 0.1.4
 */
public enum Music {

	// Music {LL}(these clips tend to be the long ones){D}(indeed friend)
	INTRO_MUSIC("Music" + File.separator + "Intro Music.wav"),
	MAIN_MENU_MUSIC("Music" + File.separator + "Main Menu Music.wav"),
	TROLOLOL_SONG_FULL("Music" + File.separator + "Trololol.wav");

	/**
	 * The Sounds value's unique audio clip, played using the {@link #play(float)} method.
	 */
	private Clip clip;
	
	/**
	 * An ArrayList of all of the clips that are currently playing
	 */
	private static ArrayList<Clip> playingClips = new ArrayList<Clip>();

	/**
	 * Constructor - initializes each enum value, loading its clip from the sound file at the provided location.
	 * 
	 * @param soundFileLocation The system path to the sound file containing the audio clip
	 */
	Music(String soundFileLocation) {
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileLocation).getAbsoluteFile());
			AudioSystem.getClip().open(audioInputStream);
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
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stops all currently playing clips
	 * 
	 * @author deager4
	 */
	public static void stopAll()
	{
		for(int count = 0; count < playingClips.size(); count ++)
			playingClips.get(count).stop();
	}
}
