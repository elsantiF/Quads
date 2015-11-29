package quads;

import java.nio.ByteBuffer;

import engine.Camera;
import quads.blocks.BlockGird;
import quads.blocks.BlockType;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Start {
	
	public static GLFWErrorCallback errorcallback;
	private static final GLFWKeyCallback KeyboardController = new KeyboardController();

	private long window;
	
	//Variables
	long lastFPS;
	long lastFrame;
	int FPS;
	public static Camera cam = new Camera();
	public static BlockGird gird = new BlockGird();
	public static BlockType selection = BlockType.STONE;
	public static int delta;
	
	private static Start instance = new Start();
	
	//Start method
	private void start(){
		try {
			glfwSetErrorCallback(errorcallback = Callbacks.errorCallbackPrint(System.err));
			if(glfwInit() != GL_TRUE){
				throw new IllegalStateException("Error");
			}
			
			glfwDefaultWindowHints();
			glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
			glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
			window = glfwCreateWindow(512, 512, "Quads! - Now in Dev", NULL, NULL);
			if(window == NULL) throw new IllegalStateException("Error");
			
			glfwSetKeyCallback(window, KeyboardController);
//			glfwSetMouseButtonCallback(window, cbfun)
			
			ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, GLFWvidmode.width(vidmode) - 512 / 2, GLFWvidmode.height(vidmode) - 512 / 2);
			glfwMakeContextCurrent(window);
			glfwSwapInterval(1);
			glfwShowWindow(window);

			
			initGL();
			getDelta();
			lastFPS = getTime();
			gameLoop();
			
		}finally {
			terminate();
		}
	}
	
	public void terminate(){
		glfwTerminate();
		errorcallback.release();
	}

	//The gameLoop
	private void gameLoop() {
		cam.move(0, 0);
		gird.generateWorld();
		glColor4f(1f, 1f, 1f, 1f); //Cambaia Alpha
		glClearColor(0.0f, 0.0f, 0.5f, 0.0f);
		while(glfwWindowShouldClose(window) == GL_FALSE){
			delta = getDelta();
			render();
			update(delta);
			glfwSwapBuffers(window);
			glfwPollEvents();
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
		return (long) (glfwGetTime() * 1000);
	}
	
	private void updateFPS(){
		if(getTime() - lastFPS > 1000){
			FPS = 0;
			lastFPS += 1000;
		}
		FPS++;
	}
	//And finish here
	
	//More methods
	private void initGL() {
		GL.createCapabilities();
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, 512, 512, 0, 1, -1);
	}
	
	//TODO: Terminar
//	public void mouseInput(){
//		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
//		if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS){
//			int mouseX = 
//			int mouseY = 512 - Mouse.getY();
//			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
//			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
//			gird.setAt(selec_x, selec_y, selection);
//		}else if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_2) == GLFW_PRESS){
//			int mouseX = Mouse.getX();
//			int mouseY = 512 - Mouse.getY();
//			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
//			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
//			gird.removeAt(selec_x, selec_y);
//		}else if(glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_3) == GLFW_PRESS){
//			int mouseX = Mouse.getX();
//			int mouseY = 512 - Mouse.getY();
//			int selec_x = Math.round(mouseX / World.BLOCK_SIZE); 
//			int selec_y = Math.round(mouseY / World.BLOCK_SIZE);
//			BlockType preselection = gird.getAt(selec_x, selec_y).getType();
//			if(preselection != BlockType.AIR) selection = preselection;
//		}
//	}
//	
	public void keyboardInput(float delta){

	}
	
	public void update(float delta){
		keyboardInput(delta);
		//mouseInput();
		cam.use();
		updateFPS();
	}
	
	public void render(){
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		gird.render();
	}
	
	public static Start getStart(){
		return instance;
	}

	public static void main(String[] args) {
		instance.start();
	}
}