package engine.util;

public class Vector2 {

	public float x;
	public float y;
	
	public Vector2(){
		set(0, 0);
	}
	
	public Vector2(float x, float y){
		set(x, y);
	}
	
	public Vector2(Vector2 vector) {
		set(vector.x, vector.y);
	}

	public Vector2 set(float x, float y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Vector2 cpy() {
		return new Vector2(this);
	}
}
