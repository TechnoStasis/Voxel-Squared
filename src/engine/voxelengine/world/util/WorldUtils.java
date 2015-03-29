package voxelengine.world.util;

import voxelengine.info.Info;

public class WorldUtils {

	private WorldUtils()
	{
		
	}
	
	public static int convertToChunkCoord(int coord)
	{
		return coord / Info.CHUNK_SIZE;
	}
	
}
