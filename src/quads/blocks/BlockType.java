package quads.blocks;

public enum BlockType {
    AIR("air", 1),
    DIRT("dirt", 2),
    GRASS("grass", 3),
    STONE("stone", 4);

    public final String loc;
    public final int id;

    BlockType(String loc, int id) {
        this.loc = loc;
        this.id = id;
    }
}
