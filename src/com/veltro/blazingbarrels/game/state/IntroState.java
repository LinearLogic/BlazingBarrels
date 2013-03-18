package com.veltro.blazingbarrels.game.state;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.veltro.blazingbarrels.BlazingBarrels;
import com.veltro.blazingbarrels.engine.graphics.Camera3D;
import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
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
	private float[][] spheres;

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
		spheres = new float[20][];
		for (int i = 0; i < 20; i++)
			spheres[i] = new float[] {(float) (Math.random() * 30), 32, (float) (Math.random() * 250) - 250, (float) (Math.random() * 100), (float) (Math.random() * 250) - 250, (float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random()};
		keyDown = true;
		r3d = new RenderBot3D();
		cam = new Camera3D();
		cam.setFieldOfVision(50);
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
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // Reset 2D and 3D
		glLoadIdentity();
		cam.useView();
		cam.draw();
		r3d.renderColoredCylinder(100, 100, 100, 64, new Location3D(), 1, 0, 1);
		r3d.renderColoredDisk(100, 0, 64, new Location3D(), 1, 1, 1);
		for (int i = 0; i < spheres.length; i++) {
			r3d.renderTransparentColoredSphere(spheres[i][0], (int) spheres[i][1], spheres[i][2], -spheres[i][3], spheres[i][4], spheres[i][5], spheres[i][6], spheres[i][7], spheres[i][8]);
		}
	}

}
