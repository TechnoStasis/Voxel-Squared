package voxelsquared.client.render.world;

import voxelengine.arrays.Array2D;
import voxelengine.info.Info;
import voxelengine.render.RenderHelper;
import voxelengine.render.Square;
import voxelengine.world.Block;
import voxelengine.world.World;
import voxelengine.world.chunk.Chunk;

/**
 * utility class that handles the rendering of individual chunks.
 * @author X1000
 *
 */
public class WorldRenderer {

	int chunkRenderID = RenderHelper.genID();
	
	Array2D renderingChunkData = null;
	
	public void renderChunk(int chunkX, int chunkY, World world)
	{
		Chunk chunk = world.getChunk(chunkX, chunkY);
		//if(renderingChunkData ==  null || !chunk.getData().equals(renderingChunkData))
		{
			//chunkRenderID = RenderHelper.genID();
			//renderingChunkData = chunk.getData();
			
			for(int x = 0; x < Info.CHUNK_SIZE; x++)
			{
				for(int y = 0; y < Info.CHUNK_SIZE; y++)
				{
					Block block = chunk.getBlock(x, y);
					
					Square square = new Square((chunkX*Info.CHUNK_SIZE)+x, (chunkY*Info.CHUNK_SIZE) + y, 1, 1);

					if(chunk.getBlock(x, y) != null) 
					{
						block.getColorBind().bind();
						square.renderSquare();
					}
				}	
			}
		} 
	}

}
