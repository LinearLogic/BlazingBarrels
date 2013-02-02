package com.veltro.blazingbarrels;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.veltro.blazingbarrels.Sounds.Volume;

/**
 * This Does all of the music for the game, yay
 * @author deager4
 * @since 0.0.4
 * 
 *
 */
public enum Music {
	
	INTRO_MUSIC("Music\\Intro Music.wav"),
	MAIN_MENU_MUSIC("Music\\Main Menu Music.wav"),
	TROLOLOL("Music\\Trololol.wav");
	
 // Nested class for specifying volume
   public static enum Volume {
      MUTE, 
      LOW,
      MEDIUM, 
      HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   Music(String musicFileName) {
      try {
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(musicFileName).getAbsoluteFile());
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
      if (volume != Volume.MUTE) 
      {
         if (clip.isRunning())
         {
            clip.stop();   // Stop the player if it is still running
         }
         clip.setFramePosition(0); // rewind to the beginning
         clip.start();     // Start playing
         clip.loop(clip.LOOP_CONTINUOUSLY);
      }
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() 
   {
      values(); // calls the constructor for all the elements
   }
}
