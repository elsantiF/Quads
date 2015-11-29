package quads.blocks;

import static org.lwjgl.opengl.GL11.*;
import static quads.World.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import engine.GameObject;
import engine.Renderable;
import engine.util.TextureLoader;


public class Block extends GameObject implements Renderable{

	private BufferedImage texture;
	private BlockType type;

	public Block(int x, int y, BlockType type){
		pos.set(x, y);
		this.type = type;
		try{
			//this.texture = TextureLoader.loadImage("/res/"+type.loc);
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	@Override
	public void render(){
		//glBindTexture(GL_TEXTURE_2D, TextureLoader.loadTexture(texture));
		if(type == BlockType.AIR){
			glColor3f(0, 0, 1);
		}
		glPushMatrix();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0 + pos.x, 0 + pos.y);
		glTexCoord2f(1, 0);
		glVertex2f(BLOCK_SIZE + pos.x, 0 + pos.y);
		glTexCoord2f(1, 1);
		glVertex2f(BLOCK_SIZE + pos.x, BLOCK_SIZE + pos.y);
		glTexCoord2f(0, 1);
		glVertex2f(0 + pos.x, BLOCK_SIZE + pos.y);
		glEnd();
		glPopMatrix();
		glColor3f(0, 0, 0);
	}
	
	public BlockType getType(){
		return type;
	}
		
	public int getID(){
		return type.id;
	}

	@Override
	public void update(float delata) {

	}
}