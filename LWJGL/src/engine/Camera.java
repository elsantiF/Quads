package engine;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.logging.Logger;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import org.pushingpixels.substance.internal.utils.icon.GlowingIcon;

import com.bulletphysics.collision.shapes.VertexData;

import engine.util.Vector2;

public class Camera extends GameObject{

	private float scale;
	
	public Camera(){
		posX = 0;
		posY = 0;
		scale = 1;
	}
	
	public void use(){
//		glTranslatef(Display.getWidth()/2, Display.getHeight()/2, 0);
//		glScalef(scale, scale, 0);
//		glTranslatef(posX, posY, 0);
		glViewport(posX, posY, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
	}
	
	public void move(float x, float y){
		y /= scale;
		x /= scale;
		posX += x;
		posY += y;
	}
	
	public void zoom(float zoom){
		this.scale += zoom * this.scale;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public void render() {
		return;
	}
	
	//TODO Terminar
	public Vector2f screenToWorld(Vector2 screen){
		FloatBuffer model = BufferUtils.createFloatBuffer(16);
		FloatBuffer projection = BufferUtils.createFloatBuffer(16);
		IntBuffer viewport = BufferUtils.createIntBuffer(16);
		
		glGetFloat(GL_MODELVIEW_MATRIX, model);
		glGetFloat(GL_PROJECTION_MATRIX, projection);
		glGetInteger(GL_VIEWPORT, viewport);
		
		float viewportHeight = viewport.get(3);
		float y = viewportHeight - screen.y;
		
		FloatBuffer coor = BufferUtils.createFloatBuffer(16);
		
		GLU.gluUnProject(screen.x, y, -1.5f, model, projection, viewport, coor);
		System.out.println(coor.get(1));
		return null;
	}
}
