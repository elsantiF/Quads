package quads;

import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import engine.Camera;
import quads.blocks.BlockGird;
import quads.blocks.BlockType;
import static org.lwjgl.opengl.GL11.*;

public class Start {

	//Variables
	long lastFPS;
	long lastFrame;
	int FPS;
	Camera cam = new Camera();
	BlockGird gird = new BlockGird();
	BlockType selection = BlockType.STONE;
	
	//Start method
	private void start(){
		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.setTitle("Quads - Now in Alpha!");
			Display.setVSyncEnabled(true);
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
		cam.move(-256, -256);
		gird.generateWorld();
		glColor4f(1f, 1f, 1f, 1f); //Cambaia Alpha
		while(!Display.isCloseRequested()){
			int delta = getDelta();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			cam.use();
			input();
			gird.render();
			updateFPS();
			Display.update();
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
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void mouseInput(){
		if(Mouse.isButtonDown(0)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			gird.setAt(selec_x, selec_y, selection);
		}else if(Mouse.isButtonDown(1)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			gird.setAt(selec_x, selec_y, BlockType.AIR);
		}else if(Mouse.isButtonDown(2)){
			int mouseX = Mouse.getX();
			int mouseY = 512 - Mouse.getY();
			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
			selection = gird.getAt(selec_x, selec_y).getType();
		}
	}
	
	public void keyboardInput(){
		boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
		boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		if(up){
			cam.move(1, 0);
		}else if(down){
			cam.move(-1, 0);
		}else if(right){
			cam.move(0, -1);
		}else if(left){
			cam.move(0, 1);
		}
		while (Keyboard.next()) {
			if(Keyboard.getEventKey() == Keyboard.KEY_L){
				gird.load(new File("save.xml"));
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_S){
				gird.save(new File("save.xml"));
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_1){
				selection = BlockType.STONE;
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_2){
				selection = BlockType.DIRT;
			}
			if(Keyboard.getEventKey() == Keyboard.KEY_3){
				selection = BlockType.GRASS;
			}
		}
	}
	
	public void input(){
		keyboardInput();
		mouseInput();
	}

	public static void main(String[] args) {
		new Start().start();
	}
}