package voxelengine.registry;

import java.util.HashMap;

/**
 * A registry class that allows the registration of a target object.
 * Stores the object with an id, and stores the id with an object, for easy retrieval of id and objects.
 * 
 * @author X1000
 *
 * @param <O> Target object for registration.
 */
public class Registry<O> {

	/** Stores ids with corresponding object. */
	public HashMap<Integer, O> idObjects;
	/** Stores objects with the corresponding id. */
	public HashMap<Object, Integer> objectIDs;
	
	int id = 0;
	
	public Registry()
	{
		idObjects = new HashMap<Integer, O>();
		objectIDs = new HashMap<Object,Integer>();
		id = 0;
	}
	
	public void register(O obj)
	{
		idObjects.put(id, obj);
		objectIDs.put(obj, id);
		id++;
	}
	
	public O getObjFromID(Integer id)
	{
		return idObjects.get(id);
	}
	
	public int getIDFromObj(O obj)
	{
		return objectIDs.get(obj);
	}
}
