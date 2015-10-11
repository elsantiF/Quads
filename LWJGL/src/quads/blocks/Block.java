package quads.blocks;

import static org.lwjgl.opengl.GL11.*;
import static quads.World.*;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.*;

import quads.Start;
import engine.GameObject;

public class Block extends GameObject{

	private Texture texture;
	private BlockType type;

	public Block(int x, int y, BlockType type){
		this.posX = x;
		this.posY = y;
		this.type = type;
		try{
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.loc)));
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void render(){
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glPushMatrix();
		//glTranslatef(posX, posY, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0 + posX, 0 + posY);
		glTexCoord2f(1, 0);
		glVertex2f(BLOCK_SIZE + posX, 0 + posY);
		glTexCoord2f(1, 1);
		glVertex2f(BLOCK_SIZE + posX, BLOCK_SIZE + posY);
		glTexCoord2f(0, 1);
		glVertex2f(0 + posX, BLOCK_SIZE + posY);
		glEnd();
		glPopMatrix();
	}
	
	public BlockType getType(){
		return type;
	}
		
	public int getID(){
		return type.id;
	}
}