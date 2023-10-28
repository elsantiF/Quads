package engine;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private static HashMap<Integer, Integer> keyState;
    private static HashMap<Integer, Integer> buttonState;

    private static double mousePosX = 0;
    private static double mousePosY = 0;

    public static void initialize(long window) {
        keyState = new HashMap<>();
        buttonState = new HashMap<>();

        glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
            keyState.put(key, action);
        });

        glfwSetMouseButtonCallback(window, (window1, button, action, mods) ->  {
            buttonState.put(button, action);
        });

        glfwSetCursorPosCallback(window, (window1, xpos, ypos) -> {
            mousePosX = xpos;
            mousePosY = ypos;
        });
    }

    public static boolean isKeyPressed(int key) {
        Integer state = keyState.get(key);
        return state != null && state == GLFW_PRESS;
    }

    public static boolean isButtonPressed(int button) {
        Integer state = buttonState.get(button);
        return state != null && state == GLFW_PRESS;
    }

    public static double getMousePosX() {
        return mousePosX;
    }

    public static double getMousePosY() {
        return mousePosY;
    }
}
