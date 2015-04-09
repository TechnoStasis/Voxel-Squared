package voxelengine.world.chunk;

import voxelengine.arrays.Array2D;
import voxelengine.info.Info;
import voxelengine.registry.BlockRegistry;
import voxelengine.world.Block;
import voxelengine.world.util.ChunkCoordinates;

public class Chunk {

	private final int SIZE = Info.CHUNK_SIZE;

	/** The array that holds the block data */
	private Array2D data = new Array2D(SIZE, SIZE);
	
	public boolean genned = false;
	
	private int chunkX = 0;
	private int chunkY = 0;
	
	public Chunk(int x, int y)
	{
		chunkX = x;
		chunkY = y;
	}
	
	public void setBlock(int chunk_x, int chunk_y, Block tile) {
		data.set(chunk_x, chunk_y, BlockRegistry.getIDFromBlock(tile));
	}

	public Block getBlock(int x, int y)
	{
		return BlockRegistry.getBlockFromID(data.get(x, y));
	}
	
	public void tick()
	{
		
	}
	
	public void fill(Block block)
	{
		for(int x = 0; x < SIZE; x++)
		{
			for(int y = 0; y < SIZE; y++)
			{
				setBlock(x, y, block);
			}
		}
	}
	
	public ChunkCoordinates toCoords()
	{
		return new ChunkCoordinates(chunkX, chunkY);
	}
	
	public Array2D getData()
	{
		return data;
	}
	
/*	public void gen(int cx, int cy, SimplexNoise noiseGenerator) {
		genned = true;
		for (int x = 0; x < Info.CHUNK_SIZE; x++) {
			for (int y = 0; y < Info.CHUNK_SIZE; y++) {
				double noise = (1+noiseGenerator.getNoise(x * Info.TILE_SIZE + (cx * Info.CHUNK_SIZE), y * Info.TILE_SIZE + (cy * Info.CHUNK_SIZE))) / 2;
				
				//System.out.println(noise);
				
				if(noise < 0.700F)
					setBlock(x, y, Blocks.grassTile, false);
				if(noise < 0.500F)
					setBlock(x, y, Blocks.basicTile, false);
				if(noise < 0.400F)
					setBlock(x, y, Blocks.greyTile, false);
			}
		}
	} */

	public boolean setChunkData(int[] data)
	{
		this.data.set(data);
		return true;
	}
	
	public int[] getChunkData()
	{
		return data.getData();
	}
}
