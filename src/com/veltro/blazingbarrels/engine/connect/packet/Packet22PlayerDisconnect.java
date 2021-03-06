package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * This packet is sent by a client upon voluntarily disconnecting from a server, or from a server to notify clients
 * that a player has disconnected. In the latter case, this packet includes the reason for the disconnect.<p>
 * 
 * This packet is both sent and received by the client.
 * 
 * @author LinearLogic
 * @since 0.3.4
 */
public class Packet22PlayerDisconnect extends BBPacket {

	/**
	 * The name of the player who is disconnecting (or being disconnected) from the server 
	 */
	private String username;

	/**
	 * An integer ID representing the reason for the player's disconnect. If the player voluntarily disconnected, the
	 * ID value is 0. If the player disconnected due to a network timeout, the ID value is 1. If the player was kicked
	 * from the server, the ID value is 2.
	 */
	private int reasonID;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (22), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param username The username of the player disconnecting from the server
	 * @param reason The {@link #reasonID} of the disconnect
	 * @param address The IP address of the server that sent this packet
	 * @param port The port on the above address
	 */
	public Packet22PlayerDisconnect(String username, int reasonID, InetAddress address, int port) {
		super(22, username + " " + reasonID, address, port);
		this.username = username;
		this.reasonID = reasonID;
	}

	public void handle() {
		// TODO
	}

	/**
	 * @return The name of the player disconnecting from the server
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return The {@link #reason} for the disconnection
	 */
	public int getReasonID() {
		return reasonID;
	}
}
