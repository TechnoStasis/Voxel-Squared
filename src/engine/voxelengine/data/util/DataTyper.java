package voxelengine.data.util;

import voxelengine.data.Data;
import voxelengine.data.DataIntArray;
import voxelengine.data.DataList;
import voxelengine.data.DataString;
import voxelengine.registry.Registry;

public class DataTyper {

	private static final Registry<Class<? extends Data>> dataTypeRegistry = new Registry<Class<? extends Data>>();
	
	static {
		dataTypeRegistry.register(DataList.class);
		dataTypeRegistry.register(DataString.class);
		dataTypeRegistry.register(DataIntArray.class);
	}
	
	@Deprecated
	public static byte getIDFromData(Data dat)
	{
		if(dat.getClass().equals(DataList.class))
			return 1;
		else if(dat.getClass().equals(DataString.class))
			return 2;			
		else return 0;
	}
	
	/**
	 * What a mouthful.
	 * @param bit
	 * @return
	 * @deprecated use getDataInstance
	 */
	@Deprecated
	public static Data getNewDataInstanceFromID(byte bit)
	{
		switch(bit)
		{
		case 1:
			return new DataList();
		case 2:
			return new DataString();
		
		default: return null;
		}
	}
	
	public static Data getDataInstance(byte id) throws Exception	
	{
		return dataTypeRegistry.getObjFromID((int) id).newInstance();
	}
	
	public static int getDataID(Data dat)
	{
		return dataTypeRegistry.getIDFromObj(dat.getClass());
	}
}
