package quads;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

import engine.Input;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import static org.lwjgl.system.MemoryUtil.*;

import static org.lwjgl.opengl.GL11.*;

public class Start {

    public static void main(String[] args) {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new RuntimeException("Can't initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        long window = glfwCreateWindow(512, 512, "Quads", NULL, NULL);
        if(window == NULL) {
            throw new RuntimeException("Can't create window");
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
        glfwSetInputMode(window, GLFW_STICKY_KEYS, GLFW_TRUE);

        Input.initialize(window);

        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glOrtho(0, 512, 512, 0, 1, -1);

        Game game = new Game();
        game.start();

        while(!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            game.update();
            glfwSwapBuffers(window);
        }

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}