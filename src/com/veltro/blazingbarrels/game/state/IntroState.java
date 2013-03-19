package com.veltro.blazingbarrels.game.state;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.veltro.blazingbarrels.BlazingBarrels;
import com.veltro.blazingbarrels.engine.graphics.Camera3D;
import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
import com.veltro.blazingbarrels.engine.graphics.model.Model;
import com.veltro.blazingbarrels.engine.graphics.model.ModelBot;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * The IntroState is the program's loading screen, the first to be encountered
 * when the game client is started.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class IntroState extends State {

	private RenderBot3D r3d;
	private Camera3D cam;
	private Model ship1;

	/**
	 * Constructor - calls the {@link State} superclass constructor with the {@link StateType#INTRO} type.
	 */
	public IntroState() {
		super(StateType.INTRO);
		keyDown = true;
	}

	@Override
	public void initialize()
	{
		keyDown = true;
		r3d = new RenderBot3D();
		cam = new Camera3D(0.3f, 300);
		cam.useView();
//		Music.INTRO_MUSIC.play((float)6);
//		ATTN: All music and sounds are disabled until the sound bug due to improper audio calls is resolved
	}

	@Override
	public void handleInput() {
		checkKeyStates();
		if (Mouse.isButtonDown(0) && !Mouse.isGrabbed())
			Mouse.setGrabbed(true);
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Mouse.setGrabbed(false);
		cam.handleKeyboardInput();
		cam.handleMouseInput();
		cam.updatePosition();
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !keyDown) {
			BlazingBarrels.setCurrentState(StateType.MAIN_MENU, true);
		}
	}

	@Override
	public void draw() {
		glMaterialf(GL_FRONT, GL_SHININESS, 10f);
//		cam.useView();
		cam.draw();
		// Draw the wall:
		r3d.renderTransparentColoredCylinder(100, 10, 50, 64, 1, new Location3D(), 0.5F, 0.5F, 0.5F, 1);

		// Draw the floor:
		r3d.renderColoredDisk(100, 80, 64, new Location3D(), 0.2118F, 0.3922F, 0.5451F);
		r3d.renderColoredDisk(80, 60, 64, new Location3D(), 0.0491F, 0.4549F, 0.9F);
		r3d.renderColoredDisk(60, 40, 64, new Location3D(), 0.1098F, 0.5255F, 0.9333F);
		r3d.renderColoredDisk(40, 20, 64, new Location3D(), 0, 0.749F, 1);
		r3d.renderColoredDisk(20, 0, 64, new Location3D(), 0, 0.9608F, 1);

		r3d.renderTransparentColoredCylinder(10, 0, 10, 5, 1, new Location3D(0, 20, 0), 1, 1, 1, 0.4F);
	}

}
