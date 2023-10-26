package quads.blocks;

import java.io.File;
import java.io.FileOutputStream;

import static quads.World.*;

public class BlockGird {

    Block[][] blockGird = new Block[BLOCK_WIDTH][BLOCK_HEIGTH];

    public void generateWorld() {
        for (int x = 0; x < BLOCK_WIDTH; x++) {
            for (int y = 0; y < BLOCK_HEIGTH; y++) {
                blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.AIR);
            }
        }
    }

    public void render() {
        for (int x = 0; x < BLOCK_WIDTH; x++) {
            for (int y = 0; y < BLOCK_HEIGTH; y++) {
                blockGird[x][y].render();
            }
        }
    }

    public void setAt(int x, int y, BlockType type) {
        if (y < 0 || y >= BLOCK_WIDTH) return;
        if (x < 0 || x >= BLOCK_HEIGTH) return;
        if (blockGird[x][y].getType() != BlockType.AIR) return;

        blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, type);
    }

    public void removeAt(int x, int y) {
        if (y < 0 || y >= BLOCK_WIDTH) return;
        if (x < 0 || x >= BLOCK_HEIGTH) return;
        if (blockGird[x][y].getType() == BlockType.AIR) return;

        blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.AIR);
    }

    public Block getAt(int x, int y) {
        return blockGird[x][y];
    }

    public void load(File loadFile) {
        //TODO: New load
        /*try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(loadFile);
            Element root = document.getRootElement();
            for (Object block : root.getChildren()) {
                Element e = (Element) block;
                int x = Integer.parseInt(e.getAttributeValue("x"));
                int y = Integer.parseInt(e.getAttributeValue("y"));
                blockGird[x][y] = new Block(x * BLOCK_SIZE, y * BLOCK_SIZE, BlockType.valueOf(e.getAttributeValue("type")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void save(File saveFile) {
        //TODO: New save
        /*Document document = new Document();
        Element root = new Element("block");
        document.setRootElement(root);
        for (int x = 0; x < BLOCK_WIDTH; x++) {
            for (int y = 0; y < BLOCK_HEIGTH; y++) {
                Element block = new Element("block");
                block.setAttribute("x", String.valueOf((int) (blockGird[x][y]).getPos().getX() / BLOCK_SIZE));
                block.setAttribute("y", String.valueOf((int) (blockGird[x][y]).getPos().getY() / BLOCK_SIZE));
                block.setAttribute("type", String.valueOf(this.getAt(x, y).getType()));
                root.addContent(block);
            }
        }
        XMLOutputter output = new XMLOutputter();
        try {
            output.output(document, new FileOutputStream(saveFile));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}