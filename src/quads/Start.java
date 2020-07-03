package quads;
//dev
import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import engine.Camera;
import engine.util.Vector2;
import quads.blocks.BlockGird;
import quads.blocks.BlockType;
import static org.lwjgl.opengl.GL11.*;

public class Start {

	//Variables
	long lastFPS;
	long lastFrame;
	int FPS;
	public static Camera cam = new Camera();
	public static BlockGird gird = new BlockGird();
	BlockType selection = BlockType.STONE;
	
	//Start method
	private void start(){
		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.setTitle("Quads - Now in Alpha!");
			Display.setVSyncEnabled(false);
			Display.create();
		} catch (LWJGLException e) {
			System.out.println(e);
			Display.destroy();
			System.exit(0);
		}
		initGL();
		getDelta();
		lastFPS = getTime();
		gameLoop();
	}

	//The gameLoop
	private void gameLoop() {
		cam.move(0, 0);
		gird.generateWorld();
		glColor4f(1f, 1f, 1f, 1f); //Cambaia Alpha
		while(!Display.isCloseRequested()){
			int delta = getDelta();
			render();
			update(delta);
		}
	}
	
	//Timing - Start here
	public int getDelta(){
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}
	
	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private void updateFPS(){
		if(getTime() - lastFPS > 1000){
			Display.setTitle("FPS: " + FPS);
			FPS = 0;
			lastFPS += 1000;
		}
		FPS++;
	}
	//And finish here
	
	//More methods
	private void initGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, 512, 512, 0, 1, -1);
	}
	
	public void mouseInput(){
		if(Mouse.isButtonDown(0)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			gird.setAt(selec_x, selec_y, selection);
			cam.screenToWorld(new Vector2(selec_x, selec_y));
		}else if(Mouse.isButtonDown(1)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			gird.removeAt(selec_x, selec_y);
		}else if(Mouse.isButtonDown(2)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			BlockType preselection = gird.getAt(selec_x, selec_y).getType();
			if(preselection != BlockType.AIR) selection = preselection;
		}
	}
	
	public void keyboardInput(float delta){
		boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
		boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		if(up) cam.move(0 * delta, -1 * delta);
		if(down) cam.move(0 * delta, 1 * delta);
		if(right) cam.move(-1 * delta, 0 * delta);
		if(left) cam.move(1 * delta, 0 * delta);
		
		while (Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_L) gird.load(new File("save.xml"));
			if(Keyboard.getEventKey() == Keyboard.KEY_S) gird.save(new File("save.xml"));
			if(Keyboard.getEventKey() == Keyboard.KEY_1) selection = BlockType.STONE;
			if(Keyboard.getEventKey() == Keyboard.KEY_2) selection = BlockType.DIRT;
			if(Keyboard.getEventKey() == Keyboard.KEY_3) selection = BlockType.GRASS;
		}
	}
	
	public void update(float delta){
		keyboardInput(delta);
		mouseInput();
		cam.use();
		updateFPS();
		Display.update();
	}
	
	public void render(){
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		gird.render();
	}

	public static void main(String[] args) {
		new Start().start();
	}
}