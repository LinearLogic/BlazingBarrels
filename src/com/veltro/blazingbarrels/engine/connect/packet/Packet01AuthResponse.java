package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * This packet is sent to a client from a BBServer instance in response to the client's sending a
 * {@link Packet00AuthRequest} to attempt authorization in order to join the server. This packet contains the server's
 * verdict, and if the user was successfully authorized, the server will now wait for the client to send a
 * {@link Packet20PlayerJoin} to begin interaction with the server.<p>
 * 
 * This packet is only ever received by the client.
 * 
 * @author LinearLogic
 * @since 0.2.9
 */
public class Packet01AuthResponse extends BBPacket {

	/**
	 * The username of the account that attempted authorization on a BBServer instance and is now receiving its verdict
	 */
	private String username;

	/**
	 * The user's authorization verdict - 'true' if authorized, else false
	 */
	private boolean authorized;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (1), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param username An player's {@link #username}
	 * @param authorizationVerdict 'true' if the player was successfully authorized (ie. if the password was accepted),
	 * else 'false'
	 * @param address The IP address from which the packet was sent
	 * @param port The port on the above address
	 */
	public Packet01AuthResponse(String username, boolean authorizationVerdict, InetAddress address, int port) {
		super(1, "username " + (authorizationVerdict ? "1" : "0"), address, port);
		this.username = username;
		authorized = authorizationVerdict;
	}

	public void handle() {
		// TODO
	}

	/**
	 * @return The {@link #username} associated with the authorizations      verdict received from the server
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Whether or not the user was successfully authorized to play on the server he/she tried to connect to
	 */
	public boolean isAuthorized() {
		return authorized;
	}
}
