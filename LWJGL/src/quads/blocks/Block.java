package quads.blocks;

import static org.lwjgl.opengl.GL11.*;
import static quads.World.*;
import java.io.File;
import java.io.FileInputStream;
import org.newdawn.slick.opengl.*;
import engine.GameObject;
import engine.Renderable;

public class Block extends GameObject implements Renderable{

	private Texture texture;
	private BlockType type;

	public Block(int x, int y, BlockType type){
		pos.set(x, y);
		this.type = type;
		try{
			this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(type.loc)));
		}catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
	}
	
	@Override
	public void render(){
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		glPushMatrix();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0 + pos.getX(), 0 + pos.getY());
		glTexCoord2f(1, 0);
		glVertex2f(BLOCK_SIZE + pos.getX(), 0 + pos.getY());
		glTexCoord2f(1, 1);
		glVertex2f(BLOCK_SIZE + pos.getX(), BLOCK_SIZE + pos.getY());
		glTexCoord2f(0, 1);
		glVertex2f(0 + pos.getX(), BLOCK_SIZE + pos.getY());
		glEnd();
		glPopMatrix();
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