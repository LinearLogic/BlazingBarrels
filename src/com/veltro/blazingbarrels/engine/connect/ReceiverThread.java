package com.veltro.blazingbarrels.engine.connect;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.veltro.blazingbarrels.engine.connect.packet.BBPacket;
import com.veltro.blazingbarrels.engine.connect.packet.Packet1AuthResponse;
import com.veltro.blazingbarrels.engine.connect.packet.Packet2DeauthWarning;

/**
 * A thread dedicated to receiving Datagram packets over a network socket. While running, this thread receives Datagram
 * packets, constructs BBPackets from them, and adds the resulting objects to the {@link #incomingPacketQueue}
 * 
 * @author LinearLogic
 * @since 0.2.3
 */
public class ReceiverThread extends Thread {

	/**
	 * Status flag for the loop. If set to false, causes the thread to complete its {@link #run()} method and terminate
	 */
	private boolean running = false;

	/**
	 * The internet socket over which to send packets in the {@link #packetQueue}
	 */
	private DatagramSocket socket = null;

	/**
	 * A queue (first in - first out list) of the packets to be sent over the internet
	 */
	public ConcurrentLinkedQueue<BBPacket> incomingPacketQueue;

	/**
	 * The thread listens for incoming packets arriving over the socket, casts them to BBPacket subclass objects, and
	 * adds them to the {@link #incomingPacketQueue}
	 */
	public void run() {
		while (running) {
			byte[] buffer = new byte[256];

			// Receive the packet
			DatagramPacket inbound = new DatagramPacket(buffer, buffer.length);
			try {
				socket.receive(inbound);
			} catch (IOException e) {
				continue;
			}

			
			// Unpack the packet's contents
			String data[] = new String(inbound.getData(), 0, inbound.getLength()).split("\\s+", 2);
			int id;
			try {
				id = Integer.parseInt(data[0]);
			} catch (NumberFormatException e) { // Invalid packet format - discard packet
				continue;
			}
			BBPacket received;

			switch(id) { // Only the id values of packets that a client should normally receive are handled
				case 1:
					String[] info = data[1].split("\\s+");
					if (info.length != 2) // Invalid data format
						continue;
					received = new Packet1AuthResponse(info[0], info[1], inbound.getAddress(), inbound.getPort());
					break;
				case 2:
					if (data.length != 2) // Invalid data format
						continue;
					received = new Packet2DeauthWarning(data[1], inbound.getAddress(), inbound.getPort());
					break;
				default: // Invalid packet for a client to be receiving - discard packet
					continue;
			}
			incomingPacketQueue.add(received);
			
		}
		socket.close();
	}

	/**
	 * Causes the main loop in the {@link #run()} method to exit; as a result, the thread completes its execution
	 */
	public void terminate() {
		running = false;
	}
}
