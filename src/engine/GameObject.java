package engine;

import engine.util.Vector2;

public abstract class GameObject {

	protected String name;
	protected Vector2 pos = new Vector2();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Vector2 getPos(){
		return pos;
	}
	
	public abstract void update(float delta);
	
}
