package voxelengine.world;

import java.io.File;

import org.lwjgl.opengl.GL11;

import voxel.core.VoxelSquared;
import voxelengine.info.Info;
import voxelengine.noise.SimplexNoise;

public class World implements IChunkManager{

	public Chunk[] chunks = new Chunk[Info.CHUNK_SIZE * Info.CHUNK_SIZE];

	public int TILE_SIZE = Info.TILE_SIZE;
	public int CHUNK_SIZE = Info.CHUNK_SIZE;

	public SimplexNoise noise = new SimplexNoise(200, 0.1, 123456789);

	public void buildChunk(int x, int y) {
		if (x < 0 || y < 0)
			return;

		chunks[(y / CHUNK_SIZE) * CHUNK_SIZE + (x / CHUNK_SIZE)] = new Chunk();
	}
	
	public Chunk getChunk(int x, int y) {

		if (x < 0 || y < 0)
			return null;

		Chunk chunk = chunks[(y / CHUNK_SIZE) * CHUNK_SIZE + (x / CHUNK_SIZE)];
		if (chunk == null)
			chunks[(y / CHUNK_SIZE) * CHUNK_SIZE + (x / CHUNK_SIZE)] = new Chunk();

		return chunk;
	}

	public void setBlock(int x, int y, Block tile) {
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);
		
		Chunk chunk = getChunk((int) Math.ceil(x / CHUNK_SIZE),(int) Math.ceil(y / CHUNK_SIZE));
		chunk.setBlock((int) Math.ceil(x % CHUNK_SIZE), (int) Math.ceil(y % CHUNK_SIZE), tile);
	}


	@Override
	public Block getBlock(int x, int y) {
		x = (int) Math.ceil(x);
		y = (int) Math.ceil(y);
		
		Chunk chunk = getChunk((int) Math.ceil(x / CHUNK_SIZE),(int) Math.ceil(y / CHUNK_SIZE));
		if(chunk == null)
			return null;
		
		return chunk.getBlock((int) Math.ceil(x % CHUNK_SIZE), (int) Math.ceil(y % CHUNK_SIZE));
	}
	
	public void renderAll() {
		for (int x = 0; x < 32; x++) {
			for (int y = 0; y < 32; y++) {
				render(x, y);
			}
		}
	}

	public void render(int x, int y) {
		if(x < 0 || y < 0)
			return;
		
		Chunk chunk = chunks[y * CHUNK_SIZE + x];
			GL11.glCallList(chunk.getList(x, y));
	}
	
	public void tick()
	{
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Chunk chunk = getChunk(x, y);
				chunk.tick(x, y);
			}
		}
	}
	
	public void gen(File dir)
	{
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Chunk chunk = (chunks[y * CHUNK_SIZE + x] = new Chunk());
				chunk.load(x, y, dir, getNoiseProvider());
		}
		}
	}
	
	public void save(File stream) throws Exception
	{
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				Chunk chunk = (chunks[y * CHUNK_SIZE + x]);
				if(chunk != null)
				chunk.save(x, y, stream);
		}
		}
	}

	@Override
	public SimplexNoise getNoiseProvider() {
		// TODO Auto-generated method stub
		return noise;
	}

}
