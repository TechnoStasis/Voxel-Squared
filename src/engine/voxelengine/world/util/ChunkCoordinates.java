package voxelengine.world.util;

public class ChunkCoordinates {

	private Long x;
	private Long y;
	
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
	
	public int hashCode()
	{
		
		return x.hashCode()*3 + y.hashCode() >> 32;
	}
	
	public boolean equals(Object obj)
	{
		return ((ChunkCoordinates)obj).x() == this.x() && ((ChunkCoordinates)obj).y() == this.y();
	}
}
