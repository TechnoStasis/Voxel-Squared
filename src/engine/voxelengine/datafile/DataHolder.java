package voxelengine.datafile;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashMap;
import java.util.Iterator;

public class DataHolder extends Data {

	public HashMap<String, Data> dataMap = new HashMap<String, Data>();

	public DataHolder() {
		dataMap.clear();
	}

	public Data getData(String id)
	{
		return dataMap.get(id);
	}
	
	public void setData(String id, Data data)
	{
		dataMap.put(id, data);
	}
	
	
	@Override
	public void load(DataInput stream) throws Exception {
		while (true)
		{   Data dat = null;		
			
			byte bit = stream.readByte();
			if(bit == 0)
				break;
			
			dat = DataTyper.getDataInstance(bit);
		
			String id = stream.readUTF();
			dat.load(stream);
			
			dataMap.put(id, dat);
		}
	}

	@Override
	public void save(DataOutput stream) throws Exception{
		Iterator<String> idIter = dataMap.keySet().iterator();

		while (idIter.hasNext()) {
			String id = idIter.next();
			Data data = dataMap.get(id);
				stream.writeByte(DataTyper.getDataID(data));
				stream.writeUTF(id);
				data.save(stream);

		}
		
		stream.writeByte(0);

	}
}
