package quads.util;

import quads.blocks.BlockType;

public class Util {

    public static BlockType getTypeByID(int id) {
        BlockType isTheBlock = null;
        for (BlockType b : BlockType.values()) {
            if (b.id == id) {
                isTheBlock = b;
            }
        }
        return isTheBlock;
    }

}
