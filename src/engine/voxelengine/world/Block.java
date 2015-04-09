package voxelengine.world;

import org.newdawn.slick.Color;

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

	public abstract Color getColorBind();
	public abstract int getID();
}
