package quads;

import engine.Camera;
import engine.Input;
import quads.blocks.BlockGird;
import quads.blocks.BlockType;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

public class Game {

    public Camera cam;
    public BlockGird grid;
    BlockType selection;

    public Game() {
        this.cam = new Camera();
        this.grid = new BlockGird();
        this.selection = BlockType.STONE;
    }

    public void start() {
        cam.move(0, 0);
        grid.generateWorld();
    }

    public void update() {

        glColor4f(1f, 1f, 1f, 1f); //Cambaia Alpha
        cam.use();
        keyboardInput();
        mouseInput();

        render();
    }

    private void keyboardInput() {
        /*boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
        boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
        boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
        boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
        if (up) cam.move(0 * delta, -1 * delta);
        if (down) cam.move(0 * delta, 1 * delta);
        if (right) cam.move(-1 * delta, 0 * delta);
        if (left) cam.move(1 * delta, 0 * delta);*/

        /*if (Keyboard.getEventKey() == Keyboard.KEY_L) gird.load(new File("save.xml"));
        if (Keyboard.getEventKey() == Keyboard.KEY_S) gird.save(new File("save.xml"));*/
        if (Input.isKeyPressed(GLFW_KEY_1)) selection = BlockType.STONE;
        if (Input.isKeyPressed(GLFW_KEY_2)) selection = BlockType.DIRT;
        if (Input.isKeyPressed(GLFW_KEY_3)) selection = BlockType.GRASS;
    }

    private void mouseInput() {
        int select_x = (int) Math.floor(Input.getMousePosX() / World.BLOCK_SIZE);
        int select_y = (int) Math.floor(Input.getMousePosY() / World.BLOCK_SIZE);

        //Vector2f pos = cam.screenToWorld(new Vector2(mouseX, mouseY));
        if (Input.isButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
            grid.setAt(select_x, select_y, selection);
        }
        if (Input.isButtonPressed(GLFW_MOUSE_BUTTON_RIGHT)) {
            grid.removeAt(select_x, select_y);
        }
        if (Input.isButtonPressed(GLFW_MOUSE_BUTTON_MIDDLE)) {
            BlockType preselection = grid.getAt(select_x, select_y).getType();
            if (preselection != BlockType.AIR) selection = preselection;
        }
    }

    public void render() {
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        grid.render();
    }
}
