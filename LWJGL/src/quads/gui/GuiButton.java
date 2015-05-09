package quads.gui;

import org.lwjgl.util.vector.Vector2f;


public abstract class GuiButton {

	public String name;
	public int x, y, width, height;
	
	public GuiButton(int x, int y, int width, int height, String name){
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public Vector2f getPos(){
		return new Vector2f(x, y);
	}
	
	public boolean isIn(int mouseX, int mouseY){
		if(mouseX > getPos().x && mouseX < width){
			if(mouseY > getPos().y && mouseY < height){
				return true;
			}
		}
		return false;
	}
	
	public abstract void render();
	public abstract void onClick(int i);
	
}
