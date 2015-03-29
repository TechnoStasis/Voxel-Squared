package voxelengine.launch;

import java.net.URLClassLoader;

import modloader.core.ModLoader;

public class LaunchData {
	
	public static final LaunchData instance = new LaunchData();
	
	public TrackedClassLoader classLoader;
	public ModLoader modLoader;
	
	public String os;
	public String vm;

	LaunchData() {
		this.getClass().getClassLoader();
		URLClassLoader loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
		classLoader = new TrackedClassLoader(loader.getURLs(), loader);
		modLoader = new ModLoader(classLoader);
		
		os = System.getProperty("os.name");
		vm = System.getProperty("java.vm.name");
	}
}
