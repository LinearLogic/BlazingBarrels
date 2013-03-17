package com.veltro.blazingbarrels.engine.connect.packet;

import java.net.InetAddress;

/**
 * This packet is sent from the client after receiving a positive {@link Packet01AuthResponse} and serves to inform the
 * server of the client's intent to join and play in-game. Upon receiving this packet, the server broadcasts a
 * {@link Packet21PlayerConnect} to enable clients to add the newly joined player to their game worlds. The server also
 * dispatches a {@link Packet10ServerSnapshot} to this client so it can populate its game world.<p>
 * 
 * This packet is only ever sent by the client.
 * 
 * @author LinearLogic
 * @since 0.3.2
 */
public class Packet20PlayerJoin extends BBPacket {

	/**
	 * The username of the player joining the server
	 */
	private String username;

	/**
	 * Whether the player is connecting as a spectator (if so, the player will be flagged as invisible, in flymode, and
	 * in godmode)
	 */
	private boolean isSpectator;

	/**
	 * Constructs the {@link BBPacket} superclass with the ID of this packet (20), its data rendered as a string, and
	 * its Internet destination address. Initializes all class fields.
	 * 
	 * @param username The username of the player joining the server
	 * @param spectator See {@link #isSpectator}
	 * @param address The IP address of the server that the client is joining
	 * @param port The port on the above address
	 */
	public Packet20PlayerJoin(String username, boolean isSpectator, InetAddress address, int port) {
		super(20, username + (isSpectator ? " s" : ""), address, port);
	}

	/**
	 * This packet is never received by the client, so it is not handled.
	 */
	public void handle() { }

	/**
	 * @return The username of the connecting player
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return Whether the player is joining as a {@link #isSpectator spectator}
	 */
	public boolean isJoiningAsSpectator() {
		return isSpectator;
	}
}
