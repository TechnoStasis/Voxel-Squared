package voxelengine.world.generation;

import voxelengine.world.chunk.Chunk;

public interface ITerrainGenerator {

	void generate(int chunk_x, int chunk_y, Chunk chunk);
	
}
