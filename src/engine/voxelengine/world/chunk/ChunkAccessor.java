package voxelengine.world.chunk;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import voxelengine.data.DataList;
import voxelengine.data.util.DataUtils;
import voxelengine.world.util.ChunkCoordinates;
import voxelengine.world.util.ChunkUtils;

/**
 * Chunk accessor that provides access to chunks;
 * @author X1000
 *
 */
public class ChunkAccessor {

	private HashMap<ChunkCoordinates, Chunk> loadedChunks = new HashMap<ChunkCoordinates, Chunk>();
	
	private File saveFile = new File("saves/chunkData.txt");
	
	/** Main list where data is held.*/
	DataList list = null; //TODO Split up to prevent data overwrite.
	
	public ChunkAccessor()
	{
		saveFile.mkdirs();
		list = DataUtils.loadList(saveFile);
		
		if(list == null)
			list = new DataList();
	}
	
	public boolean chunkIsLoaded(int x, int y)
	{
		return loadedChunks.get(new ChunkCoordinates(x, y)) != null;
	}
	
	public Chunk loadChunk(int x, int y)
	{
		if(loadedChunks.get(new ChunkCoordinates(x, y)) == null)
		{
			loadedChunks.put(new ChunkCoordinates(x, y), ChunkUtils.loadChunk(x, y, list));
		}
		
		return loadedChunks.get(new ChunkCoordinates(x, y));
	}
	
	public void unloadChunk(int x, int y)
	{
		saveChunk(x, y);
		
		loadedChunks.remove(new ChunkCoordinates(x, y));
	}
	
	public boolean saveChunk(int x, int y)
	{
		Chunk chunk = loadedChunks.get(new ChunkCoordinates(x, y));
		
		if(chunk != null)
		{
			DataList holder = ChunkUtils.saveChunk(x, y, list, chunk);
			DataUtils.saveList(saveFile, holder);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Saves all LOADED chunks, unloaded chunks are untouched.
	 */
	public void saveAll()
	{
		
		Iterator<ChunkCoordinates> iter = loadedChunks.keySet().iterator();
		
		while(iter.hasNext())
		{
			ChunkCoordinates coords = iter.next();
			
			Chunk chunk = loadedChunks.get(coords);
			
			if(chunk != null)
			{
				ChunkUtils.saveChunk((int)coords.x(), (int)coords.y(), list, chunk);
			}
		}
		
		DataUtils.saveList(saveFile, list);
	}
	
}
