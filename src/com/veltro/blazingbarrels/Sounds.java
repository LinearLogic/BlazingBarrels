package com.veltro.blazingbarrels;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
   
/**
 * @author deager4
 * @since 0.0.4
 */
public enum Sounds {
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
   BUTTON_ON("Sounds\\Button On.wav"),
   BUTTON_OFF("Sounds\\Button Off.wav");
   
   
   // Nested class for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   Sounds(String soundFileName) {
      try {
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
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
      }
   }
   
   // Optional static method to pre-load all the sound files.
   static void init() 
   {
      values(); // calls the constructor for all the elements
   }
}