package voxelengine.world.util;

import voxelengine.data.DataIntArray;
import voxelengine.data.DataList;
import voxelengine.world.chunk.Chunk;
import voxelengine.world.generation.ITerrainGenerator;

public class ChunkUtils {

	public static Chunk loadOrGenerateChunk(int x, int y, DataList holder, ITerrainGenerator gen)
	{
		Chunk chunk = loadChunk(x, y, holder);
		
		if(chunk == null){
			chunk = new Chunk(); 
			gen.generate(x, y, chunk);
		}
		
		return chunk;
	}
	
	public static Chunk loadChunk(int cx, int cy, DataList holder) {
		try {
			DataIntArray chunk = (DataIntArray) holder.getData(String.format("Chunk:%s,%s", cx, cy));
			
			Chunk builtChunk = new Chunk();
			
			builtChunk.setChunkData(chunk.getData());
			
			return builtChunk;
			
		} catch (Exception e) {
			System.out.println("Unable to load chunk");
			e.printStackTrace();
		}
		return null;
	}

	public static DataList saveChunk(int cx, int cy, DataList mainDataHolder, Chunk chunk) {

		if(mainDataHolder == null)
		{
			mainDataHolder = new DataList();
		}
		
		try {
			DataIntArray data = new DataIntArray(chunk.getChunkData());
			
			mainDataHolder.setData(String.format("Chunk:%s,%s", cx, cy), data);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mainDataHolder;
	}

}
