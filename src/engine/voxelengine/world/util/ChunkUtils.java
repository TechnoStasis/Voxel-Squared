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
			System.out.println("genning chunk");
			chunk = new Chunk(x, y); 
			gen.generate(x, y, chunk);

			return chunk;
		}
		
		return chunk;
	}
	
	public static Chunk loadChunk(int cx, int cy, DataList holder) {
		try {
			DataIntArray chunkData = (DataIntArray) holder.getData(String.format("Chunk:%s,%s", cx, cy));
			
			if(chunkData == null)
				return null;
			
			Chunk builtChunk = new Chunk(cx, cy);
			
			builtChunk.setChunkData(chunkData.getData());
			
			return builtChunk;
			
		} catch (Exception e) {
			//e.printStackTrace();
			return null;
		}
	}

	public static DataList saveChunk(DataList mainDataHolder, Chunk chunk) {

		if(mainDataHolder == null)
		{
			mainDataHolder = new DataList();
		}
		
		try {
			DataIntArray data = new DataIntArray(chunk.getChunkData());
			
			mainDataHolder.setData(String.format("Chunk:%s,%s", chunk.toCoords().x(), chunk.toCoords().y()), data);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mainDataHolder;
	}

}
