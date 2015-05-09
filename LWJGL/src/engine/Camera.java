package engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class Camera extends GameObject{

	private float scale;
	
	public Camera(){
		posX = 0;
		posY = 0;
		scale = 1;
	}
	
	public void use(){
		glTranslatef(Display.getWidth()/2, Display.getHeight()/2, 0);
		glScalef(scale, scale, 0);
		glTranslatef(posY, posX, 0);
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
}
