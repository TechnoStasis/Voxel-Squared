package voxelengine.datafile;

import java.io.DataInput;
import java.io.DataOutput;

/**
 * A list based data format based off of Markus Persson's NBT format.
 * @author X1000
 *
 */
public abstract class Data {

	public Data()
	{
		
	}

	public abstract void load(DataInput stream) throws Exception;
	
	public abstract void save(DataOutput stream) throws Exception;
	
	
}
