package modloader.identity;

import java.io.File;
import java.util.Objects;

/**
 * 
 * Holds information for a specific modification, holds its name and
 * class file. Tracked by the mod loader.
 *  */
public class ModInfoContainer {

	/**
	 * Do. not. use.
	 */
	public File modFile;
	public String modName;
	public Class<?> modClass;
	
	private ModInfoContainer(Class<?> clazz, String name)
	{
		//modFile = mod;
		modClass = Objects.requireNonNull(clazz);
		modName = Objects.requireNonNull(name);
	}
	
	/**
	 * Creates a mod container instance.
	 * 
	 * @param modClass Class that contains the mod annotaton and the initializing annotation
	 * @param name The mod's name.
	 * @return A mod info container with the parameters.
	 */
	public static ModInfoContainer createModContainer(Class<?> modClass, String name)
	{
		return new ModInfoContainer(modClass, name);
	}
	
}
