package engine.util;

public class Vector2 {

	public float x;
	public float y;
	
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
	
	public Vector2 cpy() {
		return new Vector2(this);
	}
	
}
