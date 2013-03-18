package com.veltro.blazingbarrels.game.state;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Font;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import com.veltro.blazingbarrels.BlazingBarrels;

/**
 * The connection state provides the user with two textbox into which a server's IP address and password can be typed.
 * Pressing 'enter' will then attempt to connect the client to the server at the specified address, and if the
 * connection is successful, the program will switch to the {@link GameState}. A future update will add an editable
 * list of servers with cached passwords, as well as support for text-based domain names.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class ConnectState extends State {

	private UnicodeFont font;
	private DecimalFormat formatter = new DecimalFormat("#.##");

	private FloatBuffer perspectiveProjMatrix = BufferUtils.createFloatBuffer(16);
	private FloatBuffer orthographicProjMatrix = BufferUtils.createFloatBuffer(16);
	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#CONNECT} type.
	 */
	@SuppressWarnings("unchecked")
	public ConnectState() {
		super(StateType.CONNECT);
		Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
		font = new UnicodeFont(awtFont);
		font.getEffects().add(new ColorEffect(Color.white));
		font.addAsciiGlyphs();
//		try {
//			font.loadGlyphs();
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
		keyDown = true;
	}

	@Override
	public void initialize() {
		// Graphics setup - lighting
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glGetFloat(GL_PROJECTION_MATRIX, perspectiveProjMatrix);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
		glGetFloat(GL_PROJECTION_MATRIX, orthographicProjMatrix);
		glLoadMatrix(perspectiveProjMatrix);
		glMatrixMode(GL_MODELVIEW_MATRIX);
		keyDown = true;
	}

	
	@Override
	public void handleInput() {
		checkKeyStates();
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !keyDown)
			BlazingBarrels.setCurrentState(StateType.MAIN_MENU, true);
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !keyDown) {
			// TODO: try to connect to the provided address. If successful, return StateType.GAME
		}
	}

	@Override
	public void draw() {
		glMatrixMode(GL_PROJECTION);
		glLoadMatrix(orthographicProjMatrix);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
		glDisable(GL_LIGHTING);
		font.drawString(10, 10, "Connection State");
		glEnable(GL_LIGHTING);
		glPopMatrix();
		// TODO Auto-generated method stub

	}

}
