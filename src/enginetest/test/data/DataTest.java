package test.data;

import java.io.File;

import voxelengine.datafile.DataHolder;
import voxelengine.datafile.DataIntArray;
import voxelengine.datafile.DataUtils;

public class DataTest {

	static File file = new File("test.txt");
	
	public static void main(String[] args) {
		write();
		read();
	}
	
	public static void write()
	{
		DataHolder holder = new DataHolder();
		holder.setData("haha", new DataIntArray(new int[]{1}));
		DataUtils.saveList(file, holder);
	}
	
	public static void read()
	{
		DataHolder holder = DataUtils.loadList(file);
		System.out.println(((DataIntArray)holder.getData("haha")).getData()[0]);
	}

}
