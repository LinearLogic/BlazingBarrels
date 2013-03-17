package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * This packet is sent by a BBServer instance to the client if the client's current user account has been successfully
 * authorized but the client has not sent a {@link Packet20PlayerJoin} to begin playing on the server and a certain
 * amount of time has passed. If no response from the client has been received after sending a specified number of 
 * these packets, the user's account will be deauthorized on the server, meaning that a new login handshake will have
 * to occur before the client can rejoin the server.<p>
 * 
 * This packet is only ever received by the client.
 * 
 * @author LinearLogic
 * @since 0.2.10
 */
public class Packet02DeauthWarning extends BBPacket {

	/**
	 * The username of the account that will be deauthorized if no response to this packet is received by the server
	 */
	private String username;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (2), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param username The username of the player in jeopardy of deauthorization (used by the client to make sure the
	 * packet isn't meant for someone else)
	 * @param address The IP address of the server warning the client of impending deauthorization
	 * @param port The port on the above address
	 */
	public Packet02DeauthWarning(String username, InetAddress address, int port) {
		super(2, username, address, port);
		this.username = username;
	}

	public void handle() {
		// TODO
	}

	/**
	 * @return The {@link #username} associated with this deauthorization warning
	 */
	public String getUsername() {
		return username;
	}
}
