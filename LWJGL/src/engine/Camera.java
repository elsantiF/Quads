package engine;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import engine.util.Vector2;

public class Camera extends GameObject{

	private float scale;
	
	public Camera(){
		scale = 1;
	}
	
	public void use(){
//		glTranslatef(Display.getWidth()/2, Display.getHeight()/2, 0);
//		glScalef(scale, scale, 0);
//		glTranslatef(posX, posY, 0);
		glViewport((int)pos.getX(), (int)pos.getY(), Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());
	}
	
	public void move(float d, float e){
		e /= scale;
		d /= scale;
		pos.setX(d + pos.getX());
		pos.setY(e + pos.getY());
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
	
	//TODO Terminar
	public Vector2f screenToWorld(Vector2 screen){
		return null;
	}
}
