package voxelengine.util;

import java.lang.reflect.Method;

public class ReflectionUtils {

	private ReflectionUtils()
	{
		
	}
	
	public static Method[] getClassMethods(Class<?> claz)
	{
		return claz.getMethods();
	}
	
}
