package quads.blocks;

import static quads.World.*;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
public class BlockGird {

	Block[][] blockGird = new Block[BLOCK_WIDTH][BLOCK_HEIGTH];
	
	public void generateWorld(){
		for(int x = 0; x < BLOCK_WIDTH; x++){
			for(int y = 0; y < BLOCK_HEIGTH; y++){
				blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.AIR);
			}
		}
	}
	
	public void render(){
		for(int x = 0; x < BLOCK_WIDTH; x++){
			for(int y = 0; y < BLOCK_HEIGTH; y++){
				blockGird[x][y].render();
			}
		}
	}
	
	public void setAt(int x, int y, BlockType type){
		if(!(y >= BLOCK_WIDTH) && blockGird[x][y].getType() == BlockType.AIR) blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, type);
	}
	
	public void removeAt(int x, int y){
		if(!(y >= BLOCK_WIDTH) && blockGird[x][y].getType() != BlockType.AIR) blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.AIR);	
	}
	
	public Block getAt(int x, int y){
		return blockGird[x][y];
	}
	
	public void load(File loadFile){
		try{
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(loadFile);
			Element root = document.getRootElement();
			for(Object block : root.getChildren()){
				Element e = (Element) block;
				int x = Integer.parseInt(e.getAttributeValue("x"));
				int y = Integer.parseInt(e.getAttributeValue("y"));	
				blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.valueOf(e.getAttributeValue("type")));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void save(File saveFile){
		Document document = new Document();
		Element root = new Element("block");
		document.setRootElement(root);
		for(int x = 0; x < BLOCK_WIDTH; x++){
			for(int y = 0; y < BLOCK_HEIGTH; y++){
				Element block = new Element("block");
				block.setAttribute("x", String.valueOf((int) (blockGird[x][y]).getX() / BLOCK_SIZE));
				block.setAttribute("y", String.valueOf((int) (blockGird[x][y]).getY() / BLOCK_SIZE));
				block.setAttribute("type", String.valueOf(this.getAt(x, y).getType()));
				root.addContent(block);
			}
		}
		XMLOutputter ouput = new XMLOutputter();
		try{
			ouput.output(document, new FileOutputStream(saveFile));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}