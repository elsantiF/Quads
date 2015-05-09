package quads.blocks;

public enum BlockType {
	AIR  	("res/air.png", 1),
	DIRT 	("res/dirt.png", 2),
	GRASS	("res/grass.png", 3),
	STONE	("res/stone.png", 4);
	
	public final String loc;
	public final int id;
	
	BlockType(String loc, int id) {
		this.loc = loc;
		this.id = id;
	}
}
