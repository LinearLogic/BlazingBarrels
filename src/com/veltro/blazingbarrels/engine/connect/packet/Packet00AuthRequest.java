package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * The packet sent by the client when attempting to authorize on a server in order to join and play. It contains the
 * username and password entered in the {@link ConnectState}. If the password matches the server's password or if the
 * server does not have a password, the server will send a positive response; otherwise, the client receives a negative
 * one. In both cases, the server sends a {@link Packet01AuthResponse} to the client.
 * <p>
 * This packet is only ever sent by the client.
 * 
 * @author LinearLogic
 * @since 0.2.8
 */
public class Packet00AuthRequest extends BBPacket {

	/**
	 * The username of the player attempting to log on to a BBServer instance to play
	 */
	private String username;

	/**
	 * The password submitted by the player attempting authentication. If this password matches that of the
	 * server being connected to, then the player is allowed to join and play.
	 */
	private String password;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (0), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param username An account's {@link #username}
	 * @param password The {@link #password}, which in the event of successful authentication will match the password
	 * of the server
	 * @param address The IP address of the server the client on which the client is attempting authorization
	 * @param port The port on the above address
	 */
	public Packet00AuthRequest(String username, String password, InetAddress address, int port) {
		super(0, username + (password.equals("") || password == null ? "" : " " + password), address, port);
		this.username = username;
		this.password = password;
	}

	/**
	 * This packet is never received by the server, so it is not handled.
	 */
	public void handle() { }

	/**
	 * @return The {@link #username} of the player attempting authentication with this packet
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return The {@link #password} given by the player attempting authentication with this packet
	 */
	public String getPassword() {
		return password;
	}
}
