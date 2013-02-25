package com.veltro.blazingbarrels.engine.connect;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.veltro.blazingbarrels.engine.connect.packet.BBPacket;

/**
 * A thread dedicated to sending Datagram packets to network addresses. While running, this thread waits for 
 * {@link BBPacket packets} to be added to the {@link #outgoingPacketQueue}
 * 
 * @author LinearLogic
 * @since 0.2.2
 */
public class SenderThread extends Thread {

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
	public ConcurrentLinkedQueue<BBPacket> outgoingPacketQueue;

	/**
	 * The thread listens for packets to be added to the {@link #outgoingPacketQueue}, and once they are, it sends them over
	 * the network to their destinations
	 */
	public void run() {
		while (running) {
			if (!outgoingPacketQueue.isEmpty()) {
				try {
					socket.send(outgoingPacketQueue.poll().generatePacket());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
