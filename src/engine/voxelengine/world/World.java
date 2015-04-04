package voxelengine.world;

import voxelengine.info.Info;
import voxelengine.noise.SimplexNoise;
import voxelengine.world.chunk.Chunk;
import voxelengine.world.chunk.ChunkAccessor;

public class World implements IBlockAndEntityAccess {

	//public Chunk[] chunks = new Chunk[Info.CHUNK_SIZE * Info.CHUNK_SIZE];

	public int TILE_SIZE = Info.TILE_SIZE;
	public int CHUNK_SIZE = Info.CHUNK_SIZE;

	public SimplexNoise noise = new SimplexNoise(200, 0.1, 123456789);

	private ChunkAccessor chunkAccessor;
	
	public World()
	{
		chunkAccessor = new ChunkAccessor();
	}
	
	@Override
	public boolean chunkExists(int chunkX, int chunkY) {
		return chunkAccessor.chunkIsLoaded(chunkX, chunkY);
	}

	public Chunk getChunk(int x, int y) {
		
		return chunkAccessor.loadChunk(x, y);
	}

	public void setBlock(int x, int y, Block tile) {
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);

		Chunk chunk = getChunk((int) Math.ceil(x / CHUNK_SIZE), (int) Math.ceil(y / CHUNK_SIZE));
		chunk.setBlock(Math.abs((int) Math.ceil(x % CHUNK_SIZE)), Math.abs((int) Math.ceil(y % CHUNK_SIZE)), tile);
	}

	@Override
	public Block getBlock(int x, int y) {
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);

		Chunk chunk = getChunk((int) Math.ceil(x / CHUNK_SIZE), (int) Math.ceil(y / CHUNK_SIZE));
		if (chunk == null)
			return null;
		return chunk.getBlock(Math.abs((int) Math.ceil(x % CHUNK_SIZE)), Math.abs((int) Math.ceil(y % CHUNK_SIZE)));
	}

	public void tickChunk(int x, int y) {
				Chunk chunk = getChunk(x, y);
				chunk.tick();
	}

	public void save() {
		chunkAccessor.saveAll();
	}

	// @Override
	public SimplexNoise getNoiseProvider() {
		return noise;
	}

}
