package quads;

import engine.Camera;
import engine.util.Vector2;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import quads.blocks.BlockGird;
import quads.blocks.BlockType;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.File;

import static org.lwjgl.opengl.GL11.*;

public class Start {

    public static Camera cam = new Camera();
    public static BlockGird gird = new BlockGird();
    //Variables
    long lastFPS;
    long lastFrame;
    int FPS;
    BlockType selection = BlockType.STONE;
    private long window;

    public static void main(String[] args) {
        new Start().start();
    }

    //Start method
    private void start() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new RuntimeException("Can't initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        window = glfwCreateWindow(512, 512, "Quads", NULL, NULL);
        if(window == NULL) {
            throw new RuntimeException("Can't create window");
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        initGL();
        getDelta();
        lastFPS = getTime();
        gameLoop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    //The gameLoop
    private void gameLoop() {
        cam.move(0, 0);
        gird.generateWorld();
        glColor4f(1f, 1f, 1f, 1f); //Cambaia Alpha
        while (!glfwWindowShouldClose(window)) {
            int delta = getDelta();
            render();
            update(delta);
        }
    }

    //Timing - Start here
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
        return delta;
    }

    public long getTime() {
        return System.currentTimeMillis() * 1000;
    }
    //And finish here

    private void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            // Display.setTitle("FPS: " + FPS);
            FPS = 0;
            lastFPS += 1000;
        }
        FPS++;
    }

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

    public void mouseInput() {
        //TODO: Mouse input
        /*if (Mouse.isButtonDown(0)) {
            int mouseX = Mouse.getX();
            int mouseY = 512 - Mouse.getY();
            Vector2f pos = cam.screenToWorld(new Vector2(mouseX, mouseY));
            int select_x = Math.round(pos.x / World.BLOCK_SIZE);
            int select_y = Math.round(pos.y / World.BLOCK_SIZE);
            gird.setAt(select_x, select_y, selection);
        } else if (Mouse.isButtonDown(1)) {
            int mouseX = Mouse.getX();
            int mouseY = 512 - Mouse.getY();
            int select_x = Math.round(mouseX / World.BLOCK_SIZE);
            int select_y = Math.round(mouseY / World.BLOCK_SIZE);
            gird.removeAt(select_x, select_y);
        } else if (Mouse.isButtonDown(2)) {
            int mouseX = Mouse.getX();
            int mouseY = 512 - Mouse.getY();
            int select_x = Math.round(mouseX / World.BLOCK_SIZE);
            int select_y = Math.round(mouseY / World.BLOCK_SIZE);
            BlockType preselection = gird.getAt(select_x, select_y).getType();
            if (preselection != BlockType.AIR) selection = preselection;
        }*/
    }

    public void keyboardInput(float delta) {
        //TODO: Keyboard input
        /*boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
        boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
        boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
        boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
        if (up) cam.move(0 * delta, -1 * delta);
        if (down) cam.move(0 * delta, 1 * delta);
        if (right) cam.move(-1 * delta, 0 * delta);
        if (left) cam.move(1 * delta, 0 * delta);

        while (Keyboard.next()) {
            if (Keyboard.getEventKey() == Keyboard.KEY_L) gird.load(new File("save.xml"));
            if (Keyboard.getEventKey() == Keyboard.KEY_S) gird.save(new File("save.xml"));
            if (Keyboard.getEventKey() == Keyboard.KEY_1) selection = BlockType.STONE;
            if (Keyboard.getEventKey() == Keyboard.KEY_2) selection = BlockType.DIRT;
            if (Keyboard.getEventKey() == Keyboard.KEY_3) selection = BlockType.GRASS;
        }*/
    }

    public void update(float delta) {
        keyboardInput(delta);
        mouseInput();
        cam.use();
        updateFPS();
        glfwPollEvents();
    }

    public void render() {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        gird.render();
        glfwSwapBuffers(window);
    }
}