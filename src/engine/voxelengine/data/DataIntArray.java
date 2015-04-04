package voxelengine.data;

import java.io.DataInput;
import java.io.DataOutput;

public class DataIntArray extends Data{

	private int[] data;
	
	public DataIntArray(int[] array)
	{
		data = array;
	}
	
	public DataIntArray()
	{
		
	}
	
	public void setData(int[] data)
	{
		this.data = data;
	}
	
	public int[] getData()
	{
		return data;
	}
	
	@Override
	public void load(DataInput stream) throws Exception {

		int length = stream.readShort();
		
		data = new int[length];
		
		for(int i = 0; i < length; i++)
		{
			data[i] = stream.readInt();
		}
		
	}

	@Override
	public void save(DataOutput stream) throws Exception {
		
		int length = data.length;
		
		stream.writeShort(length);
		
		for(int i = 0; i < length; i++)
		{
			stream.writeInt(data[i]);
		}
		
	}

}
