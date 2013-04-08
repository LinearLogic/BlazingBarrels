package com.veltro.blazingbarrels.game.state;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.veltro.blazingbarrels.BlazingBarrels;
import com.veltro.blazingbarrels.engine.graphics.Camera3D;
import com.veltro.blazingbarrels.engine.graphics.RenderBot3D;
import com.veltro.blazingbarrels.engine.graphics.construct.Construct3D;
import com.veltro.blazingbarrels.engine.graphics.construct.Washer;
import com.veltro.blazingbarrels.game.location.Location3D;

/**
 * The IntroState is the program's loading screen, the first to be encountered
 * when the game client is started.
 * 
 * @author LinearLogic
 * @since 0.0.3
 */
public class IntroState extends State {

	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight; 
	private FloatBuffer lModelAmbient;

	private Location3D loc = new Location3D(0, 50, 0, 0, 90, 0);
	private Camera3D cam;
	int step = 0;
	Construct3D minigun, ship;

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
		ship = Construct3D.PLAYER_SHIP(0.6f, 0.6f, 0.7f);
		ship.translate(0, 10, 0);
		ship.rotate(0, 0, 0);
		
		initLightArrays();
		glShadeModel(GL_SMOOTH);
		glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, matSpecular);				// sets specular material color
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 10.0f);					// sets shininess

		glLight(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);				// sets specular light to white
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);					// sets diffuse light to white
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);		// global ambient light 
		
		glEnable(GL_LIGHTING);										// enables lighting
		glEnable(GL_LIGHT0);										// enables light0
		
		glEnable(GL_COLOR_MATERIAL);								// enables opengl to use glColor3f to define material color
		glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);			// tell opengl glColor3f effects the ambient and diffuse properties of material
		
		keyDown = true;
		cam = new Camera3D(0.3f, 300);
		cam.setLocation(new Location3D(-73, 40, -26, 253, 322, 0));
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
		cam.handleKeyboardInput(20);
		cam.handleMouseInput();
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !keyDown) {
			BlazingBarrels.setCurrentState(StateType.MAIN_MENU, true);
		}
	}

	@Override
	public void draw() {
		glLight(GL_LIGHT0, GL_POSITION, lightPosition);				// sets light position

//		cam.useView();
		cam.draw();

		// Draw the wall:
//		RenderBot3D.renderColoredCylinder(100, 10, 50, 64, 1, new Location3D(0, 0, 0, 0, 90, 0), 0.5F, 0.5F, 0.5F, 1);

		// Draw the floor:
		RenderBot3D.renderColoredDisk(100, 80, 64, new Location3D(0, 0, 0, 0, 90, 0), 0.2118f, 0.3922f, 0.5451f, 1);
		RenderBot3D.renderColoredDisk(80, 60, 64, new Location3D(0, 0, 0, 0, 90, 0), 0.0491f, 0.4549f, 0.9f, 1);
		RenderBot3D.renderColoredDisk(60, 40, 64, new Location3D(0, 0, 0, 0, 90, 0), 0.1098f, 0.5255f, 0.9333f, 1);
		RenderBot3D.renderColoredDisk(40, 20, 64, new Location3D(0, 0, 0, 0, 90, 0), 0, 0.749f, 1, 1);
		RenderBot3D.renderColoredDisk(20, 0, 64, new Location3D(0, 0, 0, 0, 90, 0), 0, 0.9608f, 1, 1);

		loc.rotate(3, 0, 0);
//		RenderBot3D.renderColoredSphere(5, 32, 32, new Location3D(0, 20, 0), 0, 1, 1, 1);
		RenderBot3D.renderColoredCylinder(10, 0, 10, 5, 1, loc, 1, 1, 1, 0.6f);
		RenderBot3D.renderColoredDisk(100, 0, 64, new Location3D(0, 0.1f, 0, 0, 90, 0), 1, 1, 1, 0.3f);
		ship.getConstruct(0).rotate(0,  0,  10);
		ship.draw();
//		loc = new Location3D(0, 20, 0);
//		cam.getLocation().rotate(3, 0, 0);
		if (++step % 60 == 0) {
			System.out.println(cam.getLocation().toString());
			System.out.println(BlazingBarrels.getDelta());
			step = 0;
		}
//		cam.setLocation(new Location3D(20, 20, 0));
	}
	

	private void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
		
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(0.5f).put(0.5f).put(0.2f).put(0.05f).flip();
		
		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(0.4f).put(0.4f).put(0.4f).put(0.0f).flip();
		
		lModelAmbient = BufferUtils.createFloatBuffer(4);
		lModelAmbient.put(0.3f).put(0.3f).put(0.3f).put(0.0f).flip();
	}
}
