package voxelengine.data.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import voxelengine.data.DataList;
import voxelengine.log.LogManager;
import voxelengine.log.Logger;

/**
 * List of data related utilities for reading and writing, mostly centered around the Data format.
 * @author X1000
 *
 */
public class DataUtils {

	DataUtils()
	{
		
	}
	
	private static final Logger log = LogManager.requestLogger("Data Utilities");
	
	/** 
	 * Reads a string from an input stream.
	 * 
	 * @param stream The InputStream used for reading.
	 * @param charLimit The character limit, any characters(a.k.a. letters) past this limit will not be read.
	 * @return the resulting string.
	 */
	public static String readString(InputStream stream, int charLimit)
	{
		try {
			InputStreamReader reader = new InputStreamReader(stream);
			char[] chars = new char[charLimit];
			reader.read(chars);
			
			return new String(chars);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Saves a data holder to a file.
	 * @param file
	 * @param hold
	 */
	public static void saveList(File file, DataList hold) {
		try {
			
			DataOutputStream dataOutput = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(file))));

			hold.save(dataOutput);
			
			dataOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Loads a data holder type from a file.
	 * @param file
	 * @return
	 */
	public static DataList loadList(File file)
	{
		
		if(!file.exists())
		{
			log.info("File does not exist: " + file.getName());
			return null;
		}
		
		try {
			DataInputStream dataInput = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
			DataList holder = new DataList();
			holder.load(dataInput);
			
			dataInput.close();
			
			return holder;
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
