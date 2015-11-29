package quads.gui;

import engine.GameObject;

public abstract class GuiButton extends GameObject{

	public String name;
	public int x, y, width, height;
	
	public GuiButton(int x, int y, int width, int height, String name){
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
		this.name = name;
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
