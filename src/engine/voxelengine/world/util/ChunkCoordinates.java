package voxelengine.world.util;

public class ChunkCoordinates {

	private long x;
	private long y;
	
	public ChunkCoordinates(long x, long y)
	{
		this.x = x;
		this.y = y;
	}
	
	public long x()
	{
		return x;
	}
	
	public long y()
	{
		return y;
	}
	
	public boolean equals(Object obj)
	{
		return obj instanceof ChunkCoordinates && ((ChunkCoordinates)obj).x() == this.x() && ((ChunkCoordinates)obj).y() == this.y();
	}
}
