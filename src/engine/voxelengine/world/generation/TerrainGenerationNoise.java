package voxelengine.world.generation;

import voxelengine.noise.SimplexNoise;
import voxelengine.world.Blocks;
import voxelengine.world.chunk.Chunk;

public class TerrainGenerationNoise implements ITerrainGenerator{

	SimplexNoise noiseGen = null;
	
	public TerrainGenerationNoise(int maxFeature, int seed)
	{
		noiseGen = new SimplexNoise(maxFeature, 0.1, seed);
	}
	
	@Override
	public void generate(int chunk_x, int chunk_y, Chunk chunk) {
		//double noise = this.noiseGen.getNoise(chunk_x, chunk_y);
		
		chunk.fill(Blocks.grassTile);
	}

}
