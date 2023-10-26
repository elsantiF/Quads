package quads.blocks;

import static org.lwjgl.opengl.GL11.*;
import static quads.World.*;

import org.joml.Vector2f;
import org.lwjgl.stb.*;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

public class Block {

	private Vector2f pos;
	private BlockType type;
	int textureID;

	public Block(int x, int y, BlockType type){
		this.pos = new Vector2f(x, y);
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

	public void render(){
		glBindTexture(GL_TEXTURE_2D, textureID);
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
	}
	
	public BlockType getType(){
		return type;
	}
		
	public int getID(){
		return type.id;
	}
}