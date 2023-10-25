package quads.blocks;

import static org.lwjgl.opengl.GL11.*;
import static quads.World.*;
import org.lwjgl.stb.*;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import engine.GameObject;
import engine.Renderable;

public class Block extends GameObject implements Renderable{

	private BlockType type;
	int textureID;

	public Block(int x, int y, BlockType type){
		pos.set(x, y);
		this.type = type;

		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		try {
			String texturePath = Paths.get(getClass().getClassLoader()
							.getResource(type.loc + ".png").toURI()).toFile().getAbsolutePath();
			int[] width = new int[1], height = new int[1], nrChannels = new int[1];
			ByteBuffer data = STBImage.stbi_load(texturePath, width, height, nrChannels, 0);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width[0], height[0], 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
		}catch (URISyntaxException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	@Override
	public void render(){
		glBindTexture(GL_TEXTURE_2D, textureID);
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
	public void update(float delta) {

	}
}