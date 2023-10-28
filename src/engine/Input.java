package engine;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private final HashMap<Integer, Integer> keyState;
    private final HashMap<Integer, Integer> buttonState;

    private double mousePosX = 0;
    private double mousePosY = 0;

    public Input(long window) {
        this.keyState = new HashMap<>();
        this.buttonState = new HashMap<>();

        glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
            this.keyState.put(key, action);
        });

        glfwSetMouseButtonCallback(window, (window1, button, action, mods) ->  {
            this.buttonState.put(button, action);
        });

        glfwSetCursorPosCallback(window, (window1, xpos, ypos) -> {
            this.mousePosX = xpos;
            this.mousePosY = ypos;
        });
    }

    public boolean isKeyPressed(int key) {
        Integer state = keyState.get(key);
        return state != null && state == GLFW_PRESS;
    }

    public boolean isButtonPressed(int button) {
        Integer state = buttonState.get(button);
        return state != null && state == GLFW_PRESS;
    }

    public double getMousePosX() {
        return mousePosX;
    }

    public double getMousePosY() {
        return mousePosY;
    }
}
