package voxelengine.world;

/**
 * Main object to be interacted with in a world. Blocks are stored within chunks which are stored within
 * world instances.
 * @author X1000
 *
 */
public abstract class Block {

	public String name;
	
	public Block(String na)
	{
		name = na;
	}
	
	public abstract void render(int x, int y);
	public abstract int getID();
}
