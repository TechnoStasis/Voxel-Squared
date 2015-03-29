package voxelengine.registry;

import voxelengine.world.Block;

/**
 * A static wrapper class for block registration.
 * Use this if you want chunks to recognize Block ids.
 * @author X1000
 *
 */
public final class BlockRegistry{

	/** 
	 * The registry.
	 */
	private static Registry<Block> registry = new Registry<Block>();
	
	public static void registerBlock(Block block)
	{
		registry.register(block);
	}
	
	public static Block getBlockFromID(int id)
	{
		return registry.getObjFromID(id);
	}
	
	public static int getIDFromBlock(Block block)
	{
		return block != null ? registry.getIDFromObj(block) : -1;
	}
	
	public static void incrementID()
	{
		registry.id++;
	}
}
