package voxelengine.datafile;

import java.io.DataInput;
import java.io.DataOutput;

import voxelengine.info.Info;

public class DataString extends Data{

	String str = "";
	
	public DataString()
	{
		this("");
	}
	
	public DataString(String str)
	{
		this.str = str;
	}
	
	public String getValue()
	{
		return str;
	}
	
	@Override
	public void load(DataInput stream) throws Exception {
		byte[] rawBit = new byte[stream.readShort()];
		stream.readFully(rawBit);
		str = new String(rawBit, Info.CHARSET);
	}

	@Override
	public void save(DataOutput stream) throws Exception {
		stream.writeShort(str.getBytes().length);
		stream.write(str.getBytes());
	}

}
