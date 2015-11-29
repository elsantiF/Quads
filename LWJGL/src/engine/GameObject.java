package engine;

import engine.math.Vector2f;

public abstract class GameObject {

	protected String name;
	protected Vector2f pos = new Vector2f();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public abstract void update(float delata);
	
}
