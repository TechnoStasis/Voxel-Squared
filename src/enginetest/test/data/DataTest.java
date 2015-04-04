package test.data;

import java.io.File;

import voxelengine.data.DataIntArray;
import voxelengine.data.DataList;
import voxelengine.data.util.DataUtils;

public class DataTest {

	static File file = new File("test.txt");
	
	public static void main(String[] args) {
		write();
		read();
	}
	
	public static void write()
	{
		DataList holder = new DataList();
		holder.setData("haha", new DataIntArray(new int[]{1}));
		DataUtils.saveList(file, holder);
	}
	
	public static void read()
	{
		DataList holder = DataUtils.loadList(file);
		System.out.println(((DataIntArray)holder.getData("haha")).getData()[0]);
	}

}
