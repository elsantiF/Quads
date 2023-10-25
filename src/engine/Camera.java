package engine;

import engine.util.Vector2;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Camera extends GameObject {

    private float scale;

    public Camera() {
        scale = 1;
    }

    public void use() {
        glScalef(scale, scale, 0);
        glViewport((int) pos.getX(), (int) pos.getY(), 512, 512);
    }

    public void move(float d, float e) {
        e /= scale;
        d /= scale;
        pos.setX(d + pos.getX());
        pos.setY(e + pos.getY());
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

    @Override
    public void update(float delata) {

    }

    //TODO Terminar
    public Vector2 screenToWorld(Vector2 screen) {
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
