package voxelengine.world;

import voxelengine.world.chunk.Chunk;

/**
 * Classes that implement this must be able to readily store and retrieve chunks and their data.
 * @author X1000
 *
 */
public interface IBlockAndEntityAccess {

	/**
	 * Checks if a chunk with data exists.
	 * @param x The X axis in chunk coords.
	 * @param y The Y axis in chunk coords.
	 */
	//public void buildChunk(int x, int y);
	
	boolean chunkExists(int chunkX, int chunkY);
	
	public Chunk getChunk(int x, int y);
	
	/**
	 * Sets a block in the total world coordinates.
	 * @param x
	 * @param y
	 * @param tile
	 */
	public void setBlock(int x, int y, Block tile);
	
	/**
	 * Gets a block in the total world coordinates.
	 * @param x
	 * @param y
	 * @return
	 */
	public Block getBlock(int x, int y);
	
	//public SimplexNoise getNoiseProvider();
	
}
