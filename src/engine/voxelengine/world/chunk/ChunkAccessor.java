package voxelengine.world.chunk;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import voxelengine.data.DataList;
import voxelengine.data.util.DataUtils;
import voxelengine.world.generation.ITerrainGenerator;
import voxelengine.world.util.ChunkCoordinates;
import voxelengine.world.util.ChunkUtils;

/**
 * Chunk accessor that provides access to chunks;
 * @author X1000
 *
 */
public class ChunkAccessor {

	private HashMap<Integer, Chunk> loadedChunks = new HashMap<Integer, Chunk>();
	
	private File saveDir = null;
	private File saveFile = null;
	
	/** Main list where data is held.*/
	DataList list = null; //TODO Split up to prevent data overwrite.
	
	public ChunkAccessor()
	{
		saveDir = new File("saves");
		
		saveDir.mkdirs();
		saveFile = new File(saveDir, "chunk_data.txt");
		list = DataUtils.loadList(saveFile);
		
		if(list == null)
			list = new DataList();
	}
	
	public void resetChunks()
	{
		loadedChunks = new HashMap<Integer, Chunk>();
	}
	
	public boolean chunkIsLoaded(int x, int y)
	{
		return loadedChunks.get(new ChunkCoordinates(x, y)) != null;
	}
	
	public Chunk loadChunk(int x, int y, ITerrainGenerator gen)
	{
		if(loadedChunks.get(new ChunkCoordinates(x, y).hashCode()) == null)
		{
			System.out.println("Loading chunk");
			loadedChunks.put(new ChunkCoordinates(x, y).hashCode(), ChunkUtils.loadOrGenerateChunk(x, y, list, gen));
		}
		
		return loadedChunks.get(new ChunkCoordinates(x, y).hashCode());
	}

	public boolean unloadChunk(int x, int y)
	{
		saveChunk(x, y);
		
		return loadedChunks.remove(new ChunkCoordinates(x, y).hashCode()) != null;
	}
	
	//Updates the chunk data with the chunk's updates data;
	public boolean saveChunk(int x, int y)
	{
		Chunk chunk = loadedChunks.get(new ChunkCoordinates(x, y).hashCode());
		
		if(chunk != null)
		{
			list = ChunkUtils.saveChunk(list, chunk);
			return true;
		}
		
		return false;
	}
	
	public void loadCenterChunk(ITerrainGenerator gen)
	{
		loadChunk(0,0,gen);
	}
	
	/**
	 * Saves all LOADED chunks, unloaded chunks are untouched.
	 */
	public void saveAll()
	{
		
		Iterator<Integer> iter = loadedChunks.keySet().iterator();
		
		while(iter.hasNext())
		{
			Integer coords = iter.next();
			
			Chunk chunk = loadedChunks.get(coords);
			
			if(chunk != null)
			{
				list = ChunkUtils.saveChunk(list, chunk);
				
			}
		}
		
		DataUtils.saveList(saveFile, list);
	}
	
}
