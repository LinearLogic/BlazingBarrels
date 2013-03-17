package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * This packet is sent to a client to provide it with all the information it needs to create a local copy of the game
 * world and all its contents. Due to size limit on the {@link BBPacket} payload, the server will occasionally send
 * multiple instances of this packet in order to provide a complete snapshot.<p>
 * 
 * This packet is only ever received by the client.
 * 
 * @author LinearLogic
 * @since 0.3.0
 */
public class Packet10ServerSnapshot extends BBPacket {

	/**
	 * The horizontal radius, in pixels, of the game world (-1 if an updated world radius was not sent in this packet)
	 */
	private int worldRadius;

	/**
	 * The maximum value for a player's health (-1 if an updated health cap was not set in this packet)
	 */
	private int healthCap;

	/**
	 * An Array of strings containing data for each player on the server, used to update the client's copy of each
	 * player in the game world
	 */
	private String[] playerInfoStrings;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (10), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param worldRadius The horizontal radius, in pixels, of the game world
	 * @param healthCap The maximum value for a player's health
	 * @param playerSnapshots A concatenation of strings containing snapshots of players in the game world
	 * @param address The IP address of the server that sent this packet
	 * @param port The port on the above address
	 */
	public Packet10ServerSnapshot(int worldRadius, int healthCap, String playerSnapshots, InetAddress address, int port) {
		super(10, (worldRadius > -1 ? worldRadius + " " : "") + (healthCap > -1 ? healthCap + " ": "") +
				playerSnapshots, address, port);
		this.worldRadius = worldRadius;
		this.healthCap = healthCap;
		playerInfoStrings = playerSnapshots.trim().split("\\s+");
	}

	public void handle() {
		// TODO
	}

	/**
	 * @return The server's {@link #worldRadius}
	 */
	public int getWorldRadius() {
		return worldRadius;
	}

	/**
	 * @return The server's {@link #healthCap}
	 */
	public int getHealthCap() {
		return healthCap;
	}

	/**
	 * @return {@link #playerInfoStrings player info strings}
	 */
	public String[] getPlayerSnapshots() {
		return playerInfoStrings;
	}
}