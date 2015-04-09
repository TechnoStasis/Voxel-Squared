package voxelengine.launch;

import java.net.URLClassLoader;

import modloader.core.ModLoader;

public class ModLauncher {
	
	public static final ModLauncher instance = new ModLauncher();
	
	public TrackedClassLoader classLoader;
	public ModLoader modLoader;

	ModLauncher() {

		URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		classLoader = new TrackedClassLoader(loader.getURLs(), loader);
		modLoader = new ModLoader(classLoader);

	}
	
	public void launchModLoader()
	{
		modLoader.loadMods();
	}
}
