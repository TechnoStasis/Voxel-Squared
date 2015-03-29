package voxelengine.world;

import voxelengine.noise.SimplexNoise;

/**
 * Classes that implement this must be able to readily store and retrieve chunks and their data.
 * @author X1000
 *
 */
public interface IChunkManager {

	/**
	 * Builds a chunk in the specific chunk coordinates.
	 * @param x The X axis in chunk coords.
	 * @param y The Y axis in chunk coords.
	 */
	public void buildChunk(int x, int y);
	
	public Chunk getChunk(int x, int y);
	
	/**
	 * Sets a block in the total world coordinates.
	 * @param x
	 * @param y
	 * @param tile
	 */
	public void setBlock(int x, int y, Block tile);
	
	public Block getBlock(int x, int y);
	
	public SimplexNoise getNoiseProvider();
	
}
