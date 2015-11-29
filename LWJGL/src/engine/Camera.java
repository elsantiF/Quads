package engine;

import static org.lwjgl.opengl.GL11.*;

public class Camera extends GameObject{

	private float scale;
	
	public Camera(){
		scale = 1;
	}
	
	public void use(){
		glScalef(scale, scale, 0);
		//TODO: OBTENER DIMENSIONES
		glViewport((int)pos.x, (int)pos.y, 512, 512);
	}
	
	public void move(float d, float e){
		e /= scale;
		d /= scale;
		pos.set(d + pos.x, e + pos.y);
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
	public void update(float delata) {
		
	}
}
