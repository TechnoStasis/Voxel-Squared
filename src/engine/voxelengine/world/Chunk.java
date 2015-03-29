package voxelengine.world;

import java.io.File;

import org.lwjgl.opengl.GL11;

import voxel.core.VoxelSquared;
import voxelengine.arrays.Array2D;
import voxelengine.datafile.Data;
import voxelengine.datafile.DataHolder;
import voxelengine.datafile.DataIntArray;
import voxelengine.datafile.DataUtils;
import voxelengine.info.Info;
import voxelengine.noise.SimplexNoise;
import voxelengine.registry.BlockRegistry;
import voxelengine.render.RenderHelper;

public class Chunk {

	private final int SIZE = Info.CHUNK_SIZE;

	/** The array that holds the block data */
	private Array2D data = new Array2D(SIZE, SIZE);
	
	//Snapshot used to check if list rebuilding is required.
	private Array2D dataSnapshot = null;
	
	public boolean genned = false;

	public void setBlock(int x, int y, Block tile) {
		setBlock(x, y, tile, false);
	}
	
	public void setBlock(int chunk_x, int chunk_y, Block tile, boolean genList) {
		
		data.set(chunk_x, chunk_y, BlockRegistry.getIDFromBlock(tile));
		if(genList)
		this.genList(chunk_x, chunk_y);
	}

	public Block getBlock(int x, int y)
	{
		return BlockRegistry.getBlockFromID(data.get(x, y));
	}
	
	public void tick(int x, int y)
	{
		if(dataSnapshot == null || dataSnapshot != null && dataSnapshot.equals(data))
		{
			genList(x, y);
			dataSnapshot = data;
		}
	}
	
	private int renderID = -1;
	
	public void genList(int x, int y)
	{
		renderID = RenderHelper.genID();
		
		GL11.glNewList(renderID, GL11.GL_COMPILE);
		for (int cx = 0; cx < Info.CHUNK_SIZE; cx++) {
			for (int cy = 0; cy < Info.CHUNK_SIZE; cy++) {
				Block tile = getBlock(cx, cy);
				if (tile != null) {
					tile.render((x * Info.CHUNK_SIZE) + (cx * Info.TILE_SIZE), (y * Info.CHUNK_SIZE) + (cy * Info.TILE_SIZE));
				}
			}
		}
		GL11.glEndList();
	}
	
	public int getList(int x, int y)
	{
		if(renderID == -1)
			genList(x, y);
		
		return renderID;
	}
	
	public void gen(int cx, int cy, SimplexNoise noiseGenerator) {
		genned = true;
		for (int x = 0; x < Info.CHUNK_SIZE; x++) {
			for (int y = 0; y < Info.CHUNK_SIZE; y++) {
				double noise = (1+noiseGenerator.getNoise(x * Info.TILE_SIZE + (cx * Info.CHUNK_SIZE), y * Info.TILE_SIZE + (cy * Info.CHUNK_SIZE))) / 2;
				
				//System.out.println(noise);
				
				if(noise < 0.700F)
					setBlock(x, y, VoxelSquared.grass, false);
				if(noise < 0.500F)
					setBlock(x, y, VoxelSquared.basicTile, false);
				if(noise < 0.400F)
					setBlock(x, y, VoxelSquared.greyTile, false);
			}
		}
		
		genList(cx, cy);
	}

	public boolean setChunkData(int[] data)
	{
		this.data.set(data);
		return true;
	}
	
	public void load(int x, int y, File folder, SimplexNoise gen)
	{
		try {
			File file = new File(folder, "chunk_" + x+"_"+y+".txt");
			DataHolder holder = DataUtils.loadList(file);
			Data array = holder.getData("blockID");
			if(array != null && array instanceof DataIntArray)
			{
				VoxelSquared.logger.info("Data found for chunk, loading.");
				DataIntArray data = (DataIntArray) array;
				this.data.set(data.getData());
			} else
			{
				System.out.println(array == null);
			}
		} catch(Exception e)
		{
			VoxelSquared.logger.info("Caught exception reading chunk, generating new one.");
			gen(x, y, gen);
		}
	}
	
	public void save(int x, int y, File folder) {
		try {
			DataHolder holder = new DataHolder();
			holder.setData("blockID", new DataIntArray(data.getData()));
			
			DataUtils.saveList(new File(folder, "chunk_" + x+"_"+y+".txt"), holder);
		} catch(Exception e)
		{
			VoxelSquared.logger.err("Caught exception saving chunk at %s, %s", x, y);
			e.printStackTrace();
		}
		
	}
}
