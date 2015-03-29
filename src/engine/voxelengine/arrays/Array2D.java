package voxelengine.arrays;

public class Array2D {

	private int[] data = null;
	
	private int maxX;
	private int maxY;
	
	public Array2D(int maxSizeX, int maxSizeY)
	{
		maxX = maxSizeX;
		maxY = maxSizeY;
		data = new int[maxX*maxY];
	}

	public void set(int[] data)
	{
		this.data = data;
	}
	
	public void set(int x, int y, int val){
		if(validate(x,y))
		{
			int pos = position(x,y);
			data[pos] = val;
		} else
		{
			throw new IllegalArgumentException(String.format("Invalid Arguments: X: %s, Y:%s VALUE:%s ", x, y, val));
		}
	}
	public int get(int x, int y)
	{
		if(validate(x, y))
			return data[position(x, y)];
		
		return 0;
	}

	
	public int[] getData()
	{
		return data;
	}
	
	/**
	 * Gets the array position for the two dimensional coords
	 * @param x
	 * @param y
	 * @return Array position.
	 */
	private int position(int x, int y)
	{
		return y * maxX + x;
	}
	
	private boolean validate(int x, int y)
	{
		return x >= 0 && y >= y && x <= maxX && y <= maxY;
	}
	
	public boolean isEquals(Object obj)
	{
		return obj instanceof Array2D && ((Array2D)obj).getData() == this.getData();
	}
}
