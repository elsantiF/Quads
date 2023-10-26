package engine;

import org.joml.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Camera {

    private Vector2f pos;
    private float scale;

    public Camera() {
        pos = new Vector2f();
        scale = 1;
    }

    public void use() {
        glScalef(scale, scale, 0);
        glViewport((int) pos.x, (int) pos.y, 512, 512);
    }

    public void move(float d, float e) {
        e /= scale;
        d /= scale;
        pos.x = (d + pos.x);
        pos.y = (e + pos.y);
    }

    public void zoom(float zoom) {
        this.scale += zoom * this.scale;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    //TODO Terminar
    public Vector2f screenToWorld(Vector2f screen) {
        //TODO: Screen to world coordinates transform
        /*FloatBuffer model = BufferUtils.createFloatBuffer(16);
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        IntBuffer viewport = BufferUtils.createIntBuffer(16);

        glGetFloat(GL_MODELVIEW_MATRIX, model);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        glGetInteger(GL_VIEWPORT, viewport);

        float viewportHeight = viewport.get(3);
        float y = viewportHeight - screen.y;

        FloatBuffer coord = BufferUtils.createFloatBuffer(16);

        GLU.gluUnProject(screen.x, y, -1.5f, model, projection, viewport, coord);*/
        return screen;
    }
}
